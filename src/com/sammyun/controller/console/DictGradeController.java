package com.sammyun.controller.console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.sammyun.entity.dict.DictStudent.StudentStatus;
import com.sammyun.service.AdminService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictGradeService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.service.dict.DictStudentService;
import com.sammyun.util.JsonUtils;

/**
 * Controller——年级管理 <功能详细描述>
 * 
 * @author melody
 * @version [版本号, 2015年7月31日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("dictGradeController")
@RequestMapping("/console/dict_grade")
public class DictGradeController extends BaseController
{
    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "dictGradeServiceImpl")
    private DictGradeService dictGradeService;

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    @Resource(name = "dictStudentServiceImpl")
    private DictStudentService dictStudentService;

    /**
     * 学校列表
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
        Filter schoolFilter = new Filter("dictSchool", Operator.eq, dictSchool);
        pageable.addFilters(schoolFilter);
        pageable.setOrderProperty("orders");
        model.addAttribute("page", dictGradeService.findPage(pageable));
        model.addAttribute("menuId", DictGrade.class.getSimpleName());
        return "/console/dict_grade/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("menuId", DictGrade.class.getSimpleName());
        return "/console/dict_grade/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(DictGrade dictGrade, RedirectAttributes redirectAttributes, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        dictGrade.setDictSchool(dictSchool);
        dictGrade.setOrder(1);
        if (!isValid(dictGrade))
        {
            return ERROR_VIEW;
        }
        dictGradeService.save(dictGrade);
        model.addAttribute("menuId", DictGrade.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("dictGrade", dictGradeService.find(id));
        model.addAttribute("menuId", DictGrade.class.getSimpleName());
        return "/console/dict_grade/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Long id, String gradeName, RedirectAttributes redirectAttributes, ModelMap model)
    {
        DictGrade dictGrade = dictGradeService.find(id);
        dictGrade.setGradeName(gradeName);
        dictGradeService.update(dictGrade);
        model.addAttribute("menuId", DictGrade.class.getSimpleName());
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
        // TODO
        dictGradeService.delete(ids);
        return SUCCESS_MESSAGE;
    }

    /**
     * 判断标签名是否存在 <功能详细描述>
     * 
     * @param name
     * @param preName
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/checkNameExsit", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkNameExsit(String gradeName, String preName)
    {

        if (preName != null)
        {
            if (StringUtils.equalsIgnoreCase(gradeName.trim(), preName.trim()))
            {
                return true;
            }
        }
        if (StringUtils.isEmpty(gradeName))
        {
            return false;
        }
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<DictGrade> dictGrades = dictGradeService.findByName(gradeName, dictSchool);
        if (dictGrades == null || dictGrades.size() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * 跳转到升级页面
     */
    @RequestMapping(value = "/upgrade", method = RequestMethod.GET)
    public String upgrade(Long id, HttpServletRequest request, HttpServletResponse response, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        DictGrade dictGrade = dictGradeService.find(id);
        List<DictGrade> dictGrades = dictGradeService.findByName(null, dictSchool);
        dictGrades.remove(dictGrade);
        model.addAttribute("dictGrades", dictGrades);
        model.addAttribute("dictClasses", dictGrade.getDictClasses());
        model.addAttribute("dictGrade", dictGrade);
        model.addAttribute("menuId", DictGrade.class.getSimpleName());
        return "/console/dict_grade/upgrade";
    }

    /**
     * ajax弹出改名字的框
     * 
     * @param classIds
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/changeName", method = RequestMethod.GET)
    public void changeName(HttpServletRequest request, HttpServletResponse response, ModelMap model)
    {
        String[] classIds = request.getParameterValues("classIds[]");
        List<DictClass> dictClasses = new ArrayList<DictClass>();
        for (String classId : classIds)
        {
            DictClass dictClass = dictClassService.find(Long.parseLong(classId));
            dictClasses.add(dictClass);
        }
        // model.remove("dictClasses");
        // model.addAttribute("dictClasses", dictClasses);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("dictClasses", dictClasses);

        // System.out.println(classIds);
        try
        {
            // Date endDate = new Date();
            // System.out.println("时间差："+(endDate.getTime()-startDate.getTime()));

            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), data);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 选中的班级升级或者毕业处理
     * 
     * @param dictClassIds
     * @param dictGradeId
     * @param flag 0:升级 1:毕业
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/upgradeSave", method = RequestMethod.POST)
    public String upgradeSave(Long[] dictClassIds, Long dictGradeId, Integer flag, HttpServletRequest request,
            ModelMap model, RedirectAttributes redirectAttributes)
    {

        List<DictStudent> dictStudents = new LinkedList<DictStudent>();
        DictGrade dictGrade = dictGradeService.find(dictGradeId);
        List<DictClass> dictClasses = dictClassService.findList(dictClassIds);

        if (flag == 0)
        {
            for (DictClass dictClass : dictClasses)
            {
                dictClass.setDictGrade(dictGrade);
            }
        }
        if (flag == 1)
        {
            for (DictClass dictClass : dictClasses)
            {
                dictClass.setClassStatus(ClassStatus.graduated);
                dictStudents.addAll(dictClass.getDictStudents());
            }
            for (DictStudent dictStudent : dictStudents)
            {
                dictStudent.setStudentStatus(StudentStatus.graduated);
            }
        }
        dictStudentService.batchUpdate(dictStudents);
        dictClassService.batchUpdate(dictClasses);
        editDictClassName(request);
        model.addAttribute("menuId", DictGrade.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);

        return "redirect:list.ct";
    }

    /**
     * 修改班级名称
     */
    // @RequestMapping(value = "/editDictClassName", method =
    // RequestMethod.POST)
    public void editDictClassName(HttpServletRequest request)
    {
        String[] dictClassIds = request.getParameterValues("dictClassIds");
        if (dictClassIds != null && dictClassIds.length > 0)
        {
            for (String dictClassId : dictClassIds)
            {
                DictClass dictClass = dictClassService.find(Long.parseLong(dictClassId));
                String name = request.getParameter(dictClassId);
                dictClass.setName(name);
                dictClassService.update(dictClass);
            }
        }
    }

    @RequestMapping(value = "/changeOrders", method = RequestMethod.POST)
    public @ResponseBody
    Message changeOrders(Long currentId, Long changeId)
    {
        DictGrade currentDictGrade = dictGradeService.find(currentId);
        DictGrade changeDictGrade = dictGradeService.find(changeId);
        // Integer currentOrder = currentProductCategory.getOrder();
        // Integer changeOrder = changeProductCategory.getOrder();
        // if(currentOrder!=null&&changeOrder!=null&currentOrder!=
        // changeOrder){
        // currentProductCategory.setOrder(changeOrder);
        // productColumnService.save(currentProductCategory);
        // changeProductCategory.setOrder(currentOrder);
        // productColumnService.save(changeProductCategory);
        // }
        // else{
        List<DictGrade> dictGradeColumn = dictGradeService.findAll();
        for (Integer i = 0; i < dictGradeColumn.size(); i++)
        {
            // Integer newOrder = i;
            if (dictGradeColumn.get(i).getOrder().equals(i))
            {
                continue;
            }
            else
            {
                dictGradeColumn.get(i).setOrder(i);
                dictGradeService.save(dictGradeColumn.get(i));
            }
        }
        Integer currentOrder = dictGradeService.find(currentId).getOrder();
        Integer changeOrder = dictGradeService.find(changeId).getOrder();
        currentDictGrade.setOrder(changeOrder);
        dictGradeService.save(currentDictGrade);
        changeDictGrade.setOrder(currentOrder);
        dictGradeService.save(changeDictGrade);
        // }
        return SUCCESS_MESSAGE;
    }

    /**
     * ajax判断升级到的班级名称是否已经存在
     * 
     * @param className
     * @param response
     */
    @RequestMapping(value = "/isExistCName", method = RequestMethod.GET)
    public void isExistCName(String className, HttpServletResponse response)
    {
        Boolean isExist = false;
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        if (StringUtils.isEmpty(className))
        {
            isExist = false;
        }
        else
        {
            if (dictClassService.classNameExists(className.trim(), dictSchool))
            {
                isExist = true;
            }
            else
            {
                isExist = false;
            }
        }
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), isExist);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
