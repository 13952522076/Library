package com.sammyun.entity.campusviewImg;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictSchool;

/**
 * Entity - 校园风光表
 * 


 */
@Entity
@Table(name = "t_pe_campusview_img")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_campusview_img_sequence")
public class CampusviewImg extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 5092964685112877542L;

    /** 创建者 */
    private String creator;

    /** 最近更新时间 */
    private Date lastUpdateTime;

    /** 最近更新人 */
    private String lastUpdator;

    /** 图片描述 */
    private String description;

    /** 点赞次数 */
    private BigDecimal favoriteCount;

    /** 纬度 */
    private String latitude;

    /** 经度 */
    private String longitude;

    /** 位置名称 */
    private String locationName;

    /** 封面图片 */
    private String coverImage;

    /** 状态 （0未屏蔽，1屏蔽） */
    private Integer status;

    /** 查看次数 */
    private BigDecimal viewCount;

    /** 隶属的学校 */
    private DictSchool dictSchool;

    private List<CampusviewImageAttach> campusviewImageAttachs = new ArrayList<CampusviewImageAttach>();

    @Column(name = "creator", scale = 0)
    public String getCreator()
    {
        return this.creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_update_time", nullable = false, length = 19)
    public Date getLastUpdateTime()
    {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * @return 返回 lastUpdator
     */
    public String getLastUpdator()
    {
        return lastUpdator;
    }

    /**
     * @param 对lastUpdator进行赋值
     */
    public void setLastUpdator(String lastUpdator)
    {
        this.lastUpdator = lastUpdator;
    }

//    @Column(name = "description", length = 1000)
    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Column(name = "favorite_count", scale = 0)
    public BigDecimal getFavoriteCount()
    {
        return this.favoriteCount;
    }

    public void setFavoriteCount(BigDecimal favoriteCount)
    {
        this.favoriteCount = favoriteCount;
    }

    @Column(name = "latitude", length = 60)
    public String getLatitude()
    {
        return this.latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    @Column(name = "location_name", length = 200)
    public String getLocationName()
    {
        return this.locationName;
    }

    public void setLocationName(String locationName)
    {
        this.locationName = locationName;
    }

    @Column(name = "longitude", length = 60)
    public String getLongitude()
    {
        return this.longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getCoverImage()
    {
        return coverImage;
    }

    public void setCoverImage(String coverImage)
    {
        this.coverImage = coverImage;
    }

    @Column(name = "status", precision = 10, scale = 0)
    public Integer getStatus()
    {
        return this.status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    @Column(name = "view_count", scale = 0)
    public BigDecimal getViewCount()
    {
        return this.viewCount;
    }

    public void setViewCount(BigDecimal viewCount)
    {
        this.viewCount = viewCount;
    }

    /**
     * @return 返回 dictSchool
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictSchool getDictSchool()
    {
        return dictSchool;
    }

    /**
     * @param 对dictSchool进行赋值
     */
    public void setDictSchool(DictSchool dictSchool)
    {
        this.dictSchool = dictSchool;
    }

    /**
     * @return 返回 campusviewImageAttachs
     */
    @OneToMany(mappedBy = "campusviewImg", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @ElementCollection(targetClass = CampusviewImageAttach.class, fetch = FetchType.LAZY)
    //@CollectionTable(name = "xx_product_column_rotation_image", joinColumns = @JoinColumn(name = "campusviewImg", referencedColumnName = "id"))
    public List<CampusviewImageAttach> getCampusviewImageAttachs()
    {
        return campusviewImageAttachs;
    }

    /**
     * @param 对campusviewImageAttachs进行赋值
     */
    public void setCampusviewImageAttachs(List<CampusviewImageAttach> campusviewImageAttachs)
    {
        this.campusviewImageAttachs = campusviewImageAttachs;
    }

}
