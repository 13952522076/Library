package com.sammyun.service.impl.recipe;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Order;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.recipe.RecipeDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.recipe.Recipe;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.recipe.RecipeService;

/**
 * ServiceImpl - 学生食谱
 */
@Service("recipeServiceImpl")
public class RecipeServiceImpl extends BaseServiceImpl<Recipe, Long> implements RecipeService
{

    @Resource(name = "recipeDaoImpl")
    private RecipeDao recipeDao;

    @Resource(name = "recipeDaoImpl")
    public void setBaseDao(RecipeDao recipeDao)
    {
        super.setBaseDao(recipeDao);
    }

    @Override
    public boolean recipesExsit(int week, int section, Date recipesDate, int school)
    {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Recipe> findRecipes4Week(DictSchool dictSchool, Date startDate, Date endDate)
    {
        // TODO Auto-generated method stub
        List<Recipe> recipes = recipeDao.findRecipes4Week(dictSchool, startDate, endDate);
        return recipes;
    }

    @Override
    public List<Recipe> findRecipesByDictSchool(DictSchool dictSchool)
    {
        // TODO Auto-generated method stub
        List<Recipe> recipes = recipeDao.findRecipesByDictSchool(dictSchool);
        return recipes;
    }

    @Override
    public List<Recipe> findRecipes4Week(DictSchool dictSchool, Long year, Long month, Long week, Boolean isCurrent,
            List<Order> orders)
    {
        // TODO Auto-generated method stub
        return recipeDao.findRecipes4Week(dictSchool, year, month, week, isCurrent, orders);
    }

    @Override
    public Page<Recipe> findRecipes(DictSchool dictSchool, Pageable pageable)
    {
        // TODO Auto-generated method stub
        return recipeDao.findRecipes(dictSchool, pageable);
    }

    @Override
    public void changeIsCurrent()
    {
        // TODO Auto-generated method stub
        List<Recipe> recipes = this.findAll();
        if (recipes == null || recipes.size() == 0)
        {
            return;
        }
        for (Recipe recipe : recipes)
        {
            Date date = new Date();
            Date startDate = recipe.getWeekStartDate();
            Date endDate = recipe.getWeekEndDate();
            if (date.after(startDate) && date.before(endDate))
            {
                recipe.setIsCurrent(true);
                this.update(recipe);
            }
            else
            {
                if ((recipe.getIsCurrent() == null) || (recipe.getIsCurrent()))
                {
                    recipe.setIsCurrent(false);
                    this.update(recipe);
                }
            }

        }

    }

}
