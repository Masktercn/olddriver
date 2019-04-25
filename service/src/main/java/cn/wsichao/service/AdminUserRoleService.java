package cn.wsichao.service;

import cn.wsichao.pojo.AdminUser;
import cn.wsichao.pojo.AdminUserRole;
import cn.wsichao.pojo.Role;

import org.springframework.stereotype.Service;
@Service
public class AdminUserRoleService extends ManyToManyBaseService<AdminUserRole, AdminUser, Role> {

}
