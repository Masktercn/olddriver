package cn.wsichao.service;


import cn.wsichao.pojo.Permission;
import cn.wsichao.pojo.Role;
import cn.wsichao.pojo.RolePermission;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionService extends ManyToManyBaseService<RolePermission, Role, Permission> {

}
