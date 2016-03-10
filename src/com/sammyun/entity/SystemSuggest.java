package com.sammyun.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity - 意见反馈
 * 
 * @author xutianlong
 * @version [版本号, Apr 22, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_system_suggest")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_system_suggest_sequence")
public class SystemSuggest extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -2804079549923003846L;

    /** 反馈内容 */
    private String suggestContent;

    /** 反馈时间 */
    private Date suggestDate;

    /** 联系方式 （手机/邮箱） */
    private String contactInfo;

    /** 设备类型(如HuaWei C8815，SM-G900h，iphone6.2，iphone5.2) */
    private String deviceModel;

    /** 设备UDID */
    private String uuid;

    /** 系统名称 */
    private String osName;

    /** 设备系统类型（iphone，android） */
    private String deviceOs;

    /** 设备系统版本 */
    private String osVersion;

    /** 用户名 */
    private String userName;

    /** app版本 */
    private String appver;

    /** app包 */
    private String appid;

    /** app环境 */
    private String env;

    /** 会员 */
    private Member member;

    /**
     * @return 返回 suggestContent
     */
    public String getSuggestContent()
    {
        return suggestContent;
    }

    /**
     * @param 对suggestContent进行赋值
     */
    public void setSuggestContent(String suggestContent)
    {
        this.suggestContent = suggestContent;
    }

    /**
     * @return 返回 suggestDate
     */
    public Date getSuggestDate()
    {
        return suggestDate;
    }

    /**
     * @param 对suggestDate进行赋值
     */
    public void setSuggestDate(Date suggestDate)
    {
        this.suggestDate = suggestDate;
    }

    /**
     * @return 返回 contactInfo
     */
    public String getContactInfo()
    {
        return contactInfo;
    }

    /**
     * @param 对contactInfo进行赋值
     */
    public void setContactInfo(String contactInfo)
    {
        this.contactInfo = contactInfo;
    }

    /**
     * @return 返回 deviceModel
     */
    public String getDeviceModel()
    {
        return deviceModel;
    }

    /**
     * @param 对deviceModel进行赋值
     */
    public void setDeviceModel(String deviceModel)
    {
        this.deviceModel = deviceModel;
    }

    /**
     * @return 返回 uuid
     */
    public String getUuid()
    {
        return uuid;
    }

    /**
     * @param 对uuid进行赋值
     */
    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    /**
     * @return 返回 osName
     */
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
     * @return 返回 deviceOs
     */
    public String getDeviceOs()
    {
        return deviceOs;
    }

    /**
     * @param 对deviceOs进行赋值
     */
    public void setDeviceOs(String deviceOs)
    {
        this.deviceOs = deviceOs;
    }

    /**
     * @return 返回 osVersion
     */
    public String getOsVersion()
    {
        return osVersion;
    }

    /**
     * @param 对osVersion进行赋值
     */
    public void setOsVersion(String osVersion)
    {
        this.osVersion = osVersion;
    }

    /**
     * @return 返回 userName
     */
    public String getUserName()
    {
        return userName;
    }

    /**
     * @param 对userName进行赋值
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * @return 返回 appver
     */
    public String getAppver()
    {
        return appver;
    }

    /**
     * @param 对appver进行赋值
     */
    public void setAppver(String appver)
    {
        this.appver = appver;
    }

    /**
     * @return 返回 appid
     */
    public String getAppid()
    {
        return appid;
    }

    /**
     * @param 对appid进行赋值
     */
    public void setAppid(String appid)
    {
        this.appid = appid;
    }

    /**
     * @return 返回 env
     */
    public String getEnv()
    {
        return env;
    }

    /**
     * @param 对env进行赋值
     */
    public void setEnv(String env)
    {
        this.env = env;
    }
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Member getMember()
    {
        return member;
    }

    /**
     * @param 对会员进行赋值
     */
    public void setMember(Member member)
    {
        this.member = member;
    }

}
