package com.free.credit.api.common.enums;

/**
 * @类描述：
 * @创建人：林继丰
 * @创建时间：2017/11/10 10:49
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public enum OCRType {
    ID_CARD_FRONT(1, "身份证正面"),
    ID_CARD_BACKFACES(2, "身份证背面"),
    BANK_CARD(3, "银行卡");

    private Integer key;
    private String value;

    OCRType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
