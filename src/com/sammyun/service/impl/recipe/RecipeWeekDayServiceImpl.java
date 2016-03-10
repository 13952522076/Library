package com.sammyun.service.impl.recipe;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.recipe.RecipeWeekDayDao;
import com.sammyun.entity.recipe.RecipeWeekDay;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.recipe.RecipeWeekDayService;

/**
 * Service - 学生食谱周信息
 * 
 * @author xutianlong
 * @version [版本号, May 5, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("recipeWeekDayServiceImpl")
public class RecipeWeekDayServiceImpl extends BaseServiceImpl<RecipeWeekDay, Long> implements RecipeWeekDayService
{
    @Resource(name = "recipeWeekDayDaoImpl")
    private RecipeWeekDayDao recipeWeekDayDao;

    @Resource(name = "recipeWeekDayDaoImpl")
    public void setBaseDao(RecipeWeekDayDao recipeWeekDayDao)
    {
        super.setBaseDao(recipeWeekDayDao);
    }
}
