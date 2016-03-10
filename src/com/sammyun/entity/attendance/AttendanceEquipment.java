package com.sammyun.entity.attendance;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictSchool;

/**
 * Entity - 考勤机
 * 
 * @author xutianlong
 * @version [版本号, Apr 11, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_attendance_equipment")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_attendance_equipment_sequence")
public class AttendanceEquipment extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    /** 考勤机序列号、设备ID */
    private String equipmentSequence;

    /** 考勤机器名字 */
    private String equipmentName;

    /** 考勤机状态 */
    private String status;

    /** IP 地址 */
    private String ipAddress;

    /** 端口号 */
    private String port;

    /** 考勤机隶属学校 */
    private DictSchool dictSchool;

    /** 最后通信时间 */
    private Date communicationTime;

    /** 固件版本 */
    private String firmwareVersion;

    /** 备注 */
    private String remark;

    /**
     * @return 返回 equipmentSequence
     */
    @Column(length = 50)
    public String getEquipmentSequence()
    {
        return equipmentSequence;
    }

    /**
     * @param 对equipmentSequence进行赋值
     */
    public void setEquipmentSequence(String equipmentSequence)
    {
        this.equipmentSequence = equipmentSequence;
    }

    /**
     * @return 返回 equipmentName
     */
    @Column(length = 50)
    public String getEquipmentName()
    {
        return equipmentName;
    }

    /**
     * @param 对equipmentName进行赋值
     */
    public void setEquipmentName(String equipmentName)
    {
        this.equipmentName = equipmentName;
    }

    /**
     * @return 返回 status
     */
    @Column(length = 10)
    public String getStatus()
    {
        return status;
    }

    /**
     * @param 对status进行赋值
     */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * @return 返回 ipAddress
     */
    @Column(length = 30)
    public String getIpAddress()
    {
        return ipAddress;
    }

    /**
     * @param 对ipAddress进行赋值
     */
    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

    /**
     * @return 返回 port
     */
    public String getPort()
    {
        return port;
    }

    /**
     * @param 对port进行赋值
     */
    @Column(length = 10)
    public void setPort(String port)
    {
        this.port = port;
    }

    /**
     * @return 返回 dictSchool
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictSchool getDictSchool()
    {
        return dictSchool;
    }

    /**
     * @param 对dictSchool进行赋值
     */
    public void setDictSchool(DictSchool dictSchool)
    {
        this.dictSchool = dictSchool;
    }

    /**
     * @return 返回 remark
     */
    @Column(length = 500)
    public String getRemark()
    {
        return remark;
    }

    /**
     * @param 对remark进行赋值
     */
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    /**
     * @return 返回 communicationTime
     */
    public Date getCommunicationTime()
    {
        return communicationTime;
    }

    /**
     * @param 对communicationTime进行赋值
     */
    public void setCommunicationTime(Date communicationTime)
    {
        this.communicationTime = communicationTime;
    }

    /**
     * @return 返回 firmwareVersion
     */
    @Column(length = 50)
    public String getFirmwareVersion()
    {
        return firmwareVersion;
    }

    /**
     * @param 对firmwareVersion进行赋值
     */
    public void setFirmwareVersion(String firmwareVersion)
    {
        this.firmwareVersion = firmwareVersion;
    }

}
