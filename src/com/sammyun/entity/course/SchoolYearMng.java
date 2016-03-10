package com.sammyun.entity.course;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.Admin;
import com.sammyun.entity.OrderEntity;
import com.sammyun.entity.dict.DictSchool;
import com.sun.istack.internal.NotNull;

/**
 * 学年管理
 * 
 * @author xutianlong
 * @version [版本号, Apr 11, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_school_year_mng")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_school_year_mng_sequence")
public class SchoolYearMng extends OrderEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    /** 开始学年 */
    private Long startYear;

    /** 结束学年 */
    private Long endYear;

    /** 用户设置当前学期 */
    private Long term;

    /** 是否展示 */
    private Boolean isShow;

    /** 是否是当前学年 */
    private Boolean isCurrent;

    /** 操作用户 */
    private Admin operator;

    /** 学校 */
    private DictSchool dictSchool;

    /** 学生课程列表 */
    private Set<CurriculumSchedule> curriculumSchedules = new HashSet<CurriculumSchedule>();

    /** 周计划段列表 */
    private Set<WeeklyPlanSection> weeklyPlanSections = new HashSet<WeeklyPlanSection>();

    /**
     * @return 返回 startYear
     */
    public Long getStartYear()
    {
        return startYear;
    }

    /**
     * @param 对startYear进行赋值
     */
    public void setStartYear(Long startYear)
    {
        this.startYear = startYear;
    }

    /**
     * @return 返回 endYear
     */
    public Long getEndYear()
    {
        return endYear;
    }

    /**
     * @param 对endYear进行赋值
     */
    public void setEndYear(Long endYear)
    {
        this.endYear = endYear;
    }

    /**
     * @return 返回 isShow
     */
    public Boolean getIsShow()
    {
        return isShow;
    }

    /**
     * @param 对isShow进行赋值
     */
    public void setIsShow(Boolean isShow)
    {
        this.isShow = isShow;
    }

    /**
     * @return 返回 isCurrent
     */
    public Boolean getIsCurrent()
    {
        return isCurrent;
    }

    /**
     * @param 对isCurrent进行赋值
     */
    public void setIsCurrent(Boolean isCurrent)
    {
        this.isCurrent = isCurrent;
    }

    /**
     * @return 返回 term
     */
    public Long getTerm()
    {
        return term;
    }

    /**
     * @param 对term进行赋值
     */
    public void setTerm(Long term)
    {
        this.term = term;
    }

    /**
     * @return 返回 operator
     */
    public Admin getOperator()
    {
        return operator;
    }

    /**
     * @param 对operator进行赋值
     */
    public void setOperator(Admin operator)
    {
        this.operator = operator;
    }

    /**
     * @return 返回 dictSchool
     */
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
     * @return 返回 curriculumSchedules
     */
    @OneToMany(mappedBy = "schoolYearMng", fetch = FetchType.LAZY)
    public Set<CurriculumSchedule> getCurriculumSchedules()
    {
        return curriculumSchedules;
    }

    /**
     * @param 对curriculumSchedules进行赋值
     */
    public void setCurriculumSchedules(Set<CurriculumSchedule> curriculumSchedules)
    {
        this.curriculumSchedules = curriculumSchedules;
    }

    /**
     * @return 返回 weeklyPlanSections
     */
    @OneToMany(mappedBy = "schoolYearMng", fetch = FetchType.LAZY)
    public Set<WeeklyPlanSection> getWeeklyPlanSections()
    {
        return weeklyPlanSections;
    }

    /**
     * @param 对weeklyPlanSections进行赋值
     */
    public void setWeeklyPlanSections(Set<WeeklyPlanSection> weeklyPlanSections)
    {
        this.weeklyPlanSections = weeklyPlanSections;
    }
}
