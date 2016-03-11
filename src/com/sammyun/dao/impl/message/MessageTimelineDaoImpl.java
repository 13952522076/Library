package com.sammyun.dao.impl.message;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.message.MessageTimelineDao;
import com.sammyun.entity.message.MessageTimeline;

/**
 * MessageTimeline * DaoImpl - 消息时间线
 * 


 */
@Repository("messageTimelineDaoImpl")
public class MessageTimelineDaoImpl extends BaseDaoImpl<MessageTimeline, Long> implements MessageTimelineDao  {

}
