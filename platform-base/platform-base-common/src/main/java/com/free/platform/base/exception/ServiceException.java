package com.free.platform.base.exception;

import com.free.platform.base.common.IEnum;
import com.free.platform.base.enums.ResultCode;

public final class ServiceException extends RuntimeException{

	private static final long serialVersionUID = -4553715740275548963L;

	public static final Integer CODE_DEFAULT = ResultCode.EXCEPTION_BS.getKey();
	public static final String MSG_DEFAULT = ResultCode.EXCEPTION_BS.getValue();

	private Integer code;
	private String msg;

	public ServiceException(Throwable cause){
		super(ResultCode.EXCEPTION_UNKWOWN.getValue(),cause);
		this.code = ResultCode.EXCEPTION_UNKWOWN.getKey();
		this.msg = ResultCode.EXCEPTION_UNKWOWN.getValue();
	}
	
	public ServiceException(IEnum resultCode) {
	    this(resultCode.getKey(), resultCode.getValue());
	}
	
	public ServiceException(IEnum resultCode,String msg) {
	    this(resultCode.getKey(),msg);
	}
	
	public ServiceException(Integer code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
	
	public ServiceException(String serviceMsg) {
		super(serviceMsg);
		this.code = ResultCode.EXCEPTION_BUSINESS.getKey();
		this.msg = serviceMsg;
	}
	
	public ServiceException(Integer code, String msg,String causeMsg) {
		super(causeMsg);
		this.code =  code;
		this.msg = msg;
	}
	

	public ServiceException() {
		this(CODE_DEFAULT, MSG_DEFAULT);
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return String.format("code=%s|msg=%s|error=%s",code,msg,super.toString());
	}
	
}
