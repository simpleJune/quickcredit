package com.free.credit.common.enums;

import com.free.platform.base.common.IEnum;
import com.free.platform.base.enums.ResultCode;

/**
 * 功能描述: 牛贷系统异常编码
 * <p/>
 * 创建人: chunlin.li
 * <p/>
 * 创建时间: 2015/12/03.
 * <p/>
 * Copyright (c) 深圳市牛鼎丰科技有限公司-版权所有
 */
public enum CreditResultCode implements IEnum {
    /**
     * 接口异常编码
     */
    EXCEPTION_BUSINESS_LOAN(990101, "借贷业务异常"),
    EXCEPTION_BUSINESS_INVEST(990102, "投资业务异常"),
    //todo 现在其他设备登录失效无法判断
//    EXCEPTION_TOKEN(990103, "您的账号已在其他移动设备上登录或登录已过期，请重新登录!"),
    EXCEPTION_SMS(990104, "短信发送失败"),
    EXCEPTION_MOBILE(990105, "手机号已注册"),
    EXCEPTION_VCODE(990106, "您的验证码输入有误，请重新输入"),
    EXCEPTION_LOGINERR(990107, "登录密码错误次数超限"),
    EXCEPTION_PAYPWD(990108, "交易密码已设置"),
    EXCEPTION_APP(990109, "当前已经是最新版本"),
    EXCEPTION_PAYPWD_ERROR(990110, "支付密码错误"),
    EXCEPTION_LOGIN_FROZEE(990111, "账号已锁定，请明日再登录"),
    EXCEPTION_LOGIN_FAIL(990112, "登录出错"),
    EXCEPTION_TOKEN_INVALID(990113, "业务token失效"),
    EXCEPTION_RESET_LOGINPWD(990114, "请重置登录密码"),
    EXCEPTION_GET_MOBILE(990115, "获取用户手机号失败"),
    EXCEPTION_SEND_MOBILE_VCODE(990116, "发送短信验证码出错"),
    /**
     * 运行时异常
     */
    EXCEPTION_UNKWOWN_LOAN(990201, "服务繁忙，请稍后重试"),

    EXCEPTION_UNKWOWN_INVEST(990202, "服务繁忙，请稍后重试"),

    /**
     * 远程接口调用异常
     */
    EXCEPTION_RPC_USER(990401, "用户中心远程服务调用异常"),

    EXCEPTION_RPC_ACCOUNT(990402, "账户中心远程服务调用异常"),

    EXCEPTION_RPC_PAY(990403, "支付中心远程服务调用异常"),

    EXCEPTION_MOBILE_NOTEXITS(990405, "手机号未注册"),

    /**
     * ocr认证
     */
    OVER_OCR_SIZE_LIMIT(990500, "OCR上传图片超出最大尺寸限制"),
    OVER_FACE_VIDEO_SIZE_LIMIT(990501, "活体检测视频超出最大尺寸限制"),
    BANK_CARD_BANKFACES_OCR_FAIL(990502, "银行卡识别失败，请重试"),
    ID_CARD_FRONT_OCR_FAIL(990503, "身份证正面识别失败，请重试"),
    ID_CARD_BANKFACES_OCR_FAIL(990504, "身份证反面识别失败，请重试"),
    UN_SUPPORT_OCR_TYPE(990505, "OCR不支持该类型认证"),
    AUTH_STEP_HAD_DONE(990506, "认证步骤已经完成"),
    PRE_AUTH_STEP_HAVENT_DONE(990507, "请先完成前一步认证"),
    ID_CARD_AUTHENTICATION_FAIL(990508, "身份证认证失败"),
    VEDIO_AUTHENTICATION_FAIL(990509, "视频认证失败"),;

    private Integer key;

    private String value;

    private CreditResultCode(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    private CreditResultCode(ResultCode resultCode) {
        this.key = resultCode.getKey();
        this.value = resultCode.getValue();
    }

    @Override
    public Integer getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

}
