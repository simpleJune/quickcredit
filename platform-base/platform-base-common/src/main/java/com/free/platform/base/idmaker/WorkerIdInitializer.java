package com.free.platform.base.idmaker;

public interface WorkerIdInitializer {

	/**
	 * 获得workerId
	 * 
	 * @param moduleName
	 * @return
	 */
	WorkerId getWorkerId(String moduleName) throws Exception;
}
