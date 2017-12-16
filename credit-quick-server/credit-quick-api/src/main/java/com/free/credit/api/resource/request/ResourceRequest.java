/*
 * 文件名: ResourceRequest.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.resource.request;


import com.free.platform.base.common.BaseEntity;

public class ResourceRequest extends BaseEntity {
    private static final long serialVersionUID = 1L;
    
    /**
     * 资源类型
     */
    private String resType;
    
    
    /**
     * 资源编号
     */
    private String resCode;


    /**
     * @return the resType
     */
    public String getResType() {
        return resType;
    }


    /**
     * @param resType the resType to set
     */
    public void setResType(String resType) {
        this.resType = resType;
    }


    /**
     * @return the resCode
     */
    public String getResCode() {
        return resCode;
    }


    /**
     * @param resCode the resCode to set
     */
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }
    
}
