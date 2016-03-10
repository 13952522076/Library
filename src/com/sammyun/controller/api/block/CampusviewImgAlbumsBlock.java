package com.sammyun.controller.api.block;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "campusviewImgAlbumsBlock")
public class CampusviewImgAlbumsBlock
{
    private Long id;
    
    /** 最近更新时间 */
    private String lastUpdateTime;

    /** 图片描述 */
    private String description;

    /** 点赞次数 */
    private String favoriteCount;

    /** 纬度 */
    private String latitude;

    /** 经度 */
    private String longitude;

    /** 位置名称 */
    private String locationName;

    /** 查看次数 */
    private String viewCount;
    
    /** 封面图片 */
    private String coverImage;

    /** 最近更新人 */
    private String lastUpdator;
    
    /** 相册图片*/
    private List<CampusviewImgAlbumsAttachBlock> campusviewImageAttachs;

    @XmlElement
    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    @XmlElement
    public String getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }
    
    @XmlElement
    public String getLastUpdator()
    {
        return lastUpdator;
    }

    public void setLastUpdator(String lastUpdator)
    {
        this.lastUpdator = lastUpdator;
    }

    @XmlElement
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
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
    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    @XmlElement
    public String getLocationName()
    {
        return locationName;
    }

    public void setLocationName(String locationName)
    {
        this.locationName = locationName;
    }

    @XmlElement
    public List<CampusviewImgAlbumsAttachBlock> getCampusviewImageAttachs()
    {
        return campusviewImageAttachs;
    }

    public void setCampusviewImageAttachs(List<CampusviewImgAlbumsAttachBlock> campusviewImageAttachs)
    {
        this.campusviewImageAttachs = campusviewImageAttachs;
    }

    @XmlElement
    public String getFavoriteCount()
    {
        return favoriteCount;
    }

    public void setFavoriteCount(String favoriteCount)
    {
        this.favoriteCount = favoriteCount;
    }

    @XmlElement
    public String getViewCount()
    {
        return viewCount;
    }

    public void setViewCount(String viewCount)
    {
        this.viewCount = viewCount;
    }

    @XmlElement
    public String getCoverImage()
    {
        return coverImage;
    }

    public void setCoverImage(String coverImage)
    {
        this.coverImage = coverImage;
    }
    
    
    
}
