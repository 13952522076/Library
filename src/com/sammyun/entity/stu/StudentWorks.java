package com.sammyun.entity.stu;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictStudent;

/**
 * Entity - 学生作品
 * 
 * @author xutianlong
 * @version [版本号, Jun 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_student_works")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_student_works_sequence")
public class StudentWorks extends BaseEntity
{
    private static final long serialVersionUID = -9030023871411841265L;

    /** 图片地址 */
    private String imageAttach;

    /** 图片描述 */
    private String description;

    /** 隶属学生 */
    private DictStudent dictStudent;

    /**
     * @return imageAttach
     */
    public String getImageAttach()
    {
        return imageAttach;
    }

    /**
     * @param imageAttach
     */
    public void setImageAttach(String imageAttach)
    {
        this.imageAttach = imageAttach;
    }

    /**
     * @return description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return dictStudent
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictStudent getDictStudent()
    {
        return dictStudent;
    }

    /**
     * @param dictStudent
     */
    public void setDictStudent(DictStudent dictStudent)
    {
        this.dictStudent = dictStudent;
    }
    
}
