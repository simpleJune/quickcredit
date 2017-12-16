/*
 * 文件名: RiskInfoResponse.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.user.response;


import com.free.platform.base.common.BaseEntity;

/**
 *
 * @类描述：
 *
 * @创建人：luohao
 *
 * @创建时间：2016年8月27日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public class RiskInfoResponse extends BaseEntity {

    private static final long serialVersionUID = 1L;
    
    // 评估标记（0：未评估，1：首次评估，2：非首次评估，已满3个月，3：非首次评估，未满3个月）
    private int assessFlag;
    
    // 提示消息
    private String msg;

    /**
     * @return the assessFlag
     */
    public int getAssessFlag() {
        return assessFlag;
    }

    /**
     * @param assessFlag the assessFlag to set
     */
    public void setAssessFlag(int assessFlag) {
        this.assessFlag = assessFlag;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

}
