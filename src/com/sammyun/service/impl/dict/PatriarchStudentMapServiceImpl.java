package com.sammyun.service.impl.dict;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.dict.ClassTeacherMapDao;
import com.sammyun.dao.dict.PatriarchStudentMapDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.dict.PatriarchStudentMap;
import com.sammyun.service.MemberService;
import com.sammyun.service.dict.ClassTeacherMapService;
import com.sammyun.service.dict.PatriarchStudentMapService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * PatriarchStudentMap * ServiceImpl - 学生家长对应列表
 */
@Service("patriarchStudentMapServiceImpl")
public class PatriarchStudentMapServiceImpl extends BaseServiceImpl<PatriarchStudentMap, Long> implements
        PatriarchStudentMapService
{
    @Resource(name = "patriarchStudentMapDaoImpl")
    private PatriarchStudentMapDao patriarchStudentMapDao;

    @Resource(name = "classTeacherMapDaoImpl")
    private ClassTeacherMapDao classTeacherMapDao;

    @Resource(name = "patriarchStudentMapDaoImpl")
    public void setBaseDao(PatriarchStudentMapDao patriarchStudentMapDao)
    {
        super.setBaseDao(patriarchStudentMapDao);
    }

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "classTeacherMapServiceImpl")
    private ClassTeacherMapService classTeacherMapService;

    @Override
    public List<PatriarchStudentMap> findStudentByMember(Member member)
    {
        return patriarchStudentMapDao.findStudentByMember(member);
    }

    @Override
    public PatriarchStudentMap findStudentByMember(Member member, DictStudent dictStudent)
    {
        return patriarchStudentMapDao.findStudentByMember(member, dictStudent);
    }

    @Override
    public List<PatriarchStudentMap> findByStudent(DictStudent dictStudent)
    {
        return patriarchStudentMapDao.findByStudent(dictStudent);
    }

    @Override
    public List<Member> findMemberByStudent(DictStudent dictStudent)
    {
        // TODO Auto-generated method stub
        return patriarchStudentMapDao.findMemberByStudent(dictStudent);
    }

    @Override
    public List<PatriarchStudentMap> createMapsByStudents(List<DictStudent> dictStudents)
    {
        if (dictStudents == null)
        {
            return null;
        }
        if (dictStudents.size() == 0)
        {
            return null;
        }
        // DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<PatriarchStudentMap> patriarchStudentMaps = new ArrayList<PatriarchStudentMap>();
        for (DictStudent dictStudent : dictStudents)
        {
            String memberString = dictStudent.getStuRmark();
            if (memberString == null || memberString.equals(""))
            {
                continue;
            }
            memberString = memberString.replaceAll("，", ",");
            String[] memberUserNames = memberString.split(",");
            for (String memberUserName : memberUserNames)
            {
                // memberUserName.trim();
                Member member = memberService.findByUsername(memberUserName.trim());
                if (member == null)
                {
                    continue;
                }
                // member.setIsUpdate(true);
                // memberService.update(member);
                PatriarchStudentMap patriarchStudentMap = new PatriarchStudentMap();
                patriarchStudentMap.setMember(member);
                patriarchStudentMap.setDictStudent(dictStudent);
                patriarchStudentMaps.add(patriarchStudentMap);
            }
            dictStudent.setStuRmark(null);
        }
        return patriarchStudentMaps;
    }

    @Override
    public List<Member> findPatriarchByClass(DictClass dictClass)
    {
        List<Member> patriarchMembers = new ArrayList<Member>();
        if (dictClass == null)
        {
            return null;
        }
        Set<DictStudent> dictStudents = dictClass.getDictStudents();
        if (dictStudents == null)
        {
            return null;
        }
        if (dictStudents.size() == 0)
        {
            return null;
        }
        for (DictStudent dictStudent : dictStudents)
        {
            List<Member> patriarchItemMembers = this.findMemberByStudent(dictStudent);
            patriarchMembers.addAll(patriarchItemMembers);
        }
        return patriarchMembers;
    }

    @Override
    public Boolean twoPatriarchUniqueInStudent(Member one, Member two)
    {
        // TODO Auto-generated method stub
        List<DictStudent> studentsOne = patriarchStudentMapDao.findStudentByPatriarch(one);
        if (studentsOne == null || studentsOne.size() == 0)
        {
            return true;
        }
        List<DictStudent> studentsTwo = patriarchStudentMapDao.findStudentByPatriarch(two);
        if (studentsOne == null || studentsOne.size() == 0)
        {
            return true;
        }
        List<DictStudent> sameStudents = studentsOne;
        sameStudents.retainAll(studentsTwo);
        if (sameStudents.size() <= 1)
        {
            return true;
        }
        return false;
    }

    @Override
    public Boolean patriarchTeacherUnique(Member patriarch, Member teacher)
    {
        if (patriarch == null || teacher == null)
        {
            return true;
        }
        List<DictStudent> patriarchStudents = patriarchStudentMapDao.findStudentByPatriarch(patriarch);
        if (patriarchStudents == null || patriarchStudents.size() == 0)
        {
            return true;
        }
        List<DictClass> patriarchClasses = new ArrayList<DictClass>();// 从家长获取相关班级
        for (DictStudent student : patriarchStudents)
        {
            DictClass classItem = student.getDictClass();
            patriarchClasses.add(classItem);
        }
        if (patriarchClasses.size() == 0)
        {
            return true;
        }
        List<DictClass> teacherClasses = classTeacherMapDao.findClassesByMember(teacher);
        if (teacherClasses == null || teacherClasses.size() == 0)
        {
            return true;
        }
        List<DictClass> sameClasses = patriarchClasses;
        sameClasses.retainAll(teacherClasses);
        if (sameClasses.size() <= 1)
        {
            return true;
        }
        return false;
    }

    @Override
    public List<DictStudent> findStudentByPatriarch(Member member)
    {
        // TODO Auto-generated method stub
        return patriarchStudentMapDao.findStudentByPatriarch(member);
    }

    @Override
    public List<Member> findDiaryFriends(Member member)
    {
        // TODO Auto-generated method stub
        if (member == null)
        {
            return null;
        }
        List<DictStudent> dictStudents = this.findStudentByPatriarch(member);
        if (dictStudents == null || dictStudents.size() == 0)
        {
            return null;
        }
        List<Member> friends = new ArrayList<Member>();
        for (DictStudent dictStudent : dictStudents)
        {
            DictClass dictClass = dictStudent.getDictClass();
            if (dictClass != null)
            {
                List<Member> teachers = classTeacherMapService.findMemberByClass(dictClass);
                List<Member> patriarches = new ArrayList<Member>();
                Set<DictStudent> students = dictClass.getDictStudents();
                if (students != null)
                {
                    for (DictStudent student : students)
                    {
                        patriarches.addAll(this.findMemberByStudent(student));
                    }
                }
                friends.addAll(patriarches);
                friends.addAll(teachers);
            }
        }
        // 去重复
        HashSet<Member> hashSet = new HashSet<Member>(friends);
        friends.clear();
        friends.addAll(hashSet);
        return friends;
    }

}
