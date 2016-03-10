package com.sammyun.entity.stu;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictStudent;

/**
 * Entity - 综合评价
 * 
 * @author xutianlong
 * @version [版本号, Jun 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_overall_merit")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_overall_merit_sequence")
public class OverallMerit extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -2198272523394744536L;

    /** 评价等级 */
    private Double grade;

    /** 评价模板 */
    private MeritTemplate meritTemplate;

    /** 隶属学生 */
    private DictStudent dictStudent;


    /**
     * @return 返回 grade
     */
    public Double getGrade()
    {
        return grade;
    }

    /**
     * @param 对grade进行赋值
     */
    public void setGrade(Double grade)
    {
        this.grade = grade;
    }

    /**
     * @return 返回 meritTemplate
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public MeritTemplate getMeritTemplate()
    {
        return meritTemplate;
    }

    /**
     * @param 对meritTemplate进行赋值
     */
    public void setMeritTemplate(MeritTemplate meritTemplate)
    {
        this.meritTemplate = meritTemplate;
    }

    /**
     * @return 返回 dictStudent
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictStudent getDictStudent()
    {
        return dictStudent;
    }

    /**
     * @param 对dictStudent进行赋值
     */
    public void setDictStudent(DictStudent dictStudent)
    {
        this.dictStudent = dictStudent;
    }

}
