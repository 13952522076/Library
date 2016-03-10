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

import com.sammyun.Filter;
import com.sammyun.Filter.Operator;
import com.sammyun.Message;
import com.sammyun.Order;
import com.sammyun.Pageable;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.news.News;
import com.sammyun.entity.news.NewsCategory;
import com.sammyun.plugin.MessagePushPlugin;
import com.sammyun.service.AdminService;
import com.sammyun.service.MemberDeviceInfoService;
import com.sammyun.service.message.MessageService;
import com.sammyun.service.news.NewsCategoryService;
import com.sammyun.service.news.NewsService;
import com.sammyun.util.DateUtil;

/**
 * Controller - 新闻数据
 * 
 * @author xutianlong
 * @version [版本号, Apr 22, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("adminNewsController")
@RequestMapping("/console/news")
public class NewsController extends BaseController
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
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        Filter filter = new Filter("dictSchool", Operator.eq, dictSchool);
        pageable.addFilters(filter);
        model.addAttribute("page", newsService.findPage(pageable));
        model.addAttribute("menuId", News.class.getSimpleName());
        return "/console/news/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("modifyDate"));
        List<NewsCategory> newsCategories = newsCategoryService.findBySchool(dictSchool, 0, 0, orders);
        model.addAttribute("newsCategories", newsCategories);
        model.addAttribute("menuId", News.class.getSimpleName());
        return "/console/news/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ModelMap model, News news, Long newsCategoryId, RedirectAttributes redirectAttributes,
            HttpServletRequest request)
    {
        NewsCategory newsCategory = newsCategoryService.find(newsCategoryId);
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        news.setNewsCategory(newsCategory);
        news.setDictSchool(dictSchool);
        news.setTimePublish(DateUtil.getDate());
        news.setStatus(0);
        if (!isValid(news))
        {
            return ERROR_VIEW;
        }
        newsService.save(news);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        model.addAttribute("menuId", News.class.getSimpleName());
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("modifyDate"));
        List<NewsCategory> newsCategories = newsCategoryService.findBySchool(dictSchool, 0, 0, orders);
        model.addAttribute("newsCategories", newsCategories);
        model.addAttribute("news", newsService.find(id));
        model.addAttribute("menuId", News.class.getSimpleName());
        return "/console/news/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap model, News news, Long newsCategoryId, RedirectAttributes redirectAttributes)
    {
        NewsCategory newsCategory = newsCategoryService.find(newsCategoryId);
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        news.setNewsCategory(newsCategory);
        news.setDictSchool(dictSchool);
        news.setTimePublish(DateUtil.getDate());
        news.setStatus(0);
        if (!isValid(news))
        {
            return ERROR_VIEW;
        }
        newsService.update(news);
        model.addAttribute("menuId", News.class.getSimpleName());
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
        newsService.delete(ids);
        return SUCCESS_MESSAGE;
    }

}
