package com.sammyun.entity.stu;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictClass;

/**
 * Entity - 毕业证书
 * 
 * @author xutianlong
 * @version [版本号, Jun 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_graduation_certificate")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_graduation_certificate_sequence")
public class GraduationCertificate extends BaseEntity
{
    private static final long serialVersionUID = 451350228673908506L;
    
    /** 毕业时间 */
    private Date graduationDate;
    
    /** 隶属班级 */
    private DictClass dictClass;

    /**
     * @return graduationDate
     */
    public Date getGraduationDate()
    {
        return graduationDate;
    }

    /**
     * @param graduationDate
     */
    public void setGraduationDate(Date graduationDate)
    {
        this.graduationDate = graduationDate;
    }

    /**
     * @return dictClass
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @OneToOne(fetch = FetchType.LAZY)
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
