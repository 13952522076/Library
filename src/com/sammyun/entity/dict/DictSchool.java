package com.sammyun.entity.dict;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.Admin;
import com.sammyun.entity.Area;
import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.Member;
import com.sammyun.entity.announcement.Announcement;
import com.sammyun.entity.attendance.AttendanceEquipment;
import com.sammyun.entity.attendance.SchoolHours;
import com.sammyun.entity.attendance.WorkSetting;
import com.sammyun.entity.campusviewImg.CampusviewImg;
import com.sammyun.entity.classalbum.ClassAlbumImage;
import com.sammyun.entity.course.CourseName;
import com.sammyun.entity.course.QualityCourse;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.course.WeeklyPlanSection;
import com.sammyun.entity.message.Message;
import com.sammyun.entity.news.News;
import com.sammyun.entity.news.NewsCategory;
import com.sammyun.entity.parenting.Parenting;
import com.sammyun.entity.parenting.ParentingCategory;
import com.sammyun.entity.poster.Poster;
import com.sammyun.entity.profile.Profile;
import com.sammyun.entity.recipe.Recipe;
import com.sammyun.entity.stu.MeritTemplate;

/**
 * Entity - 学校
 */
@Entity
@Table(name = "t_pe_dict_school")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_dict_school_sequence")
public class DictSchool extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = -7190355280541440270L;

    /** 区域 */
    private Area area;

    /** 代码 */
    private String code;

    /** 学校名称 */
    private String name;

    /** 园长姓名 */
    private String kindergartenName;

    /** 园长电话 */
    private String kindergartenPhone;

    /** 学校地址 */
    private String address;

    /** 描述 */
    private String description;

    /** 学校logo */
    private String schoolLogo;

    /** 学校背景色 */
    private String bgColor;

    /** 会员 */
    private Set<Admin> admins = new HashSet<Admin>();

    /** 成员 */
    private Set<Member> members = new HashSet<Member>();

    /** 班级列表 */
    private Set<DictClass> dictClasses = new HashSet<DictClass>();

    /** 课程列表 */
    private Set<CourseName> courseNames = new HashSet<CourseName>();

    /** 食谱列表 */
    private Set<Recipe> recipes = new HashSet<Recipe>();

    /** 学年列表 */
    private Set<SchoolYearMng> schoolYearMngs = new HashSet<SchoolYearMng>();

    /** 考勤机器列表 */
    private Set<AttendanceEquipment> attendanceEquipments = new HashSet<AttendanceEquipment>();

    /** 新闻类别 */
    private Set<NewsCategory> newsCategories = new HashSet<NewsCategory>();

    /** 新闻 */
    private Set<News> newses = new HashSet<News>();

    /** 通知公告数据 */
    private Set<Announcement> announcements = new HashSet<Announcement>();

    /** 学校概况数据 */
    private Profile profile;

    /** 周计划段 */
    private Set<WeeklyPlanSection> weeklyPlanSections = new HashSet<WeeklyPlanSection>();

    /** 校园风光表 */
    private Set<CampusviewImg> campusviewImgs = new HashSet<CampusviewImg>();

    /** 海报 */
    private Set<Poster> posters = new HashSet<Poster>();

    /** 消息 */
    private Set<Message> messages = new HashSet<Message>();

    /** 班次设置 */
    private Set<WorkSetting> workSettings = new HashSet<WorkSetting>();

    /** 精品课程列表 */
    private Set<QualityCourse> qualityCourses = new HashSet<QualityCourse>();

    /** 班级相册 */
    private Set<ClassAlbumImage> classAlbumImages = new HashSet<ClassAlbumImage>();

    /** 评价等级模板 */
    private Set<MeritTemplate> meritTemplate = new HashSet<MeritTemplate>();

    /** 育儿类别 */
    private Set<ParentingCategory> parentingCategories = new HashSet<ParentingCategory>();

    /** 育儿 */
    private Set<Parenting> parentings = new HashSet<Parenting>();

    /** 学校上学放学时间 */
    private SchoolHours schoolHours;

    /** 年级清单 */
    private Set<DictGrade> dictGrades = new HashSet<DictGrade>();

    @Column(name = "code", length = 40)
    public String getCode()
    {
        return this.code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    // @Column(name = "description", length = 2000)
    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return schoolLogo
     */
    public String getSchoolLogo()
    {
        return schoolLogo;
    }

    /**
     * @param schoolLogo
     */
    public void setSchoolLogo(String schoolLogo)
    {
        this.schoolLogo = schoolLogo;
    }

    /**
     * @return bgColor
     */
    public String getBgColor()
    {
        return bgColor;
    }

    /**
     * @param bgColor
     */
    public void setBgColor(String bgColor)
    {
        this.bgColor = bgColor;
    }

    @Column(name = "name", length = 100)
    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 获取会员
     * 
     * @return 会员
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<Admin> getAdmins()
    {
        return admins;
    }

    /**
     * 设置会员
     * 
     * @param members 会员
     */
    public void setAdmins(Set<Admin> admins)
    {
        this.admins = admins;
    }

    /**
     * 获取成员
     * 
     * @return 成员
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<Member> getMembers()
    {
        return members;
    }

    /**
     * 设置成员
     * 
     * @param members 成员
     */
    public void setMembers(Set<Member> members)
    {
        this.members = members;
    }

    /**
     * @return 返回 address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * @param 对address进行赋值
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * @return 返回 kindergartenName
     */
    public String getKindergartenName()
    {
        return kindergartenName;
    }

    /**
     * @param 对kindergartenName进行赋值
     */
    public void setKindergartenName(String kindergartenName)
    {
        this.kindergartenName = kindergartenName;
    }

    /**
     * @return 返回 kindergartenPhone
     */
    public String getKindergartenPhone()
    {
        return kindergartenPhone;
    }

    /**
     * @param 对kindergartenPhone进行赋值
     */
    public void setKindergartenPhone(String kindergartenPhone)
    {
        this.kindergartenPhone = kindergartenPhone;
    }

    /**
     * @return 返回 area
     */
    @NotNull
    @JsonProperty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public Area getArea()
    {
        return area;
    }

    /**
     * @param 对area进行赋值
     */
    public void setArea(Area area)
    {
        this.area = area;
    }

    /**
     * 获取班级
     * 
     * @return 班级
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<DictClass> getDictClasses()
    {
        return dictClasses;
    }

    /**
     * @param 对dictClasses进行赋值
     */
    public void setDictClasses(Set<DictClass> dictClasses)
    {
        this.dictClasses = dictClasses;
    }

    /**
     * @return 返回 courseNames
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<CourseName> getCourseNames()
    {
        return courseNames;
    }

    /**
     * @param 对courseNames进行赋值
     */
    public void setCourseNames(Set<CourseName> courseNames)
    {
        this.courseNames = courseNames;
    }

    /**
     * @return 返回 recipes
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<Recipe> getRecipes()
    {
        return recipes;
    }

    /**
     * @param 对recipes进行赋值
     */
    public void setRecipes(Set<Recipe> recipes)
    {
        this.recipes = recipes;
    }

    /**
     * @return 返回 schoolYearMngs
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<SchoolYearMng> getSchoolYearMngs()
    {
        return schoolYearMngs;
    }

    /**
     * @param 对schoolYearMngs进行赋值
     */
    public void setSchoolYearMngs(Set<SchoolYearMng> schoolYearMngs)
    {
        this.schoolYearMngs = schoolYearMngs;
    }

    /**
     * @return 返回 attendanceEquipments
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<AttendanceEquipment> getAttendanceEquipments()
    {
        return attendanceEquipments;
    }

    /**
     * @param 对attendanceEquipments进行赋值
     */
    public void setAttendanceEquipments(Set<AttendanceEquipment> attendanceEquipments)
    {
        this.attendanceEquipments = attendanceEquipments;
    }

    /**
     * @return 返回 newsCategories
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<NewsCategory> getNewsCategories()
    {
        return newsCategories;
    }

    /**
     * @param 对newsCategories进行赋值
     */
    public void setNewsCategories(Set<NewsCategory> newsCategories)
    {
        this.newsCategories = newsCategories;
    }

    /**
     * @return 返回 newses
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<News> getNewses()
    {
        return newses;
    }

    /**
     * @param 对newses进行赋值
     */
    public void setNewses(Set<News> newses)
    {
        this.newses = newses;
    }

    /**
     * @return 返回 profile
     */
    @OneToOne(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    @JoinColumn(name = "dictSchool")
    public Profile getProfile()
    {
        return profile;
    }

    /**
     * @param 对profile进行赋值
     */
    public void setProfile(Profile profile)
    {
        this.profile = profile;
    }

    /**
     * @return 返回 weeklyPlanSections
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
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
     * @return 返回 announcements
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<Announcement> getAnnouncements()
    {
        return announcements;
    }

    /**
     * @param 对announcements进行赋值
     */
    public void setAnnouncements(Set<Announcement> announcements)
    {
        this.announcements = announcements;
    }

    /**
     * @return 返回 campusviewImgs
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<CampusviewImg> getCampusviewImgs()
    {
        return campusviewImgs;
    }

    /**
     * @param 对campusviewImgs进行赋值
     */
    public void setCampusviewImgs(Set<CampusviewImg> campusviewImgs)
    {
        this.campusviewImgs = campusviewImgs;
    }

    /**
     * @return 返回 posters
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<Poster> getPosters()
    {
        return posters;
    }

    /**
     * @param 对posters进行赋值
     */
    public void setPosters(Set<Poster> posters)
    {
        this.posters = posters;
    }

    /**
     * @return 返回 messages
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<Message> getMessages()
    {
        return messages;
    }

    /**
     * @param 对messages进行赋值
     */
    public void setMessages(Set<Message> messages)
    {
        this.messages = messages;
    }

    /**
     * @return 返回 workSettings
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<WorkSetting> getWorkSettings()
    {
        return workSettings;
    }

    /**
     * @param 对workSettings进行赋值
     */
    public void setWorkSettings(Set<WorkSetting> workSettings)
    {
        this.workSettings = workSettings;
    }

    /**
     * @return 返回 qualityCourses
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<QualityCourse> getQualityCourses()
    {
        return qualityCourses;
    }

    /**
     * @param 对qualityCourses进行赋值
     */
    public void setQualityCourses(Set<QualityCourse> qualityCourses)
    {
        this.qualityCourses = qualityCourses;
    }

    /**
     * @return 返回 classAlbumImages
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
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
     * @return 返回 meritTemplate
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<MeritTemplate> getMeritTemplate()
    {
        return meritTemplate;
    }

    /**
     * @param 对meritTemplate进行赋值
     */
    public void setMeritTemplate(Set<MeritTemplate> meritTemplate)
    {
        this.meritTemplate = meritTemplate;
    }

    /**
     * @return parentingCategories
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<ParentingCategory> getParentingCategories()
    {
        return parentingCategories;
    }

    /**
     * @param parentingCategories
     */
    public void setParentingCategories(Set<ParentingCategory> parentingCategories)
    {
        this.parentingCategories = parentingCategories;
    }

    /**
     * @return parentings
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<Parenting> getParentings()
    {
        return parentings;
    }

    /**
     * @param parentings
     */
    public void setParentings(Set<Parenting> parentings)
    {
        this.parentings = parentings;
    }

    /**
     * @return schoolHours
     */
    @OneToOne(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    @JoinColumn(name = "dictSchool")
    public SchoolHours getSchoolHours()
    {
        return schoolHours;
    }

    /**
     * @param schoolHours
     */
    public void setSchoolHours(SchoolHours schoolHours)
    {
        this.schoolHours = schoolHours;
    }

    /**
     * @return 返回 dictGrades
     */
    @OneToMany(mappedBy = "dictSchool", fetch = FetchType.LAZY)
    public Set<DictGrade> getDictGrades()
    {
        return dictGrades;
    }

    /**
     * @param 对dictGrades进行赋值
     */
    public void setDictGrades(Set<DictGrade> dictGrades)
    {
        this.dictGrades = dictGrades;
    }

}
