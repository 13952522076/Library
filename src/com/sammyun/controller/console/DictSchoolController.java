package com.sammyun.controller.console;

import java.util.List;

import javax.annotation.Resource;

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
import com.sammyun.entity.Admin;
import com.sammyun.entity.Area;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.AreaService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.EduCodeUtil;

/**
 * Controller - 学校管理
 * 
 * @author xutianlong
 * @version [版本号, Apr 1, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("dictSchoolController")
@RequestMapping("/console/dict_school")
public class DictSchoolController extends BaseController
{
    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "areaServiceImpl")
    private AreaService areaService;
    
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

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
        Admin admin = adminService.getCurrent();
        // 校园管理员只能看到自己学校的，超级管理员可以看到所有的
        model.addAttribute("page", dictSchoolService.findPage(pageable));
        model.addAttribute("menuId", DictSchool.class.getSimpleName());
        return "/console/dict_school/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        List<DictSchool> dictSchools = dictSchoolService.findAll();
        String schoolCode = EduCodeUtil.generateSchoolCode(dictSchools);
        model.addAttribute("schoolCode", DictSchool.class.getSimpleName());
        model.addAttribute("menuId", DictSchool.class.getSimpleName());
        return "/console/dict_school/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(DictSchool dictSchool, Long areaId, RedirectAttributes redirectAttributes, ModelMap model)
    {
        Area area = areaService.find(areaId);
        dictSchool.setArea(area);
        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }
        dictSchoolService.save(dictSchool);
        model.addAttribute("menuId", DictSchool.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("dictSchool", dictSchoolService.find(id));
        model.addAttribute("menuId", DictSchool.class.getSimpleName());
        return "/console/dict_school/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(DictSchool dictSchool, Long areaId, RedirectAttributes redirectAttributes, ModelMap model)
    {
        Area area = areaService.find(areaId);
        dictSchool.setArea(area);
        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }
        dictSchoolService.update(dictSchool);
        model.addAttribute("menuId", DictSchool.class.getSimpleName());
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
        dictSchoolService.delete(ids);
        return SUCCESS_MESSAGE;
    }

    /**
     * 验证学校编号重复 <功能详细描述>
     * 
     * @param username
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/check_code", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkCode(String code)
    {
        if (StringUtils.isEmpty(code))
        {
            return false;
        }
        if (dictSchoolService.codeExists(code))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
