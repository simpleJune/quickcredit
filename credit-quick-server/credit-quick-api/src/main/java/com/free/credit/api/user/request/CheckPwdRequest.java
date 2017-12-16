/*
 * 文件名: IdentityTypeRequest.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.user.request;


import org.hibernate.validator.constraints.NotBlank;

import com.free.credit.api.common.annotation.XnParam;
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
public class CheckPwdRequest extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "密码不能为空")
    @XnParam(isShowInLog=false)
    private String password;

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
 
}
