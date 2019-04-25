package cn.wsichao.web.controller;

import cn.wsichao.pojo.Permission;
import cn.wsichao.service.PermissionService;
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
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/list.do")
    public ModelAndView list(){
        List<Permission> permissionList = permissionService.selectList();
        ModelAndView modelAndView = new ModelAndView("permission/list");
        modelAndView.addObject("permissionList", permissionList);
        return modelAndView;
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.GET)
    public ModelAndView addPage(){
        return new ModelAndView("permission/add");
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public @ResponseBody AjaxResult addSubmit(String path, String description){
        if(CommonUtils.isEmpty(path)){
            return AjaxResult.errorInstance("请求路径不能为空");
        } else if(CommonUtils.isEmpty(description)){
            return AjaxResult.errorInstance("描述不能为空");
        }

        Permission permission = new Permission();
        permission.setDescription(description);
        permission.setPath(path);
        if(permissionService.isExisted(permission)){
            return AjaxResult.errorInstance("该权限项已存在");
        }

        permissionService.insert(permission);
        return AjaxResult.successInstance("添加成功");
    }

    @RequestMapping(value = "update.do", method = RequestMethod.GET)
    public ModelAndView updatePage(Long id){
        Permission permission = permissionService.selectOne(id);
        return new ModelAndView("permission/update","permission", permission);
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public @ResponseBody AjaxResult updateSubmit(Long id, String path, String description){
        if(CommonUtils.isEmpty(path)){
            return AjaxResult.errorInstance("请求路径不能为空");
        } else if(CommonUtils.isEmpty(description)){
            return AjaxResult.errorInstance("描述不能为空");
        }

        Permission permission = new Permission();
        permission.setPath(path);
        permission = permissionService.selectOne(permission);

        if(permission != null && permission.getId() != id){
            return AjaxResult.errorInstance("此权限已存在");
        }

        permission = permissionService.selectOne(id);
        permission.setDescription(description);
        permission.setPath(path);

        permissionService.update(permission);
        return AjaxResult.successInstance("修改成功");
    }

    @RequestMapping("/delete.do")
    public @ResponseBody AjaxResult delete(Long id){
        permissionService.delete(id);
        return AjaxResult.successInstance("删除成功");
    }
}
