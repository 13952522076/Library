package com.sammyun.entity.attendance;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.Member;

/**
 * Entity - 老师考勤
 * 
 * @author xutianlong
 * @version [版本号, May 24, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_teacher_attendance")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_teacher_attendance_sequence")
public class TeacherAttendance extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -5070161427097946231L;

    /** 考勤状态 */
    public enum Status
    {
        /** 正常 */
        normal,
        /** 迟到 */
        late,
        /** 早退 */
        early,
        /** 请假 */
        leave,
        /** 旷工 */
        absenteeism
    }

    /** 对应的老师 */
    private Member member;

    /** 对应班次名字 */
    private String workSettingName;

    /** 上班时间 */
    private String workTime;

    /** 上班 第一次刷卡 */
    private Date workSwipeTime;

    /** 迟到情况 */
    private Status workStatus;

    /** 下班时间 */
    private String closingTime;

    /** 下班 第一次刷卡 */
    private Date closingSwipeTime;

    /** 早退情况 */
    private Status closingStatus;

    /** 老师考勤详情 */
    private Set<TeacherAttendanceDetail> teacherAttendanceDetails = new HashSet<TeacherAttendanceDetail>();

    /**
     * @return 返回 member
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Member getMember()
    {
        return member;
    }

    /**
     * @param 对member进行赋值
     */
    public void setMember(Member member)
    {
        this.member = member;
    }

    /**
     * @return 返回 workSettingName
     */
    public String getWorkSettingName()
    {
        return workSettingName;
    }

    /**
     * @param 对workSettingName进行赋值
     */
    public void setWorkSettingName(String workSettingName)
    {
        this.workSettingName = workSettingName;
    }

    /**
     * @return 返回 workTime
     */
    public String getWorkTime()
    {
        return workTime;
    }

    /**
     * @param 对workTime进行赋值
     */
    public void setWorkTime(String workTime)
    {
        this.workTime = workTime;
    }

    /**
     * @return 返回 workSwipeTime
     */
    public Date getWorkSwipeTime()
    {
        return workSwipeTime;
    }

    /**
     * @param 对workSwipeTime进行赋值
     */
    public void setWorkSwipeTime(Date workSwipeTime)
    {
        this.workSwipeTime = workSwipeTime;
    }

    /**
     * @return 返回 workStatus
     */
    public Status getWorkStatus()
    {
        return workStatus;
    }

    /**
     * @param 对workStatus进行赋值
     */
    public void setWorkStatus(Status workStatus)
    {
        this.workStatus = workStatus;
    }

    /**
     * @return 返回 closingTime
     */
    public String getClosingTime()
    {
        return closingTime;
    }

    /**
     * @param 对closingTime进行赋值
     */
    public void setClosingTime(String closingTime)
    {
        this.closingTime = closingTime;
    }

    /**
     * @return 返回 closingSwipeTime
     */
    public Date getClosingSwipeTime()
    {
        return closingSwipeTime;
    }

    /**
     * @param 对closingSwipeTime进行赋值
     */
    public void setClosingSwipeTime(Date closingSwipeTime)
    {
        this.closingSwipeTime = closingSwipeTime;
    }

    /**
     * @return 返回 closingStatus
     */
    public Status getClosingStatus()
    {
        return closingStatus;
    }

    /**
     * @param 对closingStatus进行赋值
     */
    public void setClosingStatus(Status closingStatus)
    {
        this.closingStatus = closingStatus;
    }

    /**
     * @return 返回 teacherAttendanceDetails
     */
    @OneToMany(mappedBy = "teacherAttendance", fetch = FetchType.LAZY)
    public Set<TeacherAttendanceDetail> getTeacherAttendanceDetails()
    {
        return teacherAttendanceDetails;
    }

    /**
     * @param 对teacherAttendanceDetails进行赋值
     */
    public void setTeacherAttendanceDetails(Set<TeacherAttendanceDetail> teacherAttendanceDetails)
    {
        this.teacherAttendanceDetails = teacherAttendanceDetails;
    }

}
