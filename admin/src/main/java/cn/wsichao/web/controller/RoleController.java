package cn.wsichao.web.controller;

import cn.wsichao.pojo.Permission;
import cn.wsichao.pojo.Role;
import cn.wsichao.service.PermissionService;
import cn.wsichao.service.RoleService;
import cn.wsichao.util.AjaxResult;
import cn.wsichao.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/list.do")
    public ModelAndView list(){
        List<Role> roleList = roleService.selectList();
        return new ModelAndView("role/list", "roleList", roleList);
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.GET)
    public ModelAndView addPage(){
        List<Permission> permissionList = permissionService.selectList();
        return new ModelAndView("role/add", "permissionList", permissionList);
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public @ResponseBody AjaxResult addSubmit(String name, String description, Long[] permissionIds){
        Role role = new Role();
        role.setName(name);
        if(roleService.isExisted(role)){
            return AjaxResult.errorInstance("此角色名称已存在");
        }

        role.setDescription(description);
        roleService.insert(role, permissionIds);
        return AjaxResult.successInstance("添加成功");
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.GET)
    public ModelAndView updatePage(Long id){
        Role role = roleService.selectOne(id);
        return new ModelAndView("role/update", "role", role);
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public @ResponseBody AjaxResult updateSubmit(Long id, String name, String description){
        if(CommonUtils.isEmpty(name)){
            return AjaxResult.errorInstance("名称不能为空");
        } else if(CommonUtils.isEmpty(description)){
            return AjaxResult.errorInstance("描述不能为空");
        }

        Role role = new Role();
        role.setName(name);
        role = roleService.selectOne(role);

        if(role != null && role.getId() != id){
            return AjaxResult.errorInstance("此角色已存在");
        }

        role = roleService.selectOne(id);
        role.setDescription(description);
        role.setName(name);

        roleService.update(role);
        return AjaxResult.successInstance("修改成功");
    }

    @RequestMapping("/delete.do")
    public @ResponseBody AjaxResult delete(Long id){
        roleService.delete(id);
        return AjaxResult.successInstance("删除成功");
    }

}
