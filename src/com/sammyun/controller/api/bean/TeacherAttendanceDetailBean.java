package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.controller.api.model.PageModel;

@XmlRootElement(name = "teacherAttendanceDetailBean")
public class TeacherAttendanceDetailBean
{
    /** 查询时间 */
    private String date;

    /** 老师id */
    private Long memberId;
    
    /**分页信息*/
    private PageModel page;

    @XmlElement
    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    @XmlElement
    public Long getMemberId()
    {
        return memberId;
    }

    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
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
