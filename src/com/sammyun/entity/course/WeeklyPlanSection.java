package com.sammyun.entity.course;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sun.istack.internal.NotNull;

/**
 * entity - 周计划段
 * 
 * @author xutianlong
 * @version [版本号, Apr 24, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_weekly_plan_section")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_weekly_plan_section_sequence")
public class WeeklyPlanSection extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -5589521461659069431L;

    /** 学年 */
    private SchoolYearMng schoolYearMng;

    /** 学校 */
    private DictSchool dictSchool;

    /** 所属班级 */
    private DictClass dictClass;

    /** 第几周 */
    private Long week;

    /** 周的开始时间（YYYY-MM-dd） */
    private Date weekStartDate;

    /** 周的结束时间（YYYY-MM-dd） */
    private Date weekEndDate;

    /** 周主题 */
    private String weekSubject;
    
    /** 是否是当前周 */
    private Boolean isCurrent;

    /** 周计划详情 */
    private List<WeeklyPlanDetail> weeklyPlanDetails = new ArrayList<WeeklyPlanDetail>();

    /**
     * @return 返回 schoolYearMng
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public SchoolYearMng getSchoolYearMng()
    {
        return schoolYearMng;
    }

    /**
     * @param 对schoolYearMng进行赋值
     */
    public void setSchoolYearMng(SchoolYearMng schoolYearMng)
    {
        this.schoolYearMng = schoolYearMng;
    }

    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictSchool getDictSchool()
    {
        return dictSchool;
    }

    /**
     * @param 对dictSchool进行赋值
     */
    public void setDictSchool(DictSchool dictSchool)
    {
        this.dictSchool = dictSchool;
    }

    /**
     * @return 返回 dictClass
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictClass getDictClass()
    {
        return dictClass;
    }

    /**
     * @param 对dictClass进行赋值
     */
    public void setDictClass(DictClass dictClass)
    {
        this.dictClass = dictClass;
    }

    /**
     * @return 返回 week
     */
    public Long getWeek()
    {
        return week;
    }

    /**
     * @param 对week进行赋值
     */
    public void setWeek(Long week)
    {
        this.week = week;
    }

    /**
     * @return 返回 weekStartDate
     */
    public Date getWeekStartDate()
    {
        return weekStartDate;
    }

    /**
     * @param 对weekStartDate进行赋值
     */
    public void setWeekStartDate(Date weekStartDate)
    {
        this.weekStartDate = weekStartDate;
    }

    /**
     * @return 返回 weekEndDate
     */
    public Date getWeekEndDate()
    {
        return weekEndDate;
    }

    /**
     * @param 对weekEndDate进行赋值
     */
    public void setWeekEndDate(Date weekEndDate)
    {
        this.weekEndDate = weekEndDate;
    }

    /**
     * @return 返回 weekSubject
     */
    public String getWeekSubject()
    {
        return weekSubject;
    }

    /**
     * @param 对weekSubject进行赋值
     */
    public void setWeekSubject(String weekSubject)
    {
        this.weekSubject = weekSubject;
    }
    
    /**
     * @return 返回 isCurrent
     */
    public Boolean getIsCurrent()
    {
        return isCurrent;
    }

    /**
     * @param 设置isCurrent
     */
    public void setIsCurrent(Boolean isCurrent)
    {
        this.isCurrent = isCurrent;
    }

    /**
     * @return 返回 weeklyPlanDetails
     */
    @OneToMany(mappedBy = "weeklyPlanSection", fetch = FetchType.LAZY)
    public List<WeeklyPlanDetail> getWeeklyPlanDetails()
    {
        return weeklyPlanDetails;
    }

    /**
     * @param 对weeklyPlanDetails进行赋值
     */
    public void setWeeklyPlanDetails(List<WeeklyPlanDetail> weeklyPlanDetails)
    {
        this.weeklyPlanDetails = weeklyPlanDetails;
    }

}
