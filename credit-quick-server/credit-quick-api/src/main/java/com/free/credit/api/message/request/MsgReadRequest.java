package com.free.credit.api.message.request;


import org.hibernate.validator.constraints.NotBlank;
import com.free.credit.api.common.base.BaseRequest;

/**
 * @类描述：读取消息 <br/>
 * @创建人：liuhui18 <br/>
 * @创建时间：2016年5月14日 <br/>
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
@SuppressWarnings("serial")
public class MsgReadRequest extends BaseRequest {

    /**
     * 消息编号
     */
    @NotBlank(message="消息编号不能为空")
    private String messageId;
    
    /**
     * @return the messageId
     */
    public String getMessageId() {
        return messageId;
    }
    
    /**
     * @param messageId the messageId to set
     */
    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    
    
    
}
