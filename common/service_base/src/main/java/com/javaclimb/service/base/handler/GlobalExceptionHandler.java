package com.javaclimb.service.base.handler;

import com.javaclimb.service.base.result.R;
import com.javaclimb.service.base.exception.JavaclimbException;
import com.javaclimb.service.base.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理所有的异常
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R error(Exception e){
        //控制台打印异常
        e.printStackTrace();
        log.error(e.getMessage());
        log.error(ExceptionUtils.getMessage(e));
        //返回异常
        return R.error();
    }

    /**
     * 处理数学异常
     */
    @ResponseBody
    @ExceptionHandler(ArithmeticException.class)
    public R arithmeticException(Exception e){
        //控制台打印异常
        e.printStackTrace();
        log.error(e.getMessage());
        log.error(ExceptionUtils.getMessage(e));
        //返回异常
        return R.error().message("数学异常");
    }

    /**
     * 自定义异常
     */
    @ResponseBody
    @ExceptionHandler(JavaclimbException.class)
    public R javaclimbException(JavaclimbException e){
        //控制台打印异常
        e.printStackTrace();
        log.error(e.getMessage());
        log.error(ExceptionUtils.getMessage(e));
        //返回异常
        return R.error().message(e.getMessage()).code(e.getCode());
    }
}
