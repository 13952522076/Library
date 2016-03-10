package com.sammyun.dao.friendship;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.friendship.Friendship;

/**
 * Friendship * Dao - 好友关系
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface FriendshipDao extends BaseDao<Friendship, Long>
{
    /**
     * 查找未建立的好友关系
     * @param hasCreated
     * @param count
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Friendship>findFriendship(Boolean hasCreated,Integer count);
}
