package com.free.credit.api.common.utils;


import com.free.platform.base.common.BaseEntity;

public class CommonResult extends BaseEntity {
    private static final long serialVersionUID = 458850149418728852L;

    /**
	 * 在线用户登录态
	 */
	private boolean loginStatus = Boolean.FALSE;
	
	/**
	 * 登录成功返回的用户编号
	 */
    private String userNo;
    
    /**
     * 登录态验证返回的编码信息
     */
    private String code;
    
    /**
     * 登录态验证返回的描述信息
     */
    private String msg;


    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
