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

import org.hibernate.validator.constraints.NotEmpty;

import com.sammyun.entity.BaseEntity;

/**
 * Entity - 平台
 * 
 * @author xutianlong
 * @version [版本号, Aug 24, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_app_client_platform")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_app_client_platform_sequence")
public class AppClientPlatform extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -1912383658395981516L;

    /** 平台名称 */
    private String plantFormName;

    /** 平台OS清单 */
    private Set<AppClientOs> appClientOs = new HashSet<AppClientOs>();
    
    /**
     * @return 返回 plantFormName
     */
    @NotEmpty
    @Column(length = 200)
    public String getPlantFormName()
    {
        return plantFormName;
    }

    /**
     * @param 对plantFormName进行赋值
     */
    public void setPlantFormName(String plantFormName)
    {
        this.plantFormName = plantFormName;
    }

    /**
     * @return 返回 appClientOs
     */
    @OneToMany(mappedBy = "appClientPlatform", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    public Set<AppClientOs> getAppClientOs()
    {
        return appClientOs;
    }

    /**
     * @param 对appClientOs进行赋值
     */
    public void setAppClientOs(Set<AppClientOs> appClientOs)
    {
        this.appClientOs = appClientOs;
    }
    
}
