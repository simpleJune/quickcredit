/*
 * Copyright (c) 2016 free, Inc. All rights reserved.
 *
 * @author chunlin.li https://github.com/springlin2012
 *
 */
package com.free.credit.common.request;

import com.free.platform.base.skeleton.ServiceRequest;

/**
 * 功能描述: 条件请求类
 * <p/>
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2016/05/11.
 * <p/>
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public class ExampleRequest extends ServiceRequest {
    private static final long serialVersionUID = 5375490373782122417L;

    /**
     * 业务ID
     */
    private String uid;

    /**
     * 用户ID
     */
    private String userId;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
