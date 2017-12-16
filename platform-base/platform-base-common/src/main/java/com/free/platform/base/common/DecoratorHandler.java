package com.free.platform.base.common;

public interface DecoratorHandler<S, T> {
	public T invoke(S data);
}
