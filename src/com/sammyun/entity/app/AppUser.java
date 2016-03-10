package com.sammyun.entity.app;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.Member;

/***
 * Entity - 用户App清单
 * 
 * @author xutianlong
 * @version [版本号, Aug 7, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_app_user")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_app_user_sequence")
public class AppUser extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -8497638672578153483L;

    /** 安装的应用 */
    private App app;

    /** 安装会员 */
    private Member member;

    /** 安装时间 */
    private Date installTime;

    /** 升级时间 */
    private Date updateTime;

    /** 安装的设备信息 以JSON格式保存 */
    private String deviceInfo;

    /** 是否删除 */
    private Boolean isDelete;

    /**
     * @return 返回 app
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
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

    /**
     * @return 返回 member
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Member getMember()
    {
        return member;
    }

    /**
     * @param 对member进行赋值
     */
    public void setMember(Member member)
    {
        this.member = member;
    }

    /**
     * @return 返回 installTime
     */
    public Date getInstallTime()
    {
        return installTime;
    }

    /**
     * @param 对installTime进行赋值
     */
    public void setInstallTime(Date installTime)
    {
        this.installTime = installTime;
    }

    /**
     * @return 返回 updateTime
     */
    public Date getUpdateTime()
    {
        return updateTime;
    }

    /**
     * @param 对updateTime进行赋值
     */
    public void setUpdateTime(Date updateTime)
    {
        this.updateTime = updateTime;
    }

    /**
     * @return 返回 deviceInfo
     */
    public String getDeviceInfo()
    {
        return deviceInfo;
    }

    /**
     * @param 对deviceInfo进行赋值
     */
    public void setDeviceInfo(String deviceInfo)
    {
        this.deviceInfo = deviceInfo;
    }

    /**
     * @return 返回 isDelete
     */
    public Boolean getIsDelete()
    {
        return isDelete;
    }

    /**
     * @param 对isDelete进行赋值
     */
    public void setIsDelete(Boolean isDelete)
    {
        this.isDelete = isDelete;
    }

}
