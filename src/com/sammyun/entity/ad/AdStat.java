package com.sammyun.entity.ad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;
import com.sammyun.hibernate.listener.MemberListener;

/**
 * Entity - 广告统计
 * 
 * @author xutianlong
 * @version [版本号, Aug 27, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_ad_stat")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_ad_stat_sequence")
public class AdStat extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 528728985214429326L;

    /** ip */
    private String ip;

    /** 点击的用户的序列ID */
    private Long memberId;

    /** 设备信息 以JSON格式保存 */
    private String deviceInfo;

    /**
     * @return 返回 ip
     */
    @Column(length = 20)
    public String getIp()
    {
        return ip;
    }

    /**
     * @param 对ip进行赋值
     */
    public void setIp(String ip)
    {
        this.ip = ip;
    }

    /**
     * @return 返回 memberId
     */
    @Column(length = 11)
    public Long getMemberId()
    {
        return memberId;
    }

    /**
     * @param 对memberId进行赋值
     */
    public void setMemberId(Long memberId)
    {
        this.memberId = memberId;
    }

    /**
     * @return 返回 deviceInfo
     */
    public String getDeviceInfo()
    {
        return deviceInfo;
    }

    /**
     * @param 对deviceInfo进行赋值
     */
    public void setDeviceInfo(String deviceInfo)
    {
        this.deviceInfo = deviceInfo;
    }

}
