package com.sammyun.service.impl.message;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.Setting;
import com.sammyun.dao.message.MessageDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.MemberDeviceInfo;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.message.Message;
import com.sammyun.entity.message.Message.MessageCategory;
import com.sammyun.plugin.MessagePushPlugin;
import com.sammyun.service.MemberDeviceInfoService;
import com.sammyun.service.PluginService;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.message.MessageService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.SettingUtils;
import com.sammyun.util.SmsCellUtil;
import com.sammyun.util.SpringUtils;
import com.tencent.xinge.XingeApp;

/**
 * Message * ServiceImpl - 消息附件
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("messageServiceImpl")
public class MessageServiceImpl extends BaseServiceImpl<Message, Long> implements MessageService
{
    // @Resource
    // private List<MessagePushPlugin> messagePushPlugins;

    @Resource(name = "pluginServiceImpl")
    private PluginService pluginService;

    @Resource(name = "messageServiceImpl")
    private MessageService messageService;

    @Resource(name = "memberDeviceInfoServiceImpl")
    private MemberDeviceInfoService memberDeviceInfoService;

    @Resource(name = "messageDaoImpl")
    private MessageDao messageDao;

    @Resource(name = "messageDaoImpl")
    public void setBaseDao(MessageDao messageDao)
    {
        super.setBaseDao(messageDao);
    }

    @Override
    public Page<Message> findMessage(DictSchool dictSchool, MessageCategory messageCategory, Member sender,
            Boolean senderDelete, Member receiver, Boolean receiverDelete, Pageable pageable)
    {
        return messageDao.findMessage(dictSchool, messageCategory, sender, senderDelete, receiver, receiverDelete,
                pageable);
    }
    
    @Override
    public Page<Message> findMessage(DictSchool dictSchool, MessageCategory messageCategory, Member sender,
            Boolean senderDelete, Member receiver, Boolean receiverDelete, Pageable pageable, Date updateDate)
    {
        return messageDao.findMessage(dictSchool, messageCategory, sender, senderDelete, receiver, receiverDelete,
                pageable, updateDate);
    }

    @Transactional(readOnly = true)
    public Page<Message> findPage(Member member, Pageable pageable)
    {
        return messageDao.findPage(member, pageable);
    }

    @Transactional(readOnly = true)
    public Page<Message> findDraftPage(Member sender, Pageable pageable)
    {
        return messageDao.findDraftPage(sender, pageable);
    }

    @Transactional(readOnly = true)
    public Long count(Member member, Boolean read)
    {
        return messageDao.count(member, read);
    }

    public void delete(Long id, Member member)
    {
        messageDao.remove(id, member);
    }

    @Override
    public List<Message> findMessage(Boolean hasPush, Integer count)
    {
        return messageDao.findMessage(hasPush, count);
    }

    @Override
    public void sendMessage()
    {
        MessagePushPlugin xgForIosPlugin = null;
        MessagePushPlugin xgForAndroidPlugin = null;
        List<MessagePushPlugin> messagePushPlugins = pluginService.getMessagePushPlugins(true);
        for (MessagePushPlugin messagePushPlugin : messagePushPlugins)
        {
            if (messagePushPlugin.getId().equalsIgnoreCase("xgForIosPlugin"))
            {
                xgForIosPlugin = messagePushPlugin;

            }
            else if (messagePushPlugin.getId().equalsIgnoreCase("xgForAndroidPlugin"))
            {
                xgForAndroidPlugin = messagePushPlugin;
            }
        }
        List<Message> messages = messageDao.findMessage(false, 10);
        if (messages != null && messages.size() != 0)
        {
            for (Message message : messages)
            {
                Member reciver = message.getReceiver();
                String subject = message.getSubject();
                String body = message.getBody();
                // 根据是否为智能机，做出不同的判断
                if (reciver.getIsDevice())
                {
                    List<MemberDeviceInfo> memberDeviceInfos = memberDeviceInfoService.findDevice(reciver);
                    if (memberDeviceInfos != null && memberDeviceInfos.size() != 0)
                    {
                        for (MemberDeviceInfo memberDeviceInfo : memberDeviceInfos)
                        {
                            String deviceToken = memberDeviceInfo.getDeviceToken();
                            String deviceOs = memberDeviceInfo.getDeviceOs();
                            if (deviceOs != null)
                            {
                                if (deviceOs.equalsIgnoreCase("iPhone OS"))
                                {
                                    if (xgForIosPlugin != null)
                                    {
                                        JSONObject a = xgForIosPlugin.pushSingleDeviceIOS(body, deviceToken);
//                                        JSONObject a = xgForIosPlugin.pushTokenIos(body, deviceToken, XingeApp.IOSENV_DEV);

                                        message.setXgPush(true);
                                    }
                                    else
                                    {
                                        sendMessageByMobile(reciver.getMobile(), body);
                                        message.setSmsPush(true);
                                    }
                                }
                                else if (deviceOs.equalsIgnoreCase("android"))
                                {
                                    if (xgForAndroidPlugin != null)
                                    {
                                        xgForAndroidPlugin.pushTokenAndroid(subject, body, deviceToken);
                                        message.setXgPush(true);

                                    }
                                    else
                                    {
                                        sendMessageByMobile(reciver.getMobile(), body);
                                        message.setSmsPush(true);
                                    }
                                }
                            }

                        }
                    }
                    else
                    {
                        sendMessageByMobile(reciver.getMobile(), body);
                        message.setSmsPush(true);
                    }
                }
                else
                {
                    sendMessageByMobile(reciver.getMobile(), body);
                    message.setSmsPush(true);
                }
                message.setHasPush(true);
            }
            messageService.batchUpdate(messages);

        }
    }

    /**
     * 发送短信
     */
    public void sendMessageByMobile(String destnumbers, String msg)
    {
        Setting setting = SettingUtils.get();
        try
        {
            if(setting.getIsMsgNotified()){
                String sendTime = DateUtil.date2String(new Date(), 1);
                HashMap returnData = SmsCellUtil.getInstance().sendDone(destnumbers, msg, sendTime);
                if ("0".equals(returnData.get("return")))
                {
                }
                else
                {
                }
            }
           
        }
        catch (Exception e)
        {
        }
    }

    @Override
    public List<Message> findMessage(String remark, Member sender, MessageCategory messageCategory)
    {
        return messageDao.findMessage(remark, sender, messageCategory);
    }
}
