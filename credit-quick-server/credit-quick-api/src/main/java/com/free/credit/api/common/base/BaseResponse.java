package com.free.credit.api.common.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.free.platform.base.common.BaseEntity;
import com.free.platform.base.enums.ResultCode;

/**
 * @author 何源
 * @描述：api返回数据
 * @时间 2015年7月31日上午10:16:16
 */
public class BaseResponse extends BaseEntity {

    private static final long serialVersionUID = -7671756385477179547L;
    /**
     * api返回码
     */
    private String code;
    /**
     * api返回消息
     */
    private String msg;

    /**
     * 服务器时间
     */
    private long serverTime = System.currentTimeMillis();

    public BaseResponse() {

    }

    public BaseResponse(int code, String msg) {
        this.code = code + "";
        this.msg = msg;
    }

    public BaseResponse(String code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }


    /**
     * 是否成功
     * @return true成功，false失败
     */
    @JSONField(serialize=false)
    public boolean isSuccess(){
        return Integer.valueOf(this.code) == ResultCode.SUCCESS.getKey();
    }
    /**
     * 是否错误
     * @return true 失败，false成功
     */
    @JSONField(serialize=false)
    public boolean isError(){
        return !isSuccess();
    }

    public static void main(String[] args){
        System.out.print(JSON.toJSON(new BaseResponse()));
    }
}


