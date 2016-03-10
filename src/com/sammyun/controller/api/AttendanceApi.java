package com.sammyun.controller.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

import com.sammyun.Order;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.controller.api.bean.AttendaceAddBean;
import com.sammyun.controller.api.bean.AttendaceAddListBean;
import com.sammyun.controller.api.bean.AttendaceListBean;
import com.sammyun.controller.api.bean.AttendanceLeaveBean;
import com.sammyun.controller.api.block.AttendaceClassInfoBlock;
import com.sammyun.controller.api.block.AttendaceListBlock;
import com.sammyun.controller.api.block.AttendanceAddBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.attendance.AskLeave;
import com.sammyun.entity.attendance.Attendance.Status;
import com.sammyun.entity.attendance.AttendanceDetail;
import com.sammyun.entity.attendance.AttendanceDetail.Device;
import com.sammyun.entity.attendance.AttendanceDetail.TimeType;
import com.sammyun.entity.attendance.SchoolHours;
import com.sammyun.entity.attendance.TeacherAttendance;
import com.sammyun.entity.attendance.TeacherAttendanceDetail;
import com.sammyun.entity.attendance.TimeCard;
import com.sammyun.entity.attendance.TimeCard.CardStatus;
import com.sammyun.entity.attendance.WorkScheduling;
import com.sammyun.entity.attendance.WorkScheduling.SchedulingWay;
import com.sammyun.entity.attendance.WorkSetting;
import com.sammyun.entity.dict.ClassTeacherMap;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictClass.ClassStatus;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.message.Message.MessageCategory;
import com.sammyun.service.MemberService;
import com.sammyun.service.attendance.AskLeaveService;
import com.sammyun.service.attendance.AttendanceDetailService;
import com.sammyun.service.attendance.AttendanceService;
import com.sammyun.service.attendance.TeacherAttendanceDetailService;
import com.sammyun.service.attendance.TeacherAttendanceService;
import com.sammyun.service.attendance.TimeCardService;
import com.sammyun.service.attendance.WorkSchedulingService;
import com.sammyun.service.attendance.WorkSettingService;
import com.sammyun.service.dict.ClassTeacherMapService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.service.dict.DictStudentService;
import com.sammyun.service.message.MessageService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.ImUserUtil;
import com.sammyun.util.SpringUtils;

