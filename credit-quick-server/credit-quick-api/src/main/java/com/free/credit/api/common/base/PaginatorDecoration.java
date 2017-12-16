package com.free.credit.api.common.base;
/**
 * 
 * @描述：分页数据包装
 *
 * @author 何源
 * @时间  2015年8月20日下午1:45:06
 *
 */
public interface PaginatorDecoration<S, T> {
	
	public abstract T doInvoke(S item);
}
