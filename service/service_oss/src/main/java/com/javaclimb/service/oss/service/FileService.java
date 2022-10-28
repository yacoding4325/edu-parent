package com.javaclimb.service.oss.service;

import java.io.InputStream;
/**
 * @Author yaCoding
 * @create 2022-10-28 下午 5:12
 */

/**
 * 文件上传接口
 */
public interface FileService {

    /**
     * 阿里云Oss文件上传
     * @param inputStream 上传文件的输入流
     * @param module        上传到oss的文件目录
     * @param orginalFileName 原始文件名
     * @return              返回上传上去之后的url地址
     */

    String upload(InputStream inputStream,String module,String orginalFileName);
}
