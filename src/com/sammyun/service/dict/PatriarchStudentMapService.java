package com.sammyun.service.dict;

import java.util.List;

import com.sammyun.entity.Member;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.dict.PatriarchStudentMap;
import com.sammyun.service.BaseService;

/**
 * PatriarchStudentMap * Service - 学生家长对应列表
 * 
 * @version [版本号, Apr 9, 2015]
 */
public interface PatriarchStudentMapService extends BaseService<PatriarchStudentMap, Long>
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
    public PatriarchStudentMap findStudentByMember(Member member, DictStudent dictStudent);

    /**
     * 根据学生获取对应的学生家长列表
     * 
     * @param dictStudent
     */
    public List<PatriarchStudentMap> findByStudent(DictStudent dictStudent);

    /**
     * 根据学生获得相关联的家长列表
     * 
     * @param dictStudent
     * @return
     */
    public List<Member> findMemberByStudent(DictStudent dictStudent);

    /**
     * 根据家长查找到相关学生 <功能详细描述>
     * 
     * @param member
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<DictStudent> findStudentByPatriarch(Member member);

    /**
     * 根据学生来创建家长学生关系
     * 
     * @param dictStudents
     * @return
     */
    public List<PatriarchStudentMap> createMapsByStudents(List<DictStudent> dictStudents);

    /**
     * 根据班级查询班级下的相关家长
     * 
     * @param dictClass
     * @return
     */
    public List<Member> findPatriarchByClass(DictClass dictClass);

    /**
     * 判断两个家长间路线是否唯一（是否只有一个孩子能关联到这两个家长）
     * 
     * @param one
     * @param two
     * @return
     */
    public Boolean twoPatriarchUniqueInStudent(Member one, Member two);

    /**
     * 判断家长和老师之间的路线是否唯一(是否只有一个班级关联两者)
     * 
     * @param patriarch
     * @param teacher
     * @return 示意图： 家长A ｜ 两个小孩 / \ 班级1 班级2 \ / 老师B
     */
    public Boolean patriarchTeacherUnique(Member patriarch, Member teacher);
    
    /** 根据家长查询出相关小孩班级的家长和老师 */
    public List<Member> findDiaryFriends(Member member);
    
}
