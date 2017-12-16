/*
 * 文件名: ResourceRequest.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.resource.response;


import com.free.platform.base.common.BaseEntity;

/**
 *
 * @类描述：
 *
 * @创建人：luohao
 *
 * @创建时间：2016年6月17日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public class BankBinPatternResponse extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     *  卡bin规则
     */
    private String reg;
    
    /**
     * 卡片类型
     */
    private String cardType;

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    
}
