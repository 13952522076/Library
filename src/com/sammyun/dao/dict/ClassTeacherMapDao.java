package com.sammyun.dao.dict;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.dict.ClassTeacherMap;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictClass.ClassStatus;

/**
 * ClassTeacherMap * Dao - 班级
 * 
 * @author Sencloud Team

 */
public interface ClassTeacherMapDao  extends BaseDao<ClassTeacherMap, Long>
{
    /**
     * 根据学校，老师，班级状态查询班级
     */
    public List<ClassTeacherMap> findClassesBySchoolAndMember (DictSchool school,Member member,ClassStatus classStatus);
    
    /**
     * 根据班级查询老师列表
     * @param dictClass
     * @return
     */
    List<Member> findMemberByClass(DictClass dictClass);
    
    /**
     * 根据老师查找出关系列表
     * @param member
     * @return
     */
    List<ClassTeacherMap> findMapByMember(Member member);
    
    /**
     * 根据老师查找出相关班级
     * @param member
     * @return
     */
    List<DictClass> findClassesByMember(Member member);
}
