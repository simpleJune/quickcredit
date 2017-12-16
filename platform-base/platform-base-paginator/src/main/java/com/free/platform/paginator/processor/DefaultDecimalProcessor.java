package com.free.platform.paginator.processor;

import java.text.DecimalFormat;

import com.free.platform.paginator.ResultValueProcessor;

public class DefaultDecimalProcessor implements ResultValueProcessor<Object> {
	private  DecimalFormat df = null;
	
	public DefaultDecimalProcessor(){
		this("###,###.##");
	}
	
	public DefaultDecimalProcessor(String formate){
		df = new DecimalFormat(formate);
	}
	
	@Override
	public Object process(Object value) {
		return df.format(value);
	}

}
