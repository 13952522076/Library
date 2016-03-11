/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sammyun.Filter;
import com.sammyun.Filter.Mold;
import com.sammyun.Filter.Operator;
import com.sammyun.Pageable;
import com.sammyun.entity.Admin;
import com.sammyun.entity.attendance.Attendance;
import com.sammyun.entity.attendance.Attendance.Status;
import com.sammyun.entity.attendance.AttendanceDetail;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.service.AdminService;
import com.sammyun.service.attendance.AttendanceDetailService;
import com.sammyun.service.attendance.AttendanceService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.service.dict.DictStudentService;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 学生考勤管理
 * 


 */
@Controller("attendanceController")
@RequestMapping("/console/attendance")
public class AttendanceController extends BaseController
{

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

    @Resource(name = "attendanceServiceImpl")
    private AttendanceService attendanceService;

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    @Resource(name = "dictStudentServiceImpl")
    private DictStudentService dictStudentService;

    @Resource(name = "attendanceDetailServiceImpl")
    private AttendanceDetailService attendanceDetailService;

    /** 管理员service */
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    /** 学校service */
    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    /**
     * 考勤列表
     * 
     * @param model
     * @param pageable 分页信息
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model, Pageable pageable, Status status, String dictClass, String startDate,
            String endDate, HttpServletRequest request)
    {
        // 新建一个无结果的过滤器查询条件
        Filter noResultFilter = new Filter();
        noResultFilter.setOperator(Operator.eq);
        noResultFilter.setProperty("id");
        noResultFilter.setValue(-100);
        // 获取页面传来的条件
        List<Filter> filters = pageable.getFilters();
        if (filters.size() != 0)
        {
            // 状态
            filters.get(1).setValue(status);
            if (dictClass != null)
            {
                List<DictStudent> dictStudents = dictStudentService.getDictStudentByClassId(dictClass);
                if (dictStudents.size() == 0)
                {
                    filters.remove(0);
                    filters.add(noResultFilter);
                }
                else
                {
                    filters.get(0).setValue(dictStudents);
                }
            }
            else
            {
                filters.add(noResultFilter);
            }
            if (startDate != null)
            {
                Filter startTimeFilter = new Filter();
                startTimeFilter.setOperator(Operator.gt);
                startTimeFilter.setMold(Mold.dl);
                startTimeFilter.setProperty("attendanceDate");
                startTimeFilter.setValue(startDate);
                filters.add(startTimeFilter);

            }
            if (endDate != null)
            {
                Filter endTimeFilter = new Filter();
                endTimeFilter.setOperator(Operator.lt);
                endTimeFilter.setMold(Mold.dg);
                endTimeFilter.setProperty("attendanceDate");
                endTimeFilter.setValue(endDate+" 23:59:59");
                filters.add(endTimeFilter);
            }
            pageable.setFilters(filters);
        }
        else{
            //没有任何过滤器，就是没有班级，显示无
            filters.add(noResultFilter);
            pageable.setFilters(filters);
        }
        Admin admin = adminService.getCurrent();
        DictSchool dictSchool = adminService.getSchoolByAdmin(admin);
        List<DictClass> dictClasses = dictClassService.getClassesBySchool(dictSchool);
        // 获取的班级传到前台
        model.addAttribute("dictClasses", dictClasses);
        model.addAttribute("page", attendanceService.findPage(pageable));
        // 将四个参数返回页面
        model.addAttribute("returnStatus", status);
        model.addAttribute("returnDictClass", dictClass);
        model.addAttribute("returnStartDate", startDate);
        model.addAttribute("returnEndDate", endDate);
        model.addAttribute("menuId", Attendance.class.getSimpleName());
        // 进入到页面
        return "/console/attendance/list";
    }

    /**
     * @param model
     * @param status
     * @param dictclass
     * @param startDate
     * @param endDate
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(ModelMap model, Status status, String dictclass, String startDate, String endDate)
    {
        List<DictStudent> dictStudents = dictStudentService.getDictStudentByClassId(dictclass);
        List<Attendance> attendances = attendanceService.getAttendanceByConditions(status, dictStudents, startDate,
                endDate);
        List<DictClass> dictClasses = dictClassService.findAll();
        model.addAttribute("attendances", attendances);
        model.addAttribute("dictClasses", dictClasses);
        model.addAttribute("menuId", Attendance.class.getSimpleName());
        return "/console/attendance/list";
    }

    /**
     * 考勤详情
     * 
     * @param attendanceId 学生考勤ID
     * @param response
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/showAttendanceDetail", method = RequestMethod.GET)
    public void showAttendanceDetail(ModelMap model, Long attendanceId, HttpServletResponse response)
    {
        Attendance attendance = attendanceService.find(attendanceId);
        List<AttendanceDetail> attendanceDetailList = attendanceDetailService.getDetailsByAttendance(attendance);
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), attendanceDetailList);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

    }

}
