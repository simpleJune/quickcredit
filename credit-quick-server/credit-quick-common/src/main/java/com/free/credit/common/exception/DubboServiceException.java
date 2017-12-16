package com.free.credit.common.exception;


import com.free.platform.base.enums.ResultCode;

/**
 * DUBBO 服务调用异常类
 */
public class DubboServiceException extends ServiceRunTimeException {
    private static final long serialVersionUID = -4100562742161156334L;

    public static final Integer CODE_DEFAULT = ResultCode.EXCEPTION_RPC.getKey();
    public static final String MSG_DEFAULT = ResultCode.EXCEPTION_RPC.getValue();

    public DubboServiceException() {
        super(CODE_DEFAULT, MSG_DEFAULT);
    }

    public DubboServiceException(Integer code,String msg,Throwable cause){
        super(cause,code,msg);
    }

    public DubboServiceException(Integer code,String msg){
        super(code,msg);
    }

    public DubboServiceException(String msg) {
        super(CODE_DEFAULT, msg);
    }

    public DubboServiceException(Throwable cause, String msg) {
        super(cause, CreditException.CODE_DEFAULT, msg);
    }

}
