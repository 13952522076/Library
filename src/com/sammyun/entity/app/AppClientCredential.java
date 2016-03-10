package com.sammyun.entity.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.sammyun.entity.BaseEntity;

/**
 * Entity - AppClient 应用证书管理
 * 
 * @author xutianlong
 * @version [版本号, Aug 22, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_app_client_credential")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_app_client_credential_sequence")
public class AppClientCredential extends BaseEntity
{
    
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -8066153647994372749L;

    /** 应用名称 */
    private String appName;

    private AppClientOs appClientOs;

    /** app key */
    private String appKey;

    /** app Secret */
    private String appSecret;

    /** 应用服务IP地址（应用安全选项，以逗号分割，最多20台） */
    private String ipList;

    /**
     * @return 返回 appName
     */
    @Column(length = 20)
    public String getAppName()
    {
        return appName;
    }

    /**
     * @param 对appName进行赋值
     */
    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    /**
     * @return 返回 appClientOs
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public AppClientOs getAppClientOs()
    {
        return appClientOs;
    }

    /**
     * @param 对appClientOs进行赋值
     */
    public void setAppClientOs(AppClientOs appClientOs)
    {
        this.appClientOs = appClientOs;
    }

    /**
     * @return 返回 appKey
     */
    @Column(length = 20)
    public String getAppKey()
    {
        return appKey;
    }

    /**
     * @param 对appKey进行赋值
     */
    public void setAppKey(String appKey)
    {
        this.appKey = appKey;
    }

    /**
     * @return 返回 appSecret
     */
    @Column(length = 50)
    public String getAppSecret()
    {
        return appSecret;
    }

    /**
     * @param 对appSecret进行赋值
     */
    public void setAppSecret(String appSecret)
    {
        this.appSecret = appSecret;
    }

    /**
     * @return 返回 ipList
     */
    @Column(length = 500)
    public String getIpList()
    {
        return ipList;
    }

    /**
     * @param 对ipList进行赋值
     */
    public void setIpList(String ipList)
    {
        this.ipList = ipList;
    }
}
