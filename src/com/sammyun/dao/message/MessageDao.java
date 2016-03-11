package com.sammyun.dao.message;

import java.util.List;
import java.util.Date;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.BaseDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.message.Message;
import com.sammyun.entity.message.Message.MessageCategory;

/**
 * Message * Dao - 消息附件
 * 


 */
public interface MessageDao extends BaseDao<Message, Long>
{
    /**
     * 查询信消息
     * @param dictSchool
     * @param messageCategory
     * @param sender
     * @param senderDelete
     * @param receiver
     * @param receiverDelete
     * @param pageable
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Page<Message> findMessage(DictSchool dictSchool,MessageCategory messageCategory,Member sender,Boolean senderDelete,Member receiver,Boolean receiverDelete,Pageable pageable);
    
    /**
     * 查询信消息
     * @param dictSchool
     * @param messageCategory
     * @param sender
     * @param senderDelete
     * @param receiver
     * @param receiverDelete
     * @param pageable
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Page<Message> findMessage(DictSchool dictSchool,MessageCategory messageCategory,Member sender,Boolean senderDelete,Member receiver,Boolean receiverDelete,Pageable pageable, Date updateDate);
   
    /**
     * 查找消息分页
     * 
     * @param member 会员，null表示管理员
     * @param pageable 分页信息
     * @return 消息分页
     */
    Page<Message> findPage(Member member, Pageable pageable);

    /**
     * 查找草稿分页
     * 
     * @param sender 发件人，null表示管理员
     * @param pageable 分页信息
     * @return 草稿分页
     */
    Page<Message> findDraftPage(Member sender, Pageable pageable);

    /**
     * 查找消息数量
     * 
     * @param member 会员，null表示管理员
     * @param read 是否已读
     * @return 消息数量，不包含草稿
     */
    Long count(Member member, Boolean read);

    /**
     * 删除消息
     * 
     * @param id ID
     * @param member 执行人，null表示管理员
     */
    void remove(Long id, Member member);


    /**
     * 查找未推送的消息
     * <功能详细描述>
     * @param member
     * @param read
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Message> findMessage (Boolean hasPush,Integer count);
    
    /**
     * 查询同时推送的请假信息
     * @param hasPush
     * @param count
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Message> findMessage (String remark,Member sender,MessageCategory messageCategory);

}
