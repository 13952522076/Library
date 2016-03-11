/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.entity;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sammyun.entity.MemberAttribute.Type;
import com.sammyun.entity.attendance.TeacherAskLeave;
import com.sammyun.entity.attendance.TeacherAttendance;
import com.sammyun.entity.attendance.TimeCard;
import com.sammyun.entity.attendance.WorkScheduling;
import com.sammyun.entity.dict.ClassTeacherMap;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.PatriarchStudentMap;
import com.sammyun.entity.gd.AtUser;
import com.sammyun.entity.gd.DiaryAgree;
import com.sammyun.entity.gd.DiaryCollection;
import com.sammyun.entity.gd.DiaryComment;
import com.sammyun.entity.gd.DiaryTranspond;
import com.sammyun.entity.gd.GrowthDiary;
import com.sammyun.entity.message.Message;
import com.sammyun.hibernate.listener.MemberListener;
import com.sammyun.interceptor.MemberInterceptor;
import com.sammyun.util.DateUtil;
import com.sammyun.util.JsonUtils;

/**
 * Entity - 用户
 */
@Entity
@Table(name = "t_pe_member")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_member_sequence")
@EntityListeners(MemberListener.class)
public class Member extends BaseEntity
{

    private static final long serialVersionUID = 1533130686714725835L;

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

    public enum MemberType
    {
        /** 老师 */
        teacher,
        /** 家长 */
        patriarch,
        /** 系统发件人 */
        system
    }

    /** 缓存名称 */
    public static final String CACHE_NAME = "member";

    /** "身份信息"参数名称 */
    public static final String PRINCIPAL_ATTRIBUTE_NAME = MemberInterceptor.class.getName() + ".PRINCIPAL";

    /** "用户名"Cookie名称 */
    public static final String USERNAME_COOKIE_NAME = "username";

    /** 用户注册项值属性个数 */
    public static final int ATTRIBUTE_VALUE_PROPERTY_COUNT = 10;

    /** 用户注册项值属性名称前缀 */
    public static final String ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX = "attributeValue";

    /** 用户名(用作登陆账号) */
    private String username;

    /** 手机（用作登陆账号） */
    private String mobile;

    /** 真实姓名 */
    private String realName;

    /** 用户的身份证号码 */
    private String idCard;

    /** 密码 */
    private String password;

    /** 用户签名 */
    private String signature;

    /** 用户头像 */
    private String iconPhoto;

    /** E-mail */
    private String email;

    /** 积分 */
    private Long point;

    /** 是否启用 */
    private Boolean isEnabled;

    /** 是否锁定 */
    private Boolean isLocked;

    /** 连续登录失败次数 */
    private Integer loginFailureCount;

    /** 锁定日期 */
    private Date lockedDate;

    /** 注册IP */
    private String registerIp;

    /** 最后登录IP */
    private String loginIp;

    /** 最后登录日期 */
    private Date loginDate;

    /** 性别 */
    private Gender gender;

    /** 角色属性 */
    private MemberType memberType;

    /** 出生日期 */
    private Date birth;

    /** 地址 */
    private String address;

    /** 邮编 */
    private String zipCode;

    /** 电话(座机电话) */
    private String phone;

    /** 安全密匙 */
    private SafeKey safeKey;

    /** 地区 */
    private Area area;

    /** 请求验证码最大次数 */
    private Integer validateCodeNumber;

    /** 是否接送请假信息 */
    private Boolean isAcceptLeaveInfo;

    // /** 信息是否被更新过 -- 如果关联的小朋友信息更新了，也置为1 */
    // private Boolean isUpdate;

    /** 隶属学校 */
    private DictSchool dictSchool;

    /** 收款单 */
    private Set<Payment> payments = new HashSet<Payment>();

    /** 用户设备信息列表 */
    private Set<MemberDeviceInfo> memberDeviceInfos = new HashSet<MemberDeviceInfo>();

    /** 学生家长对应列表 */
    private Set<PatriarchStudentMap> patriarchStudentMap = new HashSet<PatriarchStudentMap>();

