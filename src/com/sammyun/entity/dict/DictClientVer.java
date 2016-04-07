package com.sammyun.entity.dict;

// Generated 2015-3-21 21:51:07 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * DictClientVer * Entity - 客户端版本号数据字典
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_pe_dict_client_ver")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_dict_client_ver_sequence")
public class DictClientVer extends BaseEntity
{

    @SuppressWarnings("unused")
    private BigDecimal id;

    private String code;

    public DictClientVer()
    {
    }

    public DictClientVer(BigDecimal id)
    {
        this.id = id;
    }

    public DictClientVer(BigDecimal id, String code)
    {
        this.id = id;
        this.code = code;
    }

    @Column(name = "code", length = 250)
    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

}
