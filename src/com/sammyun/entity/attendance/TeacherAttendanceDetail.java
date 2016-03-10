package com.sammyun.entity.attendance;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;

/**
 * Entity - 教师考勤详情
 * 
 * @author xutianlong
 * @version [版本号, May 24, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_teacher_attendance_detail")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_teacher_attendance_detail_sequence")
public class TeacherAttendanceDetail extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -1571744194018331401L;

    /** 打卡时间（格式（yyyy-MM-dd hh:mm:ss） */
    private Date clockInDate;

    /** 对应的打卡卡号 */
    private String cardNumber;
    
    /** 接送人照片 */
    private String iconPhoto;

    /** 老师考勤 */
    private TeacherAttendance teacherAttendance;

    /**
     * @return 返回 clockInDate
     */
    @JsonProperty
    public Date getClockInDate()
    {
        return clockInDate;
    }

    /**
     * @param 对clockInDate进行赋值
     */
    public void setClockInDate(Date clockInDate)
    {
        this.clockInDate = clockInDate;
    }

    /**
     * @return 返回 employeeAttendance
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public TeacherAttendance getTeacherAttendance()
    {
        return teacherAttendance;
    }

    /**
     * @param 对employeeAttendance进行赋值
     */
    public void setTeacherAttendance(TeacherAttendance teacherAttendance)
    {
        this.teacherAttendance = teacherAttendance;
    }

    /**
     * @return 返回 cardNumber
     */
    @JsonProperty
    public String getCardNumber()
    {
        return cardNumber;
    }

    /**
     * @param 对cardNumber进行赋值
     */
    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    /**
     * @return iconPhoto
     */ 
    @JsonProperty
    public String getIconPhoto()
    {
        return iconPhoto;
    }

    /**
     * @param iconPhoto
     */
    public void setIconPhoto(String iconPhoto)
    {
        this.iconPhoto = iconPhoto;
    }

    
}
