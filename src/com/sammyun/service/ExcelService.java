package com.sammyun.service;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sammyun.entity.ExcelMessage;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.course.CurriculumSchedule;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;

/**
 * Excel * Service - excel文件导入导出
 * 


 */
public interface ExcelService
{
    /**
     * excel导入会员信息 <功能详细描述>
     * 
     * @param in
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public List<Member> getMembersByExcel(InputStream in) throws Exception;

    /**
     * excel导入会员信息 <功能详细描述>
     * 
     * @param in
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public List<Member> getMembers(InputStream inputStream, DictSchool dictSchool, MemberType memberType,
            HttpServletRequest request) throws Exception;
    
    /**
     * excel导入老师信息 <功能详细描述>
     * 
     * @param in
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public List<Member> getTeachers(InputStream inputStream, DictSchool dictSchool, MemberType memberType,
            HttpServletRequest request) throws Exception;

    /**
     * 验证member <功能详细描述>
     * 
     * @param members
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ExcelMessage validateMembers(List<Member> members);
    
    /**
     * 验证老师 <功能详细描述>
     * 
     * @param members
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ExcelMessage validateTeachers(List<Member> members);

    /**
     * 获取从excel导入的课程 <功能详细描述>
     * 
     * @param in
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<CurriculumSchedule> getCourses(SchoolYearMng schoolYearMng, DictClass dictClass);

    /**
     * 验证课程表的week和课节 <功能详细描述>
     * 
     * @param in
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    public ExcelMessage validateCourseWeekLessions(InputStream in) throws Exception;
    
    /**
     * excel导入学生信息 <功能详细描述>
     * @param inputStream
     * @param dictSchool
     * @param memberType
     * @param request
     * @return
     * @throws Exception
     */
    public List<DictStudent> getDictStudents(InputStream inputStream, DictSchool dictSchool, 
            HttpServletRequest request) throws Exception;
    
    /**
     * 验证学生 <功能详细描述>
     * 
     * @param members
     * @return
     * @see [类、类#方法、类#成员]
     */
    public ExcelMessage validateDictStudents(List<DictStudent> dictStudents);
    
}
