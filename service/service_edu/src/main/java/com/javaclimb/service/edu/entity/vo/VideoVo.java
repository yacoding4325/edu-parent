package com.javaclimb.service.edu.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 视频VO
 */
@Data
public class VideoVo implements Serializable {
    private String id;
    private String title;
    private Boolean free;
    private Integer sort;
    private String videoSourceId;

    @ApiModelProperty(value = "原始文件名称")
    private String videoOriginalName;

}
