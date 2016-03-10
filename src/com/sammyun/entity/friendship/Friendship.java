package com.sammyun.entity.friendship;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * Entity - 好友关系
 * 
 * @author Sencloud Team

 */
@Entity
@Table(name = "t_pe_friendship")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_friendship_sequence")
public class Friendship extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 4256241505591966487L;
    
    /** 用户名称 */
    private String ownerUserPrimaryKey;
    
    /** 好友名称 */
    private String friendUserPrimaryKey;
    
    /** 是否已推送 */
    private Boolean hasCreated;

    /**
     * @return ownerUserPrimaryKey
     */
    public String getOwnerUserPrimaryKey()
    {
        return ownerUserPrimaryKey;
    }

    /**
     * @param ownerUserPrimaryKey
     */
    public void setOwnerUserPrimaryKey(String ownerUserPrimaryKey)
    {
        this.ownerUserPrimaryKey = ownerUserPrimaryKey;
    }

    /**
     * @return friendUserPrimaryKey
     */
    public String getFriendUserPrimaryKey()
    {
        return friendUserPrimaryKey;
    }

    /**
     * @param friendUserPrimaryKey
     */
    public void setFriendUserPrimaryKey(String friendUserPrimaryKey)
    {
        this.friendUserPrimaryKey = friendUserPrimaryKey;
    }

    /**
     * @return hasCreated
     */
    public Boolean getHasCreated()
    {
        return hasCreated;
    }

    /**
     * @param hasCreated
     */
    public void setHasCreated(Boolean hasCreated)
    {
        this.hasCreated = hasCreated;
    }

}
