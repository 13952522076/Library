package com.sammyun.service.impl.dict;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.dict.ClassTeacherMapDao;
import com.sammyun.dao.dict.DictClassDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.dict.ClassTeacherMap;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictClass.ClassStatus;
import com.sammyun.service.AdminService;
import com.sammyun.service.dict.ClassTeacherMapService;
import com.sammyun.service.dict.PatriarchStudentMapService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * Service - 班级
 * 
 * @author Sencloud Team

 */
@Service("classTeacherMapServiceImpl")
public class ClassTeacherMapServiceImpl extends BaseServiceImpl<ClassTeacherMap, Long> implements
        ClassTeacherMapService
{
    @Resource(name = "classTeacherMapDaoImpl")
    private ClassTeacherMapDao classTeacherMapDao;

    @Resource(name = "classTeacherMapDaoImpl")
    public void setBaseDao(ClassTeacherMapDao classTeacherMapDao)
    {
        super.setBaseDao(classTeacherMapDao);
    }

    @Resource(name = "dictClassDaoImpl")
    private DictClassDao dictClassDao;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "patriarchStudentMapServiceImpl")
    private PatriarchStudentMapService patriarchStudentMapService;

    @Override
    public List<ClassTeacherMap> findClassesBySchoolAndMember(DictSchool school, Member member, ClassStatus classStatus)
    {
        return classTeacherMapDao.findClassesBySchoolAndMember(school, member, classStatus);
    }

    @Override
    public List<Member> findMemberByClass(DictClass dictClass)
    {
        List<Member> members = classTeacherMapDao.findMemberByClass(dictClass);
        if (members != null && members.size() == 0)
        {
            return new ArrayList<Member>();
        }
        if (members == null)
        {
            return new ArrayList<Member>();
        }
        HashSet<Member> hashSet = new HashSet<Member>(members);
        members.clear();
        members.addAll(hashSet);
        return members;
    }

    @Override
    public List<ClassTeacherMap> findMapByMember(Member member)
    {
        // TODO Auto-generated method stub
        return classTeacherMapDao.findMapByMember(member);
    }

    @Override
    public List<DictClass> findClassesByMember(Member member)
    {
        // TODO Auto-generated method stub
        return classTeacherMapDao.findClassesByMember(member);
    }

    @Override
    public List<ClassTeacherMap> createMapbyMembers(List<Member> members)
    {
        if (members == null)
        {
            return null;
        }
        if (members.size() == 0)
        {
            return null;
        }
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<ClassTeacherMap> classTeacherMaps = new ArrayList<ClassTeacherMap>();
        for (Member member : members)
        {
            String classNameString = member.getAddress();
            if (classNameString == null || classNameString.equals(""))
            {
                continue;
            }
            classNameString = classNameString.replaceAll("，", ",");
            String[] classNames = classNameString.split(",");
            for (String className : classNames)
            {
                //className.trim();
                List<DictClass> dictClasses = dictClassDao.getClassByName(className.trim(), dictSchool);
                if (dictClasses == null)
                {
                    continue;
                }
                else if (dictClasses.size() == 0)
                {
                    continue;
                }
                DictClass dictClass = dictClasses.get(0);
                ClassTeacherMap classTeacherMap = new ClassTeacherMap();
                classTeacherMap.setMember(member);
                classTeacherMap.setDictClass(dictClass);
                classTeacherMaps.add(classTeacherMap);
            }
            member.setAddress(null);
        }
        return classTeacherMaps;
    }

    @Override
    public List<Member> findDiaryMembers(Member member)
    {
        // TODO Auto-generated method stub
        List<DictClass> dictClasses = this.findClassesByMember(member);
        List<Member> friends = new ArrayList<Member>();
        if (dictClasses == null || dictClasses.size() == 0)
        {
            return null;
        }
        for (DictClass dictClass : dictClasses)
        {
            List<Member> teachers = this.findMemberByClass(dictClass);
            List<Member> patriarches = patriarchStudentMapService.findPatriarchByClass(dictClass);
            friends.addAll(patriarches);
            friends.addAll(teachers);
        }
        // 去重复
        HashSet<Member> hashSet = new HashSet<Member>(friends);
        friends.clear();
        friends.addAll(hashSet);

        return friends;
    }
}
