package com.sammyun.dao.impl.recipe;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.recipe.RecipesDetailDao;
import com.sammyun.entity.recipe.Recipe;
import com.sammyun.entity.recipe.RecipeWeekDay;

/**
 * Bound * DaoImpl - 学生食谱详情
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("recipesDetailDaoImpl")
public class RecipesDetailDaoImpl extends BaseDaoImpl<RecipeWeekDay, Long> implements RecipesDetailDao {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(RecipesDetailDaoImpl.class);
    
    @Override
    public List<RecipeWeekDay> findByRecipe(Recipe recipe)
    {
        if(recipe==null){
            logger.warn("RecipesDetailDaoImpl findByRecipe: recipe is null;");
            return null;
        }
        String jpql = "select recipesDetail from RecipesDetail recipesDetail where  recipesDetail.recipe = :recipe";
        List<RecipeWeekDay> recipesDetails = null;
        try
        {
            TypedQuery<RecipeWeekDay> flushModel = entityManager.createQuery(jpql, RecipeWeekDay.class).setFlushMode(
                    FlushModeType.COMMIT);
            flushModel.setParameter("recipe", recipe);
           
            recipesDetails = (List<RecipeWeekDay>) flushModel.getResultList();
        }
        catch (Exception e)
        {
            logger.error("查询食谱详情列表页面错误：" + e.getMessage());
        }

        return recipesDetails;
    }

}
