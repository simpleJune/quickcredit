package com.free.credit.common.enums.client;

import com.free.platform.base.common.IEnum;

/**
 * 
 * 描述：app升级状态 0不需要升级,1提示升级,2强制升级
 *
 * @创建人： 何源
 *
 * @时间：2016年4月19日下午5:18:40
 *
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public enum AppUpgradeStatus implements IEnum {
	
    NOT_NEED(0,"不需要升级"),
    PROMPT(1, "提示升级"),
    FORCE(2, "强制升级") ;

    private Integer key;
    private String value;

    private AppUpgradeStatus(Integer key, String value) {
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
