package com.sammyun.service.impl.recipe;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sammyun.dao.recipe.RecipesDetailDao;
import com.sammyun.entity.recipe.Recipe;
import com.sammyun.entity.recipe.RecipeWeekDay;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.recipe.RecipeDetailService;

/**
 * ServiceImpl - 学生食谱详情
 * 


 */
@Service("recipesDetailServiceImpl")
public class RecipeDetailServiceImpl extends BaseServiceImpl<RecipeWeekDay, Long> implements RecipeDetailService
{

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(RecipeDetailServiceImpl.class);

    @Resource(name = "recipesDetailDaoImpl")
    private RecipesDetailDao recipesDetailDao;

    @Resource(name = "recipesDetailDaoImpl")
    public void setBaseDao(RecipesDetailDao recipesDetailDao)
    {
        super.setBaseDao(recipesDetailDao);
    }

    @Override
    public List<RecipeWeekDay> findByRecipe(Recipe recipe)
    {
        if (recipe == null)
        {
            logger.warn("RecipesDetailServiceImpl findByRecipe: recipe is null;");
            return null;
        }
        List<RecipeWeekDay> recipesDetails = recipesDetailDao.findByRecipe(recipe);
        return recipesDetails;
    }
}
