package com.free.credit.api.message.response;

import java.util.Date;

import com.free.platform.base.common.BaseEntity;

/**
 * @类描述：消息
 * <br/>
 * @创建人：liuhui18
 * <br/>
 * @创建时间：2016年6月6日
 * <br/>
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
public class MessageResponse extends BaseEntity {

    private static final long serialVersionUID = -5333380277208143944L;
    

    /**
     * 主键id
     */
    private String id;
    
    /**
     * 用户id
     */
    private String userId;
    
    /**
     * 消息标题
     */
    private String title;
    
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
