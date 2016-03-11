package com.sammyun.entity.dict;

// Generated 2015-3-21 21:51:07 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * Entity - 课程名称字典表
 * 


 */
@Entity
@Table(name = "t_pe_dict_course_name")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_dict_course_name_sequence")
public class DictCourseName extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 87529200236552029L;

    private String code;

    @Column(name = "code", length = 100)
    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

}
