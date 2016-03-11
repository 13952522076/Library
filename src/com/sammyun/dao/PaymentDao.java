/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao;

import java.util.List;

import com.sammyun.entity.Payment;

/**
 * Dao - 收款单
 * 


 */
public interface PaymentDao extends BaseDao<Payment, Long>
{

    /**
     * 根据编号查找收款单
     * 
     * @param sn 编号(忽略大小写)
     * @return 收款单，若不存在则返回null
     */
    Payment findBySn(String sn);

    /**
     * 通过订单查询收款单
     * 
     * @param orderIdList
     * @return
     */
    List<Payment> findListByOrderSn(String orderIdList);


}
