package com.sammyun.entity.course;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.OrderEntity;

/**
 * 精品课程 图片附件 列表
 * 
 * @author xutianlong
 * @version [版本号, Apr 21, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_quality_course_image_attach")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_quality_course_image_attach_sequence")
public class QualityCourseImageAttach extends OrderEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 2535548910277680963L;

    /** 图片附件 */
    private String imageAttach;

    /** 图片描述 */
    private String description;

    /** 隶属精品课程 */
    private QualityCourse qualityCourse;

    /**
     * @return 返回 imageAttach
     */
    public String getImageAttach()
    {
        return imageAttach;
    }

    /**
     * @param 对imageAttach进行赋值
     */
    public void setImageAttach(String imageAttach)
    {
        this.imageAttach = imageAttach;
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
     * @return 返回 qualityCourse
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public QualityCourse getQualityCourse()
    {
        return qualityCourse;
    }

    /**
     * @param 对qualityCourse进行赋值
     */
    public void setQualityCourse(QualityCourse qualityCourse)
    {
        this.qualityCourse = qualityCourse;
    }

}
