package com.sammyun.entity.dict;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.OrderEntity;
import com.sammyun.entity.classalbum.ClassAlbumImage;

/**
 * Entity - 年级表
 * 
 * @author xutianlong
 * @version [版本号, Apr 11, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_dict_grade")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_dict_grade_sequence")
public class DictGrade extends OrderEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -2401770300915580451L;

    /** 年级名称 */
    private String gradeName;

    /** 学校 */
    private DictSchool dictSchool;
    
    /** 班级列表 */
    private Set<DictClass> dictClasses = new HashSet<DictClass>();

    /**
     * @return 返回 gradeName
     */
    public String getGradeName()
    {
        return gradeName;
    }

    /**
     * @param 对gradeName进行赋值
     */
    public void setGradeName(String gradeName)
    {
        this.gradeName = gradeName;
    }

    /**
     * @return 返回 dictSchool
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
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

    @OneToMany(mappedBy = "dictGrade", fetch = FetchType.LAZY)
    public Set<DictClass> getDictClasses()
    {
        return dictClasses;
    }

    public void setDictClasses(Set<DictClass> dictClasses)
    {
        this.dictClasses = dictClasses;
    }
}
