package com.sammyun.entity.stu;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictStudent;

/**
 * Entity - 健康档案
 * 
 * @author xutianlong
 * @version [版本号, Jun 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_health_file")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_health_file_sequence")
public class HealthFile extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -4893732314778043560L;

    /** 身高 */
    private Double height;

    /** 体重 */
    private Double weight;

    /** 左眼视力 */
    private String leftVision;

    /** 右眼视力 */
    private String rightVision;
    
    /** 隶属学生 */
    private DictStudent dictStudent;

    /**
     * @return 返回 height
     */
    public Double getHeight()
    {
        return height;
    }

    /**
     * @param 对height进行赋值
     */
    public void setHeight(Double height)
    {
        this.height = height;
    }

    /**
     * @return 返回 weight
     */
    public Double getWeight()
    {
        return weight;
    }

    /**
     * @param 对weight进行赋值
     */
    public void setWeight(Double weight)
    {
        this.weight = weight;
    }

    
    /**
     * @return 返回 leftVision
     */
    public String getLeftVision()
    {
        return leftVision;
    }

    /**
     * @param 对leftVision进行赋值
     */
    public void setLeftVision(String leftVision)
    {
        this.leftVision = leftVision;
    }

    /**
     * @return 返回 rightVision
     */
    public String getRightVision()
    {
        return rightVision;
    }

    /**
     * @param 对rightVision进行赋值
     */
    public void setRightVision(String rightVision)
    {
        this.rightVision = rightVision;
    }

    /**
     * @return 返回 dictStudent
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.LAZY)
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
