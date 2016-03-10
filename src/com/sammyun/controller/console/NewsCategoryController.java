package com.sammyun.controller.console;

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
import com.sammyun.entity.news.News;
import com.sammyun.entity.news.NewsCategory;
import com.sammyun.service.AdminService;
import com.sammyun.service.news.NewsCategoryService;
import com.sammyun.service.news.NewsService;

/**
 * 新闻类别
 * 
 * @author xutianlong
 * @version [版本号, Apr 26, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("adminNewsCategoryController")
@RequestMapping("/console/news_category")
public class NewsCategoryController extends BaseController
{
    @Resource(name = "newsServiceImpl")
    private NewsService newsService;

    @Resource(name = "newsCategoryServiceImpl")
    private NewsCategoryService newsCategoryService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        Filter dictSchoolFilter = new Filter("dictSchool", Operator.eq, dictSchool);
        Filter defFlagFilter = new Filter("defFlag", Operator.eq, 0);
        pageable.addFilters(dictSchoolFilter);
        pageable.addFilters(defFlagFilter);
        model.addAttribute("page", newsCategoryService.findPage(pageable));
        model.addAttribute("menuId", News.class.getSimpleName());
        return "/console/news_category/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("menuId", News.class.getSimpleName());
        return "/console/news_category/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(NewsCategory newsCategory, ModelMap model, RedirectAttributes redirectAttributes)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        newsCategory.setDictSchool(dictSchool);
        if (!isValid(newsCategory))
        {
            return ERROR_VIEW;
        }
        newsCategory.setDefFlag(0);
        newsCategoryService.save(newsCategory);
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
        model.addAttribute("newsCategory", newsCategoryService.find(id));
        model.addAttribute("menuId", News.class.getSimpleName());
        return "/console/news_category/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(NewsCategory newsCategory, ModelMap model, RedirectAttributes redirectAttributes)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        newsCategory.setDictSchool(dictSchool);
        if (!isValid(newsCategory))
        {
            return ERROR_VIEW;
        }
        newsCategoryService.update(newsCategory);
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
        newsCategoryService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
