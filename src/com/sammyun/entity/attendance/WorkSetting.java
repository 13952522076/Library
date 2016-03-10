package com.sammyun.entity.attendance;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictSchool;

/**
 * 班次设置 <一句话功能简述> <功能详细描述> <br />
 * 说明: 1. 班次设置中的有效时间为教师考勤刷卡是否被记录的范围值。<br />
 * 如果刷卡时间不存在于任何一个有效时间内，那么该次刷卡将被忽略不计。<br />
 * 2. 班次设置示例：<br />
 * 教师班次为 8:00—17:00<br />
 * 上班打卡有效时间为： 0:00 — 12:00；<br />
 * 下班打卡有效时间为：12:00 — 23:59；<br />
 * 教师班次为 8:00—11:30； 13:30—17:00<br />
 * 上班打卡有效时间： 0:00 — 10:30；<br />
 * 中午休息起打卡有效时间： 10:30 — 12:30；<br />
 * 中午休息止打卡有效时间： 12:30 — 14:30；<br />
 * 下班打卡有效时间为： 14:30 — 23:59；<br />
 * 
 * @author xutianlong
 * @version [版本号, May 23, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_work_setting")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_work_setting_sequence")
public class WorkSetting extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 8962832903878173677L;

    /** 班次名称 */
    private String name;

    /** 上班时间 */
    private String workTime;

    /** 上班有效时间 开始时间 */
    private String startWorkTime;

    /** 上班有效时间 结束时间 */
    private String endWorkTime;

    /** 下班时间 */
    private String closingTime;

    /** 下班有效时间 开始时间 */
    private String startClosingTime;
    
    /** 下班有效时间 结束时间 */
    private String endClosingTime;
    
    /** 是否是默认排班 */
    private Boolean isDefalut;

    /** 隶属学校 */
    private DictSchool dictSchool;

    /**
     * @return 返回 name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param 对name进行赋值
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return 返回 workTime
     */
    public String getWorkTime()
    {
        return workTime;
    }

    /**
     * @param 对workTime进行赋值
     */
    public void setWorkTime(String workTime)
    {
        this.workTime = workTime;
    }

    /**
     * @return 返回 startWorkTime
     */
    public String getStartWorkTime()
    {
        return startWorkTime;
    }

    /**
     * @param 对startWorkTime进行赋值
     */
    public void setStartWorkTime(String startWorkTime)
    {
        this.startWorkTime = startWorkTime;
    }

    /**
     * @return 返回 endWorkTime
     */
    public String getEndWorkTime()
    {
        return endWorkTime;
    }

    /**
     * @param 对endWorkTime进行赋值
     */
    public void setEndWorkTime(String endWorkTime)
    {
        this.endWorkTime = endWorkTime;
    }

    /**
     * @return 返回 closingTime
     */
    public String getClosingTime()
    {
        return closingTime;
    }

    /**
     * @param 对closingTime进行赋值
     */
    public void setClosingTime(String closingTime)
    {
        this.closingTime = closingTime;
    }

    /**
     * @return 返回 startClosingTime
     */
    public String getStartClosingTime()
    {
        return startClosingTime;
    }

    /**
     * @param 对startClosingTime进行赋值
     */
    public void setStartClosingTime(String startClosingTime)
    {
        this.startClosingTime = startClosingTime;
    }
    
    /**
     * @return 返回 endClosingTime
     */
    public String getEndClosingTime()
    {
        return endClosingTime;
    }

    /**
     * @param 对endClosingTime进行赋值
     */
    public void setEndClosingTime(String endClosingTime)
    {
        this.endClosingTime = endClosingTime;
    }

    /**
     * @return 返回 dictSchool
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictSchool getDictSchool()
    {
        return dictSchool;
    }

    /**
     * @param 对dictSchool进行赋值
     */
    public void setDictSchool(DictSchool dictSchool)
    {
        this.dictSchool = dictSchool;
    }

    /**
     * @return isDefalut
     */
    public Boolean getIsDefalut()
    {
        return isDefalut;
    }

    /**
     * @param isDefalut
     */
    public void setIsDefalut(Boolean isDefalut)
    {
        this.isDefalut = isDefalut;
    }

}
