/*
 * Copyright (c) 2015 free, Inc. All rights reserved.
 *
 * @author chunlin.li https://github.com/springlin2012
 *
 */
package com.free.credit.api.message.request;

import com.free.platform.base.common.BaseEntity;
import com.free.platform.base.validation.NotBlank;

/**
 * 功能描述: 消息发送请求
 * <p/>
 * 创建人: liuhui18
 * <p/>
 * 创建时间: 2016/04/26.
 * <p/>
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public class MessageSendReq extends BaseEntity {

    private static final long serialVersionUID = -5556038670905143374L;

    /**
     * 用户id
     */
    @NotBlank(message="用户编码不能为空")
    private String userId;
    
    /**
     * 消息名称
     */
    private String title;
    
    /**
     * 1/文本;2/链接;
     */
    private Integer contentType;
    
    /**
     * 消息内容
     */
    @NotBlank(message="消息内容不能为空")    
    private String msgContent;
    
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * @return the contentType
     */
    public Integer getContentType() {
        return contentType;
    }
    
    /**
     * @param contentType the contentType to set
     */
    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }
    
    /**
     * @return the msgContent
     */
    public String getMsgContent() {
        return msgContent;
    }
    
    /**
     * @param msgContent the msgContent to set
     */
    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
    
}
