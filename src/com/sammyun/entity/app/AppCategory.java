package com.sammyun.entity.app;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;

/**
 * Entity - 应用分类 <一句话功能简述> <功能详细描述> <br />
 * 应用分类禁止删除，只有状态的变更（true-使用，false-禁用）
 * 
 * @author xutianlong
 * @version [版本号, Aug 4, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_app_category")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_app_category_sequence")
public class AppCategory extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -8608237043725923999L;

    /** 应用分类名称 */
    private String name;

    /** 应用分类说明 */
    private String description;

    /** 是否禁用 (true-使用，false-禁用) */
    private Boolean status;
    
    /** 应用分类图标*/
    private String appCategoryLogoUrl;

    /** 应用清单 */
    private Set<App> apps = new HashSet<App>();

    /**
     * @return 返回 name
     */
    @JsonProperty
    @NotEmpty(groups = Save.class)
    @Pattern(regexp = "^[0-9a-z_A-Z\\u4e00-\\u9fa5]+$")
    @Column(nullable = false, unique = true, length = 50)
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
     * @return 返回 description
     */
    @Column(nullable = false, length = 100)
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
     * @return 返回 status
     */
    @NotNull
    @Column(nullable = false,columnDefinition="Boolean default true")
    public Boolean getStatus()
    {
        return status;
    }

    /**
     * @param 对status进行赋值
     */
    public void setStatus(Boolean status)
    {
        this.status = status;
    }

    /**
     * @return 返回 appCategoryLogoUrl
     */
    @NotEmpty
    @Length(max = 500)
    @Column(nullable = false)
    public String getAppCategoryLogoUrl() {
		return appCategoryLogoUrl;
	}

    /**
     * @param 对appCategoryLogoUrl进行赋值
     */
	public void setAppCategoryLogoUrl(String appCategoryLogoUrl) {
		this.appCategoryLogoUrl = appCategoryLogoUrl;
	}

	/**
     * @return 返回 apps
     */
    @OneToMany(mappedBy = "appCategory", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    public Set<App> getApps()
    {
        return apps;
    }

    /**
     * @param 对apps进行赋值
     */
    public void setApps(Set<App> apps)
    {
        this.apps = apps;
    }

}
