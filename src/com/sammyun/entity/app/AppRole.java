package com.sammyun.entity.app;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.Member.MemberType;

/**
 * Entity - 应用角色管理 <br />
 * 针对应用做角色管理
 * 
 * @author xutianlong
 * @version [版本号, Aug 6, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_app_role")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_app_role_sequence")
public class AppRole extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1115634858339710178L;

    /** 应用角色 */
    private String name;

    /** 是否内置 */
    private Boolean isSystem;

    /** 描述 */
    private String description;

    private List<App> apps = new ArrayList<App>();

    /** 应用角色隶属的用户类型 */
    private List<MemberType> memberTypes = new ArrayList<MemberType>();
    

    /**
     * @return 返回 name
     */
    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
    public String getName()
    {
        return name;
    }

    /**
     * @param 对name进行赋值
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return 返回 isSystem
     */
    @Column(nullable = false, updatable = false)
    public Boolean getIsSystem()
    {
        return isSystem;
    }

    /**
     * @param 对isSystem进行赋值
     */
    public void setIsSystem(Boolean isSystem)
    {
        this.isSystem = isSystem;
    }

    /**
     * @return 返回 description
     */
    @Length(max = 200)
    public String getDescription()
    {
        return description;
    }

    /**
     * @param 对description进行赋值
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return 返回 apps
     */
    @ManyToMany(mappedBy = "appRoles", fetch = FetchType.LAZY)
    public List<App> getApps()
    {
        return apps;
    }

    /**
     * @param 对apps进行赋值
     */
    public void setApps(List<App> apps)
    {
        this.apps = apps;
    }

    /**
     * @return 返回 memberTypes
     */
    @ElementCollection
    @CollectionTable(name = "t_pe_role_member_type")
    public List<MemberType> getMemberTypes()
    {
        return memberTypes;
    }

    /**
     * @param 对memberTypes进行赋值
     */
    public void setMemberTypes(List<MemberType> memberTypes)
    {
        this.memberTypes = memberTypes;
    }

}
