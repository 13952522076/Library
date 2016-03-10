package com.sammyun.service.impl.message;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.message.MessageHistoryService;

import com.sammyun.dao.message.MessageHistoryDao;
import com.sammyun.entity.message.MessageHistory;

/**
 * MessageHistory * ServiceImpl - 消息历史
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("messageHistoryServiceImpl")
public class MessageHistoryServiceImpl extends BaseServiceImpl<MessageHistory, Long> implements MessageHistoryService {

    @Resource(name = "messageHistoryDaoImpl")
    private MessageHistoryDao messageHistoryDao;

    @Resource(name = "messageHistoryDaoImpl")
    public void setBaseDao(MessageHistoryDao messageHistoryDao){
        super.setBaseDao(messageHistoryDao);
    }


}
