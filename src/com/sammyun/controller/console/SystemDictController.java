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
import com.sammyun.Message.Type;
import com.sammyun.Pageable;
import com.sammyun.entity.system.SystemDict;
import com.sammyun.service.AdminService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.service.system.SystemDictService;

/**
 * Controller - 系统字典
 * 


 */
@Controller("systemDictController")
@RequestMapping("/console/systemDict")
public class SystemDictController extends BaseController
{
    @Resource(name = "systemDictServiceImpl")
    private SystemDictService systemDictService;

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    /**
     * 系统字典列表
     * 
     * @param pageable
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, String fieldName, String codeDesc, ModelMap model)
    {
        if (fieldName != null)
        {
            Filter filter = new Filter("fieldName", Operator.like, "%" + fieldName + "%");
            pageable.addFilters(filter);
            model.addAttribute("fieldName", fieldName);
        }
        if (codeDesc != null)
        {
            Filter filter = new Filter("codeDesc", Operator.like, "%" + codeDesc + "%");
            pageable.addFilters(filter);
            model.addAttribute("codeDesc", codeDesc);
        }
        model.addAttribute("page", systemDictService.findPage(pageable));
        model.addAttribute("menuId", SystemDict.class.getSimpleName());
        return "/console/systemDict/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("menuId", SystemDict.class.getSimpleName());
        return "/console/systemDict/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(SystemDict systemDict, ModelMap model, RedirectAttributes redirectAttributes)
    {
        systemDict.setField("0");
        systemDict.setFieldName("敏感词");
        systemDict.setEnabled(true);
        systemDict.setEditMode(true);
        systemDict.setCode(systemDict.getCode().trim());
        systemDict.setCodeDesc(systemDict.getCodeDesc().trim());
        if (!isValid(systemDict))
        {
            return ERROR_VIEW;
        }
        systemDictService.save(systemDict);
        model.addAttribute("menuId", SystemDict.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("systemDict", systemDictService.find(id));
        model.addAttribute("menuId", SystemDict.class.getSimpleName());
        return "/console/systemDict/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(SystemDict systemDict, ModelMap model, RedirectAttributes redirectAttributes)
    {
        SystemDict preSystemDict = systemDictService.find(systemDict.getId());
        preSystemDict.setCode(systemDict.getCode().trim());
        preSystemDict.setCodeDesc(systemDict.getCodeDesc().trim());
        systemDictService.update(preSystemDict);
        model.addAttribute("menuId", SystemDict.class.getSimpleName());
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
        // courseNameService.delete(ids);
        systemDictService.delete(ids);
        return SUCCESS_MESSAGE;
    }

    /**
     * 检查代码是否重复 <功能详细描述>
     * 
     * @param code
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/checkCodeExsit", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkCodeExsit(String code)
    {
        if (StringUtils.isEmpty(code))
        {
            return false;
        }
        List<SystemDict> systemDicts = systemDictService.findByCode(code);
        if (systemDicts != null && systemDicts.size() > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 编辑页面检查代码是否重复 <功能详细描述>
     * 
     * @param code
     * @param preCode
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/checkCodeExsit_edit", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkCodeExsitEdit(String code, String preCode)
    {
        if (StringUtils.equalsIgnoreCase(code.trim(), preCode.trim()))
        {
            return true;
        }
        if (StringUtils.isEmpty(code))
        {
            return false;
        }
        List<SystemDict> systemDicts = systemDictService.findByCode(code);
        if (systemDicts != null && systemDicts.size() > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 检查代码描述是否重复 <功能详细描述>
     * 
     * @param codeDesc
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/checkCodeDescExsit", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkCodeDescExsit(String codeDesc)
    {
        if (StringUtils.isEmpty(codeDesc))
        {
            return false;
        }
        List<SystemDict> systemDicts = systemDictService.findByCodeDesc(codeDesc);
        if (systemDicts != null && systemDicts.size() > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 编辑页面检查代码描述是否重复 <功能详细描述>
     * 
     * @param codeDesc
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/checkCodeDescExsit_edit", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkCodeDescExsitEdit(String codeDesc, String preCodeDesc)
    {
        if (StringUtils.equalsIgnoreCase(codeDesc.trim(), preCodeDesc.trim()))
        {
            return true;
        }
        if (StringUtils.isEmpty(codeDesc))
        {
            return false;
        }
        List<SystemDict> systemDicts = systemDictService.findByCodeDesc(codeDesc);
        if (systemDicts != null && systemDicts.size() > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 同步内存 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    public @ResponseBody
    Message sync()
    {
        systemDictService.set();
        Message message = new Message(Type.success, "同步内存成功");
        return message;
    }
}
