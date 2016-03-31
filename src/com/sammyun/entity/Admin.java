/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.library.Collection;
import com.sammyun.entity.library.Mark;

/**
 * Entity - 管理员
 */
@Entity
@Table(name = "t_pe_admin")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_admin_sequence")
public class Admin extends BaseEntity
{

    private static final long serialVersionUID = -7519486823153844426L;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** E-mail */
    private String email;

    /** 姓名 */
    private String name;

    /** 最后登录日期 */
    private Date loginDate;

    /** 最后登录IP */
    private String loginIp;

    /** 角色 */
    private Set<Role> roles = new HashSet<Role>();

    /** 评价 */
    private Set<Mark> marks = new HashSet<Mark>();

    /** 收藏 */
    private Set<Collection> collections = new HashSet<Collection>();

    /** 用户隶属的学校 */
    private DictSchool dictSchool;

    /** 用户头像 */
    private String iconPhoto;

    /**
     * 获取用户名
     * 
     * @return 用户名
     */
    @JsonProperty
    @NotEmpty(groups = Save.class)
    @Pattern(regexp = "^[0-9a-z_A-Z\\u4e00-\\u9fa5]+$")
    @Length(min = 2, max = 20)
    @Column(nullable = false, updatable = false, unique = true, length = 100)
    public String getUsername()
    {
        return username;
    }

    /**
     * 设置用户名
     * 
     * @param username 用户名
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * 获取密码
     * 
     * @return 密码
     */
    @NotEmpty(groups = Save.class)
    @Pattern(regexp = "^[^\\s&\"<>]+$")
    @Length(min = 4, max = 20)
    @Column(nullable = false)
    public String getPassword()
    {
        return password;
    }

    /**
     * 设置密码
     * 
     * @param password 密码
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * 获取E-mail
     * 
     * @return E-mail
     */
    @JsonProperty
    @NotEmpty
    @Email
    @Length(max = 200)
    public String getEmail()
    {
        return email;
    }

    /**
     * 设置E-mail
     * 
     * @param email E-mail
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * 获取姓名
     * 
     * @return 姓名
     */
    @JsonProperty
    @Length(max = 200)
    public String getName()
    {
        return name;
    }

    /**
     * 设置姓名
     * 
     * @param name 姓名
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 获取最后登录日期
     * 
     * @return 最后登录日期
     */
    public Date getLoginDate()
    {
        return loginDate;
    }

    /**
     * 设置最后登录日期
     * 
     * @param loginDate 最后登录日期
     */
    public void setLoginDate(Date loginDate)
    {
        this.loginDate = loginDate;
    }

    /**
     * 获取最后登录IP
     * 
     * @return 最后登录IP
     */
    public String getLoginIp()
    {
        return loginIp;
    }

    /**
     * 设置最后登录IP
     * 
     * @param loginIp 最后登录IP
     */
    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    /**
     * 获取角色
     * 
     * @return 角色
     */
    @NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_pe_admin_role")
    public Set<Role> getRoles()
    {
        return roles;
    }

    /**
     * 设置角色
     * 
     * @param roles 角色
     */
    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }

    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    public Set<Mark> getMarks()
    {
        return marks;
    }

    public void setMarks(Set<Mark> marks)
    {
        this.marks = marks;
    }

    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY)
    public Set<Collection> getCollections()
    {
        return collections;
    }

    public void setCollections(Set<Collection> collections)
    {
        this.collections = collections;
    }

    /**
     * 获取管理员隶属的学校
     * 
     * @return 返回 dictRegins
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictSchool getDictSchool()
    {
        return dictSchool;
    }

    /**
     * @param 对dictSchool进行赋值
     */
    public void setDictSchool(DictSchool dictSchool)
    {
        this.dictSchool = dictSchool;
    }

    /**
     * @return 返回 iconPhoto
     */
    public String getIconPhoto()
    {
        return iconPhoto;
    }

    /**
     * @param 对iconPhoto进行赋值
     */
    public void setIconPhoto(String iconPhoto)
    {
        this.iconPhoto = iconPhoto;
    }

    /**
     * 删除前处理
     */
    @PreRemove
    public void preRemove()
    {
    }

}
