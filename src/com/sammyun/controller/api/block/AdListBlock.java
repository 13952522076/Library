package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.ad.Ad.AdPosition;
import com.sammyun.entity.ad.Ad.ShowType;
import com.sammyun.entity.ad.Ad.SimType;
import com.sammyun.entity.ad.Ad.Type;

@XmlRootElement(name = "adListBlock")
public class AdListBlock
{

    /** 广告 */
    private Long id;
    
    /** 应用分类名称 */
    private String adAppCategoryName;

    /** 广告名称 */
    private String adName;

    /** 广告描述 */
    private String content;

    /** 广告图片 (封面图片) */
    private String adImage;

    /** 类型 */
    private Type type;

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

    /** 广告处于App的位置 */
    private AdPosition adPosition;

    /**广告弹出类型*/
    private ShowType showType;

    /** 定向SIM卡用户 */
    private SimType simType;

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
    public String getAdAppCategoryName()
    {
        return adAppCategoryName;
    }

    public void setAdAppCategoryName(String adAppCategoryName)
    {
        this.adAppCategoryName = adAppCategoryName;
    }

    @XmlElement
    public String getAdName()
    {
        return adName;
    }

    public void setAdName(String adName)
    {
        this.adName = adName;
    }

    @XmlElement
    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    @XmlElement
    public String getAdImage()
    {
        return adImage;
    }

    public void setAdImage(String adImage)
    {
        this.adImage = adImage;
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
    public String getAdPackageUrl()
    {
        return adPackageUrl;
    }

    public void setAdPackageUrl(String adPackageUrl)
    {
        this.adPackageUrl = adPackageUrl;
    }

    @XmlElement
    public String getAppName()
    {
        return appName;
    }

    public void setAppName(String appName)
    {
        this.appName = appName;
    }

    @XmlElement
    public String getAppAuthor()
    {
        return appAuthor;
    }

    public void setAppAuthor(String appAuthor)
    {
        this.appAuthor = appAuthor;
    }

    @XmlElement
    public String getAppDescription()
    {
        return appDescription;
    }

    public void setAppDescription(String appDescription)
    {
        this.appDescription = appDescription;
    }

    @XmlElement
    public String getAdSite()
    {
        return adSite;
    }

    public void setAdSite(String adSite)
    {
        this.adSite = adSite;
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

}
