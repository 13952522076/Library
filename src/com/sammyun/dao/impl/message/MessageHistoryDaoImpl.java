package com.sammyun.dao.impl.message;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.message.MessageHistoryDao;
import com.sammyun.entity.message.MessageHistory;

/**
 * MessageHistory * DaoImpl - 消息历史
 * 


 */
@Repository("messageHistoryDaoImpl")
public class MessageHistoryDaoImpl extends BaseDaoImpl<MessageHistory, Long> implements MessageHistoryDao  {

}
