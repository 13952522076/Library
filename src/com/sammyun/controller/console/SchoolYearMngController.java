/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Message;
import com.sammyun.Message.Type;
import com.sammyun.Pageable;
import com.sammyun.entity.Admin;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.course.WeeklyPlanSection;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.course.SchoolYearMngService;
import com.sammyun.service.course.WeeklyPlanSectionService;
import com.sammyun.service.dict.DictSchoolService;

/**
 * Controller - 学年管理
 * 
 * @author xutianlong

 */
@Controller("schoolYearMngController")
@RequestMapping("/console/school_year_mng")
public class SchoolYearMngController extends BaseController
{
    @Resource(name = "schoolYearMngServiceImpl")
    private SchoolYearMngService schoolYearMngService;

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;
    
    @Resource(name = "weeklyPlanSectionServiceImpl")
    private WeeklyPlanSectionService weeklyPlanSectionService;

    /**
     * 班级列表
     * 
     * @param pageable
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        // 校园管理员只能看到自己学校的，超级管理员可以看到所有的
        model.addAttribute("page", schoolYearMngService.findPage(pageable));
        model.addAttribute("menuId", SchoolYearMng.class.getSimpleName());
        return "/console/school_year_mng/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        Admin admin = adminService.getCurrent();
        List<DictSchool> dictSchools = dictSchoolService.findAll();
        model.addAttribute("dictSchools", dictSchools);
        model.addAttribute("admin", admin);
        model.addAttribute("menuId", SchoolYearMng.class.getSimpleName());
        return "/console/school_year_mng/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(SchoolYearMng schoolYearMng, Long dictSchoolId, RedirectAttributes redirectAttributes,
            ModelMap model)
    {
        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        schoolYearMng.setDictSchool(dictSchool);
        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }
        // 判断是否为当前年，如果是则把其他的都置为否
        if (schoolYearMng.getIsCurrent())
        {
            List<SchoolYearMng> schoolYearMngs = schoolYearMngService.findBySchool(dictSchool);
            if (schoolYearMngs != null && schoolYearMngs.size() > 0)
            {
                for (SchoolYearMng pre : schoolYearMngs)
                {
                    if (pre.getIsCurrent())
                    {
                        pre.setIsCurrent(false);
                        schoolYearMngService.update(pre);
                    }
                }
            }
        }
        schoolYearMngService.save(schoolYearMng);
        model.addAttribute("menuId", SchoolYearMng.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        Admin admin = adminService.getCurrent();
        List<DictSchool> dictSchools = dictSchoolService.findAll();
        model.addAttribute("admin", admin);
        model.addAttribute("dictSchools", dictSchools);
        model.addAttribute("schoolYearMng", schoolYearMngService.find(id));
        model.addAttribute("menuId", SchoolYearMng.class.getSimpleName());
        return "/console/school_year_mng/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(SchoolYearMng schoolYearMng, Long dictSchoolId, RedirectAttributes redirectAttributes,
            ModelMap model)
    {
        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        schoolYearMng.setDictSchool(dictSchool);
        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }

        // 判断是否为当前年，如果是则把其他的都置为否
        if (schoolYearMng.getIsCurrent())
        {
            List<SchoolYearMng> schoolYearMngs = schoolYearMngService.findBySchool(dictSchool);
            if (schoolYearMngs != null && schoolYearMngs.size() > 0)
            {
                for (SchoolYearMng pre : schoolYearMngs)
                {
                    if (pre.getIsCurrent()==null||pre.getIsCurrent())
                    {
                        pre.setIsCurrent(false);
                        schoolYearMngService.update(pre);
                    }
                }
            }
            schoolYearMng.setIsCurrent(true);
        }

        schoolYearMngService.update(schoolYearMng);
        model.addAttribute("menuId", SchoolYearMng.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        if(ids==null){
            return ERROR_MESSAGE;
        }
        for(Long id:ids){
            SchoolYearMng schoolYearMng = schoolYearMngService.find(id);
            if(schoolYearMng!=null){
                List<WeeklyPlanSection> weeks = weeklyPlanSectionService.findBySchoolYearMng(schoolYearMng);
                if(weeks!=null&&weeks.size()>0){
                    Message message = new Message(Type.error, schoolYearMng.getStartYear()+"-"+schoolYearMng.getEndYear()+"第"+schoolYearMng.getTerm()+"学期存在周计划。");
                    return message;
                }
            }
        }
        schoolYearMngService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
