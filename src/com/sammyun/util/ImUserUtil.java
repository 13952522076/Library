package com.sammyun.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sammyun.Setting;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.attendance.WorkSetting;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.message.Message.MessageCategory;
import com.sammyun.huanxin.EasemobIMUsers;
import com.sammyun.service.MemberService;
import com.sammyun.service.attendance.TimeCardService;
import com.sammyun.service.attendance.WorkSettingService;
import com.sammyun.service.dict.ClassTeacherMapService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.PatriarchStudentMapService;
import com.sammyun.service.json.JsonFamilyMapService;

@SuppressWarnings("unchecked")
public class ImUserUtil
{

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ImUserUtil.class);

    /** CacheManager */
    private static final CacheManager cacheManager = CacheManager.create();

    ClassTeacherMapService classTeacherMapService = SpringUtils.getBean("classTeacherMapServiceImpl",
            ClassTeacherMapService.class);

    PatriarchStudentMapService patriarchStudentMapService = SpringUtils.getBean("patriarchStudentMapServiceImpl",
            PatriarchStudentMapService.class);

    MemberService memberService = SpringUtils.getBean("memberServiceImpl", MemberService.class);

    EasemobIMUsers easemobIMUsers = SpringUtils.getBean("easemobIMUsers", EasemobIMUsers.class);

    TimeCardService timeCardService = SpringUtils.getBean("timeCardServiceImpl", TimeCardService.class);

    DictClassService dictClassService = SpringUtils.getBean("dictClassServiceImpl", DictClassService.class);

    JsonFamilyMapService jsonFamilyMapService = SpringUtils.getBean("jsonFamilyMapServiceImpl",
            JsonFamilyMapService.class);

    WorkSettingService workSettingService = SpringUtils.getBean("workSettingServiceImpl", WorkSettingService.class);

    /**
     * 根据班级将与班级相关的老师家长捆绑成好友
     * 
     * @param dictClass
     * @return
     */
    public boolean addFriendInClass(DictClass dictClass)
    {
        List<Member> members = new ArrayList<Member>();
        List<Member> patriarchMembers = new ArrayList<Member>();
        List<Member> teacherMembers = classTeacherMapService.findMemberByClass(dictClass);
        Set<DictStudent> dictStudents = dictClass.getDictStudents();
        if (dictStudents != null)
        {
            if (dictStudents.size() > 0)
            {
                for (DictStudent dictStudent : dictStudents)
                {
                    List<Member> patriarchItemMembers = patriarchStudentMapService.findMemberByStudent(dictStudent);
                    patriarchMembers.addAll(patriarchItemMembers);
                }
            }
        }
        members.addAll(teacherMembers);
        members.addAll(patriarchMembers);
        HashSet<Member> hashSet = new HashSet<Member>(members);
        members.clear();
        members.addAll(hashSet);
        if (members.size() == 0)
        {
            return false;
        }
        System.out.println(members.size());
        int num = 0;
        for (int i = 0; i < members.size(); i++)
        {
            for (int j = i; j < members.size(); j++)
            {
                String usernameI = members.get(i).getUsername();
                String usernameJ = members.get(j).getUsername();
                if (!usernameI.equals(usernameJ))
                {
                    ObjectNode objectNode = EasemobIMUsers.addFriendSingle(usernameI, usernameJ);
                    num = num + 1;
                }
            }
        }
        System.out.println(num);
        return true;
    }

    /**
     * 将同一个学校下的所有老师添加为好友
     * 
     * @param dictSchool
     */
    public boolean addTeacherFriendInSchool(DictSchool dictSchool)
    {
        List<Member> members = memberService.findBySchoolAndType(dictSchool, MemberType.teacher);
        if (members == null)
        {
            return false;
        }
        if (members.size() <= 0)
        {
            return false;
        }
        for (int i = 0; i < members.size(); i++)
        {
            for (int j = i; j < members.size(); j++)
            {
                String usernameI = members.get(i).getUsername();
                String usernameJ = members.get(j).getUsername();
                if (!usernameI.equals(usernameJ))
                {
                    ObjectNode objectNode = EasemobIMUsers.addFriendSingle(usernameI, usernameJ);
                }
            }
        }
        return true;
    }

    /**
     * 新增老师与同校的所有启用的老师成为好友
     * 
     * @param member
     * @param dictSchool
     * @return
     */
    public ObjectNode newTeacherFridendInSchool(Member newMember, DictSchool dictSchool)
    {
        List<Member> activeTeachers = memberService.findActiveTeachersBySchool(dictSchool);
        if (activeTeachers == null || activeTeachers.size() == 0)
        {
            return null;
        }
        for (Member member : activeTeachers)
        {
            if (member.getUsername() == null)
            {
                return null;
            }
        }
        ObjectNode objectNode = null;
        for (Member member : activeTeachers)
        {
            if (newMember.getUsername() == null)
            {
                continue;
            }
            if (newMember.getUsername().equals(member.getUsername()))
            {
                objectNode = easemobIMUsers.addFriend(newMember.getUsername(), member.getUsername());
                if (!objectNode.get("statusCode").asText().equalsIgnoreCase("200"))
                {
                    logger.error("教师端" + newMember.getUsername() + "与" + member.getUsername() + "添加环信好友失败");
                    continue;
                }
            }
        }
        return objectNode;
    }

    /**
     * 新增的老师和原老师组成好友 －自己先组成好友，然后循环和原老师组成好友
     * 
     * @param newMembers
     * @param oldMembers
     */
    public void twoMemberListAddFriend(List<Member> newMembers, List<Member> oldMembers)
    {
        if (newMembers == null || newMembers.size() == 0)
        {
            return;
        }
        // 新增成员先自己组成好友
        for (int i = 0; i < newMembers.size(); i++)
        {
            for (int j = i + 1; j < newMembers.size(); j++)
            {
                String nameI = newMembers.get(i).getUsername();
                String nameJ = newMembers.get(j).getUsername();
                easemobIMUsers.addFriend(nameI, nameJ);
            }
        }
        // 新增成员和老成员组成好友
        if (oldMembers == null || oldMembers.size() == 0)
        {
            return;
        }
        for (Member one : newMembers)
        {
            for (Member two : oldMembers)
            {
                easemobIMUsers.addFriend(one.getUsername(), two.getUsername());
            }
        }
    }

    /**
     * 新增老师和关联家长成为好友
     * 
     * @param newMember
     * @return
     */
    public ObjectNode newTeacherFriendWithClassPatriarch(Member newMember)
    {
        if (newMember == null || newMember.getUsername() == null)
        {
            return null;
        }
        List<DictClass> dictClasses = classTeacherMapService.findClassesByMember(newMember);
        if (dictClasses == null || dictClasses.size() == 0)
        {
            return null;
        }
        List<DictStudent> dictStudents = new ArrayList<DictStudent>();
        for (DictClass dictClass : dictClasses)
        {
            if (dictClass.getDictStudents() == null || dictClass.getDictStudents().size() == 0)
            {
                continue;
            }
            dictStudents.addAll(dictClass.getDictStudents());
        }
        if (dictStudents.size() == 0)
        {
            return null;
        }
        List<Member> patriarches = new ArrayList<Member>();
        for (DictStudent dictStudent : dictStudents)
        {
            List<Member> patriarchesItem = patriarchStudentMapService.findMemberByStudent(dictStudent);
            if (patriarchesItem == null || patriarchesItem.size() == 0)
            {
                continue;
            }
            patriarches.addAll(patriarchesItem);
        }
        List<Member> activePatriarches = new ArrayList<Member>();
        if (patriarches.size() == 0)
        {
            return null;
        }
        for (Member patriarch : patriarches)
        {
            if (patriarch.getIsEnabled() == true)
            {
                activePatriarches.add(patriarch);
            }
        }
        if (activePatriarches.size() == 0)
        {
            return null;
        }
        ObjectNode objectNode = null;
        for (Member activePatriarch : activePatriarches)
        {
            if (activePatriarch.getUsername() == null)
            {
                continue;
            }
            objectNode = easemobIMUsers.addFriend(newMember.getUsername(), activePatriarch.getUsername());
        }
        return objectNode;
    }

    /**
     * 删除老师的所有家长好友
     * 
     * @param teacher
     * @return
     */
    public ObjectNode deletePatriarchFriendByTeacher(Member teacher, List<DictClass> dictClasses)
    {
        if (teacher == null || teacher.getUsername() == null)
        {
            return null;
        }
        if (dictClasses == null || dictClasses.size() == 0)
        {
            return null;
        }
        List<Member> patriarches = new ArrayList<Member>();
        for (DictClass dictClass : dictClasses)
        {
            Set<DictStudent> dictStudents = dictClass.getDictStudents();
            if (dictStudents != null && dictStudents.size() != 0)
            {
                for (DictStudent dictstudent : dictStudents)
                {
                    List<Member> patriarchesItem = patriarchStudentMapService.findMemberByStudent(dictstudent);
                    if (patriarchesItem != null && patriarchesItem.size() != 0)
                    {
                        patriarches.addAll(patriarchesItem);
                    }
                }
            }
        }
        if (patriarches.size() == 0)
        {
            return null;
        }
        // 调环信接口删除好友
        ObjectNode objectNode = null;
        for (Member patriarch : patriarches)
        {
            objectNode = easemobIMUsers.deleteFriendSingle(teacher.getUsername(), patriarch.getUsername());
        }

        return objectNode;
    }

    /**
     * 老师添加和相关班级的学生相关家长好友关系
     * 
     * @param teacher
     * @param dictClasses
     * @return
     */
    public ObjectNode addPatriarchFriendByTeacher(Member teacher, List<DictClass> dictClasses)
    {
        if (teacher == null || teacher.getUsername() == null)
        {
            return null;
        }
        if (dictClasses == null || dictClasses.size() == 0)
        {
            return null;
        }
        List<Member> patriarches = new ArrayList<Member>();
        for (DictClass dictClass : dictClasses)
        {
            Set<DictStudent> dictStudents = dictClass.getDictStudents();
            if (dictStudents != null && dictStudents.size() != 0)
            {
                for (DictStudent dictstudent : dictStudents)
                {
                    List<Member> patriarchesItem = patriarchStudentMapService.findMemberByStudent(dictstudent);
                    if (patriarchesItem != null && patriarchesItem.size() != 0)
                    {
                        patriarches.addAll(patriarchesItem);
                    }
                }
            }
        }
        if (patriarches.size() == 0)
        {
            return null;
        }
        // 调环信接口添加好友
        ObjectNode objectNode = null;
        for (Member patriarch : patriarches)
        {
            objectNode = easemobIMUsers.addFriendSingle(teacher.getUsername(), patriarch.getUsername());
        }

        return objectNode;
    }

    /**
     * 学生更换家长后和新增的家长和班级里相关的家长成为好友
     * 
     * @param addMembers
     * @param dictClass
     * @return
     */
    public ObjectNode studentAddMember(List<Member> addMembers, DictClass dictClass)
    {
        /**
         * ｜老师 addMembers 与 学生－>班级—— >｜ ｜ ｜学生list——（家长list）list 自身list成为好友
         */
        if (dictClass == null)
        {
            return null;
        }
        if (addMembers == null || addMembers.size() == 0)
        {
            return null;
        }
        // 获取班级下的相关家长
        List<Member> patriarches = new ArrayList<Member>();
        Set<DictStudent> dictStudents = dictClass.getDictStudents();
        if (dictStudents != null && dictStudents.size() != 0)
        {
            for (DictStudent dictstudent : dictStudents)
            {
                List<Member> patriarchesItem = patriarchStudentMapService.findMemberByStudent(dictstudent);
                if (patriarchesItem != null && patriarchesItem.size() != 0)
                {
                    patriarches.addAll(patriarchesItem);
                }
            }
        }
        ObjectNode objectNode = null;
        // 家长先和家长成为朋友，然后在分别和班级下的成为朋友
        for (int i = 0; i < addMembers.size(); i++)
        {// 家长和家长成为好友
            for (int j = i; j < addMembers.size(); j++)
            {
                String usernameI = addMembers.get(i).getUsername();
                String usernameJ = addMembers.get(j).getUsername();
                if (!usernameI.equals(usernameJ))
                {
                    objectNode = EasemobIMUsers.addFriendSingle(usernameI, usernameJ);
                }
            }
        }

        for (Member addMember : addMembers)
        {// 家长分别和班级下学生成为好友
            for (Member patriarch : patriarches)
            {
                if (!addMember.getUsername().equals(patriarch.getUsername()))
                {
                    objectNode = easemobIMUsers.addFriendSingle(addMember.getUsername(), patriarch.getUsername());
                }
            }
        }

        List<Member> teachers = classTeacherMapService.findMemberByClass(dictClass);
        if (teachers != null && teachers.size() != 0)
        {
            for (Member addMember : addMembers)
            {// 家长分别和班级相关老师成为好友
                for (Member teacher : teachers)
                {
                    if (!addMember.getUsername().equals(teacher.getUsername()))
                    {
                        objectNode = easemobIMUsers.addFriendSingle(addMember.getUsername(), teacher.getUsername());
                    }
                }
            }
        }
        return objectNode;
    }

    /**
     * 学生更换家长后删除的和班级相关关系删除
     * 
     * @param delMembers
     * @param dictClass
     * @return
     */
    public ObjectNode studentDelMember(List<Member> delMembers, DictClass dictClass)
    {
        if (delMembers == null || delMembers.size() == 0)
        {
            return null;
        }
        // 获取班级下的相关家长
        List<Member> patriarches = new ArrayList<Member>();
        Set<DictStudent> dictStudents = dictClass.getDictStudents();
        if (dictStudents != null && dictStudents.size() != 0)
        {
            for (DictStudent dictstudent : dictStudents)
            {
                List<Member> patriarchesItem = patriarchStudentMapService.findMemberByStudent(dictstudent);
                if (patriarchesItem != null && patriarchesItem.size() != 0)
                {
                    patriarches.addAll(patriarchesItem);
                }
            }
        }
        // 删除的好友彼此之间删除好友关系
        for (int i = 0; i < delMembers.size(); i++)
        {
            Member one = delMembers.get(i);
            for (int j = 0; j < delMembers.size(); j++)
            {
                Member two = delMembers.get(j);
                // 删除好友的时候就不判断是否是同一个用户了
                // 判断两个家长间路线是否唯一（是否只有一个孩子能关联到这两个家长）,唯一的话可以删除
                if (patriarchStudentMapService.twoPatriarchUniqueInStudent(one, two))
                {
                    easemobIMUsers.deleteFriend(one.getUsername(), two.getUsername());
                }
            }
        }

        // 删除的好友和老师之间的删除好友关系
        List<Member> teachers = classTeacherMapService.findMemberByClass(dictClass);
        if (teachers != null && teachers.size() > 0)
        {
            for (Member delMember : delMembers)
            {
                for (Member teacher : teachers)
                {
                    // 判断老师和家长之间路线是否唯一(是否只有一个班级关联)
                    if (patriarchStudentMapService.patriarchTeacherUnique(delMember, teacher))
                    {
                        easemobIMUsers.deleteFriend(delMember.getUsername(), teacher.getUsername());
                    }
                }
            }
        }

        // 删除的好友和当前家长的删除好友关系
        for (int i = 0; i < delMembers.size(); i++)
        {
            Member one = delMembers.get(i);
            for (int j = 0; j < patriarches.size(); j++)
            {
                Member two = patriarches.get(j);
                // 删除好友的时候就不判断是否是同一个用户了
                // 判断两个家长间路线是否唯一（是否只有一个孩子能关联到这两个家长）,唯一的话可以删除
                easemobIMUsers.deleteFriend(one.getUsername(), two.getUsername());
            }
        }

        return null;
    }

    /**
     * 生成消息推送记录
     */
    public com.sammyun.entity.message.Message saveMessage(Member sender, Member reciver, String subject, String body,
            MessageCategory messageCategory, DictSchool dictSchool, String remark, HttpServletRequest request)
    {
        com.sammyun.entity.message.Message message = new com.sammyun.entity.message.Message();
        message.setBody(body);
        message.setDictSchool(dictSchool);
        message.setIsDraft(false);
        message.setMessageCategory(messageCategory);
        message.setReceiver(reciver);
        message.setReceiverDelete(false);
        message.setReceiverRead(false);
        message.setSender(sender);
        message.setSenderDelete(false);
        message.setHasPush(false);
        message.setXgPush(false);
        message.setSmsPush(false);
        message.setSubject(subject);
        message.setRemark(remark);
        message.setIp(EduUtil.getAddr(request));
        return message;
    }

    /**
     * 生成系统发件人
     */
    public void getSystemMember(DictSchool dictSchool)
    {
        List<Member> senders = memberService.findSystemMember(MemberType.system, dictSchool);
        if (senders == null || senders.size() == 0)
        {
            Member sender = new Member();
            sender.setMemberType(MemberType.system);
            sender.setDictSchool(dictSchool);
            sender.setUsername("13000000000");
            sender.setRealName("系统发件人");
            sender.setIsLocked(false);
            sender.setLoginFailureCount(0);
            sender.setSignature("");// 默认空签名
            Setting setting = SettingUtils.get();
            sender.setPassword(DigestUtils.md5Hex(setting.getInitPassword()));// 加密密码
            sender.setValidateCodeNumber(0);
            sender.setIsEnabled(false);
            sender.setPoint(0l);
            sender.setRegisterIp("192.168.1.1");
            memberService.save(sender);
        }
    }

    /**
     * 生成默认班次
     */
    public void getDefalutWorkSetting(DictSchool dictSchool)
    {
        List<WorkSetting> workSettings = workSettingService.findBySchool(dictSchool, true);
        if (workSettings == null || workSettings.size() == 0)
        {
            WorkSetting workSetting = new WorkSetting();
            workSetting.setWorkTime("08:00");
            workSetting.setStartWorkTime("08:00");
            workSetting.setEndWorkTime("08:30");
            workSetting.setClosingTime("17:00");
            workSetting.setStartClosingTime("17:00");
            workSetting.setEndClosingTime("23:59");
            workSetting.setName("默认排班");
            workSetting.setDictSchool(dictSchool);
            workSetting.setIsDefalut(true);
            workSettingService.save(workSetting);
        }
    }

    /**
     * 获取默认图片
     */
    public String getDefaultImageUrl(String imageUrl)
    {
        Setting setting = SettingUtils.get();
        String defaultImage = null;
        if (imageUrl == null || "".equalsIgnoreCase(imageUrl))
        {
            defaultImage = setting.getDefaultImage();
        }
        else
        {
            defaultImage = imageUrl;
        }
        return defaultImage;
    }

    /**
     * 构建JsonFamilyMap数据
     */
    public void getJsonFamilyMap(List<Long> ids, String field)
    {

        List<Long> memberIds = new LinkedList<Long>();

        Ehcache cache = cacheManager.getEhcache(Member.CACHE_NAME);
        net.sf.ehcache.Element cacheElement = cache.get(field);

        if (cacheElement != null)
        {
            memberIds = (List<Long>) cacheElement.getObjectValue();
        }
        try
        {
            if (ids != null && ids.size() != 0)
            {
                memberIds.addAll(ids);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }

        if (memberIds != null && memberIds.size() != 0)
        {
            cache.put(new net.sf.ehcache.Element(field, memberIds));

        }
    }
}
