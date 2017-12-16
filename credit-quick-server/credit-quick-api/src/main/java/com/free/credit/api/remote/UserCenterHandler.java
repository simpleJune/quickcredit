package com.free.credit.api.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("userCenterHandler")
public class UserCenterHandler {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 注册用户
     *
     * @throws Exception
     *//*
    public ServiceResponse<RegisterRlt> doRegister(RequestHead requestHead, String mobile, String password) {
        
        return convertCommonRlt(rlt);
    }

    *//**
     * 登录
     *
     * @param requestHead
     * @param mobile
     * @param password
     * @return
     *//*
    public ServiceResponse<LoginRlt> doLogin(RequestHead requestHead, String mobile, String password) {
        LoginReq model = new LoginReq();
        model.setLoginName(mobile);
        model.setLoginPwd(password);
        initBaseReq(requestHead, model);
        model.setSign(RequestSignUtils.addSign(model, model.getSign()));

        logger.info(">>> process:UserService|用户登录|req={}", model);
        CommonRlt<LoginRlt> rlt = userLoginService.doLogin(model);
        logger.info("<<< process:UserService|用户登录|req={},result={}", model, rlt);
        return convertCommonRlt(rlt);
    }

    *//**
     * 方法描述：获取用户密码输入错误次数
     *
     * @param requestHead
     * @param loginName
     * @return
     *//*
    public ServiceResponse<LoginErrorTimeRlt> getLoginErrorTimes(RequestHead requestHead, String loginName) {
        LoginErrorTimeReq req = new LoginErrorTimeReq();
        req.setLoginName(loginName);
        initBaseReq(requestHead, req);
        req.setSign(RequestSignUtils.addSign(req, req.getSign()));

        CommonRlt<LoginErrorTimeRlt> rlt = userLoginService.getLoginErrorTime(req);
        logger.info("<<< process:UserService|获取登录密码错误次数|req={},result={}", req, rlt);
        return convertCommonRlt(rlt);
    }


    *//**
     * 是否登录
     *
     * @param head
     * @return
     * @throws Exception
     *//*
    public ServiceResponse<Boolean> isLogin(RequestHead head) {
        CheckLoginReq model = new CheckLoginReq();
        model.setMemberNo(head.getUserId());
        model.setTokenId(WebUtil.getUserToken(head.getAccessToken()));
        initBaseReq(head, model);
        model.setSign(RequestSignUtils.addSign(model, model.getSign()));

        CommonRlt<Boolean> rlt = userLoginService.doIsLogin(model);
        return convertCommonRlt(rlt);
    }


    *//**
     * 方法描述：校验用户密码是否正确
     *
     * @param head
     * @param pwd
     * @return
     *//*
    public CommonRlt<Boolean> checkPwdByMemberNo(RequestHead head, String pwd) {
        CheckLoginPwdByMemberNoReq model = new CheckLoginPwdByMemberNoReq();
        model.setMemberNo(head.getUserId());
        model.setLoginPwd(pwd);
        initBaseReq(head, model);
        model.setSign(RequestSignUtils.addSign(model, model.getSign()));

        CommonRlt<Boolean> rlt = userLoginService.docheckLoginPwdByMemberNo(model);
        return rlt;
    }


    *//**
     * 退出
     *
     * @return
     *//*
    public ServiceResponse<EmptyObject> doLogout(RequestHead head, BaseRequest baseReq) {
        LoginOutReq model = new LoginOutReq();
        model.setMemberNo(baseReq.getUserId());
        model.setTokenId(WebUtil.getUserToken(head.getAccessToken()));
        initBaseReq(head, model);
        model.setSign(RequestSignUtils.addSign(model, model.getSign()));

        CommonRlt<EmptyObject> rlt = userLoginService.doLoginOut(model);
        logger.info("<<< process:UserService|用户登录|req={},result={}", model, rlt);
        return convertCommonRlt(rlt);
    }


