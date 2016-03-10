package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.attendance.TeacherAskLeave.AskLeaveType;

@XmlRootElement(name = "teacherAskLeaveAddBean")
public class TeacherAskLeaveAddBean
{
    /** 请假开始时间 */
    private String leaveStartDate;

    /** 请假结束时间 */
    private String leaveEndDate;

    /** 请假老师 */
    private Long leaveMemberId;

    /** 请假的天数 */
    private Double leaveDay;

    /** 请假说明 */
    private String description;

    /** 请假类型 */
    private AskLeaveType askLeaveType;

    /** 审批老师 */
    private Long approvalMemberId;

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
    public Long getLeaveMemberId()
    {
        return leaveMemberId;
    }

    public void setLeaveMemberId(Long leaveMemberId)
    {
        this.leaveMemberId = leaveMemberId;
    }

    @XmlElement
    public Double getLeaveDay()
    {
        return leaveDay;
    }

    public void setLeaveDay(Double leaveDay)
    {
        this.leaveDay = leaveDay;
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
    public Long getApprovalMemberId()
    {
        return approvalMemberId;
    }

    public void setApprovalMemberId(Long approvalMemberId)
    {
        this.approvalMemberId = approvalMemberId;
    }
    
}
