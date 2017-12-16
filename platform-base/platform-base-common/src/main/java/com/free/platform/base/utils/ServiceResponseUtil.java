package com.free.platform.base.utils;

import java.util.ArrayList;
import java.util.List;

import com.free.platform.base.common.DecoratorHandler;
import com.free.platform.base.common.IEnum;
import com.free.platform.base.exception.ServiceException;
import com.free.platform.base.skeleton.PaginatorSevResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.free.platform.base.enums.ResultCode;
import com.free.platform.base.skeleton.ServiceResponse;
import com.free.platform.paginator.domain.PageList;

/**
 * 分页查询工具
 */
public class ServiceResponseUtil {

	private final static Logger logger = LoggerFactory.getLogger(ServiceResponseUtil.class);


	/**
	 * 分页组件查询结果转dubbo服务分页输出
	 *
	 * @param rlt
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年4月19日上午9:55:21
	 *
	 */
	public static <T>ServiceResponse<PaginatorSevResp<T>> createServiceResponseForPageList(PageList<T> rlt){
		return new ServiceResponse<PaginatorSevResp<T>>(PaginatorUtil.toPaginatorSevResp(rlt));
	}

	/**
	 * 分页组件查询结果转dubbo服务分页输出
	 *
	 * @param rlt 分页结果
	 * @param decorator 装神器
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年4月19日上午9:55:29
	 *
	 */
	public static <S, T>ServiceResponse<PaginatorSevResp<T>> createServiceResponseForPageList(PageList<S> rlt,DecoratorHandler<S, T> decorator) {
		return new ServiceResponse<PaginatorSevResp<T>>(PaginatorUtil.toPaginatorSevResp(rlt,decorator));
	}

	/**
	 * 查询结果转dubbo服务输出
	 *
	 * @param rlt
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年4月19日上午9:57:33
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponse(T rlt){
		 return new ServiceResponse<T>(rlt);
	}

	/**
	 * 查询结果转dubbo服务输出
	 *
	 * @param rlt
	 * @param decorator
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年4月25日上午11:53:19
	 *
	 */
	public static <S, T>ServiceResponse<T> createServiceResponse(S rlt,DecoratorHandler<S, T> decorator){
		 return new ServiceResponse<T>(decorator.invoke(rlt));
	}

	/**
	 * 查询结果转dubbo服务输出
	 *
	 * @param rlt
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年4月19日上午9:57:33
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponse(){
		 return new ServiceResponse<T>();
	}

	/**
	 * 查询结果转dubbo服务输出
	 *
	 * @param rlt 需要装饰的数据集合
	 * @param decorator 装饰器
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年4月25日上午11:53:27
	 *
	 */
	public static <S, T>ServiceResponse<List<T>> createServiceResponseForList(List<S> rlt,DecoratorHandler<S, T> decorator){
		List<T> datas = new ArrayList<T>();
		if(decorator!=null){
			if (rlt!=null&&rlt.size() > 0) {
				for (S obj : rlt) {
					datas.add(decorator.invoke(obj));
				}
			}
		}
		return new ServiceResponse<List<T>>(datas);
	}

	/**
	 * 异常转dubbo服务输出
	 *
	 * @param resultCode 异常错误码
	 * @param e 异常
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年4月19日上午10:01:54
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponseForError(ServiceException e){
		 logger.error("{}:{}",e.getMsg(),e);
		 return new ServiceResponse<T>(e.getCode(),e.getMsg());
	}

	/**
	 * 异常转dubbo服务输出
	 *
	 * @param resultCode 异常错误码
	 * @param e 异常
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年4月19日上午10:01:54
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponse(IEnum resultCode,Exception e){
		 logger.error("{}:{}",resultCode,e);
		 return new ServiceResponse<T>(resultCode);
	}

	/**
	 * 异常转dubbo服务输出
	 *
	 * @param resultCode 异常错误码
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年4月19日上午10:01:35
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponse(IEnum resultCode){
		 return new ServiceResponse<T>(resultCode);
	}

	/**
	 * 异常转dubbo服务输出
	 *
	 * @param resultCode 异常错误码
	 * @param msg 异常业务信息
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年4月26日上午9:15:34
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponse(IEnum resultCode,String msg){
		 return new ServiceResponse<T>(resultCode,msg);
	}


	/**
	 * 异常转dubbo服务输出
	 *
	 * @param resultCode 异常错误码
	 * @param msg 异常业务信息
	 * @param e 异常(用于记error日志)
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年4月26日上午9:16:12
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponse(IEnum resultCode,String msg,Exception e){
		 logger.error("{code:{},msg:{}}:{}",resultCode.getKey(),msg,e);
		 return new ServiceResponse<T>(resultCode,msg);
	}
	/**
	 * 异常转dubbo服务输出
	 *
	 * @param msg 异常业务信息
	 * @param e 异常(用于记error日志)
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年4月26日上午9:16:12
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponseForBisError(String msg,Exception e){
		 return createServiceResponse(ResultCode.EXCEPTION_BUSINESS,msg,e);
	}

	/**
	 * 异常转dubbo服务输出
	 *
	 * @param msg 异常业务信息
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年4月26日上午9:16:12
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponseForBisError(String msg){
		 return createServiceResponse(ResultCode.EXCEPTION_BUSINESS,msg);
	}
	
	/**
	 * 输出自定义业务异常
	 *
	 * @param iEnum 异常业务信息
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年4月26日上午9:16:12
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponseForBisError(IEnum iEnum){
		return new ServiceResponse<T>(iEnum.getKey(),iEnum.getValue());
	}
}
