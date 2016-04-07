package com.sammyun.hibernate.listener;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.dict.PatriarchStudentMap;

public class DictClassListener
{
    
    @PrePersist
    public void postAdd(DictClass dictClass)
    {
        getJsonFamilyMap(dictClass);
    }

    @PreUpdate
    public void postUpdate(DictClass dictClass)
    {
        getJsonFamilyMap(dictClass);
    }

    @PreRemove
    public void postRemove(DictClass dictClass)
    {
        getJsonFamilyMap(dictClass);
    }
    
    public void getJsonFamilyMap(DictClass dictClass)
    {
        List<Long>memberIds = new LinkedList<Long>();
        Set<Long> tempMemberIds  = new  LinkedHashSet<Long>();
        for (DictStudent dictStudent : dictClass.getDictStudents())
        {
            for (PatriarchStudentMap patriarchStudentMap : dictStudent.getPatriarchStudentMap())
            {
                tempMemberIds.add(patriarchStudentMap.getMember().getId());
            }
        }
//        for (Member member : dictClass.getDictSchool().getMembers())
//        {
//            if (member.getMemberType() != null)
//            {
//                if (member.getMemberType().equals(MemberType.teacher))
//                {
//                    tempMemberIds.add(member.getId());
//                }
//            }
//            
//        }
        memberIds.addAll(tempMemberIds);
    }
}
