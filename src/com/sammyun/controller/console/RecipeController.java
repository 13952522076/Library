/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */

package com.sammyun.controller.console;

import java.io.IOException;
import java.util.ArrayList;
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

import com.sammyun.Filter;
import com.sammyun.Filter.Operator;
import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.entity.Admin;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.recipe.Recipe;
import com.sammyun.entity.recipe.RecipeImage;
import com.sammyun.entity.recipe.RecipeSection;
import com.sammyun.entity.recipe.RecipeWeekDay;
import com.sammyun.service.AdminService;
import com.sammyun.service.recipe.RecipeDetailService;
import com.sammyun.service.recipe.RecipeImageService;
import com.sammyun.service.recipe.RecipeService;
import com.sammyun.service.recipe.RecipeWeekDayService;
import com.sammyun.service.recipe.RecipesSectionService;
import com.sammyun.util.EduUtil;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 食谱controller
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("adminRecipeController")
@RequestMapping("/console/recipe")
public class RecipeController extends BaseController
{

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    @Resource(name = "recipesDetailServiceImpl")
    private RecipeDetailService recipesDetailService;

    @Resource(name = "recipeServiceImpl")
    private RecipeService recipeService;

    @Resource(name = "recipeWeekDayServiceImpl")
    private RecipeWeekDayService recipeWeekDayService;

    @Resource(name = "recipeImageServiceImpl")
    private RecipeImageService recipeImageService;

    @Resource(name = "recipesSectionServiceImpl")
    private RecipesSectionService recipesSectionService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    /**
     * 列表
     * 
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
        model.addAttribute("page", recipeService.findPage(pageable));
        model.addAttribute("menuId", Recipe.class.getSimpleName());
        return "/console/recipe/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("menuId", Recipe.class.getSimpleName());
        return "/console/recipe/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Recipe recipe, RedirectAttributes redirectAttributes, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        recipe.setDictSchool(dictSchool);
        List<RecipeWeekDay> recipeWeekDays = recipe.getRecipeWeekDays();
        if (!EduUtil.isEmpty(recipeWeekDays))
        {
            for (RecipeWeekDay recipeWeekDay : recipeWeekDays)
            {
                List<RecipeSection> recipeSections = recipeWeekDay.getRecipeSections();
                recipeWeekDay.setRecipe(recipe);
                for (RecipeSection recipeSection : recipeSections)
                {
                    List<RecipeImage> recipeImages = recipeSection.getRecipeImages();
                    recipeSection.setRecipeWeekDay(recipeWeekDay);
                    for (RecipeImage recipeImage : recipeImages)
                    {
                        recipeImage.setRecipeSection(recipeSection);
                    }
                }
            }
        }
        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }
        recipe.setIsCurrent(false);
        recipeService.save(recipe);
//        List<Recipe> recipes = recipeService.findRecipesByDictSchool(dictSchool);
//        if(recipe.getIsCurrent()){
//            if(recipes!=null&&recipes.size()!=0){
//                for(Recipe preRecipe:recipes){
//                    if(preRecipe.getIsCurrent()){
//                        preRecipe.setIsCurrent(false);
//                        recipeService.update(preRecipe);
//                    }
//                }
//            }
//        }
        model.addAttribute("menuId", Recipe.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("recipe", recipeService.find(id));
        model.addAttribute("menuId", Recipe.class.getSimpleName());
        return "/console/recipe/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Recipe recipe, RedirectAttributes redirectAttributes, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        recipe.setDictSchool(dictSchool);
        List<RecipeWeekDay> recipeWeekDays = recipe.getRecipeWeekDays();
        if (!EduUtil.isEmpty(recipeWeekDays))
        {
            for (RecipeWeekDay recipeWeekDay : recipeWeekDays)
            {
                List<RecipeSection> recipeSections = recipeWeekDay.getRecipeSections();
                recipeWeekDay.setRecipe(recipe);
                for (RecipeSection recipeSection : recipeSections)
                {
                    List<RecipeImage> recipeImages = recipeSection.getRecipeImages();
                    recipeSection.setRecipeWeekDay(recipeWeekDay);
                    for (RecipeImage recipeImage : recipeImages)
                    {
                        recipeImage.setRecipeSection(recipeSection);
                    }
                }
            }
        }
        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }
        recipe.setIsCurrent(recipeService.find(recipe.getId()).getIsCurrent());
        recipeService.update(recipe);
//        List<Recipe> recipes = recipeService.findRecipesByDictSchool(dictSchool);
//        if(recipe.getIsCurrent()){
//            recipes.remove(recipe);
//            if(recipes!=null&&recipes.size()!=0){
//                for(Recipe preRecipe:recipes){
//                    if (preRecipe.getIsCurrent() == null)
//                    {
//                        preRecipe.setIsCurrent(false);
//                        recipeService.update(preRecipe);
//                    }
//                    else if (preRecipe.getIsCurrent())
//                    {
//                        preRecipe.setIsCurrent(false);
//                        recipeService.update(preRecipe);
//                    }
//                }
//            }
//        }
        model.addAttribute("menuId", Recipe.class.getSimpleName());
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
        recipeService.delete(ids);
        return SUCCESS_MESSAGE;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public void detail(ModelMap model, Date startDate, Date endDate, HttpServletResponse response)
    {
        Admin admin = adminService.getCurrent();
        DictSchool dictSchool = admin.getDictSchool();
        List<Recipe> recipes = new ArrayList<Recipe>();
        recipes = recipeService.findRecipes4Week(dictSchool, startDate, endDate);
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), recipes);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }

}
