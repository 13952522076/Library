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
 * 上学放学时间设置
 * 时间设置示例：<br />
 * 上学时间为 8:00<br />
 * 上学打卡有效时间为： 8:00 — 8:10；<br />
 * 放学时间为 15:30
 * 放学打卡有效时间为：15:30 — 15:40；<br />
 * @author xutianlong
 * @version [版本号, May 23, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_school_hours")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_school_hours_sequence")
public class SchoolHours extends BaseEntity
{
    private static final long serialVersionUID = 4497336345452662012L;
    
    /** 上学时间 */
    private String attendTime;

    /** 上学有效时间 开始时间 */
    private String startAttendTime;

    /** 上学有效时间 结束时间 */
    private String endAttendTime;

    /** 放学时间 */
    private String finishTime;

    /** 放学有效时间 开始时间 */
    private String startFinishTime;
    
    /** 放学有效时间 结束时间 */
    private String endFinishTime;

    /** 隶属学校 */
    private DictSchool dictSchool;

    /**
     * @return attendTime
     */
    public String getAttendTime()
    {
        return attendTime;
    }

    /**
     * @param attendTime
     */
    public void setAttendTime(String attendTime)
    {
        this.attendTime = attendTime;
    }

    /**
     * @return startAttendTime
     */
    public String getStartAttendTime()
    {
        return startAttendTime;
    }
    
    /**
     * @param startAttendTime
     */
    public void setStartAttendTime(String startAttendTime)
    {
        this.startAttendTime = startAttendTime;
    }

    /**
     * @return endAttendTime
     */
    public String getEndAttendTime()
    {
        return endAttendTime;
    }

    /**
     * @param endAttendTime
     */
    public void setEndAttendTime(String endAttendTime)
    {
        this.endAttendTime = endAttendTime;
    }

    /**
     * @return finishTime
     */
    public String getFinishTime()
    {
        return finishTime;
    }

    /**
     * @param finishTime
     */
    public void setFinishTime(String finishTime)
    {
        this.finishTime = finishTime;
    }

    /**
     * @return startFinishTime
     */
    public String getStartFinishTime()
    {
        return startFinishTime;
    }

    /**
     * @param startFinishTime
     */
    public void setStartFinishTime(String startFinishTime)
    {
        this.startFinishTime = startFinishTime;
    }

    /**
     * @return endFinishTime
     */
    public String getEndFinishTime()
    {
        return endFinishTime;
    }

    /**
     * @param endFinishTime
     */
    public void setEndFinishTime(String endFinishTime)
    {
        this.endFinishTime = endFinishTime;
    }

    /**
     * @return dictSchool
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
     * @param dictSchool
     */
    public void setDictSchool(DictSchool dictSchool)
    {
        this.dictSchool = dictSchool;
    }

}
