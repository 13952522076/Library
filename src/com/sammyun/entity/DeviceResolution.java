package com.sammyun.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "t_pe_device_resolution")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_device_resolution_sequence")
public class DeviceResolution extends BaseEntity
{

    /**
	 * 
	 */
    private static final long serialVersionUID = -8475572334966352064L;

    /**
     * 分辨率值（格式：xx_xx[320_640]）
     */
    private String resolution;

    /**
     * 描述
     */
    private String description;

    /**
     * mobile首页图片启动
     */
    private Set<DeviceStartupCover> deviceStartupCover = new HashSet<DeviceStartupCover>();

    /**
     * 获取设备分辨率
     * 
     * @return
     */
    @NotEmpty
    @Column(unique = true)
    public String getResolution()
    {
        return resolution;
    }

    /**
     * 设置分辨率
     * 
     * @param resolution
     */
    public void setResolution(String resolution)
    {
        this.resolution = resolution;
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
     * 获取mobile首页启动图片
     * 
     * @return
     */
    @OneToMany(mappedBy = "deviceResolution", fetch = FetchType.LAZY)
    public Set<DeviceStartupCover> getDeviceStartupCover()
    {
        return deviceStartupCover;
    }

    /**
     * 设置mobile首页启动图片
     * 
     * @param deviceStartupCover
     */
    public void setDeviceStartupCover(Set<DeviceStartupCover> deviceStartupCover)
    {
        this.deviceStartupCover = deviceStartupCover;
    }

    /**
     * 删除前处理
     */
    @PreRemove
    public void preRemove()
    {
        Set<DeviceStartupCover> deviceStartupCovers = getDeviceStartupCover();
        if (deviceStartupCovers != null)
        {
            for (DeviceStartupCover deviceStartupCover : deviceStartupCovers)
            {
                deviceStartupCover.setDeviceResolution(null);
            }
        }
    }

}
