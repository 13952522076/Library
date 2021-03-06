package com.sammyun.dao.impl.message;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.message.MessageInboxDao;
import com.sammyun.entity.message.MessageInbox;

/**
 * MessageInbox * DaoImpl - 站内信(系统消息)
 * 


 */
@Repository("messageInboxDaoImpl")
public class MessageInboxDaoImpl extends BaseDaoImpl<MessageInbox, Long> implements MessageInboxDao 
{

}
