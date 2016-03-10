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
 * 分享
 * 
 * @author xutianlong
 * @version [版本号, Jul 3, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_diary_transpond")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_diary_transpond_sequence")
public class DiaryTranspond extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 5401491852134026351L;

    /** 分享到哪个平台 */
    public enum Shared
    {
        /** 微信 */
        weixin
    }

    /** 分享時間 */
    private Date transpondTime;

    /** 隸屬成長日記 */
    private GrowthDiary growthDiary;

    /** 分享人 */
    private Member member;

    /** 分享到哪个平台 */
    private Shared shared;

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
     * @return 返回 transpondTime
     */
    public Date getTranspondTime()
    {
        return transpondTime;
    }

    /**
     * @param 对transpondTime进行赋值
     */
    public void setTranspondTime(Date transpondTime)
    {
        this.transpondTime = transpondTime;
    }

    /**
     * @return 返回 shared
     */
    public Shared getShared()
    {
        return shared;
    }

    /**
     * @param 对shared进行赋值
     */
    public void setShared(Shared shared)
    {
        this.shared = shared;
    }
}
