package cn.wsichao.web.controller;

import cn.wsichao.pojo.Subject;
import cn.wsichao.service.SubjectService;
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
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping("/list.do")
    public ModelAndView list(){
        List<Subject> subjectList = subjectService.selectList();
        ModelAndView modelAndView = new ModelAndView("subject/list");
        modelAndView.addObject("subjectList", subjectList);
        return modelAndView;
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.GET)
    public ModelAndView addPage(){
        return new ModelAndView("subject/add");
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    public @ResponseBody AjaxResult addSubmit(String name){
        //要检查请求参数
        if(CommonUtils.isEmpty(name)){
            return AjaxResult.errorInstance("学科名称不能为空");
        }
        //还要进行唯一性检查
        Subject subject = new Subject();
        subject.setName(name);
        if(subjectService.isExisted(subject)){
            return AjaxResult.errorInstance("学科名称已存在");
        }

        subjectService.insert(subject);
        return AjaxResult.successInstance("添加成功");
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.GET)
    public ModelAndView updatePage(Long id){
        Subject subject = subjectService.selectOne(id);
        return new ModelAndView("subject/update", "subject", subject);
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    public @ResponseBody AjaxResult updateSubmit(Long id, String name){
        if(CommonUtils.isEmpty(name)){
            return AjaxResult.errorInstance("学科名称不能为空");
        }
        Subject subject = new Subject();
        subject.setName(name);
        subject = subjectService.selectOne(subject);

        if(subject!=null && subject.getId()!=id){
            return AjaxResult.errorInstance("学科名称已经存在");
        }

        subject = subjectService.selectOne(id);
        subject.setName(name);

        subjectService.update(subject);
        return AjaxResult.successInstance("修改成功");
    }

    @RequestMapping("/delete.do")
    public @ResponseBody AjaxResult delete(Long id){
        subjectService.delete(id);
        return AjaxResult.successInstance("删除成功");
    }

}
