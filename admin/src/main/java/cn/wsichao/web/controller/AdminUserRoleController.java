package cn.wsichao.web.controller;

import cn.wsichao.pojo.AdminUserRole;
import cn.wsichao.pojo.Role;
import cn.wsichao.service.AdminUserRoleService;
import cn.wsichao.service.RoleService;
import cn.wsichao.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/adminUserRole")
public class AdminUserRoleController {

    @Autowired
    private AdminUserRoleService adminUserRoleService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/update.do", method = RequestMethod.GET)
    public ModelAndView updatePage(Long adminUserId){
        List<Role> roleList = roleService.selectList();

        AdminUserRole adminUserRole = new AdminUserRole();
        adminUserRole.setAdminUserId(adminUserId);
        List<AdminUserRole> adminUserRoleList = adminUserRoleService.selectList(adminUserRole);

        ModelAndView modelAndView = new ModelAndView("adminUserRole/update");
        modelAndView.addObject("adminUserId", adminUserId);
        modelAndView.addObject("adminUserRoleList", adminUserRoleList);
        modelAndView.addObject("roleList", roleList);

        return modelAndView;
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public @ResponseBody AjaxResult updateSubmit(Long adminUserId, Long[] roleIds){
        adminUserRoleService.updateFirst(adminUserId, roleIds);
        return AjaxResult.successInstance("用户角色修改成功");
    }

}
