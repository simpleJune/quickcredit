package com.free.credit.api.common.annotation;

import java.lang.reflect.Field;
import org.springframework.util.ReflectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ValueFilter;

public class XnParamParser {
	
	public static String getLog(Object obj){
		if(obj==null){
			return "";
		}
		return JSON.toJSONString(obj, new ValueFilter() {
			@Override
			public Object process(Object object, String name, Object value) {
				if(value!=null){
					Field field = ReflectionUtils.findField(object.getClass(), name);
					if(field!=null){
						XnParam xnParam = field.getAnnotation(XnParam.class);
						if(xnParam!=null&&!xnParam.isShowInLog()){
							return "****";
						}
					}
				}
				return value;
			}
		});
	}
}
