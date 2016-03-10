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
 * DictUserAgent * Entity - 用户浏览器数据字典
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Entity
@Table(name = "t_pe_dict_user_agent")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_dict_user_agent_sequence")
public class DictUserAgent extends BaseEntity {

	private BigDecimal id;
	private String code;

	public DictUserAgent() {
	}

	public DictUserAgent(BigDecimal id) {
		this.id = id;
	}

	public DictUserAgent(BigDecimal id, String code) {
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
