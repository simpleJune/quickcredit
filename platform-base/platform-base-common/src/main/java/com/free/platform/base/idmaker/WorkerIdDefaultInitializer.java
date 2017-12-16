package com.free.platform.base.idmaker;

public class WorkerIdDefaultInitializer implements WorkerIdInitializer {

	@Override
	public WorkerId getWorkerId(String moduleName) throws Exception {
		return new WorkerId(0L);
	}

}
