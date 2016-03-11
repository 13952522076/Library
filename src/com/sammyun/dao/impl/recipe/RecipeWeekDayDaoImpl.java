package com.sammyun.dao.impl.recipe;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.recipe.RecipeWeekDayDao;
import com.sammyun.entity.recipe.RecipeWeekDay;

/**
 * Dao - 学生食谱周信息
 * 


 */
@Repository("recipeWeekDayDaoImpl")
public class RecipeWeekDayDaoImpl extends BaseDaoImpl<RecipeWeekDay, Long> implements RecipeWeekDayDao
{
}
