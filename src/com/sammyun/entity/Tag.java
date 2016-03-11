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
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity - 标签
 * 


 */
@Entity
@Table(name = "t_pe_tag")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_tag_sequence")
public class Tag extends OrderEntity
{

    private static final long serialVersionUID = -2735037966597250149L;

    /** "Tag"参数名称 */
    public static final String TAGID_ATTRIBUTE_NAME = Tag.class.getName() + ".TAGID";

    /**
     * 类型
     */
    public enum Type
    {

        /** 文章标签 */
        article
    };

    /** 名称 */
    private String name;

    /** 类型 */
    private Type type;

    /** 图标 */
    private String icon;

    /** mo移动端封面图 */
    private String moMobileCover;

    /** mo移动端封面图 */
    private String edMobileCover;

    /** 备注 */
    private String memo;

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
     * 获取类型
     * 
     * @return 类型
     */
    @NotNull(groups = Save.class)
    @Column(nullable = false, updatable = false)
    public Type getType()
    {
        return type;
    }

    /**
     * 设置类型
     * 
     * @param type 类型
     */
    public void setType(Type type)
    {
        this.type = type;
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
     * 获取MO移动端封面图
     * 
     * @return 返回 moMobileCover
     */
    public String getMoMobileCover()
    {
        return moMobileCover;
    }

    /**
     * 设置MO移动端封面图
     * 
     * @param 对moMobileCover进行赋值
     */
    public void setMoMobileCover(String moMobileCover)
    {
        this.moMobileCover = moMobileCover;
    }

    /**
     * 获取ED移动端封面图
     * 
     * @return 返回 edMobileCover
     */
    public String getEdMobileCover()
    {
        return edMobileCover;
    }

    /**
     * 设置ED移动端封面图
     * 
     * @param 对edMobileCover进行赋值
     */
    public void setEdMobileCover(String edMobileCover)
    {
        this.edMobileCover = edMobileCover;
    }

    /**
     * 获取备注
     * 
     * @return 备注
     */
    @Length(max = 200)
    public String getMemo()
    {
        return memo;
    }

    /**
     * 设置备注
     * 
     * @param memo 备注
     */
    public void setMemo(String memo)
    {
        this.memo = memo;
    }

}
