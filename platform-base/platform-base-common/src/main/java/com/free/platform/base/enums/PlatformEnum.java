package com.free.platform.base.enums;

import com.free.platform.base.common.IEnum;

/**
 * 用户来源平台类型
 */
public enum PlatformEnum implements IEnum {
	/**
	 * 本站
	 */
	LOCAL(0,"local"),
	/**
	 * 微信
	 */
	WECHAT(1,"wechat"),
	/**
	 * 安卓
	 */
	ANDROID(2,"android"),
	/**
	 * IOS
	 */
	IOS(3,"ios"),
	/**
	 * wap
	 */
	WAP(4,"wap"),
	/**
	 * web
	 */
	WEB(5,"web");

	private int key;
	private String value;

	PlatformEnum(int key,String value){
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
