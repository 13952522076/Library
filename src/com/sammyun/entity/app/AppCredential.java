package com.sammyun.entity.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;

/**
 * Entity - APP API 应用证书管理
 * 
 * @author xutianlong
 * @version [版本号, Aug 5, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_app_credential")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_app_credential_sequence")
public class AppCredential extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    /** App Key */
    private String appKey;

    /** App Secret */
    private String appSecret;

    /** true 启用，false 禁用 */
    private Boolean status;

    /** 隶属的app */
    private App app;

    /**
     * @return 返回 appKey
     */
    @NotEmpty
    @JsonProperty
    @Length(max = 20)
    @Column(nullable = false)
    public String getAppKey()
    {
        return appKey;
    }

    /**
     * @param 对appKey进行赋值
     */
    public void setAppKey(String appKey)
    {
        this.appKey = appKey;
    }

    /**
     * @return 返回 appSecret
     */
    @NotEmpty
    @JsonProperty
    @Length(max = 50)
    @Column(nullable = false)
    public String getAppSecret()
    {
        return appSecret;
    }

    /**
     * @param 对appSecret进行赋值
     */
    public void setAppSecret(String appSecret)
    {
        this.appSecret = appSecret;
    }

    /**
     * @return 返回 status
     */
    public Boolean getStatus()
    {
        return status;
    }

    /**
     * @param 对status进行赋值
     */
    public void setStatus(Boolean status)
    {
        this.status = status;
    }

    /**
     * @return 返回 app
     */
    @OneToOne(fetch = FetchType.LAZY)
    public App getApp()
    {
        return app;
    }

    /**
     * @param 对app进行赋值
     */
    public void setApp(App app)
    {
        this.app = app;
    }

}
