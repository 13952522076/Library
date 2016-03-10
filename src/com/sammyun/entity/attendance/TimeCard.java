package com.sammyun.entity.attendance;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.Member;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.hibernate.listener.TimeCardListener;

/**
 * 考勤卡管理
 * 
 * @author xutianlong
 * @version [版本号, May 7, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_time_card")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_time_card_sequence")
@EntityListeners(TimeCardListener.class)
public class TimeCard extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 1L;

    public enum CardStatus
    {
        /** 正常 */
        normal,
        /** 禁用 */
        disable,
        /** 挂失 */
        loss
    }

    /** 卡号管理 */
    private String cardNumber;

    /** 卡的状态 */
    private CardStatus cardStatus;

    /** 隶属的用户 */
    private Member member;

    /** 隶属的学生 */
    private DictStudent dictStudent;

    /**
     * @return 返回 cardNumber
     */
    @JsonProperty
    public String getCardNumber()
    {
        return cardNumber;
    }

    /**
     * @param 对cardNumber进行赋值
     */
    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    /**
     * @return 返回 cardStatus
     */
    @JsonProperty
    public CardStatus getCardStatus()
    {
        return cardStatus;
    }

    /**
     * @param 对cardStatus进行赋值
     */
    public void setCardStatus(CardStatus cardStatus)
    {
        this.cardStatus = cardStatus;
    }

    /**
     * @return 返回 member
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonProperty
    public Member getMember()
    {
        return member;
    }

    /**
     * @param 对member进行赋值
     */
    public void setMember(Member member)
    {
        this.member = member;
    }

    /**
     * @return 返回 dictStudent
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonProperty
    public DictStudent getDictStudent()
    {
        return dictStudent;
    }

    /**
     * @param 对dictStudent进行赋值
     */
    public void setDictStudent(DictStudent dictStudent)
    {
        this.dictStudent = dictStudent;
    }

}
