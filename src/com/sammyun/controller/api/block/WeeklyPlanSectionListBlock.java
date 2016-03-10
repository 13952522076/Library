package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "weeklyPlanSectionListBlock")
public class WeeklyPlanSectionListBlock
{
    /** id*/
    private Long id;
    
    /** 第几周 */
    private Long week;

    /** 周的开始时间（YYYY-MM-dd） */
    private String weekStartDate;

    /** 周的结束时间（YYYY-MM-dd） */
    private String weekEndDate;

    /** 周主题 */
    private String weekSubject;
    
    /** 是否是当前周 */
    private Boolean isCurrent;
//
//    /** 周计划详情 */
//    private List<WeeklyPlanSectionDetailBlock> weeklyPlanDetails;

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
    public Long getWeek()
    {
        return week;
    }

    public void setWeek(Long week)
    {
        this.week = week;
    }

    @XmlElement
    public String getWeekStartDate()
    {
        return weekStartDate;
    }

    public void setWeekStartDate(String weekStartDate)
    {
        this.weekStartDate = weekStartDate;
    }

    @XmlElement
    public String getWeekEndDate()
    {
        return weekEndDate;
    }

    public void setWeekEndDate(String weekEndDate)
    {
        this.weekEndDate = weekEndDate;
    }

    @XmlElement
    public String getWeekSubject()
    {
        return weekSubject;
    }

    public void setWeekSubject(String weekSubject)
    {
        this.weekSubject = weekSubject;
    }

    @XmlElement
    public Boolean getIsCurrent()
    {
        return isCurrent;
    }

    public void setIsCurrent(Boolean isCurrent)
    {
        this.isCurrent = isCurrent;
    }
    
}
