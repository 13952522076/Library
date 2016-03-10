package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "weeklyPlanSectionDetailBlock")
public class WeeklyPlanSectionDetailBlock
{
    /** 周一标题 */
    private String monday;

    /** 周一课程描述 */
    private String mondayDesc;

    /** 周三图片 */
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

    /** 上下午 */
    private String planSection;

    @XmlElement
    public String getMonday()
    {
        return monday;
    }

    public void setMonday(String monday)
    {
        this.monday = monday;
    }

    @XmlElement
    public String getMondayDesc()
    {
        return mondayDesc;
    }

    public void setMondayDesc(String mondayDesc)
    {
        this.mondayDesc = mondayDesc;
    }

    @XmlElement
    public String getMondayImage()
    {
        return mondayImage;
    }

    public void setMondayImage(String mondayImage)
    {
        this.mondayImage = mondayImage;
    }

    @XmlElement
    public String getTuesday()
    {
        return tuesday;
    }

    public void setTuesday(String tuesday)
    {
        this.tuesday = tuesday;
    }

    @XmlElement
    public String getTuesdayDesc()
    {
        return tuesdayDesc;
    }

    public void setTuesdayDesc(String tuesdayDesc)
    {
        this.tuesdayDesc = tuesdayDesc;
    }

    @XmlElement
    public String getTuesdayImage()
    {
        return tuesdayImage;
    }

    public void setTuesdayImage(String tuesdayImage)
    {
        this.tuesdayImage = tuesdayImage;
    }

    @XmlElement
    public String getWednesday()
    {
        return wednesday;
    }

    public void setWednesday(String wednesday)
    {
        this.wednesday = wednesday;
    }

    @XmlElement
    public String getWednesdayDesc()
    {
        return wednesdayDesc;
    }

    public void setWednesdayDesc(String wednesdayDesc)
    {
        this.wednesdayDesc = wednesdayDesc;
    }

    @XmlElement
    public String getWednesdayImage()
    {
        return wednesdayImage;
    }

    public void setWednesdayImage(String wednesdayImage)
    {
        this.wednesdayImage = wednesdayImage;
    }

    @XmlElement
    public String getThursday()
    {
        return thursday;
    }

    public void setThursday(String thursday)
    {
        this.thursday = thursday;
    }

    @XmlElement
    public String getThursdayDesc()
    {
        return thursdayDesc;
    }

    public void setThursdayDesc(String thursdayDesc)
    {
        this.thursdayDesc = thursdayDesc;
    }

    @XmlElement
    public String getThursdayImage()
    {
        return thursdayImage;
    }

    public void setThursdayImage(String thursdayImage)
    {
        this.thursdayImage = thursdayImage;
    }

    @XmlElement
    public String getFriday()
    {
        return friday;
    }

    public void setFriday(String friday)
    {
        this.friday = friday;
    }

    @XmlElement
    public String getFridayDesc()
    {
        return fridayDesc;
    }

    public void setFridayDesc(String fridayDesc)
    {
        this.fridayDesc = fridayDesc;
    }

    @XmlElement
    public String getFridayImage()
    {
        return fridayImage;
    }

    public void setFridayImage(String fridayImage)
    {
        this.fridayImage = fridayImage;
    }

    @XmlElement
    public String getPlanSection()
    {
        return planSection;
    }

    public void setPlanSection(String planSection)
    {
        this.planSection = planSection;
    }

    
}
