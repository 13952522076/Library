package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.attendance.TeacherAskLeave.AskLeaveType;

@XmlRootElement(name = "teacherAskLeaveDetailBlock")
public class TeacherAskLeaveDetailBlock
{
    /**id*/
    private Long id;
    
    /** 请假开始时间 */
    private String leaveStartDate;

    /** 请假结束时间 */
    private String leaveEndDate;

    /** 请假老师 */
    private String leaveMemberName;

    /** 请假的天数 */
    private Double leaveDay;

    /** 请假说明 */
    private String description;

    /** 请假类型 */
    private AskLeaveType askLeaveType;
    
    /**类型名称*/
    private String askLeaveTypeName;

    /** 审批老师 */
    private String approvalMemberName;
    
    /** 审批老师头像 */
    private String approvalMemberIconPhoto;

    /** 是否同意（同意了方可生效） */
    private Boolean isAgree;

    /** 审批意见 */
    private String approvalOpinion;
    
    /** 审批时间 */
    private String approvalDate;
    
    /** 创建时间 */
    private String createDate;

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
    public String getLeaveMemberName()
    {
        return leaveMemberName;
    }

    public void setLeaveMemberName(String leaveMemberName)
    {
        this.leaveMemberName = leaveMemberName;
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
    public String getAskLeaveTypeName()
    {
        return askLeaveTypeName;
    }

    public void setAskLeaveTypeName(String askLeaveTypeName)
    {
        this.askLeaveTypeName = askLeaveTypeName;
    }

    @XmlElement
    public String getApprovalMemberName()
    {
        return approvalMemberName;
    }

    public void setApprovalMemberName(String approvalMemberName)
    {
        this.approvalMemberName = approvalMemberName;
    }

    @XmlElement
    public Boolean getIsAgree()
    {
        return isAgree;
    }

    public void setIsAgree(Boolean isAgree)
    {
        this.isAgree = isAgree;
    }

    @XmlElement
    public String getApprovalOpinion()
    {
        return approvalOpinion;
    }

    public void setApprovalOpinion(String approvalOpinion)
    {
        this.approvalOpinion = approvalOpinion;
    }

    @XmlElement
    public String getApprovalMemberIconPhoto()
    {
        return approvalMemberIconPhoto;
    }

    public void setApprovalMemberIconPhoto(String approvalMemberIconPhoto)
    {
        this.approvalMemberIconPhoto = approvalMemberIconPhoto;
    }

    @XmlElement
    public String getApprovalDate()
    {
        return approvalDate;
    }

    public void setApprovalDate(String approvalDate)
    {
        this.approvalDate = approvalDate;
    }

    @XmlElement
    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
    }
    
}
