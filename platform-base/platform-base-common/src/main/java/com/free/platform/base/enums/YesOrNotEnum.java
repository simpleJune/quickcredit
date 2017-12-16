package com.free.platform.base.enums;

import com.free.platform.base.common.IEnum;

public enum YesOrNotEnum implements IEnum {
	YES(1,"是"),
	NOT(0,"否");
	
	YesOrNotEnum(int key,String value){
		this.key = key;
		this.value = value;
	}

	private int key;
	private String value;
	
	public Integer getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
	
}
