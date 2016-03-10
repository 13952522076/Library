/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity - 属性
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Entity
@Table(name = "t_pe_attribute")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_attribute_sequence")
public class Attribute extends OrderEntity
{

    private static final long serialVersionUID = 2447794131117928367L;

    /** 名称 */
    private String name;

    /** 属性序号 */
    private Integer propertyIndex;


    /** 可选项 */
    private List<String> options = new ArrayList<String>();

    /**
     * 获取名称
     * 
     * @return 名称
     */
    @JsonProperty
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
     * 获取属性序号
     * 
     * @return 属性序号
     */
    @Column(nullable = false, updatable = false)
    public Integer getPropertyIndex()
    {
        return propertyIndex;
    }

    /**
     * 设置属性序号
     * 
     * @param propertyIndex 属性序号
     */
    public void setPropertyIndex(Integer propertyIndex)
    {
        this.propertyIndex = propertyIndex;
    }

    /**
     * 获取可选项
     * 
     * @return 可选项
     */
    @JsonProperty
    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "T_PE_attribute_option")
    public List<String> getOptions()
    {
        return options;
    }

    /**
     * 设置可选项
     * 
     * @param options 可选项
     */
    public void setOptions(List<String> options)
    {
        this.options = options;
    }

}
