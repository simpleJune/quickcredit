package com.free.platform.base.idmaker;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.zookeeper.AsyncCallback.VoidCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.NodeExistsException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class WorkerIdZooKeeperIntializer implements WorkerIdInitializer, InitializingBean, Watcher {
	private Logger logger = LoggerFactory.getLogger(WorkerIdZooKeeperIntializer.class);

	// 超时时间10s
	private static final int SESSION_TIME_OUT = 10000;
	// 连接超时10s
	private static final long CONNECT_TIME_OUT = 20000;
	// 最大workerId编号
	private static final long MAX_WORKER_ID = IdWorker.maxWorkerId;
	// 根节点
	private static final String ROOT = "/workerId";

	private static final List<ACL> ACLs = Ids.OPEN_ACL_UNSAFE;
	
	private ExecutorService executor = Executors.newFixedThreadPool(1);

	private ZooKeeper zk = null;
	// zk服务地址
	private String hosts;
	// workerIdMap
	private Map<String, WorkerId> workerIdMap = new ConcurrentHashMap<>();
	// 初始化锁
	private Object initLock = new Object();
	// 连接监听
	private Object connectMonitor = new Object();
	
	private Object syncMonitor = new Object();

	@Override
	public WorkerId getWorkerId(String moduleName) throws Exception{
		String path = ROOT;
		if (StringUtils.hasText(path)) {
			moduleName = StringUtils.trimAllWhitespace(moduleName);
			path = path + "/" + moduleName;
		}
		WorkerId workerId = workerIdMap.get(path);
		if(workerId == null) {
			workerId = new WorkerId(null);
			workerIdMap.put(path, workerId);
		}
		
		if(workerId.getWorkerId() == null) {
			initializeWorkerId(path, workerId);
		}
		return workerId;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.isTrue(StringUtils.hasText(hosts), "not specify hosts of the zookeeper!");
		connectZooKeeper();
	}

	@Override
	public void process(WatchedEvent event) {
		if(event.getType() == EventType.None) {
			KeeperState state = event.getState();
			logger.info("ZooKeeper event state: " + state);
			switch(state) {
			case SyncConnected: {
				synchronized (connectMonitor) {
					connectMonitor.notifyAll();
				}
				break;
			}
			case AuthFailed: {
				logger.warn("connect to ZooKeeper failed: AuthFailed");
				break;
			}
			case Disconnected: {
				logger.warn("ZooKeeper disconntected!");
				break;
			}
			case Expired: {
				executor.submit(new Runnable() {
					@Override
					public void run() {
						reconnectZooKeeper();					
					}
				});
				break;
			}
			default:
				break;
			}
		}
	}

	private void connectZooKeeper() throws IOException, InterruptedException, KeeperException {
		logger.info("connect to ZooKeeper[" + this.hosts + "] . . . . . .");
		this.zk = new ZooKeeper(this.hosts, SESSION_TIME_OUT, this);
		
		synchronized (connectMonitor) {
			connectMonitor.wait(CONNECT_TIME_OUT);
		}
		// 判断是否连接超时
		if (!zk.getState().isAlive()) {
			throw new IllegalStateException("The ZooKeeper[" + this.hosts + "] connect time out!");
		}
		logger.info("connect to ZooKeeper[" + this.hosts + "] successful!");

		Stat stat = zk.exists(ROOT, false);
		if (stat == null) {
			try {
				zk.create(ROOT, null, ACLs, CreateMode.PERSISTENT);
			} catch (NodeExistsException e) {
			}
		}
	}

	private void reconnectZooKeeper() {
		synchronized (connectMonitor) {
			if(this.zk != null && this.zk.getState().isAlive()) {
				return;
			}
			try {
				this.zk = new ZooKeeper(this.hosts, SESSION_TIME_OUT, this);
				connectMonitor.wait();
			} catch (Exception e1) {
				logger.warn("重连ZooKeeper出错", e1);
			}
			// 连接不成功，继续连接
			if(!zk.getState().isAlive()) {
				reconnectZooKeeper();
			}
			// 重新初始化workerId
			for (String path : workerIdMap.keySet()) {
				logger.info("reinit workerId[" + path + "]");
				WorkerId workerId = workerIdMap.get(path);
				workerId.setWorkerId(null);
				try {
					initializeWorkerId(path, workerId);
				} catch (Exception e) {
					workerId.change(null);
					logger.error("初始化workerId[" + path + "]出错", e);
				}
			}
		}
	}

	private void initializeWorkerId(String path, WorkerId workerId) throws KeeperException, InterruptedException {
		synchronized (initLock) {
			if (workerId.getWorkerId() != null) {
				return;
			}
			Stat stat = zk.exists(path, false);
			if (stat == null) {
				try {
					zk.create(path, null, ACLs, CreateMode.PERSISTENT);
				} catch (NodeExistsException e) {
					// ingore node exist exception
				}
			}
			createWorkerIdNode(path, workerId);
		}
	}

	private  void createWorkerIdNode(final String path, final WorkerId workerId) throws InterruptedException {
		zk.sync(path, new VoidCallback() {
			@Override
			public void processResult(int rc, String path, Object ctx) {
				try {
					if (rc != 0) {
						throw new IllegalStateException("ZooKeeper sync error, result code=[" + rc + "]");
					}
					List<String> children = zk.getChildren(path, false);
					Long id = 0L;
					if (children.size() > 0) {
						Collections.sort(children);
						long last = Long.parseLong(children.get(children.size() - 1));
						if (last < MAX_WORKER_ID) {
							// 未达到最大编号，使用最后的编号
							id = last + 1;
						} else {
							// 最大编号已达到最大，但是中间有空位
							if (children.size() <= MAX_WORKER_ID) {
								for (int i = 0; i < children.size(); i++) {
									id = (long)i;
									if (Long.parseLong(children.get(i)) > id) {
										break;
									}
								}
							} else {
								// 已经达到最大id编号
								id = null;
								logger.warn("The idWorker[" + path + "] has already exceed the max id");
							}
						}
					}
					String idPath = path + "/" + id;
					try {
						zk.create(idPath, null, ACLs, CreateMode.EPHEMERAL);
						workerId.change(id);
					} catch (NodeExistsException e) {
						// 已被创建，重新创建编号
						createWorkerIdNode(path, workerId);
					}
				} catch (Exception e) {
					logger.error("create ZooKeeper workerId node error", e);
					workerId.change(null);
				}
				finally {
					synchronized (syncMonitor) {
						syncMonitor.notifyAll();
					}
				}
			}
		}, null);
		synchronized (syncMonitor) {
			syncMonitor.wait();
		}
	}

	/**
	 * @return the hosts
	 */
	public String getHosts() {
		return hosts;
	}

	/**
	 * @param hosts the hosts to set
	 */
	public void setHosts(String hosts) {
		this.hosts = hosts;
	}

	
	

	

}
