/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Filter;
import com.sammyun.Filter.Operator;
import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.Setting;
import com.sammyun.entity.Admin;
import com.sammyun.entity.Area;
import com.sammyun.entity.BaseEntity.Save;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.Gender;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.dict.ClassTeacherMap;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.AreaService;
import com.sammyun.service.ExcelService;
import com.sammyun.service.MemberAttributeService;
import com.sammyun.service.MemberService;
import com.sammyun.service.dict.ClassTeacherMapService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictStudentService;
import com.sammyun.util.EduUtil;
import com.sammyun.util.SettingUtils;

/**
 * Controller - 会员-教师
 * 
 * @author Sencloud Team

 */
@Controller("adminTeacherController")
@RequestMapping("/console/teacher")
public class TeacherController extends BaseController
{

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "memberAttributeServiceImpl")
    private MemberAttributeService memberAttributeService;

    @Resource(name = "areaServiceImpl")
    private AreaService areaService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "dictStudentServiceImpl")
    private DictStudentService dictStudentService;

    @Resource(name = "excelServiceImpl")
    private ExcelService excelService;

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    @Resource(name = "classTeacherMapServiceImpl")
    private ClassTeacherMapService classTeacherMapService;

    /**
     * 检查用户名是否被禁用或已存在
     */
    @RequestMapping(value = "/check_username", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkUsername(String username)
    {
        if (StringUtils.isEmpty(username))
        {
            return false;
        }
        if (memberService.usernameDisabled(username) || memberService.usernameExists(username))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 检查E-mail是否唯一
     */
    @RequestMapping(value = "/check_email", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkEmail(String previousEmail, String email)
    {
        if (StringUtils.equalsIgnoreCase(previousEmail, email))
        {
            return true;
        }
        if (memberService.emailExists(email))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 检查E-mail是否唯一 添加页面
     */
    @RequestMapping(value = "/add_check_email", method = RequestMethod.GET)
    public @ResponseBody
    boolean addCheckEmail(String email)
    {
        if (StringUtils.isEmpty(email))
        {
            return false;
        }
        List<Member> members = memberService.findAll();
        for (int i = 0; i < members.size(); i++)
        {
            Member member = members.get(i);
            String getEmail = member.getEmail();
            if (memberService.emailUnique(getEmail, email))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查手机号是否唯一
     */
    @RequestMapping(value = "/check_mobile", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkMobile(String previousMobile, String mobile)
    {
        if (StringUtils.equalsIgnoreCase(previousMobile, mobile))
        {
            return true;
        }
        if (memberService.mobileUnique(previousMobile, mobile))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 检查手机号是否唯一 添加页面
     */
    @RequestMapping(value = "/add_check_mobileNumber", method = RequestMethod.GET)
    public @ResponseBody
    boolean addCheckMobile(String mobile)
    {
        if (StringUtils.isEmpty(mobile))
        {
            return false;
        }
        List<Member> members = memberService.findAll();
        for (int i = 0; i < members.size(); i++)
        {
            Member member = members.get(i);
            String getMobile = member.getMobile();
            if (memberService.emailUnique(getMobile, mobile))
            {
                return false;
            }
        }
        if (memberService.usernameDisabled(mobile) || memberService.usernameExists(mobile))
        {
            return false;
        }
        return true;

    }

    /**
     * 检查会员号是否被绑定了
     */
    @RequestMapping(value = "/check_vipCode", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkVipCode(String previousVipCode, String vipCode)
    {
        if (StringUtils.isEmpty(vipCode))
        {
            return true;
        }
        if (memberService.vipCodeUnique(previousVipCode, vipCode))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 检查身份证是否唯一
     */
    @RequestMapping(value = "/add_check_idCard", method = RequestMethod.GET)
    public @ResponseBody
    boolean addCheckIdCard(String idCard)
    {
        if (StringUtils.isEmpty(idCard))
        {
            return true;
        }
        if (memberService.idCardUnique(idCard))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 检查身份证是否唯一
     */
    @RequestMapping(value = "/check_idCard", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkIdCard(String previousIdCard, String idCard)
    {
        if (StringUtils.equalsIgnoreCase(previousIdCard, idCard))
        {
            return true;
        }
        if (memberService.idCardUnique(idCard))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 查看
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(Long id, ModelMap model)
    {
        model.addAttribute("genders", Gender.values());
        model.addAttribute("memberAttributes", memberAttributeService.findList());
        model.addAttribute("member", memberService.find(id));
        model.addAttribute("menuId", Member.class.getSimpleName());
        return "/console/teacher/view";
    }

    /**
     * 列表
     * 
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, MemberType memberType, String mobile, String searchName, ModelMap model,
            HttpServletRequest request) throws UnsupportedEncodingException
    {
        String meunId = Member.class.getSimpleName();
        if (null == memberType)
        {
            memberType = MemberType.teacher;
            meunId = Member.class.getSimpleName();
        }
        if (memberType.equals(MemberType.teacher))
        {
            meunId = Member.class.getSimpleName();
        }
        else if (memberType.equals(MemberType.patriarch))
        {
            meunId = "MemberPatriarch";
        }
        else
        {
            meunId = Member.class.getSimpleName();
        }
        if (mobile != null)
        {
            Filter filter = new Filter("mobile", Operator.like, "%" + mobile + "%");
            pageable.addFilters(filter);
            model.addAttribute("mobile", mobile);
        }
        if (searchName != null)
        {
            Filter realNameFilter = new Filter("realName", Operator.like, "%" + searchName + "%");
            pageable.addFilters(realNameFilter);
            model.addAttribute("searchName", searchName);
        }

        DictSchool dictSchool = adminService.getCurrentDictSchool();
        Filter dictSchoolFilter = new Filter("dictSchool", Operator.eq, dictSchool);
        Filter memberTypeFilter = new Filter("memberType", Operator.eq, memberType);
        pageable.addFilters(dictSchoolFilter);
        pageable.addFilters(memberTypeFilter);
        model.addAttribute("page", memberService.findPage(pageable));
        model.addAttribute("menuId", meunId);
        model.addAttribute("memberType", memberType);
        return "/console/teacher/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model, MemberType memberType, HttpServletRequest request)
    {
        String meunId = Member.class.getSimpleName();
        if (null == memberType)
        {
            memberType = MemberType.teacher;
            meunId = Member.class.getSimpleName();
        }
        if (memberType.equals(MemberType.teacher))
        {
            meunId = Member.class.getSimpleName();
        }
        else if (memberType.equals(MemberType.patriarch))
        {
            meunId = "MemberPatriarch";
        }
        else
        {
            meunId = Member.class.getSimpleName();
        }
        Setting setting = SettingUtils.get();
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        Set<DictClass> dictClasses = dictSchool.getDictClasses();
        model.addAttribute("dictClasses", dictClasses);
        model.addAttribute("setting", setting);
        model.addAttribute("menuId", meunId);
        model.addAttribute("memberType", memberType);
        return "/console/teacher/add";
    }

    /**
     * 保存 新增页面
     */
    @RequestMapping(value = "/add_save", method = RequestMethod.POST)
    public String addSave(Member member, HttpServletRequest request, long areaId, long[] dictClassIds,
            RedirectAttributes redirectAttributes, ModelMap model)
    {
        Admin admin = adminService.getCurrent();
        DictSchool dictSchool = admin.getDictSchool();
        member.setDictSchool(dictSchool);
        member.setUsername(member.getMobile());
        if (!isValid(member, Save.class))
        {
            return ERROR_VIEW;
        }
        Setting setting = SettingUtils.get();
        if (member.getUsername().length() < setting.getUsernameMinLength()
                || member.getUsername().length() > setting.getUsernameMaxLength())
        {
            return ERROR_VIEW;
        }
        if (member.getPassword().length() < setting.getPasswordMinLength()
                || member.getPassword().length() > setting.getPasswordMaxLength())
        {
            return ERROR_VIEW;
        }
        if (memberService.usernameDisabled(member.getUsername()) || memberService.usernameExists(member.getUsername()))
        {
            return ERROR_VIEW;
        }
        if (!setting.getIsDuplicateEmail() && memberService.emailExists(member.getEmail()))
        {
            return ERROR_VIEW;
        }
        Area area = areaService.find(areaId);
        member.setArea(area);
        member.setIsLocked(false);
        member.setLoginFailureCount(0);
        member.setRegisterIp(EduUtil.getAddr(request));// 获取IP
        member.setSignature("");// 默认空签名
        member.setCreateDate(new Date());
        member.setModifyDate(new Date());
        member.setPassword(DigestUtils.md5Hex(member.getPassword()));// 加密密码
        member.setValidateCodeNumber(0);
        // member.setIsUpdate(true);
        member.setIsAcceptLeaveInfo(true);
        memberService.save(member);
        if (dictClassIds != null && dictClassIds.length > 0)
        {
            for (long dictClassId : dictClassIds)
            {
                DictClass dictClass = dictClassService.find(dictClassId);
                ClassTeacherMap classTeacherMap = new ClassTeacherMap();
                classTeacherMap.setMember(member);
                classTeacherMap.setDictClass(dictClass);
                classTeacherMapService.save(classTeacherMap);
            }
        }
        model.addAttribute("menuId", Member.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";

    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model, MemberType memberType, HttpServletRequest request)
    {
        String meunId = Member.class.getSimpleName();
        if (null == memberType)
        {
            memberType = MemberType.teacher;
            meunId = Member.class.getSimpleName();
        }
        if (memberType.equals(MemberType.teacher))
        {
            meunId = Member.class.getSimpleName();
        }
        else if (memberType.equals(MemberType.patriarch))
        {
            meunId = "MemberPatriarch";
        }
        else
        {
            meunId = Member.class.getSimpleName();
        }
        Setting setting = SettingUtils.get();
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        Set<DictClass> dictClasses = dictSchool.getDictClasses();
        Member member = memberService.find(id);
        List<DictClass> linkClasses = new ArrayList<DictClass>();
        if (member != null)
        {
            linkClasses = classTeacherMapService.findClassesByMember(member);
        }
        model.addAttribute("linkClasses", linkClasses);
        model.addAttribute("dictClasses", dictClasses);
        model.addAttribute("setting", setting);
        model.addAttribute("member", memberService.find(id));
        model.addAttribute("menuId", meunId);
        model.addAttribute("memberType", memberType);
        return "/console/teacher/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Member member, HttpServletRequest request, RedirectAttributes redirectAttributes, long areaId,
            ModelMap model, long[] dictClassIds)
    {
        model.addAttribute("menuId", Member.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";

    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        if (ids != null)
        {
            for (Long id : ids)
            {
                Member member = memberService.find(id);
                if (member != null)
                {
                    List<ClassTeacherMap> classTeacherMaps = classTeacherMapService.findMapByMember(member);
                    if (classTeacherMaps != null)
                    {
                        if (classTeacherMaps.size() > 0)
                        {
                            for (ClassTeacherMap classTeacherMap : classTeacherMaps)
                            {
                                classTeacherMapService.delete(classTeacherMap);
                            }
                        }
                    }
                }
            }
            // memberService.delete(ids);
            List<Member> members = memberService.findList(ids);
            if (members == null || members.size() == 0)
            {
                return ERROR_MESSAGE;
            }
            for (Member member : members)
            {
                memberService.delete(member);
            }
        }

        return SUCCESS_MESSAGE;
    }

   

}
