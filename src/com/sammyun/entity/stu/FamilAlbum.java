package com.sammyun.entity.stu;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.dict.DictStudent;

/**
 * Entity - 家庭相册
 * 
 * @author xutianlong
 * @version [版本号, Jun 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_famil_album")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_famil_album_sequence")
public class FamilAlbum extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -6102207500803807682L;

    private String imageAttach;

    /** 图片描述 */
    private String description;

    /** 隶属学生 */
    private DictStudent dictStudent;

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
     * @return 返回 description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param 对description进行赋值
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return 返回 dictStudent
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
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
