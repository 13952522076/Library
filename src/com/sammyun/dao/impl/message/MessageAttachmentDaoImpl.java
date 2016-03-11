package com.sammyun.dao.impl.message;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.message.MessageAttachmentDao;
import com.sammyun.entity.message.MessageAttachment;

/**
 * MessageAttachment * DaoImpl - 消息附件
 * 


 */
@Repository("messageAttachmentDaoImpl")
public class MessageAttachmentDaoImpl extends BaseDaoImpl<MessageAttachment, Long> implements MessageAttachmentDao  {

}
