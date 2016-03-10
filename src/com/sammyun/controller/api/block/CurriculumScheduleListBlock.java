package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "curriculumScheduleListBlock")
public class CurriculumScheduleListBlock
{
    /** id */
    private Long id;

    /** 星期 */
    private Integer week;

    /** 授课老师 */
    private String teacherName;

    /** 课程名 */
    private String courseName;

    /**
     * 课节 （课节1-8，标识第1到第8节课）
     */
    private String lessons;

    /** 教室 */
    private String classRoom;

    /** 开始时间（8:30） */
    private String startTime;

    /** 结束时间（8:30） */
    private String endTime;

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
    public Integer getWeek()
    {
        return week;
    }

    public void setWeek(Integer week)
    {
        this.week = week;
    }

    @XmlElement
    public String getTeacherName()
    {
        return teacherName;
    }

    public void setTeacherName(String teacherName)
    {
        this.teacherName = teacherName;
    }

    @XmlElement
    public String getCourseName()
    {
        return courseName;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    @XmlElement
    public String getLessons()
    {
        return lessons;
    }

    public void setLessons(String lessons)
    {
        this.lessons = lessons;
    }

    @XmlElement
    public String getClassRoom()
    {
        return classRoom;
    }

    public void setClassRoom(String classRoom)
    {
        this.classRoom = classRoom;
    }

    @XmlElement
    public String getStartTime()
    {
        return startTime;
    }

    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    @XmlElement
    public String getEndTime()
    {
        return endTime;
    }

    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

}
