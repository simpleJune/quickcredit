package com.free.credit.api.common.base;

import javax.validation.constraints.Min;

import com.free.platform.base.skeleton.PaginatorSevReq;


public class PaginatorRequest extends BaseRequest {
	private static final long serialVersionUID = 3184528312384118459L;

	/**
	 * 第几页
	 */
	@Min(value=1, message="页数必须大于0")
	private Integer pageIndex;
	
	/**
	 * 页面大小
	 */
	@Min(value=1, message="页面记录数必须大于0")
	private Integer pageSize;
	/**
	 * 排序
	 */
	private Integer sort;
	
	/**
	 * 顺序
	 */
	private Integer order;
	
	public PaginatorSevReq toPaginatorSevReq(){
		PaginatorSevReq ret = new PaginatorSevReq();
		ret.setPageIndex(pageIndex==null?1:pageIndex);
		ret.setPageSize(pageSize==null?10:pageSize);
		ret.setOrder(order);
		ret.setSort(sort);
		return ret;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}


}
