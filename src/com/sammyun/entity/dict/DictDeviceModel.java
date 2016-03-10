package com.sammyun.entity.dict;

// Generated 2015-3-21 21:51:07 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * DictDeviceModel * Entity - 设备数据字典
 * 
 * @author Sencloud Team

 */
@Entity
@Table(name = "t_pe_dict_device_model")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_dict_device_model_sequence")
public class DictDeviceModel extends BaseEntity {

	private BigDecimal id;
	private String code;

	public DictDeviceModel() {
	}

	public DictDeviceModel(BigDecimal id) {
		this.id = id;
	}

	public DictDeviceModel(BigDecimal id, String code) {
		this.id = id;
		this.code = code;
	}

	@Column(name = "code", length = 250)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
