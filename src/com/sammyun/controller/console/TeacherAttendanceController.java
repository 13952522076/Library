/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sammyun.Filter;
import com.sammyun.Filter.Mold;
import com.sammyun.Filter.Operator;
import com.sammyun.Pageable;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.TeacherAttendance;
import com.sammyun.entity.attendance.TeacherAttendance.Status;
import com.sammyun.entity.attendance.TeacherAttendanceDetail;
import com.sammyun.service.AdminService;
import com.sammyun.service.MemberService;
import com.sammyun.service.attendance.TeacherAttendanceDetailService;
import com.sammyun.service.attendance.TeacherAttendanceService;
import com.sammyun.service.course.SchoolYearMngService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 老师考勤管理
 * 
 * @author xutianlong

 */
@Controller("teacherAttendanceController")
@RequestMapping("/console/teacherAttendance")
public class TeacherAttendanceController extends BaseController
{
    @Resource(name = "schoolYearMngServiceImpl")
    private SchoolYearMngService schoolYearMngService;

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;
    
    @Resource(name = "memberServiceImpl")
    private MemberService memberService;
    
    @Resource(name = "teacherAttendanceServiceImpl")
    private TeacherAttendanceService teacherAttendanceService;
    
    @Resource(name = "teacherAttendanceDetailServiceImpl")
    private TeacherAttendanceDetailService teacherAttendanceDetailService;

    /**
     * 班级列表
     * 
     * @param pageable
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable,Status workStatus, ModelMap model,Long memberId,Status closingStatus,String endDate,String startDate)
    {   
        //获取所有的老师
        List<Member> teachers = memberService.findActiveTeachersBySchool(adminService.getCurrentDictSchool());
        model.addAttribute("teachers", teachers);
        
        if(startDate!=null){
            Filter startTimeFilter = new Filter();
            startTimeFilter.setOperator(Operator.gt);
            startTimeFilter.setMold(Mold.dl);
            startTimeFilter.setProperty("workSwipeTime");
            startTimeFilter.setValue(startDate);
            pageable.addFilters(startTimeFilter);
            model.addAttribute("startDate", startDate);
        }
        if(endDate!=null){
            Filter endTimeFilter = new Filter();
            endTimeFilter.setOperator(Operator.lt);
            endTimeFilter.setMold(Mold.dg);
            endTimeFilter.setProperty("workSwipeTime");
            endTimeFilter.setValue(endDate+" 23:59:59");
            pageable.addFilters(endTimeFilter);
            model.addAttribute("endDate", endDate);
        }
        
        if(closingStatus!=null){
            pageable.addFilters(new Filter("closingStatus", Operator.eq, closingStatus));
            model.addAttribute("closingStatus", closingStatus);
        }
        if(memberId!=null){
            pageable.addFilters(new Filter("member", Operator.eq, memberService.find(memberId)));
            model.addAttribute("memberId", memberId);
        }
        if(workStatus!=null){
            pageable.addFilters(new Filter("workStatus", Operator.eq, workStatus));
            model.addAttribute("workStatus", workStatus);
        }
        if(teachers==null||teachers.size()==0){
            pageable.addFilters(new Filter("id", Operator.eq, 0L));
        }else{
            pageable.addFilters(new Filter("member", Operator.in, teachers));
        }
        model.addAttribute("page", teacherAttendanceService.findPage(pageable));
        model.addAttribute("menuId", TeacherAttendance.class.getSimpleName());
        return "/console/teacherAttendance/list";
    }

    /**
     * ajax通过id获得考勤细节 
     * 
     * @param id
     * @param response
     */
    @RequestMapping(value = "/getDetail", method = RequestMethod.GET)
    public void getDetail(Long id, HttpServletResponse response)
    {
        TeacherAttendance teacherAttendance = teacherAttendanceService.find(id);
        List<TeacherAttendanceDetail> details = teacherAttendanceDetailService.findByTeacherAttendance(teacherAttendance);
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), details);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

   
}
