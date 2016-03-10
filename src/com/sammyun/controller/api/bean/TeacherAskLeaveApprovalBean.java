package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "teacherAskLeaveapprovalBean")
public class TeacherAskLeaveApprovalBean
{
    private Long id;
    
    /** 是否同意（同意了方可生效） */
    public Boolean isAgree;

    /** 审批意见 */
    public String approvalOpinion;

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
    
}
