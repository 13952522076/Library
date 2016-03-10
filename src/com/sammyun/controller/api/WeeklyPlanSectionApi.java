package com.sammyun.controller.api;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import com.sammyun.Order;
import com.sammyun.controller.api.block.WeeklyPlanSectionDetailBlock;
import com.sammyun.controller.api.block.WeeklyPlanSectionListBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.course.WeeklyPlanDetail;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.course.SchoolYearMngService;
import com.sammyun.service.course.WeeklyPlanDetailService;
import com.sammyun.service.course.WeeklyPlanSectionService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.ImUserUtil;
import com.sammyun.util.SpringUtils;

/**
 * api - 周计划段
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("weeklyPlanSection")
@Path("/weeklyPlanSection")
public class WeeklyPlanSectionApi
{

    @Resource(name = "schoolYearMngServiceImpl")
    private SchoolYearMngService schoolYearMngService;

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    @Resource(name = "weeklyPlanSectionServiceImpl")
    private WeeklyPlanSectionService weeklyPlanSectionService;

    @Resource(name = "weeklyPlanDetailServiceImpl")
    private WeeklyPlanDetailService weeklyPlanDetailService;

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    /**
     * 查询周计划表 <功能详细描述>
     * 
     * @param schoolYearMngId
     * @param dictClassId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/list/{dictSchoolId}/{schoolYearMngId}/{dictClassId}")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel list(@PathParam("dictSchoolId") Long dictSchoolId,
            @PathParam("schoolYearMngId") Long schoolYearMngId, @PathParam("dictClassId") Long dictClassId)
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<WeeklyPlanSectionListBlock> rows = new LinkedList<WeeklyPlanSectionListBlock>();
        if (dictSchoolId == null || schoolYearMngId == null || dictClassId == null)
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
        orders.add(Order.desc("week"));
        List<com.sammyun.entity.course.WeeklyPlanSection> weeklyPlanSections = weeklyPlanSectionService.findByClassAndSchoolYear(
                dictSchool, schoolYearMng, dictClass, orders);
        if (weeklyPlanSections == null || weeklyPlanSections.size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("没有相关周计划段信息！");
            return listRestFulModel;
        }
        for (com.sammyun.entity.course.WeeklyPlanSection weeklyPlanSection : weeklyPlanSections)
        {
            WeeklyPlanSectionListBlock weeklyPlanSectionListBlock = new WeeklyPlanSectionListBlock();
            weeklyPlanSectionListBlock.setId(weeklyPlanSection.getId());
            weeklyPlanSectionListBlock.setWeek(weeklyPlanSection.getWeek());
            weeklyPlanSectionListBlock.setWeekSubject(weeklyPlanSection.getWeekSubject());
            weeklyPlanSectionListBlock.setIsCurrent(weeklyPlanSection.getIsCurrent());
            if (weeklyPlanSection.getWeekStartDate() != null)
            {
                weeklyPlanSectionListBlock.setWeekStartDate(DateUtil.date2String(weeklyPlanSection.getWeekStartDate(),
                        10));
            }
            if (weeklyPlanSection.getWeekEndDate() != null)
            {
                weeklyPlanSectionListBlock.setWeekEndDate(DateUtil.date2String(weeklyPlanSection.getWeekEndDate(), 10));
            }
            rows.add(weeklyPlanSectionListBlock);
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("获取课程列表成功");
        return listRestFulModel;
    }

    /**
     * 查询周计划表 <功能详细描述>
     * 
     * @param schoolYearMngId
     * @param dictClassId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/content/{weeklyPlanSectionId}")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel content(@PathParam("weeklyPlanSectionId") Long weeklyPlanSectionId)
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<WeeklyPlanSectionDetailBlock> rows = new LinkedList<WeeklyPlanSectionDetailBlock>();
        ImUserUtil imUserUtil = new ImUserUtil();
        if (weeklyPlanSectionId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误！");
            return listRestFulModel;
        }
        com.sammyun.entity.course.WeeklyPlanSection weeklyPlanSection = weeklyPlanSectionService.find(weeklyPlanSectionId);
        if (weeklyPlanSection == null || weeklyPlanSection.getWeeklyPlanDetails() == null
                || weeklyPlanSection.getWeeklyPlanDetails().size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("没有相关周计划段信息！");
            return listRestFulModel;
        }

        for (WeeklyPlanDetail weeklyPlanDetail : weeklyPlanSection.getWeeklyPlanDetails())
        {
            WeeklyPlanSectionDetailBlock weeklyPlanSectionDetailBlock = new WeeklyPlanSectionDetailBlock();
            weeklyPlanSectionDetailBlock.setMonday(weeklyPlanDetail.getMonday());
            weeklyPlanSectionDetailBlock.setMondayDesc(weeklyPlanDetail.getMondayDesc());
            weeklyPlanSectionDetailBlock.setMondayImage(imUserUtil.getDefaultImageUrl(weeklyPlanDetail.getMondayImage()));
            weeklyPlanSectionDetailBlock.setTuesday(weeklyPlanDetail.getTuesday());
            weeklyPlanSectionDetailBlock.setTuesdayDesc(weeklyPlanDetail.getTuesdayDesc());
            weeklyPlanSectionDetailBlock.setTuesdayImage(imUserUtil.getDefaultImageUrl(weeklyPlanDetail.getTuesdayImage()));
            weeklyPlanSectionDetailBlock.setWednesday(weeklyPlanDetail.getWednesday());
            weeklyPlanSectionDetailBlock.setWednesdayDesc(weeklyPlanDetail.getWednesdayDesc());
            weeklyPlanSectionDetailBlock.setWednesdayImage(imUserUtil.getDefaultImageUrl(weeklyPlanDetail.getWednesdayImage()));
            weeklyPlanSectionDetailBlock.setThursday(weeklyPlanDetail.getThursday());
            weeklyPlanSectionDetailBlock.setThursdayDesc(weeklyPlanDetail.getThursdayDesc());
            weeklyPlanSectionDetailBlock.setThursdayImage(imUserUtil.getDefaultImageUrl(weeklyPlanDetail.getThursdayImage()));
            weeklyPlanSectionDetailBlock.setFriday(weeklyPlanDetail.getFriday());
            weeklyPlanSectionDetailBlock.setFridayDesc(weeklyPlanDetail.getFridayDesc());
            weeklyPlanSectionDetailBlock.setFridayImage(imUserUtil.getDefaultImageUrl(weeklyPlanDetail.getFridayImage()));
            weeklyPlanSectionDetailBlock.setPlanSection(SpringUtils.getMessage("WeeklyPlanDetail.planSection."
                    + weeklyPlanDetail.getPlanSection()));
            rows.add(weeklyPlanSectionDetailBlock);

        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("获取周计划信息成功");
        return listRestFulModel;
    }
}
