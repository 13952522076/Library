package com.sammyun.dao.impl.recipe;


import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.recipe.RecipesSectionDao;
import com.sammyun.entity.recipe.RecipeSection;

/**
 * Bound * DaoImpl - 学生食谱时间段
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("recipesSectionDaoImpl")
public class RecipesSectionDaoImpl extends BaseDaoImpl<RecipeSection, Long> implements RecipesSectionDao{

}
