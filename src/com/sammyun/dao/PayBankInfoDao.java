/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao;

import java.util.List;

import com.sammyun.entity.PayBankInfo;

/**
 * Dao - 网银支付银行列表
 * 


 */
public interface PayBankInfoDao extends BaseDao<PayBankInfo, Long>
{

    /**
     * 通过支付插件查询网银支付银行列表
     * 
     * @param paymentPluginId
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<PayBankInfo> findByPaymentPluginId(String paymentPluginId);

}
