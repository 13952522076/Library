/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

/**
 * Entity - 网银支付银行列表
 * 
 * @author xutianlong
 * @version 3.0
 */
@Entity
@Table(name = "t_pe_pay_bank_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_pay_bank_info_sequence")
public class PayBankInfo extends OrderEntity
{

    private static final long serialVersionUID = 2080194294169656575L;

    /** 支付插件 */
    private String paymentPluginId;

    /** 银行名 */
    private String bankName;

    /** 银行编码 */
    private String bankCode;

    /** 支付方式 （目前只支持bankPay） */
    private String paymentMethod;

    /** 银行图片 */
    private String bankImage;

    /**
     * 支付插件
     * 
     * @return 返回 paymentPluginId
     */
    @NotNull
    @Length(max = 50)
    @Column(nullable = false)
    public String getPaymentPluginId()
    {
        return paymentPluginId;
    }

    /**
     * 支付插件
     * 
     * @param 对paymentPluginId进行赋值
     */
    public void setPaymentPluginId(String paymentPluginId)
    {
        this.paymentPluginId = paymentPluginId;
    }

    /**
     * 银行名
     * 
     * @return 返回 bankName
     */
    @NotNull
    @Length(max = 50)
    @Column(nullable = false)
    public String getBankName()
    {
        return bankName;
    }

    /**
     * 银行名
     * 
     * @param 对bankName进行赋值
     */
    public void setBankName(String bankName)
    {
        this.bankName = bankName;
    }

    /**
     * 银行编码
     * 
     * @return 返回 bankCode
     */
    @NotNull
    @Length(max = 50)
    @Column(nullable = false)
    public String getBankCode()
    {
        return bankCode;
    }

    /**
     * 银行编码
     * 
     * @param 对bankCode进行赋值
     */
    public void setBankCode(String bankCode)
    {
        this.bankCode = bankCode;
    }

    /**
     * 支付方式 （目前只支持bankPay）
     * 
     * @return 返回 paymentMethod
     */
    @NotNull
    @Length(max = 50)
    @Column(nullable = false)
    public String getPaymentMethod()
    {
        return paymentMethod;
    }

    /**
     * 支付方式 （目前只支持bankPay）
     * 
     * @param 对paymentMethod进行赋值
     */
    public void setPaymentMethod(String paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    /**
     * 银行图片
     * 
     * @return 返回 bankImage
     */
    @NotNull
    @Length(max = 500)
    @Column(nullable = false)
    public String getBankImage()
    {
        return bankImage;
    }

    /**
     * 银行图片
     * 
     * @param 对银行图片进行赋值
     */
    public void setBankImage(String bankImage)
    {
        this.bankImage = bankImage;
    }

}
