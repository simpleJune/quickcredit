package com.free.platform.paginator;

public interface ResultValueProcessor<T> {
	
	public abstract Object process(T value);
}
