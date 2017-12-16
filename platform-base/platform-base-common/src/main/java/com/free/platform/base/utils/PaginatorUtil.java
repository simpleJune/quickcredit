package com.free.platform.base.utils;

import java.util.ArrayList;
import java.util.List;

import com.free.platform.base.skeleton.PaginatorSevResp;
import com.free.platform.base.common.DecoratorHandler;
import com.free.platform.base.common.IEnum;
import com.free.platform.base.enums.OrderEnum;
import com.free.platform.base.skeleton.PaginatorSevReq;
import com.free.platform.paginator.domain.PageList;
import com.free.platform.paginator.domain.PageRequest;
import com.free.platform.paginator.domain.Paginator;

/**
 * 分页查询工具
 */
public class PaginatorUtil {
	
	/**
	 * 分页组件查询结果转dubbo服务分页出参
	 * @param pageList
	 * @return
	 */
	public static <T>PaginatorSevResp<T> toPaginatorSevResp(PageList<T> pageList) {
		PaginatorSevResp<T> ret = new PaginatorSevResp<T>();
		ret.setPageIndex(pageList.getPaginator().getPage());
		ret.setPageSize(pageList.getPaginator().getLimit());
		ret.setTotalCount(pageList.getPaginator().getTotalCount());
		ret.setPageCount(pageList.getPaginator().getTotalPages());
		ret.setDatas(pageList);
		return ret;
	}
	
	/**
	 * 分页组件查询结果转dubbo服务分页出参
	 * @param pageList
	 * @return
	 */
	public static <S, T>PaginatorSevResp<T> toPaginatorSevResp(PageList<S> pageList,DecoratorHandler<S, T> decorator) {
		PaginatorSevResp<T> ret = new PaginatorSevResp<T>();
		ret.setPageIndex(pageList.getPaginator().getPage());
		ret.setPageSize(pageList.getPaginator().getLimit());
		ret.setTotalCount(pageList.getPaginator().getTotalCount());
		ret.setPageCount(pageList.getPaginator().getTotalPages());
		if(decorator!=null){
			List<T> datas = new ArrayList<T>();
			if (pageList.size() > 0) {
				for (S obj : pageList) {
					datas.add(decorator.invoke(obj));
				}
			}
			ret.setDatas(datas);
		}
		return ret;
	}
	
	
	/**
	 * 分页组件查询结果转dubbo服务分页出参
	 * @param pageList
	 * @return
	 */
	public static <T>PaginatorSevResp<T> getPaginatorSevResp(PageRequest request,List<T> pageList,int count) {
		PaginatorSevResp<T> ret = new PaginatorSevResp<T>();
		Paginator p = new Paginator(request.getPage(),request.getRows(),count);
		ret.setPageIndex(p.getPage());
		ret.setPageSize(p.getLimit());
		ret.setTotalCount(p.getTotalCount());
		ret.setPageCount(p.getTotalPages());
		ret.setDatas(pageList);
		return ret;
	}
	
	/**
	 * 获取空返回
	 * @param pageList
	 * @return
	 */
	public static <T>PaginatorSevResp<T> getEmptyResp(PageRequest request) {
		PaginatorSevResp<T> ret = new PaginatorSevResp<T>();
		ret.setPageIndex(request.getPage());
		ret.setPageSize(request.getRows());
		ret.setTotalCount(0);
		ret.setPageCount(0);
		ret.setDatas(new ArrayList<T>());
		return ret;
	}
	
	
	/**
	 * dubbo服务分页入参 转分页组件查询参数
	 * @param request
	 * @return
	 */
	public static PageRequest toPageRequest(PaginatorSevReq request){
		PageRequest pageRequest = new PageRequest();
		pageRequest.setPage(request.getPageIndex());
		pageRequest.setRows(request.getPageSize());
		pageRequest.setQuery(request.getQueryConditions());
		pageRequest.setContainsTotalCount(request.isContainsTotalCount());
		return pageRequest;
	}
	
	
	/**
	 * 
	 *
	 * @param request 请求名
	 * @param sortClass 排序字段名称
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年8月31日上午11:50:04
	 *
	 */
	public static <T extends IEnum> PageRequest toPageRequest(PaginatorSevReq request,Class<T> sortClass){
		PageRequest pageRequest =toPageRequest(request);
		if(null!=request.getSort()){
			pageRequest.setSort(EnumUtils.getEnumByKey(request.getSort(),sortClass).getValue());
            if(null!=request.getOrder()){
                pageRequest.setOrder(EnumUtils.getEnumByKey(request.getOrder(),OrderEnum.class).getValue());
            } else {
                pageRequest.setOrder(OrderEnum.ASC.getValue());
            }
		}

		return pageRequest;
	}
	
}
