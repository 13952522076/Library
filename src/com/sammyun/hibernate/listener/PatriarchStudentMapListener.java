package com.sammyun.hibernate.listener;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.sammyun.entity.dict.PatriarchStudentMap;
import com.sammyun.util.ImUserUtil;

public class PatriarchStudentMapListener
{
    
    @PrePersist
    public void postAdd(PatriarchStudentMap patriarchStudentMap)
    {
        getJsonFamilyMap(patriarchStudentMap);
    }

    @PreUpdate
    public void postUpdate(PatriarchStudentMap patriarchStudentMap)
    {
        getJsonFamilyMap(patriarchStudentMap);
    }

    @PreRemove
    public void postRemove(PatriarchStudentMap patriarchStudentMap)
    {
        getJsonFamilyMap(patriarchStudentMap);
    }
    
    public void getJsonFamilyMap(PatriarchStudentMap patriarchStudentMap)
    {
        ImUserUtil imUserUtil = new ImUserUtil();
        List<Long> memberIds  = new  LinkedList<Long>();
         memberIds.add(patriarchStudentMap.getMember().getId());
        imUserUtil.getJsonFamilyMap(memberIds,"patriarchStudentMap");
    }
}