    *//**
     * 获取登录用户信息(根据token获取用户信息)
     *
     * @param req
     * @return
     * @throws Exception
     *//*
    public ServiceResponse<CustomerInfoRlt> getUserInfo(RequestHead req) {
        CustomerInfoReq model = new CustomerInfoReq();
        initBaseReq(req, model);
        model.setMemberNo(req.getUserId());
        model.setSign(RequestSignUtils.addSign(model, model.getSign()));

        CommonRlt<CustomerInfoRlt> rlt = iCustomerInfoService.getUserInfo(model);
        logger.debug("<<< process:ICustomerInfoService|获取用户信息|model={},result={}", model, rlt);
        return convertCommonRlt(rlt);
    }

    public ServiceResponse<CustomerInfoRlt> getUserInfoByMobile(RequestHead req, String mobile) {
        CustomerInfoReq2 model = new CustomerInfoReq2();
        initBaseReq(req, model);
        model.setLoginName(mobile);
        model.setSign(RequestSignUtils.addSign(model, model.getSign()));

        CommonRlt<CustomerInfoRlt> rlt = iCustomerInfoService.getUserInfoByLoginName(model);
        logger.debug("<<< process:ICustomerInfoService|获取用户信息|model={},result={}", model, rlt);
        return convertCommonRlt(rlt);
    }


    *//**
     * 修改登录密码
     *
     * @param head
     *//*
    public ServiceResponse<EmptyObject> doModifyLoginPwd(RequestHead head, ModifyLoginPwdRequest req) {
        ModifyLoginPwdReq model = new ModifyLoginPwdReq();
        initBaseReq(head, model);
        model.setMemberNo(req.getUserId());
        model.setTokenId(WebUtil.getUserToken(head.getAccessToken()));
        model.setLoginPwd(req.getOldPwd());
        model.setLoginNewPwd(req.getNewPwd());
        model.setSign(RequestSignUtils.addSign(model, model.getSign()));
        CommonRlt<EmptyObject> rlt = userPwdService.doModifyLoginPwd(model);
        logger.info("<<< process:userPwdService|修改登录密码|model={},result={}", model, rlt);
        return convertCommonRlt(rlt);
    }

    *//**
     * 重置登录密码
     *
     * @param head
     * @param mobile      手机号码
     * @param newLoginPwd 新密码
     * @return
     *//*
    public ServiceResponse<EmptyObject> doResetLoginPwd(RequestHead head, String mobile, String newLoginPwd) {
        ResetLoginPwdReq model = new ResetLoginPwdReq();
        initBaseReq(head, model);
        model.setLoginName(mobile);
        model.setLoginNewPwd(newLoginPwd);
        model.setSign(RequestSignUtils.addSign(model, model.getSign()));
        CommonRlt<EmptyObject> rlt = userPwdService.doResetLoginPwd(model);
        logger.info("<<< process:userPwdService|重置登录密码|model={},result={}", model, rlt);
        return convertCommonRlt(rlt);
    }

    *//**
     * 方法描述：检查手机号在用户系统中是否有注册
     *
     * @param head
     * @param mobile
     * @return boolean 为true表明没注册，值为false表明已经注册
     *//*
    public CommonRlt<Boolean> checkMobile(RequestHead head, String mobile) {
        // 校验手机号在用户中心是否存在
        CheckMobileReq model = new CheckMobileReq();
        initBaseReq(head, model);
        model.setLoginName(mobile);
        model.setSign(RequestSignUtils.addSign(model, model.getSign()));
        return userRegisterService.doCheckMobile(model);
    }

    *//**
     * 方法描述：根据用户ID查询手机号
     *
     * @param head
     * @param memberNo
     * @return
     *//*
    public CommonRlt<String> getMobileById(RequestHead head, String memberNo) {
        CustomerInfoReq model = new CustomerInfoReq();
        initBaseReq(head, model);
        model.setMemberNo(memberNo);
        model.setSign(RequestSignUtils.addSign(model, model.getSign()));
        return iCustomerInfoService.findMobileById(model);
    }


