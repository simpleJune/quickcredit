package com.free.platform.base.common;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.free.platform.paginator.domain.PageList;
import com.free.platform.paginator.domain.PageRequest;

/**
 * 分页基础dao
 */
public interface BasePageDao<T> extends BaseDao{

	/**
	 * 批量新增
	 * @param 
	 * @return
	 */
	public Integer addBatch(List<T> list);
	
	/**
	 * 批量更新
	 * @param list
	 * @return
	 */
	public Integer updateBatch(List<T> list);
	
	/**
	 * 批量更新
	 * @param list
	 * @return
	 */
	public Integer updateBatchWithEmpty(List<T> list);
	
	/**
	 * 批量删除
	 * @param objects
	 * @return
	 */
	public Integer deleteBatch(Object[] objects);
	
	/**
	 * 新增
	 * @param t
	 * @return
	 */
	public int add(T t);
	
	/**
	 * 根据id更新
	 * @param t
	 * @return
	 */
	public int update(T t);
	
	/**
	 * 根据id更新，不忽略空字符串
	 * @param t
	 * @return
	 */
	public int updateWithEmpty(T t);
	
	/**
	 * 带条件更新 -空字符串不更新
	 * @param t
	 * @return
	 */
	public int updateWithCondIgNull(@Param("oldObj") T oldObj,@Param("newObj") T newObj);
	
	
	/**
	 * 带条件更新-空字符串也更新
	 * @param t
	 * @return
	 */
	public int updateWithCond(@Param("oldObj") T oldObj,@Param("newObj") T newObj);

	/**
	 * 根据主键删除对象
	 * @param id
	 * @return
	 */
	public int deleteByPrimaryKey(Object id);
	
	/**
	 * 根据主键获取对象
	 * @param id
	 * @return
	 */
	public T getByPrimaryKey(Object id);

	/**
	 * 分页
	 * @param t
	 * @return
	 */
	public PageList<T> list(PageRequest request);
	
	/**
	 * 条件查询
	 * @param t
	 * @return
	 */
	public List<T> list(T t);
	
	/**
	 * 条件查询
	 * @param t
	 * @return
	 */
	public T query(T t);
	
	/**
	 * 条件查询数量
	 * @param t
	 * @return
	 */
	public Integer count(T t);

}
