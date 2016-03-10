package com.sammyun.hibernate.listener;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.PostPersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import com.sammyun.entity.Member;

public class MemberListener
{
    @PostPersist
    public void postAdd(Member member)
    {
        getJsonFamilyMap(member);
    }

    @PreUpdate
    public void postUpdate(Member member)
    {
        getJsonFamilyMap(member);
    }

    @PreRemove
    public void postRemove(Member member)
    {
        getJsonFamilyMap(member);
    }
    
    public void getJsonFamilyMap(Member member)
    {
        List<Long> memberIds  = new  LinkedList<Long>();
        memberIds.add(member.getId());
    }
}
