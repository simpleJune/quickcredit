/*
 * 文件名: UserControllerTest.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.user;


import java.util.Map;

import org.junit.Test;

import com.free.credit.api.common.base.BaseRequest;
import com.free.credit.api.common.base.BaseResponse;
import com.free.credit.api.user.request.CaptchaVcodeRequest;
import com.free.credit.api.user.request.CheckPwdRequest;
import com.free.credit.api.user.request.LoginRequest;
import com.free.credit.api.user.request.RegisterRequest;
import com.free.credit.api.user.request.ResetLoginPwdRequest;
import com.free.credit.api.user.request.SendVcodeRequest;
import com.free.credit.common.enums.sms.SmsVcodeType;
import com.free.credit.test.BaseTest;


public class UserControllerTest extends BaseTest {
    private final String loginMobile = "13714204402";
    //    private final String loginMobile = "13714226722";
    private final String password = "admin123";

    @Test
    public void testSendVCode() throws Exception {
        SendVcodeRequest req = new SendVcodeRequest();
        req.setMobile("13714226722");
        req.setSmsType(SmsVcodeType.REGISTER.getKey());
        BaseResponse baseResponse = remote("/auth/vcode", Map.class, req, false);

        logger.debug(baseResponse.toString());
    }


    @Test
    public void testRegister() throws Exception {
        RegisterRequest req = new RegisterRequest();
        req.setMobile("13714226722");
        req.setPassword("admin123");
        req.setvCode("7787");

        BaseResponse baseResponse = remote("/user/register?method=ndtest", Map.class, req, false);
        logger.debug(baseResponse.toString());
    }


    @Test
    public void testLogin() throws Exception {
        LoginRequest req = new LoginRequest();
        req.setMobile(loginMobile);
        req.setPassword(password);
        BaseResponse baseResponse = remote("/user/login", Map.class, req);
        logger.debug(baseResponse.toString());

        printJson(baseResponse);
    }


    @Test
    public void testCheckPwd() throws Exception {
        CheckPwdRequest req = new CheckPwdRequest();
        req.setPassword("12345");
        BaseResponse baseResponse = remote("/user/checkpwd?method=ndtest", Map.class, req);

        logger.debug(baseResponse.toString());
    }


    @Test
    public void testRestLoginPwd() throws Exception {
        ResetLoginPwdRequest req = new ResetLoginPwdRequest();
        req.setMobile("13714204402");
        req.setNewPwd("admin123");
        req.setvCode("3371");
        BaseResponse baseResponse = remote("/user/resetLoginPwd?method=ndtest", Map.class, req);
        logger.debug(baseResponse.toString());
    }


    @Test
    public void testLogout() throws Exception {
        BaseRequest baseReq = new BaseRequest();
       /* BaseResponse isLogin = remote("/user/isLogin?method=ndtest", Map.class, baseReq);
        if ("0".equals(isLogin.getCode())) {*/
        BaseResponse baseResponse = remote("/user/logout?method=ndtest", Map.class, baseReq);
        logger.debug(baseResponse.toString());
        //}
    }


    @Test
    public void refreshData() throws Exception {
        BaseRequest baseReq = new BaseRequest();
        BaseResponse baseResponse = remote("/user/refresh?method=ndtest", Map.class, baseReq);
        logger.debug(baseResponse.toString());
        printJson(baseResponse);
    }


    @Test
    public void testUserInfo() throws Exception {
        BaseRequest baseReq = new BaseRequest();
        BaseResponse baseResponse = remote("/user/info", Map.class, baseReq);
        logger.debug(baseResponse.toString());
    }

    @Test
    public void testCaptcha() throws Exception {
        CaptchaVcodeRequest req = new CaptchaVcodeRequest();
        req.setMobile("13800000000");
        BaseResponse baseResponse = remote("/user/captcha?method=ndtest", Map.class, req);
        logger.debug(baseResponse.toString());
    }

}
