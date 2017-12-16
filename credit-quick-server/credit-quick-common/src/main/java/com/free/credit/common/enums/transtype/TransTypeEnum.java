/*
 * 文件名: TransTypeEnum.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.common.enums.transtype;

import com.free.platform.base.common.IEnum;

/**
 *
 * @类描述：
 *
 * @创建人：luohao
 *
 * @创建时间：2016年5月20日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public enum TransTypeEnum implements IEnum {
    
    WITHDRAW_APPLY      (1,     "申请提现"),
    INVEST              (2,     "投资"),
    WITHDRAW_BACK       (4,     "提现失败回充"),
    WITHDRAW            (5,     "提现成功"),      
    ADJUST              (6,     "校正"),
    RED_BAG_IN          (7,     "红包返现"), 
    SYSTEN_IN           (8,     "活动奖励"),
    ACTIVITY_IN         (9,     "活动佣金"), 
    PROFIT              (10,    "返利息"), 
    PAYMENTSAMOUNT      (11,    "回款本金"), 
    PAYMENTSPROFIT      (12,    "回款利息"),            //可用投资增加
    USER_IN             (13,    "充值"),
    ACTIVE              (14,    "活动奖励"),            //可用投资增加
    FRIEND_REDBAG       (15,    "好友红包"), 
    BOX_GUESS           (16,    "宝箱竞猜"), 
    TRANSFER_FEE        (19,    "债权转让手续费"),      //债权转让手续费，钱罐子使用   
    LOAN                (20,    "借款"),  
    LOAN_FEE            (21,    "借款管理费"),  
    REPAY               (22,    "还款"),  
    PENALTY             (23,    "还款罚息金额"),  
    
    ;
    
    private Integer key;
    
    private String value;

    private TransTypeEnum(Integer key, String value) {
        
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
