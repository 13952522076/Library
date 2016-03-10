package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.attendance.Attendance.Status;

@XmlRootElement(name = "attendaceDetailBlock")
public class AttendaceListBlock
{

    /** 实际考勤时间（IC卡、二维码打卡、条形码考勤）格式（yyyy-MM-dd） */
    private String attendanceDate;
    
    /** 入园时间 格式（yyyy-MM-dd hh:mm:ss） */
    private String enterDate;

    /** 出园时间 格式（yyyy-MM-dd hh:mm:ss） */
    private String leaveDate;

    /** 考勤状态 */
    private Status status;

    /** 考勤状态 */
    private String statusName;

    /** 学生姓名 */
    private String studentName;

    /** 学生头像 */
    private String iconPhoto;

    @XmlElement
    public String getAttendanceDate()
    {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate)
    {
        this.attendanceDate = attendanceDate;
    }

    @XmlElement
    public String getEnterDate()
    {
        return enterDate;
    }

    public void setEnterDate(String enterDate)
    {
        this.enterDate = enterDate;
    }

    @XmlElement
    public String getLeaveDate()
    {
        return leaveDate;
    }

    public void setLeaveDate(String leaveDate)
    {
        this.leaveDate = leaveDate;
    }

    @XmlElement
    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    @XmlElement
    public String getStudentName()
    {
        return studentName;
    }

    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    @XmlElement
    public String getStatusName()
    {
        return statusName;
    }

    public void setStatusName(String statusName)
    {
        this.statusName = statusName;
    }

    @XmlElement
    public String getIconPhoto()
    {
        return iconPhoto;
    }

    public void setIconPhoto(String iconPhoto)
    {
        this.iconPhoto = iconPhoto;
    }

}
