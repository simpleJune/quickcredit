package com.free.credit.api.user.controller;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.free.credit.api.common.base.BaseController;
import com.free.credit.api.common.base.BaseRequest;
import com.free.credit.api.common.base.BaseResponse;
import com.free.credit.api.common.base.RequestHead;
import com.free.credit.api.common.utils.ResponseUtil;
import com.free.credit.api.remote.SmsCenterHandler;
import com.free.credit.api.remote.UserCenterHandler;
import com.free.credit.api.user.request.CaptchaVcodeRequest;
import com.free.credit.api.user.request.CheckPwdRequest;
import com.free.credit.api.user.request.LoginRequest;
import com.free.credit.api.user.request.ModifyLoginPwdRequest;
import com.free.credit.api.user.request.RegisterRequest;
import com.free.credit.api.user.request.ResetLoginPwdRequest;

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
@RequestMapping("/user")
class UserController extends BaseController {
    @Resource
    private UserCenterHandler userCenterHandler;

    @Resource
    private SmsCenterHandler smsCenterHandler;

//    @Resource
//    private Producer captchaProducer;


    /**
     * 方法描述：用户注册
     *
     * @param head
     * @param req
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public BaseResponse register(@RequestBody RequestHead head, @Valid @RequestBody RegisterRequest req, BindingResult result) {
        logger.info(">>> process:用户注册|mobile={}", req.getMobile());
        return ResponseUtil.getSuccessResponse();
    }


    /**
     * 方法描述：用户登录
     *
     * @param head
     * @param req
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public BaseResponse login(@RequestBody RequestHead head, @Valid @RequestBody LoginRequest req, BindingResult result) {
        logger.info(">>> process:用户登录|mobile={}", req.getMobile());
        return ResponseUtil.getSuccessResponse();
    }


    @RequestMapping("/refresh")
    @ResponseBody
    public BaseResponse refreshData(@RequestBody RequestHead head, BindingResult result) {
        logger.info(">>> process:刷新用户信息|accessToken={}", head.getAccessToken());
        return ResponseUtil.getSuccessResponse();
    }


    /**
     * 方法描述：用户登录态校验
     *
     * @param head
     * @return
     */
    @RequestMapping("/isLogin")
    @ResponseBody
    public BaseResponse isLogin(@RequestBody RequestHead head) {
        /*ServiceResponse<Boolean> isLoginRlt = userCenterHandler.isLogin(head);
        if (isLoginRlt.isSuccess() && isLoginRlt.getData()) {
            return ResponseUtil.getSuccessResponse();
        } else {
            return ResponseUtil.getErrorServ();
        }*/
        return ResponseUtil.getSuccessResponse();
    }


    /**
     * 方法描述：检查密码是否正确
     *
     * @param head
     * @param req
     * @param result
     * @return
     */
    @RequestMapping("/checkpwd")
    @ResponseBody
    public BaseResponse checkPwd(@RequestBody RequestHead head, @Valid @RequestBody CheckPwdRequest req, BindingResult result) {
        logger.debug(">>> process:检查密码|userId={}", head.getUserId());
        return ResponseUtil.getSuccessResponse();
    }
        


    /**
     * 方法描述：用户退出
     *
     * @param head
     * @param req
     * @return
     */
    @RequestMapping("/logout")
    @ResponseBody
    public BaseResponse logout(@RequestBody RequestHead head, @RequestBody BaseRequest req) {
        return ResponseUtil.getSuccessResponse();
    }


    /**
     * 方法描述：重置登录密码
     *
     * @param head
     * @param req
     * @return
     */
    @RequestMapping("/resetLoginPwd")
    @ResponseBody
    public BaseResponse resetLoginPwd(@RequestBody RequestHead head, @Valid @RequestBody ResetLoginPwdRequest req, BindingResult result) {
        logger.info(">>> process:重置登录密码|mobile={}", req.getMobile());
        return ResponseUtil.getSuccessResponse();
    }


    /**
     * 方法描述：修改登录密码
     *
     * @param head
     * @param req
     * @return
     */
    @RequestMapping("/modifyLoginPwd")
    @ResponseBody
    public BaseResponse modifyLoginPwd(@RequestBody RequestHead head, @Valid @RequestBody ModifyLoginPwdRequest req, BindingResult result) {
        logger.info(">>> process:修改登录密码|userId={}", req.getUserId());
        return ResponseUtil.getSuccessResponse();
    }


    /**
     * 方法描述：生成图形验证码
     *
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/captcha")
    public ModelAndView getKaptchaImage(@RequestBody CaptchaVcodeRequest req, HttpServletResponse response) throws Exception {
        logger.info(">>> process:生成图形验证码|mobile={}", req.getMobile());

        ServletOutputStream out = null;
        try {
            /*response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            response.setContentType("image/jpeg");
            String capText = captchaProducer.createText();
            String key = TokenConstant.TOKEN_TYPE_PIC_VCODE + req.getMobile();
            //commonHelper.saveCache(key, capText, TokenConstant.PIC_VCODE_TIMEOUT);
            BufferedImage bi = captchaProducer.createImage(capText);
            out = response.getOutputStream();
            ImageIO.write(bi, "jpeg", out);
            logger.info("=== process:图形验证码生成成功|mobile={}", req.getMobile());
            out.flush();*/
        } catch (Exception e) {
            logger.warn("=== 生成验证码失败", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
        return null;
    }

}
