package com.coding.service.base.utils;

/**
 * @Author yaCoding
 * @create 2022-10-26 下午 5:03
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 从异常信息中拿到错误信息栈
 */
public class ExceptionUtils {

    public static String getMessage(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}
