package com.sammyun.dao.impl.message;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.message.MessageAttachmentDao;
import com.sammyun.entity.message.MessageAttachment;

/**
 * MessageAttachment * DaoImpl - 消息附件
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("messageAttachmentDaoImpl")
public class MessageAttachmentDaoImpl extends BaseDaoImpl<MessageAttachment, Long> implements MessageAttachmentDao  {

}
