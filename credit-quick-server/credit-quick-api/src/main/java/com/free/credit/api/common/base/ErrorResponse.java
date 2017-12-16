package com.free.credit.api.common.base;


/**
 * 
 * @描述：api返回数据
 *
 * @author 何源
 * @时间  2015年7月31日上午10:16:16
 *
 */
public class ErrorResponse extends BaseResponse{
	private static final long serialVersionUID = -7076970690575275023L;
	

	public ErrorResponse(){
		
	}
	
	public ErrorResponse(int code, String msg) {
        super(code,msg);
    }
	
	public ErrorResponse(String code, String msg) {
		super(code,msg);
	}


}
