package com.sammyun.entity.classalbum;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;

/**
 * Entity - 班级相册
 * 


 */
@Entity
@Table(name = "t_pe_class_album_image")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_class_album_image_sequence")
public class ClassAlbumImage extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = 5092964685112877542L;

    /** 图片描述 */
    private String description;

    /** 创建者 */
    private String creator;

    /** 封面图片 */
    private String coverImage;
    
    /** 状态 （0未屏蔽，1屏蔽） */
    private Integer status;

    /** 隶属的学校 */
    private DictSchool dictSchool;

    /** 隶属的班级 */
    private DictClass dictClass;

    private List<ClassAlbumImageAttach> classAlbumImageAttachs = new ArrayList<ClassAlbumImageAttach>();

    @Column(name = "creator", scale = 0)
    public String getCreator()
    {
        return this.creator;
    }

    public void setCreator(String creator)
    {
        this.creator = creator;
    }

    // @Column(name = "description", length = 1000)
    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getCoverImage()
    {
        return coverImage;
    }

    public void setCoverImage(String coverImage)
    {
        this.coverImage = coverImage;
    }

    public Integer getStatus()
    {
        return status;
    }

    public void setStatus(Integer status)
    {
        this.status = status;
    }

    /**
     * @return 返回 dictSchool
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
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
     * @return 返回 campusviewImageAttachs
     */
    @OneToMany(mappedBy = "classAlbumImage", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @ElementCollection(targetClass = ClassAlbumImageAttach.class, fetch = FetchType.LAZY)
    // @CollectionTable(name = "xx_product_column_rotation_image", joinColumns
    // = @JoinColumn(name = "campusviewImg", referencedColumnName = "id"))
    public List<ClassAlbumImageAttach> getClassAlbumImageAttachs()
    {
        return classAlbumImageAttachs;
    }

    /**
     * @param 对campusviewImageAttachs进行赋值
     */
    public void setClassAlbumImageAttachs(List<ClassAlbumImageAttach> campusviewImageAttachs)
    {
        this.classAlbumImageAttachs = campusviewImageAttachs;
    }

    /**
     * @return 返回 dictClass
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictClass getDictClass()
    {
        return dictClass;
    }

    /**
     * @param 对dictClass进行赋值
     */
    public void setDictClass(DictClass dictClass)
    {
        this.dictClass = dictClass;
    }
    
}
