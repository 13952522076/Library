package com.sammyun.entity.library;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * 图书
 * 
 * @author maxu
 */
@Entity
@Table(name = "t_pe_similar_form")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_similar_form_sequence")
public class SimilarForm extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -2798028155336348301L;

    /**
     * 用户ID
     */
    private Long adminId;

    /** 同学ID */
    private Long studentId;

    /** 相似度 */
    private Double similarity;
    
    public SimilarForm(){
        
    }
    
    public SimilarForm(Long adminId,Long studentId,Double similarity){
        this.adminId = adminId;
        this.studentId = studentId;
        this.similarity = similarity;
    }

    public Long getAdminId()
    {
        return adminId;
    }

    public void setAdminId(Long adminId)
    {
        this.adminId = adminId;
    }

    public Long getStudentId()
    {
        return studentId;
    }

    public void setStudentId(Long studentId)
    {
        this.studentId = studentId;
    }

    public Double getSimilarity()
    {
        return similarity;
    }

    public void setSimilarity(Double similarity)
    {
        this.similarity = similarity;
    }

}
