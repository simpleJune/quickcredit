/*
 * 文件名: ResetLoginPwdRequest.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.user.request;


import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.free.credit.api.common.annotation.XnParam;
import com.free.platform.base.common.BaseEntity;

/**
 *
 * @类描述：短信重置登录密码接口请求参数对象
 *
 * @创建人：luohao
 *
 * @创建时间：2016年5月19日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public class ResetLoginPwdRequest extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1\\d{10}$", message = "请输入正确的手机号")
    private String mobile;
    
    /**
     * 验证码
     */
    @NotBlank(message="验证码不为空")
    private String vCode;
    
    /**
     * 新密码
     */
    @Pattern(regexp = "^(?![0-9]+$)(?![A-Z]+$)(?![a-z]+$)[\\d\\D]{6,16}$", message = "密码格式错误")
    @XnParam(isShowInLog=false)
    private String newPwd;


    public String getvCode() {
        return vCode;
    }

    public void setvCode(String vCode) {
        this.vCode = vCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
}
