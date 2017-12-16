package com.free.platform.base.enums;

import com.free.platform.base.common.IEnum;

public enum OrderEnum implements IEnum {
	DESC(1,"desc"),//降序
	ASC(2,"asc");//升序
	
	OrderEnum(int key,String value){
		this.key = key;
		this.value = value;
	}

	private int key;
	private String value;
	
	
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
