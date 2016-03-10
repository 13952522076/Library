package com.sammyun.controller.console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Message;
import com.sammyun.Order;
import com.sammyun.Pageable;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.stu.MeritTemplate;
import com.sammyun.entity.stu.OverallMerit;
import com.sammyun.service.AdminService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictStudentService;
import com.sammyun.service.stu.MeritTemplateService;
import com.sammyun.service.stu.OverallMeritService;
import com.sammyun.util.EduUtil;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 综合评价
 * 
 * @author xutianlong
 * @version [版本号, Apr 1, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("overallMeritController")
@RequestMapping("/console/overall_merit")
public class OverallMeritController extends BaseController
{
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(OverallMeritController.class);

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "overallMeritServiceImpl")
    private OverallMeritService overallMeritService;

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    @Resource(name = "meritTemplateServiceImpl")
    private MeritTemplateService meritTemplateService;

    @Resource(name = "dictStudentServiceImpl")
    private DictStudentService dictStudentService;

    /**
     * 综合评价列表
     * 
     * @param pageable
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        pageable.setOrders(orders);

        DictSchool dictSchool = adminService.getCurrentDictSchool();

        model.addAttribute("page", overallMeritService.findBySchool(dictSchool, pageable));
        model.addAttribute("menuId", OverallMerit.class.getSimpleName());
        return "/console/overall_merit/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<DictClass> dictClasses = dictClassService.getClassesBySchool(dictSchool);
        List<MeritTemplate> meritTemplates = meritTemplateService.findBySchool(dictSchool);
        model.addAttribute("dictClasses", dictClasses);
        model.addAttribute("meritTemplates", meritTemplates);
        model.addAttribute("menuId", OverallMerit.class.getSimpleName());
        return "/console/overall_merit/add";
    }

    /**
     * ajax获取学生 <功能详细描述>
     * 
     * @param dictClassId
     * @param response
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getStudents", method = RequestMethod.GET)
    public void getStudents(Long dictClassId, HttpServletResponse response)
    {
        DictClass dictClass = dictClassService.find(dictClassId);
        Set<DictStudent> students = dictClass.getDictStudents();
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), students);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    /**
     * ajax校验学生 - 新增页面
     * 
     * @param dictStudentId
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/checkStudent", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkStudent(Long dictStudentId)
    {
        DictStudent dictStudent = dictStudentService.find(dictStudentId);
        if (dictStudent == null)
        {
            return true;
        }
        List<OverallMerit> overallMerits = overallMeritService.findByDictStudent(dictStudent);
        if (overallMerits == null || overallMerits.size() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * ajax校验学生 - 编辑页面
     * 
     * @param dictStudentId
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/checkStudentForEdit", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkStudentForEdit(Long preDictStudentId, Long dictStudentId)
    {
        if (preDictStudentId.equals(dictStudentId))
        {
            return true;
        }
        DictStudent dictStudent = dictStudentService.find(dictStudentId);
        if (dictStudent == null)
        {
            return true;
        }
        List<OverallMerit> overallMerits = overallMeritService.findByDictStudent(dictStudent);
        if (overallMerits == null || overallMerits.size() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Long dictStudentId, DictStudent tempDictStudernt, RedirectAttributes redirectAttributes,
            ModelMap model)
    {
        DictStudent dictStudent = dictStudentService.find(dictStudentId);
        List<OverallMerit> overallMerits = tempDictStudernt.getOverallMerits();
        if (overallMerits == null || overallMerits.size() == 0)
        {
            return ERROR_VIEW;
        }
        for (OverallMerit overallMerit : overallMerits)
        {
            overallMerit.setDictStudent(dictStudent);
            Long id = overallMerit.getMeritTemplate().getId();
            MeritTemplate meritTemplate = meritTemplateService.find(id);
            overallMerit.setMeritTemplate(meritTemplate);
        }
        overallMeritService.batchUpdate(overallMerits);
        model.addAttribute("menuId", OverallMerit.class.getSimpleName());
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
        List<DictClass> dictClasses = dictClassService.getClassesBySchool(dictSchool);
        List<MeritTemplate> meritTemplates = meritTemplateService.findBySchool(dictSchool);
        List<MeritTemplate> tempMeritTemplates = new LinkedList<MeritTemplate>();
        OverallMerit overallMerit = overallMeritService.find(id);
        for (OverallMerit tempOverallMerit : overallMerit.getDictStudent().getOverallMerits())
        {
            tempMeritTemplates.add(tempOverallMerit.getMeritTemplate());
        }
        model.addAttribute("dictClasses", dictClasses);
        model.addAttribute("meritTemplates", meritTemplates);
        model.addAttribute("tempMeritTemplates", tempMeritTemplates);
        model.addAttribute("overallMerit", overallMerit);
        model.addAttribute("menuId", OverallMerit.class.getSimpleName());
        return "/console/overall_merit/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Long dictStudentId, DictStudent tempDictStudernt, RedirectAttributes redirectAttributes,
            ModelMap model)
    {

        DictStudent dictStudent = dictStudentService.find(dictStudentId);
        List<OverallMerit> overallMerits = tempDictStudernt.getOverallMerits();
        if (overallMerits == null || overallMerits.size() == 0)
        {
            return ERROR_VIEW;
        }
        overallMeritService.deleteByDictStudent(dictStudentService.find(dictStudentId));
        List<OverallMerit> tempOverallMerits = new ArrayList<OverallMerit>();
        for (OverallMerit overallMerit : overallMerits)
        {
            if (null != overallMerit && EduUtil.isNotEmpty(overallMerit.getMeritTemplate()))
            {
                overallMerit.setId(null);
                overallMerit.setDictStudent(dictStudent);
                Long id = overallMerit.getMeritTemplate().getId();
                MeritTemplate meritTemplate = meritTemplateService.find(id);
                overallMerit.setMeritTemplate(meritTemplate);
                tempOverallMerits.add(overallMerit);
            }
        }
        overallMeritService.batchUpdate(tempOverallMerits);
        model.addAttribute("menuId", OverallMerit.class.getSimpleName());
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
        overallMeritService.delete(ids);
        return SUCCESS_MESSAGE;
    }

}
