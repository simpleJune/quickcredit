/*
 * Copyright (c) 2017 free, Inc. All rights reserved.
 *
 * @author chunlin.li
 *
 */
package com.free.credit.api.common.base;

import com.free.platform.base.common.BaseEntity;

/**
 * 功能描述: 订单基础返回类
 * <p/>
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2017/11/09.
 * <p/>
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public class OrderBaseResponse extends BaseEntity {
    private static final long serialVersionUID = -5554187560879062333L;

    /**
     * 订单编号
     */
    private String orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
