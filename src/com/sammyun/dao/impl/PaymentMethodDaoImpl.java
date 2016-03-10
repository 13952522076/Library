/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.impl;


import org.springframework.stereotype.Repository;

import com.sammyun.dao.PaymentMethodDao;
import com.sammyun.entity.PaymentMethod;

/**
 * Dao - 支付方式
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("paymentMethodDaoImpl")
public class PaymentMethodDaoImpl extends BaseDaoImpl<PaymentMethod, Long> implements PaymentMethodDao {

}