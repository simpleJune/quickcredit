/*
 * 文件名: SendVcodeRequest.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.user.request;


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
public class CaptchaVcodeRequest extends BaseEntity {

    
    private static final long serialVersionUID = 1L;
    
    /**
     * 手机号
     */
    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^1\\d{10}$", message = "手机号码格式不对")
    private String mobile;

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
    
}
