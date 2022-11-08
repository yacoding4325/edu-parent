package com.javaclimb.service.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaclimb.service.acl.entity.Permission;
import com.javaclimb.service.acl.mapper.PermissionMapper;
import com.javaclimb.service.acl.service.PermissionService;
import com.javaclimb.service.acl.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yaCoding
 * @create 2022-11-08 下午 4:59
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private RolePermissionService rolePermissionService;
    /**
     * 查询菜单实现类
     * @return
     */
    @Override
    public List<Permission> queryAllMenu() {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        List<Permission> list = baseMapper.selectList(queryWrapper);
        //把查询的所有的菜单用递归方式生成结构化菜单
        List<Permission> resultList = buildPermission(list);
        return resultList;
    }

    /**
     * 把查询的所有的菜单用递归方式生成结构化菜单
     */
    private List<Permission> buildPermission(List<Permission> list) {
        //创建list集合，用于数据最终 封装
        List<Permission> finalNode = new ArrayList<>();
        //遍历所有菜单的list，得到顶层菜单--pid=0--设置level=1
        for (Permission permissionNode: list) {
            //获取 顶层菜单，pid = 0
            if ("0".equals(permissionNode.getPid())) {
                //设置level = 1
                permissionNode.setLevel(1);
                //根据顶层菜单，向里面进行查询子菜单，封装到finalNode
                finalNode.add(selectChildren(permissionNode,list));
            }
        }
        return finalNode;
    }

    /**
     * 递归查询下级菜单
     * @param permissionNode
     * @param list
     * @return
     */
    private Permission selectChildren(Permission permissionNode, List<Permission> list) {
        // 因为需要往下级菜单里放新的菜单，所以需要初始化
        permissionNode.setChildren(new ArrayList<Permission>());
        //遍历所有的菜单list，进行判断比较，比较当前id和遍历出来的菜单pid是否相同
        for(Permission it : list){
            //比较当前id和遍历出来的菜单pid是否相同
            if(permissionNode.getId().equals(it.getPid())){
                it.setLevel(permissionNode.getLevel() + 1);
                //把查询出来的子菜单放到父菜单里面
                permissionNode.getChildren().add(selectChildren(it, list) );
            }
        }
        return permissionNode;
    }
}
