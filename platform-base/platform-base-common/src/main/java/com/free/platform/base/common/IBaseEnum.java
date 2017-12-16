package com.free.platform.base.common;

/**
 * 功能描述: 枚举扩展基类
 */
public interface IBaseEnum<T> extends IEnum {

    /**
     * 重置消息
     * @param value
     */
    public abstract T setValue(String value);

    /**
     * key相等判断
     * @param key
     * @return
     */
    public abstract boolean isSuccess(Integer key);

}
