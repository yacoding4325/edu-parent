package com.javaclimb.service.cms.feign.fallback;

import com.javaclimb.service.base.result.R;
import com.javaclimb.service.cms.feign.OssRemoveFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author yaCoding
 * @create 2022-11-03 下午 8:13
 */

@Slf4j
@Service
public class OssRemoveFileServiceFallBack implements OssRemoveFileService {
    /**
     * 删除图片
     *
     * @param url
     */
    @Override
    public R removeFile(String url) {
        log.info("熔断保护");
        return R.error().message("调用超时");
    }
}
