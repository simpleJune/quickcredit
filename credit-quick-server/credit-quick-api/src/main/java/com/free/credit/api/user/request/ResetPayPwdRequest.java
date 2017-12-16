/*
 * 文件名: InitPayPwdRequest.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.user.request;


import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.free.credit.api.common.annotation.XnParam;
import com.free.credit.api.common.base.BaseRequest;

/**
 *
 * @类描述：初始化交易密码请求对象
 *
 * @创建人：luohao
 *
 * @创建时间：2016年5月19日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public class ResetPayPwdRequest extends BaseRequest {

    private static final long serialVersionUID = 1L;
    
    /**
     * 业务token
     */
    @NotBlank(message = "身份验证失效，请重新认证")
    private String token;
    
    /**
     * 新密码
     */
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?![A-Z]+$)(?![a-z]+$)[\\d\\D]{8,20}$", message = "密码格式错误")
    @XnParam(isShowInLog=false)
    private String payPwd;

    /**
     * @return the payPwd
     */
    public String getPayPwd() {
        return payPwd;
    }

    /**
     * @param payPwd the payPwd to set
     */
    public void setPayPwd(String payPwd) {
        this.payPwd = payPwd;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

}
