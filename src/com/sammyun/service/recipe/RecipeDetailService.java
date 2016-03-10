package com.sammyun.service.recipe;

import java.util.List;

import com.sammyun.entity.recipe.Recipe;
import com.sammyun.entity.recipe.RecipeWeekDay;
import com.sammyun.service.BaseService;

/**
 * Service - 学生食谱详情
 * 
 * @author Sencloud Team

 */
public interface RecipeDetailService extends BaseService<RecipeWeekDay, Long>
{

    /**
     * 通过食谱查找到食谱详情列表 <功能详细描述>
     * 
     * @param recipe
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<RecipeWeekDay> findByRecipe(Recipe recipe);
}
