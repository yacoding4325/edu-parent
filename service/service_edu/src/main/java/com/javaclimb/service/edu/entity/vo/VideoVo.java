package com.javaclimb.service.edu.entity.vo;

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
}
