/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.PayBankInfoDao;
import com.sammyun.entity.PayBankInfo;
import com.sammyun.service.PayBankInfoService;


/**
 * Service - 网银支付银行列表
 * @author  xutianlong
 * @version  [版本号, Aug 27, 2014]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Service("payBankInfoServiceImpl")
public class PayBankInfoServiceImpl extends BaseServiceImpl<PayBankInfo, Long> implements PayBankInfoService {
	
	@Resource(name = "payBankInfoDaoImpl")
	private PayBankInfoDao payBankInfoDao;
	@Resource(name = "payBankInfoDaoImpl")
	public void setBaseDao(PayBankInfoDao payBankInfoDao) {
		super.setBaseDao(payBankInfoDao);
	}

	/**
	 * 通过支付插件查询网银支付银行列表
	 * @param paymentPluginId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<PayBankInfo> findByPaymentPluginId(String paymentPluginId) {
		return payBankInfoDao.findByPaymentPluginId(paymentPluginId);
	}
}
