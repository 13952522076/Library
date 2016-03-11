/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sammyun.Setting;
import com.sammyun.controller.console.ExcelController;
import com.sammyun.dao.ExcelDao;
import com.sammyun.dao.MemberDao;
import com.sammyun.dao.dict.DictClassDao;
import com.sammyun.dao.dict.DictStudentDao;
import com.sammyun.entity.ExcelMessage;
import com.sammyun.entity.ExcelMessage.Status;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.course.CurriculumSchedule;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.dict.DictStudent.StudentStatus;
import com.sammyun.service.AdminService;
import com.sammyun.service.ExcelService;
import com.sammyun.service.MemberService;
import com.sammyun.util.EduUtil;
import com.sammyun.util.SettingUtils;

/**
 * Service - Excel导入
 * 


 */
@Service("excelServiceImpl")
public class ExcelServiceImpl implements ExcelService
{
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @Resource(name = "memberDaoImpl")
    private MemberDao memberDao;

    @Resource(name = "dictClassDaoImpl")
    private DictClassDao dictClassDao;

    @Resource(name = "dictStudentDaoImpl")
    private DictStudentDao dictStudentDao;

    @Resource(name = "excelDaoImpl")
    private ExcelDao excelDao;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    private static HSSFWorkbook courseWorkbook;

