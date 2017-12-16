/*
 * 文件名: AccountOper.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.common.enums.customer;

import com.free.platform.base.common.IEnum;

/**
 *
 * @类描述：开户文件操作标记
 *
 * @创建人：luohao
 *
 * @创建时间：2016年5月7日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public enum AccountOper  implements IEnum {
    
    OPEN_ACCOUNT(0, "开户"),
    
    MODIFY_INFO(1, "修改基本信息"),
    
    CANCEL_ACCOUNT(2, "销户"),
    
    FROZEN_ACCOUNT(3, "冻结"),
    
    UNFREEZ_ACCOUNT(4, "解冻");

    private Integer key;
    
    private String value;

    private AccountOper(Integer key, String value) {
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
