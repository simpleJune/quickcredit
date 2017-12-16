/*
 * 文件名: IdRequest.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.common.base;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @类描述：通用主键查询请求参数对象
 *
 * @创建人：luohao
 *
 * @创建时间：2016年5月14日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public class BusinessIdRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;
    
    /**
     * 业务ID
     */
    @NotBlank(message = "业务id不能为空")
    private String businessId;

    /**
     * @return the businessId
     */
    public String getBusinessId() {
        return businessId;
    }

    /**
     * @param businessId the businessId to set
     */
    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }
    
    
    
}
