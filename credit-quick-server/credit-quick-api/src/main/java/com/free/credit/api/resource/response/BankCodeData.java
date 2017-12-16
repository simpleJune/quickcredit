package com.free.credit.api.resource.response;

import com.free.platform.base.common.BaseEntity;


/**
 * 
 *
 * @类描述：银行卡信息
 *
 * @创建人：luohao
 *
 * @创建时间：2016年5月23日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public class BankCodeData extends BaseEntity{

    private static final long serialVersionUID = 1L;

    /**
     * 银行代码
     */
	private String bankCode;
	
	/**
	 * 银行名称
	 */
	private String bankName;
	
	/**
     * 日限额
     */
	private String dayLimitAmount;
	
	/**
     * 月限额
     */
	private String monthLimitAmount;
	
	/**
     * 单次限额
     */
	private String recordLimitAmount;
	

    public String getRecordLimitAmount() {
        return recordLimitAmount;
    }




    public void setRecordLimitAmount(String recordLimitAmount) {
        this.recordLimitAmount = recordLimitAmount;
    }




    /**
	 * 银行图片
	 */
	private String bankPng;


	/**
     * @return the bankCode
     */
    public String getBankCode() {
        return bankCode;
    }




    /**
     * @param bankCode the bankCode to set
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
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




    /**
     * @return the monthLimitAmount
     */
    public String getMonthLimitAmount() {
        return monthLimitAmount;
    }




    /**
     * @param monthLimitAmount the monthLimitAmount to set
     */
    public void setMonthLimitAmount(String monthLimitAmount) {
        this.monthLimitAmount = monthLimitAmount;
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
    
}
