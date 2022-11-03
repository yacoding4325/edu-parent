package com.javaclimb.service.cms.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.javaclimb.service.cms.entity.Ad;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.javaclimb.service.cms.entity.vo.AdVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 广告推荐 Mapper 接口
 * </p>
 *

 */
public interface AdMapper extends BaseMapper<Ad> {

    /**
     * 分页查询广告推荐列表
     * mp会自动组装queryMapper
     * @ Param(Constants.WRAPPER)和xml文件中的${ew.customSqlSegment}对应
     *
     * @param pageParam         分页对象
     * @param queryWrapper      查询条件对象
     * @return
     */
    List<AdVo> selectPageByQueryWrapper(Page<AdVo> pageParam, @Param(Constants.WRAPPER) QueryWrapper<AdVo> queryWrapper);
}
