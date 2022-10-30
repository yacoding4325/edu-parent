package com.javaclimb.service.edu.controller.admin;


import com.javaclimb.service.base.result.R;
import com.javaclimb.service.edu.entity.Chapter;
import com.javaclimb.service.edu.entity.vo.ChapterVo;
import com.javaclimb.service.edu.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 课程章节 前端控制器
 * </p>
 */
@Api(description = "章节管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/edu/chapter")
public class ChapterController {
    @Resource
    private ChapterService chapterService;

    @GetMapping("nested-list/{courseId}")
    public R nestedListByCourseId(@ApiParam(value = "课程id",required = true)@PathVariable String courseId){
        List<ChapterVo> chapterVoList = chapterService.nestedList(courseId);
        return R.ok().data("items",chapterVoList);
    }

    @ApiOperation("新增章节")
    @PostMapping("save")
    public R save(@ApiParam(value = "章节对象",required = true)@RequestBody Chapter chapter) {
        boolean result = chapterService.save(chapter);
        if(result){
            return R.ok().message("保存成功");
        }else{
            return R.error().message("保存失败");
        }
    }

    @ApiOperation("根据id查询章节")
    @GetMapping("get/{id}")
    public R getById(@ApiParam(value = "章节id",required = true)@PathVariable String id){
        Chapter chapter = chapterService.getById(id);
        if(chapter != null) {
            return R.ok().data("item",chapter);
        }else{
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("修改章节")
    @PostMapping("update")
    public R update(@ApiParam(value = "章节对象",required = true)@RequestBody Chapter chapter) {
        boolean result = chapterService.updateById(chapter);
        if(result){
            return R.ok().message("修改成功");
        }else{
            return R.error().message("修改失败");
        }
    }

    @ApiOperation("根据id删除章节")
    @GetMapping("remove/{id}")
    public R removeById(@ApiParam(value = "章节id",required = true)@PathVariable String id){
        boolean result = chapterService.removeChapterById(id);
        if(result){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("删除失败");
        }
    }
}
