/*
 * Copyright (c) 2014 hytz365, Inc. All rights reserved.
 *
 * @author lichunlin https://github.com/springlin2012
 *
 */
package com.free.credit.common.exception;

/**
 * 功能描述: 基础服务运行异常
 * <p/>
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2015/12/01.
 * <p/>
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class ServiceRunTimeException extends RuntimeException {
    private static final long serialVersionUID = -1400754210058875341L;

    private Integer code;
    private String msg;

    public ServiceRunTimeException(Throwable cause) {
        super(cause);
    }

    public ServiceRunTimeException() {
        this(CreditException.CODE_DEFAULT, CreditException.MSG_DEFAULT);
    }

    public ServiceRunTimeException(String msg) {
        super(msg);
        this.code = CreditException.CODE_DEFAULT;
        this.msg = msg;
    }

    public ServiceRunTimeException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public ServiceRunTimeException(String message, Integer code, String msg) {
        super(message);
        this.code = code;
        this.msg = msg;
    }

    public ServiceRunTimeException(String message, Throwable cause, Integer code, String msg) {
        super(message, cause);
        this.code = code;
        this.msg = msg;
    }

    public ServiceRunTimeException(Throwable cause, Integer code, String msg) {
        super(cause);
        this.code = code;
        this.msg = msg;
    }

    public ServiceRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Integer code, String msg) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.code = code;
        this.msg = msg;
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
}
