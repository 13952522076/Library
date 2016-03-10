package com.sammyun.entity.app;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.NotEmpty;

import com.sammyun.entity.BaseEntity;

/**
 * @author xutianlong
 * @version [版本号, Aug 24, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_app_client_os")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_app_client_os_sequence")
public class AppClientOs extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 190725384707261134L;

    /** 系统Key */
    private String osKey;

    /** 系统Name */
    private String osName;

    /** 隶属平台 */
    private AppClientPlatform appClientPlatform;

    /** 应用证书清单 */
    private Set<AppClientCredential> appClientCredentials = new HashSet<AppClientCredential>();

    /**
     * @return 返回 osKey
     */
    @NotEmpty
    @Column(length = 50)
    public String getOsKey()
    {
        return osKey;
    }

    /**
     * @param 对osKey进行赋值
     */
    public void setOsKey(String osKey)
    {
        this.osKey = osKey;
    }

    /**
     * @return 返回 osName
     */
    @NotEmpty
    @Column(length = 50)
    public String getOsName()
    {
        return osName;
    }

    /**
     * @param 对osName进行赋值
     */
    public void setOsName(String osName)
    {
        this.osName = osName;
    }

    /**
     * @return 返回 appClientPlatform
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public AppClientPlatform getAppClientPlatform()
    {
        return appClientPlatform;
    }

    /**
     * @param 对appClientPlatform进行赋值
     */
    public void setAppClientPlatform(AppClientPlatform appClientPlatform)
    {
        this.appClientPlatform = appClientPlatform;
    }

    /**
     * @return 返回 appClientCredentials
     */
    @OneToMany(mappedBy = "appClientOs", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    public Set<AppClientCredential> getAppClientCredentials()
    {
        return appClientCredentials;
    }

    /**
     * @param 对appClientCredentials进行赋值
     */
    public void setAppClientCredentials(Set<AppClientCredential> appClientCredentials)
    {
        this.appClientCredentials = appClientCredentials;
    }

}
