/*
 * 文件名: SmsNoticeType.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.common.enums.sms;

import com.free.platform.base.common.IEnum;

/**
 *
 * @类描述：投资端消息、短信模板
 *
 * @创建人：luohao
 *
 * @创建时间：2016年7月4日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public enum LoanSmsType implements IEnum {
    
    REGOK(1, "REGOK"),                          // 注册成功
    
    AUTHOK(2, "AUTHOK"),                        // 身份认证成功
    
    AUTHFAIL(3, "AUTHFAIL"),                    // 身份认证失败    
    
    APPLYACCEPT(4, "APPLYACCEPT"),              // 申请受理
    
    PREAPPROVALOK(5, "PREAPPROVALOK"),          // 预审批成功
    
    PREAPPROVALFAIL(6, "PREAPPROVALFAIL"),      // 预审批失败
    
    APPROVALOK(7, "APPROVALOK"),                // 审批成功
    
    APPROVALFAIL(8, "APPROVALFAIL"),            // 审批失败   
    
    APPROVALINVALID(9, "APPROVALINVALID"),      // 审批结果过期（12,6,1）
    
    BEGINRAISE(10, "BEGINRAISE"),               // 开始募集
    
    RAISEOK(11, "RAISEOK"),                     // 募集成功
    
    RAISEFAIL(12, "RAISEFAIL"),                 // 募集失败
    
    DRAWOK(13, "DRAWOK"),                       // 提现成功
    
    REPAYMENTREMIND(14, "REPAYMENTREMIND"),     // 还款提醒（提前三天、还款当天）
    
    REPAYMENTFAIL0(15, "REPAYMENTFAIL0"),       // 自动还款失败0点
    
    REPAYMENTFAIL24(16, "REPAYMENTFAIL24"),     // 自动还款失败24点
    
    RECHARGEOK(17, "RECHARGEOK"),               // 充值成功
    
    RECHARGEFAIL(18, "RECHARGEFAIL"),           // 充值失败
    
    ACTIVEREPAYMENT(19, "ACTIVEREPAYMENT"),     // 借款人主动还款成功
     
    OVERDUEOK(20, "OVERDUEOK"),                 // 借款人逾期还款成功
    
    OVERDUEREMIND(21, "OVERDUEREMIND");         // 逾期提醒（逾期第一、二、三天）



    private Integer key;
    
    private String value;

    private LoanSmsType(Integer key, String value) {
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
