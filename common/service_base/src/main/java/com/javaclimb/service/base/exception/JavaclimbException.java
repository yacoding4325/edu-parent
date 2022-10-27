package com.javaclimb.service.base.exception;

import com.javaclimb.service.base.result.ResultCodeEnum;
import lombok.Data;

/**
 * 自定义异常
 * 用户自己主动抛出的异常
 */

/**
 * @Author yaCoding
 * @create 2022-10-26 下午 4:59
 */
@Data
public class JavaclimbException extends RuntimeException{
    private Integer code;

    public JavaclimbException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}
