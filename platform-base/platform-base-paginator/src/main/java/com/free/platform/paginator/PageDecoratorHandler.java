package com.free.platform.paginator;

public interface PageDecoratorHandler<T> {
	public T invoke(Object data);
}
