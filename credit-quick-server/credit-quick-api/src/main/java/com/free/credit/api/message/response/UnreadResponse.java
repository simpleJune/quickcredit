package com.free.credit.api.message.response;

import com.free.platform.base.common.BaseEntity;

/**
 * 实体Bean @author： liuhui18 @创建时间：2016年06月02日 10:50:48
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public class UnreadResponse extends BaseEntity {
    
    private static final long serialVersionUID = 3261661113065818305L;
    
    /**
     * 未读系统消息数
     */
    private Integer unreadMsgCount;
    
    /**
     * 未读公告数
     */
    private Integer unreadNoticeCount;
    
    /**
     * 未读总数
     */
    private Integer unreadTotalCount;
    
    /**
     * @return the unreadMsgCount
     */
    public Integer getUnreadMsgCount() {
        return unreadMsgCount;
    }
    
    /**
     * @param unreadMsgCount the unreadMsgCount to set
     */
    public void setUnreadMsgCount(Integer unreadMsgCount) {
        this.unreadMsgCount = unreadMsgCount;
    }
    
    /**
     * @return the unreadNoticeCount
     */
    public Integer getUnreadNoticeCount() {
        return unreadNoticeCount;
    }
    
    /**
     * @param unreadNoticeCount the unreadNoticeCount to set
     */
    public void setUnreadNoticeCount(Integer unreadNoticeCount) {
        this.unreadNoticeCount = unreadNoticeCount;
    }
    
    /**
     * @return the unreadTotalCount
     */
    public Integer getUnreadTotalCount() {
        return unreadTotalCount;
    }
    
    /**
     * @param unreadTotalCount the unreadTotalCount to set
     */
    public void setUnreadTotalCount(Integer unreadTotalCount) {
        this.unreadTotalCount = unreadTotalCount;
    }
    
}
