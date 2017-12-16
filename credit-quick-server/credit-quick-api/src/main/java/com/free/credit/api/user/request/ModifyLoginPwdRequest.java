/*
 * 文件名: ModifyLoginPwdRequest.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.user.request;


import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.free.credit.api.common.annotation.XnParam;
import com.free.credit.api.common.base.BaseRequest;

/**
 *
 * @类描述：修改登录密码接口请求参数
 *
 * @创建人：luohao
 *
 * @创建时间：2016年5月19日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public class ModifyLoginPwdRequest extends BaseRequest {
    
    
    private static final long serialVersionUID = 1L;

    /**
     * 旧密码
     */
    @NotBlank(message="旧密码不能为空")
    @XnParam(isShowInLog=false)
    private String oldPwd;
    
    /**
     * 新密码
     */
    @NotBlank(message="新密码不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?![A-Z]+$)(?![a-z]+$)[\\d\\D]{8,20}$", message = "密码格式错误")
    @XnParam(isShowInLog=false)
    private String newPwd;

    /**
     * @return the oldPwd
     */
    public String getOldPwd() {
        return oldPwd;
    }

    /**
     * @param oldPwd the oldPwd to set
     */
    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    /**
     * @return the newPwd
     */
    public String getNewPwd() {
        return newPwd;
    }

    /**
     * @param newPwd the newPwd to set
     */
    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }
    
}
