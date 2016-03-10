package com.sammyun.entity.gd;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * Entity - 日志标签
 * 
 * @author xutianlong
 * @version [版本号, Jul 9, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_diary_tag")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_diary_tag_sequence")
public class DiaryTag extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 4414410723055294256L;

    /** 标签名字 */
    private String name;

    /** 日志标签 */
    private Set<GrowthDiary> growthDiaries = new HashSet<GrowthDiary>();

    /**
     * @return 返回 name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param 对name进行赋值
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return 返回 growthDiaries
     */
    @ManyToMany(mappedBy = "diaryTags", fetch = FetchType.LAZY)
    public Set<GrowthDiary> getGrowthDiaries()
    {
        return growthDiaries;
    }

    /**
     * @param 对growthDiaries进行赋值
     */
    public void setGrowthDiaries(Set<GrowthDiary> growthDiaries)
    {
        this.growthDiaries = growthDiaries;
    }

}
