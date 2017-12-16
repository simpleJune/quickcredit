/*
 * 文件名: SmsCenterHandler.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @类描述：消息业务处理
 *
 * @创建人：luohao
 *
 * @创建时间：2016年5月17日
 *
 * @版权：Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 *
 */
@Component
public class SmsCenterHandler {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    
    @Value("${send.sms}")
    private String sendSms = "0";

    @SuppressWarnings("unused")
	private String smsExpiretimeKey="sms.expired.time";
    
    @SuppressWarnings("unused")
    private String smsCountKey="sms.count.key";
    
    /*
    
    *//**
     * 
     * 方法描述：发送验证码
     * @param mobile
     * @return
     *//*
    public SmsResult<String> sendVcode(String mobile, int moduleId){
        logger.info("=====>>> RandomCodeService|发送短信验证码|generateCode={}",genCodeDto);
        SmsResult<String> smsResult =p2pRandomCodeService.generateCode(genCodeDto);
        logger.info("<<<===== RandomCodeService|发送短信验证码|result={}",smsResult);
        return smsResult;
   
    }
    
    
    *//**
     * 
     * 方法描述：校验验证码
     * @param mobile
     * @param vcode
     * @return
     *//*
    public boolean checkVCode(String mobile, String vcode, int moduleId) {
        //验证码校验
        CheckCodeReq checkCodeDto = new CheckCodeReq();
        checkCodeDto.setMobile(mobile);
        checkCodeDto.setPartnerId(defaultConfig.getPartnerId());
        checkCodeDto.setModuleId(EnumUtils.getValueByKey(moduleId, SmsVcodeType.values()));
        checkCodeDto.setCode(vcode);
        SmsResult<Object> smsResult = p2pRandomCodeService.checkCode(checkCodeDto);
        logger.info("UserService校验短信验证码,checkCodeDto={},result={}", checkCodeDto, smsResult);
        return smsResult.getReturnCode() == ResultCode.SUCCESS.getKey();
        
    }

    *//**
     * 手机短信发送
     * @param mobile    手机号
     * @param moduleId  模板ID
     * @param param     参数值
     * @return
     *//*
    public SmsResult<Object> send(String mobile, String moduleId, String ... param) throws ServiceException {
        
        SmsResult<Object> result = null;
        
        String content = "";
        if(null!=param&&param.length>0){
        	for (int i=0; i<param.length; i++) {
                content = content + param[i] + ",";
            }
        	 content = content.substring(0,content.length()-1); 
        }

        logger.info("手机短信发送 request: [mobile={},moduleId={},content={}]", mobile, moduleId, content);
        try {
            SendMsgReq SendMegReq = new SendMsgReq();
            SendMegReq.setMobile(mobile);
            SendMegReq.setPartnerId(defaultConfig.getPartnerId());
            SendMegReq.setModuleId(moduleId);
            SendMegReq.setValue(content);
            result = smsService.sendMsg(SendMegReq);

            logger.info("手机短信发送成功, mobile={}, result={}", mobile, result);
        } catch (Exception e) {
            logger.error("手机短信发送异常", e);
        }

        return result;
    }
    
*/
    
}
