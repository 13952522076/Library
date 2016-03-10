/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sammyun.Filter;
import com.sammyun.Filter.Operator;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.attendance.TeacherAskLeave;
import com.sammyun.entity.attendance.TeacherAttendance.Status;
import com.sammyun.entity.attendance.WorkScheduling;
import com.sammyun.entity.attendance.WorkScheduling.SchedulingWay;
import com.sammyun.service.AdminService;
import com.sammyun.service.MemberService;
import com.sammyun.service.attendance.TeacherAttendanceService;
import com.sammyun.service.attendance.WorkSchedulingService;
import com.sammyun.util.DateUtil;

/**
* Controller - 考勤统计
* 
* @author Sencloud Team
* @version 3.0
*/
@Controller("attendanceStatisticsController")
@RequestMapping("/console/attendance_statistics")
public class attendanceStatisticsController extends BaseController
{

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;
    
    @Resource(name = "memberServiceImpl")
    private MemberService memberService;
    
    @Resource(name = "workSchedulingServiceImpl")
    private WorkSchedulingService workSchedulingService;
    
    
    @Resource(name = "teacherAttendanceServiceImpl")
    private TeacherAttendanceService teacherAttendanceService;
    
    /**
     * 格式化时间 eg：yyyy-MM-dd
     */
    private static String DATE_FORMAT = "yyyy-MM-dd";
    
    /**
     * 考勤统计列表
     * 
     * @throws ParseException 
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable,Date date,String searchName,ModelMap model) throws ParseException
    {   
    	List<Map<String,String>> teacherList = new ArrayList<Map<String,String>>();
    	if(date==null)
        {
    		date = DateUtil.getDate();
        }
        else
        {
        	if (DateUtil.isEqualYM(date)) 
        	{
        		date = DateUtil.getDate();
        	}
        	else
        	{
        		date=DateUtil.getDefaultDay(date,DATE_FORMAT);
        	}
        }
        int year = DateUtil.getYear(date);
        int month = DateUtil.getMonth(date);
        Date firstDate=DateUtil.getFirstDate(date);
        
    	if (searchName != null)
        {
    		Filter realNameFilter = new Filter("realName", Operator.like, "%" + searchName + "%");
            pageable.addFilters(realNameFilter);
            model.addAttribute("searchName", searchName);
        }
    	
    	Page<Member> page = memberService.findPage(adminService.getCurrentDictSchool(),pageable,MemberType.teacher,true);

        for(Member teacher: page.getContent())
        {
        	Map<String, String> teacherMap = new HashMap<String, String>(); 
        	List<TeacherAskLeave> teacherAskLeave=new ArrayList<TeacherAskLeave>();
        	//出勤
        	Long attendance=teacherAttendanceService.findAttendanceCount(teacher,year,month);
        	//迟到
        	Long late=teacherAttendanceService.findTardinessCount(teacher,Status.late,year,month);
            //早退
        	Long early=teacherAttendanceService.findTardinessCount(teacher,Status.early,year,month);
        	//请假记录
        	teacherAskLeave=teacherAttendanceService.findAskLeave(teacher,firstDate,date);
        	//非旷工日期
        	List<Date> attendanceDate=teacherAttendanceService.findAttendanceDate(teacher,year,month);
        	//旷工日期
        	List<Date> absentDate=DateUtil.getSubtraction(date,attendanceDate);
        	
        	//获取老师的排班
        	List<WorkScheduling> workScheduling=workSchedulingService.findWorkScheduling(teacher);
        	for(WorkScheduling workSchedul:workScheduling)
        	{
        		  List<Integer> week=new ArrayList<Integer>();
        		  Set<String> keys = workSchedul.getSchedulingAttributes().keySet();
                  List<String> keyList = new ArrayList<String>(keys); 
                  Collections.sort(keyList, new Comparator<String>() {  
                      public int compare(String arg0, String arg1) { 
                          if(arg0.equals("monday"))   {arg0 = "1"+arg0;};
                          if(arg0.equals("tuesday"))  {arg0 = "2"+arg0;};
                          if(arg0.equals("wednesday")){arg0 = "3"+arg0;};
                          if(arg0.equals("thursday")) {arg0 = "4"+arg0;};
                          if(arg0.equals("friday"))   {arg0 = "5"+arg0;};
                          if(arg0.equals("saturday")) {arg0 = "6"+arg0;};
                          if(arg0.equals("sunday"))   {arg0 = "7"+arg0;};
                          if(arg1.equals("monday"))   {arg1 = "1"+arg1;};
                          if(arg1.equals("tuesday"))  {arg1 = "2"+arg1;};
                          if(arg1.equals("wednesday")){arg1 = "3"+arg1;};
                          if(arg1.equals("thursday")) {arg1 = "4"+arg1;};
                          if(arg1.equals("friday"))   {arg1 = "5"+arg1;};
                          if(arg1.equals("saturday")) {arg1 = "6"+arg1;};
                          if(arg1.equals("sunday"))   {arg1 = "7"+arg1;};
                          return arg0.compareTo(arg1); 
                      }  
                  });
                  String times = "";
                  for(String key:keyList){
                	  if(key.equals("monday")){
                		  week.add(2);
                      }
                      if(key.equals("tuesday")){
                    	  week.add(3);
                      }
                      if(key.equals("wednesday")){
                    	  week.add(4);
                      }
                      if(key.equals("thursday")){
                    	  week.add(5);
                      }
                      if(key.equals("friday")){
                    	  week.add(6);
                      }
                      if(key.equals("saturday")){
                    	  week.add(7);
                      }
                      if(key.equals("sunday")){
                    	  week.add(1);
                      }
                      times=times+key+",";
                 }
                 if(workSchedul.getSchedulingWay()==SchedulingWay.week)
                 {
                	 //旷工日期（按周排班 ）
                	 absentDate=DateUtil.getSubDate(week,absentDate);
                 }
                 else
                 {
                	 //旷工日期（按指定日期）
                	 absentDate=DateUtil.intersectDate(absentDate, DateUtil.string2DateList(times,","));
                 }
        	}
        	//请假（天）
        	Long LeaveDays=0L;
        	//旷工（天）
        	Long absentDays=(long)absentDate.size();
        	if(teacherAskLeave != null)
        	{
        		for(TeacherAskLeave askLeave : teacherAskLeave)
        		{
        			Date startDate=askLeave.getLeaveStartDate();
        			Date endDate=askLeave.getLeaveEndDate();
        			LeaveDays=LeaveDays+DateUtil.getIntersection(date,startDate,endDate);
        			//减请假日期内的旷工
        			absentDays=absentDays-DateUtil.getContainCount(startDate, endDate,absentDate);
        		}
        	}
        	teacherMap.put("realName", teacher.getRealName());
        	teacherMap.put("LATE_COUNT",late.toString());
        	teacherMap.put("EARLY_COUNT",early.toString());
        	teacherMap.put("LEAVE_COUNT",LeaveDays.toString());
        	teacherMap.put("ATTENDANCE_DAYS",attendance.toString());
        	teacherMap.put("ABSENTEEISM_COUNT",absentDays.toString());
        	teacherList.add(teacherMap);
        }
        model.addAttribute("page",page);
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("date",DateUtil.date2String(date, 2));
        model.addAttribute("menuId","AttendanceStatistics");
        return "/console/attendance_statistics/list";
    }
}
