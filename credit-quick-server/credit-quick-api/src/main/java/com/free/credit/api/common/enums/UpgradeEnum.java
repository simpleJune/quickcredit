/*
 * 文件名: IdentityType.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司2版权所有.
 */
package com.free.credit.api.common.enums;

import com.free.platform.base.common.IEnum;


/**
 *
 * @类描述：
 *
 * @创建人：luohao
 *
 * @创建时间：2016年6月1日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司2版权所有.
 *
 */
public enum UpgradeEnum implements IEnum {
    
    FORCR      (1,     "提示更新"),                    // 申请提现
    PROMPT     (2,     "强制更新"),                    // 投资
    ;
    
    private Integer key;
    
    private String value;

    private UpgradeEnum(Integer key, String value) {
        
        this.key = key;
        
        this.value = value;
        
    }

    public Integer getKey() {
        return this.key;
    }


    public String getValue() {
        return this.value;
    }
    
}
