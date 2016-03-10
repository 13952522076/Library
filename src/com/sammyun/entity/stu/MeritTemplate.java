package com.sammyun.entity.stu;

import java.util.HashSet;
import java.util.Set;

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

import com.sammyun.entity.OrderEntity;
import com.sammyun.entity.dict.DictSchool;

/***
 * Entity - 评价等级模板
 * 
 * @author xutianlong
 * @version [版本号, Jun 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_merit_template")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_merit_template_sequence")
public class MeritTemplate extends OrderEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 9031414854493983285L;

    /** 评价项名称 */
    private String meritName;

    /** 隶属学校 */
    private DictSchool dictSchool;

    /** 综合评价列表 */
    private Set<OverallMerit> overallMerits = new HashSet<OverallMerit>();

    /**
     * @return 返回 meritName
     */
    public String getMeritName()
    {
        return meritName;
    }

    /**
     * @param 对meritName进行赋值
     */
    public void setMeritName(String meritName)
    {
        this.meritName = meritName;
    }

    /**
     * @return 返回 dictSchool
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
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
     * @return 返回 overallMerits
     */
    @OneToMany(mappedBy = "meritTemplate", fetch = FetchType.LAZY)
    public Set<OverallMerit> getOverallMerits()
    {
        return overallMerits;
    }

    /**
     * @param 对overallMerits进行赋值
     */
    public void setOverallMerits(Set<OverallMerit> overallMerits)
    {
        this.overallMerits = overallMerits;
    }

}
