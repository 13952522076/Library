package com.sammyun.entity.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.sammyun.entity.BaseEntity;

/**
 * Entity - 应用截图
 * 
 * @author xutianlong
 * @version [版本号, Aug 4, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_app_screenshot")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_app_screenshot_sequence")
public class AppScreenshot extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -8926744954555179041L;

    /** 截图文件名 */
    private String screenshot;

    /** 截图地址 */
    private String screenshotUrl;
    
    /** 隶属的应用管理 */
    private App app;

    /**
     * @return 返回 screenshot
     */
    @NotEmpty
    @Length(max = 200)
    @Column(nullable = false)
    public String getScreenshot()
    {
        return screenshot;
    }

    /**
     * @param 对screenshot进行赋值
     */
    public void setScreenshot(String screenshot)
    {
        this.screenshot = screenshot;
    }

    /**
     * @return 返回 screenshotUrl
     */
    @NotEmpty
    @Length(max = 400)
    @Column(nullable = false)
    public String getScreenshotUrl()
    {
        return screenshotUrl;
    }

    /**
     * @param 对screenshotUrl进行赋值
     */
    public void setScreenshotUrl(String screenshotUrl)
    {
        this.screenshotUrl = screenshotUrl;
    }

    /**
     * @return 返回 app
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false)
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
