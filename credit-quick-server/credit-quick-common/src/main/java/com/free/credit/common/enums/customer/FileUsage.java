/*
 * 文件名: FileType.java
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
 * @创建时间：2016年5月11日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public enum FileUsage implements IEnum {
    
    ID_FRONT(1, "身份证正面"),
    
    ID_BACK(2, "身份证反面"),
    
    HAND_WRITTEN(3, "手写签名"),
    
    BANK_CARD(4, "银行卡"),
    
    FACE(5, "人脸识别");

    private Integer key;
    
    private String value;

    private FileUsage(Integer key, String value) {
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
