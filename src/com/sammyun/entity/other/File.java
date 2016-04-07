package com.sammyun.entity.other;

// Generated 2015-3-21 21:51:07 by Hibernate Tools 3.4.0.CR1

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sammyun.entity.BaseEntity;

/**
 * File * Entity - 文件
 */
@Entity
@Table(name = "t_pe_file")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_file_sequence")
public class File extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -2158768252116686867L;

    @SuppressWarnings("unused")
    private BigDecimal id;

    private Long appId;

    private String attr;

    private Long bizId;

    private String catalog;

    private String fileDesc;

    private Long domainId;

    private Long fileIndex;

    private Boolean isPublic;

    private String meta;

    private String name;

    private String owner;

    private Long scope;

    private Long fileSize;

    private Long status;

    private String storage;

    private Date updateAt;

    private BigDecimal userId;

    public File()
    {
    }

    public File(BigDecimal id, Date updateAt)
    {
        this.id = id;
        this.updateAt = updateAt;
    }

    public File(BigDecimal id, Long appId, String attr, Long bizId, String catalog, String fileDesc, Long domainId,
            Long fileIndex, Boolean isPublic, String meta, String name, String owner, Long scope, Long fileSize,
            Long status, String storage, Date updateAt, BigDecimal userId)
    {
        this.id = id;
        this.appId = appId;
        this.attr = attr;
        this.bizId = bizId;
        this.catalog = catalog;
        this.fileDesc = fileDesc;
        this.domainId = domainId;
        this.fileIndex = fileIndex;
        this.isPublic = isPublic;
        this.meta = meta;
        this.name = name;
        this.owner = owner;
        this.scope = scope;
        this.fileSize = fileSize;
        this.status = status;
        this.storage = storage;
        this.updateAt = updateAt;
        this.userId = userId;
    }

    @Column(name = "app_id", precision = 10, scale = 0)
    public Long getAppId()
    {
        return this.appId;
    }

    public void setAppId(Long appId)
    {
        this.appId = appId;
    }

    @Column(name = "attr", length = 4000)
    public String getAttr()
    {
        return this.attr;
    }

    public void setAttr(String attr)
    {
        this.attr = attr;
    }

    @Column(name = "biz_id", precision = 10, scale = 0)
    public Long getBizId()
    {
        return this.bizId;
    }

    public void setBizId(Long bizId)
    {
        this.bizId = bizId;
    }

    @Column(name = "catalog", length = 64)
    public String getCatalog()
    {
        return this.catalog;
    }

    public void setCatalog(String catalog)
    {
        this.catalog = catalog;
    }

    @Column(name = "file_desc", length = 1024)
    public String getFileDesc()
    {
        return this.fileDesc;
    }

    public void setFileDesc(String fileDesc)
    {
        this.fileDesc = fileDesc;
    }

    @Column(name = "domain_id", precision = 10, scale = 0)
    public Long getDomainId()
    {
        return this.domainId;
    }

    public void setDomainId(Long domainId)
    {
        this.domainId = domainId;
    }

    @Column(name = "file_index", precision = 10, scale = 0)
    public Long getFileIndex()
    {
        return this.fileIndex;
    }

    public void setFileIndex(Long fileIndex)
    {
        this.fileIndex = fileIndex;
    }

    @Column(name = "is_public", precision = 1, scale = 0)
    public Boolean getIsPublic()
    {
        return this.isPublic;
    }

    public void setIsPublic(Boolean isPublic)
    {
        this.isPublic = isPublic;
    }

    @Column(name = "meta", length = 4000)
    public String getMeta()
    {
        return this.meta;
    }

    public void setMeta(String meta)
    {
        this.meta = meta;
    }

    @Column(name = "name", length = 128)
    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Column(name = "owner", length = 64)
    public String getOwner()
    {
        return this.owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    @Column(name = "scope", precision = 10, scale = 0)
    public Long getScope()
    {
        return this.scope;
    }

    public void setScope(Long scope)
    {
        this.scope = scope;
    }

    @Column(name = "file_size", precision = 10, scale = 0)
    public Long getFileSize()
    {
        return this.fileSize;
    }

    public void setFileSize(Long fileSize)
    {
        this.fileSize = fileSize;
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

    @Column(name = "storage", length = 128)
    public String getStorage()
    {
        return this.storage;
    }

    public void setStorage(String storage)
    {
        this.storage = storage;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at", nullable = false, length = 19)
    public Date getUpdateAt()
    {
        return this.updateAt;
    }

    public void setUpdateAt(Date updateAt)
    {
        this.updateAt = updateAt;
    }

    @Column(name = "user_id", scale = 0)
    public BigDecimal getUserId()
    {
        return this.userId;
    }

    public void setUserId(BigDecimal userId)
    {
        this.userId = userId;
    }

}
