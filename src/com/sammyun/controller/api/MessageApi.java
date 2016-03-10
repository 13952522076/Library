package com.sammyun.controller.api;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.sammyun.Order;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.controller.api.bean.MessageAddBean;
import com.sammyun.controller.api.bean.MessageListBean;
import com.sammyun.controller.api.block.MessageListBlock;
import com.sammyun.controller.api.block.MessageMemberInfoBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.dict.PatriarchStudentMap;
import com.sammyun.entity.message.Message.MessageCategory;
import com.sammyun.service.MemberService;
import com.sammyun.service.dict.ClassTeacherMapService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.service.message.MessageService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.ImUserUtil;
import com.sammyun.util.SpringUtils;

/**
 * Api - 消息
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("message")
@Path("/message")
public class MessageApi
{

    @Context
    HttpServletRequest request;

    @Resource(name = "messageServiceImpl")
    private MessageService messageService;

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "classTeacherMapServiceImpl")
    private ClassTeacherMapService classTeacherMapService;

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    /**
     * 根据学校老师查询每个班的家长信息
     * 
     * @param dictSchoolId
     * @param memberId
     * @param classStatus
     * @return
     * @see [类、类#方法、类#成员]
     */

    @GET
    @Path("/v1/memberInfo/{dictClassId}")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel memberInfo(@PathParam("dictClassId") Long dictClassId)
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<MessageMemberInfoBlock> rows = new LinkedList<MessageMemberInfoBlock>();
        Set<Member> memberSets = new HashSet<Member>();
        if (dictClassId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误！");
            return listRestFulModel;
        }

        DictClass dictClass = dictClassService.find(dictClassId);
        if (dictClass == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("未知班级不存在！"));
            return listRestFulModel;
        }
        if (dictClass.getDictStudents() == null || dictClass.getDictStudents().size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("班级信息为空！"));
            return listRestFulModel;
        }
        for (DictStudent dictStudent : dictClass.getDictStudents())
        {
            for (PatriarchStudentMap patriarchStudentMap : dictStudent.getPatriarchStudentMap())
            {
                Member tempMember = patriarchStudentMap.getMember();
                if (tempMember != null)
                {
                    memberSets.add(tempMember);
                }
            }
        }
        for (Member member : memberSets)
        {
            MessageMemberInfoBlock messageMemberInfoBlock = new MessageMemberInfoBlock();
            messageMemberInfoBlock.setId(member.getId());
            messageMemberInfoBlock.setRealName(member.getRealName());
            rows.add(messageMemberInfoBlock);
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("获取班级家长信息成功"));
        return listRestFulModel;

    }

    /**
     * 新增消息 <功能详细描述>
     * 
     * @param messageAddBean
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/add")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileRestFulModel add(MessageAddBean messageAddBean)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        String body = messageAddBean.getBody();
        Long dictSchoolId = messageAddBean.getDictSchoolId();
        MessageCategory messageCategory = messageAddBean.getMessageCategory();
        Long[] receiverIds = messageAddBean.getReceiverIds();
        Long senderId = messageAddBean.getSenderId();
        String subject = messageAddBean.getSubject();

        if (dictSchoolId == null || receiverIds == null || senderId == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }
        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        if (dictSchool == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("未知学校不存在！"));
            return mobileRestFulModel;
        }
        Member sender = memberService.find(senderId);
        if (sender == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.unknownAccount"));
            return mobileRestFulModel;
        }
        List<Member> receivers = memberService.findList(receiverIds);
        if (receivers == null || receivers.size() == 0)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("收信人不可为空！"));
            return mobileRestFulModel;
        }
        List<com.sammyun.entity.message.Message> messages = new LinkedList<com.sammyun.entity.message.Message>();
        ImUserUtil imUserUtil = new ImUserUtil();
        for (Member receiver : receivers)
        {
            messages.add(imUserUtil.saveMessage(sender, receiver, subject, body, MessageCategory.NOTICE, dictSchool,
                    null, request));
        }
        messageService.batchUpdate(messages);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("发送成功！");
        return mobileRestFulModel;
    }

    /**
     * 查看消息列表
     */
    @POST
    @Path("/v1/list")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel list(MessageListBean messageListBean)
    {

        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<MessageListBlock> rows = new LinkedList<MessageListBlock>();
        Long dictSchoolId = messageListBean.getDictSchoolId();
        Long memberId = messageListBean.getMemberId();
        ImUserUtil imUserUtil = new ImUserUtil();
        // MessageCategory messageCategory =
        // messageListBean.getMessageCategory();
        String type = messageListBean.getType();

        if (dictSchoolId == null || memberId == null || type == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误！");
            return listRestFulModel;
        }
        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        if (dictSchool == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("未知学校不存在！"));
            return listRestFulModel;
        }
        Member member = memberService.find(memberId);
        if (member == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.unknownAccount"));
            return listRestFulModel;
        }

        Pageable pageable;
        if (messageListBean.getPage() == null)
        {
            pageable = new Pageable();
        }
        else
        {
            pageable = new Pageable(messageListBean.getPage().getPageNumber(), messageListBean.getPage().getPageSize());

        }
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        pageable.setOrders(orders);
        Page<com.sammyun.entity.message.Message> messages = new Page<com.sammyun.entity.message.Message>();

        if (type.equalsIgnoreCase("send"))
        {
            messages = messageService.findMessage(dictSchool, null, member, false, null, null, pageable);
        }
        else if (type.equalsIgnoreCase("receive"))
        {
            messages = messageService.findMessage(dictSchool, null, null, null, member, false, pageable);
        }
        if (messages == null || messages.getContent().size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关消息！"));
            return listRestFulModel;
        }
        for (com.sammyun.entity.message.Message message : messages.getContent())
        {
            MessageListBlock messageListBlock = new MessageListBlock();
            messageListBlock.setId(message.getId());
            messageListBlock.setBody(message.getBody());
            messageListBlock.setReceiverRead(message.getReceiverRead());
            messageListBlock.setSubject(message.getSubject());
            messageListBlock.setIconPhoto(imUserUtil.getDefaultImageUrl(message.getSender().getIconPhoto()));
            messageListBlock.setAdjust(message.getMessageCategory().isAdjust());
            messageListBlock.setMessageCategory(message.getMessageCategory());
            if (message.getMessageCategory().equals(MessageCategory.ANNOUNCEMENT)
                    || message.getMessageCategory().equals(MessageCategory.NEWS))
            {
                messageListBlock.setTransferId(message.getRemark());
            }
            if (message.getCreateDate() != null)
            {
                messageListBlock.setCreateDate(DateUtil.date2String(message.getCreateDate(), 1));
            }
            messageListBlock.setRealName(message.getSender().getRealName());
            rows.add(messageListBlock);
        }
        PageModel page = new PageModel();
        page.setPageNumber(messages.getPageNumber());
        page.setTotalPages(messages.getTotalPages());
        listRestFulModel.setPage(page);
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("获取消息列表成功！"));
        return listRestFulModel;
    }

    /**
     * 查看消息列表
     */
    @POST
    @Path("/v2/list")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel list_v2(MessageListBean messageListBean, @QueryParam("updateDate") Long updateDate)
    {

        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<MessageListBlock> rows = new LinkedList<MessageListBlock>();
        Long dictSchoolId = messageListBean.getDictSchoolId();
        Long memberId = messageListBean.getMemberId();
        ImUserUtil imUserUtil = new ImUserUtil();
        // MessageCategory messageCategory =
        // messageListBean.getMessageCategory();
        String type = messageListBean.getType();

        if (dictSchoolId == null || memberId == null || type == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误！");
            return listRestFulModel;
        }
        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        if (dictSchool == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("未知学校不存在！"));
            return listRestFulModel;
        }
        Member member = memberService.find(memberId);
        if (member == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.unknownAccount"));
            return listRestFulModel;
        }

        Pageable pageable;
        if (messageListBean.getPage() == null)
        {
            pageable = new Pageable();
        }
        else
        {
            pageable = new Pageable(messageListBean.getPage().getPageNumber(), messageListBean.getPage().getPageSize());

        }
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        pageable.setOrders(orders);
        Page<com.sammyun.entity.message.Message> messages = new Page<com.sammyun.entity.message.Message>();

        if (type.equalsIgnoreCase("send"))
        {
            messages = messageService.findMessage(dictSchool, null, member, false, null, null, pageable, new Date(
                    updateDate));
        }
        else if (type.equalsIgnoreCase("receive"))
        {
            messages = messageService.findMessage(dictSchool, null, null, null, member, false, pageable, new Date(
                    updateDate));
        }
        if (messages == null || messages.getContent().size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关消息！"));
            return listRestFulModel;
        }
        for (com.sammyun.entity.message.Message message : messages.getContent())
        {
            MessageListBlock messageListBlock = new MessageListBlock();
            messageListBlock.setId(message.getId());
            messageListBlock.setBody(message.getBody());
            messageListBlock.setReceiverRead(message.getReceiverRead());
            messageListBlock.setSubject(message.getSubject());
            messageListBlock.setIconPhoto(imUserUtil.getDefaultImageUrl(message.getSender().getIconPhoto()));
            messageListBlock.setAdjust(message.getMessageCategory().isAdjust());
            messageListBlock.setMessageCategory(message.getMessageCategory());
            if (message.getMessageCategory().equals(MessageCategory.ANNOUNCEMENT)
                    || message.getMessageCategory().equals(MessageCategory.NEWS))
            {
                messageListBlock.setTransferId(message.getRemark());
            }
            if (message.getCreateDate() != null)
            {
                messageListBlock.setCreateDate(DateUtil.date2String(message.getCreateDate(), 1));
            }
            messageListBlock.setRealName(message.getSender().getRealName());
            rows.add(messageListBlock);
        }
        PageModel page = new PageModel();
        page.setPageNumber(messages.getPageNumber());
        page.setTotalPages(messages.getTotalPages());
        listRestFulModel.setPage(page);
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("获取消息列表成功！"));
        return listRestFulModel;
    }

    /**
     * 设置消息为已读 <功能详细描述>
     * 
     * @param dictSchoolId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/receiverRead/{messageId}")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel receiverRead(@PathParam("messageId") Long messageId)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        if (messageId == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }
        com.sammyun.entity.message.Message message = messageService.find(messageId);
        if (message == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("未知消息不存在！");
            return mobileRestFulModel;
        }
        message.setReceiverRead(true);
        messageService.update(message);

        if (message.getMessageCategory().equals(MessageCategory.ASKLEAVE))
        {
            Member sender = message.getSender();
            MessageCategory messageCategory = message.getMessageCategory();
            String remark = message.getRemark();
            String tempRemark = "已回推";
            if (!remark.equalsIgnoreCase(tempRemark))
            {
                List<com.sammyun.entity.message.Message> messages = messageService.findMessage(remark, sender,
                        messageCategory);
                if (messages != null && messages.size() != 0)
                {
                    ImUserUtil imUserUtil = new ImUserUtil();
                    DictSchool dictSchool = message.getDictSchool();
                    Member reciver = message.getSender();
                    Member systemSender = memberService.findSystemMember(MemberType.system, dictSchool).get(0);
                    String subject = "请假审批";
                    String body = SpringUtils.getMessage("Api.message.receiverRead", reciver.getRealName());
                    messageService.save(imUserUtil.saveMessage(systemSender, reciver, subject, body,
                            MessageCategory.NOTICE, dictSchool, null, request));
                    for (com.sammyun.entity.message.Message tempMessage : messages)
                    {
                        tempMessage.setRemark(tempRemark);
                    }
                    messageService.batchUpdate(messages);
                }
            }
        }
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage(SpringUtils.getMessage("消息已读！"));
        return mobileRestFulModel;

    }

    /**
     * 设置发件人删除 <功能详细描述>
     * 
     * @param dictSchoolId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/senderDelete/{messageId}")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel senderDelete(@PathParam("messageId") Long messageId)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        if (messageId == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }
        com.sammyun.entity.message.Message message = messageService.find(messageId);
        if (message == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("未知消息不存在！");
            return mobileRestFulModel;
        }
        message.setSenderDelete(true);
        messageService.update(message);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage(SpringUtils.getMessage("删除成功！"));
        return mobileRestFulModel;

    }

    /**
     * 设置收件人删除 <功能详细描述>
     * 
     * @param dictSchoolId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/receiverDelete/{messageId}")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel receiverDelete(@PathParam("messageId") Long messageId)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        if (messageId == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }
        com.sammyun.entity.message.Message message = messageService.find(messageId);
        if (message == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("未知消息不存在！");
            return mobileRestFulModel;
        }
        message.setReceiverDelete(true);
        messageService.update(message);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage(SpringUtils.getMessage("删除成功！"));
        return mobileRestFulModel;

    }
}
