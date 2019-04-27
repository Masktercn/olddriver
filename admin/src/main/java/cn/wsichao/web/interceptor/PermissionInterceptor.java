package cn.wsichao.web.interceptor;

import cn.wsichao.pojo.AdminUser;
import cn.wsichao.service.AdminUserRoleService;
import cn.wsichao.util.AjaxResult;
import cn.wsichao.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class PermissionInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AdminUserRoleService adminUserRoleService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //当前adminUserId
        //请求路径
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute("adminUser");
        if(adminUser == null){
            //用户没有登录
            if(request.getHeader("X-Requested-With") != null){
                //ajax请求
                response.getWriter().print(JsonUtils.toJson(AjaxResult.errorInstance("您还没有登录，请先登录")));
            } else{
                response.getWriter().print("您还没有登录，请先登录");
            }
        }

        Long adminUserId = adminUser.getId();
        String path = request.getServletPath();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("adminUserId", adminUserId);
        params.put("path", path);

        boolean result = adminUserRoleService.checkPermission(params);
        if(result){
            return true;
        } else{
            if(request.getHeader("X-Requested-With") != null){
                //ajax请求
                response.getWriter().print(JsonUtils.toJson(AjaxResult.errorInstance("权限不足")));
            } else{
                response.getWriter().print("权限不足");
            }
            return false;
        }
    }
}
