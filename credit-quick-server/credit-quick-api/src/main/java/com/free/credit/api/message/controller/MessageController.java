package com.free.credit.api.message.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.free.credit.api.common.base.BaseResponse;
import com.free.credit.api.common.base.PaginatorRequest;
import com.free.credit.api.common.base.RequestHead;
import com.free.credit.api.common.utils.ResponseUtil;
import com.free.credit.api.message.request.MsgReadRequest;
import com.free.credit.api.message.request.NoticeReadRequest;
import com.free.credit.api.remote.SmsCenterHandler;

/**
 * 功能描述: 消息中心
 * <p/>
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2017/11/06.
 * <p/>
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
@Controller
@RequestMapping("/msg")
public class MessageController {
    private static Logger logger = LoggerFactory.getLogger(MessageController.class);
    
    @Resource
    private SmsCenterHandler msgService;
    
    
    /**
     * 方法描述：获取消息列表
     * @param pageRequest
     * @return
     */
    @RequestMapping("/system/list")
    @ResponseBody
    public BaseResponse listMessage(@RequestBody RequestHead head, @Valid @RequestBody PaginatorRequest pageRequest, BindingResult result) {
        logger.debug(">>> process:获取消息列表|request={}", pageRequest);
        return ResponseUtil.getSuccessResponse();

    }
    
    
    /**
     * 方法描述：读取消息
     * @param request
     * @return
     */
    @RequestMapping("/system/read")
    @ResponseBody
    public BaseResponse readMessage(@RequestBody RequestHead head, @Valid @RequestBody MsgReadRequest request, BindingResult result ) {
        logger.debug(">>> process:读取消息|request={}", request);
        return ResponseUtil.getSuccessResponse();

        
    }
    
    
    /**
     * 方法描述：获取公告列表
     * @param pageRequest
     * @return
     */
    @RequestMapping("/notice/list")
    @ResponseBody
    public BaseResponse listNotice(@RequestBody RequestHead head, @Valid @RequestBody PaginatorRequest pageRequest, BindingResult result) {
        logger.debug(">>> process:获取公告列表|request={}", pageRequest);
        return ResponseUtil.getSuccessResponse();

    }
    
    
    /**
     * 方法描述：读取消息
     * @param request
     * @return
     */
    @RequestMapping("/notice/read")
    @ResponseBody
    public BaseResponse readNotice(@RequestBody RequestHead head, @Valid @RequestBody NoticeReadRequest request, BindingResult result) {
        logger.debug(">>> process:读取消息|request={}", request);
        return ResponseUtil.getSuccessResponse();

    }
    
    
    
    /**
     * 方法描述：获取未读信息
     * @return
     */
    @RequestMapping("/countUnread")
    @ResponseBody
    public BaseResponse countUnread(@RequestBody RequestHead head) {
        logger.debug(">>> process:获取未读信息|request={}", head);
        return ResponseUtil.getSuccessResponse();
    }
    
    
}
