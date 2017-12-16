/*
 * 文件名: LoginRequest.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.user.request;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.free.credit.api.common.annotation.XnParam;
import com.free.platform.base.common.BaseEntity;

/**
 *
 * @类描述：
 *
 * @创建人：luohao
 *
 * @创建时间：2016年5月18日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public class LoginRequest extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 手机号码
     */
    @NotNull(message="手机号码不能为空")
    @Pattern(regexp="^1\\d{10}$",message="请输入正确的手机号")
    private String mobile;
    
    /**
     * 密码
     */
    @NotBlank(message="密码不能为空")
    @XnParam(isShowInLog=false)
    private String password;
    
    /**
     * 图片验证码(登录密码输入次数超过3次)
     */
    private String vCode;
    

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getvCode() {
        return vCode;
    }

    public void setvCode(String vCode) {
        this.vCode = vCode;
    }
}
