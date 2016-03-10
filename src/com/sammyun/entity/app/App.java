package com.sammyun.entity.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.NumericField;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sammyun.BigDecimalNumericFieldBridge;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.util.JsonDateSerializer;

/**
 * Entity - 应用 <br />
 * 应用不可以删除，只可以下架和变更
 * 
 * @author xutianlong
 * @version [版本号, Jul 27, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_app")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_app_sequence")
public class App extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 5785764463677485244L;

    /** 应用系统 */
    public enum OperatingSystem
    {

        /** android */
        android,
        /** iphone **/
        ios,
        /** web混用 */
        hybird,
        /** 支持hbuilder */
        hbuilder
    }

    /** 运行方式 */
    public enum RunType
    {
        /** 内嵌应用 */
        build_in,
        /** 第三方(用于做广告使用，默认都是build_in) */
        thirdparty
    }

    /** 应用，一个系列App的只许有一个 parent */
    private App parent;

    /** 历史应用 */
    private Set<App> history = new HashSet<App>();

    /** 应用分类 */
    private AppCategory appCategory;

    /** 应用名称 */
    private String appName;

    /** 应用ID标识 */
    private String appId;

    /** 应用简介 */
    private String appDescription;

    /** 应用升级说明 */
    private String appUpDescription;

    /** 应用截图 （应用截图320*480，jpg\png格式，单张图片不超过50k） */
    private List<AppScreenshot> appScreenshots = new ArrayList<AppScreenshot>();

    /** 操作系统 */
    private OperatingSystem operatingSystem;

    private RunType runType;

    /** 仅当是OperatingSystem是 hybird和thirdparty需要使用 附件地址 */
    private String appAttachment;

    /** 第三方应用的下载地址 */
    private String installUrl;

    /** 应用图标 （支持大小为98*98pxPNG图片，大小不超过20k。） */
    private String logoAppImg;

    /**
     * 打开应用的前缀，例如： 1、pe://xshutong.com/Hybird/res/rsXX/index.html<br />
     * 2、pe://xshutong.com/schoolcalendar <br >
     * 1)、前缀：pe://xshutong.com (pe://为验证链接的合法性)<br >
     * 2)、Hybird/res/rsXX/index.html (具体的 Hybird链接地址)<br />
     * 3)、schoolcalendar（内置应用的ID,用于启动内置应用）
     */
    private String prefix;

    /*** 打开应用的URL地址 */
    private String openUrl;

    /** 如：56K，9.2M */
    private String appSize;

    /** 版本名称 */
    private String versionName;

    /** 版本号 */
    private String versionCode;

    /** 应用标识 英文名字，如:news，bus等 */
    private String appCode;

    /** 是否上架（默认false 未上架，true 上架），需要经过审核才可以 */
    private Boolean isOnline;

    /** 上架时间 */
    private Date onlineDate;

    /** 上架操作者 */
    private String onlineOperator;

    /** 主要针对外部应用需要审核（当审核通过后》显示为【待上架】） */
    private Boolean isAuditing;

    /** 审核人 */
    private String auditingOperator;

    /** 开发者 */
    private String developer;

    /** APP API 应用证书管理 原生应用不需要通过应用证书管理 即hybird和thirdparty需要使用 */
    private AppCredential appCredential;

    /** 是否收费 */
    private Boolean isCharge;

    /** 销售价 */
    private BigDecimal price;

    /** 下载是否使用积分兑换 （如果设置了收费，就算销售价，不算积分） */
    private Boolean isPoint;

    /** 应用下载积分兑换数 */
    private Long point;

    /** 应用统计 */
    private AppStat appStat;

    /** 角色 */
    private Set<AppRole> appRoles = new HashSet<AppRole>();

    /** 应用挂的学校 */
    private Set<DictSchool> dictSchools = new HashSet<DictSchool>();

    /** App评论 */
    private Set<AppReview> appReviews = new HashSet<AppReview>();

    /** 应用超市海报 清单 */
    private Set<AppPoster> appPosters = new HashSet<AppPoster>();

    /** 应用用户安装列表 */
    private Set<AppUser> appUsers = new HashSet<AppUser>();

    /** app关键词，用于做App检索 */
    private String keyWord;

    // 定义缓存统计
    /** 评价得分缓存名称 */
    public static final String AVGRATING_CACHE_NAME = "appAvgRating";

    /** 评价得分缓存更新间隔时间 */
    public static final int AVGRATING_CACHE_INTERVAL = 60000;

    /** 得分数量缓存名称 */
    public static final String COUNTRATING_CACHE_NAME = "appCountRating";

    /** 得分数量缓存更新间隔时间 */
    public static final int COUNTRATING_CACHE_INTERVAL = 60000;

    /** 应用安装用户量缓存名称 */
    public static final String COUNTUSER_CACHE_NAME = "appCountUser";

    /** 应用安装用户量缓存更新间隔时间 */
    public static final int COUNTUSER_CACHE_INTERVAL = 60000;

    /** 应用查看的数量缓存名称 */
    public static final String COUNTVIEW_CACHE_NAME = "appCountView";

    /** 应用查看的数量缓存更新间隔时间 */
    public static final int COUNTVIEW_CACHE_INTERVAL = 60000;

    /**
     * @return 返回 parent
     */
    @ManyToOne(fetch = FetchType.LAZY)
    public App getParent()
    {
        return parent;
    }

    /**
     * @param 对parent进行赋值
     */
    public void setParent(App parent)
    {
        this.parent = parent;
    }

    /**
     * @return 返回 history
     */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    public Set<App> getHistory()
    {
        return history;
    }

    /**
     * @param 对history进行赋值
     */
    public void setHistory(Set<App> history)
    {
        this.history = history;
    }

    /**
     * @return 返回 appCategory
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public AppCategory getAppCategory()
    {
        return appCategory;
    }

    /**
     * @param 对appCategory进行赋值
     */
    public void setAppCategory(AppCategory appCategory)
    {
        this.appCategory = appCategory;
    }

    /**
     * @return 返回 appName
     */
    @JsonProperty
    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
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
     * @return 返回 appId
     */
    @JsonProperty
    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
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
     * @return 返回 appDescription
     */
    @NotEmpty
    @Length(max = 1000)
    @Column(nullable = false)
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
     * @return 返回 appUpDescription
     */
    @Length(max = 1000)
    public String getAppUpDescription()
    {
        return appUpDescription;
    }

    /**
     * @param 对appUpDescription进行赋值
     */
    public void setAppUpDescription(String appUpDescription)
    {
        this.appUpDescription = appUpDescription;
    }

    /**
     * @return 返回 appScreenshots
     */
    @OneToMany(mappedBy = "app", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<AppScreenshot> getAppScreenshots()
    {
        return appScreenshots;
    }

    /**
     * @param 对appScreenshots进行赋值
     */
    public void setAppScreenshots(List<AppScreenshot> appScreenshots)
    {
        this.appScreenshots = appScreenshots;
    }

    /**
     * @return 返回 operatingSystem
     */
    @JsonProperty
    @NotEmpty
    @Length(max = 2)
    @Column(nullable = false)
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
     * @return 返回 runType
     */
    @NotEmpty
    @Length(max = 2)
    @Column(nullable = false)
    public RunType getRunType()
    {
        return runType;
    }

    /**
     * @param 对runType进行赋值
     */
    public void setRunType(RunType runType)
    {
        this.runType = runType;
    }

    /**
     * @return 返回 appAttachment
     */
    @Length(max = 500)
    public String getAppAttachment()
    {
        return appAttachment;
    }

    /**
     * @param 对appAttachment进行赋值
     */
    public void setAppAttachment(String appAttachment)
    {
        this.appAttachment = appAttachment;
    }

    /**
     * @return 返回 installUrl
     */
    @NotEmpty
    @Length(max = 500)
    @Column(nullable = false)
    public String getInstallUrl()
    {
        return installUrl;
    }

    /**
     * @param 对installUrl进行赋值
     */
    public void setInstallUrl(String installUrl)
    {
        this.installUrl = installUrl;
    }

    /**
     * @return 返回 logoAppImg
     */
    @NotEmpty
    @Length(max = 500)
    @Column(nullable = false)
    public String getLogoAppImg()
    {
        return logoAppImg;
    }

    /**
     * @param 对logoAppImg进行赋值
     */
    public void setLogoAppImg(String logoAppImg)
    {
        this.logoAppImg = logoAppImg;
    }

    /**
     * @return 返回 prefix
     */
    @NotEmpty
    @Length(max = 500)
    @Column(nullable = false)
    public String getPrefix()
    {
        return prefix;
    }

    /**
     * @param 对prefix进行赋值
     */
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    /**
     * @return 返回 openUrl
     */
    @NotEmpty
    @Length(max = 500)
    @Column(nullable = false)
    public String getOpenUrl()
    {
        return openUrl;
    }

    /**
     * @param 对openUrl进行赋值
     */
    public void setOpenUrl(String openUrl)
    {
        this.openUrl = openUrl;
    }

    /**
     * @return 返回 appSize
     */
    @NotEmpty
    @Length(max = 10)
    @Column(nullable = false)
    public String getAppSize()
    {
        return appSize;
    }

    /**
     * @param 对appSize进行赋值
     */
    public void setAppSize(String appSize)
    {
        this.appSize = appSize;
    }

    /**
     * @return 返回 versionName
     */
    @NotEmpty
    @Length(max = 50)
    @Column(nullable = false)
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
     * @return 返回 versionCode
     */
    @NotEmpty
    @Length(max = 10)
    @Column(nullable = false)
    public String getVersionCode()
    {
        return versionCode;
    }

    /**
     * @param 对versionCode进行赋值
     */
    public void setVersionCode(String versionCode)
    {
        this.versionCode = versionCode;
    }

    /**
     * @return 返回 appCode
     */
    @NotEmpty
    @Length(max = 10)
    @Column(nullable = false)
    public String getAppCode()
    {
        return appCode;
    }

    /**
     * @param 对appCode进行赋值
     */
    public void setAppCode(String appCode)
    {
        this.appCode = appCode;
    }

    /**
     * @return 返回 isOnline
     */
    @NotEmpty
    @Column(nullable = false)
    public Boolean getIsOnline()
    {
        return isOnline;
    }

    /**
     * @param 对isOnline进行赋值
     */
    public void setIsOnline(Boolean isOnline)
    {
        this.isOnline = isOnline;
    }

    /**
     * @return 返回 onlineOperator
     */
    public String getOnlineOperator()
    {
        return onlineOperator;
    }

    /**
     * @param 对onlineOperator进行赋值
     */
    public void setOnlineOperator(String onlineOperator)
    {
        this.onlineOperator = onlineOperator;
    }

    /**
     * @return 返回 isAuditing
     */
    @NotEmpty
    @Column(nullable = false)
    public Boolean getIsAuditing()
    {
        return isAuditing;
    }

    /**
     * @param 对isAuditing进行赋值
     */
    public void setIsAuditing(Boolean isAuditing)
    {
        this.isAuditing = isAuditing;
    }

    /**
     * @return 返回 auditingOperator
     */
    public String getAuditingOperator()
    {
        return auditingOperator;
    }

    /**
     * @param 对auditingOperator进行赋值
     */
    public void setAuditingOperator(String auditingOperator)
    {
        this.auditingOperator = auditingOperator;
    }

    /**
     * @return 返回 developer
     */
    public String getDeveloper()
    {
        return developer;
    }

    /**
     * @param 对developer进行赋值
     */
    public void setDeveloper(String developer)
    {
        this.developer = developer;
    }

    /**
     * @return 返回 appCredential
     */
    @OneToOne(mappedBy = "app", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public AppCredential getAppCredential()
    {
        return appCredential;
    }

    /**
     * @param 对appCredential进行赋值
     */
    public void setAppCredential(AppCredential appCredential)
    {
        this.appCredential = appCredential;
    }

    @NotNull
    @Column(nullable = false)
    public Boolean getIsCharge()
    {
        return isCharge;
    }

    /**
     * @param 对isCharge进行赋值
     */
    public void setIsCharge(Boolean isCharge)
    {
        this.isCharge = isCharge;
    }

    @JsonProperty
    @Field(store = Store.YES, index = Index.UN_TOKENIZED)
    @NumericField
    @FieldBridge(impl = BigDecimalNumericFieldBridge.class)
    @NotNull
    @Min(0)
    @Digits(integer = 12, fraction = 3)
    @Column(nullable = false, precision = 21, scale = 6)
    public BigDecimal getPrice()
    {
        return price;
    }

    /**
     * @param 对price进行赋值
     */
    public void setPrice(BigDecimal price)
    {
        this.price = price;
    }

    /**
     * @return 返回 isPoint
     */
    @NotNull
    @Column(nullable = false)
    public Boolean getIsPoint()
    {
        return isPoint;
    }

    /**
     * @param 对isPoint进行赋值
     */
    public void setIsPoint(Boolean isPoint)
    {
        this.isPoint = isPoint;
    }

    /**
     * @return 返回 point
     */
    @JsonProperty
    @NotNull(groups = Save.class)
    @Min(0)
    @Column(nullable = false)
    public Long getPoint()
    {
        return point;
    }

    /**
     * @param 对point进行赋值
     */
    public void setPoint(Long point)
    {
        this.point = point;
    }

    /**
     * @return 返回 appRoles
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_pe_app_app_role")
    public Set<AppRole> getAppRoles()
    {
        return appRoles;
    }

    /**
     * @param 对appRoles进行赋值
     */
    public void setAppRoles(Set<AppRole> appRoles)
    {
        this.appRoles = appRoles;
    }

    /**
     * 返回 应用挂的学校
     * 
     * @return 返回 dictSchools 应用挂的学校
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_pe_app_dict_school")
    public Set<DictSchool> getDictSchools()
    {
        return dictSchools;
    }

    /**
     * 设置 应用挂的学校
     * 
     * @param 对dictSchools进行赋值
     */
    public void setDictSchools(Set<DictSchool> dictSchools)
    {
        this.dictSchools = dictSchools;
    }

    /**
     * @return 返回 appReviews
     */
    @OneToMany(mappedBy = "app", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<AppReview> getAppReviews()
    {
        return appReviews;
    }

    /**
     * @param 对appReviews进行赋值
     */
    public void setAppReviews(Set<AppReview> appReviews)
    {
        this.appReviews = appReviews;
    }

    /**
     * @return 返回 appPosters
     */
    @OneToMany(mappedBy = "app", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<AppPoster> getAppPosters()
    {
        return appPosters;
    }

    /**
     * @param 对appPosters进行赋值
     */
    public void setAppPosters(Set<AppPoster> appPosters)
    {
        this.appPosters = appPosters;
    }

    /**
     * @return 返回 appStat
     */
    @OneToOne(mappedBy = "app", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public AppStat getAppStat()
    {
        return appStat;
    }

    /**
     * @param 对appStat进行赋值
     */
    public void setAppStat(AppStat appStat)
    {
        this.appStat = appStat;
    }

    /**
     * @return 返回 appUsers
     */
    @OneToMany(mappedBy = "app", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<AppUser> getAppUsers()
    {
        return appUsers;
    }

    /**
     * @param 对appUsers进行赋值
     */
    public void setAppUsers(Set<AppUser> appUsers)
    {
        this.appUsers = appUsers;
    }

    /**
     * @return 返回 keyWord
     */
    @Column(nullable = true, length = 200)
    public String getKeyWord()
    {
        return keyWord;
    }

    /**
     * @param 对keyWord进行赋值
     */
    public void setKeyWord(String keyWord)
    {
        this.keyWord = keyWord;
    }

    /**
     * @return 返回 onlineDate
     */
    @JsonSerialize(using = JsonDateSerializer.class)
    @Field(store = Store.YES, index = Index.UN_TOKENIZED)
    @DateBridge(resolution = Resolution.SECOND)
    @Column(nullable = true, updatable = false)
    public Date getOnlineDate()
    {
        return onlineDate;
    }

    /**
     * @param 对onlineDate进行赋值
     */
    public void setOnlineDate(Date onlineDate)
    {
        this.onlineDate = onlineDate;
    }

}
