/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sammyun.FileInfo.FileType;
import com.sammyun.Filter;
import com.sammyun.Filter.Operator;
import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.Setting;
import com.sammyun.entity.Admin;
import com.sammyun.entity.Area;
import com.sammyun.entity.BaseEntity.Save;
import com.sammyun.entity.ExcelMessage;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.Gender;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.dict.ClassTeacherMap;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.huanxin.EasemobIMUsers;
import com.sammyun.service.AdminService;
import com.sammyun.service.AreaService;
import com.sammyun.service.ExcelService;
import com.sammyun.service.MemberAttributeService;
import com.sammyun.service.MemberService;
import com.sammyun.service.dict.ClassTeacherMapService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictStudentService;
import com.sammyun.util.EduUtil;
import com.sammyun.util.ExcelImportUtil;
import com.sammyun.util.ImUserUtil;
import com.sammyun.util.JsonUtils;
import com.sammyun.util.SettingUtils;

/**
 * Controller - 会员-教师
 * 
 * @author Sencloud Team
 * @version 3.0
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

    @Resource(name = "easemobIMUsers")
    private EasemobIMUsers easemobIMUsers;

    ImUserUtil imUserUtil = new ImUserUtil();

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
        /**
         * 注册IM用户[单个]
         */
        easemobIMUsers.createUserSingle(member.getUsername(), member.getPassword().toLowerCase());
        // //将学校下所有的老师设置为好友
        // imUserUtil.newTeacherFridendInSchool(member, dictSchool);
        // //将相关班级的相关学生的相关启用家长添加好友
        // imUserUtil.newTeacherFriendWithClassPatriarch(member);
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
        //DictSchool dictSchool = adminService.getCurrentDictSchool();
        // 获取之前的用户
        Member preMember = memberService.find(member.getId());
        // 获取原相关班级
        //List<DictClass> preDictClasses = classTeacherMapService.findClassesByMember(preMember);
        if (preMember == null)
        {
            addFlashMessage(redirectAttributes, ERROR_MESSAGE);
            return ERROR_VIEW;
        }
        preMember.setRealName(member.getRealName());
        preMember.setEmail(member.getEmail());
        preMember.setGender(member.getGender());
        preMember.setBirth(member.getBirth());
        Area newArea = areaService.find(areaId);
        preMember.setArea(newArea);
        preMember.setAddress(member.getAddress());
        preMember.setZipCode(member.getZipCode());
        preMember.setPhone(member.getPhone());
        preMember.setIconPhoto(member.getIconPhoto());
        preMember.setIsEnabled(member.getIsEnabled());
        // preMember.setIsUpdate(true);
        // start设置班级教师关联
        // 先删除所有关联，再添加
        if (member != null)
        {
            List<ClassTeacherMap> classTeacherMaps = classTeacherMapService.findMapByMember(member);
            if (classTeacherMaps != null && classTeacherMaps.size() > 0)
            {
                for (ClassTeacherMap classTeacherMap : classTeacherMaps)
                {
                    classTeacherMapService.delete(classTeacherMap);
                }
            }
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
        }
        // end 设置班级教师关联
        // 手机号码未改变
        if (preMember.getMobile().equals(member.getMobile()))
        {
            // 判断是否密码改变
            if ((member.getPassword() != null) && (!member.getPassword().equals("")))
            {
                String password = DigestUtils.md5Hex(member.getPassword());
                easemobIMUsers.modifyUserPassword(preMember.getUsername(), password.toLowerCase());
                preMember.setPassword(password);
            }
            // /**
            // * 更新当前老师的关联班级相关的
            // */
            // List<DictClass> dictClasses = new ArrayList<DictClass>();
            // if(dictClassIds!=null){
            // //获取当前的相关联的班级，得到新增的和删除的。
            // List<DictClass> newDictClasses = new ArrayList<DictClass>();
            // for(Long id:dictClassIds){
            // newDictClasses.add(dictClassService.find(id));
            // }
            // List<DictClass> delDictClasses = new ArrayList<DictClass>();
            // delDictClasses.addAll(preDictClasses);
            // delDictClasses.removeAll(newDictClasses);//获取删除的班级
            // List<DictClass> addDictClasses = new ArrayList<DictClass>();
            // addDictClasses.addAll(newDictClasses);
            // addDictClasses.removeAll(preDictClasses);//获取新增的班级
            // //对删除的班级做删除处理。
            // imUserUtil.deletePatriarchFriendByTeacher(preMember,
            // delDictClasses);
            // //对新增班级做新增处理
            // imUserUtil.addPatriarchFriendByTeacher(preMember,
            // addDictClasses);
            // }
            // else{
            // //删除当前老师下相关班级的家长好友
            // imUserUtil.deletePatriarchFriendByTeacher(preMember,
            // preDictClasses);
            // }
            memberService.update(preMember);
            model.addAttribute("menuId", Member.class.getSimpleName());
            addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
            return "redirect:list.ct";
        }
        // 手机号码改变
        else
        {
            String preUserName = preMember.getUsername();
            String password = preMember.getPassword();
            preMember.setMobile(member.getMobile());
            preMember.setUsername(member.getMobile());
            if (member.getPassword() != null)
            {
                password = DigestUtils.md5Hex(member.getPassword());
                preMember.setPassword(password);
            }
            // 删除用户
            easemobIMUsers.deleteIMUserByUserPrimaryKey(preUserName);
            // 新建用户
            easemobIMUsers.createUserSingle(preMember.getUsername(), password.toLowerCase());
            // //获取相关班级
            // List<DictClass> newDictClasses = new ArrayList<DictClass>();
            // for(Long id:dictClassIds){
            // newDictClasses.add(dictClassService.find(id));
            // }
            // //添加班级下学生的家长好友
            // imUserUtil.addPatriarchFriendByTeacher(preMember,
            // newDictClasses);
            // //添加学校老师
            // imUserUtil.newTeacherFridendInSchool(preMember,dictSchool);
            // 更新老师
            memberService.update(preMember);
            model.addAttribute("menuId", Member.class.getSimpleName());
            addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
            return "redirect:list.ct";

        }

        // DictSchool dictSchool = adminService.getCurrentDictSchool();
        // member.setDictSchool(dictSchool);
        // if (!isValid(member))
        // {
        // return ERROR_VIEW;
        // }
        // Setting setting = SettingUtils.get();
        // if (member.getPassword() != null
        // && (member.getPassword().length() < setting.getPasswordMinLength()
        // || member.getPassword().length() > setting.getPasswordMaxLength()))
        // {
        // return ERROR_VIEW;
        // }
        // Member pMember = memberService.find(member.getId());
        // List<DictClass> preDictClasses =
        // classTeacherMapService.findClassesByMember(pMember);
        //
        // if (pMember == null)
        // {
        // return ERROR_VIEW;
        // }
        // pMember.setMobile(member.getMobile());
        //
        // if (StringUtils.isEmpty(member.getPassword()))
        // {
        // member.setPassword(pMember.getPassword());
        // }
        // else
        // {
        // member.setPassword(DigestUtils.md5Hex(member.getPassword()));
        // }
        // if (pMember.getIsLocked() && !member.getIsLocked())
        // {
        // member.setLoginFailureCount(0);
        // member.setLockedDate(null);
        // }
        // else
        // {
        // member.setIsLocked(pMember.getIsLocked());
        // member.setLoginFailureCount(pMember.getLoginFailureCount());
        // member.setLockedDate(pMember.getLockedDate());
        // }
        // Area area = areaService.find(areaId);
        // member.setArea(area);
        // // 如果添加修改项，这里也需要修改
        // BeanUtils.copyProperties(member, pMember, new String[] {"username",
        // "mobile", "point", "registerIp", "loginIp",
        // "loginDate", "safeKey", "payments", "signature", "createDate",
        // "dictSchool", "validateCodeNumber"});
        //
        //
        // //先删除所有关联，再添加
        // if(member!=null){
        // List<ClassTeacherMap> classTeacherMaps =
        // classTeacherMapService.findMapByMember(member);
        // if(classTeacherMaps!=null){
        // if(classTeacherMaps.size()>0){
        // for(ClassTeacherMap classTeacherMap:classTeacherMaps){
        // classTeacherMapService.delete(classTeacherMap);
        // }
        // }
        // }
        // if(dictClassIds!=null){
        // if(dictClassIds.length>0){
        // for(long dictClassId:dictClassIds){
        // DictClass dictClass = dictClassService.find(dictClassId);
        // ClassTeacherMap classTeacherMap = new ClassTeacherMap();
        // classTeacherMap.setMember(member);
        // classTeacherMap.setDictClass(dictClass);
        // classTeacherMapService.save(classTeacherMap);
        // }
        // }
        // }
        //
        // }
        //
        // /**
        // * 更新当前老师的关联班级相关的
        // */
        // List<DictClass> dictClasses = new ArrayList<DictClass>();
        // if(dictClassIds!=null){
        // //获取当前的相关联的班级，得到新增的和删除的。
        // List<DictClass> newDictClasses = new ArrayList<DictClass>();
        // for(Long id:dictClassIds){
        // newDictClasses.add(dictClassService.find(id));
        // }
        // List<DictClass> delDictClasses = new ArrayList<DictClass>();
        // delDictClasses.addAll(preDictClasses);
        // delDictClasses.removeAll(newDictClasses);//获取删除的班级
        // List<DictClass> addDictClasses = new ArrayList<DictClass>();
        // addDictClasses.addAll(newDictClasses);
        // addDictClasses.removeAll(preDictClasses);//获取新增的班级
        // //对删除的班级做删除处理。
        // imUserUtil.deletePatriarchFriendByTeacher(pMember, delDictClasses);
        // //对新增班级做新增处理
        // imUserUtil.addPatriarchFriendByTeacher(pMember, addDictClasses);
        // }
        // else{
        // //删除当前老师下相关班级的家长好友
        // imUserUtil.deletePatriarchFriendByTeacher(pMember, preDictClasses);
        // }
        //
        //
        //
        // /**
        // * 重置IM用户密码 提供管理员token
        // */
        // ObjectNode modifyIMUserPasswordWithAdminTokenNode =
        // easemobIMUsers.modifyUserPassword(pMember.getUsername(),
        // pMember.getPassword().toLowerCase());
        // if (null != modifyIMUserPasswordWithAdminTokenNode)
        // {
        // if
        // (modifyIMUserPasswordWithAdminTokenNode.get("statusCode").asText().equalsIgnoreCase("200"))
        // {
        // memberService.update(pMember, 0, adminService.getCurrent());
        // model.addAttribute("menuId", Member.class.getSimpleName());
        // addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        // return "redirect:list.ct";
        // }
        // else
        // {
        // member.setPassword(member.getPassword());
        // memberService.update(member);
        // return ERROR_VIEW;
        // }
        // }
        // else
        // {
        // member.setPassword(member.getPassword());
        // memberService.update(member);
        // return ERROR_VIEW;
        // }

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
                easemobIMUsers.deleteUserByUserPrimaryKey(member.getUsername());
                memberService.delete(member);
            }
        }

        return SUCCESS_MESSAGE;
    }

    /**
     * 批量插入老师家长信息
     * 
     * @throws Exception
     * @throws IOException
     */
    @RequestMapping(value = "/batchSaveMember", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public void batchSaveMember(FileType fileType, MultipartFile file, HttpServletResponse response, ModelMap model,
            MemberType memberType, Pageable pageable, HttpServletRequest request) throws Exception
    {
        // Date startDate = new Date();
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        if (null == memberType)
        {
            memberType = MemberType.teacher;
        }
        ExcelMessage excelMessage = new ExcelMessage();
        Map<String, Object> data = new HashMap<String, Object>();
        if (file == null)
        {
            data.put("message", Message.warn("导入文件为空文件，请检查文件是否正确！"));
        }
        InputStream inputStream = ExcelImportUtil.getInputStream(file);
        if (inputStream == null)
        {
            data.put("message", Message.warn("读取文件失败，请检查文件是否正确！"));
        }
        List<Member> members = excelService.getTeachers(inputStream, dictSchool, memberType, request);

        excelMessage = excelService.validateTeachers(members);
        if (excelMessage.getStatus().toString().equals("success"))
        {
            List<String> usernames = new LinkedList<String>();
            for (Member member : members)
            {
                usernames.add(member.getUsername());
            }
            Setting setting = SettingUtils.get();
            String initPassword = setting.getInitPassword();
            ObjectNode createNewIMUserBatchGenNode = easemobIMUsers.createUserBatch(usernames,
                    DigestUtils.md5Hex(initPassword));
            if (null != createNewIMUserBatchGenNode)
            {
                try
                {
                    List<Member> preTeachers = memberService.findActiveTeachersBySchool(dictSchool);
                    memberService.batchUpdate(members);
                    List<Member> addMembers = new ArrayList<Member>();
                    for (Member addMember : members)
                    {
                        addMembers.add(memberService.findByUsername(addMember.getUsername()));
                    }
                    List<ClassTeacherMap> classTeacherMaps = classTeacherMapService.createMapbyMembers(addMembers);
                    classTeacherMapService.batchUpdate(classTeacherMaps);
                    memberService.batchUpdate(addMembers);// 将address置空
                    // //将学校下所有的老师设置为好友
                    // //imUserUtil.addTeacherFriendInSchool(dictSchool);
                    // imUserUtil.twoMemberListAddFriend(addMembers,preTeachers);
                    // //将批量导入的老师和家长绑定好友关系
                    // for(Member addMember : members){
                    // Member twiceMember =
                    // memberService.findByUsername(addMember.getUsername());
                    // imUserUtil.newTeacherFriendWithClassPatriarch(twiceMember);
                    // }
                    data.put("message", Message.success("导入成功！"));
                }
                catch (Exception e)
                {
                    data.put("message", Message.warn("导入失败，请检查文件是否正确！"));
                }
            }
            else
            {
                data.put("message", Message.warn("导入失败，请检查文件是否正确！"));
            }
        }
        else
        {
            data.put("message", Message.warn(excelMessage.getError()));
        }
        try
        {
            // Date endDate = new Date();
            // System.out.println("时间差："+(endDate.getTime()-startDate.getTime()));

            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), data);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
