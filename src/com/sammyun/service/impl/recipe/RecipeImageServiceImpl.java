package com.sammyun.service.impl.recipe;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.recipe.RecipeImageDao;
import com.sammyun.entity.recipe.RecipeImage;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.recipe.RecipeImageService;

/**
 * service - 食谱图片 
 * @author  xutianlong
 * @version  [版本号, May 5, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("recipeImageServiceImpl")
public class RecipeImageServiceImpl extends BaseServiceImpl<RecipeImage, Long> implements RecipeImageService
{
    @Resource(name = "recipeImageDaoImpl")
    private RecipeImageDao recipeImageDao;

    @Resource(name = "recipeImageDaoImpl")
    public void setBaseDao(RecipeImageDao recipeImageDao)
    {
        super.setBaseDao(recipeImageDao);
    }
}
