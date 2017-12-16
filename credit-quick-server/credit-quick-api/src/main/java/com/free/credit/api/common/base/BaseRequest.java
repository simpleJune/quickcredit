package com.free.credit.api.common.base;

import com.free.platform.base.common.BaseEntity;

/**
 * 
 * @描述：api请求数据
 *
 * @author 何源
 * @时间  2015年7月31日上午10:16:16
 *
 */
public class BaseRequest extends BaseEntity{

	private static final long serialVersionUID = 2339018794015002032L;

	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
