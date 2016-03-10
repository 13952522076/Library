package com.sammyun.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Entity - 应用启动页
 * 
 * @author xutianlong
 * @version [版本号, Aug 27, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_app_start_page")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_app_start_page_sequence")
public class AppStartPage extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 482738792492361114L;

    /** 启动页名称 */
    private String pageName;

    /** 启动图片 */
    private String pageImage;

    /** 第三方地址 */
    private String webSite;

    /** 摘要 */
    private String summary;

    /** 是否是广告，默认为false */
    private Boolean isAd;

    /**
     * @return 返回 pageName
     */
    @Column(length = 20)
    public String getPageName()
    {
        return pageName;
    }

    /**
     * @param 对pageName进行赋值
     */
    public void setPageName(String pageName)
    {
        this.pageName = pageName;
    }

    /**
     * @return 返回 pageImage
     */
    @Column(length = 20)
    public String getPageImage()
    {
        return pageImage;
    }

    /**
     * @param 对pageImage进行赋值
     */
    public void setPageImage(String pageImage)
    {
        this.pageImage = pageImage;
    }

    /**
     * @return 返回 webSite
     */
    @Column(length = 500)
    public String getWebSite()
    {
        return webSite;
    }

    /**
     * @param 对webSite进行赋值
     */
    public void setWebSite(String webSite)
    {
        this.webSite = webSite;
    }

    /**
     * @return 返回 summary
     */
    @Column(length = 10)
    public String getSummary()
    {
        return summary;
    }

    /**
     * @param 对summary进行赋值
     */
    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    /**
     * @return 返回 isAd
     */
    public Boolean getIsAd()
    {
        return isAd;
    }

    /**
     * @param 对isAd进行赋值
     */
    public void setIsAd(Boolean isAd)
    {
        this.isAd = isAd;
    }
}
