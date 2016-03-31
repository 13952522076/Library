package com.sammyun.dao.impl.library;

import java.util.List;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import javax.persistence.criteria.CriteriaBuilder;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.library.CollectionDao;
import com.sammyun.entity.Admin;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.Collection;
import javax.persistence.criteria.CriteriaQuery;

/**
 * DaoImpl - 喜欢收藏
 */
@Repository("collectionDaoImpl")
public class CollectionDaoImpl extends BaseDaoImpl<Collection, Long> implements CollectionDao
{

    @Override
    public List<Collection> findByBookAndAdmin(Book book, Admin admin)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Collection> criteriaQuery = criteriaBuilder.createQuery(Collection.class);
        Root<Collection> root = criteriaQuery.from(Collection.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (book != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("book"), book));
        }
        if (admin != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("admin"), admin));
        }
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery, null, null, null, null);
    }

}
