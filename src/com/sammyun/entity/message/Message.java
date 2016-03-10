/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.entity.message;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.Member;
import com.sammyun.entity.dict.DictSchool;

/**
 * Entity - 消息
 * 
 * @author Sencloud Team

 */
@Entity
@Table(name = "t_pe_message")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_message_sequence")
public class Message extends BaseEntity
{

    private static final long serialVersionUID = -5035343536762850722L;

    /**
     * 消息类型
     * 
     * @author xutianlong
     * @version [版本号, May 4, 2015]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    // public enum MessageCategory
    // {
    // /** 通知 */
    // NOTICE,
    // /** 新闻 */
    // NEWS,
    // /** 公告通知 */
    // ANNOUNCEMENT,
    // /** 请假通知 */
    // ASKLEAVE
    // }
    public enum MessageCategory
    {
        /** 通知 */
        NOTICE
        {
            @Override
            public boolean isAdjust()
            {
                return false;
            }
        },
        /** 新闻 */
        NEWS
        {
            @Override
            public boolean isAdjust()
            {
                return true;
            }
        },
        /** 公告通知 */
        ANNOUNCEMENT
        {
            @Override
            public boolean isAdjust()
            {
                return true;
            }
        },
        /** 请假通知 */
        ASKLEAVE
        {
            @Override
            public boolean isAdjust()
            {
                return false;
            }
        };

        /** 用于标识是否可以跳转 */

        public boolean isAdjust()
        {
            return false;
        }
    }

    /** 消息类型 */
    private MessageCategory messageCategory;

    /** 上级消息 */
    private Message parent;

    /** 消息主题 */
    private String subject;

    /** 内容 */
    private String body;

    /** 是否为草稿 默认为否 */
    private Boolean isDraft;

    /** 收件人已读 */
    private Boolean receiverRead;

    /** 发件人删除 */
    private Boolean senderDelete;

    /** 收件人删除 */
    private Boolean receiverDelete;

    /** 发件人 */
    private Member sender;

    /** 收件人 */
    private Member receiver;

    /** 消息过期时间 */
    private Date expireDate;

    /** 访问或者跳转地址 */
    private String url;

    /** ip */
    private String ip;

    /** 是否已推送 */
    private Boolean hasPush;

    /** 信鸽推送 */
    private Boolean xgPush;

    /** 短信推送 */
    private Boolean smsPush;

    /** 备注 */
    private String remark;

    /** 隶属学校 */
    private DictSchool dictSchool;

    /** 下级消息列表 */
    private Set<Message> children = new HashSet<Message>();

    /**
     * @return 返回 subject
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * @param 对subject进行赋值
     */
    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    /**
     * @return 返回 body
     */
    public String getBody()
    {
        return body;
    }

    /**
     * @param 对body进行赋值
     */
    public void setBody(String body)
    {
        this.body = body;
    }

    /**
     * @return 返回 messageCategory
     */
    public MessageCategory getMessageCategory()
    {
        return messageCategory;
    }

    /**
     * @param 对messageCategory进行赋值
     */
    public void setMessageCategory(MessageCategory messageCategory)
    {
        this.messageCategory = messageCategory;
    }

    /**
     * @return 返回 isDraft
     */
    public Boolean getIsDraft()
    {
        return isDraft;
    }

    /**
     * @param 对isDraft进行赋值
     */
    public void setIsDraft(Boolean isDraft)
    {
        this.isDraft = isDraft;
    }

    /**
     * @return 返回 receiverRead
     */
    public Boolean getReceiverRead()
    {
        return receiverRead;
    }

    /**
     * @param 对receiverRead进行赋值
     */
    public void setReceiverRead(Boolean receiverRead)
    {
        this.receiverRead = receiverRead;
    }

    /**
     * @return 返回 senderDelete
     */

    public Boolean getSenderDelete()
    {
        return senderDelete;
    }

    /**
     * @param 对senderDelete进行赋值
     */
    public void setSenderDelete(Boolean senderDelete)
    {
        this.senderDelete = senderDelete;
    }

    /**
     * @return 返回 receiverDelete
     */
    public Boolean getReceiverDelete()
    {
        return receiverDelete;
    }

    /**
     * @param 对receiverDelete进行赋值
     */
    public void setReceiverDelete(Boolean receiverDelete)
    {
        this.receiverDelete = receiverDelete;
    }

    /**
     * @return 返回 sender
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Member getSender()
    {
        return sender;
    }

    /**
     * @param 对sender进行赋值
     */
    public void setSender(Member sender)
    {
        this.sender = sender;
    }

    /**
     * @return 返回 receiver
     */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Member getReceiver()
    {
        return receiver;
    }

    /**
     * @param 对receiver进行赋值
     */
    public void setReceiver(Member receiver)
    {
        this.receiver = receiver;
    }

    /**
     * @return 返回 expireDate
     */
    public Date getExpireDate()
    {
        return expireDate;
    }

    /**
     * @param 对expireDate进行赋值
     */
    public void setExpireDate(Date expireDate)
    {
        this.expireDate = expireDate;
    }

    /**
     * @return 返回 url
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param 对url进行赋值
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * @return 返回 ip
     */
    public String getIp()
    {
        return ip;
    }

    /**
     * @param 对ip进行赋值
     */
    public void setIp(String ip)
    {
        this.ip = ip;
    }

    /**
     * @return hasPush
     */
    public Boolean getHasPush()
    {
        return hasPush;
    }

    /**
     * @param hasPush
     */
    public void setHasPush(Boolean hasPush)
    {
        this.hasPush = hasPush;
    }

    /**
     * @return xgPush
     */
    public Boolean getXgPush()
    {
        return xgPush;
    }

    /**
     * @param xgPush
     */
    public void setXgPush(Boolean xgPush)
    {
        this.xgPush = xgPush;
    }

    /**
     * @return smsPush
     */
    public Boolean getSmsPush()
    {
        return smsPush;
    }

    /**
     * @param smsPush
     */
    public void setSmsPush(Boolean smsPush)
    {
        this.smsPush = smsPush;
    }

    /**
     * @return remark
     */
    public String getRemark()
    {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    /**
     * @return 返回 dictSchool
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictSchool getDictSchool()
    {
        return dictSchool;
    }

    /**
     * @param 对dictSchool进行赋值
     */
    public void setDictSchool(DictSchool dictSchool)
    {
        this.dictSchool = dictSchool;
    }

    /**
     * 获取上级消息
     * 
     * @return 上级消息
     */
    @ManyToOne(fetch = FetchType.LAZY)
    public Message getParent()
    {
        return parent;
    }

    /**
     * 设置上级消息
     * 
     * @param parent 上级消息
     */
    public void setParent(Message parent)
    {
        this.parent = parent;
    }

    /**
     * 获取下级消息
     * 
     * @return 下级消息
     */
    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<Message> getChildren()
    {
        return children;
    }

    /**
     * 设置下级消息
     * 
     * @param children 下级消息
     */
    public void setChildren(Set<Message> children)
    {
        this.children = children;
    }

}
