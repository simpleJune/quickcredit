package com.free.credit.api.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.free.platform.base.common.DecoratorHandler;
import com.free.platform.base.common.IEnum;
import com.free.platform.base.enums.ResultCode;
import com.free.platform.base.exception.ServiceException;
import com.free.platform.base.skeleton.PaginatorSevResp;
import com.free.platform.base.skeleton.ServiceResponse;
import com.free.platform.base.utils.PaginatorUtil;
import com.free.platform.base.utils.ServiceResponseUtil;
import com.free.platform.paginator.domain.PageList;

public class ServerResponseUtils {

	/**
	 * 查询结果转dubbo服务输出
	 *
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
	 * 分页组件查询结果转dubbo服务分页输出
	 *
	 * @param rlt 分页结果
	 * @param decorator
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年4月19日上午9:55:29
	 *
	 */
	public static <S, T>ServiceResponse<PaginatorSevResp<T>> createServiceResponseForPageList(PageList<S> rlt,DecoratorHandler<S, T> decorator) {
		return new ServiceResponse<PaginatorSevResp<T>>(PaginatorUtil.toPaginatorSevResp(rlt,decorator));
	}

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
		List<T> datas = responseForList(rlt, decorator);
		return new ServiceResponse<List<T>>(datas);
	}


    /**
     * DTOtoBO 对象转换
     * @author chunlin.li
     *
     * @param rlt
     * @param decorator
     * @param <S>
     * @param <T>
     * @return
     */
    public static <S, T> List<T> responseForList(List<S> rlt, DecoratorHandler<S, T> decorator) {
        List<T> datas = new ArrayList<T>();
        if (decorator != null) {
            if (CollectionUtils.isNotEmpty(rlt)) {
                for (S obj : rlt) {
                    datas.add(decorator.invoke(obj));
                }
            }
        }

        return datas;
    }

	
	/**
	 * 参数错误
	 *
	 * @param msg
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年9月29日上午10:36:03
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponseForParamError(String msg){
		return ServiceResponseUtil.createServiceResponse(ResultCode.EXCEPTION_PARAMS,msg);
	}

	/**
	 * 数据错误
	 *
	 * @param msg
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年9月29日上午11:22:00
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponseForDataError(String msg){
		return ServiceResponseUtil.createServiceResponse(ResultCode.EXCEPTION_UNKWOWN,msg);
	}
	
	/**
	 * 调用远程服务出现异常
	 *
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年9月29日上午11:22:00
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponseForRpcError(ServiceResponse<?> rlt){
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("code", rlt.getKey());
		data.put("msg", rlt.getValue());
		return ServiceResponseUtil.createServiceResponse(ResultCode.EXCEPTION_UNKWOWN,JSONObject.toJSONString(data));
	}
	
	/**
	 * 调用远程服务出现异常
	 *
	 * @param msg
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年9月29日下午12:46:31
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponseForRpcError(int code,String msg){
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("code",code);
		data.put("msg", msg);
		return ServiceResponseUtil.createServiceResponse(ResultCode.EXCEPTION_UNKWOWN,JSONObject.toJSONString(data));
	}
	
	/**
	 * 调用远程服务出现异常
	 *
	 * @param msg
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年9月29日下午12:46:31
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponseForRpcError(String code,String msg){
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("code",code);
		data.put("msg", msg);
		return ServiceResponseUtil.createServiceResponse(ResultCode.EXCEPTION_RPC,JSONObject.toJSONString(data));
	}

    /**
     * 调用远程服务出现异常
     *
     * @param msg
     * @return
     * @创建人 何源
     * @创建时间 2016年9月29日下午12:46:31
     *
     */
    public static <T>ServiceResponse<T> createServiceResponseForRpcError(String msg){
        return ServiceResponseUtil.createServiceResponse(ResultCode.EXCEPTION_RPC, msg);
    }

    /**
     * 调用远程服务返回数据出现业务错误
     *
     * @param msg
     * @return
     * @创建人 何源
     * @创建时间 2016年9月29日下午12:46:31
     *
     */
    public static <T>ServiceResponse<T> createServiceResponseForRpcRspError(String code,String msg){
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("code",code);
        data.put("msg", msg);
        return ServiceResponseUtil.createServiceResponse(ResultCode.EXCEPTION_BUSINESS,JSONObject.toJSONString(data));
    }
	
	
	/**
	 * 远程异常
	 *
	 * @param msg
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年9月29日下午12:46:31
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponseForSystemError(String msg){
		return ServiceResponseUtil.createServiceResponse(ResultCode.EXCEPTION_UNKWOWN,msg);
	}
	
	/**
	 * 创建系统异常
	 *
	 * @param msg
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年9月29日下午12:46:31
	 *
	 */
	public static ServiceException createSystemException(String msg){
		return new ServiceException(ResultCode.EXCEPTION_UNKWOWN,msg);
	}
	
	/**
	 * 创建系统异常
	 *
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年9月29日下午12:46:31
	 *
	 */
	public static ServiceException createBisException(IEnum e){
		return new ServiceException(e);
	}
	
	public static ServiceException createDataException(String msg){
		return new ServiceException(ResultCode.EXCEPTION_UNKWOWN,msg);
	}
	
	/**
	 * 创建系统异常
	 *
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年9月29日下午12:46:31
	 *
	 */
	public static ServiceException createRpcException(IEnum e){
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("code", e.getKey());
		data.put("msg", e.getValue());
		return new ServiceException(ResultCode.EXCEPTION_UNKWOWN,JSONObject.toJSONString(data));
	}
	
	

	/**
	 * 异常服务输出
	 *
	 * @param resultCode 异常错误码
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年4月19日上午10:01:35
	 *
	 */
	public static <T>ServiceResponse<T> createServiceResponseForBisError(IEnum resultCode,Object... params){
		String msg = resultCode.getValue();
		if(params!=null&&params.length>0){
			msg=String.format(msg, params);
		}
		return new ServiceResponse<T>(resultCode,msg);
	}

    /**
     * 业务消息返回
     * @param msg
     * @param <T>
     * @return
     */
    public static <T>ServiceResponse<T> createBisMsgResponse(String msg){
        return createServiceResponseForBisError(ResultCode.EXCEPTION_BUSINESS.setValue(msg));
    }

    /**
     * 基础服务异常返回
     * @param msg
     * @param <T>
     * @return
     */
    public static <T>ServiceResponse<T> createBsMsgResponse(String msg){
        return createServiceResponseForBisError(ResultCode.EXCEPTION_BS.setValue(msg));
    }
}
