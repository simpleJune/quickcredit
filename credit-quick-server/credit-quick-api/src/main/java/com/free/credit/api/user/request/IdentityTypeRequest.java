/*
 * 文件名: IdentityTypeRequest.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.user.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.free.platform.base.common.BaseEntity;

/**
 *
 * @类描述：
 *
 * @创建人：luohao
 *
 * @创建时间：2016年9月2日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public class IdentityTypeRequest extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 身份类型：1投资者，2借款人
     */
    @NotNull(message="用户身份类型不能为空")
    @Min(value = 1 , message = "用户身份类型错误")
    @Max(value = 2 , message = "用户身份类型错误")
    private Integer identityType;

    /**
     * @return the identityType
     */
    public Integer getIdentityType() {
        return identityType;
    }

    /**
     * @param identityType the identityType to set
     */
    public void setIdentityType(Integer identityType) {
        this.identityType = identityType;
    }
    
}
