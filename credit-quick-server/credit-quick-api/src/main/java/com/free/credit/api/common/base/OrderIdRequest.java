/*
 * 文件名: IdRequest.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.common.base;

import javax.validation.constraints.NotNull;

/**
 * 功能描述: 订单ID查询实体
 * <p/>
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2017/11/06.
 * <p/>
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public class OrderIdRequest extends BaseRequest {
    private static final long serialVersionUID = 1607567368795956214L;

    /**
     * 业务ID
     */
    @NotNull(message = "业务id不能为空")
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
