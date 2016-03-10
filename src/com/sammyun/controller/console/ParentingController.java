package com.sammyun.controller.console;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Message;
import com.sammyun.Order;
import com.sammyun.Order.Direction;
import com.sammyun.Pageable;
import com.sammyun.Setting;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.parenting.Parenting;
import com.sammyun.entity.parenting.ParentingCategory;
import com.sammyun.plugin.MessagePushPlugin;
import com.sammyun.service.AdminService;
import com.sammyun.service.MemberDeviceInfoService;
import com.sammyun.service.message.MessageService;
import com.sammyun.service.news.NewsCategoryService;
import com.sammyun.service.news.NewsService;
import com.sammyun.service.parenting.ParentingCategoryService;
import com.sammyun.service.parenting.ParentingService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.SettingUtils;

/**
 * Controller - 育儿数据
 * 
 * @author xutianlong
 * @version [版本号, Apr 22, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("adminParentingController")
@RequestMapping("/console/parenting")
public class ParentingController extends BaseController
{
    @Resource(name = "newsServiceImpl")
    private NewsService newsService;

    @Resource(name = "newsCategoryServiceImpl")
    private NewsCategoryService newsCategoryService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "messageServiceImpl")
    private MessageService messageService;

    @Resource(name = "memberDeviceInfoServiceImpl")
    private MemberDeviceInfoService memberDeviceInfoService;

    @Resource
    private List<MessagePushPlugin> messagePushPlugins;

    @Resource(name = "parentingServiceImpl")
    private ParentingService parentingService;

    @Resource(name = "parentingCategoryServiceImpl")
    private ParentingCategoryService parentingCategoryService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        // 新闻中心默认长度为5
        if (pageable.getPageSize() == 12)
        {
            pageable.setPageSize(5);
        }

        pageable.setOrderProperty("isTop");
        pageable.setOrderDirection(Direction.desc);
//        DictSchool dictSchool = adminService.getCurrentDictSchool();
//        Filter filter = new Filter("dictSchool", Operator.eq, dictSchool);
//        pageable.addFilters(filter);
        // DictSchool dictSchool = adminService.getCurrentDictSchool();
        // Filter filter = new Filter("dictSchool", Operator.eq, dictSchool);
        // pageable.addFilters(filter);
        model.addAttribute("page", parentingService.findPage(pageable));
        model.addAttribute("menuId", Parenting.class.getSimpleName());
        return "/console/parenting/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        // DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("modifyDate"));
        List<ParentingCategory> parentingCategories = parentingCategoryService.findBySchool(null, 0, 0, orders);
        model.addAttribute("parentingCategories", parentingCategories);
        model.addAttribute("menuId", Parenting.class.getSimpleName());
        return "/console/parenting/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ModelMap model, Parenting parenting, Long parentingCategoryId,
            RedirectAttributes redirectAttributes, HttpServletRequest request)
    {
        ParentingCategory parentingCategory = parentingCategoryService.find(parentingCategoryId);
        // DictSchool dictSchool = adminService.getCurrentDictSchool();
        parenting.setParentingCategory(parentingCategory);
        // parenting.setDictSchool(dictSchool);
        parenting.setTimePublish(DateUtil.getDate());
        parenting.setStatus(0);
        if (!isValid(parenting))
        {
            return ERROR_VIEW;
        }
        parentingService.save(parenting);
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
        // DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("modifyDate"));
        List<ParentingCategory> parentingCategories = parentingCategoryService.findBySchool(null, 0, 0, orders);
        model.addAttribute("parentingCategories", parentingCategories);
        model.addAttribute("parenting", parentingService.find(id));
        model.addAttribute("menuId", Parenting.class.getSimpleName());
        return "/console/parenting/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap model, Parenting parenting, Long parentingCategoryId,
            RedirectAttributes redirectAttributes)
    {
        ParentingCategory parentingCategory = parentingCategoryService.find(parentingCategoryId);
        // DictSchool dictSchool = adminService.getCurrentDictSchool();
        parenting.setParentingCategory(parentingCategory);
        // parenting.setDictSchool(dictSchool);
        parenting.setTimePublish(DateUtil.getDate());
        parenting.setStatus(0);
        if (!isValid(parenting))
        {
            return ERROR_VIEW;
        }
        parentingService.update(parenting);
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
        parentingService.delete(ids);
        return SUCCESS_MESSAGE;
    }

    /**
     * 检查当前类别是否超出限制
     */
    @RequestMapping(value = "/checkNumByCategory", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkNumByCategory(Long parentingCategoryId)
    {
        Setting setting = SettingUtils.get();
        if (parentingCategoryId == null)
        {
            return true;
        }
        ParentingCategory parentingCategory = parentingCategoryService.find(parentingCategoryId);
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        Long count = parentingService.findIsTopCountByCategory(parentingCategory, dictSchool);
        if (count >= setting.getParentingCategoryLimit())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 检查育儿置顶是否超出限制
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/checkNum", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkNum()
    {
        Setting setting = SettingUtils.get();
        // DictSchool dictSchool = adminService.getCurrentDictSchool();
        Long count = parentingService.findIsTopCount();
        if (count >= setting.getParentingCategoryLimit())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
