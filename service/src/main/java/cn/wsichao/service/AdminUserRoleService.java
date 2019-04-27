package cn.wsichao.service;

import cn.wsichao.mapper.AdminUserRoleMapper;
import cn.wsichao.pojo.AdminUser;
import cn.wsichao.pojo.AdminUserRole;
import cn.wsichao.pojo.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminUserRoleService extends ManyToManyBaseService<AdminUserRole, AdminUser, Role> {

    @Autowired
    private AdminUserRoleMapper mapper;

    public boolean checkPermission(Map<String, Object> params) {
        int count = mapper.checkPermission(params);
        return count>0;
    }
}
