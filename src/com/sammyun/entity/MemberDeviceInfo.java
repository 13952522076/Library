/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity - 会员设备信息列表
 * 
 * @author xutianlong
 * @version 3.0
 */
@Entity
@Table(name = "t_pe_member_device_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_member_device_info_sequence")
public class MemberDeviceInfo extends BaseEntity
{

    private static final long serialVersionUID = -5196308821978071175L;

   /** ISO国家代码*/
    private String isoCountryCode;
    
    /** 移动厂商 比如 中国移动，联通，电信*/
    private String carrierName;
    
    /** 移动国家码 */
    private String mobileCountryCode;
    
    /** 设备名 */
    private String deviceName;

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

    /** 设备mac地址 */
    private String macAddress;

    /** 用户名 */
    private String userName;

    /** 机器默认语言 */
    private String localLanguage;
    
    /** app版本 */
    private String appver;
    
    /** app包 */
    private String appid;
    
    /** app环境 */
    private String env;

    /** 设备Token */
    private String deviceToken;

    /** 海拔高度 */
    private String altitude;
    
    /**速度  */
    private String speed;
    
    /** 经度 */
    private String longitude;
    
    /** 时区 */
    private String  timezone;
    
    /** 纬度 */
    private String  latitude;
    
    /** 精度 */
    private String  accuracy;
   
    /** 时间戳*/
    private String  timestamp;
    
    /** 朝向*/
    private String heading;
    
    /** 高度的精度（米） */
    private String  altitudeAccuracy;
    
    /** 会员 */
    private Member member;

    /**
     * @return 返回 设备名
     */
    @NotEmpty
    @Length(max = 100)
    @Column(nullable = false, length = 100)
    public String getDeviceName()
    {
        return deviceName;
    }

