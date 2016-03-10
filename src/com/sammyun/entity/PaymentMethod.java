/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity - 支付方式
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Entity
@Table(name = "t_pe_payment_method")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_payment_method_sequence")
public class PaymentMethod extends OrderEntity
{

    private static final long serialVersionUID = 6949816500116581199L;

    /**
     * 方式
     */
    public enum Method
    {

        /** 在线支付 */
        online,

        /** 线下支付 */
        offline
    };

    /** 名称 */
    private String name;

    /** 方式 */
    private Method method;

    /** 超时时间 */
    private Integer timeout;

    /** 图标 */
    private String icon;

    /** 介绍 */
    private String description;

    /** 内容 */
    private String content;

    /**
     * 获取名称
     * 
     * @return 名称
     */
    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
    public String getName()
    {
        return name;
    }

    /**
     * 设置名称
     * 
     * @param name 名称
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 获取方式
     * 
     * @return 方式
     */
    @NotNull
    @Column(nullable = false)
    public Method getMethod()
    {
        return method;
    }

    /**
     * 设置方式
     * 
     * @param method 方式
     */
    public void setMethod(Method method)
    {
        this.method = method;
    }

    /**
     * 获取超时时间
     * 
     * @return 超时时间
     */
    @Min(1)
    public Integer getTimeout()
    {
        return timeout;
    }

    /**
     * 设置超时时间
     * 
     * @param timeout 超时时间
     */
    public void setTimeout(Integer timeout)
    {
        this.timeout = timeout;
    }

    /**
     * 获取图标
     * 
     * @return 图标
     */
    @Length(max = 200)
    public String getIcon()
    {
        return icon;
    }

    /**
     * 设置图标
     * 
     * @param icon 图标
     */
    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    /**
     * 获取介绍
     * 
     * @return 介绍
     */
    @Length(max = 200)
    public String getDescription()
    {
        return description;
    }

    /**
     * 设置介绍
     * 
     * @param description 介绍
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * 获取内容
     * 
     * @return 内容
     */
    @Lob
    public String getContent()
    {
        return content;
    }

    /**
     * 设置内容
     * 
     * @param content 内容
     */
    public void setContent(String content)
    {
        this.content = content;
    }

    /**
     * 删除前处理
     */
    @PreRemove
    public void preRemove()
    {
    }

}