    public ServiceResponse<EmptyObject> updateCustomerInfo(RequestHead head, AuthSendSmsRequest req) {
        UpdateCustomerInoReq model = new UpdateCustomerInoReq();
        initBaseReq(head, model);
        model.setBanckCode(req.getBankCode());
        model.setBankCardNo(req.getBankCardNo());
        model.setBankName(req.getBankName());
        model.setMemberName(req.getUserName());
        model.setMemberNo(head.getUserId());
        model.setCertNo(req.getIdCard());
        model.setCertType(CertType.SFZ);
        model.setSign(RequestSignUtils.addSign(model, model.getSign()));

        CommonRlt<EmptyObject> rlt = iCustomerInfoService.updateCustomerInfo(model);
        logger.info("<<< process:CustomerInfoService|更新用户信息|model={},result={}", model, rlt);
        return convertCommonRlt(rlt);
    }

    *//**
     * 实名验证接口
     *
     * @param head
     * @param flag
     * @return
     * @创建人 何源
     * @创建时间 2016年5月30日下午4:10:09
     *//*
    public ServiceResponse<EmptyObject> updateNameFlag(RequestHead head, boolean flag) {
        // 更新用户实名标识
        NameFlagReq model = new NameFlagReq();
        initBaseReq(head, model);
        model.setMemberNo(head.getUserId());
        model.setFlag(flag ? "Y" : "N"); //实名认证标识 N未实名，Y已实名
        model.setSign(RequestSignUtils.addSign(model, model.getSign()));
        CommonRlt<EmptyObject> rlt = iCustomerInfoService.updateNameFlag(model);
        logger.info("CustomerInfoService更新用户实名标识,model={},result={}", model, rlt);
        return convertCommonRlt(rlt);
    }

    *//**
     * 实名认证之后更新用户身份证信息
     *
     * @param head
     * @param req
     * @return
     *//*
    public ServiceResponse<EmptyObject> updateCertInfo(RequestHead head, AuthSendSmsRequest req) {
        // 更新用户实名标识
        CertReq certReq = new CertReq();
        initBaseReq(head, certReq);
        certReq.setCertNo(req.getIdCard());
        certReq.setMemberNo(req.getUserId());
        certReq.setMemberName(req.getUserName());
        certReq.setCertType(CertType.SFZ);
        certReq.setSign(RequestSignUtils.addSign(certReq, certReq.getSign()));
        CommonRlt<EmptyObject> rlt = iCustomerInfoService.updateCertInfo(certReq);
        logger.info("CustomerInfoService更新用户身份证信息,model={},result={}", certReq, rlt);
        return convertCommonRlt(rlt);
    }

    *//**
     * 更新用户人脸识别标识
     *
     * @param head
     * @param flag
     * @return
     *//*
    public ServiceResponse<EmptyObject> updateScanFlag(RequestHead head, boolean flag) {
        // 更新用户实名标识
        UpdateScanFlagReq req = new UpdateScanFlagReq();
        initBaseReq(head, req);
        req.setMemberNo(head.getUserId());
        req.setScanFlag(flag ? "1" : "2");//实名认证标识 N未实名，Y已实名
        req.setSign(RequestSignUtils.addSign(req, req.getSign()));
        CommonRlt<EmptyObject> rlt = iCustomerInfoService.updateScanFlag(req);
        logger.info("CustomerInfoService更新用户实名标识,model={},result={}", req, rlt);
        return convertCommonRlt(rlt);
    }


    *//**
     * 方法描述：记录/更新 设备信息
     *
     * @param head
     * @param userId
     * @param flag(1、更新设备信息 2、记录设备信息)
     *//*
    public void doDeviceInfo(RequestHead head, String userId, int flag) {

        DeviceInfoReq model = new DeviceInfoReq();
        model.setMemberNo(userId);
        model.setDeviceId(head.getDeviceId());
        model.setDeviceDetail(head.getPhoneType());
        model.setDeviceResolution(head.getPhoneResolution());
        model.setSystemVersion(head.getVersion());
        model.setToken(WebUtil.getUserToken(head.getAccessToken()));
        this.initBaseReq(head, model);
        model.setSign(RequestSignUtils.addSign(model, model.getSign()));

        if (flag == 1) {
            CommonRlt<EmptyObject> rlt = iCustomerInfoService.updateDeviceInfo(model);
            logger.info("iCustomerInfoService更新设备信息,参数={},result={}", model, rlt);
        } else if (flag == 2) {
            CommonRlt<EmptyObject> rlt = iCustomerInfoService.createDeviceInfo(model);
            logger.info("iCustomerInfoService创建设备信息,参数={},result={}", model, rlt);
        }
    }


