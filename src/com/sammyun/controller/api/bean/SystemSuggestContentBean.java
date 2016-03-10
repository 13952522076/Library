package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "systemSuggestContentBean")
public class SystemSuggestContentBean
{
    /** 反馈内容 */
    private String suggestContent;

    /** 反馈时间 */
    private String suggestDate;

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

    /** app版本 */
    private String appver;

    /** app包 */
    private String appid;

    /** app环境 */
    private String env;
    
    /** 会员 */
    private Long memberId;

    @XmlElement
    public String getSuggestContent()
    {
        return suggestContent;
    }

    public void setSuggestContent(String suggestContent)
    {
        this.suggestContent = suggestContent;
    }

    @XmlElement
    public String getSuggestDate()
    {
        return suggestDate;
    }

    
    public void setSuggestDate(String suggestDate)
    {
        this.suggestDate = suggestDate;
    }

    @XmlElement
    public String getDeviceModel()
    {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel)
    {
        this.deviceModel = deviceModel;
    }

    @XmlElement
    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    @XmlElement
    public String getOsName()
    {
        return osName;
    }

    public void setOsName(String osName)
    {
        this.osName = osName;
    }

    @XmlElement
    public String getDeviceOs()
    {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs)
    {
        this.deviceOs = deviceOs;
    }

    @XmlElement
    public String getOsVersion()
    {
        return osVersion;
    }

    public void setOsVersion(String osVersion)
    {
        this.osVersion = osVersion;
    }

    @XmlElement
    public String getAppver()
    {
        return appver;
    }

    public void setAppver(String appver)
    {
        this.appver = appver;
    }

    @XmlElement
    public String getAppid()
    {
        return appid;
    }

    public void setAppid(String appid)
    {
        this.appid = appid;
    }

    @XmlElement
    public String getEnv()
    {
        return env;
    }

    public void setEnv(String env)
    {
        this.env = env;
    }

    @XmlElement
    public Long getMemberId()
    {
        return memberId;
    }

    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }
}
