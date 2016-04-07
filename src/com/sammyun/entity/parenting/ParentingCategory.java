package com.sammyun.entity.parenting;

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
import com.sammyun.entity.dict.DictSchool;

/**
 * Entity - 育儿类别
 * 
 * @author xutianlong
 * @version [版本号, Apr 21, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

@Entity
@Table(name = "t_pe_parenting_category")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_parenting_category_sequence")
public class ParentingCategory extends BaseEntity
{
    private static final long serialVersionUID = 5525019389447443694L;
    
    /** 类别名称 */
    private String categoryName;

    /** 删除标记（0未删除，1删除） */
    private Integer defFlag;

    /** 状态（0，上架 1，下架） */
    private Integer status;

    /** 隶属的学校 */
    private DictSchool dictSchool;

    /** 育儿 */
    private Set<Parenting> parentings = new HashSet<Parenting>();

    /**
     * @return categoryName
     */
    public String getCategoryName()
    {
        return categoryName;
    }

    /**
     * @param categoryName
     */
    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    /**
     * @return defFlag
     */
    public Integer getDefFlag()
    {
        return defFlag;
    }

    /**
     * @param defFlag
     */
    public void setDefFlag(Integer defFlag)
    {
        this.defFlag = defFlag;
    }

    /**
     * @return status
     */
    public Integer getStatus()
    {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    /**
     * @return dictSchool
     * @see [类、类#方法、类#成员]
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    public DictSchool getDictSchool()
    {
        return dictSchool;
    }

    /**
     * @param dictSchool
     */
    public void setDictSchool(DictSchool dictSchool)
    {
        this.dictSchool = dictSchool;
    }

    /**
     * @return parentings
     */
    @OneToMany(mappedBy = "parentingCategory", fetch = FetchType.LAZY)
    public Set<Parenting> getParentings()
    {
        return parentings;
    }

    /**
     * @param parentings
     */
    public void setParentings(Set<Parenting> parentings)
    {
        this.parentings = parentings;
    }

}
