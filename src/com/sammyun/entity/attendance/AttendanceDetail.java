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
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sammyun.entity.BaseEntity;
import com.sammyun.util.JsonDateSerializer;

/**
 * Entity - 学生考勤详情
 * 
 * @author Sencloud Team

 */
@Entity
@Table(name = "t_pe_attendance_detail")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_attendance_detail_sequence")
public class AttendanceDetail extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 2158450773486024598L;

    /** 时间段 */
    public enum TimeType
    {
        /** 早上 */

        morning,

        /** 中午 */

        noon,
        /** 下午 */

        afternoon,

        /** 晚上 */
        night
    }

    /** 考勤设备 */
    public enum Device
    {
        /** IC卡 */
        IC,
        /** 二维码 */
        QR,
        /** 条形码 */
        BAR
    }

    /** 考勤时间段 */
    private TimeType timeType;

    /** 打卡时间（IC卡、二维码打卡、条形码考勤）格式（yyyy-MM-dd hh:mm:ss） */
    private Date clockInDate;

    /** 设备（包含IC卡、手环、移动设备） */
    private Device device;

    /** 系统（IC/IOS/安卓等） */
    private String systemInfo;

    /** 接送人 */
    private String take;
    
    /** 接送人照片 */
    private String iconPhoto;

    /** 备注（系统管理员或者） */
    private String remarks;

    /** 学生考勤 */
    private Attendance attendance;

    @JsonProperty
    public TimeType getTimeType()
    {
        return timeType;
    }

    public void setTimeType(TimeType timeType)
    {
        this.timeType = timeType;
    }

    @JsonProperty
    public Device getDevice()
    {
        return device;
    }

    public void setDevice(Device device)
    {
        this.device = device;
    }

    @JsonProperty
    public String getSystemInfo()
    {
        return systemInfo;
    }

    public void setSystemInfo(String systemInfo)
    {
        this.systemInfo = systemInfo;
    }

    @JsonProperty
    public String getTake()
    {
        return take;
    }

    public void setTake(String take)
    {
        this.take = take;
    }
    
    @JsonProperty
    public String getIconPhoto()
    {
        return iconPhoto;
    }

    public void setIconPhoto(String iconPhoto)
    {
        this.iconPhoto = iconPhoto;
    }

    @JsonProperty
    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Attendance getAttendance()
    {
        return attendance;
    }

    public void setAttendance(Attendance attendance)
    {
        this.attendance = attendance;
    }

    @JsonProperty
    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getClockInDate()
    {
        return clockInDate;
    }

    public void setClockInDate(Date clockInDate)
    {
        this.clockInDate = clockInDate;
    }

}
