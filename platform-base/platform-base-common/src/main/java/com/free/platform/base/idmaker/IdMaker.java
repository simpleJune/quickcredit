package com.free.platform.base.idmaker;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import com.free.platform.base.idmaker.WorkerId.WorkerIdListener;

public class IdMaker implements WorkerIdListener, InitializingBean, ApplicationContextAware {

	private static ApplicationContext applicationContext = null;
	// WorkerId初始器
	private WorkerIdInitializer workerIdInitializer = new WorkerIdDefaultInitializer();
	// 模块名称
	private String moduleName;
	// 数据中心
	private Long dataCenter = 0L;
	// idWorker
	private volatile IdWorker idWorker = null;
	// workerId
	private WorkerId workerId;

	
	/**
	 * 生成id
	 * 
	 * @return
	 */
	public static long nextId() {
		IdMaker idMaker = applicationContext.getBean(IdMaker.class);
		return idMaker.make();
	}

	
	/**
	 * 生成id
	 * 
	 * @return
	 */
	public long make() {
		if(idWorker == null) {
			throw new IllegalStateException("idWorker没有初始化,不能生产id");
		}
		long id = idWorker.nextId();
		return id;
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(workerIdInitializer, "没有指定workerIdInitializer");
		Assert.notNull(dataCenter, "没有指定dataCenter");
		this.workerId = workerIdInitializer.getWorkerId(moduleName);
		this.idWorker = new IdWorker(dataCenter, workerId.getWorkerId());
		this.workerId.addListener(this);
	}
	

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		IdMaker.applicationContext = applicationContext;
	}
	
	@Override
	public void change(WorkerId workerId) {
		if(workerId.getWorkerId() == null)  {
			this.idWorker = null;
		}
		else {
			this.idWorker = new IdWorker(this.dataCenter, workerId.getWorkerId());			
		}
	}


	/**
	 * @return the workerIdInitializer
	 */
	public WorkerIdInitializer getWorkerIdInitializer() {
		return workerIdInitializer;
	}

	/**
	 * @param workerIdInitializer
	 *            the workerIdInitializer to set
	 */
	public void setWorkerIdInitializer(WorkerIdInitializer workerIdInitializer) {
		this.workerIdInitializer = workerIdInitializer;
	}

	/**
	 * @return the moduleName
	 */
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * @param moduleName
	 *            the moduleName to set
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * @return the dataCenter
	 */
	public Long getDataCenter() {
		return dataCenter;
	}

	/**
	 * @param dataCenter
	 *            the dataCenter to set
	 */
	public void setDataCenter(Long dataCenter) {
		this.dataCenter = dataCenter;
	}

}
