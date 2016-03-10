package com.sammyun.hibernate.listener;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.sammyun.entity.attendance.TimeCard;

public class TimeCardListener
{
    @PrePersist
    public void postAdd(TimeCard timeCard)
    {
        getJsonFamilyMap(timeCard);
    }

    @PreUpdate
    public void postUpdate(TimeCard timeCard)
    {
        getJsonFamilyMap(timeCard);
    }

    @PreRemove
    public void postRemove(TimeCard timeCard)
    {
        getJsonFamilyMap(timeCard);
    }
    
    public void getJsonFamilyMap(TimeCard timeCard)
    {
        List<Long> memberIds  = new  LinkedList<Long>();
         memberIds.add(timeCard.getMember().getId());
    }
}
