package cn.wsichao.web.controller;

import cn.wsichao.pojo.AdminUser;
import cn.wsichao.pojo.Role;
import cn.wsichao.service.AdminUserService;
import cn.wsichao.service.RoleService;
import cn.wsichao.util.AjaxResult;
import cn.wsichao.util.CommonUtils;
import com.sun.org.apache.bcel.internal.generic.IMPDEP1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/adminUser")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/list.do")
    public ModelAndView list(){
        List<AdminUser> adminUserList = adminUserService.selectList();
        return new ModelAndView("adminUser/list", "adminUserList", adminUserList);
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.GET)
    public ModelAndView addPage(){
        List<Role> roleList = roleService.selectList();
        return new ModelAndView("adminUser/add", "roleList", roleList);
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public @ResponseBody AjaxResult addSubmit(String account, String password, Long[] roleIds){

        String salt = "fuckSalt";
        if(CommonUtils.isEmpty(account)){
            return AjaxResult.errorInstance("账号不能为空");
        } else if(CommonUtils.isEmpty(password)){
            return AjaxResult.errorInstance("密码不能为空");
        }

        AdminUser adminUser = new AdminUser();
        adminUser.setAccount(account);

        if(adminUserService.isExisted(adminUser)){
            return AjaxResult.errorInstance("该账号已存在");
        }

        password = CommonUtils.calculateMD5(password + salt);
        adminUser.setPassword(password);
        adminUser.setPasswordSalt(salt);
        adminUserService.insert(adminUser, roleIds);
        return AjaxResult.successInstance("添加成功");
    }

    @RequestMapping("/delete.do")
    public @ResponseBody AjaxResult delete(Long id){
        adminUserService.delete(id);
        return AjaxResult.successInstance("删除成功");
    }

    @RequestMapping(value = "/resetPassword.do", method = RequestMethod.GET)
    public ModelAndView resetPassword(Long id){
        AdminUser adminUser = adminUserService.selectOne(id);
        return new ModelAndView("adminUser/resetPassword", "adminUser", adminUser);
    }

    @RequestMapping(value = "/resetPassword.do", method = RequestMethod.POST)
    public @ResponseBody AjaxResult resetPasswordSubmit(Long id, String password){
        if(CommonUtils.isEmpty(password)){
            return AjaxResult.errorInstance("密码不能为空");
        } else if(password.length() < 6){
            return AjaxResult.errorInstance("密码最少需要6位");
        }
        AdminUser adminUser = new AdminUser();
        adminUser.setId(id);
        adminUser = adminUserService.selectOne(adminUser);
        String salt = "fuckSalt";
        adminUser.setPassword(CommonUtils.calculateMD5(password + salt));

        adminUserService.update(adminUser);
        return AjaxResult.successInstance("密码修改成功");
    }

    @RequestMapping("/toggleDisable.do")
    public @ResponseBody AjaxResult toggleDisable(Long id){
        AdminUser adminUser = adminUserService.selectOne(id);

        if(adminUser.getIsDisabled()){
            adminUser.setIsDisabled(false);
            adminUserService.update(adminUser);
            return AjaxResult.successInstance("已启用");
        } else{
            adminUser.setIsDisabled(true);
            adminUserService.update(adminUser);
            return AjaxResult.successInstance("已禁用");
        }
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public ModelAndView loginPage(){
        return new ModelAndView("adminUser/login");
    }
}
