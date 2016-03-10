package com.sammyun.controller.api.block;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.sammyun.entity.dict.DictStudent.StudentStatus;

@XmlRootElement(name = "login4StudentInfoBlock")
public class Login4StudentInfoBlock
{
    /** 班级id */
    private Long dictClassId;
    
    /** 学生id */
    private Long dictStudentId;
    
    /** 学生学号 */
    private String studentNo;

    /** 学生姓名 */
    private String studentName;

    /** 班级名 */
    private String name;
    
    /** 卡号管理 */
    private String cardNumber;
    
    /** 学生状态 */
    private StudentStatus studentStatus;

    @XmlElement
    public Long getDictClassId()
    {
        return dictClassId;
    }

    public void setDictClassId(Long dictClassId)
    {
        this.dictClassId = dictClassId;
    }

    @XmlElement
    public Long getDictStudentId()
    {
        return dictStudentId;
    }

    public void setDictStudentId(Long dictStudentId)
    {
        this.dictStudentId = dictStudentId;
    }

    @XmlElement
    public String getStudentNo()
    {
        return studentNo;
    }

    public void setStudentNo(String studentNo)
    {
        this.studentNo = studentNo;
    }

    @XmlElement
    public String getStudentName()
    {
        return studentName;
    }

    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    @XmlElement
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @XmlElement
    public String getCardNumber()
    {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber)
    {
        this.cardNumber = cardNumber;
    }

    @XmlElement
    public StudentStatus getStudentStatus()
    {
        return studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus)
    {
        this.studentStatus = studentStatus;
    }
}
