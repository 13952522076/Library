package com.sammyun.dao.impl.library;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Order;
import com.sammyun.Order.Direction;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.library.MarkDao;
import com.sammyun.entity.Admin;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.Mark;

/**
 * DaoImpl - 评分
 */
@Repository("markDaoImpl")
public class MarkDaoImpl extends BaseDaoImpl<Mark, Long> implements MarkDao
{

    @Override
    public List<Mark> findListByBook(Book book)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Mark> criteriaQuery = criteriaBuilder.createQuery(Mark.class);
        Root<Mark> root = criteriaQuery.from(Mark.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (book != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("book"), book));
        }
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery, null, null, null, null);
    }

    @Override
    public List<Mark> findListByAdmin(Admin admin)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Mark> criteriaQuery = criteriaBuilder.createQuery(Mark.class);
        Root<Mark> root = criteriaQuery.from(Mark.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (admin != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("admin"), admin));
        }
        criteriaQuery.where(restrictions);
        Order order = new Order("mark", Direction.desc) ;
        List<Order> orders = new ArrayList<Order>();
        orders.add(order);
        return super.findList(criteriaQuery, null, null, null, orders);
    }

}
