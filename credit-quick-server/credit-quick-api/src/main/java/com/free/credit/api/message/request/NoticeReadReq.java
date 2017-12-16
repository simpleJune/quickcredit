package com.free.credit.api.message.request;

import com.free.platform.base.common.BaseEntity;
import com.free.platform.base.validation.NotBlank;

public class NoticeReadReq extends BaseEntity {
 
    private static final long serialVersionUID = -3183904545577773164L;
    
    @NotBlank(message="用户编号不能为空")
    private String userId;
    @NotBlank(message="公告编号不能为空")
    private String noticeId;
    
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

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
