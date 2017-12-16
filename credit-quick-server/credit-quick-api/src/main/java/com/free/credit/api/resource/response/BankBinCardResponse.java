/*
 * 文件名: ResourceRequest.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.resource.response;


import com.free.platform.base.common.BaseEntity;

import java.util.List;

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
public class BankBinCardResponse extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    /**
     *  银行信息列表
     */
    private List<BankInfoResponse> bankList;

    public List<BankInfoResponse> getBankList() {
        return bankList;
    }

    public void setBankList(List<BankInfoResponse> bankList) {
        this.bankList = bankList;
    }

    
}
