/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Filter;
import com.sammyun.Filter.Operator;
import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.entity.Admin;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.AskLeave;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.service.AdminService;
import com.sammyun.service.MemberService;
import com.sammyun.service.attendance.AskLeaveService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictStudentService;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 请假
 * 


 */
@Controller("askLeaveController")
@RequestMapping("/console/askLeave")
public class AskLeaveController extends BaseController
{
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "askLeaveServiceImpl")
    private AskLeaveService askLeaveService;

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    @Resource(name = "dictStudentServiceImpl")
    private DictStudentService dictStudentService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    /**
     * 列表 <功能详细描述>
     * 
     * @param model
     * @param pageable
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, Pageable pageable)
    {
        Admin admin = adminService.getCurrent();
        List<DictStudent> students = new ArrayList<DictStudent>();
        students = dictStudentService.findStudentsBySchool(admin.getDictSchool());
        Filter filter = new Filter("dictStudent", Operator.in, students);
        if(students==null||students.size()==0){
        	filter = new Filter("id", Operator.eq, "0");//添加一个空的过滤器
        }
        List<Filter> filters = pageable.getFilters();
        filters.add(filter);
        pageable.setFilters(filters);
        model.addAttribute("menuId", AskLeave.class.getSimpleName());
        model.addAttribute("page", askLeaveService.findPage(pageable));
        return "/console/askLeave/list";

    }

    /**
     * 删除 <功能详细描述>
     * 
     * @param ids
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        askLeaveService.delete(ids);
        return SUCCESS_MESSAGE;
    }

    /**
     * 跳转添加页面 <功能详细描述>
     * 
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        Admin admin = adminService.getCurrent();
        DictSchool dictSchool = admin.getDictSchool();
        model.addAttribute("dictClasses", dictSchool.getDictClasses());
        model.addAttribute("members", dictSchool.getMembers());
        model.addAttribute("menuId", AskLeave.class.getSimpleName());
        return "/console/askLeave/add";
    }

    /**
     * ajax获取学生 <功能详细描述>
     * 
     * @param dictClassId
     * @param response
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getStudents", method = RequestMethod.GET)
    public void getStudents(Long dictClassId, HttpServletResponse response)
    {
        DictClass dictClass = dictClassService.find(dictClassId);
        Set<DictStudent> students = dictClass.getDictStudents();
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), students);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(AskLeave askLeave, Long dictStudentId, Long auditingUserId,
            RedirectAttributes redirectAttributes, ModelMap model)
    {
        DictStudent dictStudent = dictStudentService.find(dictStudentId);
        Member member = memberService.find(auditingUserId);
        askLeave.setDictStudent(dictStudent);
        askLeave.setStuName(dictStudent.getStudentName());
        askLeave.setStuNo(dictStudent.getStudentNo());
        askLeave.setAuditingUserId(member.getUsername());
        if (member.getRealName() == null)
        {
            askLeave.setAuditingUserName(member.getUsername());
        }
        else
        {
            askLeave.setAuditingUserName(member.getRealName());
        }
        if (!isValid(askLeave))
        {
            return ERROR_VIEW;
        }
        askLeaveService.save(askLeave);
        model.addAttribute("menuId", AskLeave.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        Admin admin = adminService.getCurrent();
        DictSchool dictSchool = admin.getDictSchool();
        model.addAttribute("dictClasses", dictSchool.getDictClasses());
        model.addAttribute("members", dictSchool.getMembers());
        model.addAttribute("askLeave", askLeaveService.find(id));

        model.addAttribute("menuId", AskLeave.class.getSimpleName());
        return "/console/askLeave/edit";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(AskLeave askLeave, Long dictStudentId, Long auditingUserId,
            RedirectAttributes redirectAttributes, ModelMap model)
    {
        DictStudent dictStudent = dictStudentService.find(dictStudentId);
        Member member = memberService.find(auditingUserId);
        askLeave.setDictStudent(dictStudent);
        askLeave.setStuName(dictStudent.getStudentName());
        askLeave.setStuNo(dictStudent.getStudentNo());
        askLeave.setAuditingUserId(member.getUsername());
        if (member.getRealName() == null)
        {
            askLeave.setAuditingUserName(member.getUsername());
        }
        else
        {
            askLeave.setAuditingUserName(member.getRealName());
        }
        if (!isValid(askLeave))
        {
            return ERROR_VIEW;
        }
        askLeaveService.update(askLeave);
        model.addAttribute("menuId", SchoolYearMng.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

}
