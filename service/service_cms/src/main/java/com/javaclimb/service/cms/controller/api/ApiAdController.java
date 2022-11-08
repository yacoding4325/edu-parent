package com.javaclimb.service.cms.controller.api;

import com.javaclimb.service.base.result.R;
import com.javaclimb.service.cms.entity.Ad;
import com.javaclimb.service.cms.service.AdService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 广告位前端服务类
 */
@Api(description = "广告推荐")
@RestController
@RequestMapping("/api/cms/ad")
@Slf4j
//@CrossOrigin
public class ApiAdController {

    @Resource
    private AdService adService;

    @ApiOperation("根据推荐位id显示广告推荐")
    @GetMapping("list/{adTypeId}")
    public R listByAdTypeId(@ApiParam(value = "推荐位id",required = true)@PathVariable String adTypeId){
        List<Ad> adList = adService.selectByAdTypeId(adTypeId);
        return R.ok().data("items",adList);
    }
}

