package com.sammyun.controller.console;

import java.util.List;

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
import com.sammyun.entity.parenting.Parenting;
import com.sammyun.entity.parenting.ParentingCategory;
import com.sammyun.service.AdminService;
import com.sammyun.service.parenting.ParentingCategoryService;
import com.sammyun.service.parenting.ParentingService;

/**
 * 育儿类别
 * 
 * @author xutianlong
 * @version [版本号, Apr 26, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("adminParentingCategoryController")
@RequestMapping("/console/parenting_category")
public class ParentingCategoryController extends BaseController
{
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "parentingCategoryServiceImpl")
    private ParentingCategoryService parentingCategoryService;

    @Resource(name = "parentingServiceImpl")
    private ParentingService parentingService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        // DictSchool dictSchool = adminService.getCurrentDictSchool();
        // Filter dictSchoolFilter = new Filter("dictSchool", Operator.eq,
        // dictSchool);
        Filter defFlagFilter = new Filter("defFlag", Operator.eq, 0);
        // pageable.addFilters(dictSchoolFilter);
        pageable.addFilters(defFlagFilter);
        model.addAttribute("page", parentingCategoryService.findPage(pageable));
        model.addAttribute("menuId", Parenting.class.getSimpleName());
        return "/console/parenting_category/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("menuId", Parenting.class.getSimpleName());
        return "/console/parenting_category/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ParentingCategory parentingCategory, ModelMap model, RedirectAttributes redirectAttributes)
    {
        // DictSchool dictSchool = adminService.getCurrentDictSchool();
        // parentingCategory.setDictSchool(dictSchool);
        if (!isValid(parentingCategory))
        {
            return ERROR_VIEW;
        }
        parentingCategory.setDefFlag(0);
        parentingCategoryService.save(parentingCategory);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        model.addAttribute("menuId", Parenting.class.getSimpleName());
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("parentingCategory", parentingCategoryService.find(id));
        model.addAttribute("menuId", Parenting.class.getSimpleName());
        return "/console/parenting_category/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ParentingCategory parentingCategory, ModelMap model, RedirectAttributes redirectAttributes)
    {
        // DictSchool dictSchool = adminService.getCurrentDictSchool();
        // parentingCategory.setDictSchool(dictSchool);
        if (!isValid(parentingCategory))
        {
            return ERROR_VIEW;
        }
        parentingCategoryService.update(parentingCategory);
        model.addAttribute("menuId", Parenting.class.getSimpleName());
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
        for (Long id : ids)
        {
            ParentingCategory parentingCategory = parentingCategoryService.find(id);
            List<Parenting> parentings = parentingService.findByCategory(parentingCategory, null);
            if ((parentings != null) && (parentings.size() > 0))
            {
                String errMessage = parentingCategory.getCategoryName() + "下已有育儿信息，请勿删除。";
                Message message = new Message(com.sammyun.Message.Type.warn, errMessage);
                return message;
            }
        }
        parentingCategoryService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
