package com.javaclimb.service.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.javaclimb.service.oss.service.FileService;
import com.javaclimb.service.oss.utils.OssProperties;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author yaCoding
 * @create 2022-10-28 下午 5:13
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private OssProperties ossProperties;

    /**
     * 阿里云Oss文件上传
     * @param inputStream 上传文件的输入流
     * @param module        上传到oss的文件目录
     * @param orginalFileName 原始文件名
     * @return
     */

    @Override
    public String upload(InputStream inputStream, String module, String orginalFileName) {
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ossProperties.getEndpoint();
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ossProperties.getKeyId();
        String accessKeySecret = ossProperties.getKeySecret();
        String bucketName = ossProperties.getBucketName();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        String folder = new DateTime().toString("yyyy/MM/dd");
        // 文件名
        String fileName = UUID.randomUUID().toString();
        //文件扩展名
        String fileExtention = orginalFileName.substring(orginalFileName.lastIndexOf("."));
        //最终的路径 类似avatar/2021/12/05/xxxxxxxxx.jpg
        String objectName = module+"/"+folder+"/"+fileName+fileExtention;
        ossClient.putObject(bucketName, objectName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        //https://javaclimb-file.oss-cn-beijing.aliyuncs.com/avatar/01.jpeg
        return "https://"+bucketName+"."+endpoint+"/"+objectName;
    }
}
