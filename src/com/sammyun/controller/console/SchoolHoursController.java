package com.sammyun.controller.console;

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
import com.sammyun.entity.attendance.SchoolHours;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.attendance.SchoolHoursService;

/**
 * Controller - 上学放学时间设置
 * 
 * @author xutianlong
 * @version [版本号, Apr 1, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("schoolHoursController")
@RequestMapping("/console/school_hours")
public class SchoolHoursController extends BaseController
{
    @Resource(name = "schoolHoursServiceImpl")
    private SchoolHoursService schoolHoursService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    /**
     * 上学放学列表
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
        model.addAttribute("page", schoolHoursService.findPage(pageable));
        model.addAttribute("menuId", SchoolHours.class.getSimpleName());
        return "/console/school_hours/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Long total, ModelMap model, RedirectAttributes redirectAttributes)
    {
        model.addAttribute("menuId", SchoolHours.class.getSimpleName());
        if (total == 0)
        {
            return "/console/school_hours/add";
        }
        else
        {
            addFlashMessage(redirectAttributes, Message.error("当前学校已经存在上学放学数据，请编辑或者删除后再新增。"));
            return "redirect:list.ct";
        }
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request, SchoolHours schoolHours, RedirectAttributes redirectAttributes,
            ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        schoolHours.setDictSchool(dictSchool);
        if (!isValid(schoolHours))
        {
            return ERROR_VIEW;
        }
        schoolHoursService.save(schoolHours);
        model.addAttribute("menuId", SchoolHours.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        SchoolHours schoolHours = schoolHoursService.find(id);
        model.addAttribute("schoolHours", schoolHours);
        model.addAttribute("menuId", SchoolHours.class.getSimpleName());
        return "/console/school_hours/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpServletRequest request, SchoolHours schoolHours, RedirectAttributes redirectAttributes,
            ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        schoolHours.setDictSchool(dictSchool);
        if (!isValid(schoolHours))
        {
            return ERROR_VIEW;
        }
        schoolHoursService.update(schoolHours);
        model.addAttribute("menuId", SchoolHours.class.getSimpleName());
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
        schoolHoursService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
