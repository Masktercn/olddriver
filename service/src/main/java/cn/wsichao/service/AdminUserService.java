package cn.wsichao.service;

import cn.wsichao.pojo.AdminUser;
import cn.wsichao.pojo.AdminUserRole;
import cn.wsichao.util.CommonUtils;
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

    public AdminUser login(String account, String password) {
        AdminUser adminUser = new AdminUser();
        adminUser.setAccount(account);

        adminUser = selectOne(adminUser);
        if(adminUser!=null){
            if(adminUser.getPassword().equalsIgnoreCase(CommonUtils.calculateMD5(password+ adminUser.getPasswordSalt()))){
                return adminUser;
            }
        }
        return null;
    }
}