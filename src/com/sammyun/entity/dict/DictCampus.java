package com.sammyun.entity.dict;


import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 *  Entity - 校区
 * 
 * @author Sencloud Team

 */
@Entity
@Table(name = "t_pe_dict_campus")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_dict_campus_sequence")
public class DictCampus extends BaseEntity {

	private BigDecimal id;
	private String name;
	private BigDecimal regionId;
	private BigDecimal schoolId;

	public DictCampus() {
	}

	public DictCampus(BigDecimal id) {
		this.id = id;
	}

	public DictCampus(BigDecimal id, String name, BigDecimal regionId,
			BigDecimal schoolId) {
		this.id = id;
		this.name = name;
		this.regionId = regionId;
		this.schoolId = schoolId;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "region_id", scale = 0)
	public BigDecimal getRegionId() {
		return this.regionId;
	}

	public void setRegionId(BigDecimal regionId) {
		this.regionId = regionId;
	}

	@Column(name = "school_id", scale = 0)
	public BigDecimal getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(BigDecimal schoolId) {
		this.schoolId = schoolId;
	}

}
