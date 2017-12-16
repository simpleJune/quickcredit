package com.free.platform.base.common;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 基础bean
 */
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = -7809346561546528726L;

	  @Override
	    public String toString() {
	        return JSON.toJSONString(this, new SerializerFeature[]{SerializerFeature.WriteMapNullValue,
	                SerializerFeature.WriteNullListAsEmpty,
	                SerializerFeature.WriteNullStringAsEmpty,
	                SerializerFeature.WriteNullNumberAsZero,
	                SerializerFeature.WriteNullBooleanAsFalse,
	                SerializerFeature.UseISO8601DateFormat});
	    }
}
