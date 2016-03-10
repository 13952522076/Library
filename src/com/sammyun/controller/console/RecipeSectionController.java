/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */

package com.sammyun.controller.console;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Message;
import com.sammyun.entity.recipe.RecipeSection;
import com.sammyun.service.recipe.RecipesSectionService;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 食谱段
 * 
 * @author xutianlong
 * @version [版本号, May 3, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("adminRecipeSectionController")
@RequestMapping("/console/recipe_section")
public class RecipeSectionController extends BaseController
{

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(RecipeSectionController.class);

    @Resource(name = "recipesSectionServiceImpl")
    private RecipesSectionService recipesSectionService;

    /**
     * 进入食谱段管理页面 <功能详细描述>
     * 
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String recipeSection(ModelMap model)
    {
        List<RecipeSection> recipesSections = recipesSectionService.findAll();
        model.addAttribute("recipesSections", recipesSections);
        model.addAttribute("menuId", RecipeSection.class.getSimpleName());
        return "/console/recipe_section/list";
    };

    /**
     * 食谱段的增加 <功能详细描述>
     * 
     * @param model
     * @param sectionName
     * @param redirectAttributes
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String recipeSectionAdd(ModelMap model, String addSectionName, Integer addOrder,
            RedirectAttributes redirectAttributes)
    {
        RecipeSection recipesSection = new RecipeSection();
        recipesSection.setCreateDate(new Date());
        recipesSection.setModifyDate(new Date());
        recipesSection.setOrder(addOrder);
        recipesSection.setSectionName(addSectionName);
        recipesSectionService.save(recipesSection);
        model.addAttribute("menuId", RecipeSection.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 删除 <功能详细描述>
     * 
     * @param ids
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        recipesSectionService.delete(ids);
        return SUCCESS_MESSAGE;
    }

    /**
     * ajax通过id获取对象 <功能详细描述>
     * 
     * @param model
     * @param attendanceId
     * @param response
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public void edit(ModelMap model, Long id, HttpServletResponse response)
    {
        RecipeSection resipesSection = recipesSectionService.find(id);
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), resipesSection);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

    /**
     * 更新 <功能详细描述>
     * 
     * @param model
     * @param editId
     * @param editSectionName
     * @param editOrder
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap model, long editId, String editSectionName, int editOrder)
    {
        RecipeSection recipesSection = recipesSectionService.find(editId);
        recipesSection.setModifyDate(new Date());
        recipesSection.setOrder(editOrder);
        recipesSection.setSectionName(editSectionName);
        recipesSectionService.update(recipesSection);
        model.addAttribute("menuId", RecipeSection.class.getSimpleName());
        return "redirect:list.ct";
    }

}
