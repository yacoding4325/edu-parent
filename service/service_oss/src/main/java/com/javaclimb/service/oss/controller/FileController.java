package com.javaclimb.service.oss.controller;

/**
 * @Author yaCoding
 * @create 2022-10-28 下午 5:15
 */

import com.javaclimb.service.base.exception.JavaclimbException;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.base.result.ResultCodeEnum;
import com.javaclimb.service.base.utils.ExceptionUtils;
import com.javaclimb.service.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * 阿里云文件管理控制类
 */
@Api(description = "阿里云文件管理")
@RestController
@RequestMapping("admin/oss/file")
@Slf4j
@CrossOrigin
public class FileController {

    @Resource
    private FileService fileService;


    @ApiOperation("文件上传")
    @PostMapping("upload")
    public R upload(@ApiParam(value="文件",required = true) @RequestParam("file") MultipartFile file,
                    @ApiParam(value="模块",required = true) @RequestParam("module") String module){
        try {
            InputStream inputStream = file.getInputStream();
            String orginalFileName = file.getOriginalFilename();
            String url = fileService.upload(inputStream,module,orginalFileName);
            return R.ok().message("文件上传成功").data("url",url);
        } catch (IOException e) {
            log.error(ExceptionUtils.getMessage(e));
            throw new JavaclimbException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
    }
}
