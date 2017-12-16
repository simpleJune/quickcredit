/*
 * 文件名: SmsVcodeType.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.common.enums.sms;

import com.free.platform.base.common.IEnum;


public enum SmsVcodeType implements IEnum {
    
    REGISTER(1, "REGISTER"),                             // 用户注册

    RESETLOGIN(2, "MODIFYLOGIN"),                        // 重置登录密码

    CONFIRMCREDITAPPLY(4,"LOANCODE"),                   // 确认借款

    CONFIRM_REPAYMENT(7,"REPAYCODE"),                  // 确认还款
    ;

    private Integer key;
    private String value;

    private SmsVcodeType(Integer key, String value) {
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
