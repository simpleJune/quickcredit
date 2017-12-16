package com.free.credit.api.common.base;

import com.free.platform.base.common.BaseEntity;
import com.free.platform.base.enums.ResultCode;

/**
 * 
 * @描述：api返回数据
 *
 * @author 何源
 * @时间  2015年7月31日上午10:16:16
 *
 */
public class SuccessResponse<T extends BaseEntity> extends BaseResponse{
    private static final long serialVersionUID = -7671756385477179547L;

    /**
     * api返回数据 
     */
    private T data;
    
    
    public SuccessResponse() {
        super(ResultCode.SUCCESS.getKey(),ResultCode.SUCCESS.getValue());
    }

    public SuccessResponse(T data) {
        super(ResultCode.SUCCESS.getKey(),ResultCode.SUCCESS.getValue());
        this.data = data;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
