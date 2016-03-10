package com.sammyun.entity.course;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictSchool;

/**
 * Entity - 精品课程
 * 
 * @author xutianlong
 * @version [版本号, Jun 3, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_quality_course")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_quality_course_sequence")
public class QualityCourse extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 8846941909377286205L;

    /** 精品课程描述 */
    private String description;

    /** 隶属的学校 */
    private DictSchool dictSchool;

    /** 状态 （0未屏蔽，1屏蔽） */
    private Integer status;

    /** 精品课程 图片附件 列表 */
    private List<QualityCourseImageAttach> qualityCourseImageAttachs = new ArrayList<QualityCourseImageAttach>();

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
     * @return 返回 dictSchool
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictSchool getDictSchool()
    {
        return dictSchool;
    }

    /**
     * @param 对dictSchool进行赋值
     */
    public void setDictSchool(DictSchool dictSchool)
    {
        this.dictSchool = dictSchool;
    }

    /**
     * @return 返回 status
     */
    public Integer getStatus()
    {
        return status;
    }

    /**
     * @param 对status进行赋值
     */
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    /**
     * @return 返回 qualityCourseImageAttachs
     */
    @OneToMany(mappedBy = "qualityCourse", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    public List<QualityCourseImageAttach> getQualityCourseImageAttachs()
    {
        return qualityCourseImageAttachs;
    }

    /**
     * @param 对qualityCourseImageAttachs进行赋值
     */
    public void setQualityCourseImageAttachs(List<QualityCourseImageAttach> qualityCourseImageAttachs)
    {
        this.qualityCourseImageAttachs = qualityCourseImageAttachs;
    }

}
