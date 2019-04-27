package cn.wsichao.mapper;


import cn.wsichao.pojo.AdminUser;
import cn.wsichao.pojo.AdminUserRole;
import cn.wsichao.pojo.Role;

import java.util.Map;

public interface AdminUserRoleMapper extends IManyToManyMapper<AdminUserRole, AdminUser, Role> {

    int checkPermission(Map<String, Object> params);
}

