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
 * Entity - 定向设备
 * 
 * @author xutianlong
 * @version [版本号, Aug 26, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_ad_device_type")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_ad_device_type_sequence")
public class AdDeviceType extends OrderEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -4509485608851249928L;

    private String deviceType;
    

    /** 广告 */
    private Set<Ad> ads = new HashSet<Ad>();

    /**
     * @return 返回 deviceType
     */
    @Column(length = 30)
    public String getDeviceType()
    {
        return deviceType;
    }

    /**
     * @param 对deviceType进行赋值
     */
    public void setDeviceType(String deviceType)
    {
        this.deviceType = deviceType;
    }

    /**
     * @return 返回 ads
     */
    @ManyToMany(mappedBy = "adDeviceTypes", fetch = FetchType.LAZY)
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
