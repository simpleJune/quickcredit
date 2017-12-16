package com.free.credit.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.free.credit.api.common.base.BaseResponse;
import com.free.credit.api.common.base.RequestHead;
import com.free.credit.api.common.base.SuccessResponse;
import com.free.credit.api.user.request.LoginRequest;
import com.free.platform.base.enums.ResultCode;


public class BaseTest extends AbstractTest {
    protected final static Logger logger = LoggerFactory.getLogger(BaseTest.class);
    // DEV
    private static String baseUrl = "http://127.0.0.1:8080/rest";
    // ISV
    //private static String baseUrl = "http://10.17.8.45:9099/credit-quick-api/rest";

    private static String token = "";


    public String getUrl() {
        return baseUrl;
    }

    protected String getFile(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            buffer = IOUtils.toByteArray(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Base64.encodeBase64String(buffer);
    }


    public String getToken() throws Exception {
        return this.token;
    }

    public void setToken(String token) throws Exception {
        this.token = token;
    }

    public String getTestUser() throws Exception {
        return "";
    }


    public String getToken(String mobile, String pwd) throws Exception {
        LoginRequest req = new LoginRequest();
        req.setMobile(mobile);
        req.setPassword(pwd);
        logger.info("=================登录 开始===================");
        BaseResponse baseResponse = remote("/user/login", Map.class, req, false);
        logger.info("=================登录 结束===================");
        if (Integer.valueOf(baseResponse.getCode()) == ResultCode.SUCCESS.getKey()) {
            SuccessResponse rlt = (SuccessResponse) baseResponse;
            Map map = (Map) rlt.getData();
            return (String) map.get("token");
        }

        return null;
    }

    @Override
    public String getProxy() {
        //return "127.0.0.1:8888";
        return null;
    }

    @Override
    protected RequestHead getRequestHead(boolean needToken) throws Exception {
        RequestHead header = new RequestHead();
        header.setOsType("android");
        header.setDeviceId("8DCBF01C-6376-447C-9070-0A1AE8189298");
        header.setIdfa("dd");
        header.setImsi("imsi");
        header.setMac("mac");
        header.setPhoneResolution("1242*2208");
        header.setPhoneType("x86_x64");
        header.setVersion("1");
        header.setNetwork("WIFI");
        if (needToken) {
            header.setAccessToken(this.getToken());
        }
        return header;
    }

    protected void printJson(Object obj) {
        String json = JSON.toJSONString(obj, true);
        System.out.println("\n" + json);
    }


}
