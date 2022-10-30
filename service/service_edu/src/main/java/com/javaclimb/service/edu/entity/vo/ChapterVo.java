package com.javaclimb.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 章节VO
 */
@Data
public class ChapterVo implements Serializable {
    private String id;
    private String title;
    private Integer sort;
    private List<VideoVo> children = new ArrayList<>();
}
