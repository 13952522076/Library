package com.sammyun.hibernate.listener;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.dict.PatriarchStudentMap;
import com.sammyun.util.ImUserUtil;

public class DictStudentListener
{
    @PrePersist
    public void postAdd(DictStudent dictStudent)
    {
        getJsonFamilyMap(dictStudent);
    }

    @PreUpdate
    public void postUpdate(DictStudent dictStudent)
    {
        getJsonFamilyMap(dictStudent);
    }

    @PreRemove
    public void postRemove(DictStudent dictStudent)
    {
        getJsonFamilyMap(dictStudent);
    }
    
    public void getJsonFamilyMap(DictStudent dictStudent)
    {
        ImUserUtil imUserUtil = new ImUserUtil();
        List<Long> memberIds  = new  LinkedList<Long>();
        Set<Long> tempMemberIds  = new  LinkedHashSet<Long>();
        for (PatriarchStudentMap patriarchStudentMap : dictStudent.getPatriarchStudentMap())
        {
            tempMemberIds.add(patriarchStudentMap.getMember().getId());
        }
        memberIds.addAll(tempMemberIds);
        imUserUtil.getJsonFamilyMap(memberIds,"dictStudent");
    }
}
