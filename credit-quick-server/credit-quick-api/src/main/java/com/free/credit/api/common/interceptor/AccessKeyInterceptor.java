package com.free.credit.api.common.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.free.credit.api.common.base.ErrorResponse;
import com.free.credit.api.common.base.RequestHead;
import com.free.credit.api.common.constants.Constants;
import com.free.credit.api.common.utils.CommonResult;
import com.free.credit.api.common.utils.RequestUtil;
import com.free.credit.api.common.utils.ResponseUtil;
import com.free.credit.api.common.utils.WebUtil;
import com.free.credit.api.remote.UserCenterHandler;
import com.free.platform.base.utils.StringUtils;


public class AccessKeyInterceptor extends HandlerInterceptorAdapter {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private UserCenterHandler userCenterHandler;

    private List<String> noNeedTokenServices; // 白名单URL列表

    // 是否开启权限验证
    @Value("${openTokenValidate}")
    private boolean openTokenValidate = true;

    // 参数是否需要解密
    @Value("${data.encryptFlag}")
    boolean encryptFlag = false;

    @Resource
    public RequestMappingHandlerMapping handlerMapping;
    // RequestMapping Path List
    protected final static Set<String> patternPaths = new HashSet<String>();


    @SuppressWarnings("unchecked")
	@Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String apiUrl = getApiUrl(request);
        request.setAttribute(Constants.REQUEST_ENCRYPT_FLAG, encryptFlag);
        //参数打印截取
        if(request.getParameterMap()!=null&&request.getParameterMap().toString().length()>1000) {
            logger.info("===>>> process:鉴权处理器|请求|apiUrl={},params={}", apiUrl, request.getParameterMap().toString().substring(0,1000));
        }else{
            logger.info("===>>> process:鉴权处理器|请求|apiUrl={},params={}", apiUrl, request.getParameterMap());
        }

//        logger.info("===>>> process:鉴权处理器|请求|apiUrl={},params={}", apiUrl, request.getParameterMap());

        // 请求路径校验
        if (!this.checkReqUrlExists(apiUrl, response)) {
            return responseOutWithJson(response, ResponseUtil.getErrorInvalideReq());
        }

        // HTTP请求
        if (request.getContentType() != null) {
            if ((MediaType.valueOf(request.getContentType()).includes(MediaType.APPLICATION_JSON)
                    || MediaType.valueOf(request.getContentType()).includes(MediaType.APPLICATION_FORM_URLENCODED)) ) {

                // 获取参数
                Map<String, Object> params = null;
                try {
                    if (MediaType.valueOf(request.getContentType()).includes(MediaType.APPLICATION_FORM_URLENCODED)) {
                        String paramJson = RequestUtil.getRequestJsonString(request);
                        params = JSON.parseObject(paramJson, Map.class);
                    } else {
                        params = this.getParamsBody(encryptFlag, request);
                    }
                } catch (Exception e) {
                    logger.error("=== process:鉴权处理器|获取参数错误 ", e);
                    return responseOutWithJson(response, ResponseUtil.getErrorBusi(e.getMessage()));
                }
                //参数打印截取
                if(params!=null&&params.toString().length()>1000) {
                    logger.info("===>>> process:鉴权处理器|请求|apiUrl={},params={}", apiUrl, params.toString().substring(0,1000));
                }else{
                    logger.info("===>>> process:鉴权处理器|请求|apiUrl={},params={}", apiUrl, params);
                }
                // 请求参数或者请求头为空
                if (params == null || params.get(Constants.API_PARAMS_HEADER_KEY) == null) {
                    return responseOutWithJson(response, ResponseUtil.getErrorInvalideReq());
                }
                request.setAttribute(Constants.REQUEST_PARAMS, params);

                // 验证Token
                CommonResult checkResult = checkToken(getApiUrl(request), params, response);
                if (checkResult.isLoginStatus()) {
                    request.setAttribute(Constants.REQUEST_PARAMS, params);
                    return true;
                } else {
                    ErrorResponse errorRes = new ErrorResponse(checkResult.getCode(), checkResult.getMsg());
                    return responseOutWithJson(response, errorRes);
                }
            } else if (MediaType.valueOf(request.getContentType()).includes(MediaType.MULTIPART_FORM_DATA)) { // 文件上传请求
                logger.info("===>>> process:鉴权处理器|文件上传请求|ContentType={}", MediaType.valueOf(request.getContentType()));
                if (openTokenValidate && needToken(getApiUrl(request))) { // 验证token是否有效
                    RequestHead head = new RequestHead();
                    head.setAccessToken(request.getParameter("accessToken"));
                    logger.info("===>>> process:鉴权处理器|请求参数|token={}", request.getParameter("autoToken"));

                    try {
                        if (StringUtils.isNotBlank(head.getAccessToken())) {
                        	
                        	//TODO 验证是否登录
                            /*ServiceResponse<Boolean> rlt = userCenterHandler.isLogin(head);
                            logger.info("<<< process:鉴权处理器|检查用户登录态|返回参数|request={}, response={}", head, rlt);
                            if (rlt.isSuccess()&&rlt.getData()) {
                                return true;
                            }*/
                        }
                    } catch (Exception e) {
                        logger.error("=== process:鉴权处理器|文件上传|检查用户登录态异常|apiUrl={},request={}", apiUrl, request, e);
                    }

                    return responseOutWithJson(response, ResponseUtil.getErrorToken());
                }
                return true;
            } else if (MediaType.valueOf(request.getContentType()).includes(MediaType.APPLICATION_FORM_URLENCODED)) {
                return true;
            }
        }

