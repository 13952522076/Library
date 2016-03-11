package com.sammyun.dao.recipe;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.recipe.Recipe;
import com.sammyun.entity.recipe.RecipeWeekDay;

/**
 * Bound * Dao - 学生食谱详情
 * 


 */
public interface RecipesDetailDao extends BaseDao<RecipeWeekDay, Long> {

    /**
     * 通过食谱查找到食谱详情列表
     * <功能详细描述>
     * @param recipe
     * @return List<RecipesDetail>
     * @see [类、类#方法、类#成员]
     */
    public List<RecipeWeekDay> findByRecipe(Recipe recipe);
}
