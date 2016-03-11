package com.sammyun.service.impl.message;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.message.MessageAttachmentService;

import com.sammyun.dao.message.MessageAttachmentDao;
import com.sammyun.entity.message.MessageAttachment;

/**
 * MessageAttachment * ServiceImpl - 消息附件
 * 


 */
@Service("messageAttachmentServiceImpl")
public class MessageAttachmentServiceImpl extends BaseServiceImpl<MessageAttachment, Long> implements MessageAttachmentService {

    @Resource(name = "messageAttachmentDaoImpl")
    private MessageAttachmentDao messageAttachmentDao;

    @Resource(name = "messageAttachmentDaoImpl")
    public void setBaseDao(MessageAttachmentDao messageAttachmentDao){
        super.setBaseDao(messageAttachmentDao);
    }


}