        return responseOutWithJson(response, ResponseUtil.getErrorInvalideReq());
    }

    /**
     * 验证token
     *
     * @param apiUrl   api路径
     * @param params   请求参数
     * @param response
     * @return
     * @创建人 何源
     * @创建时间 2016年7月13日上午10:02:48
     */
    @SuppressWarnings("unchecked")
    private CommonResult checkToken(String apiUrl, Map<String, Object> params, HttpServletResponse response) {
        CommonResult result = new CommonResult();
        if (params != null) {
            // 白名单URL不验证Token
            if (!needToken(apiUrl)) {
                result.setLoginStatus(true);
                return result;
            }
            logger.info("===>>> openTokenValidate={}",openTokenValidate);
            // 验证token是否有效
            if (openTokenValidate) {
                Map<Object, Object> header = (Map<Object, Object>) params.get(Constants.API_PARAMS_HEADER_KEY);
                RequestHead head = JSON.parseObject(JSON.toJSONString(header), RequestHead.class);
                if (head != null) {
                    if (StringUtils.isNotBlank(head.getAccessToken())) {
                        try {
                        	// TODO 验证token是否有效
                        	//验证token是否有效
                           /* ServiceResponse<Boolean> rlt = userCenterHandler.isLogin(head);
                            if (rlt.isSuccess()&&rlt.getData()) {
                                result.setLoginStatus(Boolean.TRUE);
                                return result;
                            }*/
                        } catch (Exception e) {
                            logger.error("=== process:鉴权处理器|验证用户登录态失败|apiUrl={},request={}", apiUrl, params, e);
                            return result;
                        }
                    }

                    result.setLoginStatus(responseOutWithJson(response, ResponseUtil.getErrorToken()));
                }
            } else {
                result.setLoginStatus(Boolean.TRUE);
            }
        }

        return result;
    }


    /**
     * 获取请求参数
     *
     * @param request
     * @return
     * @创建人 何源
     * @创建时间 2016年7月12日下午5:35:55
     */
    @SuppressWarnings("unchecked")
	private Map<String, Object> getParamsBody(Boolean encryptFlag, HttpServletRequest request) {
        Map<String, Object> retData = null;
        byte[] bytes;
        if (encryptFlag) {
        } else {
            try {
                bytes = IOUtils.toByteArray(request.getInputStream());
            } catch (IOException e) {
                logger.error(">>> process:鉴权处理器|读取参数错误", e);
                bytes = null;
            }

            if (bytes != null && bytes.length > 0) {
                retData = JSON.parseObject(bytes, 0, bytes.length, Charset.forName("UTF-8").newDecoder(), Map.class);
            } else {
                retData = new HashMap<String, Object>();
            }

            Map<Object, Object> header = (Map<Object, Object>) retData.get(Constants.API_PARAMS_HEADER_KEY);
            Map<Object, Object> body = (Map<Object, Object>) retData.get(Constants.API_PARAMS_BODY_KEY);
            if (body == null) {
                body = new HashMap<Object, Object>();
                retData.put(Constants.API_PARAMS_BODY_KEY, body);
            }
            if (header != null) {
                RequestHead head = JSON.parseObject(JSON.toJSONString(header), RequestHead.class);
                if (head != null && StringUtils.isNotBlank(head.getAccessToken())) {
                    header.put(Constants.API_PARAMS_USERID_KEY, WebUtil.getUserId(head.getAccessToken()));
                    body.put(Constants.API_PARAMS_USERID_KEY, WebUtil.getUserId(head.getAccessToken()));
                }
            }
        }

        return retData;
    }

    /**
     * 获取接口路径
     *
     * @param request
     * @return
     * @创建人 何源
     * @创建时间 2016年7月12日下午5:17:18
     */
    private String getApiUrl(HttpServletRequest request) {
        String url = request.getRequestURI().substring(request.getRequestURI().lastIndexOf(
                request.getServletPath()) + request.getServletPath().length());
        return url;
    }


    private boolean needToken(String url) {
        if (noNeedTokenServices != null) {
            for (String temp : noNeedTokenServices) {
                if (url.contains(temp.trim())) {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * 将对象以json对象输出
     *
     * @param response
     * @param obj      输出对象
     * @return
     * @创建人 何源
     * @创建时间 2016年7月8日上午9:59:27
     */
    @SuppressWarnings("static-access")
	protected boolean responseOutWithJson(HttpServletResponse response, Object obj) {
        //将实体对象转换为JSON Object转换
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        if (response.getStatus() == response.SC_NOT_FOUND){
            return false;
        }

        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.append(JSONObject.toJSONString(obj));
            logger.info("=== process:鉴权处理器|outputObject={}", obj);
        } catch (IOException e) {
            logger.error("=== process:鉴权处理器|返回错误", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }

        return false;
    }


    public List<String> getNoNeedTokenServices() {
        return noNeedTokenServices;
    }

    public void setNoNeedTokenServices(List<String> noNeedTokenServices) {
        this.noNeedTokenServices = noNeedTokenServices;
    }



    @SuppressWarnings({ "rawtypes", "static-access" })
	protected boolean checkReqUrlExists(String requestUrl, HttpServletResponse response) {
        if (StringUtils.isBlank(requestUrl)) {
            return false;
        }

        try {
            if (patternPaths.isEmpty()) {
                Map handlerMethods = this.handlerMapping.getHandlerMethods();
                Set handlerSet = handlerMethods.keySet();
                Iterator iterator = handlerSet.iterator();
                while (iterator.hasNext()) {
                    RequestMappingInfo iter = (RequestMappingInfo) iterator.next();
                    Set patterns = iter.getPatternsCondition().getPatterns();
                    patternPaths.add(String.valueOf(patterns.iterator().next()));
                }
            }

            // TODO 判断路径是否存在, 设置 HTTP_STATUS
            if (!patternPaths.contains(requestUrl)) {
                response.setStatus(response.SC_NOT_FOUND);
                return false;
            }
        } catch (Exception e) {
            logger.warn("=== process:鉴权处理器|请求路径校验异常", e);
        }

        return true;
    }


    public RequestMappingHandlerMapping getHandlerMapping() {
        return handlerMapping;
    }

    public void setHandlerMapping(RequestMappingHandlerMapping handlerMapping) {
        this.handlerMapping = handlerMapping;
    }
}