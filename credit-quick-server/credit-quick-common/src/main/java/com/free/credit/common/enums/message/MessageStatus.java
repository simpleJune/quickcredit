package com.free.credit.common.enums.message;

import com.free.platform.base.common.IEnum;

/**
 * 
 * 描述：消息状态
 *
 * @创建人： 刘晖
 *
 * @时间：2016年4月21日下午2:59:50
 *
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public enum MessageStatus implements IEnum {
    // 消息状态 1/正常; 9/删除
    ACTIVE(1, "正常"),
    REMOVED(9, "删除");
    
    private Integer key;
    private String value;

    private MessageStatus(Integer key, String value) {
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
 

}