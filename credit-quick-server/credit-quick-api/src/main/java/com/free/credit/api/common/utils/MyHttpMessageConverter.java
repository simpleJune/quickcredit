package com.free.credit.api.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServletServerHttpRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.free.credit.api.common.annotation.XnParamParser;
import com.free.credit.api.common.base.RequestHead;
import com.free.credit.api.common.constants.Constants;
import com.free.platform.base.exception.ParamException;

public class MyHttpMessageConverter extends FastJsonHttpMessageConverter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @SuppressWarnings("unchecked")
    @Override
    protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Object rlt = null;
        String apiUrl = null;
        if (inputMessage instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest request = (ServletServerHttpRequest) inputMessage;
            Map<String, Object> data = (Map<String, Object>) request.getServletRequest().getAttribute(Constants.REQUEST_PARAMS);
            if (data == null) {
                return null;
            }
            Object obj;
            if (clazz == RequestHead.class) {
                obj = data.get(Constants.API_PARAMS_HEADER_KEY);
            } else {
                obj = data.get(Constants.API_PARAMS_BODY_KEY);
            }
            if (obj == null) {
                obj = new HashMap<String, String>(1);
            }

            try {
                rlt = JSON.parseObject(JSON.toJSONString(obj), clazz);
            } catch (Exception e) {
                logger.error("=====>>>params={}", JSON.toJSONString(obj));
                throw new ParamException("参数类型错误", e);
            }
            apiUrl = getApiUrl(request.getServletRequest());
        } else {
            byte[] bytes = null;
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                InputStream in = inputMessage.getBody();
                byte[] buf = new byte[1024];
                for (; ; ) {
                    int len = in.read(buf);
                    if (len == -1) {
                        break;
                    }

                    if (len > 0) {
                        baos.write(buf, 0, len);
                    }
                }
                bytes = baos.toByteArray();
                rlt = JSON.parseObject(bytes, 0, bytes.length, super.getCharset().newDecoder(), clazz);
            } catch (Exception e) {
                logger.error("=====>>>params={}", JSON.parse(bytes, 0, bytes.length, super.getCharset().newDecoder()));
                throw new ParamException("参数类型错误", e);
            }
        }

        if (logger.isInfoEnabled()) {
            logger.info("=====>>>url:{};params:{}", apiUrl, XnParamParser.getLog(rlt));
        }

        return rlt;
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

    @Override
    protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        if (logger.isInfoEnabled()) {
            logger.info("=====<<< outputObject:{}", XnParamParser.getLog(obj));
        }
        super.writeInternal(obj, outputMessage);
    }
}
