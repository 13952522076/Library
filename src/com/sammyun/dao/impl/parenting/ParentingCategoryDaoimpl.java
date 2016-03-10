package com.sammyun.dao.impl.parenting;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Order;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.news.NewsCategoryDao;
import com.sammyun.dao.parenting.ParentingCategoryDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.news.NewsCategory;
import com.sammyun.entity.parenting.ParentingCategory;

/**
 * ParentingCategory * DaoImpl - 育儿类别
 * 
 * @author Sencloud Team

 */
@Repository("parentingCategoryDaoImpl")
public class ParentingCategoryDaoimpl extends BaseDaoImpl<ParentingCategory, Long> implements ParentingCategoryDao
{

    @Override
    public List<ParentingCategory> findBySchool(DictSchool dictSchool, Integer defFlag, Integer status, List<Order> orders)
    {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ParentingCategory> criteriaQuery = criteriaBuilder.createQuery(ParentingCategory.class);
        Root<ParentingCategory> root = criteriaQuery.from(ParentingCategory.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();

        if (dictSchool != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("dictSchool"), dictSchool));
        }
        if (defFlag != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("defFlag"), defFlag));
        }
        if (status != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("status"), status));
        }
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery, null, null, null, orders);

    }

}
