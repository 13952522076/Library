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
import com.sammyun.entity.dict.DictStudent;

/**
 * Entity - 学生考勤
 * 


 */
@Entity
@Table(name = "t_pe_attendance")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_attendance_sequence")
public class Attendance extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -2960335909606619098L;

    /** 考勤状态 */
    public enum Status
    {
        /** 正常 */
        normal,
        /** 迟到 */
        late,
        /** 早退 */
        early,
        /** 病假 */
        sick,
        /** 事假 */
        compassionate
    }

    /** 实际考勤时间（IC卡、二维码打卡、条形码考勤）格式（yyyy-MM-dd） */
    private Date attendanceDate;

    /** 入园时间 格式（yyyy-MM-dd hh:mm:ss） */
    private Date enterDate;

    /** 出园时间 格式（yyyy-MM-dd hh:mm:ss） */
    private Date leaveDate;

    /** 考勤状态 */
    private Status status;

    /** 备注 */
    private String remarks;

    /** 隶属的学生 */
    private DictStudent dictStudent;

    /** 学生考勤详情 */
    private Set<AttendanceDetail> attendanceDetails = new HashSet<AttendanceDetail>();

    /**
     * @return 返回 attendanceDate
     */
    public Date getAttendanceDate()
    {
        return attendanceDate;
    }

    /**
     * @param 对attendanceDate进行赋值
     */
    public void setAttendanceDate(Date attendanceDate)
    {
        this.attendanceDate = attendanceDate;
    }

    /**
     * @return 返回 dictStudent
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictStudent getDictStudent()
    {
        return dictStudent;
    }

    /**
     * @param 对dictStudent进行赋值
     */
    public void setDictStudent(DictStudent dictStudent)
    {
        this.dictStudent = dictStudent;
    }

    /**
     * @return 返回 status
     */
    public Status getStatus()
    {
        return status;
    }

    /**
     * @param 对status进行赋值
     */
    public void setStatus(Status status)
    {
        this.status = status;
    }

    public Date getEnterDate()
    {
        return enterDate;
    }

    public void setEnterDate(Date enterDate)
    {
        this.enterDate = enterDate;
    }

    public Date getLeaveDate()
    {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate)
    {
        this.leaveDate = leaveDate;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    @OneToMany(mappedBy = "attendance", fetch = FetchType.LAZY)
    public Set<AttendanceDetail> getAttendanceDetails()
    {
        return attendanceDetails;
    }

    public void setAttendanceDetails(Set<AttendanceDetail> attendanceDetails)
    {
        this.attendanceDetails = attendanceDetails;
    }

}
