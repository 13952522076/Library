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
import com.sammyun.entity.attendance.WorkSetting;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.attendance.WorkSettingService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.DateUtil;

/**
 * Controller - 班次管理
 * 
 * @author xutianlong
 * @version [版本号, Apr 1, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("workSettingController")
@RequestMapping("/console/workSetting")
public class WorkSettingController extends BaseController
{
    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "workSettingServiceImpl")
    private WorkSettingService workSettingService;
    
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

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
        Filter filter = new Filter("dictSchool", Operator.eq, dictSchool);
        pageable.addFilters(filter);
        model.addAttribute("page", workSettingService.findPage(pageable));
        model.addAttribute("menuId", WorkSetting.class.getSimpleName());
        return "/console/workSetting/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("menuId", WorkSetting.class.getSimpleName());
        return "/console/workSetting/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(HttpServletRequest request,WorkSetting workSetting, RedirectAttributes redirectAttributes, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        workSetting.setDictSchool(dictSchool);
        if (!isValid(workSetting))
        {
            return ERROR_VIEW;
        }
        workSettingService.save(workSetting);
        model.addAttribute("menuId", WorkSetting.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("workSetting", workSettingService.find(id));
        model.addAttribute("menuId", WorkSetting.class.getSimpleName());
        return "/console/workSetting/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(HttpServletRequest request,WorkSetting workSetting, RedirectAttributes redirectAttributes, ModelMap model)
    {
        workSetting.setCreateDate(workSettingService.find(workSetting.getId()).getCreateDate());
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        workSetting.setDictSchool(dictSchool);
        if (!isValid(workSetting))
        {
            return ERROR_VIEW;
        }
        workSettingService.update(workSetting);
        model.addAttribute("menuId", WorkSetting.class.getSimpleName());
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
        workSettingService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
