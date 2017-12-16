package com.free.credit.api.common.base;

import org.hibernate.validator.constraints.NotBlank;

public class BasePaginatorRequest extends PaginatorRequest {
    private static final long serialVersionUID = 4846755758976312679L;

    @NotBlank(message = "用户ID不能为空")
    private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
