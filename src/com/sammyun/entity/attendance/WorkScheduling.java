package com.sammyun.entity.attendance;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.Member;

/**
 * 排班管理 <br >
 * 说明：针对老师 做周一到周日的排班级（依据班次设置做）
 * 
 * @author xutianlong
 * @version [版本号, May 24, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_work_scheduling")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_work_setting_sequence")
public class WorkScheduling extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -7956874640618990267L;

    /**
     * 排班方式
     */
    public enum SchedulingWay
    {
        /** 按周排班 */
        week,
        /** 按指定日期排班 */
        date
    }
    
    /** 隶属的老师的排班(1对1) */
    private Member member;
    
    /** 隶属的老师的排班(1对1) */
    private SchedulingWay schedulingWay;

    /** 排班的具体情况（周一到周日） */
    private Map<String, WorkSetting> schedulingAttributes = new HashMap<String, WorkSetting>();

    /**
     * @return 返回 member
     */
    @NotFound(action = NotFoundAction.IGNORE)
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

    /**
     * t_pe_scheduling_attribute 表对应字段 
     * t_pe_work_scheduling表的序列ID<br />
     * member <br />
     * week <br />
     * t_pe_work_setting 的序列ID
     * @return 返回 schedulingAttributes
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "t_pe_scheduling_attribute")
    @MapKeyColumn(name = "week", length = 100)
    public Map<String, WorkSetting> getSchedulingAttributes()
    {
        return schedulingAttributes;
    }

    /**
     * @param 对schedulingAttributes进行赋值
     */
    public void setSchedulingAttributes(Map<String, WorkSetting> schedulingAttributes)
    {
        this.schedulingAttributes = schedulingAttributes;
    }

    /**
     * @return schedulingWay
     */
    public SchedulingWay getSchedulingWay()
    {
        return schedulingWay;
    }

    /**
     * @param schedulingWay
     */
    public void setSchedulingWay(SchedulingWay schedulingWay)
    {
        this.schedulingWay = schedulingWay;
    }

}
