/*
 * 文件名: IdentityType.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司2版权所有.
 */
package com.free.credit.api.common.enums;

import com.free.platform.base.common.IEnum;


/**
 *
 * @类描述：
 *
 * @创建人：luohao
 *
 * @创建时间：2016年6月1日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司2版权所有.
 *
 */
public enum TransFlagEnum implements IEnum {
    
    WITHDRAW_APPLY      (1,     "2"),                    // 申请提现
    INVEST              (2,     "2"),                    // 投资
    WITHDRAW_BACK       (4,     "1"),                    // 提现失败回充
    WITHDRAW            (5,     "2"),                    // 提现成功
    PROFIT              (10,    "1"),                    // 返利息
    PAYMENTSAMOUNT      (11,    "1"),                    // 回款本金
    PAYMENTSPROFIT      (12,    "1"),                    // 回款利息
    USER_IN             (13,    "1"),                    // 充值
    LOAN                (20,    "1"),                 // 借款放款，牛贷使用
    LOAN_MANAGEMENT     (21,    "2"),           // 借款管理费，牛贷使用
    REPAYMENT           (22,    "2"),             // 还款，牛贷使用
    REPAYMENT_PENALTY   (23,    "2"),          // 还款逾期费用，牛贷使用
    REPAYMENT_INTEREST  (24,    "2"),              // 还款逾期费用，牛贷使用
    REPAYMENT_FREEZE    (25,    "2"),              // 还款冻结，牛贷使用
    REPAYMENT_FAIL      (26,    "2"),              // 还款失败，牛贷使用   
    REPAYMENT_FREEZE2   (27,    "2"),        // 还款冻结，牛贷使用  
    ;
    
    private Integer key;
    
    private String value;

    private TransFlagEnum(Integer key, String value) {
        
        this.key = key;
        
        this.value = value;
        
    }

    public Integer getKey() {
        return this.key;
    }


    public String getValue() {
        return this.value;
    }
    
}
