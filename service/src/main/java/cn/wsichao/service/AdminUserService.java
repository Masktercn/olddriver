package cn.wsichao.service;

import cn.wsichao.pojo.AdminUser;
import cn.wsichao.pojo.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService extends BaseService<AdminUser> {

    @Autowired
    private AdminUserRoleService adminUserRoleService;

    public void insert(AdminUser adminUser, Long[] roleIds) {
        insert(adminUser);
        AdminUser params = new AdminUser();
        params.setAccount(adminUser.getAccount());
        adminUser = selectOne(params);

        adminUserRoleService.updateFirst(adminUser.getId(), roleIds);
    }
}