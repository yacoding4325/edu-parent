package com.javaclimb.service.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.javaclimb.service.acl.entity.Permission;
import com.javaclimb.service.acl.entity.RolePermission;
import com.javaclimb.service.acl.entity.User;
import com.javaclimb.service.acl.mapper.PermissionMapper;
import com.javaclimb.service.acl.service.PermissionService;
import com.javaclimb.service.acl.service.RolePermissionService;
import com.javaclimb.service.acl.service.UserService;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private UserService userService;
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
     * 根据角色获取菜单
     */
    @Override
    public List<Permission> selectAllMenu(String roleId) {
        //获取到所有菜单
        List<Permission> allPermissionList = baseMapper.selectList(new QueryWrapper<>());
        //根据角色id获取到角色权限列表
        List<RolePermission> rolePermissionList = rolePermissionService.list(new QueryWrapper<RolePermission>().eq("role_id",roleId));
        //遍历所有菜单，获取到每一项，看它是否在角色权限列表里，如果在的话，给它设置标记
        for(int i = 0;i<allPermissionList.size();i++){
            Permission permission = allPermissionList.get(i);
            for(int j = 0;j<rolePermissionList.size();j++){
                RolePermission rolePermission = rolePermissionList.get(j);
                if(rolePermission.getPermissionId().equals(permission.getId())){
                    permission.setSelect(true);
                }
            }
        }
        List<Permission> result = buildPermission(allPermissionList);
        return result;
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

    //递归删除菜单
    @Override
    public void removeChildById(String id) {
        List<String> idList = new ArrayList<>();
        selectChildListById(id,idList);
        idList.add(id);
        baseMapper.deleteBatchIds(idList);
    }

    /**
     * 根据当前菜单id查询它的子子孙孙菜单id，封装到list集合
     */
    private void selectChildListById(String id, List<String> idList) {
        //查询当下菜单的下级
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",id);
        queryWrapper.select("id");
        List<Permission> childList = baseMapper.selectList(queryWrapper);
        //把childList里面的菜单id取出来，封装到idList
        childList.forEach(item -> {
            idList.add(item.getId());
            selectChildListById(item.getId(),idList);
        });
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

    /**
     * 给角色分配菜单权限
     * @param roleId
     * @param permissionIds
     */
    @Override
    public void saveRolePermissionRelationShip(String roleId, String[] permissionIds) {
        //删除久的权限
        rolePermissionService.remove(new QueryWrapper<RolePermission>().eq("role_id",roleId));
        //组装要增加的角色权限列表
        List<RolePermission> rolePermissionList = new ArrayList<>();
        for(String permissionId:permissionIds){
            if(StringUtils.isEmpty(permissionId)){
                continue;
            }
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionList.add(rolePermission);
        }
        //把角色 权限 列表批量保存到数据库
        rolePermissionService.saveBatch(rolePermissionList);
    }

    /**根据用户ID查询有权限的菜单*/
    @Override
    public List<String> selectPermissionValueByUserId(String userId) {
        List<String> list ;
        //管理员有所有菜单权限
        if (isSysAdmin(userId)) {
            list = baseMapper.selectAllPermissionValue();
        } else {//根据用户id查询有权限的菜单
            list = baseMapper.selectPermissionValueByUserId(userId);
        }
        return list;
    }

    /**
     * 判断用户是否是系统管理员
     */
    public boolean isSysAdmin(String userId) {
        User user = userService.getById(userId);
        if (user!=null && "admin".equals(user.getUsername())){
            return true;
        }
        return false;
    }
}
