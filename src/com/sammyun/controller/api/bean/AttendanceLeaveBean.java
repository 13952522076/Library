package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.attendance.AskLeave.AskLeaveType;

@XmlRootElement(name = "leaveDetailBean")
public class AttendanceLeaveBean
{
    /** 用户id */
    private Long memberId;
    
    /** 请假开始时间 */
    private String leaveStartDate;

    /** 请假结束时间 */
    private String leaveEndDate;

    /** 请假学生编号 */
    private String stuNo;

    /** 请假说明 */
    private String description;

    /** 请假类型 */
    private AskLeaveType askLeaveType;

    @XmlElement    
    public Long getMemberId()
    {
        return memberId;
    }

    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    @XmlElement
    public String getLeaveStartDate()
    {
        return leaveStartDate;
    }

    public void setLeaveStartDate(String leaveStartDate)
    {
        this.leaveStartDate = leaveStartDate;
    }

    @XmlElement
    public String getLeaveEndDate()
    {
        return leaveEndDate;
    }

    public void setLeaveEndDate(String leaveEndDate)
    {
        this.leaveEndDate = leaveEndDate;
    }

    @XmlElement
    public String getStuNo()
    {
        return stuNo;
    }

    public void setStuNo(String stuNo)
    {
        this.stuNo = stuNo;
    }

    @XmlElement
    public AskLeaveType getAskLeaveType()
    {
        return askLeaveType;
    }

    public void setAskLeaveType(AskLeaveType askLeaveType)
    {
        this.askLeaveType = askLeaveType;
    }

    @XmlElement
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

}
