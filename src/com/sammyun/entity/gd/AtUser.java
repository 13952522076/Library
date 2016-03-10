package com.sammyun.entity.gd;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.Member;

/**
 * At到用戶列表
 * 
 * @author xutianlong
 * @version [版本号, Jul 3, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_at_user")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_at_user_sequence")
public class AtUser extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    /** 成长日记 */
    private GrowthDiary growthDiary;

    /** 被at用户I */
    private Member member;

    /** 被at时间 */
    private Date atusersTime;

    /**
     * @return 返回 growthDiary
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public GrowthDiary getGrowthDiary()
    {
        return growthDiary;
    }

    /**
     * @param 对growthDiary进行赋值
     */
    public void setGrowthDiary(GrowthDiary growthDiary)
    {
        this.growthDiary = growthDiary;
    }

    /**
     * @return 返回 member
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Member getMember()
    {
        return member;
    }

    /**
     * @param 对member进行赋值
     */
    public void setMember(Member member)
    {
        this.member = member;
    }

    /**
     * @return 返回 atusersTime
     */
    public Date getAtusersTime()
    {
        return atusersTime;
    }

    /**
     * @param 对atusersTime进行赋值
     */
    public void setAtusersTime(Date atusersTime)
    {
        this.atusersTime = atusersTime;
    }

}
