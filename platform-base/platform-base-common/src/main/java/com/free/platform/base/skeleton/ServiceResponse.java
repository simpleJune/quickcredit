package com.free.platform.base.skeleton;

import com.free.platform.base.common.BaseEntity;
import com.free.platform.base.common.IEnum;
import com.free.platform.base.enums.ResultCode;

/**
 * dubbo服务返回信息
 */
public class ServiceResponse<T> extends BaseEntity implements IEnum {

	private static final long serialVersionUID = -1308723420701237097L;
	
	 /**
     * 返回编码
     */
    private int code = ResultCode.SUCCESS.getKey();
    /**
     * 返回消息
     */
    private String msg = ResultCode.SUCCESS.getValue();
	
	/**
	 * 消息内容
	 */
	private T data;
	
	/**
	 * 构造函数
	 * @param code 数据
	 * @param msg 消息头
	 */
	public ServiceResponse(int code,String msg){
		this.code = code;
		this.msg = msg;
	}
	/**
	 * 构造函数
	 * @param data 数据
	 * @param head 消息头
	 */
	public ServiceResponse(T data,IEnum head){
		this.code = head.getKey();
		this.msg = head.getValue();
		this.data = data;
	}
	/**
	 * 构造函数(成功)
	 * @param data 数据
	 */
	public ServiceResponse(T data){
		this(data,ResultCode.SUCCESS);
	}
	

	public ServiceResponse(){

	}

	public ServiceResponse(IEnum errorCode, String msg) {
		this.code = errorCode.getKey();
		this.msg  = msg;
	}

    public void setServiceResponse(IEnum errorCode) {
        this.code = errorCode.getKey();
        this.msg  = errorCode.getValue();
    }

	/**
	 * 构造函数(失败)
	 * @param errorCode
	 */
	public ServiceResponse(IEnum errorCode){
		this(null,errorCode);
	}


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	/**
	 * 是否成功
	 * @return true成功，false失败
	 */
	public boolean isSuccess(){
		return this.code == ResultCode.SUCCESS.getKey();
	}
	/**
	 * 是否错误
	 * @return true 失败，false成功
	 */
	public boolean isError(){
		return !isSuccess();
	}
	/**
	 * 是什么类型错误
	 * @param error 具体错误
	 * @return
	 */
	public boolean isError(ResultCode error){
		if(error==null){
			return isError();
		}
		return this.code == error.getKey();
	}
	
	@Override
	public Integer getKey() {
		return this.code;
	}
	@Override
	public String getValue() {
		return this.msg;
	}
	
	@Override
	public String toString() {
		return "{code:" + code + ", msg:" + msg + ", data:" + data + "}";
	}
	
}
