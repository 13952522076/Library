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
import com.sammyun.entity.dict.DictClass;

/**
 * Entity - 毕业合影
 * @author  xutianlong
 * @version  [版本号, 2015-6-18]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_graduation_photo")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_graduation_photo_sequence")
public class GraduationPhoto extends BaseEntity
{
    private static final long serialVersionUID = -3606073846263301936L;
    
    /** 图片地址 */
    private String imageAttach;

    /** 图片描述 */
    private String description;

    /** 隶属班级 */
    private DictClass dictClass;

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
     * @return dictClass
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictClass getDictClass()
    {
        return dictClass;
    }

    /**
     * @param dictClass
     */
    public void setDictClass(DictClass dictClass)
    {
        this.dictClass = dictClass;
    }
}
