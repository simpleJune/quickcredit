/*
 * 文件名: BusinessInfoRequest.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.common.base;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @类描述：
 *
 * @创建人：luohao
 *
 * @创建时间：2016年5月16日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public class BusinessInfoRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;
    
    /**
     * 业务数据
     */
    @NotBlank(message = "业务数据不能为空")
    private String businessInfo;

    /**
     * @return the businessInfo
     */
    public String getBusinessInfo() {
        return businessInfo;
    }

    /**
     * @param businessInfo the businessInfo to set
     */
    public void setBusinessInfo(String businessInfo) {
        this.businessInfo = businessInfo;
    }

}
