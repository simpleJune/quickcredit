/*
 * 文件名: StaticResourceController.java
 * 版权: Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有.
 */
package com.free.credit.api.resource.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.free.credit.api.common.base.BaseController;
import com.free.credit.api.common.base.BaseResponse;
import com.free.credit.api.common.base.RequestHead;
import com.free.credit.api.common.utils.ResponseUtil;
import com.free.credit.api.resource.request.ResourceRequest;
import com.free.credit.api.resource.response.BankBaseResponse;
import com.free.platform.base.enums.ResultCode;
import com.free.platform.base.utils.StringUtils;


/**
 * 功能描述: 鉴权
 * <p/>
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2017/11/06.
 * <p/>
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
@Controller
@RequestMapping("/resources")
public class StaticResourceController extends BaseController {

    @Value("${resource.url}")
    private String resouceUrl = "";

    @Value("${bank.card.path}")
    private String bankCardPath = "";


    /**
     * 方法描述：静态资源获取（协议URL、广告banner、帮助）
     *
     * @param req
     * @param result
     * @return
     */
    @RequestMapping("/static")
    @ResponseBody
    public BaseResponse getStaticResource(@Valid @RequestBody ResourceRequest req, BindingResult result, @RequestBody RequestHead head) {
        logger.info(">>> process:静态资源获取|request={}", req);

        if (ResponseUtil.existsParamsError(result)) {
            return ResponseUtil.getErrorParams(result);
        }
        return ResponseUtil.getSuccessResponse();
    }


    /**
     * 方法描述：获取银行卡bin
     *
     * @param head
     * @return
     */
    @RequestMapping("/binCard")
    @ResponseBody
    public BaseResponse getBinCard(@RequestBody RequestHead head) {

        try {
            logger.info("============获取银行卡bin=================");
            File file = getBankCardFile();

            StringBuilder buffer = readBankCard(file);

            String json = buffer.toString().replace(" ", "");

            BankBaseResponse result = JSONObject.parseObject(json, BankBaseResponse.class);

            return ResponseUtil.getSuccessResponse(result.getData());

        } catch (FileNotFoundException e) {
            logger.error("获取文件失败", e);

        } catch (Exception e) {
            logger.error("请求异常", e);
        }
        return ResponseUtil.getError(ResultCode.EXCEPTION_UNKWOWN.getKey(), "服务请求异常");

    }

    private File getBankCardFile() throws FileNotFoundException {
        File file = null;
        if (StringUtils.isNotBlank(bankCardPath)) {
            file = new File(bankCardPath);// 获取服务器配置文件夹的文件
        }
        if (null == file || !file.exists()) {// 获取项目中的文件
            URL filePath = getClass().getResource("/config/bankcard/bankCardInfo.json");
            if (null == filePath || StringUtils.isBlank(filePath.getPath())) {
                throw new FileNotFoundException("文件不存在");
            }
            file = new File(filePath.getPath());
        }
        return file;
    }

    private StringBuilder readBankCard(File file) throws FileNotFoundException {
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return buffer;
    }
}
