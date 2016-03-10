package com.sammyun.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name = "t_pe_device_startup_cover")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_device_startup_cover_sequence")
public class DeviceStartupCover extends OrderEntity
{

    private static final long serialVersionUID = 8482564046751453927L;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片路径
     */
    private String pictureUrl;

    /**
     * 图片link
     */
    private String link;

    /**
     * 描述
     */
    private String description;

    /**
     * 设备分辨率
     */
    private DeviceResolution deviceResolution;

    /**
     * 获取名称
     * 
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * 设置名称
     * 
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 获取图片路径
     * 
     * @return
     */
    @NotEmpty
    public String getPictureUrl()
    {
        return pictureUrl;
    }

    /**
     * 设置图片路径
     * 
     * @param picture_url
     */
    public void setPictureUrl(String pictureUrl)
    {
        this.pictureUrl = pictureUrl;
    }

    /**
     * 获取图片link
     * 
     * @return
     */
    public String getLink()
    {
        return link;
    }

    /**
     * 设置图片link
     * 
     * @param link
     */
    public void setLink(String link)
    {
        this.link = link;
    }

    /**
     * 获取描述
     * 
     * @return
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * 设置描述
     * 
     * @param desc
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * 获取设备分辨率
     * 
     * @return
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    public DeviceResolution getDeviceResolution()
    {
        return deviceResolution;
    }

    /**
     * 设置设备分辨率
     * 
     * @param deviceResolution
     */
    public void setDeviceResolution(DeviceResolution deviceResolution)
    {
        this.deviceResolution = deviceResolution;
    }

}