    *//**
     * 对象转换
     *
     * @param commonRlt
     * @return
     *//*
    public <T> ServiceResponse<T> convertCommonRlt(final CommonRlt<T> commonRlt) {
        if (commonRlt.getReturnCode() == ResultCode.SUCCESS.getKey()) {
            return new ServiceResponse<T>(commonRlt.getData());
        } else {
            return new ServiceResponse<T>(commonRlt.getReturnCode(), commonRlt.getReturnMsg());
        }
    }


    public BaseResponse convertError(IEnum head) {
        if (ResultMsgEnum.LOGINNAME_NO_EXIST.getReturnCode() == head.getKey()) {
            return new BaseResponse("userNotExists", "用户不存在");
        } else if (ResultMsgEnum.LOGINNAME_EXIST.getReturnCode() == head.getKey()) {
            return new BaseResponse("userExists", "登录名已存在");
        } else if (ResultMsgEnum.LOGIN_PWD_ERROR.getReturnCode() == head.getKey()) {
            return new BaseResponse("passwordNotRight", "密码错误");
        } else if (ResultMsgEnum.LOGIN_OLDPWD_ERROR.getReturnCode() == head.getKey()) {
            return new BaseResponse("passwordNotRight", "原始密码错误");
        } else if (ResultMsgEnum.PAY_PWD_ERROR.getReturnCode() == head.getKey()) {
            return new BaseResponse("payPwdNotRight", "支付密码错误");
        } else if (ResultMsgEnum.PAY_PWD_LOCK.getReturnCode() == head.getKey()) {
            return new BaseResponse("payPwdLock", "支付密码输入错误次数已大于最大");
        } else if (ResultMsgEnum.LOGIN_PWD_LOCK.getReturnCode() == head.getKey()) {
            return new BaseResponse("loginPwdLock", "登录密码输入错误次数已大于最大");
        } else if (ResultMsgEnum.REFEREEUID_NOTEXIST.getReturnCode() == head.getKey()) {
            return new BaseResponse("rcUserNotExists", "推荐人不存在");
        } else if (ResultMsgEnum.NO_INFO.getReturnCode() == head.getKey()) {
            return new BaseResponse("noInfo", "未获取到数据");
        } else if (ResultMsgEnum.TOKEN_NOEXIST.getReturnCode() == head.getKey()) {
            return new BaseResponse("tokenInvalide", "token无效");
        } else if (ResultMsgEnum.STATUS_NOT_CORRENT.getReturnCode() == head.getKey()) {
            return new BaseResponse("statusNotRight", "数据状态不正确");
        } else if (ResultMsgEnum.NOT_BINDING.getReturnCode() == head.getKey()) {
            return new BaseResponse("accountNotBind", "账户没有绑定");
        } else if (ResultMsgEnum.MEMBERNO_NO_EXIST.getReturnCode() == head.getKey()) {
            return new BaseResponse("membernoNotExists", "用户不存在");
        } else if (ResultMsgEnum.SYSTEM_ERROR.getReturnCode() == head.getKey()) {
            return ResponseUtil.getErrorServ();
        } else {
            return new BaseResponse(head.getKey(), head.getValue());
        }
    }

    *//**
     * 方法描述：将APP端传过来的头信息转换为用户模块需要的信息
     *
     * @param head
     * @param model
     *//*
    private void initBaseReq(RequestHead head, BaseReq model) {
        model.setAppVersion(head.getVersion());
        model.setSourceType(head.getOsType());
        userConfig.initBaseReq(userConfig.getPartnerId(), model);
    }*/

}
