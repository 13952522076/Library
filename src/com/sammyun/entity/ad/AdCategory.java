package com.sammyun.entity.ad;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.sammyun.entity.BaseEntity;

/**
 * Entity - 广告分类 <一句话功能简述> <功能详细描述> <br />
 * 
 * @author xutianlong
 * @version [版本号, Aug 4, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_ad_app_category")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_ad_app_category_sequence")
public class AdCategory extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -6526799491235824123L;

    /** 应用分类名称 */
    private String name;
    
    /** 是否启用 (true-使用，false-禁用) */
    private Boolean status;

    /** 广告类型 */
    private Set<Ad> ads = new HashSet<Ad>();

    /**
     * @return 返回 name
     */
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
     * @return 返回 ads
     */
    @OneToMany(mappedBy = "adCategory", fetch = FetchType.LAZY)
    public Set<Ad> getAds()
    {
        return ads;
    }

    /**
     * @param 对ads进行赋值
     */
    public void setAds(Set<Ad> ads)
    {
        this.ads = ads;
    }
    
    /**
     * @return 返回 status
     */
    @NotNull
    @Column(nullable = false, columnDefinition = "Boolean default true")
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
    
}
