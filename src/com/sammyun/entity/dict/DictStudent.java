package com.sammyun.entity.dict;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.attendance.AskLeave;
import com.sammyun.entity.attendance.Attendance;
import com.sammyun.entity.attendance.TimeCard;
import com.sammyun.entity.stu.FamilAlbum;
import com.sammyun.entity.stu.HealthFile;
import com.sammyun.entity.stu.OverallMerit;
import com.sammyun.entity.stu.StudentWorks;
import com.sammyun.hibernate.listener.DictStudentListener;
import com.sammyun.util.FreemarkerUtils;

import freemarker.template.TemplateException;

/**
 * Entity - 学生
 * 
 * @author xutianlong
 * @version [版本号, Mar 27, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_dict_student")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_dict_student_sequence")
@EntityListeners(DictStudentListener.class)
public class DictStudent extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -7669962182113131068L;
    
    /** 静态路径 */
    private static String staticPath;

    /**
     * 性别
     */
    public enum Gender
    {

        /** 男 */
        male,

        /** 女 */
        female
    }

    /** 学生状态 */
    public enum StudentStatus
    {
        /** 活跃 */
        active,
        /** 毕业 */
        graduated,
        /** 休学 */
        quit,
        /** 辍学 */
        dropouts
    }

    /** 学生学号 */
    private String studentNo;

    /** 学生姓名 */
    private String studentName;

    /** 学生头像 */
    private String iconPhoto;

    /** 学生状态 */
    private StudentStatus studentStatus;

    /** 性别 */
    private Gender gender;

    /** 生日（YYYY-MM-dd） */
    private Date birthday;

    /** 入园、入学时间 */
    private Date stuDate;

    /** 学生地址 */
    private String stuAddress;

    /** 备注 */
    private String stuRmark;

    /** 学生隶属哪个班级 */
    private DictClass dictClass;

    /** 学生的考勤列表 */
    private Set<Attendance> attendances = new HashSet<Attendance>();

    /** 请假列表 */
    private Set<AskLeave> askLeaves = new HashSet<AskLeave>();

    /** 学生家长对应列表 */
    private Set<PatriarchStudentMap> patriarchStudentMap = new HashSet<PatriarchStudentMap>();

    /** 卡片管理 */
    private Set<TimeCard> timeCards = new HashSet<TimeCard>();

    /** 家庭相册 */
    private List<FamilAlbum> familAlbums = new ArrayList<FamilAlbum>();

    /**健康档案*/
    private HealthFile healthFile;
    
    /** 学生作品 */
    private List<StudentWorks> studentWorkss = new ArrayList<StudentWorks>();
    
    /** 综合评价 */
    private List<OverallMerit> overallMerits  = new ArrayList<OverallMerit>();
    
    
    static
    {
        try
        {
            File moshopXmlFile = new ClassPathResource("/preschoolEdu.xml").getFile();
            org.dom4j.Document document = new SAXReader().read(moshopXmlFile);
            org.dom4j.Element element = (org.dom4j.Element) document.selectSingleNode("/preschoolEdu/template[@id='dictStudentContent']");
            staticPath = element.attributeValue("staticPath");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * @return 返回 studentNo
     */
    public String getStudentNo()
    {
        return studentNo;
    }

    /**
     * @param 对studentNo进行赋值
     */
    public void setStudentNo(String studentNo)
    {
        this.studentNo = studentNo;
    }

    /**
     * @return 返回 studentName
     */
    @JsonProperty
    public String getStudentName()
    {
        return studentName;
    }

    /**
     * @return 返回 iconPhoto
     */
    public String getIconPhoto()
    {
        return iconPhoto;
    }

    /**
     * @param 对iconPhoto进行赋值
     */
    public void setIconPhoto(String iconPhoto)
    {
        this.iconPhoto = iconPhoto;
    }

    /**
     * @return 返回 studentStatus
     */
    public StudentStatus getStudentStatus()
    {
        return studentStatus;
    }

    /**
     * @param 对studentStatus进行赋值
     */
    public void setStudentStatus(StudentStatus studentStatus)
    {
        this.studentStatus = studentStatus;
    }

    /**
     * @param 对studentName进行赋值
     */
    public void setStudentName(String studentName)
    {
        this.studentName = studentName;
    }

    /**
     * @return 返回 gender
     */
    public Gender getGender()
    {
        return gender;
    }

    /**
     * @param 对gender进行赋值
     */
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    /**
     * @return 返回 birthday
     */
    public Date getBirthday()
    {
        return birthday;
    }

    /**
     * @param 对birthday进行赋值
     */
    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    /**
     * @return 返回 stuDate
     */
    public Date getStuDate()
    {
        return stuDate;
    }

    /**
     * @param 对stuDate进行赋值
     */
    public void setStuDate(Date stuDate)
    {
        this.stuDate = stuDate;
    }

    /**
     * @return 返回 stuAddress
     */
    public String getStuAddress()
    {
        return stuAddress;
    }

    /**
     * @param 对stuAddress进行赋值
     */
    public void setStuAddress(String stuAddress)
    {
        this.stuAddress = stuAddress;
    }

    /**
     * @return 返回 stuRmark
     */
    public String getStuRmark()
    {
        return stuRmark;
    }

    /**
     * @param 对stuRmark进行赋值
     */
    public void setStuRmark(String stuRmark)
    {
        this.stuRmark = stuRmark;
    }

    /**
     * 获取学生隶属的班级
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonBackReference
    public DictClass getDictClass()
    {
        return dictClass;
    }

    /**
     * 设置学生隶属的班级
     * 
     * @param dictClass
     * @see [类、类#方法、类#成员]
     */
    @JsonBackReference
    public void setDictClass(DictClass dictClass)
    {
        this.dictClass = dictClass;
    }

    /**
     * @return 返回 attendances
     */
    @OneToMany(mappedBy = "dictStudent", fetch = FetchType.LAZY)
    public Set<Attendance> getAttendances()
    {
        return attendances;
    }

    /**
     * @param 对attendances进行赋值
     */
    public void setAttendances(Set<Attendance> attendances)
    {
        this.attendances = attendances;
    }

    /**
     * @return 返回 askLeaves
     */
    @OneToMany(mappedBy = "dictStudent", fetch = FetchType.LAZY)
    public Set<AskLeave> getAskLeaves()
    {
        return askLeaves;
    }

    /**
     * @param 对askLeaves进行赋值
     */
    public void setAskLeaves(Set<AskLeave> askLeaves)
    {
        this.askLeaves = askLeaves;
    }

    /**
     * @return 返回 patriarchStudentMap
     */
    @OneToMany(mappedBy = "dictStudent", fetch = FetchType.LAZY)
    public Set<PatriarchStudentMap> getPatriarchStudentMap()
    {
        return patriarchStudentMap;
    }

    /**
     * @param patriarchStudentMap
     */
    public void setPatriarchStudentMap(Set<PatriarchStudentMap> patriarchStudentMap)
    {
        this.patriarchStudentMap = patriarchStudentMap;
    }

    /**
     * @return 返回 timeCards
     */
    @OneToMany(mappedBy = "dictStudent", fetch = FetchType.LAZY)
    public Set<TimeCard> getTimeCards()
    {
        return timeCards;
    }

    /**
     * @param timeCards
     */
    public void setTimeCards(Set<TimeCard> timeCards)
    {
        this.timeCards = timeCards;
    }
    

    /**
     * @return 返回 familAlbums
     */
    @OneToMany(mappedBy = "dictStudent", fetch = FetchType.LAZY)
    public List<FamilAlbum> getFamilAlbums()
    {
        return familAlbums;
    }

    /**
     * @param 对familAlbums进行赋值
     */
    public void setFamilAlbums(List<FamilAlbum> familAlbums)
    {
        this.familAlbums = familAlbums;
    }

    /**
     * @return 返回 healthFile
     */
    @OneToOne(mappedBy = "dictStudent", fetch = FetchType.LAZY)
    @JoinColumn(name = "dictStudent")
    public HealthFile getHealthFile()
    {
        return healthFile;
    }

    /**
     * @param 对healthFile进行赋值
     */
    public void setHealthFile(HealthFile healthFile)
    {
        this.healthFile = healthFile;
    }
    
    /**
     * @return
     * @see [类、类#方法、类#成员]
     */
    @OneToMany(mappedBy = "dictStudent", fetch = FetchType.LAZY)
    public List<StudentWorks> getStudentWorkss()
    {
        return studentWorkss;
    }

    /**
     * @param studentWorkss
     */
    public void setStudentWorkss(List<StudentWorks> studentWorkss)
    {
        this.studentWorkss = studentWorkss;
    }

    /**
     * @return overallMerits
     */
    @OneToMany(mappedBy = "dictStudent", fetch = FetchType.LAZY)
    public List<OverallMerit> getOverallMerits()
    {
        return overallMerits;
    }

    /**
     * @param overallMerits
     */
    public void setOverallMerits(List<OverallMerit> overallMerits)
    {
        this.overallMerits = overallMerits;
    }

    /**
     * 获取访问路径
     * 
     * @return 访问路径
     */
    @JsonProperty
    @Transient
    public String getPath()
    {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", getId());
        try
        {
            return FreemarkerUtils.process(staticPath, model);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (TemplateException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
