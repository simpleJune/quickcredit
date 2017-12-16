package com.free.credit.api.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @类描述：遮罩工具类
 * <br/>
 * @创建人：liuhui18
 * <br/>
 * @创建时间：2016年5月19日
 * <br/>
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class MaskUtil {
    
    /**
     * 方法描述：遮罩姓名, 展示姓名的前两位，后面名字用“x”代替.
     * <p>
     * ex. 
     * 借款人姓名为："张三丰", 则显示"张三*"; 如借款人姓名"欧阳三丰"，则显示"欧阳**";
     * 如果姓名为单名, 则仅展示姓，例如借款人姓名"张三"，则展示为"张*"
     * @param name
     * @return 
     */
    public static String maskName(String name) {
        if(StringUtils.isBlank(name))  {
            return name;
        }
        String trimedName = StringUtils.trim(name);
        StringBuilder maskName = new StringBuilder();
        int maskStart = trimedName.length() > 2 ? 2 : 1;
        maskName.append(trimedName.substring(0, maskStart));
        for(int i = maskStart; i < name.length(); i++) {
            maskName.append("*");
        }
        return maskName.toString();
    }
    
    /**
     * 方法描述：遮罩手机号码, 展示前三后四，中间隐藏的规则
     * <p>
     * ex. 
     * 电话号码为"18516221253", 则展示电话号码为："185xxxx1253"
     * @param phoneNo 手机号码
     * @return 
     */
    public static String maskPhoneNo(String phoneNo) {
        return maskPhoneNo(phoneNo);
    }
    
    /**
     * 方法描述：遮罩身份证号码, 展示前三后四，中间隐藏的规则
     * <p>
     * ex. 
     * 身份证号为”365421198512011102“，则展示为”365xxxxxxxxxxx1102"
     * @param idNo 身份证号码
     * @return 
     */
    public static String maskIdentityNo(String idNo) {
        return maskNumbers(idNo);
    }
    
    
    private static String maskNumbers(String number) {
        if(StringUtils.isBlank(number)) {
            return number;
        }
        String trimedNumber = StringUtils.trim(number);
        StringBuilder maskNumber = new StringBuilder();
        int length = trimedNumber.length();
        for(int i = 0; i < length; i++) {
            maskNumber.append((i >=3 && i < length-4) ?  "*" : trimedNumber.charAt(i) );                
        }
        return maskNumber.toString();
    }

}
