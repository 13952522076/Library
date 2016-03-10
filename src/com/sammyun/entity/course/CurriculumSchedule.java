package com.sammyun.entity.course;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictClass;
import com.sun.istack.internal.NotNull;

/**
 * Entity - 课程表管理
 * 
 * @author xutianlong
 * @version [版本号, Apr 11, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_curriculum_schedule")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_curriculum_schedule_sequence")
public class CurriculumSchedule extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 6383562911678344870L;

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

    /** 所属 学年 */
    private SchoolYearMng schoolYearMng;

    /** 所属班级 */
    private DictClass dictClass;

    /**
     * @return 返回 week
     */
    @JsonProperty
    public Integer getWeek()
    {
        return week;
    }

    /**
     * @param 对week进行赋值
     */
    public void setWeek(Integer week)
    {
        this.week = week;
    }

    /**
     * @return 返回 teacherName
     */
    @JsonProperty
    public String getTeacherName()
    {
        return teacherName;
    }

    /**
     * @param 对teacherName进行赋值
     */
    public void setTeacherName(String teacherName)
    {
        this.teacherName = teacherName;
    }

    /**
     * @return 返回 courseName
     */
    @JsonProperty
    public String getCourseName()
    {
        return courseName;
    }

    /**
     * @param 对courseName进行赋值
     */
    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    /**
     * @return 返回 lessons
     */
    @JsonProperty
    public String getLessons()
    {
        return lessons;
    }

    /**
     * @param 对lessons进行赋值
     */
    public void setLessons(String lessons)
    {
        this.lessons = lessons;
    }

    /**
     * @return 返回 classRoom
     */
    @JsonProperty
    public String getClassRoom()
    {
        return classRoom;
    }

    /**
     * @param 对classRoom进行赋值
     */
    public void setClassRoom(String classRoom)
    {
        this.classRoom = classRoom;
    }

    /**
     * @return 返回 startTime
     */
    @JsonProperty
    public String getStartTime()
    {
        return startTime;
    }

    /**
     * @param 对startTime进行赋值
     */
    public void setStartTime(String startTime)
    {
        this.startTime = startTime;
    }

    /**
     * @return 返回 endTime
     */
    @JsonProperty
    public String getEndTime()
    {
        return endTime;
    }

    /**
     * @param 对endTime进行赋值
     */
    public void setEndTime(String endTime)
    {
        this.endTime = endTime;
    }

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
}
