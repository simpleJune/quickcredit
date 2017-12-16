/*
 * 文件名: AccountInfoResponse.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.user.response;

import com.free.platform.base.common.BaseEntity;

/**
 *
 * @类描述：
 *
 * @创建人：luohao
 *
 * @创建时间：2016年8月26日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public class AccountInfoResponse extends BaseEntity{

    private static final long serialVersionUID = 1L;
    
    /**
     * 可用余额
     */
    private long balance;
    
    /**
     * 冻结金额
     */
    private long blockedAmount;
    
    /**
     * 投资金额（借款人代表投资中的金额，借贷人代表待还款金额）
     */
    private long loanAmount;
    
    /**
     * 银行编码
     */
    private String bankNo;
    
    /**
     * 银行名称
     */
    private String bankName;
    
    /**
     * 银行预留手机号
     */
    private String mobile;
    
    /**
     * 银行卡号
     */
    private String bankCardNo;
    
    /**
     * 图片URL路径
     */   
    private String bankPng;
    
    /**
     * 日累计充值限额
     */
    private String dayLimitAmount;
    

    /**
     * @return the balance
     */
    public long getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(long balance) {
        this.balance = balance;
    }

    /**
     * @return the blockedAmount
     */
    public long getBlockedAmount() {
        return blockedAmount;
    }

    /**
     * @param blockedAmount the blockedAmount to set
     */
    public void setBlockedAmount(long blockedAmount) {
        this.blockedAmount = blockedAmount;
    }

    /**
     * @return the loanAmount
     */
    public long getLoanAmount() {
        return loanAmount;
    }

    /**
     * @param loanAmount the loanAmount to set
     */
    public void setLoanAmount(long loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * @return the bankNo
     */
    public String getBankNo() {
        return bankNo;
    }

    /**
     * @param bankNo the bankNo to set
     */
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the bankName
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * @param bankName the bankName to set
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    /**
     * @return the bankCardNo
     */
    public String getBankCardNo() {
        return bankCardNo;
    }

    /**
     * @param bankCardNo the bankCardNo to set
     */
    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    /**
     * @return the bankPng
     */
    public String getBankPng() {
        return bankPng;
    }

    /**
     * @param bankPng the bankPng to set
     */
    public void setBankPng(String bankPng) {
        this.bankPng = bankPng;
    }

    /**
     * @return the dayLimitAmount
     */
    public String getDayLimitAmount() {
        return dayLimitAmount;
    }

    /**
     * @param dayLimitAmount the dayLimitAmount to set
     */
    public void setDayLimitAmount(String dayLimitAmount) {
        this.dayLimitAmount = dayLimitAmount;
    }

}
