package com.sammyun.dao.impl.news;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.news.NewsDao;
import com.sammyun.entity.news.News;
import com.sammyun.entity.news.NewsCategory;

/**
 * News * DaoImpl - 新闻数据
 * 


 */
@Repository("newsDaoImpl")
public class NewsDaoImpl extends BaseDaoImpl<News, Long> implements NewsDao  {

    @Override
    public Page<News> findBySchool(NewsCategory newsCategory, Integer status, Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<News> criteriaQuery = criteriaBuilder.createQuery(News.class);
        Root<News> root = criteriaQuery.from(News.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        
        if (newsCategory != null) {
          restrictions = criteriaBuilder.and(
                  restrictions,
                  criteriaBuilder.equal(
                          root.get("newsCategory"),newsCategory));
      }
      if (status != null) {
          restrictions = criteriaBuilder.and(
                  restrictions,
                  criteriaBuilder.equal(
                          root.get("status"), status));
      }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
        
    }

}
