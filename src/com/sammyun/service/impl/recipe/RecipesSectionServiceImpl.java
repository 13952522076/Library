package com.sammyun.service.impl.recipe;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.recipe.RecipesSectionService;
import com.sammyun.dao.recipe.RecipesSectionDao;
import com.sammyun.entity.recipe.RecipeSection;

/**
 * ServiceImpl - 学生食谱时间段
 * 


 */
@Service("recipesSectionServiceImpl")
public class RecipesSectionServiceImpl extends BaseServiceImpl<RecipeSection, Long> implements RecipesSectionService{
	  
	@Resource(name = "recipesSectionDaoImpl")
    private RecipesSectionDao recipesSectionDao;

    @Resource(name = "recipesSectionDaoImpl")
    public void setBaseDao(RecipesSectionDao recipesSectionDao){
        super.setBaseDao(recipesSectionDao);
    }
}
