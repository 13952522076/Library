package com.sammyun.entity.library;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * 图书信息管理系统数据统计表
 * 
 * @author maxu
 */
@Entity
@Table(name = "t_pe_statistics")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_statistics_sequence")
public class Statistics extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -2798028155336348301L;

    /** 项目评分所需最少投票数 */
    private Float minVoters;

    /** 所有项目综合评分 */
    private Float averageScore;

    public Float getMinVoters()
    {
        return minVoters;
    }

    public void setMinVoters(Float minVoters)
    {
        this.minVoters = minVoters;
    }

    public Float getAverageScore()
    {
        return averageScore;
    }

    public void setAverageScore(Float averageScore)
    {
        this.averageScore = averageScore;
    }

}
