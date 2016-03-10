package com.sammyun.entity.yellowpage;

// Generated 2015-3-21 21:51:07 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * Yellowpage * Entity - 黄页数据
 * 
 * @author Sencloud Team

 */
@Entity
@Table(name = "t_pe_yellowpage")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_yellowpage_sequence")
public class Yellowpage extends BaseEntity {

	private BigDecimal id;
	private String address;
	private BigDecimal avatarId;
	private String code;
	private String extension;
	private String fax;
	private String function;
	private String urlHome;
	private String mail;
	private String name;
	private String tel;
	private BigDecimal schoolId;
	private BigDecimal parentId;

	public Yellowpage() {
	}

	public Yellowpage(BigDecimal id) {
		this.id = id;
	}

	public Yellowpage(BigDecimal id, String address, BigDecimal avatarId,
			String code, String extension, String fax, String function,
			String urlHome, String mail, String name, String tel,
			BigDecimal schoolId, BigDecimal parentId) {
		this.id = id;
		this.address = address;
		this.avatarId = avatarId;
		this.code = code;
		this.extension = extension;
		this.fax = fax;
		this.function = function;
		this.urlHome = urlHome;
		this.mail = mail;
		this.name = name;
		this.tel = tel;
		this.schoolId = schoolId;
		this.parentId = parentId;
	}
	
	@Column(name = "address", length = 200)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "avatar_id", scale = 0)
	public BigDecimal getAvatarId() {
		return this.avatarId;
	}

	public void setAvatarId(BigDecimal avatarId) {
		this.avatarId = avatarId;
	}

	@Column(name = "code", length = 40)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "extension", length = 30)
	public String getExtension() {
		return this.extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	@Column(name = "fax", length = 100)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "function", length = 1000)
	public String getFunction() {
		return this.function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@Column(name = "url_home", length = 200)
	public String getUrlHome() {
		return this.urlHome;
	}

	public void setUrlHome(String urlHome) {
		this.urlHome = urlHome;
	}

	@Column(name = "mail", length = 200)
	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	@Column(name = "name", length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "tel", length = 100)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "school_id", scale = 0)
	public BigDecimal getSchoolId() {
		return this.schoolId;
	}

	public void setSchoolId(BigDecimal schoolId) {
		this.schoolId = schoolId;
	}

	@Column(name = "parent_id", scale = 0)
	public BigDecimal getParentId() {
		return this.parentId;
	}

	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}

}
