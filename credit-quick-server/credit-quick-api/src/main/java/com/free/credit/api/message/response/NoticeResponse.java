package com.free.credit.api.message.response;

import java.util.Date;

import com.free.platform.base.common.BaseEntity;

public class NoticeResponse extends BaseEntity {

    private static final long serialVersionUID = 7601318731016180902L;

    /**
     * 主键id
     */
    private String id;
    
    /**
     * 来源
     */
    private String source;
    
    /**
     * 标题
     */
    private String title;
    
    /**
     * 内容
     */
    private String content;
    
    /**
     * 类型，1为html页面类型，2为指定url类型
     */
    private Integer contentType;
    
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
     * @return the source
     */
    public String getSource() {
        return source;
    }
    
    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
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
