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
 * 成長日記收藏
 * 
 * @author xutianlong
 * @version [版本号, Jul 3, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_diary_collection")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_diary_collection_sequence")
public class DiaryCollection extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 5401491852134026351L;

    /** 收藏狀態 */
    public enum CollectionsStatus
    {
        /** 正常 */
        normal,
        /** 刪除 */
        delete

    }

    /** 收藏時間 */
    private Date collectionsTime;

    /** 收藏狀態 */
    private CollectionsStatus collectionsStatus;

    /** 隸屬成長日記 */
    private GrowthDiary growthDiary;

    /** 收藏人 */
    private Member member;

    /**
     * @return 返回 collectionsTime
     */
    public Date getCollectionsTime()
    {
        return collectionsTime;
    }

    /**
     * @param 对collectionsTime进行赋值
     */
    public void setCollectionsTime(Date collectionsTime)
    {
        this.collectionsTime = collectionsTime;
    }

    /**
     * @return 返回 collectionsStatus
     */
    public CollectionsStatus getCollectionsStatus()
    {
        return collectionsStatus;
    }

    /**
     * @param 对collectionsStatus进行赋值
     */
    public void setCollectionsStatus(CollectionsStatus collectionsStatus)
    {
        this.collectionsStatus = collectionsStatus;
    }

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

}
