package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "teacherAskLeaveListBlock")
public class TeacherAskLeaveListBlock
{
    /**id*/
    private Long id;
    
    /** 审批老师 */
    private String approvalMemberName;

    /** 是否同意（同意了方可生效） */
    private Boolean isAgree;

    /** 创建时间 */
    private String createDate;
    
    /** 请假老师 */
    private String leaveMemberName;
    
    /** 请假老师头像 */
    private String leaveMemberIconPhoto;

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
    public String getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(String createDate)
    {
        this.createDate = createDate;
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
    public String getLeaveMemberIconPhoto()
    {
        return leaveMemberIconPhoto;
    }

    public void setLeaveMemberIconPhoto(String leaveMemberIconPhoto)
    {
        this.leaveMemberIconPhoto = leaveMemberIconPhoto;
    }
    
}
