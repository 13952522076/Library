package com.sammyun.controller.console;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Order;
import com.sammyun.entity.Admin;
import com.sammyun.entity.course.CurriculumSchedule;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.course.CurriculumScheduleService;
import com.sammyun.service.course.SchoolYearMngService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 课程表controller
 * 
 * @author Sencloud Team

 */
@Controller("curriculumScheduleController")
@RequestMapping("/console/curriculum_schedule")
public class CurriculumScheduleController extends BaseController
{
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

    /** 管理员service */
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    /** 学校service */
    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    /** 班级service */
    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    /** 校历service */
    @Resource(name = "schoolYearMngServiceImpl")
    private SchoolYearMngService schoolYearMngService;

    /** 课程表service */
    @Resource(name = "curriculumScheduleServiceImpl")
    private CurriculumScheduleService curriculumScheduleService;

    /**
     * 课程表管理页面 <功能详细描述>
     * 
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model)
    {
        Admin admin = adminService.getCurrent();
        DictSchool dictSchool = admin.getDictSchool();
        List<SchoolYearMng> schoolYearMngs = schoolYearMngService.findBySchool(dictSchool);
        Set<DictClass> classes = dictSchool.getDictClasses();
        model.addAttribute("classes", classes);
        model.addAttribute("schoolYearMngs", schoolYearMngs);
        model.addAttribute("menuId", CurriculumSchedule.class.getSimpleName());
        return "/console/course/list";
    };

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        Admin admin = adminService.getCurrent();
        DictSchool dictSchool = admin.getDictSchool();
        List<SchoolYearMng> schoolYearMngs = schoolYearMngService.findBySchool(dictSchool);
        Set<DictClass> classes = dictSchool.getDictClasses();
        model.addAttribute("classes", classes);
        model.addAttribute("schoolYearMngs", schoolYearMngs);
        model.addAttribute("menuId", CurriculumSchedule.class.getSimpleName());
        return "/console/course/add";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap model, long id)
    {
        CurriculumSchedule course = curriculumScheduleService.find(id);
        Admin admin = adminService.getCurrent();
        DictSchool dictSchool = admin.getDictSchool();
        List<SchoolYearMng> schoolYearMngs = schoolYearMngService.findBySchool(dictSchool);
        Set<DictClass> classes = dictSchool.getDictClasses();
        model.addAttribute("course", course);
        model.addAttribute("classes", classes);
        model.addAttribute("schoolYearMngs", schoolYearMngs);
        model.addAttribute("menuId", CurriculumSchedule.class.getSimpleName());
        return "/console/course/edit";
    }

    /**
     * ajax请求课程表表格数据 <功能详细描述>
     * 
     * @param model
     * @param schoolYearMngId
     * @param classId
     * @param response
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public void detail(ModelMap model, Long schoolYearMngId, Long classId, HttpServletResponse response)
    {
        SchoolYearMng schoolYearMng = schoolYearMngService.find(schoolYearMngId);
        DictClass dictClass = dictClassService.find(classId);
        List<Order> orders = null;
        List<CurriculumSchedule> courses = curriculumScheduleService.findByClassAndSchoolYear(schoolYearMng, dictClass,orders);
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), courses);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    /**
     * 保存 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ModelMap model, Long schoolYearMngId, Long classId, CurriculumSchedule curriculumSchedule,
            RedirectAttributes redirectAttributes)
    {
        SchoolYearMng schoolYearMng = schoolYearMngService.find(schoolYearMngId);
        DictClass dictClass = dictClassService.find(classId);
        curriculumSchedule.setSchoolYearMng(schoolYearMng);
        curriculumSchedule.setDictClass(dictClass);
        curriculumScheduleService.save(curriculumSchedule);
        model.addAttribute("menuId", CurriculumSchedule.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 更新 <功能详细描述>
     * 
     * @param model
     * @param schoolYearMngId
     * @param classId
     * @param curriculumSchedule
     * @param redirectAttributes
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap model, Long schoolYearMngId, Long classId, CurriculumSchedule curriculumSchedule,
            RedirectAttributes redirectAttributes)
    {
        SchoolYearMng schoolYearMng = schoolYearMngService.find(schoolYearMngId);
        DictClass dictClass = dictClassService.find(classId);
        curriculumSchedule.setSchoolYearMng(schoolYearMng);
        curriculumSchedule.setDictClass(dictClass);
        curriculumScheduleService.update(curriculumSchedule);
        model.addAttribute("menuId", CurriculumSchedule.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 验证是否存在课程
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/courseExsit", method = RequestMethod.GET)
    public void courseExsit(HttpServletResponse response,long schoolYearMngId,long classId,int week,String lessons){
        SchoolYearMng schoolYearMng = schoolYearMngService.find(schoolYearMngId);
        DictClass dictClass = dictClassService.find(classId);
        Boolean courseExsit = curriculumScheduleService.courseExsit(schoolYearMng,dictClass,week,lessons); 
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), courseExsit);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
       
        
    }
    
}
