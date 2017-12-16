/*
 * 文件名: SmsNoticeType.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.common.enums.sms;

import com.free.platform.base.common.IEnum;

/**
 *
 * @类描述：借贷端消息、短信模板
 *
 * @创建人：luohao
 *
 * @创建时间：2016年7月4日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public enum InvestSmsType implements IEnum {
    
    REGOK(1, "REGOK"),                          // 注册成功
    
    AUTHOK(2, "AUTHOK"),                        // 身份认证成功
    
    AUTHFAIL(3, "AUTHFAIL"),                    // 身份认证失败    
    
    RISKRESULT(4, "RISKRESULT"),                // 风险评估结果通知
    
    RECHARGEOK(5, "RECHARGEOK"),                // 充值成功
    
    RECHARGEFAIL(6, "RECHARGEFAIL"),            // 充值失败
    
    INVESTOK(7, "INVESTOK"),                    // 投资成功
    
    RAISEOK(8, "RAISEOK"),                      // 标的募集成功
    
    RAISEFAIL(9, "RAISEFAIL"),                  // 标的募集失败
    
    PROFITOK(10, "PROFITOK"),                   // 收益到账
    
    DRAWOK(11, "DRAWOK"),                       // 提现成功
    
    PROFITADVANCE(12, "PROFITADVANCE"),         // 收益提前到账
    
    LOANOVER(13, "LOANOVER"),                   // 标的正常还款结束
    
    ;



    private Integer key;
    
    private String value;

    private InvestSmsType(Integer key, String value) {
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
