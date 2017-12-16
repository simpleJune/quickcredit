/*
 * Copyright (c) 2014 hytz365, Inc. All rights reserved.
 *
 * @author lichunlin https://github.com/springlin2012
 *
 */
package com.free.credit.common.exception;


import com.free.platform.base.enums.ResultCode;

/**
 * 功能描述: 系统异常父类
 * <p/>
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2015/12/01.
 * <p/>
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class CreditException extends Exception {
    private static final long serialVersionUID = -599897360294509848L;

    public static final Integer CODE_DEFAULT = ResultCode.EXCEPTION_BS.getKey();
    public static final String MSG_DEFAULT = ResultCode.EXCEPTION_BS.getValue();

    private Integer code;
    private String msg;

    public CreditException(Throwable cause) {
        super(null, cause);
    }

    public CreditException(Integer code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public CreditException() {
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
}
