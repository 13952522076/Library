package com.sammyun.service.friendship;

import com.sammyun.entity.friendship.Friendship;
import com.sammyun.service.BaseService;

/**
 * Message * Service - 好友关系
 * 


 */
public interface FriendshipService extends BaseService<Friendship, Long>
{
    /**
     * 建议好友关系
     * <功能详细描述>
     * @see [类、类#方法、类#成员]
     */
    public void createFriendship();
}
