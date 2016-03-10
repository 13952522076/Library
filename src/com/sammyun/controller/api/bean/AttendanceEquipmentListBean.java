package com.sammyun.controller.api.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "attendanceEquipmentListBean")
public class AttendanceEquipmentListBean
{
    /** 设备号 */
    private String equipmentSequence;

    /** 更新时间 */
    private String modifyDate;

    @XmlElement
    public String getEquipmentSequence()
    {
        return equipmentSequence;
    }

    public void setEquipmentSequence(String equipmentSequence)
    {
        this.equipmentSequence = equipmentSequence;
    }

    @XmlElement
    public String getModifyDate()
    {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate)
    {
        this.modifyDate = modifyDate;
    }
    
    
}
