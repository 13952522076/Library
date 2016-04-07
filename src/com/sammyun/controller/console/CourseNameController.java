package com.sammyun.controller.console;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.entity.Admin;
import com.sammyun.entity.course.CourseName;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.course.CourseNameService;
import com.sammyun.service.dict.DictSchoolService;

/**
 * Controller - 课程
 */
@Controller("courseNameController")
@RequestMapping("/console/course_name")
public class CourseNameController extends BaseController
{
    @Resource(name = "courseNameServiceImpl")
    private CourseNameService courseNameService;

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    /**
     * 班级列表
     * 
     * @param pageable
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", courseNameService.findPage(pageable));
        model.addAttribute("menuId", CourseName.class.getSimpleName());
        return "/console/course_name/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        Admin admin = adminService.getCurrent();
        List<DictSchool> dictSchools = dictSchoolService.findAll();
        model.addAttribute("dictSchools", dictSchools);
        model.addAttribute("admin", admin);
        model.addAttribute("menuId", CourseName.class.getSimpleName());
        return "/console/course_name/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(CourseName courseName, Long dictSchoolId, RedirectAttributes redirectAttributes)
    {
        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        courseName.setDictSchool(dictSchool);
        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }
        courseNameService.save(courseName);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        Admin admin = adminService.getCurrent();
        List<DictSchool> dictSchools = dictSchoolService.findAll();
        model.addAttribute("admin", admin);
        model.addAttribute("dictSchools", dictSchools);
        model.addAttribute("courseName", courseNameService.find(id));
        return "/console/course_name/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(CourseName courseName, Long dictSchoolId, RedirectAttributes redirectAttributes)
    {
        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        courseName.setDictSchool(dictSchool);
        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }
        courseNameService.update(courseName);
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
        courseNameService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
