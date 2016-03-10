package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "announcementSummaryBlock")
public class AnnouncementSummaryBlock
{
    /** id */
    private Long id;
    
    /** 标题 */
    private String title;

    /** 摘要 */
    private String summary;
    
    /** 预览图 */
    private String smallIconfile;

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
    public String getSmallIconfile()
    {
        return smallIconfile;
    }

    public void setSmallIconfile(String smallIconfile)
    {
        this.smallIconfile = smallIconfile;
    }
    
    
}
