package cn.wsichao.web.controller;

import cn.wsichao.pojo.Permission;
import cn.wsichao.pojo.RolePermission;
import cn.wsichao.service.PermissionService;
import cn.wsichao.service.RolePermissionService;
import cn.wsichao.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/rolePermission")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/update.do", method = RequestMethod.GET)
    public ModelAndView updatePage(Long roleId){
        List<Permission> permissionList = permissionService.selectList();

        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(roleId);

        List<RolePermission> rolePermissionList = rolePermissionService.selectList(rolePermission);

        ModelAndView modelAndView = new ModelAndView("rolePermission/update");
        modelAndView.addObject("roleId", roleId);
        modelAndView.addObject("permissionList", permissionList);
        modelAndView.addObject("rolePermissionList", rolePermissionList);

        return modelAndView;
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public @ResponseBody AjaxResult updateSubmit(Long roleId, Long[] permissionIds){
        rolePermissionService.updateFirst(roleId, permissionIds);
        return AjaxResult.successInstance("权限分配成功");
    }
}
