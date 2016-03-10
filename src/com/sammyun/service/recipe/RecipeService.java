package com.sammyun.service.recipe;

import java.util.Date;
import java.util.List;

import com.sammyun.Order;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.recipe.Recipe;
import com.sammyun.service.BaseService;

/**
 * RecipesService * Service - 学生食谱
 * 
 * @author Sencloud Team

 */
public interface RecipeService extends BaseService<Recipe, Long>
{
    /**
     * 通过1周，2食谱段，3食谱时间，4学校 判断 食谱是否存在
     * <功能详细描述>
     * @param week
     * @param section
     * @param recipesDate
     * @param school
     * @return
     * @see [类、类#方法、类#成员]
     */
    public boolean recipesExsit(int week, int section, Date recipesDate, int school);
    
    /**
     * 为页面显示列表
     * <功能详细描述>
     * @param dictschool
     * @param startDate
     * @param endDate
     * @return List<Recipe>
     * @see [类、类#方法、类#成员]
     */
    public List<Recipe> findRecipes4Week(DictSchool dictSchool,Date startDate,Date endDate);
    
    
    /**
     * 根据学校查询出食谱列表
     * <功能详细描述>
     * @param dictSchool
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Recipe> findRecipesByDictSchool(DictSchool dictSchool);
    
    /**
     * 根据年月查询食谱信息
     * <功能详细描述>
     * @param dictSchool
     * @param yeat
     * @param month
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Recipe> findRecipes4Week(DictSchool dictSchool,Long year,Long month,Long week,Boolean isCurrent,List<Order>orders);
    
    /**
     * 根据学校查询食谱信息
     * <功能详细描述>
     * @param dictSchool
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Page<Recipe> findRecipes(DictSchool dictSchool,Pageable pageable);
    
    /**
     * Job定时器改变是否当前周
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void changeIsCurrent();
}
