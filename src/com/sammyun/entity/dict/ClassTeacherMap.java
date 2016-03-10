package com.sammyun.entity.dict;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.Member;

/**
 * entity - 班级的老师列表
 * 
 * @author xutianlong
 * @version [版本号, Apr 6, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_class_teacher_map")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_class_teacher_map_sequence")
public class ClassTeacherMap  extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -1170546399826858727L;

    /** 对应的班级 */
    private DictClass dictClass;

    /** 对应的老师 */
    private Member member;

    /** 老师对应的科目 */
    private String subject;

    /** 是否是班主任 */
    private Boolean isHeadMaster;

    /** 备注 */
    private String remark;

    /**
     * @return 返回 dictClass
     */
    
    @NotNull
    @NotFound(action=NotFoundAction.IGNORE)    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictClass getDictClass()
    {
        return dictClass;
    }

    /**
     * @param 对dictClass进行赋值
     */
    public void setDictClass(DictClass dictClass)
    {
        this.dictClass = dictClass;
    }

    /**
     * @return 返回 member
     */
    @NotNull
    @NotFound(action=NotFoundAction.IGNORE)    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Member getMember()
    {
        return member;
    }

    /**
     * @param 对member进行赋值
     */
    public void setMember(Member member)
    {
        this.member = member;
    }

    /**
     * @return 返回 subject
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * @param 对subject进行赋值
     */
    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    /**
     * @return 返回 isHeadMaster
     */
    public Boolean getIsHeadMaster()
    {
        return isHeadMaster;
    }

    /**
     * @param 对isHeadMaster进行赋值
     */
    public void setIsHeadMaster(Boolean isHeadMaster)
    {
        this.isHeadMaster = isHeadMaster;
    }

    /**
     * @return 返回 remark
     */
    public String getRemark()
    {
        return remark;
    }

    /**
     * @param 对remark进行赋值
     */
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

}
