/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.LockModeType;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sammyun.dao.PaymentDao;
import com.sammyun.entity.Payment;
import com.sammyun.entity.Payment.Status;
import com.sammyun.entity.Payment.Type;
import com.sammyun.service.MemberService;
import com.sammyun.service.PaymentService;



/**
 * Service - 收款单
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("paymentServiceImpl")
public class PaymentServiceImpl extends BaseServiceImpl<Payment, Long> implements PaymentService
{

    @Resource(name = "paymentDaoImpl")
    private PaymentDao paymentDao;


    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "paymentDaoImpl")
    public void setBaseDao(PaymentDao paymentDao)
    {
        super.setBaseDao(paymentDao);
    }

    @Transactional(readOnly = true)
    public Payment findBySn(String sn)
    {
        return paymentDao.findBySn(sn);
    }

    public void handle(Payment payment)
    {
        paymentDao.refresh(payment, LockModeType.PESSIMISTIC_WRITE);
        if (payment != null && payment.getStatus() == Status.wait)
        {
            if (payment.getType() == Type.payment)
            {}
            else if (payment.getType() == Type.recharge)
            {}
            payment.setStatus(Status.success);
            payment.setPaymentDate(new Date());
            paymentDao.merge(payment);
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
        return paymentDao.findListByOrderSn(orderIdList);
    }


}
