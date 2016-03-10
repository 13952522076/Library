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

/***
 * 成長日記圖片附件
 * 
 * @author xutianlong
 * @version [版本号, Jul 3, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_diary_picture")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_diary_picture_sequence")
public class DiaryPicture extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 6712217903855437076L;

    /** 成长日記 */
    private GrowthDiary growthDiary;

    /** 图片地址 */
    private String pictureUrl;

    /** 图片类别 */
    private String pictureType;

    /** 图片时间 */
    private Date pictureTime;

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
     * @return 返回 pictureUrl
     */
    public String getPictureUrl()
    {
        return pictureUrl;
    }

    /**
     * @param 对pictureUrl进行赋值
     */
    public void setPictureUrl(String pictureUrl)
    {
        this.pictureUrl = pictureUrl;
    }

    /**
     * @return 返回 pictureType
     */
    public String getPictureType()
    {
        return pictureType;
    }

    /**
     * @param 对pictureType进行赋值
     */
    public void setPictureType(String pictureType)
    {
        this.pictureType = pictureType;
    }

    /**
     * @return 返回 pictureTime
     */
    public Date getPictureTime()
    {
        return pictureTime;
    }

    /**
     * @param 对pictureTime进行赋值
     */
    public void setPictureTime(Date pictureTime)
    {
        this.pictureTime = pictureTime;
    }
}
