package cn.wsichao.web.resolver;

import cn.wsichao.util.AjaxResult;
import cn.wsichao.util.JsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LogManager.getLogger(MyHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        logger.error("服务器出错", ex);
        //考虑请求类型
        if(request.getHeader("X-Requested-With")!=null){
            //ajax请求
            AjaxResult ajaxResult = AjaxResult.errorInstance("服务器出错了");
            try {
                response.getWriter().print(JsonUtils.toJson(ajaxResult));
            } catch (IOException e) {
                //记录日志
                logger.error("发送ajax服务器出错提示信息时出错了",e);
            }
            return new ModelAndView();
        } else{
            //普通请求
            return new ModelAndView("500");
        }
    }
}
