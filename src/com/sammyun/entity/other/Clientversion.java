package com.sammyun.entity.other;

// Generated 2015-3-21 21:51:07 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * Clientversion * Entity - 客户端版本管理
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Entity
@Table(name = "t_pe_clientversion")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_clientversion_sequence")
public class Clientversion extends BaseEntity {

	private BigDecimal id;
	private BigDecimal timeCreate;
	private String description;
	private String urlDownload;
	private String fileSize;
	private BigDecimal timeModify;
	private Long typeOs;
	private Long flagPublish;
	private BigDecimal timePublish;
	private Long typeUpgrade;
	private String versionName;
	private String versionNumber;
	private BigDecimal idCreator;
	private BigDecimal idModifier;
	private BigDecimal idPublisher;

	public Clientversion() {
	}

	public Clientversion(BigDecimal id) {
		this.id = id;
	}

	public Clientversion(BigDecimal id, BigDecimal timeCreate,
			String description, String urlDownload, String fileSize,
			BigDecimal timeModify, Long typeOs, Long flagPublish,
			BigDecimal timePublish, Long typeUpgrade, String versionName,
			String versionNumber, BigDecimal idCreator, BigDecimal idModifier,
			BigDecimal idPublisher) {
		this.id = id;
		this.timeCreate = timeCreate;
		this.description = description;
		this.urlDownload = urlDownload;
		this.fileSize = fileSize;
		this.timeModify = timeModify;
		this.typeOs = typeOs;
		this.flagPublish = flagPublish;
		this.timePublish = timePublish;
		this.typeUpgrade = typeUpgrade;
		this.versionName = versionName;
		this.versionNumber = versionNumber;
		this.idCreator = idCreator;
		this.idModifier = idModifier;
		this.idPublisher = idPublisher;
	}

	@Column(name = "time_create", scale = 0)
	public BigDecimal getTimeCreate() {
		return this.timeCreate;
	}

	public void setTimeCreate(BigDecimal timeCreate) {
		this.timeCreate = timeCreate;
	}

	@Column(name = "description", length = 500)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "url_download", length = 200)
	public String getUrlDownload() {
		return this.urlDownload;
	}

	public void setUrlDownload(String urlDownload) {
		this.urlDownload = urlDownload;
	}

	@Column(name = "file_size", length = 20)
	public String getFileSize() {
		return this.fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	@Column(name = "time_modify", scale = 0)
	public BigDecimal getTimeModify() {
		return this.timeModify;
	}

	public void setTimeModify(BigDecimal timeModify) {
		this.timeModify = timeModify;
	}

	@Column(name = "type_os", precision = 10, scale = 0)
	public Long getTypeOs() {
		return this.typeOs;
	}

	public void setTypeOs(Long typeOs) {
		this.typeOs = typeOs;
	}

	@Column(name = "flag_publish", precision = 10, scale = 0)
	public Long getFlagPublish() {
		return this.flagPublish;
	}

	public void setFlagPublish(Long flagPublish) {
		this.flagPublish = flagPublish;
	}

	@Column(name = "time_publish", scale = 0)
	public BigDecimal getTimePublish() {
		return this.timePublish;
	}

	public void setTimePublish(BigDecimal timePublish) {
		this.timePublish = timePublish;
	}

	@Column(name = "type_upgrade", precision = 10, scale = 0)
	public Long getTypeUpgrade() {
		return this.typeUpgrade;
	}

	public void setTypeUpgrade(Long typeUpgrade) {
		this.typeUpgrade = typeUpgrade;
	}

	@Column(name = "version_name", length = 100)
	public String getVersionName() {
		return this.versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	@Column(name = "version_number", length = 30)
	public String getVersionNumber() {
		return this.versionNumber;
	}

	public void setVersionNumber(String versionNumber) {
		this.versionNumber = versionNumber;
	}

	@Column(name = "id_creator", scale = 0)
	public BigDecimal getIdCreator() {
		return this.idCreator;
	}

	public void setIdCreator(BigDecimal idCreator) {
		this.idCreator = idCreator;
	}

	@Column(name = "id_modifier", scale = 0)
	public BigDecimal getIdModifier() {
		return this.idModifier;
	}

	public void setIdModifier(BigDecimal idModifier) {
		this.idModifier = idModifier;
	}

	@Column(name = "id_publisher", scale = 0)
	public BigDecimal getIdPublisher() {
		return this.idPublisher;
	}

	public void setIdPublisher(BigDecimal idPublisher) {
		this.idPublisher = idPublisher;
	}

}
