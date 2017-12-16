/*
 * 文件名: CertType.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.common.enums.customer;

import com.free.platform.base.common.IEnum;

/**
 *
 * @类描述：用户证件类型
 *
 * @创建人：luohao
 *
 * @创建时间：2016年5月6日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
public enum CertType implements IEnum {
    
    ID_CARD(10100, "居民身份证"),
    
    TEMP_ID(10200, "临时身份证"),
    
    RESIDENCE(10300, "户口簿"),
    
    PASSPORT(10400, "护照"),
    
    SOLDIER_CARD(10501, "士兵证"),
    
    CERTIFICATE_OFFICERS(10502, "军官证"), 
    
    POLICE_OFFICER(10602, "警官证"),  
    
    HONGKONG_MACAO(10701, "港澳居民来往内地通行证"),
    
    TAIWAN_RESIDENTS(10703, "台湾居民来往大陆通行证"), 
    
    ALIEN_RESIDENCE(11000, "外国人居留证");

    private Integer key;
    
    private String value;

    private CertType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public Integer getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }    
    
}
