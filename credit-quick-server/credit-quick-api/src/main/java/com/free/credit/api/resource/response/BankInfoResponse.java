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
public class BankInfoResponse extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     *  银行名称
     */
    private String bankName;
    
    /**
     * 银行代码
     */
    private String bankCode;
    
    /**
     * 匹配规则
     */
    private BankBinPatternResponse[] patterns;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode.toLowerCase();
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public BankBinPatternResponse[] getPatterns() {
        return patterns;
    }

    public void setPatterns(BankBinPatternResponse[] patterns) {
        this.patterns = patterns;
    }

    
}
