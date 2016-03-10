package com.sammyun.dao.impl.news;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Order;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.news.NewsCategoryDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.news.NewsCategory;

/**
 * NewsCategory * DaoImpl - 新闻类别
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("newsCategoryDaoImpl")
public class NewsCategoryDaoimpl extends BaseDaoImpl<NewsCategory, Long> implements NewsCategoryDao 
{

    @Override
    public List<NewsCategory> findBySchool(DictSchool dictSchool, Integer defFlag, Integer status,List<Order> orders)
    {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<NewsCategory> criteriaQuery = criteriaBuilder.createQuery(NewsCategory.class);
        Root<NewsCategory> root = criteriaQuery.from(NewsCategory.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        
        if (dictSchool != null) {
          restrictions = criteriaBuilder.and(
                  restrictions,
                  criteriaBuilder.equal(
                          root.get("dictSchool"),dictSchool));
      }
      if (defFlag != null) {
          restrictions = criteriaBuilder.and(
                  restrictions,
                  criteriaBuilder.equal(
                          root.get("defFlag"), defFlag));
      }
      if (status != null) {
          restrictions = criteriaBuilder.and(
                  restrictions,
                  criteriaBuilder.equal(
                          root.get("status"), status));
      }
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery, null, null, null, orders);
        
    }

}
