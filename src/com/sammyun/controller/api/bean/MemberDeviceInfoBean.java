package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "memberDeviceInfoBean")
public class MemberDeviceInfoBean
{
    /** ISO国家代码 */
    private String isoCountryCode;

    /** 移动厂商 比如 中国移动，联通，电信 */
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

    /** 速度 */
    private String speed;

    /** 经度 */
    private String longitude;

    /** 时区 */
    private String timezone;

    /** 纬度 */
    private String latitude;

    /** 精度 */
    private String accuracy;

    /** 时间戳 */
    private String timestamp;

    /** 朝向 */
    private String heading;

    /** 高度的精度（米） */
    private String altitudeAccuracy;

    @XmlElement
    public String getIsoCountryCode()
    {
        return isoCountryCode;
    }

    public void setIsoCountryCode(String isoCountryCode)
    {
        this.isoCountryCode = isoCountryCode;
    }

    @XmlElement
    public String getCarrierName()
    {
        return carrierName;
    }

    public void setCarrierName(String carrierName)
    {
        this.carrierName = carrierName;
    }

    @XmlElement
    public String getMobileCountryCode()
    {
        return mobileCountryCode;
    }

    public void setMobileCountryCode(String mobileCountryCode)
    {
        this.mobileCountryCode = mobileCountryCode;
    }

    @XmlElement
    public String getDeviceName()
    {
        return deviceName;
    }

    public void setDeviceName(String deviceName)
    {
        this.deviceName = deviceName;
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
    public String getMacAddress()
    {
        return macAddress;
    }

    public void setMacAddress(String macAddress)
    {
        this.macAddress = macAddress;
    }

    @XmlElement
    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    @XmlElement
    public String getLocalLanguage()
    {
        return localLanguage;
    }

    public void setLocalLanguage(String localLanguage)
    {
        this.localLanguage = localLanguage;
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
    public String getDeviceToken()
    {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken)
    {
        this.deviceToken = deviceToken;
    }

    @XmlElement
    public String getAltitude()
    {
        return altitude;
    }

    public void setAltitude(String altitude)
    {
        this.altitude = altitude;
    }

    @XmlElement
    public String getSpeed()
    {
        return speed;
    }

    public void setSpeed(String speed)
    {
        this.speed = speed;
    }

    @XmlElement
    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    @XmlElement
    public String getTimezone()
    {
        return timezone;
    }

    public void setTimezone(String timezone)
    {
        this.timezone = timezone;
    }

    @XmlElement
    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    @XmlElement
    public String getAccuracy()
    {
        return accuracy;
    }

    public void setAccuracy(String accuracy)
    {
        this.accuracy = accuracy;
    }

    @XmlElement
    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    @XmlElement
    public String getHeading()
    {
        return heading;
    }

    public void setHeading(String heading)
    {
        this.heading = heading;
    }

    @XmlElement
    public String getAltitudeAccuracy()
    {
        return altitudeAccuracy;
    }

    public void setAltitudeAccuracy(String altitudeAccuracy)
    {
        this.altitudeAccuracy = altitudeAccuracy;
    }

}