    /** 班级老师对应列表 */
    private Set<ClassTeacherMap> classTeacherMap = new HashSet<ClassTeacherMap>();

    /** 系统建议 */
    private Set<SystemSuggest> systemSuggests = new HashSet<SystemSuggest>();

    /** 接收的消息 */
    private Set<Message> inMessages = new HashSet<Message>();

    /** 发送的消息 */
    private Set<Message> outMessages = new HashSet<Message>();

    /** 考勤卡列表 */
    private Set<TimeCard> timeCards = new HashSet<TimeCard>();

    /** 排班管理 */
    private Set<WorkScheduling> workSchedulings = new HashSet<WorkScheduling>();

    /** 考勤管理管理 */
    private Set<TeacherAttendance> teacherAttendances = new HashSet<TeacherAttendance>();

    /** 成长记 */
    private Set<GrowthDiary> growthDiaries = new HashSet<GrowthDiary>();

    /** 成长记转发 */
    private Set<DiaryTranspond> diaryTransponds = new HashSet<DiaryTranspond>();

    /** 成長日記评论表 */
    private Set<DiaryComment> diaryComments = new HashSet<DiaryComment>();

    /** 成長日記收藏 */
    private Set<DiaryCollection> diaryCollections = new HashSet<DiaryCollection>();

    /** 成長日記点赞 */
    private Set<DiaryAgree> diaryAgrees = new HashSet<DiaryAgree>();

    /** 成長日記At到用戶列表 */
    private Set<AtUser> atUsers = new HashSet<AtUser>();

    /** 老师请假列表 */
    private Set<TeacherAskLeave> teacherAskLeaves = new HashSet<TeacherAskLeave>();

    /** 我审批的请假列表 */
    private Set<TeacherAskLeave> approvalAskLeaves = new HashSet<TeacherAskLeave>();

    /**
     * 获取用户名
     * 
     * @return 用户名
     */
    @JsonProperty
    @NotEmpty(groups = Save.class)
    @Pattern(regexp = "^[0-9a-z_A-Z\\u4e00-\\u9fa5]+$")
    // @Column(nullable = false, updatable = false, unique = true, length =
    // 100)
    @Column(nullable = false, unique = true, length = 100)
    public String getUsername()
    {
        return username;
    }

    /**
     * 设置用户名
     * 
     * @param username 用户名
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * 用户签名
     * 
     * @return 返回 signature
     */
    @Length(max = 200)
    @Column(nullable = false)
    public String getSignature()
    {
        return signature;
    }

