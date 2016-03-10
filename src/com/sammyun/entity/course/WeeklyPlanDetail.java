package com.sammyun.entity.course;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sun.istack.internal.NotNull;

/**
 * Entity - 周计划详情
 * 
 * @author xutianlong
 * @version [版本号, Apr 24, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_weekly_plan_detail")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_weekly_plan_detail_sequence")
public class WeeklyPlanDetail extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1435564873355199562L;

    /** 上下午 */
    public enum PlanSection
    {
        /** 上午 */
        MORNING,
        /** 下午 */
        AFTERNOON
    }

    /** 周一标题 */
    private String monday;

    /** 周一课程描述 */
    private String mondayDesc;

    /** 周一图片 */
    private String mondayImage;

    /** 周二 */
    private String tuesday;

    /** 周二课程描述 */
    private String tuesdayDesc;

    /** 周二图片 */
    private String tuesdayImage;

    /** 周三 */
    private String wednesday;

    /** 周三课程描述 */
    private String wednesdayDesc;

    /** 周三图片 */
    private String wednesdayImage;

    /** 周四 */
    private String thursday;

    /** 周四课程描述 */
    private String thursdayDesc;

    /** 周四图片 */
    private String thursdayImage;

    /** 周五 */
    private String friday;

    /** 周五课程描述 */
    private String fridayDesc;

    /** 周五图片 */
    private String fridayImage;

    /** 周计划段 */
    private WeeklyPlanSection weeklyPlanSection;

    /** 上下午 */
    private PlanSection planSection;
    /**
     * @return 返回 monday
     */
    public String getMonday()
    {
        return monday;
    }

    /**
     * @param 对monday进行赋值
     */
    public void setMonday(String monday)
    {
        this.monday = monday;
    }

    /**
     * @return 返回 mondayDesc
     */
    public String getMondayDesc()
    {
        return mondayDesc;
    }

    /**
     * @param 对mondayDesc进行赋值
     */
    public void setMondayDesc(String mondayDesc)
    {
        this.mondayDesc = mondayDesc;
    }

    /**
     * @return 返回 mondayImage
     */
    public String getMondayImage()
    {
        return mondayImage;
    }

    /**
     * @param 对mondayImage进行赋值
     */
    public void setMondayImage(String mondayImage)
    {
        this.mondayImage = mondayImage;
    }

    /**
     * @return 返回 tuesday
     */
    public String getTuesday()
    {
        return tuesday;
    }

    /**
     * @param 对tuesday进行赋值
     */
    public void setTuesday(String tuesday)
    {
        this.tuesday = tuesday;
    }

    /**
     * @return 返回 tuesdayDesc
     */
    public String getTuesdayDesc()
    {
        return tuesdayDesc;
    }

    /**
     * @param 对tuesdayDesc进行赋值
     */
    public void setTuesdayDesc(String tuesdayDesc)
    {
        this.tuesdayDesc = tuesdayDesc;
    }

    /**
     * @return 返回 tuesdayImage
     */
    public String getTuesdayImage()
    {
        return tuesdayImage;
    }

    /**
     * @param 对tuesdayImage进行赋值
     */
    public void setTuesdayImage(String tuesdayImage)
    {
        this.tuesdayImage = tuesdayImage;
    }

    /**
     * @return 返回 wednesday
     */
    public String getWednesday()
    {
        return wednesday;
    }

    /**
     * @param 对wednesday进行赋值
     */
    public void setWednesday(String wednesday)
    {
        this.wednesday = wednesday;
    }

    /**
     * @return 返回 wednesdayDesc
     */
    public String getWednesdayDesc()
    {
        return wednesdayDesc;
    }

    /**
     * @param 对wednesdayDesc进行赋值
     */
    public void setWednesdayDesc(String wednesdayDesc)
    {
        this.wednesdayDesc = wednesdayDesc;
    }

    /**
     * @return 返回 wednesdayImage
     */
    public String getWednesdayImage()
    {
        return wednesdayImage;
    }

    /**
     * @param 对wednesdayImage进行赋值
     */
    public void setWednesdayImage(String wednesdayImage)
    {
        this.wednesdayImage = wednesdayImage;
    }

    /**
     * @return 返回 thursday
     */
    public String getThursday()
    {
        return thursday;
    }

    /**
     * @param 对thursday进行赋值
     */
    public void setThursday(String thursday)
    {
        this.thursday = thursday;
    }

    /**
     * @return 返回 thursdayDesc
     */
    public String getThursdayDesc()
    {
        return thursdayDesc;
    }

    /**
     * @param 对thursdayDesc进行赋值
     */
    public void setThursdayDesc(String thursdayDesc)
    {
        this.thursdayDesc = thursdayDesc;
    }

    /**
     * @return 返回 thursdayImage
     */
    public String getThursdayImage()
    {
        return thursdayImage;
    }

    /**
     * @param 对thursdayImage进行赋值
     */
    public void setThursdayImage(String thursdayImage)
    {
        this.thursdayImage = thursdayImage;
    }

    /**
     * @return 返回 friday
     */
    public String getFriday()
    {
        return friday;
    }

    /**
     * @param 对friday进行赋值
     */
    public void setFriday(String friday)
    {
        this.friday = friday;
    }

    /**
     * @return 返回 fridayDesc
     */
    public String getFridayDesc()
    {
        return fridayDesc;
    }

    /**
     * @param 对fridayDesc进行赋值
     */
    public void setFridayDesc(String fridayDesc)
    {
        this.fridayDesc = fridayDesc;
    }

    /**
     * @return 返回 fridayImage
     */
    public String getFridayImage()
    {
        return fridayImage;
    }

    /**
     * @param 对fridayImage进行赋值
     */
    public void setFridayImage(String fridayImage)
    {
        this.fridayImage = fridayImage;
    }

    /**
     * @return 返回 weeklyPlanSection
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public WeeklyPlanSection getWeeklyPlanSection()
    {
        return weeklyPlanSection;
    }

    /**
     * @param 对weeklyPlanSection进行赋值
     */
    public void setWeeklyPlanSection(WeeklyPlanSection weeklyPlanSection)
    {
        this.weeklyPlanSection = weeklyPlanSection;
    }

    /**
     * 返回 planSection
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public PlanSection getPlanSection()
    {
        return planSection;
    }

    /**
     * 设置 planSection
     * <功能详细描述>
     * @param planSection
     * @see [类、类#方法、类#成员]
     */
    public void setPlanSection(PlanSection planSection)
    {
        this.planSection = planSection;
    }
    
}
