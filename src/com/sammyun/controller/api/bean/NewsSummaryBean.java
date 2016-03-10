package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.controller.api.model.PageModel;


@XmlRootElement(name = "newsSummaryBean")
public class NewsSummaryBean
{
    /** 新闻类别id */
    private Long categoryId;
    
    /**分页信息*/
    private PageModel page;

    @XmlElement
    public Long getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId(Long categoryId)
    {
        this.categoryId = categoryId;
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
