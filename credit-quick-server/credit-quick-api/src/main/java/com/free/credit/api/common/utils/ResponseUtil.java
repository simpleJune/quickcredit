package com.free.credit.api.common.utils;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;

import com.free.credit.api.common.base.BaseResponse;
import com.free.credit.api.common.base.ErrorResponse;
import com.free.credit.api.common.base.MapEntity;
import com.free.credit.api.common.base.PaginatorResponse;
import com.free.credit.api.common.base.SuccessResponse;
import com.free.credit.common.enums.CreditResultCode;
import com.free.platform.base.common.BaseEntity;
import com.free.platform.base.common.IEnum;
import com.free.platform.base.enums.ResultCode;
import com.free.platform.base.skeleton.PaginatorSevResp;
/**
 * 
 * @描述：api接口返回处理类
 *
 * @author 何源
 * @时间  2015年8月20日下午1:59:49
 *
 */
public final class ResponseUtil {
    protected static final Logger logger = LoggerFactory.getLogger(ResponseUtil.class);
    /**
     * 无数据返回
     */
    private static final SuccessResponse<BaseEntity> success = new SuccessResponse<BaseEntity>();
    /**
     * 服务器异常错误
     */
    private static final ErrorResponse errorServ =  new ErrorResponse(ResultCode.EXCEPTION_UNKWOWN.getKey(), ResultCode.EXCEPTION_UNKWOWN.getValue());

	/**
	 * 参数错误
	 */
	@SuppressWarnings("unused")
	private static final ErrorResponse errorParams =  new ErrorResponse(ResultCode.EXCEPTION_PARAMS.getKey(), ResultCode.EXCEPTION_PARAMS.getValue());

	/**
	 * token失效
	 */
	private static final ErrorResponse errorToken =  new ErrorResponse(CreditResultCode.EXCEPTION_TOKEN_INVALID.getKey(), CreditResultCode.EXCEPTION_TOKEN_INVALID.getValue());
	
	/**
	 * token失效
	 */
	private static final ErrorResponse errorInvalideReq =  new ErrorResponse(ResultCode.EXCEPTION_INVALIDE_REQ.getKey(), ResultCode.EXCEPTION_INVALIDE_REQ.getValue());
	
	
    /**
     * 无数据返回
     * @return
     */
    public static BaseResponse getSuccessResponse(){
        return success;
    }
    
    /**
     * 返回成功
     * @param data 源数据
     * @return
     */
    public static <T extends BaseEntity>BaseResponse getSuccessResponse(T data){
        return new SuccessResponse<BaseEntity>(data);
    }
    
    
    /**
     * 返回成功
     *
     * @param key 键
     * @param data 数据
     * @param clazz 装饰器 必须有构造函数
     * @return
     * @创建人 何源
     * @创建时间 2016年5月17日下午2:46:29
     *
     */
    @SuppressWarnings("unchecked")
    public static <S,T>BaseResponse getSuccessResponse(String key,List<S> data,Class<T> clazz){
    	MapEntity<String,Object> ret = new MapEntity<String,Object>();
    	String mapKey = key==null?"list":key;
        if(clazz!=null){
            List<T> list = new LinkedList<T>();
            List<Object> datas = (List<Object>)data;
            if(datas!=null&&datas.size()>0){
                for(Object obj:datas){
                    try {
                        Constructor<T> con = clazz.getConstructor(obj.getClass());
                        T retObj = con.newInstance(obj);
                        list.add(retObj);
                    } catch (Exception e) {
                        logger.error(clazz.getName()+"找不到构造函数:",e);
                    }
                }
            }
            ret.put(mapKey,list);
        }else{
            ret.put(mapKey,data);
        }
        return new SuccessResponse<BaseEntity>(ret);
    }
    
    
    /**
     * 
     *
     * @param key  键
     * @param data 类型为 String Boolean Byte Short Integer Long Float Double Date 的数据
     * @return
     * @创建人 何源
     * @创建时间 2016年4月20日上午10:55:51
     *
     */
    public static <T>BaseResponse getSuccessResponse(String key,Object data){
        MapEntity<String,Object> ret = new MapEntity<String,Object>();
        ret.put(key, data);
        return new SuccessResponse<BaseEntity>(ret);
    }
    
    /**
     * 返回分页成功
     * @param datas 源数据
     * @param clazz 装饰器
     * @return
     */
    public static <T>BaseResponse getSuccessResponse(PaginatorSevResp<T> datas){
        return  getSuccessResponse(new PaginatorResponse<T>(datas));
    }
    
