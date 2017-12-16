package com.free.platform.base.enums;

import com.free.platform.base.common.IBaseEnum;

/**
 * 功能描述: 系统返回业务编码常量
 */
public enum ResultCode implements IBaseEnum<ResultCode> {

    SUCCESS(0, "成功"),

    /**
     * 全局默认异常类
     */
    EXCEPTION_BUSINESS(100100, "业务异常"),

    EXCEPTION_UNKWOWN(100200, "服务繁忙，请稍后再试"),

    EXCEPTION_BS(100300, "系统基础服务异常"),
    
    EXCEPTION_RPC(100400, "远程服务调用异常"),
    
    EXCEPTION_CACHE(100500, "缓存服务异常"),
    
    EXCEPTION_PARAMS(100600, "参数错误"),
    
    EXCEPTION_INVALIDE_REQ(100700, "无效的请求"),
    
    EXCEPTION_SECURITY(100900, "安全错误"),

    ;
    
    
    

    private Integer key;
    private String value;

    private ResultCode(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Integer getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public ResultCode setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean isSuccess(Integer key) {
        if (null == key) return false;
        return this.key.intValue() == key.intValue();
    }

}
