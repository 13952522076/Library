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

import com.sammyun.dao.PayBankInfoDao;
import com.sammyun.entity.PayBankInfo;

/**
 * Dao - 网银支付银行列表
 * @author  xutianlong
 * @version  [版本号, Aug 27, 2014]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository("payBankInfoDaoImpl")
public class PayBankInfoDaoImpl extends BaseDaoImpl<PayBankInfo, Long> implements PayBankInfoDao{


	/**
	 * 通过支付插件查询网银支付银行列表
	 * @param paymentPluginId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Override
	public List<PayBankInfo> findByPaymentPluginId(String paymentPluginId) {
		if (paymentPluginId == null) {
			return null;
		}
		String jpql = "select payBankInfo from PayBankInfo PayBankInfo where payBankInfo.paymentPluginId = :paymentPluginId order by payBankInfo.order asc";
		try {
			TypedQuery<PayBankInfo> query = entityManager.createQuery(jpql, PayBankInfo.class).setFlushMode(FlushModeType.COMMIT).setParameter("paymentPluginId", paymentPluginId);
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
