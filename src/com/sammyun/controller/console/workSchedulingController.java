package com.sammyun.controller.console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.WorkScheduling;
import com.sammyun.entity.attendance.WorkScheduling.SchedulingWay;
import com.sammyun.entity.attendance.WorkSetting;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.MemberService;
import com.sammyun.service.attendance.WorkSchedulingService;
import com.sammyun.service.attendance.WorkSettingService;
import com.sammyun.service.dict.DictSchoolService;

/**
 * Controller - 排版管理
 * 
 * @author xutianlong
 * @version [版本号, Apr 1, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("workSchedulingController")
@RequestMapping("/console/workScheduling")
public class workSchedulingController extends BaseController
{
    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "workSettingServiceImpl")
    private WorkSettingService workSettingService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "workSchedulingServiceImpl")
    private WorkSchedulingService workSchedulingService;

    /** 考勤状态 */
    public enum weekday
    {
        /** 周一 */
        monday,
        /** 周二 */
        tuesday,
        /** 周三 */
        wednesday,
        /** 周四 */
        thursday,
        /** 周五 */
        friday,
        /** 周六 */
        saturday,
        /** 周日 */
        sunday
    }

    /**
     * 班次列表
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
        List<Member> teachers = memberService.findActiveTeachersBySchool(dictSchool);
        if (teachers != null && teachers.size() > 0)
        {
            Filter filter = new Filter("member", Operator.in, teachers);
            pageable.addFilters(filter);
        }
        else
        {
            Filter filter = new Filter("id", Operator.eq, 0L);
            pageable.addFilters(filter);
        }
        //为了列表抓key
        Map<Long, String> bigTimeMap = new HashMap<Long,String>();
        List<WorkScheduling> workSchedulings = workSchedulingService.findPage(pageable).getContent();
        for(WorkScheduling workScheduling:workSchedulings){
            Set<String> keys = workScheduling.getSchedulingAttributes().keySet();
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
                    key = "周一";
                }
                if(key.equals("tuesday")){
                    key = "周二";
                }
                if(key.equals("wednesday")){
                    key = "周三";
                }
                if(key.equals("thursday")){
                    key = "周四";
                }
                if(key.equals("friday")){
                    key = "周五";
                }
                if(key.equals("saturday")){
                    key = "周六";
                }
                if(key.equals("sunday")){
                    key = "周日";
                }
                
                times = times+key+",";
            }
            if(times.length()>0){
                times=times.substring(0,times.length()-1);
            }
            bigTimeMap.put(workScheduling.getMember().getId(), times);
        }
        model.addAttribute("bigTimeMap", bigTimeMap);
        model.addAttribute("page", workSchedulingService.findPage(pageable));
        model.addAttribute("menuId", WorkScheduling.class.getSimpleName());
        return "/console/workScheduling/list";
    }

//    /**
//     * 添加
//     */
//    @RequestMapping(value = "/add", method = RequestMethod.GET)
//    public String add(ModelMap model)
//    {
//        // 获得没有班排过的老师
//        DictSchool dictSchool = adminService.getCurrentDictSchool();
//        List<Member> members = memberService.findActiveTeachersBySchool(dictSchool);
//        List<Member> addedMembers = workSchedulingService.findMembers();
//        if (members != null)
//        {
//            members.removeAll(addedMembers);
//        }
//        model.addAttribute("members", members);
//        // 获得当前学校的班次
//        List<WorkSetting> workSettings = workSettingService.findBySchool(dictSchool);
//        model.addAttribute("workSettings", workSettings);
//        model.addAttribute("menuId", WorkScheduling.class.getSimpleName());
//        return "/console/workScheduling/add";
//    }
    
    /**
     * 添加2
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        // 获得没有班排过的老师
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<Member> members = memberService.findActiveTeachersBySchool(dictSchool);
        List<Member> addedMembers = workSchedulingService.findMembers();
        if (members != null)
        {
            members.removeAll(addedMembers);
        }
        model.addAttribute("members", members);
        // 获得当前学校的班次
        List<WorkSetting> workSettings = workSettingService.findBySchool(dictSchool);
        model.addAttribute("workSettings", workSettings);
        model.addAttribute("menuId", WorkScheduling.class.getSimpleName());
        return "/console/workScheduling/add2";
    }

//    /**
//     * 保存
//     */
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String save(Long[] memberIds, Long workSettingId, String[] weeks, String layerDates, String schedulingWay,
//            RedirectAttributes redirectAttributes, ModelMap model)
//    {
//        WorkSetting workSetting = workSettingService.find(workSettingId);
//        for (Long memberId : memberIds)
//        {
//            Member member = memberService.find(memberId);
//            Map<String, WorkSetting> schedulingAttributes = new HashMap<String, WorkSetting>();
//            WorkScheduling workScheduling = new WorkScheduling();
//            if (schedulingWay.equals(SchedulingWay.week.toString()))
//            {
//                for(String week:weeks){
//                    schedulingAttributes.put(week, workSetting);
//                }
//            }else{
//                String[] dates = layerDates.split(",");
//                for(String layerDate:dates){
//                    schedulingAttributes.put(layerDate, workSetting);
//                }
//            }
//            workScheduling.setMember(member);
//            workScheduling.setSchedulingAttributes(schedulingAttributes);
//            workSchedulingService.save(workScheduling);
//
//        }
//        model.addAttribute("menuId", WorkScheduling.class.getSimpleName());
//        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
//        return "redirect:list.ct";
//    }
    
    
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(String schedulingWay,HttpServletRequest request,Long[] memberIds,
            RedirectAttributes redirectAttributes, ModelMap model)
    {
        // 获得没有班排过的老师
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        // 获得当前学校的班次
        List<WorkSetting> workSettings = workSettingService.findBySchool(dictSchool);
        for(Long memberId:memberIds){
            Member member = memberService.find(memberId);
            WorkScheduling workScheduling = new WorkScheduling();
            workScheduling.setMember(member);
            Map<String,WorkSetting> schedulingAttributes = new HashMap<String,WorkSetting>();
            if(schedulingWay.equals(SchedulingWay.week.toString())){
                workScheduling.setSchedulingWay(SchedulingWay.week);
                for(WorkSetting workSetting:workSettings){
                    String settingName = workSetting.getName()+"weeks";
                    String[] weeks =  request.getParameterValues(settingName);
                    if(weeks!=null && weeks.length>0){
                        for(String week:weeks){
                            schedulingAttributes.put(week, workSetting);
                        }
                    }
                }
            }else {
                workScheduling.setSchedulingWay(SchedulingWay.date);
                for(WorkSetting workSetting:workSettings){
                    String settingName = workSetting.getName()+"layerDates";
                    String dateString =  request.getParameter(settingName);
                    if(dateString==null){
                     continue;
                    }
                    String[] dates =  dateString.split(",");
                    if(dates==null||dates.length==0){
                        continue;
                    }
                    for(String date:dates){
                        if(date!=null&&!date.equals("")){
                            schedulingAttributes.put(date, workSetting);
                        }
                    }
                }
            }
            if(schedulingAttributes.isEmpty()){
                model.addAttribute("menuId", WorkScheduling.class.getSimpleName());
                addFlashMessage(redirectAttributes, Message.error("未选择当前方式排班"));
                return "redirect:add.ct";
            }
            workScheduling.setSchedulingAttributes(schedulingAttributes);
            workSchedulingService.save(workScheduling);
        }
        model.addAttribute("menuId", WorkScheduling.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        // 获得没有班排过的老师
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<Member> members = memberService.findActiveTeachersBySchool(dictSchool);
        List<Member> addedMembers = workSchedulingService.findMembers();
        members.removeAll(addedMembers);
        members.add(workSchedulingService.find(id).getMember());
        model.addAttribute("members", members);
        // 获得当前学校的班次
        List<WorkSetting> workSettings = workSettingService.findBySchool(dictSchool);
        model.addAttribute("workSettings", workSettings);
        Map<String, WorkSetting> attributes = workSchedulingService.find(id).getSchedulingAttributes();
        model.addAttribute("attributes", attributes);
        model.addAttribute("workScheduling", workSchedulingService.find(id));
        model.addAttribute("menuId", WorkScheduling.class.getSimpleName());
        return "/console/workScheduling/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Long id,String schedulingWay,HttpServletRequest request,
            RedirectAttributes redirectAttributes, ModelMap model)
    {
        // 获得没有班排过的老师
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        // 获得当前学校的班次
        List<WorkSetting> workSettings = workSettingService.findBySchool(dictSchool);
        
        WorkScheduling workScheduling = workSchedulingService.find(id);
        Map<String,WorkSetting> schedulingAttributes = new HashMap<String,WorkSetting>();
        if(schedulingWay.equals(SchedulingWay.week.toString())){
            workScheduling.setSchedulingWay(SchedulingWay.week);
            for(WorkSetting workSetting:workSettings){
                String settingName = workSetting.getName()+"weeks";
                String[] weeks =  request.getParameterValues(settingName);
                if(weeks!=null && weeks.length>0){
                    for(String week:weeks){
                        schedulingAttributes.put(week, workSetting);
                    }
                }
            }
        }else {
            workScheduling.setSchedulingWay(SchedulingWay.date);
            for(WorkSetting workSetting:workSettings){
                String settingName = workSetting.getName()+"layerDates";
                String dateString =  request.getParameter(settingName);
                if(dateString==null){
                 continue;
                }
                String[] dates =  dateString.split(",");
                if(dates==null||dates.length==0){
                    continue;
                }
                for(String date:dates){
                    if(date!=null&&!date.equals("")){
                        schedulingAttributes.put(date, workSetting);
                    }
                }
            }
        }
        if(schedulingAttributes.isEmpty()){
            model.addAttribute("menuId", WorkScheduling.class.getSimpleName());
            addFlashMessage(redirectAttributes, Message.error("未选择当前方式排班"));
            return "redirect:add.ct";
        }
        workScheduling.setSchedulingAttributes(schedulingAttributes);
        workSchedulingService.update(workScheduling);
        model.addAttribute("menuId", WorkScheduling.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        workSchedulingService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
