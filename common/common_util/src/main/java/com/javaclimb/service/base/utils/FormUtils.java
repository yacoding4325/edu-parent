package com.javaclimb.service.base.utils;

/**
 * @Author yaCoding
 * @create 2022-11-04 下午 1:56
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具类
 */
public class FormUtils {
    /*手机号验证 */
    public static boolean isMobile(String str) {
        Pattern p = Pattern.compile("^[1][3,4,5,7,8,9][0-9]{9}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
