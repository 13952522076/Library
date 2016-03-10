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
 * Entity - 网络类型 <一句话功能简述> <功能详细描述>
 * 
 * @author xutianlong
 * @version [版本号, Aug 26, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_ad_device_type")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_ad_device_type_sequence")
public class AdNetType extends OrderEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -5173965579854927177L;

    /** wifi,2g,2.5g,3g,4G 其他 */
    private String netType;
    

    /** 广告 */
    private Set<Ad> ads = new HashSet<Ad>();

    /**
     * @return 返回 netType
     */
    @Column(length = 30)
    public String getNetType()
    {
        return netType;
    }

    /**
     * @param 对netType进行赋值
     */
    public void setNetType(String netType)
    {
        this.netType = netType;
    }

    /**
     * @return 返回 ads
     */
    @ManyToMany(mappedBy = "adNetTypes", fetch = FetchType.LAZY)
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
