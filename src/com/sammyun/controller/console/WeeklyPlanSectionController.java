package com.sammyun.controller.console;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.course.WeeklyPlanDetail;
import com.sammyun.entity.course.WeeklyPlanSection;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.course.SchoolYearMngService;
import com.sammyun.service.course.WeeklyPlanDetailService;
import com.sammyun.service.course.WeeklyPlanSectionService;
import com.sammyun.service.dict.DictClassService;

/**
 * Controller - 周计划管理
 * 
 * @author xutianlong
 * @version [版本号, May 2, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("adminWeeklyPlanSectionController")
@RequestMapping("/console/weekly_plan_section")
public class WeeklyPlanSectionController extends BaseController
{
    @Resource(name = "weeklyPlanSectionServiceImpl")
    private WeeklyPlanSectionService weeklyPlanSectionService;

    @Resource(name = "weeklyPlanDetailServiceImpl")
    private WeeklyPlanDetailService weeklyPlanDetailService;

    @Resource(name = "schoolYearMngServiceImpl")
    private SchoolYearMngService schoolYearMngService;

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    /**
     * 列表
     * 
     * @param pageable
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        Filter filter = new Filter("dictSchool", Operator.eq, dictSchool);
        pageable.addFilters(filter);
        model.addAttribute("page", weeklyPlanSectionService.findPage(pageable));
        model.addAttribute("menuId", WeeklyPlanSection.class.getSimpleName());
        return "/console/weekly_plan_section/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<SchoolYearMng> schoolYearMngs = schoolYearMngService.findBySchool(dictSchool);
        List<DictClass> dictClasses = dictClassService.getClassesBySchool(dictSchool);
        model.addAttribute("schoolYearMngs", schoolYearMngs);
        model.addAttribute("dictClasses", dictClasses);
        model.addAttribute("menuId", WeeklyPlanSection.class.getSimpleName());
        return "/console/weekly_plan_section/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(WeeklyPlanSection weeklyPlanSection, Long schoolYearMngId, Long dictClassId,
            RedirectAttributes redirectAttributes, ModelMap model)
    {
        SchoolYearMng schoolYearMng = this.schoolYearMngService.find(schoolYearMngId);
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        DictClass dictClass = dictClassService.find(dictClassId);
        weeklyPlanSection.setSchoolYearMng(schoolYearMng);
        weeklyPlanSection.setDictSchool(dictSchool);
        weeklyPlanSection.setDictClass(dictClass);
        if (!isValid(weeklyPlanSection))
        {
            return ERROR_VIEW;
        }
        
//        //如果iscurrent为真，当前学校的其他为假
//        if(weeklyPlanSection.getIsCurrent()){
//            List<WeeklyPlanSection> weeklyPlanSections = weeklyPlanSectionService.findByClass(dictClass);
//            if(weeklyPlanSections!=null&&weeklyPlanSections.size()>0){
//                for(WeeklyPlanSection prePlanSection:weeklyPlanSections){
//                    if(prePlanSection.getIsCurrent()==true){
//                        prePlanSection.setIsCurrent(false);
//                        weeklyPlanSectionService.update(prePlanSection);
//                    }
//                }
//            }
//        }
        weeklyPlanSection.setIsCurrent(false);
        weeklyPlanSectionService.save(weeklyPlanSection);

        List<WeeklyPlanDetail> weeklyPlanDetails = buildDetails(weeklyPlanSection.getWeeklyPlanDetails(),
                weeklyPlanSection);
        weeklyPlanDetailService.batchUpdate(weeklyPlanDetails);
        model.addAttribute("menuId", WeeklyPlanSection.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<SchoolYearMng> schoolYearMngs = schoolYearMngService.findBySchool(dictSchool);
        List<DictClass> dictClasses = dictClassService.getClassesBySchool(dictSchool);
        model.addAttribute("schoolYearMngs", schoolYearMngs);
        model.addAttribute("dictClasses", dictClasses);
        model.addAttribute("weeklyPlanSection", weeklyPlanSectionService.find(id));
        model.addAttribute("menuId", WeeklyPlanSection.class.getSimpleName());
        return "/console/weekly_plan_section/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(WeeklyPlanSection weeklyPlanSection, Long schoolYearMngId, Long dictClassId,
            RedirectAttributes redirectAttributes, ModelMap model)
    {
        SchoolYearMng schoolYearMng = this.schoolYearMngService.find(schoolYearMngId);
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        DictClass dictClass = dictClassService.find(dictClassId);
        weeklyPlanSection.setSchoolYearMng(schoolYearMng);
        weeklyPlanSection.setDictSchool(dictSchool);
        weeklyPlanSection.setDictClass(dictClass);
        weeklyPlanSection.setIsCurrent(weeklyPlanSectionService.find(weeklyPlanSection.getId()).getIsCurrent());
        
        //如果iscurrent为真，当前学校的其他为假
//        if(weeklyPlanSection.getIsCurrent()){
//            List<WeeklyPlanSection> weeklyPlanSections = weeklyPlanSectionService.findByClass(dictClass);
//            if(weeklyPlanSections!=null&&weeklyPlanSections.size()>0){
//                for(WeeklyPlanSection prePlanSection:weeklyPlanSections){
//                    if(prePlanSection.getIsCurrent()==true){
//                        prePlanSection.setIsCurrent(false);
//                        weeklyPlanSectionService.update(prePlanSection);
//                    }
//                }
//            }
//        }

        List<WeeklyPlanDetail> originalWeeklyPlanDetails = new ArrayList<WeeklyPlanDetail>();
        originalWeeklyPlanDetails.addAll(weeklyPlanSection.getWeeklyPlanDetails());
        if (!isValid(weeklyPlanSection))
        {
            return ERROR_VIEW;
        }
        weeklyPlanSection.getWeeklyPlanDetails().clear();
        weeklyPlanSectionService.update(weeklyPlanSection);
        weeklyPlanDetailService.deleteByWeeklyPlanSection(weeklyPlanSection);
        List<WeeklyPlanDetail> weeklyPlanDetails = buildDetails(originalWeeklyPlanDetails, weeklyPlanSection);
        weeklyPlanDetailService.batchUpdate(weeklyPlanDetails);
        model.addAttribute("menuId", WeeklyPlanSection.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 构建 周计划详情
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    private List<WeeklyPlanDetail> buildDetails(List<WeeklyPlanDetail> originalWeeklyPlanDetails,
            WeeklyPlanSection weeklyPlanSection)
    {
        List<WeeklyPlanDetail> weeklyPlanDetails = new ArrayList<WeeklyPlanDetail>();
        for (WeeklyPlanDetail weeklyPlanDetail : originalWeeklyPlanDetails)
        {
            if (null != weeklyPlanDetail)
            {
                weeklyPlanDetail.setId(null);
                weeklyPlanDetail.setWeeklyPlanSection(weeklyPlanSection);
                weeklyPlanDetails.add(weeklyPlanDetail);
            }
        }
        return weeklyPlanDetails;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        weeklyPlanSectionService.delete(ids);
        return SUCCESS_MESSAGE;
    }

}
