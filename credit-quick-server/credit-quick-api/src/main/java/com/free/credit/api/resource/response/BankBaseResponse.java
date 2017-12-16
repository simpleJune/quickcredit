/*
 * 文件名: ResourceRequest.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.resource.response;


import com.free.credit.api.common.base.BaseResponse;

/**
 *
 * @类描述：
 *
 * @创建人：luohao
 *
 * @创建时间：2016年6月17日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public class BankBaseResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;
    
    /**
     *  银行信息列表
     */
    private BankBinCardResponse data;

    public BankBinCardResponse getData() {
        return data;
    }

    public void setData(BankBinCardResponse data) {
        this.data = data;
    }


   

    
}
