package com.sammyun.dao.impl.recipe;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.recipe.RecipeImageDao;
import com.sammyun.entity.recipe.RecipeImage;

/**
 * Dao - 食谱图片
 * 
 * @author Sencloud Team

 */
@Repository("recipeImageDaoImpl")
public class RecipeImageDaoImpl extends BaseDaoImpl<RecipeImage, Long> implements RecipeImageDao
{
}
