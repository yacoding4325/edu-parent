package com.javaclimb.service.cms.feign;

import com.javaclimb.service.base.result.R;
import com.javaclimb.service.cms.feign.fallback.OssRemoveFileServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 删除云端图片
 */
@FeignClient(value = "service-oss",fallback = OssRemoveFileServiceFallBack.class)
public interface OssRemoveFileService {

    /**
     * 删除图片
     */
    @DeleteMapping("/admin/oss/file/remove")
    public R removeFile(@RequestBody String url);

}
