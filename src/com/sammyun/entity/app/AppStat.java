package com.sammyun.entity.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import com.sammyun.entity.BaseEntity;

/***
 * Entity - 应用统计
 * 
 * @author xutianlong
 * @version [版本号, Aug 8, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_app_stat")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_app_stat_sequence")
public class AppStat extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -6970438181494770619L;

    /** 评价平均得分 */
    private Double avgRating;

    /** 得分数量 */
    private Integer countRating;

    /** 应用安装的用户量 */
    private Integer countUser;

    /** 应用查看的数量 */
    private Integer countView;

    /** 对应版本 */
    private App app;

    /**
     * @return 返回 avgRating
     */
    @Min(0)
    @Column(nullable = false)
    public Double getAvgRating()
    {
        return avgRating;
    }

    /**
     * @param 对avgRating进行赋值
     */
    public void setAvgRating(Double avgRating)
    {
        this.avgRating = avgRating;
    }

    /**
     * @return 返回 countRating
     */
    @Min(0)
    @Column(nullable = false)
    public Integer getCountRating()
    {
        return countRating;
    }

    /**
     * @param 对countRating进行赋值
     */
    public void setCountRating(Integer countRating)
    {
        this.countRating = countRating;
    }

    /**
     * @return 返回 countUser
     */
    @Min(0)
    @Column(nullable = false)
    public Integer getCountUser()
    {
        return countUser;
    }

    /**
     * @param 对countUser进行赋值
     */
    public void setCountUser(Integer countUser)
    {
        this.countUser = countUser;
    }

    /**
     * @return 返回 countView
     */
    @Min(0)
    @Column(nullable = false)
    public Integer getCountView()
    {
        return countView;
    }

    /**
     * @param 对countView进行赋值
     */
    public void setCountView(Integer countView)
    {
        this.countView = countView;
    }

    /**
     * @return 返回 app
     */
    @OneToOne(fetch = FetchType.LAZY)
    public App getApp()
    {
        return app;
    }

    /**
     * @param 对app进行赋值
     */
    public void setApp(App app)
    {
        this.app = app;
    }

}
