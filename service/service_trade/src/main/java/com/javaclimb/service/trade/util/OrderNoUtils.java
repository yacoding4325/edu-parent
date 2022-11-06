package com.javaclimb.service.trade.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 订单号工具类
 */
public class OrderNoUtils {

    /**
     * 获取订单号
     * 年月日时分秒+三位整数
     */
    public static String getOrderNo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for(int i=0;i<3;i++){
            //随机生成一个整数，这个整数的范围就是[0,9]
            result += random.nextInt(10);
        }
        return newDate + result;
    }

    public static void main(String[] args) {
        String s = getOrderNo();
        System.out.println(s);
    }
}
