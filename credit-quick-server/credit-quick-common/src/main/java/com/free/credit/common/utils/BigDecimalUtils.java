/*
 * Copyright (c) 2017 free, Inc. All rights reserved.
 *
 * @author chunlin.li
 *
 */
package com.free.credit.common.utils;

import java.math.BigDecimal;

/**
 * 功能描述: 金额操作工具类
 * <p/>
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2017/11/11.
 * <p/>
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public class BigDecimalUtils {


    public static Long amountScal(Long amount, Integer scal) {
        int position = 10;
        for (int i = 1; i < scal; i++) {
            position *= 10;
        }
        return (amount / position);
    }


    public static Long amountScal(Long amount) {
        return amountScal(amount, 2);
    }


    /**
     * 四舍五入格式为指定位小数，
     * 100.256d - 100.26
     * 100 -  100.00
     *
     * @param amount
     * @return
     * @创建人 何源
     * @创建时间 2016年10月19日下午3:40:05
     */
    public static String formate(Long amount, int scale) {
        if (amount == null) {
            return "0.00";
        }
        return new BigDecimal(amount).divide(new BigDecimal(10000)).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    public static String formate(Long amount) {
        return formate(amount, 0);
    }

    public static String formatePenny(Long amount) {
        return formate(amount, 2);
    }

    public static void main(String[] args) {
        System.out.println(formate(102460L, 2));
    }
}
