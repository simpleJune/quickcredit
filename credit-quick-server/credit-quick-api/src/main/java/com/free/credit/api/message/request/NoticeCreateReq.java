/*
 * Copyright (c) 2015 free, Inc. All rights reserved.
 *
 * @author chunlin.li https://github.com/springlin2012
 *
 */
package com.free.credit.api.message.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

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
public class NoticeCreateReq extends BaseEntity {
    
    private static final long serialVersionUID = -3413516622280801560L;
    
    /**
     * 公告标题
     */
    @NotBlank(message="公告标题")
    private String title;
    
    /**
     * 公告类型(1/系统消息;2/资讯公告;3/更新公告)
     */
    @NotNull(message="未指定公告类型")
    private Integer type;
    
    /**
     * 1/文本;2/链接;
     */
    private Integer contentType;
    
    /**
     * 消息内容
     */
    @NotBlank(message="公告内容为空")
    private String noticeContent;
    
    /**
     * 生效时间
     */
    private Date activeStartTime;
    
    /**
     * 结束时间
     */
    private Date activeEndTime;
    
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
     * @return the type
     */
    public Integer getType() {
        return type;
    }
    
    /**
     * @param type the type to set
     */
    public void setType(Integer type) {
        this.type = type;
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
     * @return the noticeContent
     */
    public String getNoticeContent() {
        return noticeContent;
    }
    
    /**
     * @param noticeContent the noticeContent to set
     */
    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }
    
    /**
     * @return the activeStartTime
     */
    public Date getActiveStartTime() {
        return activeStartTime;
    }
    
    /**
     * @param activeStartTime the activeStartTime to set
     */
    public void setActiveStartTime(Date activeStartTime) {
        this.activeStartTime = activeStartTime;
    }
    
    /**
     * @return the activeEndTime
     */
    public Date getActiveEndTime() {
        return activeEndTime;
    }
    
    /**
     * @param activeEndTime the activeEndTime to set
     */
    public void setActiveEndTime(Date activeEndTime) {
        this.activeEndTime = activeEndTime;
    }
    
}
