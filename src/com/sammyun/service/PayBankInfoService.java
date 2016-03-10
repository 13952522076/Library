/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service;

import java.util.List;

import com.sammyun.entity.PayBankInfo;

/**
 * Service - 网银支付银行列表
 * @author  xutianlong
 * @version  [版本号, Aug 27, 2014]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface PayBankInfoService extends BaseService<PayBankInfo, Long>{

	/**
	 * 通过支付插件查询网银支付银行列表
	 * @param paymentPluginId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	List<PayBankInfo> findByPaymentPluginId(String paymentPluginId);

}
