package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "curriculumScheduleForSchoolBlock")
public class CurriculumScheduleForSchoolBlock
{
    private Long id;

    /** 开始学年 */
    private Long startYear;

    /** 结束学年 */
    private Long endYear;

    /** 用户设置当前学期 */
    private Long term;
    
    /** 是否是当前学年 */
    private Boolean isCurrent;

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
    public Long getStartYear()
    {
        return startYear;
    }

    public void setStartYear(Long startYear)
    {
        this.startYear = startYear;
    }

    @XmlElement
    public Long getEndYear()
    {
        return endYear;
    }

    public void setEndYear(Long endYear)
    {
        this.endYear = endYear;
    }

    @XmlElement
    public Long getTerm()
    {
        return term;
    }

    public void setTerm(Long term)
    {
        this.term = term;
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
