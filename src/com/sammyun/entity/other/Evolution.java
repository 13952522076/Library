package com.sammyun.entity.other;

// Generated 2015-3-21 21:51:07 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sammyun.entity.BaseEntity;

/**
 * Evolution * Entity -
 */
@Entity
@Table(name = "t_pe_evolution")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_evolution_sequence")
public class Evolution extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -4757765986361501640L;

    private String FHash;

    private String module;

    private Long FType;

    private String FValue;

    private Long dbType;

    private Long sqlType;

    private Date appliedAt;

    private Long status;

    private String lastProblem;

    public Evolution()
    {
    }

    public Evolution(String FHash, Date appliedAt)
    {
        this.FHash = FHash;
        this.appliedAt = appliedAt;
    }

    public Evolution(String FHash, String module, Long FType, String FValue, Long dbType, Long sqlType, Date appliedAt,
            Long status, String lastProblem)
    {
        this.FHash = FHash;
        this.module = module;
        this.FType = FType;
        this.FValue = FValue;
        this.dbType = dbType;
        this.sqlType = sqlType;
        this.appliedAt = appliedAt;
        this.status = status;
        this.lastProblem = lastProblem;
    }

    @Id
    @Column(name = "f_hash", unique = true, nullable = false, length = 100)
    public String getFHash()
    {
        return this.FHash;
    }

    public void setFHash(String FHash)
    {
        this.FHash = FHash;
    }

    @Column(name = "module")
    public String getModule()
    {
        return this.module;
    }

    public void setModule(String module)
    {
        this.module = module;
    }

    @Column(name = "f_type", precision = 10, scale = 0)
    public Long getFType()
    {
        return this.FType;
    }

    public void setFType(Long FType)
    {
        this.FType = FType;
    }

    @Column(name = "f_value", length = 65535)
    public String getFValue()
    {
        return this.FValue;
    }

    public void setFValue(String FValue)
    {
        this.FValue = FValue;
    }

    @Column(name = "db_type", precision = 10, scale = 0)
    public Long getDbType()
    {
        return this.dbType;
    }

    public void setDbType(Long dbType)
    {
        this.dbType = dbType;
    }

    @Column(name = "sql_type", precision = 10, scale = 0)
    public Long getSqlType()
    {
        return this.sqlType;
    }

    public void setSqlType(Long sqlType)
    {
        this.sqlType = sqlType;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "applied_at", nullable = false, length = 19)
    public Date getAppliedAt()
    {
        return this.appliedAt;
    }

    public void setAppliedAt(Date appliedAt)
    {
        this.appliedAt = appliedAt;
    }

    @Column(name = "status", precision = 10, scale = 0)
    public Long getStatus()
    {
        return this.status;
    }

    public void setStatus(Long status)
    {
        this.status = status;
    }

    @Column(name = "last_problem", length = 65535)
    public String getLastProblem()
    {
        return this.lastProblem;
    }

    public void setLastProblem(String lastProblem)
    {
        this.lastProblem = lastProblem;
    }

}
