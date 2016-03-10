package com.sammyun.entity.dict;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * Entity - 设备凭证数据字典
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Entity
@Table(name = "t_pe_dict_device_token")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_dict_device_token_sequence")
public class DictDeviceToken extends BaseEntity
{

    private BigDecimal id;

    private String code;

    public DictDeviceToken()
    {
    }

    public DictDeviceToken(BigDecimal id)
    {
        this.id = id;
    }

    public DictDeviceToken(BigDecimal id, String code)
    {
        this.id = id;
        this.code = code;
    }

    @Column(name = "code", length = 100)
    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

}
