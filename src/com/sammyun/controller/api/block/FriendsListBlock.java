package com.sammyun.controller.api.block;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "friendsInfoBlock")
public class FriendsListBlock
{
    /**名称*/
    private String name;
    
    /**好友列表*/
    private List<FriendsInfoBlock> friends;

    @XmlElement
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @XmlElement
    public List<FriendsInfoBlock> getFriends()
    {
        return friends;
    }

    public void setFriends(List<FriendsInfoBlock> friends)
    {
        this.friends = friends;
    }
    
    
    
}
