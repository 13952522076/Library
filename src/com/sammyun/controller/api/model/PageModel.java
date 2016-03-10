package com.sammyun.controller.api.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pageModel")
public class PageModel
{
    /** 页码 */
    private Integer pageNumber;

    /** 每页个数 */
    private Integer pageSize;
    
    /** 总页数 */
    private Integer totalPages;

    @XmlElement
    public Integer getPageNumber()
    {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber)
    {
        this.pageNumber = pageNumber;
    }

    @XmlElement
    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    @XmlElement
    public Integer getTotalPages()
    {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages)
    {
        this.totalPages = totalPages;
    }

    
}
