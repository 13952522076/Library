package com.sammyun.controller.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.sammyun.Order;
import com.sammyun.controller.api.bean.CurriculumScheduleClassInfoBean;
import com.sammyun.controller.api.block.AttendaceClassInfoBlock;
import com.sammyun.controller.api.block.CurriculumScheduleForSchoolBlock;
import com.sammyun.controller.api.block.CurriculumScheduleListBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.dict.ClassTeacherMap;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictClass.ClassStatus;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.PatriarchStudentMap;
import com.sammyun.service.MemberService;
import com.sammyun.service.course.CurriculumScheduleService;
import com.sammyun.service.course.SchoolYearMngService;
import com.sammyun.service.dict.ClassTeacherMapService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.service.dict.PatriarchStudentMapService;
import com.sammyun.util.SpringUtils;

/**
 * api - 课程表管理
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("curriculumSchedule")
@Path("/curriculumSchedule")
public class CurriculumScheduleApi
{
    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "schoolYearMngServiceImpl")
    private SchoolYearMngService schoolYearMngService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "classTeacherMapServiceImpl")
    private ClassTeacherMapService classTeacherMapService;

    @Resource(name = "patriarchStudentMapServiceImpl")
    private PatriarchStudentMapService patriarchStudentMapService;

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    @Resource(name = "curriculumScheduleServiceImpl")
    private CurriculumScheduleService curriculumScheduleService;

    /**
     * 查询学年列表
     * 
     * @param loginBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/schoolYearMngo/{dictSchoolId}")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel schoolYearMng(@PathParam("dictSchoolId") Long dictSchoolId)
    {

        ListRestFulModel listRestFulModel = new ListRestFulModel();
        if (dictSchoolId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误！");
            return listRestFulModel;
        }

        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        if (dictSchool == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("未知学校不存在！"));
            return listRestFulModel;
        }
        // 默认查询显示的学年
        Boolean isShow = true;
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        List<SchoolYearMng> schoolYearMngs = schoolYearMngService.findSchoolYearMng(dictSchool, isShow, orders);
        if (schoolYearMngs == null || schoolYearMngs.size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("没有相关学年信息！");
            return listRestFulModel;
        }
        List<CurriculumScheduleForSchoolBlock> rows = new LinkedList<CurriculumScheduleForSchoolBlock>();
        for (SchoolYearMng schoolYearMng : schoolYearMngs)
        {
            CurriculumScheduleForSchoolBlock curriculumScheduleForSchoolBlock = new CurriculumScheduleForSchoolBlock();
            curriculumScheduleForSchoolBlock.setStartYear(schoolYearMng.getStartYear());
            curriculumScheduleForSchoolBlock.setEndYear(schoolYearMng.getEndYear());
            curriculumScheduleForSchoolBlock.setTerm(schoolYearMng.getTerm());
            curriculumScheduleForSchoolBlock.setId(schoolYearMng.getId());
            curriculumScheduleForSchoolBlock.setIsCurrent(schoolYearMng.getIsCurrent());
            rows.add(curriculumScheduleForSchoolBlock);
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("获取学年列表成功");
        return listRestFulModel;
    }

    /**
     * 查询班级列表
     * 
     * @param loginBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/classInfo")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel classInfo(CurriculumScheduleClassInfoBean curriculumScheduleClassInfoBean)
    {

        ListRestFulModel listRestFulModel = new ListRestFulModel();
        Set<DictClass> dictClassSets = new HashSet<DictClass>();
        List<DictClass> dictClasses = new ArrayList<DictClass>();
        List<AttendaceClassInfoBlock> classInfos = new LinkedList<AttendaceClassInfoBlock>();

        Long dictSchoolId = curriculumScheduleClassInfoBean.getDictSchoolId();
        Long memberId = curriculumScheduleClassInfoBean.getMemberId();
        MemberType memberType = curriculumScheduleClassInfoBean.getMemberType();
        ClassStatus classStatus = curriculumScheduleClassInfoBean.getClassStatus();

        if (dictSchoolId == null || memberId == null || memberType == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误！");
            return listRestFulModel;
        }

        Member member = memberService.find(memberId);
        if (member == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.unknownAccount"));
            return listRestFulModel;
        }
        if (!memberType.equals(member.getMemberType()))
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("会员类型错误"));
            return listRestFulModel;
        }

        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        if (dictSchool == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("未知学校不存在！"));
            return listRestFulModel;
        }
        if (classStatus == null)
        {
            classStatus = ClassStatus.active;
        }

        if (memberType.equals(MemberType.patriarch))
        {
            List<PatriarchStudentMap> patriarchStudentMaps = patriarchStudentMapService.findStudentByMember(member);
            if (patriarchStudentMaps != null && patriarchStudentMaps.size() != 0)
            {
                for (PatriarchStudentMap patriarchStudentMap : patriarchStudentMaps)
                {
                    dictClassSets.add(patriarchStudentMap.getDictStudent().getDictClass());
                }
            }
        }
        else if (memberType.equals(MemberType.teacher))
        {

            List<ClassTeacherMap> classTeacherMaps = classTeacherMapService.findClassesBySchoolAndMember(dictSchool,
                    member, classStatus);
            if (classTeacherMaps != null && classTeacherMaps.size() != 0)
            {

                for (ClassTeacherMap classTeacherMap : classTeacherMaps)
                {
                    dictClassSets.add(classTeacherMap.getDictClass());
                }

            }
        }
        else
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("会员类型错误！"));
            return listRestFulModel;
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
        listRestFulModel.setRows(classInfos);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("获取班级列表成功");
        return listRestFulModel;
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
     * 查询课程列表
     * 
     * @param loginBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/list/{schoolYearMngId}/{dictClassId}")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel list(@PathParam("schoolYearMngId") Long schoolYearMngId,
            @PathParam("dictClassId") Long dictClassId)
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<CurriculumScheduleListBlock> rows = new LinkedList<CurriculumScheduleListBlock>();
        if (schoolYearMngId == null || dictClassId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误！");
            return listRestFulModel;
        }
        SchoolYearMng schoolYearMng = schoolYearMngService.find(schoolYearMngId);
        if (schoolYearMng == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("未知学年不存在！");
            return listRestFulModel;
        }
        DictClass dictClass = dictClassService.find(dictClassId);
        if (dictClass == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("未知班级不存在！");
            return listRestFulModel;
        }
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.asc("week"));
        orders.add(Order.asc("lessons"));
        List<com.sammyun.entity.course.CurriculumSchedule> curriculumSchedules = curriculumScheduleService.findByClassAndSchoolYear(
                schoolYearMng, dictClass, orders);
        if (curriculumSchedules == null || curriculumSchedules.size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("没有相关课程表信息！");
            return listRestFulModel;
        }
        for (com.sammyun.entity.course.CurriculumSchedule curriculumSchedule : curriculumSchedules)
        {
            CurriculumScheduleListBlock curriculumScheduleListBlock = new CurriculumScheduleListBlock();
            curriculumScheduleListBlock.setId(curriculumSchedule.getId());
            curriculumScheduleListBlock.setWeek(curriculumSchedule.getWeek());
            curriculumScheduleListBlock.setTeacherName(curriculumSchedule.getTeacherName());
            curriculumScheduleListBlock.setCourseName(curriculumSchedule.getCourseName());
            curriculumScheduleListBlock.setLessons(curriculumSchedule.getLessons());
            curriculumScheduleListBlock.setClassRoom(curriculumSchedule.getClassRoom());
            curriculumScheduleListBlock.setStartTime(curriculumSchedule.getStartTime());
            curriculumScheduleListBlock.setEndTime(curriculumSchedule.getEndTime());
            rows.add(curriculumScheduleListBlock);
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("获取课程列表成功");
        return listRestFulModel;
    }
}
