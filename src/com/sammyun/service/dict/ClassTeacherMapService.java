package com.sammyun.service.dict;

import java.util.List;

import com.sammyun.entity.Member;
import com.sammyun.entity.dict.ClassTeacherMap;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictClass.ClassStatus;
import com.sammyun.service.BaseService;

/**
 * ClassTeacherMap * Service - 班级老师对应列表
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface ClassTeacherMapService extends BaseService<ClassTeacherMap, Long> 
{
    
    /**
     * 根据学校，老师，班级状态查询班级
     */
    public List<ClassTeacherMap> findClassesBySchoolAndMember (DictSchool school,Member member,ClassStatus classStatus);

    /**
     * 根据班级查询导对应的老师列表
     * @param dictclass
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
    
    /**
     * 根据老师数组生成对应关系
     * @param members
     * @return
     */
    List<ClassTeacherMap> createMapbyMembers(List<Member> members);
    
    /**
     * 根据老师查找出相关班级下的相应的家长和老师
     * <功能详细描述>
     * @param member
     * @return
     * @see [类、类#方法、类#成员]
     */
    List<Member> findDiaryMembers(Member member);
}
