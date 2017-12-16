/*
 * 文件名: SendVcodeRequest.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.user.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.free.platform.base.common.BaseEntity;

/**
 *
 * @类描述：发送短信验证码请求对象
 *
 * @创建人：luohao
 *
 * @创建时间：2016年5月17日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public class SendVcodeRequest extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1\\d{10}$", message = "手机号码格式不对")
    private String mobile;
    
    /**
     * 短信类型(1、用户注册 2、找回登录密码 3、修改交易密码 4、找回交易密码)
     */

    @NotNull(message = "短信类型不能为空")
    @Min(value = 1 , message = "短信类型错误")
    @Max(value = 7 , message = "短信类型错误")
    private Integer smsType; 

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
     * @return the smsType
     */
    public Integer getSmsType() {
        return smsType;
    }

    /**
     * @param smsType the smsType to set
     */
    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
    }

}
