package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.ad.Ad.AdPosition;
import com.sammyun.entity.ad.Ad.Platform;
import com.sammyun.entity.ad.Ad.ShowType;
import com.sammyun.entity.ad.Ad.SimType;
import com.sammyun.entity.ad.Ad.Type;

@XmlRootElement(name = "adListBean")
public class AdListBean
{
    /** member id */
    private Long memberId;

    /** 类型 */
    private Type type;
    
    /** 应用平台 */
    private Platform platform;

    /** 广告处于App的位置 */
    private AdPosition adPosition;

    /**广告弹出类型*/
    private ShowType showType;

    /** 定向SIM卡用户 */
    private SimType simType;

    /** 所在地区名称 */
    private String adAreaName;

    /** 定向设备 */
    private String deviceType;

    /** 定向网络类型 */
    private String netType;

    /** 定向运营商 */
    private String netWork;

    /** 定向投放时间段 */
    private String slotTime;

    /**分页信息*/
    private PageModel page;
    
    @XmlElement
    public Long getMemberId()
    {
        return memberId;
    }

    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    @XmlElement
    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    @XmlElement
    public Platform getPlatform()
    {
        return platform;
    }

    public void setPlatform(Platform platform)
    {
        this.platform = platform;
    }

    @XmlElement
    public AdPosition getAdPosition()
    {
        return adPosition;
    }

    public void setAdPosition(AdPosition adPosition)
    {
        this.adPosition = adPosition;
    }

    @XmlElement
    public ShowType getShowType()
    {
        return showType;
    }

    public void setShowType(ShowType showType)
    {
        this.showType = showType;
    }

    @XmlElement
    public SimType getSimType()
    {
        return simType;
    }

    public void setSimType(SimType simType)
    {
        this.simType = simType;
    }

    @XmlElement
    public String getAdAreaName()
    {
        return adAreaName;
    }

    public void setAdAreaName(String adAreaName)
    {
        this.adAreaName = adAreaName;
    }

    @XmlElement
    public String getDeviceType()
    {
        return deviceType;
    }

    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }

    @XmlElement
    public String getNetType()
    {
        return netType;
    }

    public void setNetType(String netType)
    {
        this.netType = netType;
    }

    @XmlElement
    public String getNetWork()
    {
        return netWork;
    }

    public void setNetWork(String netWork)
    {
        this.netWork = netWork;
    }

    @XmlElement
    public String getSlotTime()
    {
        return slotTime;
    }

    public void setSlotTime(String slotTime)
    {
        this.slotTime = slotTime;
    }

    @XmlElement
    public PageModel getPage()
    {
        return page;
    }

    public void setPage(PageModel page)
    {
        this.page = page;
    } 
    
}
