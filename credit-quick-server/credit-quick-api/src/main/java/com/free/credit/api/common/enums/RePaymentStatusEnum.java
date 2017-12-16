/*
 * Copyright (c) 2017 free, Inc. All rights reserved.
 *
 * @author chunlin.li
 *
 */
package com.free.credit.api.common.enums;

import com.free.platform.base.common.IEnum;

/**
 * 功能描述: 订单状体
 * <p/>
 * 创建人: chunlin.ligg
 * <p/>
 * 创建时间: 2017/11/09.
 * <p/>
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public enum RePaymentStatusEnum implements IEnum {

    OTHER(0, ""),
    REFUND_PROCESS(1, "还款中"),
    REFUND_COMPLET(2, "还款成功"),
    REFUND_FAIL(3, "还款失败"),

    // 自动还款/手动还款
    REFUND_TYPE_NORMAL(10, "手动还款"),
    REFUND_TYPE_SYSTEM(11, "自动还款"),
    ;

    private Integer key;
    private String value;

    RePaymentStatusEnum(Integer key, String value) {
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

    /**
     * 状态类型转换
     * @param status
     * @return
     */
    public static RePaymentStatusEnum statusTypeConvert(Integer status) {
        /**
         SYSTEM(1, "系统还款"),
         NORMAL(2, "主动正常还款"),
         PREPAYMENT(3,"主动提前还款"),
         OVERDUE(4, "主动逾期还款");
         */
        if (status == 1) {
            return REFUND_TYPE_SYSTEM;
        } else if (status == 2) {
            return REFUND_TYPE_NORMAL;
        }
        return OTHER;
    }
}
