package com.sammyun.dao.impl.message;

import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.message.MessageDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.message.Message;
import com.sammyun.entity.message.Message.MessageCategory;

/**
 * Message * DaoImpl - 消息附件
 */
@Repository("messageDaoImpl")
public class MessageDaoImpl extends BaseDaoImpl<Message, Long> implements MessageDao
{

    @Override
    public Page<Message> findMessage(DictSchool dictSchool, MessageCategory messageCategory, Member sender,
            Boolean senderDelete, Member receiver, Boolean receiverDelete, Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> root = criteriaQuery.from(Message.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();

        if (dictSchool != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("dictSchool"), dictSchool));
        }
        if (messageCategory != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("messageCategory"), messageCategory));
        }
        if (sender != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("sender"), sender));
        }
        if (senderDelete != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("senderDelete"), senderDelete));
        }
        if (receiver != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("receiver"), receiver));
        }
        if (receiverDelete != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("receiverDelete"), receiverDelete));
        }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }

    @Override
    public Page<Message> findMessage(DictSchool dictSchool, MessageCategory messageCategory, Member sender,
            Boolean senderDelete, Member receiver, Boolean receiverDelete, Pageable pageable, Date updateDate)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> root = criteriaQuery.from(Message.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();

        if (dictSchool != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("dictSchool"), dictSchool));
        }
        if (messageCategory != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("messageCategory"), messageCategory));
        }
        if (sender != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("sender"), sender));
        }
        if (senderDelete != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("senderDelete"), senderDelete));
        }
        if (receiver != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("receiver"), receiver));
        }
        if (receiverDelete != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("receiverDelete"), receiverDelete));
        }
        if (updateDate != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.greaterThanOrEqualTo(root.<Date> get("modifyDate"), updateDate));
        }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }

    public Page<Message> findPage(Member member, Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> root = criteriaQuery.from(Message.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.isNull(root.get("forMessage")),
                criteriaBuilder.equal(root.get("isDraft"), false));
        if (member != null)
        {
            restrictions = criteriaBuilder.and(
                    restrictions,
                    criteriaBuilder.or(
                            criteriaBuilder.and(criteriaBuilder.equal(root.get("sender"), member),
                                    criteriaBuilder.equal(root.get("senderDelete"), false)),
                            criteriaBuilder.and(criteriaBuilder.equal(root.get("receiver"), member),
                                    criteriaBuilder.equal(root.get("receiverDelete"), false))));
        }
        else
        {
            restrictions = criteriaBuilder.and(
                    restrictions,
                    criteriaBuilder.or(
                            criteriaBuilder.and(criteriaBuilder.isNull(root.get("sender")),
                                    criteriaBuilder.equal(root.get("senderDelete"), false)),
                            criteriaBuilder.and(criteriaBuilder.isNull(root.get("receiver")),
                                    criteriaBuilder.equal(root.get("receiverDelete"), false))));
        }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);

    }

    public Page<Message> findDraftPage(Member sender, Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> root = criteriaQuery.from(Message.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.isNull(root.get("forMessage")),
                criteriaBuilder.equal(root.get("isDraft"), true));
        if (sender != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("sender"), sender));
        }
        else
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.isNull(root.get("sender")));
        }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }

    public Long count(Member member, Boolean read)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> root = criteriaQuery.from(Message.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.isNull(root.get("forMessage")),
                criteriaBuilder.equal(root.get("isDraft"), false));
        if (member != null)
        {
            if (read != null)
            {
                restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(
                        criteriaBuilder.and(criteriaBuilder.equal(root.get("sender"), member),
                                criteriaBuilder.equal(root.get("senderDelete"), false),
                                criteriaBuilder.equal(root.get("senderRead"), read)),
                        criteriaBuilder.and(criteriaBuilder.equal(root.get("receiver"), member),
                                criteriaBuilder.equal(root.get("receiverDelete"), false),
                                criteriaBuilder.equal(root.get("receiverRead"), read))));
            }
            else
            {
                restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(
                        criteriaBuilder.and(criteriaBuilder.equal(root.get("sender"), member),
                                criteriaBuilder.equal(root.get("senderDelete"), false)),
                        criteriaBuilder.and(criteriaBuilder.equal(root.get("receiver"), member),
                                criteriaBuilder.equal(root.get("receiverDelete"), false))));
            }
        }
        else
        {
            if (read != null)
            {
                restrictions = criteriaBuilder.and(
                        restrictions,
                        criteriaBuilder.or(
                                criteriaBuilder.and(criteriaBuilder.isNull(root.get("sender")),
                                        criteriaBuilder.equal(root.get("senderDelete"), false),
                                        criteriaBuilder.equal(root.get("senderRead"), read)),
                                criteriaBuilder.and(criteriaBuilder.isNull(root.get("receiver")),
                                        criteriaBuilder.equal(root.get("receiverDelete"), false),
                                        criteriaBuilder.equal(root.get("receiverRead"), read))));
            }
            else
            {
                restrictions = criteriaBuilder.and(
                        restrictions,
                        criteriaBuilder.or(
                                criteriaBuilder.and(criteriaBuilder.isNull(root.get("sender")),
                                        criteriaBuilder.equal(root.get("senderDelete"), false)),
                                criteriaBuilder.and(criteriaBuilder.isNull(root.get("receiver")),
                                        criteriaBuilder.equal(root.get("receiverDelete"), false))));
            }
        }
        criteriaQuery.where(restrictions);
        return super.count(criteriaQuery, null);
    }

    @SuppressWarnings("unused")
    public void remove(Long id, Member member)
    {
        Message message = super.find(id);

    }

    @Override
    public List<Message> findMessage(Boolean hasPush, Integer count)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> root = criteriaQuery.from(Message.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (hasPush != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("hasPush"), hasPush));
        }
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery, null, count, null, null);
    }

    @Override
    public List<Message> findMessage(String remark, Member sender, MessageCategory messageCategory)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> criteriaQuery = criteriaBuilder.createQuery(Message.class);
        Root<Message> root = criteriaQuery.from(Message.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (remark != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("remark"), remark));
        }
        if (sender != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("sender"), sender));
        }
        if (messageCategory != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("messageCategory"), messageCategory));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

}
