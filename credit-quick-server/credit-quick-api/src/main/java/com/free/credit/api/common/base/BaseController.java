package com.free.credit.api.common.base;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import com.free.credit.api.common.utils.DateEditor;
import com.free.credit.api.common.utils.ResponseUtil;
import com.free.credit.common.request.UserReq;
import com.free.platform.base.enums.ResultCode;
import com.free.platform.base.exception.ParamException;
import com.free.platform.base.skeleton.ServiceRequest;

public class BaseController {
    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

	
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateEditor());

    }

    @ExceptionHandler
    @ResponseBody
    public BaseResponse exp(Exception ex) {
        if (ex instanceof ParamException) {
            ParamException exception = (ParamException) ex;
            logger.warn(">>> 参数错误", ex);
            return ResponseUtil.getErrorParam(exception.getErrorMsg());
        }

        if (ex instanceof MethodArgumentNotValidException) {
            logger.warn(">>> 参数错误", ex);
            Object[] notValidObj = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldError().getArguments();
            String message = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldError().getDefaultMessage();
            StringBuilder sb = new StringBuilder();
            for (Object o : notValidObj) {
                if (!(o instanceof DefaultMessageSourceResolvable))
                    continue;
                DefaultMessageSourceResolvable messageSourceResolvable = (DefaultMessageSourceResolvable) o;
                sb.append("参数[").append(messageSourceResolvable.getDefaultMessage()).append("]").append(message).append(",");
                break;
            }
            return ResponseUtil.getError(ResultCode.EXCEPTION_UNKWOWN.getKey(), sb.toString());
        }
        logger.error("=== 服务器异常", ex);
        return ResponseUtil.getErrorServ();
    }


    protected void initHead(UserReq param, RequestHead head) {
        //param.setPartnerId(defaultConfig.getPartnerId());
        param.setUserId(head.getUserId());
    }

    protected void initHead(ServiceRequest param, RequestHead head) {
        //param.setPartnerId(defaultConfig.getPartnerId());
    }
}
