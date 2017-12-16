package com.free.credit.api.message.request;

import com.free.platform.base.common.BaseEntity;
import com.free.platform.base.validation.NotBlank;

public class MessageReadReq extends BaseEntity {
 
    private static final long serialVersionUID = 8671147323920626164L;
    
    @NotBlank(message="用户编号不能为空")
    private String userId;
    @NotBlank(message="消息编号不能为空")
    private String messageId;
    
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
     * @return the messageId
     */
    public String getMessageId() {
        return messageId;
    }
    
    /**
     * @param messageId the messageId to set
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
    
}
