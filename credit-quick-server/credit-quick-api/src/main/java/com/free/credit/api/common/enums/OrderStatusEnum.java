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
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2017/11/09.
 * <p/>
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public enum OrderStatusEnum implements IEnum {

    OTHER(0, ""),
    APPLY_PROCESS(1, "借款申请中"),
    APPLY_FAIL(2, "借款申请失败"),
    RAISE_PROCESS(3, "募集中"),
    LOAN_FAIL(4, "借款失败"),
    REFUND_PROCESS(5, "放款成功未还款"),
    REFUND_COMPLET(6, "已还清"),
    REFUND_OVERDUE(7, "已逾期"),
    REFUND_ING(8, "正在还款")
    ;

    private Integer key;
    private String value;

    OrderStatusEnum(Integer key, String value) {
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
