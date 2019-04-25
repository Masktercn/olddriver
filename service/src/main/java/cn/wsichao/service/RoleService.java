package cn.wsichao.service;


import cn.wsichao.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService<Role> {

    @Autowired
    private RolePermissionService rolePermissionService;

    public void insert(Role role, Long[] permissionIds){
        insert(role);
        Role params = new Role();
        params.setName(role.getName());
        role = selectOne(params);

        rolePermissionService.updateFirst(role.getId(), permissionIds);
    }
}
