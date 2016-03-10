package com.sammyun.entity.dict;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.classalbum.ClassAlbumImage;
import com.sammyun.entity.course.CurriculumSchedule;
import com.sammyun.entity.course.WeeklyPlanSection;
import com.sammyun.entity.stu.GraduationCertificate;
import com.sammyun.entity.stu.GraduationPhoto;
import com.sammyun.hibernate.listener.DictClassListener;

/**
 * Entity - 班级
 * 
 * @author xutianlong
 * @version [版本号, Mar 27, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_dict_class")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_dict_class_sequence")
@EntityListeners(DictClassListener.class)
public class DictClass extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 6968685365027376204L;

    /**
     * 班级状态
     * 
     * @author xutianlong
     * @version [版本号, Apr 6, 2015]
     * @see [相关类/方法]
     * @since [产品/模块版本]
     */
    public enum ClassStatus
    {
        /** 活跃 */
        active,
        /** 毕业 */
        graduated
    }

    /** 班级代码 */
    private String code;

    /** 班级名 */
    private String name;

    /** 班主任 */
    private String cmaster;

    /** 描述 */
    private String description;

    /** 班级的状态 */
    private ClassStatus classStatus;

    /** 学生隶属的学校 */
    private DictSchool dictSchool;
    
    /** 班级隶属的年级 */
    private DictGrade dictGrade;
    
    /** 毕业证书*/
    private GraduationCertificate graduationCertificate;

    /** 学生列表 */
    private Set<DictStudent> dictStudents = new HashSet<DictStudent>();

    /** 班级老师对应列表 */
    private Set<ClassTeacherMap> classTeacherMap = new HashSet<ClassTeacherMap>();

    /** 班级对应课程表 */
    private Set<CurriculumSchedule> curriculumSchedules = new HashSet<CurriculumSchedule>();

    /** 周计划段列表 */
    private Set<WeeklyPlanSection> weeklyPlanSections = new HashSet<WeeklyPlanSection>();

    /** 班级相册 */
    private Set<ClassAlbumImage> classAlbumImages = new HashSet<ClassAlbumImage>();

    /** 毕业合影 */
    private List<GraduationPhoto> graduationPhotos = new ArrayList<GraduationPhoto>();

    /**
     * @return 返回 code
     */
    public String getCode()
    {
        return code;
    }

    /**
     * @param 对code进行赋值
     */
    public void setCode(String code)
    {
        this.code = code;
    }

    /**
     * @return 返回 name
     */
    @JsonProperty
    public String getName()
    {
        return name;
    }

    /**
     * @param 对name进行赋值
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return 返回 cmaster
     */
    public String getCmaster()
    {
        return cmaster;
    }

    /**
     * @param 对cmaster进行赋值
     */
    public void setCmaster(String cmaster)
    {
        this.cmaster = cmaster;
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
     * 获取班级的状态
     * 
     * @return 返回 classStatus
     */
    public ClassStatus getClassStatus()
    {
        return classStatus;
    }

    /**
     * 设置班级的状态
     * 
     * @param 对classStatus进行赋值
     */
    public void setClassStatus(ClassStatus classStatus)
    {
        this.classStatus = classStatus;
    }

    /**
     * 获取班级隶属的学校
     * 
     * @return 返回 dictRegins
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictSchool getDictSchool()
    {
        return dictSchool;
    }

    /**
     * 设置班级隶属的学校
     * 
     * @param 对dictSchool进行赋值
     */
    public void setDictSchool(DictSchool dictSchool)
    {
        this.dictSchool = dictSchool;
    }
    
    /**
     * 获取班级隶属的年级
     * 
     * @return 返回 dictRegins
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictGrade getDictGrade()
    {
        return dictGrade;
    }

    /**
     * @param dictGrade
     */
    public void setDictGrade(DictGrade dictGrade)
    {
        this.dictGrade = dictGrade;
    }


    /**
     * @return
     * @see [类、类#方法、类#成员]
     */
    @OneToOne(mappedBy = "dictClass", fetch = FetchType.LAZY)
    @JoinColumn(name = "dictClass")
    public GraduationCertificate getGraduationCertificate()
    {
        return graduationCertificate;
    }

    /**
     * @param graduationCertificate
     * @see [类、类#方法、类#成员]
     */
    public void setGraduationCertificate(GraduationCertificate graduationCertificate)
    {
        this.graduationCertificate = graduationCertificate;
    }

    /**
     * @return 返回 dictStudents
     */
    @JsonProperty
    @OneToMany(mappedBy = "dictClass", fetch = FetchType.LAZY)
    public Set<DictStudent> getDictStudents()
    {
        return dictStudents;
    }

    /**
     * @param 对dictStudents进行赋值
     */
    public void setDictStudents(Set<DictStudent> dictStudents)
    {
        this.dictStudents = dictStudents;
    }

    /**
     * @return ClassTeacherMap
     */
    @OneToMany(mappedBy = "dictClass", fetch = FetchType.LAZY)
    public Set<ClassTeacherMap> getClassTeacherMap()
    {
        return classTeacherMap;
    }

    /**
     * @param classTeacherMap
     */
    public void setClassTeacherMap(Set<ClassTeacherMap> classTeacherMap)
    {
        this.classTeacherMap = classTeacherMap;
    }

    /**
     * @return 返回 curriculumSchedules
     */
    @OneToMany(mappedBy = "dictClass", fetch = FetchType.LAZY)
    public Set<CurriculumSchedule> getCurriculumSchedules()
    {
        return curriculumSchedules;
    }

    /**
     * @param 对curriculumSchedules进行赋值
     */
    public void setCurriculumSchedules(Set<CurriculumSchedule> curriculumSchedules)
    {
        this.curriculumSchedules = curriculumSchedules;
    }

    /**
     * @return 返回 weeklyPlanSections
     */
    @OneToMany(mappedBy = "dictClass", fetch = FetchType.LAZY)
    public Set<WeeklyPlanSection> getWeeklyPlanSections()
    {
        return weeklyPlanSections;
    }

    /**
     * @param 对weeklyPlanSections进行赋值
     */
    public void setWeeklyPlanSections(Set<WeeklyPlanSection> weeklyPlanSections)
    {
        this.weeklyPlanSections = weeklyPlanSections;
    }

    /**
     * @return 返回 classAlbumImages
     */
    @OneToMany(mappedBy = "dictClass", fetch = FetchType.LAZY)
    public Set<ClassAlbumImage> getClassAlbumImages()
    {
        return classAlbumImages;
    }

    /**
     * @param 对classAlbumImages进行赋值
     */
    public void setClassAlbumImages(Set<ClassAlbumImage> classAlbumImages)
    {
        this.classAlbumImages = classAlbumImages;
    }

    /**
     * @return graduationPhotos
     */
    @OneToMany(mappedBy = "dictClass", fetch = FetchType.LAZY)
    public List<GraduationPhoto> getGraduationPhotos()
    {
        return graduationPhotos;
    }

    /**
     * @param graduationPhotos
     */
    public void setGraduationPhotos(List<GraduationPhoto> graduationPhotos)
    {
        this.graduationPhotos = graduationPhotos;
    }

}
