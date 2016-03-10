package com.sammyun.controller.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

import com.sammyun.Order;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.controller.api.bean.TeacherAttendanceDetailBean;
import com.sammyun.controller.api.block.TeacherAttendanceListBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.TeacherAttendance.Status;
import com.sammyun.service.MemberService;
import com.sammyun.service.attendance.TeacherAttendanceService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.ImUserUtil;
import com.sammyun.util.SpringUtils;

/**
 * api - 老师考勤
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("teacherAttendance")
@Path("/teacherAttendance")
public class TeacherAttendanceApi
{
    @Context
    HttpServletRequest request;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "teacherAttendanceServiceImpl")
    private TeacherAttendanceService teacherAttendanceService;

    /**
     * 查询相关教师考勤记录 <功能详细描述>
     * 
     * @param attendaceDetailBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/list")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel list(TeacherAttendanceDetailBean teacherAttendaceListBean)
    {
        ListRestFulModel teacherAttendanceRestFulModel = new ListRestFulModel();
        ImUserUtil imUserUtil = new ImUserUtil();
        Page<com.sammyun.entity.attendance.TeacherAttendance> teacherAttendances = new Page<com.sammyun.entity.attendance.TeacherAttendance>();
        String date = teacherAttendaceListBean.getDate();
        Long memberId = teacherAttendaceListBean.getMemberId();
        Date begin = null;
        Date beginDate = null;
        Date endDate = null;
        Member member = memberService.find(memberId);
        if (member == null)
        {
            teacherAttendanceRestFulModel.setResultCode(1);
            teacherAttendanceRestFulModel.setResultMessage(SpringUtils.getMessage("未知老师不存在！"));
            return teacherAttendanceRestFulModel;
        }
        Pageable pageable;
        if (teacherAttendaceListBean.getPage() == null)
        {
            pageable = new Pageable();
        }
        else
        {
            pageable = new Pageable(teacherAttendaceListBean.getPage().getPageNumber(),
                    teacherAttendaceListBean.getPage().getPageSize());

        }
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        // orders.add(Order.desc("attendanceDate"));
        pageable.setOrders(orders);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        if (date == null)
        {
            begin = new Date();
        }
        else
        {
            try
            {
                begin = format.parse(date);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        Calendar beginCalendar = DateUtils.toCalendar(begin);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, beginCalendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, beginCalendar.get(Calendar.MONTH));

        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        beginDate = calendar.getTime();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        endDate = calendar.getTime();
        beginDate = DateUtil.string2Date(DateUtil.date2String(beginDate, 10));
        endDate = DateUtil.string2Date(DateUtil.date2String(endDate, 10));
        teacherAttendances = teacherAttendanceService.findList(member, beginDate, endDate, pageable);

        if (teacherAttendances == null || teacherAttendances.getContent().size() == 0)
        {
            teacherAttendanceRestFulModel.setResultCode(1);
            teacherAttendanceRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关考勤记录！"));
            return teacherAttendanceRestFulModel;
        }

        List<TeacherAttendanceListBlock> attendaceDetails = new LinkedList<TeacherAttendanceListBlock>();

        for (com.sammyun.entity.attendance.TeacherAttendance teacherAttendance : teacherAttendances.getContent())
        {
            TeacherAttendanceListBlock attendaceDetail = new TeacherAttendanceListBlock();
            if (teacherAttendance.getWorkSwipeTime() != null)
            {
                attendaceDetail.setWorkSwipeTime(DateUtil.date2String(teacherAttendance.getWorkSwipeTime(), 1));
            }
            if (teacherAttendance.getClosingSwipeTime() != null)
            {
                attendaceDetail.setClosingSwipeTime(DateUtil.date2String(teacherAttendance.getClosingSwipeTime(), 1));
            }
            attendaceDetail.setWorkSettingName(teacherAttendance.getWorkSettingName());
            attendaceDetail.setMemberName(teacherAttendance.getMember().getRealName());
            attendaceDetail.setMemberIconPhoto(imUserUtil.getDefaultImageUrl(teacherAttendance.getMember().getIconPhoto()));
            attendaceDetail.setWorkStatus(convertStatus(teacherAttendance.getWorkStatus()));
            attendaceDetail.setClosingStatus(convertStatus(teacherAttendance.getClosingStatus()));
            attendaceDetails.add(attendaceDetail);
        }

        PageModel page = new PageModel();
        page.setPageNumber(teacherAttendances.getPageNumber());
        page.setTotalPages(teacherAttendances.getTotalPages());
        teacherAttendanceRestFulModel.setPage(page);
        teacherAttendanceRestFulModel.setRows(attendaceDetails);
        teacherAttendanceRestFulModel.setResultCode(0);
        teacherAttendanceRestFulModel.setResultMessage(SpringUtils.getMessage("成功"));
        return teacherAttendanceRestFulModel;
    }

    /**
     * 将教师考勤状态转化为字符型
     * 
     * @param status
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String convertStatus(Status status)
    {
        String statusString = "";
        if (status == Status.early)
        {
            statusString = "早退";
        }
        else if (status == Status.late)
        {
            statusString = "迟到";
        }
        else if (status == Status.leave)
        {
            statusString = "请假";
        }
        else if (status == Status.normal)
        {
            statusString = "正常";
        }
        else
        {
            statusString = "未知";
        }
        return statusString;
    }

}
