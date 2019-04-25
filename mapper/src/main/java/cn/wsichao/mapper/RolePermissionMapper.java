package cn.wsichao.mapper;


import cn.wsichao.pojo.Permission;
import cn.wsichao.pojo.Role;
import cn.wsichao.pojo.RolePermission;

public interface RolePermissionMapper extends IManyToManyMapper<RolePermission, Role, Permission> {

}
