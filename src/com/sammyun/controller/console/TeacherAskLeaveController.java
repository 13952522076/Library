package com.sammyun.controller.console;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sammyun.Filter;
import com.sammyun.Filter.Mold;
import com.sammyun.Filter.Operator;
import com.sammyun.Pageable;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.TeacherAskLeave;
import com.sammyun.entity.attendance.TeacherAskLeave.AskLeaveType;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.MemberService;
import com.sammyun.service.attendance.TeacherAskLeaveService;

/**
 * Controller - 教师请假
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("teacherAskLeaveController")
@RequestMapping("/console/teacherAskLeave")
public class TeacherAskLeaveController extends BaseController
{
    @Resource(name = "teacherAskLeaveServiceImpl")
    private TeacherAskLeaveService teacherAskLeaveService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    /**
     * 教师请假列表
     * 
     * @param pageable
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model, String username, AskLeaveType askLeaveType,
            String leaveStartDate, String leaveEndDate)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        if (username != null)
        {
            model.addAttribute("username", username);
            List<Member> teachers = memberService.findTeacherByNameAndSchoolInLike(username, dictSchool);
            if (teachers != null && teachers.size() > 0)
            {
                Filter filter = new Filter("leaveMember", Operator.in, teachers);
                pageable.addFilters(filter);
            }
            else
            {
                Filter filter = new Filter("id", Operator.eq, 0L);
                pageable.addFilters(filter);
            }
        }

        if (askLeaveType != null)
        {
            model.addAttribute("askLeaveType", askLeaveType);
            Filter filter = new Filter("askLeaveType", Operator.eq, askLeaveType);
            pageable.addFilters(filter);
        }
        //时间的筛选
        if (leaveStartDate != null && !leaveStartDate.equals(""))
        {
            model.addAttribute("leaveStartDate", leaveStartDate);
            Filter startTimeFilter = new Filter();
            startTimeFilter.setOperator(Operator.gt);
            startTimeFilter.setMold(Mold.dl);
            startTimeFilter.setProperty("leaveStartDate");
            startTimeFilter.setValue(leaveStartDate);
            pageable.addFilters(startTimeFilter);
        }
        if (leaveEndDate != null && !leaveEndDate.equals(""))
        {
            model.addAttribute("leaveEndDate", leaveEndDate);
            Filter endTimeFilter = new Filter();
            endTimeFilter.setOperator(Operator.lt);
            endTimeFilter.setMold(Mold.dg);
            endTimeFilter.setProperty("leaveEndDate");
            endTimeFilter.setValue(leaveEndDate+" 23:59:59");
            pageable.addFilters(endTimeFilter);
        }

        List<Member> teachers = memberService.findActiveTeachersBySchool(dictSchool);
        if (teachers != null && teachers.size() > 0)
        {
            Filter filter = new Filter("leaveMember", Operator.in, teachers);
            pageable.addFilters(filter);
        }
        else
        {
            Filter filter = new Filter("id", Operator.eq, 0L);
            pageable.addFilters(filter);
        }
        model.addAttribute("page", teacherAskLeaveService.findPage(pageable));
        model.addAttribute("menuId", TeacherAskLeave.class.getSimpleName());
        return "/console/teacherAskLeave/list";
    }

}
