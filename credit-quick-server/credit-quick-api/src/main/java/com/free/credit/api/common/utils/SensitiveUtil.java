/*
 * 文件名: SensitiveInfoUtil.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.common.utils;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @类描述：脱敏信息处理
 *
 * @创建人：luohao
 *
 * @创建时间：2016年10月26日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public final class SensitiveUtil {
    
    /** 
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号<例子：李**> 
     *  
     * @param name 
     * @return 
     */  
    public static String chineseName(String fullName) {  
        if (StringUtils.isBlank(fullName)) {  
            return "";  
        }  
        String name = StringUtils.left(fullName, 1);  
        return StringUtils.rightPad(name, StringUtils.length(fullName), "*");  
    } 
    
    /** 
     * [手机号码] 前三位，后四位，其他隐藏<例子:138******1234> 
     *  
     * @param num 
     * @return 
     */  
    public static String mobilePhone(String num) {  
        if (StringUtils.isBlank(num)) {  
            return "";  
        }  
        return StringUtils.left(num, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(num, 4), StringUtils.length(num), "*"), "***"));  
    }  
    
    /** 
     * [银行卡号] 前四位，后四位，其他用星号隐藏每位1个星号<例子:6222**********1234> 
     *  
     * @param cardNum 
     * @return 
     */  
    public static String bankCard(String cardNum) {  
        if (StringUtils.isBlank(cardNum)) {  
            return "";  
        }  
        return StringUtils.left(cardNum, 4).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(cardNum, 4), StringUtils.length(cardNum), "*"), "****"));  
    }  
    
    
    /** 
     * [身份证号] 前三位，后四位，其他用星号隐藏每位1个星号<例子:6222**********1234> 
     *  
     * @param cardNum 
     * @return 
     */  
    public static String idCard(String idCard) {  
        if (StringUtils.isBlank(idCard)) {  
            return "";  
        }  
        return StringUtils.left(idCard, 3).concat(StringUtils.removeStart(StringUtils.leftPad(StringUtils.right(idCard, 4), StringUtils.length(idCard), "*"), "****"));  
    }  
    
    public static void main(String[] args) {
        
        String userName = "王宝强";
        
        String cardNo = "622848015211111121212";
        
        String id = "421129198402153923";
        
        String mobile = "15989435236";
        
        System.out.println(chineseName(userName));
        
        System.out.println(mobilePhone(mobile));
        
        System.out.println(bankCard(cardNo));
        
        System.out.println(idCard(id));
        
    }
    
    
}
