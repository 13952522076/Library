package com.sammyun.service.impl.message;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.message.MessageInboxService;

import com.sammyun.dao.message.MessageInboxDao;
import com.sammyun.entity.message.MessageInbox;

/**
 * MessageInbox * ServiceImpl - 站内信(系统消息)
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("messageInboxServiceImpl")
public class MessageInboxServiceImpl extends BaseServiceImpl<MessageInbox, Long> implements MessageInboxService{

    @Resource(name = "messageInboxDaoImpl")
    private MessageInboxDao messageInboxDao;

    @Resource(name = "messageInboxDaoImpl")
    public void setBaseDao(MessageInboxDao messageInboxDao){
        super.setBaseDao(messageInboxDao);
    }


}
