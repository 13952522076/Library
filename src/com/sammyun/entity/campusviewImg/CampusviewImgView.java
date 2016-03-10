package com.sammyun.entity.campusviewImg;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 * Entity - 校园风光查看记录表
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Entity
@Table(name = "t_pe_campusview_img_view")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_campusview_img_view_sequence")
public class CampusviewImgView extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -8309610413253068934L;

    /** 查看时间戳 */
    private Date timestamp;

    /** 校园风光表 */
    private CampusviewImg campusviewImg;

    /** 点赞用户 */
    private Member member;

    @Column(name = "timestamp", scale = 0)
    public Date getTimestamp()
    {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp)
    {
        this.timestamp = timestamp;
    }

    /**
     * @return 返回 campusviewImg
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public CampusviewImg getCampusviewImg()
    {
        return campusviewImg;
    }

    /**
     * @param 对campusviewImg进行赋值
     */
    public void setCampusviewImg(CampusviewImg campusviewImg)
    {
        this.campusviewImg = campusviewImg;
    }

    /**
     * @return 返回 member
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
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

}
