package com.free.platform.paginator.processor;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.free.platform.paginator.ResultValueProcessor;

public class DefaultDateProcessor implements ResultValueProcessor<Date> {
	private  SimpleDateFormat sdf = null;
	
	public DefaultDateProcessor(){
		this("yyyy-MM-dd");
	}
	
	public DefaultDateProcessor(String formate){
		sdf = new SimpleDateFormat(formate);
	}
	
	@Override
	public Object process(Date value) {
		return sdf.format(value);
	}

}
