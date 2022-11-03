package com.javaclimb.service.cms.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.service.base.result.R;
import com.javaclimb.service.base.result.ResultCodeEnum;
import com.javaclimb.service.cms.entity.AdType;
import com.javaclimb.service.cms.service.AdTypeService;
import com.javaclimb.service.base.exception.JavaclimbException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 推荐位 前端控制器
 * </p>
 *
 */
@Api(description = "推荐位管理")
@RestController
@RequestMapping("/admin/cms/ad-type")
@Slf4j
@CrossOrigin
public class AdTypeController {

    @Autowired
    private AdTypeService adTypeService;

    //查询广告位表所有数据
    @ApiOperation(value = "所有广告位列表")
    @GetMapping("findAll")
    public R findAllAdType(){
        List<AdType> list = adTypeService.list();
        return R.ok().data("items",list);
    }

    //根据id获取广告位信息
    @ApiOperation(value = "根据id获取广告位信息")
    @GetMapping("get/{id}")
    public R getAdTypeById(@ApiParam(value = "广告位ID",required = true)@PathVariable String id){
        AdType adType = adTypeService.getById(id);
        if(adType!=null){
            return R.ok().data("item",adType);
        }
        return R.error().message("数据不存在");
    }

    /**
     * 分页查询
     */
    @ApiOperation(value = "广告位分页列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码",required = true,example = "1") @PathVariable Long page,
                      @ApiParam(value = "每页记录数",required = true,example = "1") @PathVariable Long limit){
        Page<AdType> pageParam = new Page<>(page,limit);
        //adTypeService.page(pageParam);
        IPage<AdType> pageModel = adTypeService.page(pageParam);
        //获取广告位列表
        List<AdType> records = pageModel.getRecords();
        //获取广告位总数
        long total = pageModel.getTotal();
        return R.ok().data("total",total).data("rows",records);
    }

    /**
     * 新增广告位
     */
    @ApiOperation(value = "新增广告位")
    @PostMapping("save")
    public R save(@ApiParam("广告位对象")@RequestBody AdType adType){
        adTypeService.save(adType);
        return R.ok().message("保存成功");
    }

    /**
     * 更新广告位
     */
    @ApiOperation(value = "更新广告位")
    @PostMapping("update")
    public R update(@ApiParam("广告位对象")@RequestBody AdType adType){
        boolean result = adTypeService.updateById(adType);
        if(result){
            return R.ok().message("更新成功");
        }else {
            return R.error().message("数据不存在或更新失败");
        }
    }

    /**
     * 根据id删除广告位   假删除
     */
    @ApiOperation(value = "根据ID删除广告位")
    @DeleteMapping("remove/{id}")
    public R removeById(@ApiParam(value = "广告位ID",required = true)@PathVariable String id){
        boolean result = adTypeService.removeById(id);
        if(result){
            return R.ok().message("删除成功");
        }else {
            return R.error().message("删除失败");
        }
    }

    /**
     * 根据ID列表批量删除广告位
     */
    @ApiOperation(value = "根据ID列表批量删除广告位")
    @DeleteMapping("batch-remove")
    public R batchRemove(@ApiParam(value = "广告位ID列表",required = true)@RequestBody List<String> idList){
        boolean res = adTypeService.removeByIds(idList);
        if(res){
            return R.ok().message("删除成功");
        }
        return R.error().message("删除失败");
    }
}