    /**
     * @param 对设备名进行赋值
     */
    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
    }

    /**
     * @return 返回 设备类型
     */
    @Length(max = 50)
    @Column(nullable = true, length = 50)
    public String getDeviceModel()
    {
        return deviceModel;
    }

    /**
     * @param 对设备类型进行赋值
     */
    public void setDeviceModel(String deviceModel)
    {
        this.deviceModel = deviceModel;
    }

    /**
     * @return 返回 设备系统类型
     */
    @Length(max = 20)
    @Column(nullable = true, length = 20)
    public String getDeviceOs()
    {
        return deviceOs;
    }

    /**
     * @param 对设备系统类型进行赋值
     */
    public void setDeviceOs(String deviceOs)
    {
        this.deviceOs = deviceOs;
    }

    /**
     * @return 返回 设备系统版本
     */
    @Length(max = 20)
    @Column(nullable = true, length = 20)
    public String getOsVersion()
    {
        return osVersion;
    }

    /**
     * @param 对设备系统版本进行赋值
     */
    public void setOsVersion(String osVersion)
    {
        this.osVersion = osVersion;
    }

    /**
     * @return 返回 设备mac地址
     */
    @Length(max = 50)
    @Column(nullable = true, length = 50)
    public String getMacAddress()
    {
        return macAddress;
    }

    /**
     * @param 对设备mac地址进行赋值
     */
    public void setMacAddress(String macAddress)
    {
        this.macAddress = macAddress;
    }

    /**
     * @return 返回 用户名
     */
    @Length(max = 50)
    @Column(nullable = true, length = 50)
    public String getUserName()
    {
        return userName;
    }

    /**
     * @param 对用户名进行赋值
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    /**
     * @return 返回 机器默认语言
     */
    @Length(max = 50)
    @Column(nullable = true, length = 50)
    public String getLocalLanguage()
    {
        return localLanguage;
    }

    /**
     * @param 对机器默认语言进行赋值
     */
    public void setLocalLanguage(String localLanguage)
    {
        this.localLanguage = localLanguage;
    }

    /**
     * @return 返回 设备uuid
     */
    @Length(max = 100)
    @Column(nullable = true, length = 100)
    public String getUuid()
    {
        return uuid;
    }

    /**
     * @param 对设备uuid进行赋值
     */
    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    /**
     * @return 返回 设备Token
     */
    @Length(max = 100)
    @Column(nullable = true, length = 100)
    public String getDeviceToken()
    {
        return deviceToken;
    }

    /**
     * @param 对设备Token进行赋值
     */
    public void setDeviceToken(String deviceToken)
    {
        this.deviceToken = deviceToken;
    }
    
    /**
     * @return 返回 carrierName
     */
    public String getCarrierName()
    {
        return carrierName;
    }

    /**
     * @param 对carrierName进行赋值
     */
    public void setCarrierName(String carrierName)
    {
        this.carrierName = carrierName;
    }

    /**
     * @return 返回 会员
     */
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

    /**
     * @return ISO国家代码
     */
    public String getIsoCountryCode()
    {
        return isoCountryCode;
    }

    /**
     * @param isoCountryCode
     *              ISO国家代码
     */
    public void setIsoCountryCode(String isoCountryCode)
    {
        this.isoCountryCode = isoCountryCode;
    }

    /**
     * @return 移动国家码
     */
    public String getMobileCountryCode()
    {
        return mobileCountryCode;
    }

    /**
     * @param mobileCountryCode
     *               移动国家码
     */
    public void setMobileCountryCode(String mobileCountryCode)
    {
        this.mobileCountryCode = mobileCountryCode;
    }

    /**
     * @return 系统名称
     */
    public String getOsName()
    {
        return osName;
    }

    /**
     * @param osName
     *         系统名称
     */
    public void setOsName(String osName)
    {
        this.osName = osName;
    }

    /**
     * @return  app版本
     */
    public String getAppver()
    {
        return appver;
    }

    /**
     * @param appver
     *         app版本
     */
    public void setAppver(String appver)
    {
        this.appver = appver;
    }

    /**
     * @return app包
     */
    public String getAppid()
    {
        return appid;
    }

    /**
     * @param appid
     *        app包
     */
    public void setAppid(String appid)
    {
        this.appid = appid;
    }
    
    /**
     * @return app环境
     */

    public String getEnv()
    {
        return env;
    }

    /**
     * @param env
     *        app环境
     */
    public void setEnv(String env)
    {
        this.env = env;
    }

    /**
     * @return  海拔高度
     */
    public String getAltitude()
    {
        return altitude;
    }
    
    /**
     * @param altitude
     *         海拔高度
     */
    public void setAltitude(String altitude)
    {
        this.altitude = altitude;
    }

    /**
     * @return 速度 
     */
    public String getSpeed()
    {
        return speed;
    }

    /**
     * @param speed
     *          速度 
     */
    public void setSpeed(String speed)
    {
        this.speed = speed;
    }

    /**
     * @return  经度 
     */
    public String getLongitude()
    {
        return longitude;
    }

    /**
     * @param longitude
     *           经度 
     */
    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    /**
     * @return 时区
     */
    public String getTimezone()
    {
        return timezone;
    }

    /**
     * @param timezone
     *           时区
     */
    public void setTimezone(String timezone)
    {
        this.timezone = timezone;
    }

    /**
     * @return  纬度
     */
    public String getLatitude()
    {
        return latitude;
    }

    /**
     * @param latitude
     *            纬度
     */
    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    /**
     * @return   精度
     */
    public String getAccuracy()
    {
        return accuracy;
    }
    
    /**
     * @param accuracy
     *            精度
     */
    public void setAccuracy(String accuracy)
    {
        this.accuracy = accuracy;
    }

    /**
     * @return  时间戳
     */
    public String getTimestamp()
    {
        return timestamp;
    }

    /**
     * @param timestamp
     *           时间戳
     */
    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    /**
     * @return  朝向
     */
    public String getHeading()
    {
        return heading;
    }

    /**
     * @param heading
     *            朝向
     */
    public void setHeading(String heading)
    {
        this.heading = heading;
    }

    /**
     * @return   高度的精度（米）
     */
    public String getAltitudeAccuracy()
    {
        return altitudeAccuracy;
    }

    /**
     * @param altitudeAccuracy
     *            高度的精度（米）
     */
    public void setAltitudeAccuracy(String altitudeAccuracy)
    {
        this.altitudeAccuracy = altitudeAccuracy;
    }

}
