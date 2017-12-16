package com.free.platform.base.idmaker;

import java.util.ArrayList;
import java.util.List;

public class WorkerId {

	private List<WorkerIdListener> listeners = new ArrayList<WorkerIdListener>();
	// worker编号
	private Long workerId;

	public WorkerId(Long workerId) {
		this.workerId = workerId;
	}

	/**
	 * 增加workerId监听器
	 * 
	 * @param listener
	 */
	public void addListener(WorkerIdListener listener) {
		this.listeners.add(listener);
	}

	/**
	 * 修改workerId
	 *
	 * @param workerId
	 */
	public void change(Long workerId) {
		this.workerId = workerId;
		for (WorkerIdListener listener : listeners) {
			listener.change(this);
		}
	}

	/**
	 * @return the listeners
	 */
	public List<WorkerIdListener> getListeners() {
		return listeners;
	}

	/**
	 * @param listeners
	 *            the listeners to set
	 */
	public void setListeners(List<WorkerIdListener> listeners) {
		this.listeners = listeners;
	}

	/**
	 * @return the workerId
	 */
	public Long getWorkerId() {
		return workerId;
	}

	/**
	 * @param workerId
	 *            the workerId to set
	 */
	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	public interface WorkerIdListener {

		/**
		 * WorkerId发生改变
		 *
		 * @param workerId
		 */
		void change(WorkerId workerId);
	}
}
