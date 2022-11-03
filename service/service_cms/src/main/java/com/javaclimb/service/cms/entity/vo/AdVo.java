package com.javaclimb.service.cms.entity.vo;

/**
 * @Author yaCoding
 * @create 2022-11-03 下午 8:12
 */

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 广告推荐页面展示
 */
public class AdVo implements Serializable {

    @ApiModelProperty(value = "ID")
    private String id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "广告位")
    private String type;

    @ApiModelProperty(value = "排序")
    private Integer sort;
}
