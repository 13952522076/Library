package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "newsContentBlock")
public class NewsContentBlock
{
    /** 标题 */
    private String title;

    /** 摘要 */
    private String summary;

    /** 正文 */
    private String content;

    /** 新闻作者 */
    private String author;

    /** 新闻来源 */
    private String source;

    /** 详情url地址 */
    private String detailUrl;
//
//    /** 图标文件标识 */
//    private String smallIconfile;

    /** 发布时间 */
    private String timePublish;

    /** 浏览数 */
    private String viewCount;

    @XmlElement
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @XmlElement
    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
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
    public String getAuthor()
    {
        return author;
    }

    public void setAuthor(String author)
    {
        this.author = author;
    }

    @XmlElement
    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    @XmlElement
    public String getDetailUrl()
    {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl)
    {
        this.detailUrl = detailUrl;
    }

//    @XmlElement
//    public String getSmallIconfile()
//    {
//        return smallIconfile;
//    }
//
//    public void setSmallIconfile(String smallIconfile)
//    {
//        this.smallIconfile = smallIconfile;
//    }


    @XmlElement
    public String getTimePublish()
    {
        return timePublish;
    }

    public void setTimePublish(String timePublish)
    {
        this.timePublish = timePublish;
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

}
