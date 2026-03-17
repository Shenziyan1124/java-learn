package com.itheima.exception;

import com.itheima.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * 全局异常处理
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception ex) {
        log.error(ex.getMessage());
        return Result.error(ex.getMessage());
    }

}
