/*
 * 文件名: SendType.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.common.enums;

/**
 *
 * @类描述：
 *
 * @创建人：xn039099
 *
 * @创建时间：2017年5月7日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public enum ProductRateLoanFeeTypeEnum {
    /**
     *  1/基本日利率,2/管理费,3/居间管理费
     */
    BASIC_RATE(1, "基本日利率"),
    SERVICE_RATE(2,"服务费率"),
    MANAGE_RATE(6, "居间与咨询管理服务费");

    private Integer key;
    
    private String value;

    private ProductRateLoanFeeTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
