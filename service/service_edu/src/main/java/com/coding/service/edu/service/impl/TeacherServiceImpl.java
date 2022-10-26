package com.coding.service.edu.service.impl;

/**
 * @Author yaCoding
 * @create 2022-10-26 下午 3:55
 */

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coding.service.edu.entity.Teacher;
import com.coding.service.edu.mapper.TeacherMapper;
import com.coding.service.edu.service.TeacherService;
import org.springframework.stereotype.Service;

/**
 * * <p>
 *  * 讲师 服务实现类
 *  * </p>
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService{
}
