package com.sammyun.controller.console;

import java.util.Set;

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
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.stu.MeritTemplate;
import com.sammyun.entity.stu.OverallMerit;
import com.sammyun.service.AdminService;
import com.sammyun.service.stu.MeritTemplateService;

/**
 * Controller - 评价等级模板
 * 
 * @author xutianlong
 * @version [版本号, Apr 1, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("meritTemplateController")
@RequestMapping("/console/merit_template")
public class MeritTemplateController extends BaseController
{
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;
    
    @Resource(name = "meritTemplateServiceImpl")
    private MeritTemplateService meritTemplateService;
    
    /**
     * 评价等级模块列表
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
        model.addAttribute("page", meritTemplateService.findPage(pageable));
        model.addAttribute("menuId", MeritTemplate.class.getSimpleName());
        return "/console/merit_template/list";
    }
    
    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("menuId", MeritTemplate.class.getSimpleName());
        return "/console/merit_template/add";
    }
    
    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(MeritTemplate meritTemplate, RedirectAttributes redirectAttributes, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        meritTemplate.setDictSchool(dictSchool);
        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }
        meritTemplateService.save(meritTemplate);
        model.addAttribute("menuId", MeritTemplate.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }
    
    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("meritTemplate", meritTemplateService.find(id));
        model.addAttribute("menuId", MeritTemplate.class.getSimpleName());
        return "/console/merit_template/edit";
    }
    
    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(MeritTemplate meritTemplate, RedirectAttributes redirectAttributes, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        meritTemplate.setDictSchool(dictSchool);
        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }
        meritTemplateService.update(meritTemplate);
        model.addAttribute("menuId", MeritTemplate.class.getSimpleName());
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
            MeritTemplate meritTemplate = meritTemplateService.find(id);
            Set<OverallMerit> overallMerits = meritTemplate.getOverallMerits();
            if (overallMerits.size() > 0)
            {
                String errMessage = "所选记录下有相关数据，不可删除！";
                return Message.error(errMessage);
            }
        }
        meritTemplateService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
