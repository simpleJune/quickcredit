package com.free.credit.api.message.response;

import com.free.platform.base.common.BaseEntity;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;

/**
 * 描述： 实体Bean @创建人： liuhui18 @创建时间：2016年06月02日 10:50:48
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class MessageRlt extends BaseEntity {
    
    private static final long serialVersionUID = 8357386807340787447L;
    
    /**
     * 主键id
     */
    private String id;
    
    /**
     * 用户id
     */
    private String userId;
    
    /**
     * 消息类型
     */
    private String msgType;
    
    /**
     * 内容
     */
    private String content;
    
    /**
     * 状态，1为正常，2为已删除
     */
    private Integer status;
    
    /**
     * 是否已读，1为未读，2为已读
     */
    private Integer readStatus;
    
    /**
     * 创建时间
     */
    private Date createTime;
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }
    
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
     * @return the msgType
     */
    public String getMsgType() {
        return msgType;
    }
    
    /**
     * @param msgType the msgType to set
     */
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    
    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }
    
    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }
    
    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    /**
     * @return the readStatus
     */
    public Integer getReadStatus() {
        return readStatus;
    }
    
    /**
     * @param readStatus the readStatus to set
     */
    public void setReadStatus(Integer readStatus) {
        this.readStatus = readStatus;
    }
    
    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }
    
    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
}
