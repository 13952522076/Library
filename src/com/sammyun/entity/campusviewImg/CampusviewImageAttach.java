package com.sammyun.entity.campusviewImg;

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
import com.sammyun.entity.OrderEntity;

/**
 * 校园相册图片附件
 * @author xutianlong
 * @version [版本号, Apr 21, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_campusview_image_attach")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_campusview_image_attach_sequence")
public class CampusviewImageAttach extends OrderEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 2088554399359973129L;

    /**  图片附件  */
    private String imageAttach;

    /** 隶属的校园相册图片 */
    private CampusviewImg campusviewImg;

    /**
     * @return 返回 imageAttach
     */
    public String getImageAttach()
    {
        return imageAttach;
    }

    /**
     * @param 对imageAttach进行赋值
     */
    public void setImageAttach(String imageAttach)
    {
        this.imageAttach = imageAttach;
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

}
