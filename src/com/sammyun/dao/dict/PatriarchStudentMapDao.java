package com.sammyun.dao.dict;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.dict.PatriarchStudentMap;

/**
 * PatriarchStudentMap * Dao - 学生家长对应列表
 * 


 */
public interface PatriarchStudentMapDao extends BaseDao<PatriarchStudentMap, Long>
{
    /**
     * 根据家长获取对应的学生家长列表
     * 
     * @param member
     */
    public List<PatriarchStudentMap> findStudentByMember(Member member);
    
    /**
     * 根据学生和家长获取对应列表
     * 
     * @param member
     */
    public PatriarchStudentMap findStudentByMember(Member member,DictStudent dictStudent);
    
    /**
     * 根据学生获取对应的学生家长列表
     * 
     * @param dictStudent
     */
    public List<PatriarchStudentMap> findByStudent(DictStudent dictStudent);
    
    /**
     * 根据学生获得相关联的家长列表
     * @param dictStudent
     * @return
     */
    public List<Member> findMemberByStudent(DictStudent dictStudent);
    
    /**
     * 根据家长获取学生列表
     * @param member
     * @return
     */
    public List<DictStudent> findStudentByPatriarch(Member member); 
    
    
}
