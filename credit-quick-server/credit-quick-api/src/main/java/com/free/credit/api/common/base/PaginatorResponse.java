package com.free.credit.api.common.base;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Min;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.free.platform.base.common.BaseEntity;
import com.free.platform.base.skeleton.PaginatorSevResp;

/**
 * 
 * @描述：api分页返回数据
 *
 * @author 何源
 * @时间  2015年7月31日上午10:15:22
 *
 */
public class PaginatorResponse<E> extends BaseEntity{
	
	private final static Logger logger = LoggerFactory.getLogger(PaginatorResponse.class);
	private static final long serialVersionUID = 3548744260313101555L;
	/**
	 * 页码
	 */
	@Min(value=1, message="页数必须大于0")
	private int pageIndex;
	/**
	 * 页码大小
	 */
	private int pageSize;
	/**
	 * 总页数
	 */
	private int pageCount;
	/**
	 * 数据总条数
	 */
	private int totalCount;
	/**
	 * 数据
	 */
	private List<E> datas = new ArrayList<E>();
	
	public PaginatorResponse(){
		
	}
	
	public PaginatorResponse(PaginatorSevResp<E> pageSevResp){
		pageIndex = pageSevResp.getPageIndex();
		pageSize = pageSevResp.getPageSize();
		pageCount = pageSevResp.getPageCount();
		totalCount =  pageSevResp.getTotalCount();
		if(pageSevResp.getDatas()!=null&&pageSevResp.getDatas().size()>0){
			datas = pageSevResp.getDatas();
		}
	}
	
	public <S> PaginatorResponse(PaginatorSevResp<S> pageSevResp,PaginatorDecoration<S, E> decoration){
		pageIndex = pageSevResp.getPageIndex();
		pageSize = pageSevResp.getPageSize();
		pageCount = pageSevResp.getPageCount();
		totalCount =  pageSevResp.getTotalCount();
		if(pageSevResp.getDatas()!=null&&pageSevResp.getDatas().size()>0){
			for(S data:pageSevResp.getDatas()){
					datas.add(decoration.doInvoke(data));
			}
		}
	}
	
	public <S> PaginatorResponse(PaginatorSevResp<S> pageSevResp, final Class<E> clzz){
		this(pageSevResp,new PaginatorDecoration<S, E>() {
			@Override
			public E doInvoke(Object item) {
				try {
					Constructor<E> con = clzz.getConstructor(item.getClass());
					return con.newInstance(item);
				} catch (Exception e) {
					logger.error(clzz.getName()+"找不到构造函数(type,params={}):{}",item.getClass(),item,e);
				}
				return null;
			}
		});
	}

	public List<E> getDatas() {
		return datas;
	}

	public void setDatas(List<E> datas) {
		this.datas = datas;
	}

	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
