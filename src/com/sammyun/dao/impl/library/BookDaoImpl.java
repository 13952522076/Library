package com.sammyun.dao.impl.library;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.library.BookDao;
import com.sammyun.entity.library.Book;

/**
 * DaoImpl - 书籍
 */
@Repository("bookDaoImpl")
public class BookDaoImpl extends BaseDaoImpl<Book, Long> implements BookDao
{

    @Override
    public List<Book> findByKeyword(String keyword)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (keyword != null && keyword!="")
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.<String>get("name"), "%"+keyword+"%"));
            restrictions = criteriaBuilder.or(restrictions, criteriaBuilder.like(root.<String>get("description"), "%"+keyword+"%"));
            restrictions = criteriaBuilder.or(restrictions, criteriaBuilder.like(root.<String>get("author"), "%"+keyword+"%"));
            restrictions = criteriaBuilder.or(restrictions, criteriaBuilder.like(root.<String>get("publishCompany"), "%"+keyword+"%"));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

}
