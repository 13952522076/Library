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
 * Entity - 专业
 * 
 * @author Sencloud Team

 */
@Entity
@Table(name = "t_pe_dict_specialty")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_dict_specialty_sequence")
public class DictSpecialty extends BaseEntity {

	private BigDecimal id;
	private String code;
	private String description;
	private String name;
	private String codeStd;
	private BigDecimal idDept;

	public DictSpecialty() {
	}

	public DictSpecialty(BigDecimal id) {
		this.id = id;
	}

	public DictSpecialty(BigDecimal id, String code, String description,
			String name, String codeStd, BigDecimal idDept) {
		this.id = id;
		this.code = code;
		this.description = description;
		this.name = name;
		this.codeStd = codeStd;
		this.idDept = idDept;
	}

	@Column(name = "code", length = 40)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "description", length = 2000)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "code_std", length = 40)
	public String getCodeStd() {
		return this.codeStd;
	}

	public void setCodeStd(String codeStd) {
		this.codeStd = codeStd;
	}

	@Column(name = "id_dept", scale = 0)
	public BigDecimal getIdDept() {
		return this.idDept;
	}

	public void setIdDept(BigDecimal idDept) {
		this.idDept = idDept;
	}

}
