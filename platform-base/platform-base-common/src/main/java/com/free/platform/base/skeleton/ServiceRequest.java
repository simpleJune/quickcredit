package com.free.platform.base.skeleton;

import com.free.platform.base.common.BaseEntity;


/**
 * dubbo入参通用
 */
public class ServiceRequest extends BaseEntity{

	private static final long serialVersionUID = 3459783708734221725L;
	/**
	 * 系统来源
	 */
	private String sysSource;

    /**
     * 来源
     */
    private String clientIP;

    /**
     * 渠道
     */
    private String channel;

	public String getSysSource() {
		return sysSource;
	}

	public void setSysSource(String sysSource) {
		this.sysSource = sysSource;
	}

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getClientIP() {
        return clientIP;
    }

    public void setClientIP(String clientIP) {
        this.clientIP = clientIP;
    }
}
