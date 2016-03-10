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
 * Entity - 定向运营商
 * 
 * @author xutianlong
 * @version [版本号, Aug 26, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_ad_net_work")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_ad_net_work_sequence")
public class AdNetWork extends OrderEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -5996617624753089504L;

    /** 定向运营商 */
    private String netWork;
    

    /** 广告 */
    private Set<Ad> ads = new HashSet<Ad>();

    /**
     * @return 返回 netWork
     */
    @Column(length = 30)
    public String getNetWork()
    {
        return netWork;
    }

    /**
     * @param 对netWork进行赋值
     */
    public void setNetWork(String netWork)
    {
        this.netWork = netWork;
    }

    /**
     * @return 返回 ads
     */
    @ManyToMany(mappedBy = "adNetWorks", fetch = FetchType.LAZY)
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
