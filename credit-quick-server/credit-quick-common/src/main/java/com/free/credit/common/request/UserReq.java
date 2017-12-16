package com.free.credit.common.request;

import javax.validation.constraints.NotNull;

import com.free.platform.base.skeleton.ServiceRequest;

/**
 * 
 * 描述：用户借款信息查询
 *
 * @创建人： 何源
 *
 * @时间：2016年7月5日下午6:20:27
 *
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public class UserReq extends ServiceRequest {

	private static final long serialVersionUID = -4485683503564441431L;
	
	@NotNull(message="用户id不能为空")
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
