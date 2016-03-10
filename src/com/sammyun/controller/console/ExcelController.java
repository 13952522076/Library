/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sammyun.Pageable;
import com.sammyun.entity.Admin;
import com.sammyun.entity.ExcelMessage;
import com.sammyun.entity.ExcelMessage.Status;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.course.CurriculumSchedule;
import com.sammyun.entity.course.SchoolYearMng;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.ExcelService;
import com.sammyun.service.MemberService;
import com.sammyun.service.course.CurriculumScheduleService;
import com.sammyun.service.course.SchoolYearMngService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.EduConstants;
import com.sammyun.util.EduUtil;
import com.sammyun.util.ExcelImportUtil;

/**
 * Controller - excel文件导入导出
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("excelController")
@RequestMapping("/console/excel")
public class ExcelController extends BaseController
{

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ExcelController.class);

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    @Resource(name = "excelServiceImpl")
    private ExcelService excelService;

    /** 管理员service */
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    /** 学校service */
    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    /** 用户service */
    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    /** 校历service */
    @Resource(name = "schoolYearMngServiceImpl")
    private SchoolYearMngService schoolYearMngService;

    /** 课程表service */
    @Resource(name = "curriculumScheduleServiceImpl")
    private CurriculumScheduleService curriculumScheduleService;

    /**
     * 导入老师/家长
     * 
     * @param attendanceId 学生考勤ID
     * @param response
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/import_member", method = RequestMethod.POST)
    public String importMember(ModelMap model, MemberType memberType, Pageable pageable, MultipartFile file,
            HttpServletRequest request) throws Exception
    {
        if (null == memberType)
        {
            memberType = MemberType.teacher;
        }
        ExcelMessage excelMessage = new ExcelMessage();
        excelMessage.setUrl(request.getContextPath() + "/console/member/list.ct");
        excelMessage.setStatus(com.sammyun.entity.ExcelMessage.Status.fail);
        if (file == null)
        {
            excelMessage.setError("导入文件为空文件！");
            model.addAttribute("excelMessage", excelMessage);
            return "/console/common/excelImportResult";
        }
        InputStream inputStream = ExcelImportUtil.getInputStream(file);
        if (inputStream == null)
        {
            excelMessage.setError("读取文件失败！");
            model.addAttribute("excelMessage", excelMessage);
            return "/console/common/excelImportResult";
        }
        // 从service获得members
        List<Member> members = new ArrayList<Member>();
        // excelService.getMembers(inputStream);
        // 验证members
        excelMessage = excelService.validateMembers(members);
        // start 判断是否通过验证，循环插入
        if (excelMessage.getStatus().toString().equals("success"))
        {
            Admin admin = adminService.getCurrent();
            DictSchool dictSchool = admin.getDictSchool();
            List<Member> memberList = new ArrayList<Member>();
            for (int i = 0; i < members.size(); i++)
            {
                Member member = members.get(i);
                member.setMemberType(memberType);
                member.setCreateDate(new Date());
                member.setModifyDate(new Date());
                member.setIsEnabled(true);
                member.setIsLocked(false);
                member.setLoginFailureCount(0);
                try
                {
                    String password = member.getEmail().substring(member.getEmail().length() - 6,
                            member.getEmail().length());
                    member.setPassword(DigestUtils.md5Hex(password));
                }
                catch (Exception e)
                {
                    member.setPassword(DigestUtils.md5Hex("111111"));
                }
                member.setPoint(0L);
                member.setRegisterIp(EduUtil.getAddr(request));
                member.setSignature("");
                member.setDictSchool(dictSchool);
                // start这里try catch为了保存失败时删除保存的数据
                try
                {
                    member.setValidateCodeNumber(0);
                    memberService.save(member);
                    memberList.add(member);
                }
                catch (Exception e)
                {
                    excelMessage.setStatus(com.sammyun.entity.ExcelMessage.Status.fail);
                    excelMessage.setError("第" + (i + 1) + "条数据保存出错");
                    logger.error("ExcelController import_member 保存出错: " + e.getMessage());
                    if (memberList.size() > 0)
                    {
                        for (int n = 0; n < memberList.size();)
                        {
                            memberService.delete(memberList.get(n));
                        }
                    }
                    return "/console/common/excelImportResult";
                }
                // end这里try catch为了保存失败时删除保存的数据
            }
        }
        // end 判断是否通过验证，循环插入
        excelMessage.setUrl(request.getContextPath() + "/console/member/list.ct");
        model.addAttribute("excelMessage", excelMessage);
        return "/console/common/excelImportResult";
    }

    /**
     * 实现下载模版功能 <功能详细描述>
     * 
     * @param fileName
     * @return
     * @throws IOException
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
    public ModelAndView download(String fileName, HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        String path = EduConstants.excelTemplate + fileName;
        try
        {
            EduUtil.download(request, response, path, "application/octet-stream", fileName);
        }
        catch (Exception e)
        {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * 批量上传课程表 <功能详细描述>
     * 
     * @param model
     * @param schoolYearMngId
     * @param classId
     * @param file
     * @param request
     * @return
     * @throws Exception
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/import_course", method = RequestMethod.POST)
    public @ResponseBody
    ExcelMessage importCourse(ModelMap model, Long schoolYearMngId, Long classId, MultipartFile file,
            HttpServletRequest request) throws Exception
    {
        // 定义excelMessage返回给新页面
        ExcelMessage excelMessage = new ExcelMessage();
        excelMessage.setStatus(com.sammyun.entity.ExcelMessage.Status.fail);
        excelMessage.setUrl(request.getContextPath() + "/console/curriculum_schedule/list.ct");
        // 获取默认的校历，班级
        SchoolYearMng schoolYearMng = schoolYearMngService.find(schoolYearMngId);
        DictClass dictClass = dictClassService.find(classId);
        // 判断为空
        if (file == null)
        {
            excelMessage.setError("导入文件为空文件！");
            return excelMessage;
           // model.addAttribute("excelMessage", excelMessage);
          //  return "/console/common/excelImportResult";
        }
        InputStream is = ExcelImportUtil.getInputStream(file);
        if (is == null)
        {
            excelMessage.setError("读取文件失败！");
            return excelMessage;
            // model.addAttribute("excelMessage", excelMessage);
            //  return "/console/common/excelImportResult";
        }
        // 判断星期，课节填写是否有误
        ExcelMessage intExcelMessage = excelService.validateCourseWeekLessions(is);
        if (intExcelMessage.getStatus() == Status.fail)
        {
            intExcelMessage.setUrl(request.getContextPath() + "/console/curriculum_schedule/list.ct");
            return intExcelMessage;
            //model.addAttribute("excelMessage", intExcelMessage);
           // return "/console/common/excelImportResult";
        }
        // 从service获得课程列表
        List<CurriculumSchedule> courses = excelService.getCourses(schoolYearMng, dictClass);

        // 保存，保存出错删除
        List<CurriculumSchedule> addedCourses = new ArrayList<CurriculumSchedule>();
        for (int i = 0; i < courses.size(); i++)
        {
            CurriculumSchedule course = courses.get(i);
            try
            {
                curriculumScheduleService.save(course);
                addedCourses.add(course);
            }
            catch (Exception e)
            {
                excelMessage.setStatus(com.sammyun.entity.ExcelMessage.Status.fail);
                excelMessage.setError("第" + (i + 1) + "条数据保存出错");
                logger.error("ExcelController import_course 保存出错: " + e.getMessage());
                if (addedCourses.size() > 0)
                {
                    for (int n = 0; n < addedCourses.size();)
                    {
                        curriculumScheduleService.delete(addedCourses.get(n));
                    }
                }
                return excelMessage;
                //model.addAttribute("excelMessage", excelMessage);
          //      return "/console/common/excelImportResult";
            }
        }

        excelMessage.setStatus(com.sammyun.entity.ExcelMessage.Status.success);
        excelMessage.setOk("共插入" + courses.size() + "条数据");
        return excelMessage;
        //model.addAttribute("excelMessage", excelMessage);
       // return "/console/common/excelImportResult";

    }

}
