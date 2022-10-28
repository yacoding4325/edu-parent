package com.javaclimb.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程分类Vo
 */
@Data
public class SubjectVo implements Serializable {
    private String id;
    private String title;
    private Integer sort;
    private List<SubjectVo> children = new ArrayList<>();
}
