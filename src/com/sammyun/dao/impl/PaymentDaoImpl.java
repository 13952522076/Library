/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.PaymentDao;
import com.sammyun.entity.Payment;

/**
 * Dao - 收款单
 * 
 * @author Sencloud Team

 */
@Repository("paymentDaoImpl")
public class PaymentDaoImpl extends BaseDaoImpl<Payment, Long> implements PaymentDao
{

    public Payment findBySn(String sn)
    {
        if (sn == null)
        {
            return null;
        }
        String jpql = "select payment from Payment payment where lower(payment.sn) = lower(:sn)";
        try
        {
            return entityManager.createQuery(jpql, Payment.class).setFlushMode(FlushModeType.COMMIT).setParameter("sn",
                    sn).getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    /**
     * 通过订单查询收款单
     * 
     * @param orderIdList
     * @return
     */
    @Override
    public List<Payment> findListByOrderSn(String orderIdList)
    {
        if (orderIdList == null)
        {
            return null;
        }
        String jpql = "select payment from Payment payment where payment.order.sn in (:sn)";
        try
        {
            TypedQuery<Payment> query = entityManager.createQuery(jpql, Payment.class).setFlushMode(
                    FlushModeType.COMMIT).setParameter("sn", orderIdList);
            return query.getResultList();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

}
