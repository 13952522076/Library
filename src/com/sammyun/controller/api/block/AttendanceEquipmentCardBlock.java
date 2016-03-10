package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.attendance.TimeCard.CardStatus;

@XmlRootElement(name = "attendanceEquipmentCardBlock")
public class AttendanceEquipmentCardBlock
{
    /** 卡号管理 */
    private String cardNumber;

    /** 卡的状态 */
    private CardStatus cardStatus;

    @XmlElement
    public String getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    @XmlElement
    public CardStatus getCardStatus()
    {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus)
    {
        this.cardStatus = cardStatus;
    }
    
    
}
