package com.sammyun.entity.ad;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.OrderEntity;

/**
 * Entity - 投放时间段
 * 
 * @author xutianlong
 * @version [版本号, Aug 26, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_ad_time_slot")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_ad_time_slot_sequence")
public class AdTimeSlot extends OrderEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 6600706951328570392L;

    /** 时间点 */
    private String slotTime;

    /** 广告 */
    private Set<Ad> ads = new HashSet<Ad>();

    /**
     * @return 返回 slotTime
     */
    @Column(length = 40)
    public String getSlotTime()
    {
        return slotTime;
    }

    /**
     * @param 对slotTime进行赋值
     */
    public void setSlotTime(String slotTime)
    {
        this.slotTime = slotTime;
    }

    /**
     * @return 返回 ads
     */
    @ManyToMany(mappedBy = "adTimeSlots", fetch = FetchType.LAZY)
    public Set<Ad> getAds()
    {
        return ads;
    }

    /**
     * @param 对ads进行赋值
     */
    public void setAds(Set<Ad> ads)
    {
        this.ads = ads;
    }

}
