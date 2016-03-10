package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.poster.Poster.PosterPosition;
import com.sammyun.entity.poster.Poster.PosterType;

@XmlRootElement(name = "posterListBlock")
public class PosterListBlock
{
    /** 海报id */
    private Long id;
    
    /** 海报位置 */
    private PosterPosition posterPosition;

    /** 海报标题 */
    private String posterTitle;

    /** 海报封面（640 * 398） */
    private String posterCover;

    /** 海报查看次数 */
    private Long viewCount;

    /** 跳转界面类型 */
    private PosterType posterType;

    /** 外联地址 */
    private String url;

    /** 内容详情 */
    private String content;
    
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
    public PosterPosition getPosterPosition()
    {
        return posterPosition;
    }

    public void setPosterPosition(PosterPosition posterPosition)
    {
        this.posterPosition = posterPosition;
    }

    @XmlElement
    public String getPosterTitle()
    {
        return posterTitle;
    }

    public void setPosterTitle(String posterTitle)
    {
        this.posterTitle = posterTitle;
    }

    @XmlElement
    public String getPosterCover()
    {
        return posterCover;
    }

    public void setPosterCover(String posterCover)
    {
        this.posterCover = posterCover;
    }

    @XmlElement
    public Long getViewCount()
    {
        return viewCount;
    }

    public void setViewCount(Long viewCount)
    {
        this.viewCount = viewCount;
    }

    @XmlElement
    public PosterType getPosterType()
    {
        return posterType;
    }

    public void setPosterType(PosterType posterType)
    {
        this.posterType = posterType;
    }

    @XmlElement
    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
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
    
    

}
