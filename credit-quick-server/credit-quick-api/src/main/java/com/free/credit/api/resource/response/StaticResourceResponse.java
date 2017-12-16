package com.free.credit.api.resource.response;

import com.free.platform.base.common.BaseEntity;


public class StaticResourceResponse extends BaseEntity {
    private static final long serialVersionUID = 1143059274686765831L;

    /**
     *资源编码
     */
    private String resCode;

    /**
     *资源名称
     */
    private String resName;

    /**
     *资源描述
     */
    private String resDesc;

    /**
     *资源URL
     */
    private String resUrl;

    /**
     *跳转URL
     */
    private String skipUrl;

    /**
     *资源顺序
     */
    private Integer resIndex;


    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResDesc() {
        return resDesc;
    }

    public void setResDesc(String resDesc) {
        this.resDesc = resDesc;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }

    public String getSkipUrl() {
        return skipUrl;
    }

    public void setSkipUrl(String skipUrl) {
        this.skipUrl = skipUrl;
    }

    public Integer getResIndex() {
        return resIndex;
    }

    public void setResIndex(Integer resIndex) {
        this.resIndex = resIndex;
    }
}
