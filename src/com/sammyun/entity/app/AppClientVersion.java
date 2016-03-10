package com.sammyun.entity.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * Entity - 终端版本管理
 * 
 * @author xutianlong
 * @version [版本号, Aug 10, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_app_client_version")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_app_client_version_sequence")
public class AppClientVersion extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -8447864459027363966L;

    /** 应用系统 */
    public enum OperatingSystem
    {

        /** android */
        android,
        /** iphone **/
        ios
    }

    /** 升级类型 */
    public enum TypeOfUpgrade
    {
        /** 无需升级 */
        noneed,
        /** 可选升级 */
        optional,
        /** 强制升级 */
        forced
    }

    /** 显示名称 */
    private String versionName;

    /** 终端应用版本ID */
    private String appId;

    /** 升级消息 */
    private String description;

    /** 下载地址 */
    private String urlDownload;

    /** 文件大小 */
    private String fileSize;

    /** 版本号 */
    private String versionNumber;

    /** 升级消息是否做了推送 */
    private Boolean flagPublish;

    /** 操作系统 */
    private OperatingSystem operatingSystem;

    /** 升级类型 */
    private TypeOfUpgrade typeOfUpgrade;

    /**
     * @return 返回 description
     */
    @Column(length = 500)
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
     * @return 返回 urlDownload
     */
    @Column(length = 100)
    public String getUrlDownload()
    {
        return urlDownload;
    }

    /**
     * @param 对urlDownload进行赋值
     */
    public void setUrlDownload(String urlDownload)
    {
        this.urlDownload = urlDownload;
    }

    /**
     * @return 返回 fileSize
     */
    public String getFileSize()
    {
        return fileSize;
    }

    /**
     * @param 对fileSize进行赋值
     */
    public void setFileSize(String fileSize)
    {
        this.fileSize = fileSize;
    }

    /**
     * @return 返回 versionName
     */
    public String getVersionName()
    {
        return versionName;
    }

    /**
     * @param 对versionName进行赋值
     */
    public void setVersionName(String versionName)
    {
        this.versionName = versionName;
    }
    
    /**
     * @return 返回 appId
     */
    public String getAppId()
    {
        return appId;
    }

    /**
     * @param 对appId进行赋值
     */
    public void setAppId(String appId)
    {
        this.appId = appId;
    }

    /**
     * @return 返回 versionNumber
     */
    public String getVersionNumber()
    {
        return versionNumber;
    }

    /**
     * @param 对versionNumber进行赋值
     */
    public void setVersionNumber(String versionNumber)
    {
        this.versionNumber = versionNumber;
    }

    /**
     * @return 返回 flagPublish
     */
    @Column(columnDefinition = "Boolean default false")
    public Boolean getFlagPublish()
    {
        return flagPublish;
    }

    /**
     * @param 对flagPublish进行赋值
     */
    public void setFlagPublish(Boolean flagPublish)
    {
        this.flagPublish = flagPublish;
    }

    /**
     * @return 返回 operatingSystem
     */
    public OperatingSystem getOperatingSystem()
    {
        return operatingSystem;
    }

    /**
     * @param 对operatingSystem进行赋值
     */
    public void setOperatingSystem(OperatingSystem operatingSystem)
    {
        this.operatingSystem = operatingSystem;
    }

    /**
     * @return 返回 typeOfUpgrade
     */
    public TypeOfUpgrade getTypeOfUpgrade()
    {
        return typeOfUpgrade;
    }

    /**
     * @param 对typeOfUpgrade进行赋值
     */
    public void setTypeOfUpgrade(TypeOfUpgrade typeOfUpgrade)
    {
        this.typeOfUpgrade = typeOfUpgrade;
    }
}