    @Override
    public List<Member> getMembersByExcel(InputStream is) throws Exception
    {
        // TODO Auto-generated method stub
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
        List<Member> members = new ArrayList<Member>();
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++)
        {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null)
            {
                continue;
            }
            int infoSize = hssfSheet.getRow(0).getLastCellNum();// 获取有多少列
            int rowSize = hssfSheet.getLastRowNum();// 获取有多少条数据
            // 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
            {
                HSSFRow memberInfo = hssfSheet.getRow(rowNum);
                Member member = new Member();
                String username = null;
                String mobile = null;
                String realName = null;
                for (int i = 0; i < memberInfo.getLastCellNum(); i++)
                {
                    HSSFCell memberCell = memberInfo.getCell(i);
                    if (i == 0)
                    {
                        // 用户名
                        username = memberCell.toString();
                    }
                    if (i == 1)
                    {
                        // 手机
                        mobile = memberCell.toString();
                    }
                    if (i == 2)
                    {
                        // 真实姓名
                        realName = memberCell.toString();
                    }
                }
                // 判断这几个属性是否有空值
                if (username != null)
                {
                    member.setUsername(username);
                }
                else
                {
                    String errorString = "第" + rowNum + "条纪录的用户名有误！";
                    return null;
                }
                if (mobile != null)
                {
                    member.setMobile(mobile);
                }
                else
                {
                    String errorString = "第" + rowNum + "条纪录的手机号有误！";
                    return null;
                }
                if (realName != null)
                {
                    member.setRealName(realName);
                }
                else
                {
                    String errorString = "第" + rowNum + "条纪录的真实姓名有误！";
                    return null;
                }
                members.add(member);

            }
        }
        return members;
    }

    @Override
    public List<Member> getMembers(InputStream inputStream, DictSchool dictSchool, MemberType memberType,
            HttpServletRequest request) throws Exception
    {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        List<Member> members = new ArrayList<Member>();
        // start——循环工作表
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++)
        {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null)
            {
                continue;
            }
            // start—— 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
            {
                HSSFRow memberInfo = hssfSheet.getRow(rowNum);
                if (memberInfo == null)
                {
                    continue;
                }
                Member member = new Member();
                member.setMemberType(memberType);
                member.setCreateDate(new Date());
                member.setModifyDate(new Date());
                member.setIsEnabled(true);
                member.setIsLocked(false);
                member.setLoginFailureCount(0);
                Setting setting = SettingUtils.get();
                member.setPassword(DigestUtils.md5Hex(setting.getInitPassword()));
                member.setPoint(0L);
                member.setRegisterIp(EduUtil.getAddr(request));
                member.setSignature("");
                member.setValidateCodeNumber(0);
                member.setDictSchool(dictSchool);
                // member.setIsUpdate(true);
                member.setIsAcceptLeaveInfo(true);
                String mobile = null;
                String realName = null;
                // start——遍历列
                for (int i = 0; i < memberInfo.getLastCellNum(); i++)
                {
                    HSSFCell memberCell = memberInfo.getCell(i);
                    if (memberCell == null)
                    {
                        continue;
                    }
                    // if (i == 0)
                    // {
                    // // 用户名
                    // username = memberCell.toString();
                    // username = username.trim();
                    // member.setUsername(username);
                    // }
                    if (i == 0)
                    {
                        // 手机
                        mobile = memberCell.toString();
                        member.setMobile(mobile);
                        member.setUsername(mobile);
                    }
                    if (i == 1)
                    {
                        // 真实姓名
                        realName = memberCell.toString();
                        realName = realName.trim();
                        member.setRealName(realName);
                    }

                }
                // end——遍历列
                members.add(member);
            }
            // end——循环row
        }
        // end——循环工作表

        return members;
    }

    @Override
    public ExcelMessage validateMembers(List<Member> members)
    {
        ExcelMessage ret = new ExcelMessage();
        if (members.size() == 0)
        {
            ret.setStatus(Status.fail);
            ret.setError("会员列表为空");
            return ret;
        }
        // 先自身判断关键属性是否重复
        ExcelMessage selfValidate = excelDao.validateMemberSelf(members);
        if (selfValidate.getStatus().toString().equals("fail"))
        {
            return selfValidate;
        }
        // start 循环 判断数据库
        for (int i = 0; i < members.size(); i++)
        {
            int num = i + 1;
            Member member = members.get(i);
            // 判断用户名
            if (member.getUsername() != null)
            {
                if (memberDao.usernameExists(member.getUsername()))
                {
                    ret.setStatus(Status.fail);
                    ret.setError("第" + num + "条纪录的用户名存在！");
                    return ret;
                }
                else
                {
                    if (member.getUsername().length() > 100)
                    {
                        ret.setStatus(Status.fail);
                        ret.setError("第" + num + "条纪录的用户名长度超过100！");
                        return ret;
                    }
                }
            }
            else
            {
                ret.setStatus(Status.fail);
                ret.setError("第" + num + "条纪录的用户名不能为空！");
                return ret;
            }
            // 判断手机号
            if (member.getMobile() != null)
            {
                if (memberDao.mobileUnique(member.getMobile()))
                {
                    ret.setStatus(Status.fail);
                    ret.setError("第" + num + "条纪录的手机号存在！");
                    return ret;
                }
                else
                {
                    if (!EduUtil.isMobile(member.getMobile()))
                    {
                        ret.setStatus(Status.fail);
                        ret.setError("第" + num + "条纪录的手机号格式不正确！");
                        return ret;
                    }
                }
            }
            else
            {
                ret.setStatus(Status.fail);
                ret.setError("第" + num + "条纪录的手机号不能为空！");
                return ret;
            }
            // 真实姓名
            if (member.getRealName() == null)
            {
                ret.setStatus(Status.fail);
                ret.setError("第" + num + "条纪录的真实姓名不能为空！");
                return ret;
            }
        }
        // end 循环 判断数据库

        ret.setOk("共插入" + members.size() + "条纪录");
        ret.setStatus(Status.success);
        return ret;
    }

    /**
     * 获取从excel导入的课程 <功能详细描述>
     * 
     * @param in
     * @return
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    @Override
    public List<CurriculumSchedule> getCourses(SchoolYearMng schoolYearMng, DictClass dictClass)
    {

        List<CurriculumSchedule> courses = new ArrayList<CurriculumSchedule>();
        // start循环工作表
        for (int numSheet = 0; numSheet < courseWorkbook.getNumberOfSheets(); numSheet++)
        {
            HSSFSheet hssfSheet = courseWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null)
            {
                continue;
            }
            // start—— 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
            {
                HSSFRow courseInfo = hssfSheet.getRow(rowNum);
                CurriculumSchedule course = new CurriculumSchedule();
                int week = 1;
                String teacherName = null;
                String courseName = null;
                String lessons = null;
                String classRoom = null;
                String startTime = null;
                String endTime = null;
                // start——遍历列
                for (int i = 0; i < courseInfo.getLastCellNum(); i++)
                {
                    HSSFCell courseCell = courseInfo.getCell(i);
                    if (i == 0)
                    {
                        // 星期
                        String weekString = courseCell.toString();
                        week = Integer.parseInt(weekString);
                        course.setWeek(week);
                    }
                    if (i == 1)
                    {
                        // 授课老师
                        teacherName = courseCell.toString();
                        course.setTeacherName(teacherName);
                    }
                    if (i == 2)
                    {
                        // 课程名
                        courseName = courseCell.toString();
                        course.setCourseName(courseName);
                    }
                    if (i == 3)
                    {
                        // 课节
                        lessons = courseCell.toString();
                        course.setLessons(lessons);
                    }
                    if (i == 4)
                    {
                        // 教室
                        if (courseCell != null)
                        {
                            classRoom = courseCell.toString();
                            course.setClassRoom(classRoom);
                        }
                    }
                    if (i == 5)
                    {
                        // 开始时间

                        startTime = courseCell.toString();
                        if (startTime.contains("："))
                        {
                            startTime = startTime.replace('：', ':');
                        }
                        course.setStartTime(startTime);
                    }
                    if (i == 6)
                    {
                        // 结束时间
                        endTime = courseCell.toString();
                        if (endTime.contains("："))
                        {
                            endTime = endTime.replace('：', ':');
                        }
                        course.setEndTime(endTime);
                    }
                }
                // end——遍历列
                course.setDictClass(dictClass);
                course.setSchoolYearMng(schoolYearMng);
                courses.add(course);
            }
            // end—— 循环行Row
        }
        // end循环工作表

        return courses;
    }

    /**
     * 验证课程表的week和课节 <功能详细描述>
     * 
     * @param in
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    @Override
    public ExcelMessage validateCourseWeekLessions(InputStream is) throws Exception
    {

        courseWorkbook = new HSSFWorkbook(is);
        ExcelMessage excelMessage = new ExcelMessage();
        excelMessage.setStatus(Status.success);
        // start循环工作表
        for (int numSheet = 0; numSheet < courseWorkbook.getNumberOfSheets(); numSheet++)
        {
            HSSFSheet hssfSheet = courseWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null)
            {
                continue;
            }
            // 判断是否选对表
            HSSFRow titleInfo = hssfSheet.getRow(0);
            ExcelMessage titleValidate = new ExcelMessage();
            titleValidate.setStatus(Status.fail);
            titleValidate.setError("请检查模版正确！标题栏不对应。");
            if ((titleInfo.getCell(0) == null) || (!titleInfo.getCell(0).toString().equals("星期")))
            {
                return titleValidate;
            }
            if ((titleInfo.getCell(1) == null) || (!titleInfo.getCell(1).toString().equals("授课老师")))
            {
                return titleValidate;
            }

            // start—— 循环行Row
            System.out.println(hssfSheet.getLastRowNum());
            if (hssfSheet.getLastRowNum() == 0)
            {
                excelMessage.setStatus(Status.fail);
                excelMessage.setError("无数据。");
                is.close();
                return excelMessage;
            }
            // 对week判断
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
            {
                HSSFRow courseInfo = hssfSheet.getRow(rowNum);
                if (courseInfo == null)
                {
                    continue;
                }
                if (courseInfo.getCell(0) == null)
                {
                    excelMessage.setStatus(Status.fail);
                    excelMessage.setError("第" + rowNum + "条记录的星期未填写。");
                    is.close();
                    return excelMessage;
                }

                String weekString = courseInfo.getCell(0).toString();
                try
                {
                    int week = Integer.parseInt(weekString);
                    if (week == 1)
                    {
                        continue;
                    }
                    else if (week == 2)
                    {
                        continue;
                    }
                    else if (week == 3)
                    {
                        continue;
                    }
                    else if (week == 4)
                    {
                        continue;
                    }
                    else if (week == 5)
                    {
                        continue;
                    }
                    else if (week == 6)
                    {
                        continue;
                    }
                    else if (week == 7)
                    {
                        continue;
                    }
                    else
                    {
                        excelMessage.setStatus(Status.fail);
                        excelMessage.setError("第" + rowNum + "条记录的星期填写错误，请填写1-7的数字");
                        return excelMessage;
                    }

                }
                catch (Exception e)
                {
                    logger.error("第" + rowNum + "条记录的星期填写错误" + e.getMessage());
                    excelMessage.setStatus(Status.fail);
                    excelMessage.setError("第" + rowNum + "条记录的星期填写错误，请填写1-7的数字");
                    is.close();
                    return excelMessage;
                }
            }
            // 对lessons判断
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
            {
                HSSFRow courseInfo = hssfSheet.getRow(rowNum);
                if (courseInfo == null)
                {
                    continue;
                }
                if (courseInfo.getCell(3) == null)
                {
                    excelMessage.setStatus(Status.fail);
                    excelMessage.setError("第" + rowNum + "条记录的课节未填写。");
                    is.close();
                    return excelMessage;
                }
                String lessonString = courseInfo.getCell(3).toString();
                try
                {
                    int lesson = Integer.parseInt(lessonString);
                    if (lesson == 1)
                    {
                        continue;
                    }
                    else if (lesson == 2)
                    {
                        continue;
                    }
                    else if (lesson == 3)
                    {
                        continue;
                    }
                    else if (lesson == 4)
                    {
                        continue;
                    }
                    else if (lesson == 5)
                    {
                        continue;
                    }
                    else if (lesson == 6)
                    {
                        continue;
                    }
                    else if (lesson == 7)
                    {
                        continue;
                    }
                    else if (lesson == 8)
                    {
                        continue;
                    }
                    else
                    {
                        excelMessage.setStatus(Status.fail);
                        excelMessage.setError("第" + rowNum + "条记录的课节填写错误，请填写1-8的数字");
                        is.close();
                        return excelMessage;
                    }

                }
                catch (Exception e)
                {
                    logger.error("第" + rowNum + "条记录的课节填写错误" + e.getMessage());
                    excelMessage.setStatus(Status.fail);
                    excelMessage.setError("第" + rowNum + "条记录的课节填写错误，请填写1-8的数字");
                    is.close();
                    return excelMessage;
                }
            }
            // start 对课程名做判断
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
            {
                HSSFRow courseInfo = hssfSheet.getRow(rowNum);
                if (courseInfo == null)
                {
                    continue;
                }
                if (courseInfo.getCell(2) == null)
                {
                    excelMessage.setStatus(Status.fail);
                    excelMessage.setError("第" + rowNum + "条记录的课程名未填写。");
                    is.close();
                    return excelMessage;
                }

            }
            // end 对课程名做判断

        }
        return excelMessage;
    }

    @Override
    public List<Member> getTeachers(InputStream inputStream, DictSchool dictSchool, MemberType memberType,
            HttpServletRequest request) throws Exception
    {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        List<Member> members = new ArrayList<Member>();
        // start——循环工作表
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++)
        {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null)
            {
                continue;
            }
            int infoSize = hssfSheet.getRow(0).getLastCellNum();// 获取有多少列
            int rowSize = hssfSheet.getLastRowNum();// 获取有多少条数据
            // start—— 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
            {
                HSSFRow memberInfo = hssfSheet.getRow(rowNum);
                if (memberInfo == null)
                {
                    continue;
                }
                Member member = new Member();
                member.setMemberType(memberType);
                member.setCreateDate(new Date());
                member.setModifyDate(new Date());
                member.setIsEnabled(true);
                member.setIsLocked(false);
                member.setLoginFailureCount(0);
                Setting setting = SettingUtils.get();
                member.setPassword(DigestUtils.md5Hex(setting.getInitPassword()));
                member.setPoint(0L);
                member.setRegisterIp(EduUtil.getAddr(request));
                member.setSignature("");
                member.setValidateCodeNumber(0);
                member.setDictSchool(dictSchool);
                // member.s(true);
                member.setIsAcceptLeaveInfo(true);
                String mobile = null;
                String realName = null;
                // start——遍历列
                for (int i = 0; i < memberInfo.getLastCellNum(); i++)
                {
                    HSSFCell memberCell = memberInfo.getCell(i);
                    if (memberCell == null)
                    {
                        continue;
                    }
                    // if (i == 0)
                    // {
                    // // 用户名
                    // username = memberCell.toString();
                    // username = username.trim();
                    // member.setUsername(username);
                    // }
                    if (i == 0)
                    {
                        // 手机
                        mobile = memberCell.toString();
                        member.setMobile(mobile);
                        member.setUsername(mobile);
                    }
                    if (i == 1)
                    {
                        // 真实姓名
                        realName = memberCell.toString();
                        realName = realName.trim();
                        member.setRealName(realName);
                    }
                    if (i == 2)
                    {
                        // 老师班级关系-暂存在地址中
                        String classTeacherMap = memberCell.toString();
                        classTeacherMap = classTeacherMap.trim();
                        member.setAddress(classTeacherMap);
                    }

                }
                // end——遍历列
                members.add(member);
            }
            // end——循环row
        }
        // end——循环工作表

        return members;
    }

    @Override
    public ExcelMessage validateTeachers(List<Member> members)
    {
        ExcelMessage ret = new ExcelMessage();
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        if (members.size() == 0)
        {
            ret.setStatus(Status.fail);
            ret.setError("会员列表为空");
            return ret;
        }
        // 先自身判断关键属性是否重复
        ExcelMessage selfValidate = excelDao.validateMemberSelf(members);
        if (selfValidate.getStatus().toString().equals("fail"))
        {
            return selfValidate;
        }
        // start 循环 判断数据库
        for (int i = 0; i < members.size(); i++)
        {
            int num = i + 1;
            Member member = members.get(i);
            // 判断用户名
            if (member.getUsername() != null)
            {
                if (memberDao.usernameExists(member.getUsername()))
                {
                    ret.setStatus(Status.fail);
                    ret.setError("第" + num + "条纪录的用户名存在！");
                    return ret;
                }
                else
                {
                    if (member.getUsername().length() > 100)
                    {
                        ret.setStatus(Status.fail);
                        ret.setError("第" + num + "条纪录的用户名长度超过100！");
                        return ret;
                    }
                }
            }
            else
            {
                ret.setStatus(Status.fail);
                ret.setError("第" + num + "条纪录的用户名不能为空！");
                return ret;
            }
            // 判断手机号
            if (member.getMobile() != null)
            {
                if (memberDao.mobileUnique(member.getMobile()))
                {
                    ret.setStatus(Status.fail);
                    ret.setError("第" + num + "条纪录的手机号存在！");
                    return ret;
                }
                else
                {
                    if (!EduUtil.isMobile(member.getMobile()))
                    {
                        ret.setStatus(Status.fail);
                        ret.setError("第" + num + "条纪录的手机号格式不正确！");
                        return ret;
                    }
                }
            }
            else
            {
                ret.setStatus(Status.fail);
                ret.setError("第" + num + "条纪录的手机号不能为空！");
                return ret;
            }
            // 真实姓名
            if (member.getRealName() == null)
            {
                ret.setStatus(Status.fail);
                ret.setError("第" + num + "条纪录的真实姓名不能为空！");
                return ret;
            }
            // 关联的班级
            if (member.getAddress() != null)
            {
                ret.setStatus(Status.fail);
                String classNameString = member.getAddress();
                classNameString = classNameString.replaceAll("，", ",");
                String[] classNames = classNameString.split(",");
                for (String className : classNames)
                {
                    List<DictClass> dictClasses = dictClassDao.getClassByName(className.trim(), dictSchool);
                    if (dictClasses == null)
                    {
                        ret.setError("第" + num + "条纪录的下的 " + className.trim() + " 班级不存在");
                        return ret;
                    }
                    else if (dictClasses.size() == 0)
                    {
                        ret.setError("第" + num + "条纪录的下的 " + className.trim() + " 班级不存在");
                        return ret;
                    }

                }
            }
        }
        // end 循环 判断数据库
        ret.setOk("共插入" + members.size() + "条纪录");
        ret.setStatus(Status.success);
        return ret;
    }

    @Override
    public List<DictStudent> getDictStudents(InputStream inputStream, DictSchool dictSchool, HttpServletRequest request)
            throws Exception
    {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);
        List<DictStudent> dictStudents = new ArrayList<DictStudent>();
        // start--循环工作表
        for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++)
        {
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
            if (hssfSheet == null)
            {
                continue;
            }
            int infoSize = hssfSheet.getRow(0).getLastCellNum();// 获取有多少列
            int rowSize = hssfSheet.getLastRowNum();// 获取有多少条数据
            // start—— 循环行Row
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
            {
                HSSFRow studentInfo = hssfSheet.getRow(rowNum);
                if (studentInfo == null)
                {
                    continue;
                }
                DictStudent dictStudent = new DictStudent();
                dictStudent.setCreateDate(new Date());
                dictStudent.setModifyDate(new Date());
                dictStudent.setStudentStatus(StudentStatus.active);
                String studentName = null;
                String studentNo = null;
                DictClass dictClass = new DictClass();
                // start_遍历列
                for (int i = 0; i < studentInfo.getLastCellNum(); i++)
                {
                    HSSFCell studentCell = studentInfo.getCell(i);
                    if (studentCell == null)
                    {
                        continue;
                    }
                    if (i == 0)
                    {
                        // 学号
                        studentNo = studentCell.toString();
                        studentNo = studentNo.trim();
                        dictStudent.setStudentNo(studentNo);
                    }
                    if (i == 1)
                    {
                        // 学生姓名
                        studentName = studentCell.toString();
                        studentName = studentName.trim();
                        dictStudent.setStudentName(studentName);
                    }
                    if (i == 2)
                    {
                        // 班级
                        String className = studentCell.toString();
                        className = className.trim();
                        List<DictClass> classes = dictClassDao.getClassByName(className, dictSchool);
                        if (classes != null)
                        {
                            if (classes.size() > 0)
                            {
                                dictClass = classes.get(0);
                                dictStudent.setDictClass(dictClass);
                            }
                        }

                    }
                    if (i == 3)
                    {
                        // 相关班级
                        String memberString = studentCell.toString();
                        memberString = memberString.trim();
                        memberString = memberString.replaceAll("，", ",");
                        dictStudent.setStuRmark(memberString);
                    }
                }
                // end___遍历列
                dictStudents.add(dictStudent);
            }
        }
        // end ——循环工作表
        return dictStudents;
    }

    @Override
    public ExcelMessage validateDictStudents(List<DictStudent> dictStudents)
    {
        ExcelMessage ret = new ExcelMessage();
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        if (dictStudents.size() == 0)
        {
            ret.setStatus(Status.fail);
            ret.setError("学生列表为空");
            return ret;
        }
        // 先自身判断关键属性是否重复
        ExcelMessage selfValidate = excelDao.validateStudentSelf(dictStudents);
        if (selfValidate.getStatus().toString().equals("fail"))
        {
            return selfValidate;
        }
        // start 循环 判断数据库
        for (int i = 0; i < dictStudents.size(); i++)
        {
            int num = i + 1;
            DictStudent dictStudent = dictStudents.get(i);
            // 判断学号
            if (dictStudent.getStudentName() != null)
            {
                if (dictStudentDao.studentNoExists(dictStudent.getStudentNo(), dictSchool.getDictClasses()))
                {
                    ret.setStatus(Status.fail);
                    ret.setError("第" + num + "条纪录的学号存在！");
                    return ret;
                }
                else
                {

                }
            }
            else
            {
                ret.setStatus(Status.fail);
                ret.setError("第" + num + "条纪录的学号不能为空！");
                return ret;
            }
            // 判断班级
            if (dictStudent.getDictClass() == null)
            {
                ret.setStatus(Status.fail);
                ret.setError("第" + num + "条纪录下的班级查找不到！");
                return ret;
            }
            // 判断关联的家长是否存在
            if (dictStudent.getStuRmark() != null)
            {
                ret.setStatus(Status.fail);
                String memberString = dictStudent.getStuRmark();
                memberString = memberString.replaceAll("，", ",");
                String[] memberUseNames = memberString.split(",");
                for (String memberUseName : memberUseNames)
                {
                    // memberUseName.trim();
                    Member member = memberService.findByUsername(memberUseName.trim());
                    if (member == null)
                    {
                        ret.setError("第" + num + "条纪录的下的 " + memberUseName.trim() + " 家长不存在");
                        return ret;
                    }
                }
            }
        }
        // end 循环 判断数据库
        ret.setOk("共插入" + dictStudents.size() + "条纪录");
        ret.setStatus(Status.success);
        return ret;
    }

}
