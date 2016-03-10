package com.sammyun.job;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sammyun.service.friendship.FriendshipService;

@Component("friendshipJob")
@Lazy(false)
public class FriendshipJob
{
    @Resource(name = "friendshipServiceImpl")
    private FriendshipService friendshipService;

//    @Scheduled(cron = "${job.friendship.cron}")
    public void createFriendship()
    {
        friendshipService.createFriendship();
    }
}
