package com.javaclimb.service.base.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaclimb.service.base.result.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author yaCoding
 * @create 2022-11-09 下午 5:59
 */
//写入 Response的数据
public class ResponseUtil {

    public static void out(HttpServletResponse response, R r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(),r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
