package com.sammyun.entity.attendance;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.Member;

/**
 * Entity - 老师请假
 * 
 * @author xutianlong
 * @version [版本号, Apr 11, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_teacher_ask_leave")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_teacher_ask_leave_sequence")
public class TeacherAskLeave extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 2661432386706410232L;

    /**
     * 请假类型
     * 
     * @author xutianlong
     * @version [版本号, Jul 27, 2015]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    public enum AskLeaveType
    {
        /** 病假 */
        sick,

        /** 事假 */
        compassionate,

        /** 年假 */
        annual,

        /** 调休 */
        off,

        /** 婚假 */
        marriage,

        /** 产假 */
        maternity,

        /** 陪产 */
        accompany,

        /** 路途假 */
        way,

        /** 丧假 */
        funeral,

        /** 其他 */
        other
    }

    /** 请假开始时间 */
    public Date leaveStartDate;

    /** 请假结束时间 */
    public Date leaveEndDate;

    /** 请假老师 */
    public Member leaveMember;

    /** 请假的天数 */
    public Double leaveDay;

    /** 请假说明 */
    public String description;

    /** 请假类型 */
    public AskLeaveType askLeaveType;

    /** 审批老师 */
    public Member approvalMember;
    
    /** 审批时间 */
    private Date approvalDate;

    /** 是否同意（同意了方可生效） */
    public Boolean isAgree;

    /** 审批意见 */
    public String approvalOpinion;

    /**
     * @return 返回 leaveStartDate
     */
    public Date getLeaveStartDate()
    {
        return leaveStartDate;
    }

    /**
     * @param 对leaveStartDate进行赋值
     */
    public void setLeaveStartDate(Date leaveStartDate)
    {
        this.leaveStartDate = leaveStartDate;
    }

    /**
     * @return 返回 leaveEndDate
     */
    public Date getLeaveEndDate()
    {
        return leaveEndDate;
    }

    /**
     * @param 对leaveEndDate进行赋值
     */
    public void setLeaveEndDate(Date leaveEndDate)
    {
        this.leaveEndDate = leaveEndDate;
    }

    /**
     * @return 返回 leaveMember
     */
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Member getLeaveMember()
    {
        return leaveMember;
    }

    /**
     * @param 对leaveMember进行赋值
     */
    public void setLeaveMember(Member leaveMember)
    {
        this.leaveMember = leaveMember;
    }

    /**
     * @return 返回 leaveDay
     */
    public Double getLeaveDay()
    {
        return leaveDay;
    }

    /**
     * @param 对leaveDay进行赋值
     */
    public void setLeaveDay(Double leaveDay)
    {
        this.leaveDay = leaveDay;
    }

    /**
     * @return 返回 description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param 对description进行赋值
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return 返回 askLeaveType
     */
    public AskLeaveType getAskLeaveType()
    {
        return askLeaveType;
    }

    /**
     * @param 对askLeaveType进行赋值
     */
    public void setAskLeaveType(AskLeaveType askLeaveType)
    {
        this.askLeaveType = askLeaveType;
    }

    /**
     * @return 返回 approvalMember
     */
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Member getApprovalMember()
    {
        return approvalMember;
    }

    /**
     * @param 对approvalMember进行赋值
     */
    public void setApprovalMember(Member approvalMember)
    {
        this.approvalMember = approvalMember;
    }

    
    /***
     * @return approvalDate
     */
    public Date getApprovalDate()
    {
        return approvalDate;
    }

    /**
     * @param approvalDate
     */
    public void setApprovalDate(Date approvalDate)
    {
        this.approvalDate = approvalDate;
    }

    /**
     * @return 返回 isAgree
     */
    public Boolean getIsAgree()
    {
        return isAgree;
    }

    /**
     * @param 对isAgree进行赋值
     */
    public void setIsAgree(Boolean isAgree)
    {
        this.isAgree = isAgree;
    }

    /**
     * @return 返回 approvalOpinion
     */
    public String getApprovalOpinion()
    {
        return approvalOpinion;
    }

    /**
     * @param 对approvalOpinion进行赋值
     */
    public void setApprovalOpinion(String approvalOpinion)
    {
        this.approvalOpinion = approvalOpinion;
    }

}
