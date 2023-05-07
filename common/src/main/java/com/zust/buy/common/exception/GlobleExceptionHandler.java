package com.zust.buy.common.exception;

import com.zust.buy.common.entity.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobleExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseData exceptionHandler(HttpServletRequest request, Exception e) {
        log.info(e.getMessage());
        return ResponseData.error("服务端异常：" + e.getMessage() + "请联系管理员。");
    }
}
