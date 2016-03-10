package com.sammyun.entity.app;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sammyun.entity.OrderEntity;
import com.sammyun.util.JsonDateSerializer;

/**
 * Entity - 应用超市海报
 * 
 * @author xutianlong
 * @version [版本号, Aug 10, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_app_poster")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_app_poster_sequence")
public class AppPoster extends OrderEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -5792517468845563609L;

    /** 应用系统 */
    public enum OperatingSystem
    {
        /** android */
        android,
        /** iphone **/
        ios
    }

    /** 跳转对象 */
    public enum GotoMethod
    {
        /** 外部链接 */
        link,
        /** 应用介绍界面 */
        app
    }

    /** 标题(必填) */
    private String posterName;

    /** 海报图片 */
    private String posterImg;

    /** 操作系统 */
    private OperatingSystem operatingSystem;

    /** 跳转对象 */
    private GotoMethod gotoMethod;

    /** 当gotoMethod=like设置该外链接 例如：http://www.google.hk */
    private String externalLink;

    /** 当gotoMethod=app设置app链接 */
    private App app;

    /** 是否上架（默认false 未上架，true 上架) */
    private Boolean isOnline;

    /** 上架时间 */
    private Date onlineDate;

    /**
     * @return 返回 posterName
     */
    @NotEmpty
    @Pattern(regexp = "^[0-9a-z_A-Z\\u4e00-\\u9fa5]+$")
    @Column(nullable = false, length = 50)
    public String getPosterName()
    {
        return posterName;
    }

    /**
     * @param 对posterName进行赋值
     */
    public void setPosterName(String posterName)
    {
        this.posterName = posterName;
    }

    /**
     * @return 返回 posterImg
     */
    @NotEmpty
    @Column(nullable = false, length = 500)
    public String getPosterImg()
    {
        return posterImg;
    }

    /**
     * @param 对posterImg进行赋值
     */
    public void setPosterImg(String posterImg)
    {
        this.posterImg = posterImg;
    }

    /**
     * @return 返回 operatingSystem
     */
    @NotEmpty
    @Column(nullable = false, length = 2)
    public OperatingSystem getOperatingSystem()
    {
        return operatingSystem;
    }

    /**
     * @param 对operatingSystem进行赋值
     */
    public void setOperatingSystem(OperatingSystem operatingSystem)
    {
        this.operatingSystem = operatingSystem;
    }

    /**
     * @return 返回 gotoMethod
     */
    @NotEmpty
    @Column(nullable = false, length = 2)
    public GotoMethod getGotoMethod()
    {
        return gotoMethod;
    }

    /**
     * @param 对gotoMethod进行赋值
     */
    public void setGotoMethod(GotoMethod gotoMethod)
    {
        this.gotoMethod = gotoMethod;
    }

    /**
     * @return 返回 externalLink
     */
    @Column(nullable = true, length = 500)
    public String getExternalLink()
    {
        return externalLink;
    }

    /**
     * @param 对externalLink进行赋值
     */
    public void setExternalLink(String externalLink)
    {
        this.externalLink = externalLink;
    }

    /**
     * @return 返回 app
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
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
     * @return 返回 isOnline
     */
    @NotEmpty
    @Column(nullable = false)
    public Boolean getIsOnline()
    {
        return isOnline;
    }

    /**
     * @param 对isOnline进行赋值
     */
    public void setIsOnline(Boolean isOnline)
    {
        this.isOnline = isOnline;
    }

    /**
     * @return 返回 onlineDate
     */
    @JsonSerialize(using = JsonDateSerializer.class)
    @Field(store = Store.YES, index = Index.UN_TOKENIZED)
    @DateBridge(resolution = Resolution.SECOND)
    public Date getOnlineDate()
    {
        return onlineDate;
    }

    /**
     * @param 对onlineDate进行赋值
     */
    public void setOnlineDate(Date onlineDate)
    {
        this.onlineDate = onlineDate;
    }

}
