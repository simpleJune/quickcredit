package com.free.credit.api.common.base;

import com.free.credit.api.common.utils.WebUtil;
import com.free.platform.base.common.BaseEntity;

public class RequestHead extends BaseEntity {
    private static final long serialVersionUID = -81883304538806824L;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 渠道id
     */
    private String channelId;

    /**
     * 系统版本
     */
    private String osType;

    /**
     * 客户端版本
     */
    private String version;

    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 只有ios获取idfa
     */
    private String idfa;

    /**
     * 只是安卓获取imsi
     */
    private String imsi;

    /**
     * 只是安卓获取mac
     */
    private String mac;

    /**
     * 手机类型
     */
    private String phoneType;

    /**
     * 手机分辨率
     */
    private String phoneResolution;

    /**
     * 网络类型
     */
    private String network;


    /**
	 * 授权令牌,可选，当这个为空的时候，不返回钱包
	 */
	private String accessToken;


    public String getUserId() {
        return WebUtil.getUserId(accessToken);
    }

    /**
     * 方法描述：根据消息头中的accessToken获取用户缓存token
     * @return
     */
    public String getUserToken() {
        return WebUtil.getUserToken(accessToken);
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIdfa() {
        return idfa;
    }

    public void setIdfa(String idfa) {
        this.idfa = idfa;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getPhoneResolution() {
        return phoneResolution;
    }

    public void setPhoneResolution(String phoneResolution) {
        this.phoneResolution = phoneResolution;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
}
