/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.entity.ad;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.OrderEntity;
import com.sammyun.entity.dict.DictSchool;

/**
 * Entity - 广告
 * 
 * @author xutianlong
 * @version [版本号, Aug 26, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_ad")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_ad_sequence")
public class Ad extends OrderEntity
{

    private static final long serialVersionUID = -1307743303786909390L;

    /**
     * 类型
     */
    public enum Type
    {

        /** 应用推广 */
        app,

        /** 网站推广 */
        website
    }

    public enum Platform
    {
        /** 安卓 */
        android,
        /** IOS */
        ios
    }

    /**
     * 处于App的什么位置
     * 
     * @author xutianlong
     * @version [版本号, Aug 27, 2015]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    public enum AdPosition
    {
        /** 启动页 */
        start,
        /** 首页海报页 */
        index,
        /** 新闻页 */
        news,
        /** 成长记 */
        gd
    }

    /** 广告类型 */
    public enum SimType
    {
        /** 不限制 */
        all,
        /** 仅限带有SIM卡的用户 */
        sim
    }

    /** 广告弹出类型 */
    public enum ShowType
    {
        /** 弹出 */
        pop,

        /** 当前界面打开 */
        inside
    }

    /** 分类 */
    private AdCategory adCategory;

    /** 广告名称 */
    private String adName;

    /** 广告描述 */
    private String content;

    /** 广告图片 (封面图片) */
    private String adImage;

    /** 类型 */
    private Type type;

    /** 应用平台 */
    private Platform platform;

    /** 应用地址（type=app使用） */
    private String adPackageUrl;

    /** 应用名称 （type=app使用） */
    private String appName;

    /** 应用作者（type=app使用） */
    private String appAuthor;

    /** 应用描述 （type=app使用） */
    private String appDescription;

    /** 广告链接(链接地址)（type=website使用）,需要验证是否正确的网址 */
    private String adSite;

    /** 投放时间段 */
    /** 起始日期 （YYYY-DD-MM） */
    private Date beginDate;

    /** 结束日期 （YYYY-DD-MM） */
    private Date endDate;

    /** 是否为草稿 默认为否 */
    private Boolean isDraft;

    /** 广告处于App的位置 */
    private AdPosition adPosition;

    /** 广告弹出类型 */
    private ShowType showType;

    /** 定向SIM卡用户 */
    private SimType simType;

    /** 广告投放区域 */
    private List<AdArea> adAreas = new ArrayList<AdArea>();

    /** 定向设备 */
    private List<AdDeviceType> adDeviceTypes = new ArrayList<AdDeviceType>();

    /** 定向网络类型 */
    private List<AdNetType> adNetTypes = new ArrayList<AdNetType>();

    /** 定向运营商 */
    private List<AdNetWork> adNetWorks = new ArrayList<AdNetWork>();

    /** 定向投放时间段 */
    private List<AdTimeSlot> adTimeSlots = new ArrayList<AdTimeSlot>();

    /** 定向学校 */
    private List<DictSchool> adDictSchools = new ArrayList<DictSchool>();

    /** 是否上架 */
    private Boolean isOnLine;

    /**
     * @return 返回 adAppCategory
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public AdCategory getAdCategory()
    {
        return adCategory;
    }

    /**
     * @param 对adAppCategory进行赋值
     */
    public void setAdCategory(AdCategory adCategory)
    {
        this.adCategory = adCategory;
    }

    /**
     * 获取标题
     * 
     * @return 标题
     */
    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
    public String getAdName()
    {
        return adName;
    }

    /**
     * 设置标题
     * 
     * @param title 标题
     */
    public void setAdName(String adName)
    {
        this.adName = adName;
    }

    /**
     * 获取类型
     * 
     * @return 类型
     */
    @NotNull
    @Column(nullable = false)
    public Type getType()
    {
        return type;
    }

    /**
     * 设置类型
     * 
     * @param type 类型
     */
    public void setType(Type type)
    {
        this.type = type;
    }

    /**
     * 获取应用平台
     * 
     * @return 应用平台
     */
    public Platform getPlatform()
    {
        return platform;
    }

    /**
     * 设置应用平台
     * 
     * @param palPlatform 应用平台
     */
    public void setPlatform(Platform platform)
    {
        this.platform = platform;
    }

    /**
     * 获取广告描述
     * 
     * @return 广告描述
     */
    @Lob
    public String getContent()
    {
        return content;
    }

    /**
     * 设置广告描述
     * 
     * @param content 广告描述
     */
    public void setContent(String content)
    {
        this.content = content;
    }

    /**
     * 获取起始日期
     * 
     * @return 起始日期
     */
    public Date getBeginDate()
    {
        return beginDate;
    }

    /**
     * 设置起始日期
     * 
     * @param beginDate 起始日期
     */
    public void setBeginDate(Date beginDate)
    {
        this.beginDate = beginDate;
    }

    /**
     * 获取结束日期
     * 
     * @return 结束日期
     */
    public Date getEndDate()
    {
        return endDate;
    }

    /**
     * 设置结束日期
     * 
     * @param endDate 结束日期
     */
    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    /**
     * 判断是否已开始
     * 
     * @return 是否已开始
     */
    @Transient
    public boolean hasBegun()
    {
        return getBeginDate() == null || new Date().after(getBeginDate());
    }

    /**
     * 判断是否已结束
     * 
     * @return 是否已结束
     */
    @Transient
    public boolean hasEnded()
    {
        return getEndDate() != null && new Date().after(getEndDate());
    }

    /**
     * @return 返回 adPackageUrl
     */
    @Column(length = 300)
    public String getAdPackageUrl()
    {
        return adPackageUrl;
    }

    /**
     * @param 对adPackageUrl进行赋值
     */
    public void setAdPackageUrl(String adPackageUrl)
    {
        this.adPackageUrl = adPackageUrl;
    }

    /**
     * @return 返回 appName
     */
    @Column(length = 30)
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
     * @return 返回 appAuthor
     */
    @Column(length = 50)
    public String getAppAuthor()
    {
        return appAuthor;
    }

    /**
     * @param 对appAuthor进行赋值
     */
    public void setAppAuthor(String appAuthor)
    {
        this.appAuthor = appAuthor;
    }

    /**
     * @return 返回 appDescription
     */
    @Column(length = 300)
    public String getAppDescription()
    {
        return appDescription;
    }

    /**
     * @param 对appDescription进行赋值
     */
    public void setAppDescription(String appDescription)
    {
        this.appDescription = appDescription;
    }

    /**
     * @return 返回 adSite
     */
    @Column(length = 300)
    public String getAdSite()
    {
        return adSite;
    }

    /**
     * @param 对adSite进行赋值
     */
    public void setAdSite(String adSite)
    {
        this.adSite = adSite;
    }

    /**
     * @return 返回 adAreas
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_pe_ad_adarea")
    @OrderBy("order asc")
    public List<AdArea> getAdAreas()
    {
        return adAreas;
    }

    /**
     * @param 对adAreas进行赋值
     */
    public void setAdAreas(List<AdArea> adAreas)
    {
        this.adAreas = adAreas;
    }

    /**
     * @return 返回 adDeviceTypes
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_pe_ad_addevice_type")
    @OrderBy("order asc")
    public List<AdDeviceType> getAdDeviceTypes()
    {
        return adDeviceTypes;
    }

    /**
     * @param 对adDeviceTypes进行赋值
     */
    public void setAdDeviceTypes(List<AdDeviceType> adDeviceTypes)
    {
        this.adDeviceTypes = adDeviceTypes;
    }

    /**
     * @return 返回 adNetTypes
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_pe_ad_adnet_type")
    @OrderBy("order asc")
    public List<AdNetType> getAdNetTypes()
    {
        return adNetTypes;
    }

    /**
     * @param 对adNetTypes进行赋值
     */
    public void setAdNetTypes(List<AdNetType> adNetTypes)
    {
        this.adNetTypes = adNetTypes;
    }

    /**
     * @return 返回 adNetWorks
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_pe_ad_ad_network")
    @OrderBy("order asc")
    public List<AdNetWork> getAdNetWorks()
    {
        return adNetWorks;
    }

    /**
     * @param 对adNetWorks进行赋值
     */
    public void setAdNetWorks(List<AdNetWork> adNetWorks)
    {
        this.adNetWorks = adNetWorks;
    }

    /**
     * @return 返回 adTimeSlots
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_pe_ad_ad_time_slot")
    @OrderBy("order asc")
    public List<AdTimeSlot> getAdTimeSlots()
    {
        return adTimeSlots;
    }

    /**
     * @param 对adTimeSlots进行赋值
     */
    public void setAdTimeSlots(List<AdTimeSlot> adTimeSlots)
    {
        this.adTimeSlots = adTimeSlots;
    }

    /**
     * @return 返回 adPosition
     */
    public AdPosition getAdPosition()
    {
        return adPosition;
    }

    /**
     * @param 对adPosition进行赋值
     */
    public void setAdPosition(AdPosition adPosition)
    {
        this.adPosition = adPosition;
    }

    /**
     * @return 返回 isOnLine
     */
    public Boolean getIsOnLine()
    {
        return isOnLine;
    }

    /**
     * @param 对isOnLine进行赋值
     */
    public void setIsOnLine(Boolean isOnLine)
    {
        this.isOnLine = isOnLine;
    }

    /**
     * @return 返回 simType
     */
    public SimType getSimType()
    {
        return simType;
    }

    /**
     * @param 对simType进行赋值
     */
    public void setSimType(SimType simType)
    {
        this.simType = simType;
    }

    /**
     * @return 返回 adImage
     */
    @Column(length = 300)
    public String getAdImage()
    {
        return adImage;
    }

    /**
     * @param 对adImage进行赋值
     */
    public void setAdImage(String adImage)
    {
        this.adImage = adImage;
    }

    /**
     * @return 返回 showType
     */
    public ShowType getShowType()
    {
        return showType;
    }

    /**
     * @param 对showType进行赋值
     */
    public void setShowType(ShowType showType)
    {
        this.showType = showType;
    }

    /**
     * @return 返回adDictSchools
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_pe_ad_dict_school")
    public List<DictSchool> getAdDictSchools()
    {
        return adDictSchools;
    }

    /**
     * @param 对adDictSchools进行赋值
     */
    public void setAdDictSchools(List<DictSchool> adDictSchools)
    {
        this.adDictSchools = adDictSchools;
    }

    /**
     * @return 返回 isDraft
     */
    public Boolean getIsDraft()
    {
        return isDraft;
    }

    /**
     * @param 对isDraft进行赋值
     */
    public void setIsDraft(Boolean isDraft)
    {
        this.isDraft = isDraft;
    }
}
