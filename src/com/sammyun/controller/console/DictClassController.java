package com.sammyun.controller.console;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictClass.ClassStatus;
import com.sammyun.entity.dict.DictGrade;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.service.AdminService;
import com.sammyun.service.MemberService;
import com.sammyun.service.StaticService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictGradeService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.service.dict.DictStudentService;
import com.sammyun.util.EduCodeUtil;

/**
 * Controller - 班级管理
 * 
 * @author xutianlong
 * @version [版本号, Apr 1, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("dictClassController")
@RequestMapping("/console/dict_class")
public class DictClassController extends BaseController
{

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "staticServiceImpl")
    private StaticService staticService;

    @Resource(name = "dictStudentServiceImpl")
    private DictStudentService dictStudentService;

    @Resource(name = "dictGradeServiceImpl")
    private DictGradeService dictGradeService;

    /**
     * 班级列表
     * 
     * @param pageable
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ClassStatus classStatus, Pageable pageable, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        Filter filter = new Filter("dictSchool", Operator.eq, dictSchool);
        pageable.addFilters(filter);
        Filter statusFilter = new Filter();
        statusFilter.setProperty("classStatus");
        statusFilter.setOperator(Operator.eq);
        if (classStatus != null && classStatus == ClassStatus.graduated)
        {
            statusFilter.setValue(ClassStatus.graduated);
        }
        else
        {
            statusFilter.setValue(ClassStatus.active);
        }
        pageable.addFilters(statusFilter);
        model.addAttribute("classStatus", classStatus);
        model.addAttribute("page", dictClassService.findPage(pageable));
        model.addAttribute("menuId", DictClass.class.getSimpleName());
        return "/console/dict_class/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        EduCodeUtil.generateClassCode(null);
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<DictClass> dictClasses = dictClassService.getClassesBySchool(dictSchool);
        String classCode = EduCodeUtil.generateClassCode(dictClasses);
        model.addAttribute("classCode", classCode);
        model.addAttribute("dictGrades", dictSchool.getDictGrades());
        model.addAttribute("menuId", DictClass.class.getSimpleName());
        return "/console/dict_class/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Long dictGradeId, DictClass dictClass, RedirectAttributes redirectAttributes, ModelMap model)
    {
        DictGrade dictGrade = dictGradeService.find(dictGradeId);
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        dictClass.setDictSchool(dictSchool);
        dictClass.setDictGrade(dictGrade);
        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }
        dictClassService.save(dictClass);
        model.addAttribute("menuId", DictClass.class.getSimpleName());
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
        model.addAttribute("dictGrades", dictSchool.getDictGrades());
        model.addAttribute("dictClass", dictClassService.find(id));
        model.addAttribute("menuId", DictClass.class.getSimpleName());
        return "/console/dict_class/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Long dictGradeId, DictClass dictClass, RedirectAttributes redirectAttributes, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        DictGrade dictGrade = dictGradeService.find(dictGradeId);
        dictClass.setDictSchool(dictSchool);
        dictClass.setDictGrade(dictGrade);
        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }
        dictClassService.update(dictClass);
        model.addAttribute("menuId", DictClass.class.getSimpleName());
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
        for (long id : ids)
        {
            DictClass dictClass = dictClassService.find(id);
            Set<DictStudent> dictStudents = dictClass.getDictStudents();
            if (dictStudents.size() > 0)
            {
                String className = dictClass.getName();
                String errMessage = className + "下有数据，不可删除！";
                return Message.error(errMessage);
            }
        }
        dictClassService.delete(ids);
        return SUCCESS_MESSAGE;
    }

    /**
     * 验证班级编号重复 <功能详细描述>
     * 
     * @param username
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/check_code", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkCode(String code)
    {

        DictSchool dictSchool = adminService.getCurrentDictSchool();
        if (StringUtils.isEmpty(code))
        {
            return false;
        }
        if (dictClassService.codeExists(code, dictSchool))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @RequestMapping(value = "/edit_check_code", method = RequestMethod.GET)
    public @ResponseBody
    boolean editCheckCode(String code, String previousCode)
    {
        if (StringUtils.equalsIgnoreCase(code, previousCode))
        {
            return true;
        }
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        if (StringUtils.isEmpty(code))
        {
            return false;
        }
        if (dictClassService.codeExists(code, dictSchool))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 班级批量生成静态毕业纪念册
     */
    @RequestMapping(value = "/batchStatic", method = RequestMethod.GET)
    public @ResponseBody
    Message build(Long id, HttpServletRequest request, HttpServletResponse response)
    {
        DictClass dictClass = dictClassService.find(id);
        Set<DictStudent> dictStudents = dictClass.getDictStudents();
        for (DictStudent dictStudent : dictStudents)
        {
            staticService.build(dictStudent);
        }
        response.setContentType("text/html; charset=UTF-8");
        return SUCCESS_MESSAGE;
    }

}
