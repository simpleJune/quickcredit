/*
 * Copyright (c) 2017 free, Inc. All rights reserved.
 *
 * @author chunlin.li
 *
 */
package com.free.credit.common.utils;

/**
 * 功能描述: 计算工具类
 * <p/>
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2017/11/27.
 * <p/>
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public class CalculateUtils {

    /**
     * 月利率转日利率
     * @param rate
     * @return
     */
    public static Double month2DayRate(Double rate) {
        if (rate == null) {
            return null;
        }

        return (rate / 30);
    }

}
