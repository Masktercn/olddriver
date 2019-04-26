package cn.wsichao.web.controller;

import cn.wsichao.pojo.AdminUser;
import cn.wsichao.util.CommonUtils;
import cn.wsichao.util.ImageCodeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class OtherController {

    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request){
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute("adminUser");

        if(adminUser == null){
            return new ModelAndView("redirect:/adminUser/login.do");
        }
        return new ModelAndView("index");
    }

    @RequestMapping("/welcome")
    public ModelAndView welcome(){
        return new ModelAndView("welcome");
    }

    @RequestMapping("/imageCode.do")
    public void imageCode(HttpServletRequest request, HttpServletResponse response){
        ImageCodeUtils.sendImageCode(request.getSession(), response);
    }

}