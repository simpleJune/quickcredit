package com.free.credit.api.common.enums;

import com.free.platform.base.common.IEnum;

/**
 * 描述：
 *
 * @创建人： 彭雨佳
 * @时间：2017/11/16 Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public enum LoanOrderStatus  implements IEnum {
    DEFAULT(0, "未借款"),
    APPLY_PROCESS(1, "借款中"),
    RAISE_PROCESS(2, "募集中"),
    APPLY_FAIL(3, "借款失败"),
    LOAN_FAIL(4, "放款失败"),
    LOAN_SUCCESS(5, "放款成功未还款"),
    REFUND_OVERDUE(6, "还款逾期"),
    REFUND_ING(7,"正在还款中")
            ;

    private Integer key;
    private String value;

    LoanOrderStatus(Integer key, String value) {
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
