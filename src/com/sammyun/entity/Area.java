/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.sammyun.entity.dict.DictSchool;

/**
 * Entity - 地区
 * 
 * @author Sencloud Team

 */
@Entity
@Table(name = "t_pe_area")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_area_sequence")
public class Area extends OrderEntity
{

    private static final long serialVersionUID = -2158109459123036967L;

    /** 树路径分隔符 */
    private static final String TREE_PATH_SEPARATOR = ",";

    /** 代码 */
    private String code;

    /** 名称 */
    private String name;

    /** 全称 */
    private String fullName;

    /** 树路径 */
    private String treePath;

    /** 上级地区 */
    private Area parent;

    /** 下级地区 */
    private Set<Area> children = new HashSet<Area>();

    /** 会员 */
    private Set<Member> members = new HashSet<Member>();

    /** 区域下学校 */
    private Set<DictSchool> dictSchools = new HashSet<DictSchool>();

    /**
     * @return 返回 code
     */
    public String getCode()
    {
        return code;
    }

    /**
     * @param 对code进行赋值
     */
    public void setCode(String code)
    {
        this.code = code;
    }

    /**
     * @return 返回 dictSchools
     */
    @OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
    public Set<DictSchool> getDictSchools()
    {
        return dictSchools;
    }

    /**
     * @param 对dictSchools进行赋值
     */
    public void setDictSchools(Set<DictSchool> dictSchools)
    {
        this.dictSchools = dictSchools;
    }

    /**
     * 获取名称
     * 
     * @return 名称
     */
    @NotEmpty
    @Length(max = 100)
    @Column(nullable = false, length = 100)
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
     * 获取全称
     * 
     * @return 全称
     */
    @Column(nullable = false, length = 500)
    public String getFullName()
    {
        return fullName;
    }

    /**
     * 设置全称
     * 
     * @param fullName 全称
     */
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    /**
     * 获取树路径
     * 
     * @return 树路径
     */
    @Column(nullable = false, updatable = false)
    public String getTreePath()
    {
        return treePath;
    }

    /**
     * 设置树路径
     * 
     * @param treePath 树路径
     */
    public void setTreePath(String treePath)
    {
        this.treePath = treePath;
    }

    /**
     * 获取上级地区
     * 
     * @return 上级地区
     */
    @ManyToOne(fetch = FetchType.LAZY)
    public Area getParent()
    {
        return parent;
    }

    /**
     * 设置上级地区
     * 
     * @param parent 上级地区
     */
    public void setParent(Area parent)
    {
        this.parent = parent;
    }

    /**
     * 获取下级地区
     * 
     * @return 下级地区
     */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy("order asc")
    public Set<Area> getChildren()
    {
        return children;
    }

    /**
     * 设置下级地区
     * 
     * @param children 下级地区
     */
    public void setChildren(Set<Area> children)
    {
        this.children = children;
    }

    /**
     * 获取会员
     * 
     * @return 会员
     */
    @OneToMany(mappedBy = "area", fetch = FetchType.LAZY)
    public Set<Member> getMembers()
    {
        return members;
    }

    /**
     * 设置会员
     * 
     * @param members 会员
     */
    public void setMembers(Set<Member> members)
    {
        this.members = members;
    }

    /**
     * 持久化前处理
     */
    @PrePersist
    public void prePersist()
    {
        Area parent = getParent();
        if (parent != null)
        {
            setFullName(parent.getFullName() + getName());
            setTreePath(parent.getTreePath() + parent.getId() + TREE_PATH_SEPARATOR);
        }
        else
        {
            setFullName(getName());
            setTreePath(TREE_PATH_SEPARATOR);
        }
    }

    /**
     * 更新前处理
     */
    @PreUpdate
    public void preUpdate()
    {
        Area parent = getParent();
        if (parent != null)
        {
            setFullName(parent.getFullName() + getName());
        }
        else
        {
            setFullName(getName());
        }
    }

    /**
     * 删除前处理
     */
    @PreRemove
    public void preRemove()
    {
        Set<Member> members = getMembers();
        if (members != null)
        {
            for (Member member : members)
            {
                member.setArea(null);
            }
        }
    }

    /**
     * 重写toString方法
     * 
     * @return 全称
     */
    @Override
    public String toString()
    {
        return getFullName();
    }

}
