package com.free.credit.api.message.request;

import org.hibernate.validator.constraints.NotBlank;

import com.free.credit.api.common.base.BaseRequest;

/**
 * @类描述：读取消息 <br/>
 * @创建人：liuhui18 <br/>
 * @创建时间：2016年5月14日 <br/>
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
@SuppressWarnings("serial")
public class NoticeReadRequest extends BaseRequest {
    
    /**
     * 公告编号
     */
    @NotBlank(message="公告编号不能为空")
    private String noticeId;

    /**
     * @return the noticeId
     */
    public String getNoticeId() {
        return noticeId;
    }

    /**
     * @param noticeId the noticeId to set
     */
    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }
    
    
    
}