/**
 * api - 学生考勤
 * 
 * @author Sencloud Team
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("attendance")
@Path("/attendance")
public class AttendanceApi
{
    @Context
    HttpServletRequest request;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    @Resource(name = "dictStudentServiceImpl")
    private DictStudentService dictStudentService;

    @Resource(name = "classTeacherMapServiceImpl")
    private ClassTeacherMapService classTeacherMapService;

    @Resource(name = "attendanceServiceImpl")
    private AttendanceService attendanceService;

    @Resource(name = "askLeaveServiceImpl")
    private AskLeaveService askLeaveService;

    @Resource(name = "attendanceDetailServiceImpl")
    private AttendanceDetailService attendanceDetailService;

    @Resource(name = "timeCardServiceImpl")
    private TimeCardService timeCardService;

    @Resource(name = "messageServiceImpl")
    private MessageService messageService;

    @Resource(name = "teacherAttendanceServiceImpl")
    private TeacherAttendanceService teacherAttendanceService;

    @Resource(name = "workSchedulingServiceImpl")
    private WorkSchedulingService workSchedulingService;

    @Resource(name = "teacherAttendanceDetailServiceImpl")
    private TeacherAttendanceDetailService teacherAttendanceDetailService;

    @Resource(name = "workSettingServiceImpl")
    private WorkSettingService workSettingService;

    /**
     * 根据学校老师查询班级信息
     * 
     * @param dictSchoolId
     * @param memberId
     * @param classStatus
     * @return
     * @see [类、类#方法、类#成员]
     */

    @GET
    @Path("/v1/classInfo/{dictSchoolId}/{memberId}/{classStatus}")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel classInfo(@PathParam("dictSchoolId") Long dictSchoolId,
            @PathParam("memberId") Long memberId, @PathParam("classStatus") ClassStatus classStatus)
    {
        ListRestFulModel attendanceRestFulModel = new ListRestFulModel();
        List<AttendaceClassInfoBlock> classInfos = new LinkedList<AttendaceClassInfoBlock>();
        Set<DictClass> dictClassSets = new HashSet<DictClass>();
        List<DictClass> dictClasses = new ArrayList<DictClass>();

        if (dictSchoolId == null || memberId == null)
        {
            attendanceRestFulModel.setResultCode(1);
            attendanceRestFulModel.setResultMessage("参数错误！");
            return attendanceRestFulModel;
        }

        Member member = memberService.find(memberId);
        if (member == null)
        {
            attendanceRestFulModel.setResultCode(1);
            attendanceRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.unknownAccount"));
            return attendanceRestFulModel;
        }

        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        if (dictSchool == null)
        {
            attendanceRestFulModel.setResultCode(1);
            attendanceRestFulModel.setResultMessage(SpringUtils.getMessage("未知学校不存在！"));
            return attendanceRestFulModel;
        }
        if (classStatus == null)
        {
            classStatus = ClassStatus.active;
        }

        List<ClassTeacherMap> classTeacherMaps = classTeacherMapService.findClassesBySchoolAndMember(dictSchool,
                member, classStatus);
        if (classTeacherMaps != null && classTeacherMaps.size() != 0)
        {
            for (ClassTeacherMap classTeacherMap : classTeacherMaps)
            {
                dictClassSets.add(classTeacherMap.getDictClass());
            }
            dictClasses.addAll(dictClassSets);
            newSort(dictClasses);
            for (DictClass dictClass : dictClasses)
            {
                AttendaceClassInfoBlock classInfoBlock = new AttendaceClassInfoBlock();
                classInfoBlock.setId(dictClass.getId());
                classInfoBlock.setName(dictClass.getName());
                classInfoBlock.setCmaster(dictClass.getCmaster());
                classInfoBlock.setDescription(dictClass.getDescription());
                classInfos.add(classInfoBlock);
            }
        }
        attendanceRestFulModel.setRows(classInfos);
        attendanceRestFulModel.setResultCode(0);
        attendanceRestFulModel.setResultMessage(SpringUtils.getMessage("成功"));
        return attendanceRestFulModel;

    }

    /**
     * 重写sort方法，对班级排序
     * 
     * @param dictClasses
     * @see [类、类#方法、类#成员]
     */
    public void newSort(List<DictClass> dictClasses)
    {
        Collections.sort(dictClasses, new Comparator<DictClass>()
        {
            public int compare(DictClass arg0, DictClass arg1)
            {
                Integer order1 = Integer.parseInt(arg0.getCode());
                Integer order2 = Integer.parseInt(arg1.getCode());
                return order1.compareTo(order2);
            }
        });
    }

    /**
     * 查询相关考勤记录 <功能详细描述>
     * 
     * @param attendaceDetailBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/list")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel list(AttendaceListBean attendaceDetailBean)
    {
        ListRestFulModel attendanceRestFulModel = new ListRestFulModel();
        ImUserUtil imUserUtil = new ImUserUtil();
        Page<com.sammyun.entity.attendance.Attendance> attendances = new Page<com.sammyun.entity.attendance.Attendance>();

        String date = attendaceDetailBean.getDate();
        Long dictClassId = attendaceDetailBean.getDictClassId();
        Long memberId = attendaceDetailBean.getMemberId();
        String studentNo = attendaceDetailBean.getStudentNo();
        MemberType memberType = attendaceDetailBean.getMemberType();

        Date begin = null;
        Date beginDate = null;
        Date endDate = null;
        if (memberType == null || "".equalsIgnoreCase(memberType.toString()) || memberId == null)
        {
            attendanceRestFulModel.setResultCode(1);
            attendanceRestFulModel.setResultMessage(SpringUtils.getMessage("参数错误！"));
            return attendanceRestFulModel;
        }
        Member member = memberService.find(memberId);
        if (member == null)
        {
            attendanceRestFulModel.setResultCode(1);
            attendanceRestFulModel.setResultMessage(SpringUtils.getMessage("未知老师不存在！"));
            return attendanceRestFulModel;
        }
        Pageable pageable;
        if (attendaceDetailBean.getPage() == null)
        {
            pageable = new Pageable();
        }
        else
        {
            pageable = new Pageable(attendaceDetailBean.getPage().getPageNumber(),
                    attendaceDetailBean.getPage().getPageSize());

        }
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        orders.add(Order.desc("attendanceDate"));
        pageable.setOrders(orders);

        if (memberType.equals(MemberType.patriarch))
        {
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
            if (studentNo == null || "".equalsIgnoreCase(studentNo.toString()))
            {
                attendanceRestFulModel.setResultCode(1);
                attendanceRestFulModel.setResultMessage(SpringUtils.getMessage("参数错误！"));
                return attendanceRestFulModel;
            }
            List<DictStudent> dictStudents = dictStudentService.findByStudentNo(studentNo, member.getDictSchool());
            if (dictStudents == null || dictStudents.size() == 0)
            {
                attendanceRestFulModel.setResultCode(1);
                attendanceRestFulModel.setResultMessage(SpringUtils.getMessage("未知学生不存在！"));
                return attendanceRestFulModel;
            }
            DictStudent dictStudent = dictStudents.get(0);
            attendances = attendanceService.findAttendance(member, dictStudent, null, beginDate, endDate, pageable);
        }
        if (memberType.equals(MemberType.teacher))
        {
            if (date == null)
            {
                date = DateUtil.date2String(new Date(), 10);
            }

            beginDate = DateUtil.string2Date(date);
            endDate = DateUtil.string2Date(date);

            if (dictClassId == null || "".equalsIgnoreCase(dictClassId.toString()))
            {
                attendanceRestFulModel.setResultCode(1);
                attendanceRestFulModel.setResultMessage(SpringUtils.getMessage("参数错误！"));
                return attendanceRestFulModel;
            }
            DictClass dictClass = dictClassService.find(dictClassId);
            if (dictClass == null)
            {
                attendanceRestFulModel.setResultCode(1);
                attendanceRestFulModel.setResultMessage(SpringUtils.getMessage("未知班级不存在！"));
                return attendanceRestFulModel;
            }
            attendances = attendanceService.findAttendance(member, null, dictClass, beginDate, endDate, pageable);
        }
        List<AttendaceListBlock> attendaceDetails = new LinkedList<AttendaceListBlock>();
        if (attendances == null || attendances.getContent().size() == 0)
        {
            attendanceRestFulModel.setResultCode(1);
            attendanceRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关考勤记录！"));
            return attendanceRestFulModel;
        }
        for (com.sammyun.entity.attendance.Attendance attendance : attendances.getContent())
        {
            AttendaceListBlock attendaceDetailBlock = new AttendaceListBlock();
            if (attendance.getEnterDate() != null)
            {
                attendaceDetailBlock.setEnterDate(DateUtil.date2String(attendance.getEnterDate(), 1));
            }
            if (attendance.getLeaveDate() != null)
            {
                attendaceDetailBlock.setLeaveDate(DateUtil.date2String(attendance.getLeaveDate(), 1));
            }
            if (attendance.getAttendanceDate() != null)
            {
                attendaceDetailBlock.setAttendanceDate(DateUtil.date2String(attendance.getAttendanceDate(), 10));
            }
            attendaceDetailBlock.setStatus(attendance.getStatus());
            attendaceDetailBlock.setStatusName(SpringUtils.getMessage("Attendance.status." + attendance.getStatus()));
            attendaceDetailBlock.setStudentName(attendance.getDictStudent().getStudentName());
            attendaceDetailBlock.setIconPhoto(imUserUtil.getDefaultImageUrl(attendance.getDictStudent().getIconPhoto()));
            attendaceDetails.add(attendaceDetailBlock);
        }
        PageModel page = new PageModel();
        page.setPageNumber(attendances.getPageNumber());
        page.setTotalPages(attendances.getTotalPages());
        attendanceRestFulModel.setPage(page);
        attendanceRestFulModel.setRows(attendaceDetails);
        attendanceRestFulModel.setResultCode(0);
        attendanceRestFulModel.setResultMessage(SpringUtils.getMessage("成功"));
        return attendanceRestFulModel;

    }

    /**
     * 家长请假 <功能详细描述>
     * 
     * @param leaveDetailBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/leave")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileRestFulModel leave(AttendanceLeaveBean leaveDetailBean)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        String leaveStartDate = leaveDetailBean.getLeaveStartDate();
        if (leaveStartDate == null || "".equalsIgnoreCase(leaveStartDate))
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("起始时间不可为空！");
            return mobileRestFulModel;
        }
        String leaveEndDate = leaveDetailBean.getLeaveEndDate();
        if (leaveEndDate == null || "".equalsIgnoreCase(leaveEndDate))
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("结束时间不可为空！");
            return mobileRestFulModel;
        }

        String stuNo = leaveDetailBean.getStuNo();
        if (stuNo == null || "".equalsIgnoreCase(stuNo))
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("学号不可为空！");
            return mobileRestFulModel;
        }
        String description = leaveDetailBean.getDescription();
        if (description == null || "".equalsIgnoreCase(description))
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("请假说明不可为空！");
            return mobileRestFulModel;
        }
        if (leaveDetailBean.getMemberId() == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }

        Member member = memberService.find(leaveDetailBean.getMemberId());
        if (member == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.unknownAccount"));
            return mobileRestFulModel;
        }
        List<DictStudent> dictStudents = dictStudentService.findByStudentNo(stuNo, member.getDictSchool());
        if (dictStudents == null || dictStudents.size() == 0)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("未知学生不存在！"));
            return mobileRestFulModel;
        }
        DictStudent dictStudent = dictStudents.get(0);

        Date beginDate = DateUtil.string2Date(leaveStartDate);
        Date endDate = DateUtil.string2Date(leaveEndDate);
        Calendar beginCalendar = DateUtils.toCalendar(beginDate);
        Calendar endCalendar = DateUtils.toCalendar(endDate);
        int beginYear = beginCalendar.get(Calendar.YEAR);
        int endYear = endCalendar.get(Calendar.YEAR);
        int beginMonth = beginCalendar.get(Calendar.MONTH);
        int endMonth = endCalendar.get(Calendar.MONTH);
        int beginDay = beginCalendar.get(Calendar.DATE);
        int endDay = endCalendar.get(Calendar.DATE);
        for (int year = beginYear; year <= endYear; year++)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            for (int month = year == beginYear ? beginMonth : calendar.getActualMinimum(Calendar.MONTH); month <= (year == endYear ? endMonth
                    : calendar.getActualMaximum(Calendar.MONTH)); month++)
            {
                for (int day = (year == beginYear && month == beginMonth) ? beginDay
                        : calendar.getActualMinimum(Calendar.DATE); day <= ((year == endYear && month == endMonth) ? endDay
                        : calendar.getActualMaximum(Calendar.DATE)); day++)
                {
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DATE, day);
                    Date date = calendar.getTime();
                    List<com.sammyun.entity.attendance.Attendance> attendance = attendanceService.findAttendance(
                            dictStudent, DateUtil.string2Date(DateUtil.date2String(date, 10)));
                    if (attendance == null || attendance.size() == 0)
                    {
                        com.sammyun.entity.attendance.Attendance attendances = new com.sammyun.entity.attendance.Attendance();
                        attendances.setAttendanceDate(DateUtil.string2Date(DateUtil.date2String(date, 10)));
                        // attendances.setLeaveDate(DateUtil.string2Date(leaveEndDate));
                        // attendances.setEnterDate(DateUtil.string2Date(leaveStartDate));
                        if (leaveDetailBean.getAskLeaveType().toString().equalsIgnoreCase(Status.sick.toString()))
                        {
                            attendances.setStatus(Status.sick);
                        }
                        else if (leaveDetailBean.getAskLeaveType().toString().equalsIgnoreCase(
                                Status.compassionate.toString()))
                        {
                            attendances.setStatus(Status.compassionate);
                        }
                        attendances.setDictStudent(dictStudent);
                        attendanceService.save(attendances);

                    }
                    else
                    {
                        if (leaveDetailBean.getAskLeaveType().toString().equalsIgnoreCase(Status.sick.toString()))
                        {
                            attendance.get(0).setStatus(Status.sick);
                        }
                        else if (leaveDetailBean.getAskLeaveType().toString().equalsIgnoreCase(
                                Status.compassionate.toString()))
                        {
                            attendance.get(0).setStatus(Status.compassionate);
                        }

                        attendanceService.update(attendance.get(0));
                    }
                }
            }
        }

        AskLeave askLeave = new AskLeave();
        askLeave.setLeaveStartDate(DateUtil.string2Date(leaveStartDate));
        askLeave.setLeaveEndDate(DateUtil.string2Date(leaveEndDate));
        askLeave.setStuName(dictStudent.getStudentName());
        askLeave.setStuNo(stuNo);
        askLeave.setDescription(description);
        askLeave.setDictStudent(dictStudent);
        askLeave.setAskLeaveType(leaveDetailBean.getAskLeaveType());
        askLeaveService.save(askLeave);

        List<com.sammyun.entity.message.Message> messages = new LinkedList<com.sammyun.entity.message.Message>();
        DictClass dictClass = dictStudent.getDictClass();
        ImUserUtil imUserUtil = new ImUserUtil();
        String subject = SpringUtils.getMessage("请假信息知晓");
        String body = description;
        Set<Member> memberSets = new HashSet<Member>();
        if (dictClass != null)
        {
            if (dictClass.getClassTeacherMap() != null && dictClass.getClassTeacherMap().size() != 0)
            {
                for (ClassTeacherMap classTeacherMap : dictClass.getClassTeacherMap())
                {
                    Member tempMember = classTeacherMap.getMember();
                    if (tempMember != null)
                    {
                        memberSets.add(tempMember);
                    }
                }
                for (Member reciver : memberSets)
                {
                    if (reciver.getIsAcceptLeaveInfo() != null && !reciver.getIsAcceptLeaveInfo())
                    {
                        continue;
                    }
                    messages.add(imUserUtil.saveMessage(member, reciver, subject, body, MessageCategory.ASKLEAVE,
                            member.getDictSchool(), askLeave.getId().toString(), request));
                }
            }
        }
        messageService.batchUpdate(messages);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("请假申请已经成功提交！");
        return mobileRestFulModel;

    }

    /**
     * 新增考勤记录 <功能详细描述>
     * 
     * @param leaveDetailBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/add")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel add(AttendaceAddListBean attendanceEquipmentSyncListBean)
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<AttendanceAddBlock> rows = new LinkedList<AttendanceAddBlock>();

        List<AttendaceAddBean> attendanceInfos = attendanceEquipmentSyncListBean.getAttendanceInfos();
        if (attendanceInfos == null || attendanceInfos.size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("没有可同步的考勤信息！"));
            return listRestFulModel;
        }
        for (AttendaceAddBean attendaceAddBean : attendanceInfos)
        {
            Long appId = attendaceAddBean.getId();
            Device device = attendaceAddBean.getDevice();
            String systemInfo = attendaceAddBean.getSystemInfo();
            String cardNumber = attendaceAddBean.getCardNumber();
            String enterDateString = attendaceAddBean.getEnterDate();
            String leaveDateString = attendaceAddBean.getLeaveDate();
            boolean isRealTime = attendaceAddBean.isHasRealTime();

            ImUserUtil imUserUtil = new ImUserUtil();
            AttendanceAddBlock attendanceAddBlock = new AttendanceAddBlock();
            String subject = "刷卡通知";
            String body = "";

            Date enterDate = null;
            Date leaveDate = null;

            Date testDate = null;
            if (enterDateString != null && !"".equalsIgnoreCase(enterDateString))
            {
                testDate = formatDate(enterDateString);
                enterDate = formatDate(enterDateString);
            }

            if (leaveDateString != null && !"".equalsIgnoreCase(leaveDateString))
            {
                testDate = formatDate(leaveDateString);
                leaveDate = formatDate(leaveDateString);
            }

            if (device == null || systemInfo == null || "".equalsIgnoreCase(systemInfo) || cardNumber == null
                    || "".equalsIgnoreCase(cardNumber) || cardNumber == null || "".equalsIgnoreCase(cardNumber)
                    || appId == null)
            {
                listRestFulModel.setResultCode(1);
                listRestFulModel.setResultMessage(SpringUtils.getMessage("参数错误"));
                return listRestFulModel;
            }
            TimeCard timeCard = timeCardService.findByCardNumber(cardNumber, CardStatus.normal);
            if (timeCard == null)
            {
                listRestFulModel.setResultCode(1);
                listRestFulModel.setResultMessage("未知卡号不存在！");
                return listRestFulModel;
            }
            Member member = timeCard.getMember();
            DictSchool dictSchool = member.getDictSchool();
            if (dictSchool == null)
            {
                listRestFulModel.setResultCode(1);
                listRestFulModel.setResultMessage("未知学校不存在！");
                return listRestFulModel;
            }
            Member sender = memberService.findSystemMember(MemberType.system, dictSchool).get(0);
            SchoolHours schoolHours = dictSchool.getSchoolHours();
            if (member.getMemberType().equals(MemberType.patriarch))
            {
                DictStudent dictStudent = timeCard.getDictStudent();
                List<com.sammyun.entity.attendance.Attendance> attendances = attendanceService.findAttendance(
                        dictStudent, DateUtil.string2Date(DateUtil.date2String(testDate, 10)));
                if (schoolHours != null)
                {

                    String endAttendTime = DateUtil.date2String(testDate, 10) + " " + schoolHours.getEndAttendTime()
                            + ":00";
                    String startFinishTime = DateUtil.date2String(testDate, 10) + " "
                            + schoolHours.getStartFinishTime() + ":00";

                    if (testDate != null && testDate.before(formatDate(endAttendTime)))
                    {
                        if (attendances == null || attendances.size() == 0)
                        {
                            attendanceAddBlock = getAttendanceAddBlock(attendances, testDate, dictSchool, dictStudent,
                                    member, appId, device, systemInfo, 0, "save");
                        }
                        else
                        {
                            attendanceAddBlock = getAttendanceAddBlock(attendances, testDate, dictSchool, dictStudent,
                                    member, appId, device, systemInfo, 0, "update");
                        }
                        body = createMessage(member.getRealName(), testDate, dictStudent.getStudentName(), 1);
                    }
                    else if (testDate != null && testDate.after(formatDate(startFinishTime)))
                    {
                        if (attendances == null || attendances.size() == 0)
                        {
                            attendanceAddBlock = getAttendanceAddBlock(attendances, testDate, dictSchool, dictStudent,
                                    member, appId, device, systemInfo, 1, "save");
                        }
                        else
                        {
                            attendanceAddBlock = getAttendanceAddBlock(attendances, testDate, dictSchool, dictStudent,
                                    member, appId, device, systemInfo, 1, "update");
                        }
                        body = createMessage(member.getRealName(), testDate, dictStudent.getStudentName(), 2);

                    }
                    else
                    {

                        if (attendances == null || attendances.size() == 0)
                        {
                            attendanceAddBlock = getAttendanceAddBlock(attendances, testDate, dictSchool, dictStudent,
                                    member, appId, device, systemInfo, 0, "save");
                            body = createMessage(member.getRealName(), testDate, dictStudent.getStudentName(), 1);
                        }
                        else
                        {

                            if (enterDate != null)
                            {
                                updateAttendance(attendances.get(0), testDate, dictSchool, 0);
                            }
                            else if (leaveDate != null)
                            {
                                updateAttendance(attendances.get(0), testDate, dictSchool, 1);
                            }
                            attendanceAddBlock = getReturnData(
                                    appId,
                                    saveAttendanceDetail(device, systemInfo, member.getRealName(), attendances.get(0),
                                            testDate));
                            body = createMessage(member.getRealName(), testDate, dictStudent.getStudentName(), 2);
                        }
                    }

                }
                else
                {
                    if (attendances == null || attendances.size() == 0)
                    {
                        attendanceAddBlock = getAttendanceAddBlock(attendances, testDate, dictSchool, dictStudent,
                                member, appId, device, systemInfo, 0, "save");
                        body = createMessage(member.getRealName(), testDate, dictStudent.getStudentName(), 1);
                    }
                    else
                    {
                        com.sammyun.entity.attendance.Attendance temp = updateAttendance(attendances.get(0), testDate,
                                dictSchool, 0);
                        attendanceAddBlock = getAttendanceAddBlock(attendances, testDate, dictSchool, dictStudent,
                                member, appId, device, systemInfo, 1, "upate");
                        body = createMessage(member.getRealName(), testDate, dictStudent.getStudentName(), 2);

                    }
                }
            }
            else if (member.getMemberType().equals(MemberType.teacher))
            {
                String endWorkTime = null;
                String startClosingTime = null;

                List<TeacherAttendance> teacherAttendances = findTeacherAttendances(member, testDate);
                WorkSetting workSetting = getWorkSetting(member, dictSchool, testDate);
                if (workSetting == null)
                {
                    continue;
                }

                endWorkTime = DateUtil.date2String(testDate, 10) + " " + workSetting.getEndWorkTime() + ":00";
                if (getIsCurrentDay(workSetting.getWorkTime(), workSetting.getClosingTime()))
                {
                    Date date = DateUtil.getDateByAddDays(testDate, 1);
                    startClosingTime = DateUtil.date2String(date, 10) + " " + workSetting.getStartClosingTime() + ":00";
                }
                else
                {
                    startClosingTime = DateUtil.date2String(testDate, 10) + " " + workSetting.getStartClosingTime()
                            + ":00";
                }

                if (testDate != null && testDate.before(formatDate(endWorkTime)))
                {
                    if (teacherAttendances == null || teacherAttendances.size() == 0)
                    {
                        attendanceAddBlock = getTeacherAttendanceAddBlock(teacherAttendances, appId, cardNumber,
                                member, workSetting, testDate, 0, "save");
                    }
                    else
                    {
                        attendanceAddBlock = getTeacherAttendanceAddBlock(teacherAttendances, appId, cardNumber,
                                member, workSetting, testDate, 0, "update");
                    }

                }
                else if (testDate != null && testDate.after(formatDate(startClosingTime)))
                {
                    if (teacherAttendances == null || teacherAttendances.size() == 0)
                    {
                        attendanceAddBlock = getTeacherAttendanceAddBlock(teacherAttendances, appId, cardNumber,
                                member, workSetting, testDate, 1, "save");
                    }
                    else
                    {
                        attendanceAddBlock = getTeacherAttendanceAddBlock(teacherAttendances, appId, cardNumber,
                                member, workSetting, testDate, 1, "update");
                    }
                }
                else
                {
                    if (teacherAttendances == null || teacherAttendances.size() == 0)
                    {
                        attendanceAddBlock = getTeacherAttendanceAddBlock(teacherAttendances, appId, cardNumber,
                                member, workSetting, testDate, 0, "save");
                    }
                    else
                    {
                        if (enterDate != null)
                        {
                            updateTeacherAttendance(teacherAttendances.get(0), workSetting, testDate, 0);
                        }
                        else if (leaveDate != null)
                        {
                            updateTeacherAttendance(teacherAttendances.get(0), workSetting, testDate, 1);
                        }
                        attendanceAddBlock = getReturnData(appId,
                                saveTeacherAttendanceDetail(cardNumber, testDate, teacherAttendances.get(0)));
                    }
                }
                body = createMessage(member.getRealName(), testDate, null, 3);
            }
            // 实时数据才需要推送
            if (isRealTime && member.getMemberType().equals(MemberType.patriarch))
            {
                messageService.save(imUserUtil.saveMessage(sender, member, subject, body, MessageCategory.NOTICE,
                        member.getDictSchool(), null, request));
            }
            rows.add(attendanceAddBlock);
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("考勤成功！");
        return listRestFulModel;

    }

    /**
     * 获取排班信息
     */
    public WorkSetting getWorkSetting(Member member, DictSchool dictSchool, Date date)
    {

        WorkSetting workSetting = null;
        List<WorkScheduling> workSchedulings = workSchedulingService.findWorkScheduling(member);
        if (workSchedulings != null && workSchedulings.size() != 0)
        {
            WorkScheduling workScheduling = workSchedulings.get(0);
            Map<String, WorkSetting> workSchedulingMap = workScheduling.getSchedulingAttributes();
            SchedulingWay schedulingWay = workScheduling.getSchedulingWay();
            if (schedulingWay.equals(SchedulingWay.week))
            {
                workSetting = workSchedulingMap.get(getWeekDay(date));

            }
            if (schedulingWay.equals(SchedulingWay.date))
            {
                workSetting = workSchedulingMap.get(DateUtil.date2String(date, 10));
            }
        }
        if (workSetting == null)
        {
            List<WorkSetting> workSettings = workSettingService.findBySchool(dictSchool, true);
            if (workSettings != null && workSettings.size() != 0)
            {
                workSetting = workSettings.get(0);
            }
        }
        return workSetting;
    }

    /**
     * 生成接口返回数据 -- 学生
     */
    public AttendanceAddBlock getAttendanceAddBlock(List<com.sammyun.entity.attendance.Attendance> attendances,
            Date date, DictSchool dictSchool, DictStudent dictStudent, Member member, Long appId, Device device,
            String systemInfo, Integer flag, String type)
    {
        com.sammyun.entity.attendance.Attendance attendance = new com.sammyun.entity.attendance.Attendance();
        type = type.trim();
        if ("save".equals(type))
        {
            attendance = saveAttendance(date, dictSchool, dictStudent, flag);
        }
        else if ("update".equals(type))
        {
            attendance = updateAttendance(attendances.get(0), date, dictSchool, flag);
        }
        AttendanceAddBlock attendanceAddBlock = getReturnData(appId,
                saveAttendanceDetail(device, systemInfo, member.getRealName(), attendance, date));
        return attendanceAddBlock;
    }

    /**
     * 生成学生考勤信息
     */
    public com.sammyun.entity.attendance.Attendance saveAttendance(Date date, DictSchool dictSchool,
            DictStudent dictStudent, Integer flag)
    {

        com.sammyun.entity.attendance.Attendance attendance = new com.sammyun.entity.attendance.Attendance();
        attendance.setAttendanceDate(DateUtil.string2Date(DateUtil.date2String(date, 10)));

        if (flag == 0)
        {
            attendance.setEnterDate(date);
            attendance.setStatus(getEnterStatus(date, dictSchool));
        }
        else if (flag == 1)
        {
            attendance.setLeaveDate(date);
            attendance.setStatus(getLeaveStatus(date, dictSchool));
        }
        attendance.setDictStudent(dictStudent);
        attendanceService.save(attendance);
        return attendance;
    }

    /**
     * 更新学生考勤信息
     */
    public com.sammyun.entity.attendance.Attendance updateAttendance(
            com.sammyun.entity.attendance.Attendance attendance, Date date, DictSchool dictSchool, Integer flag)
    {
        if (flag == 0)
        {
            if (attendance.getEnterDate() == null || date.after(attendance.getEnterDate()))
            {
                attendance.setEnterDate(date);
            }
            attendance.setStatus(getEnterStatus(date, dictSchool));
        }
        else if (flag == 1)
        {
            if (attendance.getLeaveDate() == null || date.after(attendance.getLeaveDate()))
            {
                attendance.setLeaveDate(date);
            }
            attendance.setStatus(getLeaveStatus(date, dictSchool));
        }
        attendanceService.update(attendance);
        return attendance;
    }

    /**
     * 生成接口返回数据 -- 老师
     */
    public AttendanceAddBlock getTeacherAttendanceAddBlock(List<TeacherAttendance> teacherAttendances, Long appId,
            String cardNumber, Member member, WorkSetting workSetting, Date date, Integer flag, String type)
    {
        TeacherAttendance teacherAttendance = new TeacherAttendance();
        type = type.trim();
        if ("save".equals(type))
        {
            teacherAttendance = saveTeacherAttendance(member, workSetting, date, flag);
        }
        else if ("update".equals(type))
        {
            teacherAttendance = updateTeacherAttendance(teacherAttendances.get(0), workSetting, date, flag);
        }
        AttendanceAddBlock attendanceAddBlock = getReturnData(appId,
                saveTeacherAttendanceDetail(cardNumber, date, teacherAttendance));
        return attendanceAddBlock;
    }

    /**
     * 生成教师考勤信息
     */
    public TeacherAttendance saveTeacherAttendance(Member member, WorkSetting workSetting, Date date, Integer flag)
    {

        TeacherAttendance teacherAttendance = new TeacherAttendance();
        teacherAttendance.setMember(member);
        teacherAttendance.setWorkSettingName(workSetting.getName());
        teacherAttendance.setWorkTime(workSetting.getWorkTime());
        teacherAttendance.setClosingTime(workSetting.getClosingTime());

        if (flag == 0)
        {
            teacherAttendance.setWorkSwipeTime(date);
            teacherAttendance.setWorkStatus(getWorkStatus(workSetting.getWorkTime(), workSetting.getClosingTime(),
                    date, workSetting.getStartWorkTime(), workSetting.getEndWorkTime()));

        }
        else if (flag == 1)
        {
            teacherAttendance.setClosingSwipeTime(date);
            teacherAttendance.setClosingStatus(getClosingStatus(workSetting.getWorkTime(),
                    workSetting.getClosingTime(), date, workSetting.getStartClosingTime(),
                    workSetting.getEndClosingTime()));
        }
        teacherAttendanceService.save(teacherAttendance);
        return teacherAttendance;
    }

    /**
     * 更新教师考勤信息
     */
    public TeacherAttendance updateTeacherAttendance(TeacherAttendance teacherAttendance, WorkSetting workSetting,
            Date date, Integer flag)
    {
        if (flag == 0)
        {
            if (teacherAttendance.getWorkSwipeTime() == null || date.after(teacherAttendance.getWorkSwipeTime()))
            {
                teacherAttendance.setWorkSwipeTime(date);
            }
            teacherAttendance.setWorkStatus(getWorkStatus(workSetting.getWorkTime(), workSetting.getClosingTime(),
                    date, workSetting.getStartWorkTime(), workSetting.getEndWorkTime()));
        }
        else if (flag == 1)
        {
            if (teacherAttendance.getClosingSwipeTime() == null || date.after(teacherAttendance.getClosingSwipeTime()))
            {
                teacherAttendance.setClosingSwipeTime(date);
            }
            teacherAttendance.setClosingStatus(getClosingStatus(workSetting.getWorkTime(),
                    workSetting.getClosingTime(), date, workSetting.getStartClosingTime(),
                    workSetting.getEndClosingTime()));
        }
        teacherAttendanceService.update(teacherAttendance);
        return teacherAttendance;
    }

    /**
     * 生成接口返回数据
     */
    public AttendanceAddBlock getReturnData(Long appId, Long serverId)
    {
        AttendanceAddBlock attendanceAddBlock = new AttendanceAddBlock();
        attendanceAddBlock.setAppId(appId);
        attendanceAddBlock.setServerId(serverId);
        return attendanceAddBlock;
    }

    /**
     * 初始化date
     */
    public Date formatDate(String dataString)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try
        {
            date = format.parse(dataString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 计算入园状态 -- 学生
     */
    public Status getEnterStatus(Date enterDate, DictSchool dictSchool)
    {
        Status status = Status.normal;
        SchoolHours schoolHours = dictSchool.getSchoolHours();
        if (schoolHours != null)
        {
            String startAttendTime = DateUtil.date2String(enterDate, 10) + " " + schoolHours.getStartAttendTime()
                    + ":00";
            String endAttendTime = DateUtil.date2String(enterDate, 10) + " " + schoolHours.getEndAttendTime() + ":00";
            Date beginDate = formatDate(startAttendTime);
            Date endDate = formatDate(endAttendTime);

            if (enterDate.after(endDate))
            {
                status = Status.late;
            }
        }
        return status;
    }

    /**
     * 计算出园状态 -- 学生
     */
    public Status getLeaveStatus(Date leaveDate, DictSchool dictSchool)
    {
        Status status = Status.normal;
        SchoolHours schoolHours = dictSchool.getSchoolHours();
        if (schoolHours != null)
        {
            String startFinishTime = DateUtil.date2String(leaveDate, 10) + " " + schoolHours.getStartFinishTime()
                    + ":00";
            String endFinishTime = DateUtil.date2String(leaveDate, 10) + " " + schoolHours.getEndFinishTime() + ":00";
            Date beginDate = formatDate(startFinishTime);
            Date endDate = formatDate(endFinishTime);
            if (leaveDate.before(beginDate))
            {
                status = Status.early;
            }
        }
        return status;
    }

    /**
     * 查找教师考情列表
     */
    public List<com.sammyun.entity.attendance.TeacherAttendance> findTeacherAttendances(Member member, Date date)
    {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = null;
        Date endDate = null;

        try
        {
            String stringDate = DateUtil.date2String(date, 10) + " 00:00:00";
            beginDate = format.parse(stringDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        try
        {
            String stringDate = DateUtil.date2String(date, 10) + " 23:59:59";
            endDate = format.parse(stringDate);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        List<com.sammyun.entity.attendance.TeacherAttendance> teacherAttendances = teacherAttendanceService.findTeacherAttendance(
                member, beginDate, endDate);
        return teacherAttendances;
    }

    /**
     * 生成推送信息
     */
    public String createMessage(String realName, Date date, String studentName, Integer type)
    {
        Calendar calendar = DateUtils.toCalendar(date);
        int year = 0;
        int month = 0;
        int day = 0;
        int hour = 0;
        int minute = 0;
        year = calendar.get(Calendar.YEAR);
        String stringYear = String.valueOf(year);
        month = calendar.get(Calendar.MONTH) + 1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        String body = null;
        if (type == 1)
        {
            body = SpringUtils.getMessage("Api.attendance.enterNotice", studentName, stringYear, month, day, hour,
                    minute);
        }
        else if (type == 2)
        {
            body = SpringUtils.getMessage("Api.attendance.leaveNotice", studentName, stringYear, month, day, hour,
                    minute);
        }
        else if (type == 3)
        {
            body = SpringUtils.getMessage("Api.message.teacherLeaveNotice", realName, stringYear, month, day, hour,
                    minute);
        }
        return body;
    }

    /**
     * 判断上班状态 <功能详细描述>
     * 
     * @param workSwipeTime
     * @param startWorkTime
     * @param endWorkTime
     * @return
     * @throws ParseException
     * @see [类、类#方法、类#成员]
     */
    public TeacherAttendance.Status getWorkStatus(String workTime, String closingTime, Date workSwipeTime,
            String startWorkTime, String endWorkTime)
    {
        TeacherAttendance.Status status = TeacherAttendance.Status.normal;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String workSwipeTimeToString = DateUtil.date2String(workSwipeTime, 10) + " " + startWorkTime + ":00";
        String endWorkTimeToString = DateUtil.date2String(workSwipeTime, 10) + " " + endWorkTime + ":00";
        Date beginDate = null;
        Date endDate = null;
        try
        {
            beginDate = format.parse(workSwipeTimeToString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        try
        {
            endDate = format.parse(endWorkTimeToString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        if (beginDate != null && workSwipeTime.before(beginDate))
        {
            status = TeacherAttendance.Status.normal;
        }
        else if (beginDate != null && endDate != null && workSwipeTime.after(beginDate)
                && workSwipeTime.before(endDate))
        {
            status = TeacherAttendance.Status.late;
        }
        else
        {
            status = TeacherAttendance.Status.absenteeism;
        }
        return status;
    }

    /**
     * 判断下班状态 <功能详细描述>
     * 
     * @param workSwipeTime
     * @param startWorkTime
     * @param endWorkTime
     * @return
     * @throws ParseException
     * @see [类、类#方法、类#成员]
     */
    public TeacherAttendance.Status getClosingStatus(String workTime, String closingTime, Date closingSwipeTime,
            String startClosingTime, String endClosingTime)
    {
        TeacherAttendance.Status status = TeacherAttendance.Status.normal;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = closingSwipeTime;
        if (getIsCurrentDay(workTime, closingTime))
        {
            date = DateUtil.getDateByAddDays(closingSwipeTime, 1);
        }
        String startClosingTimeToString = DateUtil.date2String(date, 10) + " " + startClosingTime + ":00";
        String endClosingTimeToString = DateUtil.date2String(date, 10) + " " + endClosingTime + ":00";

        Date beginDate = null;
        Date endDate = null;
        try
        {
            beginDate = format.parse(startClosingTimeToString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        try
        {
            endDate = format.parse(endClosingTimeToString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        if (null != beginDate && closingSwipeTime.before(beginDate))
        {
            status = TeacherAttendance.Status.early;
        }
        return status;
    }

    /**
     * 计算班次时间是否跨天
     */
    public boolean getIsCurrentDay(String workTime, String closingTime)
    {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String workTimeToString = DateUtil.date2String(DateUtil.getDate(), 10) + " " + workTime + ":00";
        String closingTimeToString = DateUtil.date2String(DateUtil.getDate(), 10) + " " + closingTime + ":00";

        Date beginDate = null;
        Date endDate = null;
        try
        {
            beginDate = format.parse(workTimeToString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        try
        {
            endDate = format.parse(closingTimeToString);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        if (null != endDate && beginDate.after(endDate))
        {
            return true;
        }
        return false;
    }

    /**
     * 计算当前时间为周几
     */
    public String getWeekDay(Date date)
    {
        int weekDay = DateUtil.getWeek(DateUtil.date2String(date, 10));
        String weekDayString = null;

        if (weekDay == 1)
        {
            weekDayString = "sunday";
        }
        if (weekDay == 2)
        {
            weekDayString = "monday";
        }
        if (weekDay == 3)
        {
            weekDayString = "tuesday";
        }
        if (weekDay == 4)
        {
            weekDayString = "wednesday";
        }
        if (weekDay == 5)
        {
            weekDayString = "thursday";
        }
        if (weekDay == 6)
        {
            weekDayString = "friday";
        }
        if (weekDay == 7)
        {
            weekDayString = "saturday";
        }
        return weekDayString;
    }

    /**
     * 生成考勤详情
     */
    public Long saveAttendanceDetail(Device device, String systemInfo, String take,
            com.sammyun.entity.attendance.Attendance attendance, Date date)
    {
        AttendanceDetail attendanceDetail = new AttendanceDetail();
        attendanceDetail.setClockInDate(date);
        attendanceDetail.setDevice(device);
        attendanceDetail.setSystemInfo(systemInfo);
        attendanceDetail.setTake(take);

        Calendar beginCalendar = DateUtils.toCalendar(date);
        int hour = beginCalendar.get(Calendar.HOUR_OF_DAY);
        if (hour >= 6 && hour <= 11)
        {
            attendanceDetail.setTimeType(TimeType.morning);
        }
        else if (hour >= 12 && hour <= 13)
        {
            attendanceDetail.setTimeType(TimeType.noon);
        }
        else if (hour >= 14 && hour <= 18)
        {
            attendanceDetail.setTimeType(TimeType.afternoon);
        }
        else if (hour >= 19 && hour <= 24)
        {
            attendanceDetail.setTimeType(TimeType.night);
        }
        attendanceDetail.setAttendance(attendance);
        attendanceDetailService.save(attendanceDetail);
        return attendanceDetail.getId();
    }

    /**
     * 生成教师考勤详情
     */
    public Long saveTeacherAttendanceDetail(String cardNumber, Date clockInDate, TeacherAttendance teacherAttendance)
    {
        TeacherAttendanceDetail teacherAttendanceDetail = new TeacherAttendanceDetail();
        teacherAttendanceDetail.setCardNumber(cardNumber);
        teacherAttendanceDetail.setClockInDate(clockInDate);
        teacherAttendanceDetail.setTeacherAttendance(teacherAttendance);
        teacherAttendanceDetailService.save(teacherAttendanceDetail);
        return teacherAttendanceDetail.getId();
    }
}
