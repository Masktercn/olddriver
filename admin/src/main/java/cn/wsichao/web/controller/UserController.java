package cn.wsichao.web.controller;

import cn.wsichao.pojo.User;
import cn.wsichao.service.UserService;
import cn.wsichao.util.CommonUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/list.do")
    public ModelAndView list(Integer curr, Date beginTime, Date endTime, String param){
        if(curr == null){
            curr = 1;
        }
        if(endTime != null){
            endTime.setTime(endTime.getTime() + 1000*60*60*24-1);
        }
        if(!CommonUtils.isEmpty(param)){
            param = "%"+param+"%";
        }
        Map<String , Object> params = new HashMap<String, Object>();
        params.put("beginTime", beginTime);
        params.put("endTime", endTime);
        params.put("param", param);

        PageInfo<User> pageInfo = userService.search(curr, 10, params);
        return new ModelAndView("user/list", "pageInfo", pageInfo);
    }
}
