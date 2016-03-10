package com.sammyun.entity.dict;

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

import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.Member;
import com.sammyun.hibernate.listener.PatriarchStudentMapListener;

/**
 * entity - 学生家长列表 <功能详细描述>
 * 
 * @author xutianlong
 * @version [版本号, Apr 9, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_patriarch_student_map")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_patriarch_student_map_sequence")
@EntityListeners(PatriarchStudentMapListener.class)
public class PatriarchStudentMap extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 2289940382819450178L;

    public enum Type
    {
        /** 爸爸 */
        father,
        /** 妈妈 */
        mother,
        /** 爷爷 */
        grandfather,
        /** 奶奶 */
        grandmother,
        /** 外祖父 */
        mgrandfather,
        /** 外祖母 */
        mgrandmother,
    }

    /** 对应的学生 */
    private DictStudent dictStudent;

    /** 对应的家长 */
    private Member member;

    /** 是爸爸还是妈妈 */

    private Type type;

    /** 备注 */
    private String remark;

    /**
     * @return 对应的学生
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictStudent getDictStudent()
    {
        return dictStudent;
    }

    /**
     * @param dictStudent 对应的学生
     */
    public void setDictStudent(DictStudent dictStudent)
    {
        this.dictStudent = dictStudent;
    }

    /**
     * @return 对应的家长
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Member getMember()
    {
        return member;
    }

    /**
     * @param member 对应的家长
     */
    public void setMember(Member member)
    {
        this.member = member;
    }

    /**
     * @return 家长类型
     */
    public Type getType()
    {
        return type;
    }

    /**
     * @param type 家长类型
     */
    public void setType(Type type)
    {
        this.type = type;
    }

    /**
     * @return 备注
     */
    public String getRemark()
    {
        return remark;
    }

    /**
     * @param remark 备注
     */
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

}
