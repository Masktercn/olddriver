package cn.wsichao.mapper;


import cn.wsichao.pojo.AdminUser;
import cn.wsichao.pojo.AdminUserRole;
import cn.wsichao.pojo.Role;

public interface AdminUserRoleMapper extends IManyToManyMapper<AdminUserRole, AdminUser, Role> {

}
