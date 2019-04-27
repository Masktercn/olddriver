package cn.wsichao.web.interceptor;

import cn.wsichao.pojo.AdminUser;
import cn.wsichao.service.AdminUserService;
import cn.wsichao.util.JedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class KeepOnlineInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AdminUserService adminUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //先判断当前请求的session中是否有adminUser对象
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute("adminUser");

        //如果没有，从cookie中取出原来的sessionId
        if(adminUser == null){
            Cookie sessionIdCookie = WebUtils.getCookie(request, "JSESSIONID");
            if(sessionIdCookie == null){
                return true;
            }

            String oldSessionId = sessionIdCookie.getValue();

            //从redis服务器中拿到对应的userId
            String userId = JedisUtils.get("keepOnline_"+oldSessionId);
            if(userId == null){
                return true;
            }
            //从数据库中根据userId把adminUser对象查询出来，设置到session中
            adminUser = adminUserService.selectOne(Long.parseLong(userId));

            if(adminUser != null){
                request.getSession().setAttribute("adminUser", adminUser);
            }
        }


        //把当前sessionId和userId存放到redis服务器
        if(adminUser != null){
            JedisUtils.setex("keepOnline_"+request.getSession().getId(),60*60*24, adminUser.getId()+"");
        }

        return true;
    }
}
