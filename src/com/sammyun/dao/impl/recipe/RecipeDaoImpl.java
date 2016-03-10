package com.sammyun.dao.impl.recipe;

import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sammyun.Order;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.recipe.RecipeDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.recipe.Recipe;

/**
 * Recipes * DaoImpl - 学生食谱
 * 
 * @author Sencloud Team

 */
@Repository("recipeDaoImpl")
public class RecipeDaoImpl extends BaseDaoImpl<Recipe, Long> implements RecipeDao
{

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(RecipeDaoImpl.class);

    @Override
    public Recipe getRecipesBy4Conditions(int week, int section, Date recipesDate, int school)
    {
        // TODO Auto-generated method stub
        // String jpql = "select count(*) from Recipes  where  = ";
        // Long count = entityManager.createQuery(jpql,
        // Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
        // "username", username).getSingleResult();
        return null;
    }

    @Override
    public List<Recipe> findRecipes4Week(DictSchool dictSchool, Date startDate, Date endDate)
    {
        String jpql = "select recipe from Recipe recipe where 1=1 and recipe.dictSchool = :dictSchool and recipe.recipesDate >= :startDate and recipe.recipesDate <= :endDate";
        List<Recipe> recipes = null;
        try
        {
            TypedQuery<Recipe> flushModel = entityManager.createQuery(jpql, Recipe.class).setFlushMode(
                    FlushModeType.COMMIT);
            flushModel.setParameter("dictSchool", dictSchool);
            flushModel.setParameter("startDate", startDate);
            flushModel.setParameter("endDate", endDate);
            recipes = (List<Recipe>) flushModel.getResultList();
        }
        catch (Exception e)
        {
            logger.error("查询食谱列表页面错误：" + e.getMessage());
        }

        return recipes;
    }

    @Override
    public List<Recipe> findRecipesByDictSchool(DictSchool dictSchool)
    {
        String jpql = "select recipe from Recipe recipe where 1=1 and recipe.dictSchool = :dictSchool";
        List<Recipe> recipes = null;
        try
        {
            TypedQuery<Recipe> flushModel = entityManager.createQuery(jpql, Recipe.class).setFlushMode(
                    FlushModeType.COMMIT);
            flushModel.setParameter("dictSchool", dictSchool);
            recipes = (List<Recipe>) flushModel.getResultList();
        }
        catch (Exception e)
        {
            logger.error("查询食谱列表页面错误：" + e.getMessage());
        }

        return recipes;
    }

    @Override
    public List<Recipe> findRecipes4Week(DictSchool dictSchool,Long year,Long month,Long week,Boolean isCurrent,List<Order>orders)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteriaQuery = criteriaBuilder.createQuery(Recipe.class);
        Root<Recipe> root = criteriaQuery.from(Recipe.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (dictSchool != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("dictSchool"), dictSchool));
        }
        if (year != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("year"), year));
        }
        if (month != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("month"), month));
        }
        if (week != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("week"), week));
        }
        if (isCurrent != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("isCurrent"), isCurrent));
        }
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery, null, null, null, orders);
    }

    @Override
    public Page<Recipe> findRecipes(DictSchool dictSchool, Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Recipe> criteriaQuery = criteriaBuilder.createQuery(Recipe.class);
        Root<Recipe> root = criteriaQuery.from(Recipe.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (dictSchool != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("dictSchool"), dictSchool));
        }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }

}