    /**
     * 用户签名
     * 
     * @param 对signature进行赋值
     */
    public void setSignature(String signature)
    {
        this.signature = signature;
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
     * 获取密码
     * 
     * @return 密码
     */
    @NotEmpty(groups = Save.class)
    @Pattern(regexp = "^[^\\s&\"<>]+$")
    @Column(nullable = false)
    public String getPassword()
    {
        return password;
    }

    /**
     * 设置密码
     * 
     * @param password 密码
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * 获取E-mail
     * 
     * @return E-mail
     */
    @JsonProperty
    @Email
    @Length(max = 200)
    @Column(nullable = true)
    public String getEmail()
    {
        return email;
    }

    /**
     * 设置E-mail
     * 
     * @param email E-mail
     */
    public void setEmail(String email)
    {
        this.email = email;
    }

    /**
     * 获取积分
     * 
     * @return 积分
     */
    @JsonProperty
    @NotNull(groups = Save.class)
    @Min(0)
    @Column(nullable = false)
    public Long getPoint()
    {
        return point;
    }

    /**
     * 设置积分
     * 
     * @param point 积分
     */
    public void setPoint(Long point)
    {
        this.point = point;
    }

    /**
     * 获取是否启用
     * 
     * @return 是否启用
     */
    @NotNull
    @Column(nullable = false)
    public Boolean getIsEnabled()
    {
        return isEnabled;
    }

    /**
     * 设置是否启用
     * 
     * @param isEnabled 是否启用
     */
    public void setIsEnabled(Boolean isEnabled)
    {
        this.isEnabled = isEnabled;
    }

    /**
     * 获取是否锁定
     * 
     * @return 是否锁定
     */
    @JsonProperty
    @Column(nullable = false)
    public Boolean getIsLocked()
    {
        return isLocked;
    }

    /**
     * 设置是否锁定
     * 
     * @param isLocked 是否锁定
     */
    public void setIsLocked(Boolean isLocked)
    {
        this.isLocked = isLocked;
    }

    /**
     * 获取连续登录失败次数
     * 
     * @return 连续登录失败次数
     */
    @Column(nullable = false)
    public Integer getLoginFailureCount()
    {
        return loginFailureCount;
    }

    /**
     * 设置连续登录失败次数
     * 
     * @param loginFailureCount 连续登录失败次数
     */
    public void setLoginFailureCount(Integer loginFailureCount)
    {
        this.loginFailureCount = loginFailureCount;
    }

    /**
     * 获取锁定日期
     * 
     * @return 锁定日期
     */
    public Date getLockedDate()
    {
        return lockedDate;
    }

    /**
     * 设置锁定日期
     * 
     * @param lockedDate 锁定日期
     */
    public void setLockedDate(Date lockedDate)
    {
        this.lockedDate = lockedDate;
    }

    /**
     * 获取注册IP
     * 
     * @return 注册IP
     */
    @Column(nullable = false, updatable = false)
    public String getRegisterIp()
    {
        return registerIp;
    }

    /**
     * 设置注册IP
     * 
     * @param registerIp 注册IP
     */
    public void setRegisterIp(String registerIp)
    {
        this.registerIp = registerIp;
    }

    /**
     * 获取最后登录IP
     * 
     * @return 最后登录IP
     */
    public String getLoginIp()
    {
        return loginIp;
    }

    /**
     * 设置最后登录IP
     * 
     * @param loginIp 最后登录IP
     */
    public void setLoginIp(String loginIp)
    {
        this.loginIp = loginIp;
    }

    /**
     * 设置角色属性
     * 
     * @param memberType 角色属性
     */
    public void setMemberType(MemberType memberType)
    {
        this.memberType = memberType;
    }

    /**
     * 获取角色属性
     * 
     * @return 角色属性
     */
    public MemberType getMemberType()
    {
        return memberType;
    }

    /**
     * 获取最后登录日期
     * 
     * @return 最后登录日期
     */
    public Date getLoginDate()
    {
        return loginDate;
    }

    /**
     * 设置最后登录日期
     * 
     * @param loginDate 最后登录日期
     */
    public void setLoginDate(Date loginDate)
    {
        this.loginDate = loginDate;
    }

    /**
     * 获取性别
     * 
     * @return 性别
     */
    @JsonProperty
    public Gender getGender()
    {
        return gender;
    }

    /**
     * 设置性别
     * 
     * @param gender 性别
     */
    public void setGender(Gender gender)
    {
        this.gender = gender;
    }

    /**
     * 获取出生日期
     * 
     * @return 出生日期
     */
    @JsonProperty
    public Date getBirth()
    {
        return birth;
    }

    /**
     * 设置出生日期
     * 
     * @param birth 出生日期
     */
    public void setBirth(Date birth)
    {
        this.birth = birth;
    }

    /**
     * 获取地址
     * 
     * @return 地址
     */
    @JsonProperty
    @Length(max = 200)
    public String getAddress()
    {
        return address;
    }

    /**
     * 设置地址
     * 
     * @param address 地址
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * 获取邮编
     * 
     * @return 邮编
     */
    @JsonProperty
    @Length(max = 200)
    public String getZipCode()
    {
        return zipCode;
    }

    /**
     * 设置邮编
     * 
     * @param zipCode 邮编
     */
    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    /**
     * 获取电话
     * 
     * @return 电话
     */
    @JsonProperty
    @Length(max = 200)
    public String getPhone()
    {
        return phone;
    }

    /**
     * 设置电话
     * 
     * @param phone 电话
     */
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    /**
     * 获取手机
     * 
     * @return 手机
     */
    @JsonProperty
    @Length(max = 200)
    public String getMobile()
    {
        return mobile;
    }

    /**
     * 设置手机
     * 
     * @param mobile 手机
     */
    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    /**
     * @return 返回 realName
     */
    @JsonProperty
    public String getRealName()
    {
        return realName;
    }

    /**
     * @param 对realName进行赋值
     */
    public void setRealName(String realName)
    {
        this.realName = realName;
    }

    /**
     * @return 返回 idCard
     */
    public String getIdCard()
    {
        return idCard;
    }

    /**
     * @param 对idCard进行赋值
     */
    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    /**
     * @return 返回 age
     */
    @Transient
    @JsonProperty
    public Integer getAge()
    {
        return DateUtil.getAge(this.getBirth());
    }

    /**
     * 获取安全密匙
     * 
     * @return 安全密匙
     */
    @Embedded
    public SafeKey getSafeKey()
    {
        return safeKey;
    }

    /**
     * 设置安全密匙
     * 
     * @param safeKey 安全密匙
     */
    public void setSafeKey(SafeKey safeKey)
    {
        this.safeKey = safeKey;
    }

    /**
     * 获取地区
     * 
     * @return 地区
     */
    @ManyToOne(fetch = FetchType.LAZY)
    public Area getArea()
    {
        return area;
    }

    /**
     * 设置地区
     * 
     * @param area 地区
     */
    public void setArea(Area area)
    {
        this.area = area;
    }

    /**
     * 获取收款单
     * 
     * @return 收款单
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<Payment> getPayments()
    {
        return payments;
    }

    /**
     * 设置收款单
     * 
     * @param payments 收款单
     */
    public void setPayments(Set<Payment> payments)
    {
        this.payments = payments;
    }

    /**
     * @return 返回 用户设备信息列表
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<MemberDeviceInfo> getMemberDeviceInfos()
    {
        return memberDeviceInfos;
    }

    /**
     * @param 对用户设备信息列表进行赋值
     */
    public void setMemberDeviceInfos(Set<MemberDeviceInfo> memberDeviceInfos)
    {
        this.memberDeviceInfos = memberDeviceInfos;
    }

    /**
     * @return 返回 学生家长对应列表
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<PatriarchStudentMap> getPatriarchStudentMap()
    {
        return patriarchStudentMap;
    }

    /**
     * @param 对用户设备信息列表进行赋值
     */
    public void setPatriarchStudentMap(Set<PatriarchStudentMap> patriarchStudentMap)
    {
        this.patriarchStudentMap = patriarchStudentMap;
    }

    /**
     * @return 返回 用户隶属学校
     */
    @NotNull
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    public DictSchool getDictSchool()
    {
        return dictSchool;
    }

    /**
     * @param 对用户隶属学校设置
     */
    public void setDictSchool(DictSchool dictSchool)
    {
        this.dictSchool = dictSchool;
    }

    /**
     * @return 每天请求验证码次数
     */
    @Column(columnDefinition = "int default 0", nullable = false)
    public Integer getValidateCodeNumber()
    {
        return validateCodeNumber;
    }

    /**
     * @param validateCodeNumber 每天请求验证码次数
     */
    public void setValidateCodeNumber(Integer validateCodeNumber)
    {
        this.validateCodeNumber = validateCodeNumber;
    }

    /**
     * 是否接收请假信息
     * 
     * @return isAcceptLeaveInfo
     * @see [类、类#方法、类#成员]
     */
    public Boolean getIsAcceptLeaveInfo()
    {
        return isAcceptLeaveInfo;
    }

    /**
     * @param isAcceptLeaveInfo 是否接收请假信息
     */
    public void setIsAcceptLeaveInfo(Boolean isAcceptLeaveInfo)
    {
        this.isAcceptLeaveInfo = isAcceptLeaveInfo;
    }

    // /**
    // * @return isUpdate
    // */
    // public Boolean getIsUpdate()
    // {
    // return isUpdate;
    // }
    //
    // /**
    // * @param isUpdate
    // */
    // public void setIsUpdate(Boolean isUpdate)
    // {
    // this.isUpdate = isUpdate;
    // }

    /**
     * @return ClassTeacherMap
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
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
     * @return 返回 systemSuggests
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<SystemSuggest> getSystemSuggests()
    {
        return systemSuggests;
    }

    /**
     * @param 对systemSuggests进行赋值
     */
    public void setSystemSuggests(Set<SystemSuggest> systemSuggests)
    {
        this.systemSuggests = systemSuggests;
    }

    /**
     * @return 返回 timeCards
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<TimeCard> getTimeCards()
    {
        return timeCards;
    }

    /**
     * @param 对timeCards进行赋值
     */
    public void setTimeCards(Set<TimeCard> timeCards)
    {
        this.timeCards = timeCards;
    }

    /**
     * @return 返回排班列表
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<WorkScheduling> getWorkSchedulings()
    {
        return workSchedulings;
    }

    /**
     * @param workSchedulings
     */
    public void setWorkSchedulings(Set<WorkScheduling> workSchedulings)
    {
        this.workSchedulings = workSchedulings;
    }

    /**
     * 获取发送的消息
     * 
     * @return 发送的消息
     */
    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<Message> getOutMessages()
    {
        return outMessages;
    }

    /**
     * 设置发送的消息
     * 
     * @param outMessages 发送的消息
     */
    public void setOutMessages(Set<Message> outMessages)
    {
        this.outMessages = outMessages;
    }

    /**
     * 获取接收的消息
     * 
     * @return 接收的消息
     */
    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<Message> getInMessages()
    {
        return inMessages;
    }

    /**
     * 设置接收的消息
     * 
     * @param inMessages 接收的消息
     */
    public void setInMessages(Set<Message> inMessages)
    {
        this.inMessages = inMessages;
    }

    /**
     * 获取教师考勤
     * 
     * @return teacherAttendances
     * @see [类、类#方法、类#成员]
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<TeacherAttendance> getTeacherAttendances()
    {
        return teacherAttendances;
    }

    /**
     * 设置教师考勤
     * 
     * @param teacherAttendances
     * @see [类、类#方法、类#成员]
     */
    public void setTeacherAttendances(Set<TeacherAttendance> teacherAttendances)
    {
        this.teacherAttendances = teacherAttendances;
    }

    /**
     * 获取成长记
     * 
     * @return growthDiaries
     * @see [类、类#方法、类#成员]
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<GrowthDiary> getGrowthDiaries()
    {
        return growthDiaries;
    }

    /**
     * 设置成长记
     * 
     * @param growthDiaries
     * @see [类、类#方法、类#成员]
     */
    public void setGrowthDiaries(Set<GrowthDiary> growthDiaries)
    {
        this.growthDiaries = growthDiaries;
    }

    /**
     * 获取成长记转发
     * 
     * @return diaryTransponds
     * @see [类、类#方法、类#成员]
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<DiaryTranspond> getDiaryTransponds()
    {
        return diaryTransponds;
    }

    /**
     * 设置成长记转发
     * 
     * @param diaryTransponds
     * @see [类、类#方法、类#成员]
     */
    public void setDiaryTransponds(Set<DiaryTranspond> diaryTransponds)
    {
        this.diaryTransponds = diaryTransponds;
    }

    /**
     * 获取成长记评论
     * 
     * @return diaryComments
     * @see [类、类#方法、类#成员]
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<DiaryComment> getDiaryComments()
    {
        return diaryComments;
    }

    /**
     * 设置成长记评论
     * 
     * @param diaryComments
     * @see [类、类#方法、类#成员]
     */
    public void setDiaryComments(Set<DiaryComment> diaryComments)
    {
        this.diaryComments = diaryComments;
    }

    /**
     * 获取成长记收藏
     * 
     * @return diaryCollections
     * @see [类、类#方法、类#成员]
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<DiaryCollection> getDiaryCollections()
    {
        return diaryCollections;
    }

    /**
     * 设置成长记收藏
     * 
     * @param diaryComments
     * @see [类、类#方法、类#成员]
     */
    public void setDiaryCollections(Set<DiaryCollection> diaryCollections)
    {
        this.diaryCollections = diaryCollections;
    }

    /**
     * 获取成长记点赞
     * 
     * @return diaryAgrees
     * @see [类、类#方法、类#成员]
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<DiaryAgree> getDiaryAgrees()
    {
        return diaryAgrees;
    }

    /**
     * 设置成长记点赞
     * 
     * @param diaryAgrees
     * @see [类、类#方法、类#成员]
     */
    public void setDiaryAgrees(Set<DiaryAgree> diaryAgrees)
    {
        this.diaryAgrees = diaryAgrees;
    }

    /**
     * 获取成长记AT用户
     * 
     * @return atUsers
     * @see [类、类#方法、类#成员]
     */
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<AtUser> getAtUsers()
    {
        return atUsers;
    }

    /**
     * 设置成长记AT用户
     * 
     * @param atUsers
     * @see [类、类#方法、类#成员]
     */
    public void setAtUsers(Set<AtUser> atUsers)
    {
        this.atUsers = atUsers;
    }

    /**
     * 获取老师请假列表
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @OneToMany(mappedBy = "leaveMember", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<TeacherAskLeave> getTeacherAskLeaves()
    {
        return teacherAskLeaves;
    }

    /**
     * @param 对teacherAskLeave进行赋值
     */
    public void setTeacherAskLeaves(Set<TeacherAskLeave> teacherAskLeaves)
    {
        this.teacherAskLeaves = teacherAskLeaves;
    }

    /**
     * @return 返回 approvalAskLeaves
     */
    @OneToMany(mappedBy = "approvalMember", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    public Set<TeacherAskLeave> getApprovalAskLeaves()
    {
        return approvalAskLeaves;
    }

    /**
     * @param 对approvalAskLeaves进行赋值
     */
    public void setApprovalAskLeaves(Set<TeacherAskLeave> approvalAskLeaves)
    {
        this.approvalAskLeaves = approvalAskLeaves;
    }

    /**
     * 获取用户注册项值
     * 
     * @param memberAttribute 用户注册项
     * @return 用户注册项值
     */
    @Transient
    public Object getAttributeValue(MemberAttribute memberAttribute)
    {
        if (memberAttribute != null)
        {
            if (memberAttribute.getType() == Type.gender)
            {
                return getGender();
            }
            else if (memberAttribute.getType() == Type.birth)
            {
                return getBirth();
            }
            else if (memberAttribute.getType() == Type.area)
            {
                return getArea();
            }
            else if (memberAttribute.getType() == Type.address)
            {
                return getAddress();
            }
            else if (memberAttribute.getType() == Type.zipCode)
            {
                return getZipCode();
            }
            else if (memberAttribute.getType() == Type.phone)
            {
                return getPhone();
            }
            else if (memberAttribute.getType() == Type.mobile)
            {
                return getMobile();
            }
            else if (memberAttribute.getType() == Type.height)
            {
                return getMobile();
            }
            else if (memberAttribute.getType() == Type.weight)
            {
                return getMobile();
            }
            else if (memberAttribute.getType() == Type.checkbox)
            {
                if (memberAttribute.getPropertyIndex() != null)
                {
                    try
                    {
                        String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + memberAttribute.getPropertyIndex();
                        String propertyValue = (String) PropertyUtils.getProperty(this, propertyName);
                        if (propertyValue != null)
                        {
                            return JsonUtils.toObject(propertyValue, List.class);
                        }
                    }
                    catch (IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
                    catch (InvocationTargetException e)
                    {
                        e.printStackTrace();
                    }
                    catch (NoSuchMethodException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            else
            {
                if (memberAttribute.getPropertyIndex() != null)
                {
                    try
                    {
                        String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + memberAttribute.getPropertyIndex();
                        return (String) PropertyUtils.getProperty(this, propertyName);
                    }
                    catch (IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
                    catch (InvocationTargetException e)
                    {
                        e.printStackTrace();
                    }
                    catch (NoSuchMethodException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 设置用户注册项值
     * 
     * @param memberAttribute 用户注册项
     * @param attributeValue 用户注册项值
     */
    @Transient
    public void setAttributeValue(MemberAttribute memberAttribute, Object attributeValue)
    {
        if (memberAttribute != null)
        {
            if (attributeValue instanceof String && StringUtils.isEmpty((String) attributeValue))
            {
                attributeValue = null;
            }
            else if (memberAttribute.getType() == Type.gender
                    && (attributeValue instanceof Gender || attributeValue == null))
            {
                setGender((Gender) attributeValue);
            }
            else if (memberAttribute.getType() == Type.birth
                    && (attributeValue instanceof Date || attributeValue == null))
            {
                setBirth((Date) attributeValue);
            }
            else if (memberAttribute.getType() == Type.area
                    && (attributeValue instanceof Area || attributeValue == null))
            {
                setArea((Area) attributeValue);
            }
            else if (memberAttribute.getType() == Type.address
                    && (attributeValue instanceof String || attributeValue == null))
            {
                setAddress((String) attributeValue);
            }
            else if (memberAttribute.getType() == Type.zipCode
                    && (attributeValue instanceof String || attributeValue == null))
            {
                setZipCode((String) attributeValue);
            }
            else if (memberAttribute.getType() == Type.phone
                    && (attributeValue instanceof String || attributeValue == null))
            {
                setPhone((String) attributeValue);
            }
            else if (memberAttribute.getType() == Type.mobile
                    && (attributeValue instanceof String || attributeValue == null))
            {
                setMobile((String) attributeValue);
            }
            else if (memberAttribute.getType() == Type.checkbox
                    && (attributeValue instanceof List || attributeValue == null))
            {
                if (memberAttribute.getPropertyIndex() != null)
                {
                    if (attributeValue == null
                            || (memberAttribute.getOptions() != null && memberAttribute.getOptions().containsAll(
                                    (List<?>) attributeValue)))
                    {
                        try
                        {
                            String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX
                                    + memberAttribute.getPropertyIndex();
                            PropertyUtils.setProperty(this, propertyName, JsonUtils.toJson(attributeValue));
                        }
                        catch (IllegalAccessException e)
                        {
                            e.printStackTrace();
                        }
                        catch (InvocationTargetException e)
                        {
                            e.printStackTrace();
                        }
                        catch (NoSuchMethodException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
            else
            {
                if (memberAttribute.getPropertyIndex() != null)
                {
                    try
                    {
                        String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + memberAttribute.getPropertyIndex();
                        PropertyUtils.setProperty(this, propertyName, attributeValue);
                    }
                    catch (IllegalAccessException e)
                    {
                        e.printStackTrace();
                    }
                    catch (InvocationTargetException e)
                    {
                        e.printStackTrace();
                    }
                    catch (NoSuchMethodException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 移除所有用户注册项值
     */
    @Transient
    public void removeAttributeValue()
    {
        setGender(null);
        setBirth(null);
        setArea(null);
        setAddress(null);
        setZipCode(null);
        setPhone(null);
        setMobile(null);
        for (int i = 0; i < ATTRIBUTE_VALUE_PROPERTY_COUNT; i++)
        {
            String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + i;
            try
            {
                PropertyUtils.setProperty(this, propertyName, null);
            }
            catch (IllegalAccessException e)
            {
                e.printStackTrace();
            }
            catch (InvocationTargetException e)
            {
                e.printStackTrace();
            }
            catch (NoSuchMethodException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断当前用户的设备是否为智能设备
     * 
     * @return 商品价格
     */
    @JsonProperty
    @Transient
    public boolean getIsDevice()
    {
        if (getMemberDeviceInfos() != null && getMemberDeviceInfos().size() != 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

}