    /**
     * 返回分页成功
     * @param datas 源数据
     * @param clazz 装饰器
     * @return
     */
    public static BaseResponse getSuccessResponse(Map<String,Object> map){
         if(map==null){
        	 return getSuccessResponse();
         }
         BaseEntity data = new MapEntity<String,Object>(map);
    	 return  getSuccessResponse(data);
    }
    
    /**
     * 返回分页成功
     * @param datas 源数据
     * @param clazz 装饰器
     * @return
     */
    public static <T>BaseResponse getSuccessResponse(PaginatorSevResp<?> datas,Class<T> clazz){
        return  getSuccessResponse(new PaginatorResponse<T>(datas,clazz));
    }
    
    /**
     * 获取服务器异常错误
     * @return
     */
    public static ErrorResponse getErrorServ() {
        return errorServ;
    }
    
    
    /**
     * 获取token错误
     * @return
     */
    public static ErrorResponse getErrorToken() {
        return errorToken;
    }
    
    /**
     * 获取token错误
     * @return
     */
    public static ErrorResponse getErrorInvalideReq() {
        return errorInvalideReq;
    }
    
    /**
     * 判断是否存在参数错误
     * @return
     */
    public static boolean existsParamsError(BindingResult... results) {
        if(results!=null&&results.length>0){
            for(BindingResult result:results){
                if(result.hasErrors()){
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 获取参数错误
     * @return
     */
    public static ErrorResponse getErrorParams(BindingResult... results) {
        
        ErrorResponse errorParams =  new ErrorResponse(ResultCode.EXCEPTION_PARAMS.getKey(), ResultCode.EXCEPTION_PARAMS.getValue());
   
        if(results!=null&&results.length>0){
       
            for(BindingResult result:results){
                
                if(result.hasErrors()) {
                    
                    // 取第一条错误消息返回给前端
                    String msg = result.getFieldError().getDefaultMessage();
                    
                    errorParams.setMsg(msg);
                    
                    return errorParams;

                }
            }
        }
        return errorParams;
    }
    // 去掉没必要的error错误消息
//	/**
//	 * 获取参数错误
//	 * @return
//	 */
//	public static ErrorResponse getErrorParams(List<BaseResponse> errors) {
//		errorParams.setErrors(errors);
//		return errorParams;
//	}
	
	/**
	 * 
	 *
	 * @param message 错误消息
	 * @return
	 *
	 */
	public static ErrorResponse getErrorParam(String message) {
		return new ErrorResponse(ResultCode.EXCEPTION_PARAMS.getKey(),message);
	}

    /**
	 * 返回失败
	 * @param code 错误编码
	 * @param message 错误信息
	 * @return
	 */
	public static BaseResponse getError(String code,String message){
		//return getErrorBusi(new BaseResponse(code,message));
		return new ErrorResponse(code,message);
	}
	
	/**
     * 返回失败
     * @param code 错误编码
     * @param message 错误信息
     * @return
     */
    public static BaseResponse getError(int code,String message){
        //return getErrorBusi(new BaseResponse(code,message));
        return new ErrorResponse(code,message);
    }
	
	

	
	
	/**
	 * 借款端service异常处理
	 *
	 * @param resp
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年9月29日下午1:27:59
	 *
	 */
	public static <T>BaseResponse getLoanServError(IEnum resp,Object... params){
		String msg = resp.getValue();
		if(params!=null&&params.length>0){
			msg=String.format(msg, params);
		}
		ErrorResponse errorBusi = new ErrorResponse(resp.getKey(), msg);
		return errorBusi;
	}
	
	
	/**
	 * 业务逻辑错误
	 *
	 * @param error
	 * @return
	 * @创建人 何源
	 * @创建时间 2016年5月12日下午5:43:22
	 *
	 */
	public static BaseResponse getErrorBusi(String errorMsg) {
		ErrorResponse errorBusi =  new ErrorResponse(ResultCode.EXCEPTION_BUSINESS.getKey(), ResultCode.EXCEPTION_BUSINESS.getValue());
		/*List<BaseResponse> errors = new ArrayList<BaseResponse>();
		errors.add(error);
		errorBusi.setErrors(errors);*/
		errorBusi.setMsg(errorMsg);
		return errorBusi;
	}
}
