package com.sammyun.service.impl.message;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.message.MessageTimelineService;

import com.sammyun.dao.message.MessageTimelineDao;
import com.sammyun.entity.message.MessageTimeline;

/**
 * MessageTimeline * ServiceImpl - 消息时间线
 * 
 * @author Sencloud Team

 */
@Service("messageTimelineServiceImpl")
public class MessageTimelineServiceImpl extends BaseServiceImpl<MessageTimeline, Long> implements MessageTimelineService {

    @Resource(name = "messageTimelineDaoImpl")
    private MessageTimelineDao messageTimelineDao;

    @Resource(name = "messageTimelineDaoImpl")
    public void setBaseDao(MessageTimelineDao messageTimelineDao){
        super.setBaseDao(messageTimelineDao);
    }


}
