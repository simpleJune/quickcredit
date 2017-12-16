/*
 * 文件名: IdentityType.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.common.enums;


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
public enum IdentityType {
    /*
    INVEST("10005", "投资人"),*/
    
    LOAN("10006", "借款人");
    
    private String key;
    
    private String value;

    private IdentityType(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
    
}
