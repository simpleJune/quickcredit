/*
 * 文件名: IdentityType.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.common.enums.customer;

import com.free.platform.base.common.IEnum;

/**
 *
 * @类描述：
 *
 * @创建人：luohao
 *
 * @创建时间：2016年6月1日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public enum IdentityType implements IEnum {
    
    INVEST(1, "投资人"),
    
    LOAN(2, "借款人");
    
    private Integer key;
    
    private String value;

    private IdentityType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }    
    
    
}
