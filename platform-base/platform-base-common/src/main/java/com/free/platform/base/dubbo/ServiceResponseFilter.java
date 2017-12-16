package com.free.platform.base.dubbo;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.RpcResult;
import com.free.platform.base.exception.ServiceException;
import com.free.platform.base.enums.ResultCode;
import com.free.platform.base.utils.ServiceResponseUtil;

/**
 * 描述：异常处理
 */
public class ServiceResponseFilter implements Filter {

	public Result invoke(Invoker<?> invoker, Invocation inv)throws RpcException {
		 try{
			 Result obj = invoker.invoke(inv);
			 if(obj.getException()==null){
				  return obj;
			 }else{
				  RpcResult rlt = new RpcResult();
				  String str = obj.getException().getMessage()+"|";
				  Pattern p = Pattern.compile("(.*?)=(.*?)\\|");
				  Matcher m = p.matcher(str);
				  Integer code = null;
				  String msg = null,error=null;
				  while (m.find()){
						if("code".equals(m.group(1))){
							code = Integer.parseInt(m.group(2));
						}
						if("msg".equals(m.group(1))){
							msg = m.group(2);
						}
						if("error".equals(m.group(1))){
							error = m.group(2);
						}
				  }
				  ServiceException ex = null;
				  if(code==null){
					  ex = new ServiceException(obj.getException());
				  }else{
					  ex = new ServiceException(code,msg,error);
				  }
				  rlt.setValue(ServiceResponseUtil.createServiceResponseForError(ex));
				  return rlt;
			 }
		 }catch(Exception e ){
			 if(e.getCause() instanceof ConstraintViolationException){
				 //interpolatedMessage='投资编号不能为空', propertyPath=investId
				 ConstraintViolationException ex = (ConstraintViolationException)e.getCause();
				 Set<ConstraintViolation<?>> data = ex.getConstraintViolations();
				 String msg = "";
				 if(data.size()>0){
					 ConstraintViolation item = (ConstraintViolation)data.toArray()[0];
					 //String str =  item.getPropertyPath().toString();
					 msg = item.getMessage();
				 }
				 RpcResult rlt = new RpcResult();
				 rlt.setValue(ServiceResponseUtil.createServiceResponse(ResultCode.EXCEPTION_PARAMS,msg));
				 return rlt;
			 }
			 throw e;
		 }
		
	}

}