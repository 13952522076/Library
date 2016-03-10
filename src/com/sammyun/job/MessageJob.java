package com.sammyun.job;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sammyun.service.message.MessageService;

@Component("messageJob")
@Lazy(false)
public class MessageJob
{
    @Resource(name = "messageServiceImpl")
    private MessageService messageService;

    @Scheduled(cron = "${job.message.cron}")
    public void sendMessage()
    {
        messageService.sendMessage();
    }
    
}
