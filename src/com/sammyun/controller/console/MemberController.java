/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.huanxin.EasemobIMUsers;
import com.sammyun.service.AdminService;
import com.sammyun.service.AreaService;
import com.sammyun.service.ExcelService;
import com.sammyun.service.MemberAttributeService;
import com.sammyun.service.MemberService;
import com.sammyun.service.dict.DictStudentService;
import com.sammyun.util.EduUtil;
import com.sammyun.util.ExcelImportUtil;
import com.sammyun.util.JsonUtils;
import com.sammyun.util.SettingUtils;

/**
 * Controller - 会员
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("adminMemberController")
@RequestMapping("/console/member")
public class MemberController extends BaseController
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

    @Resource(name = "easemobIMUsers")
    private EasemobIMUsers easemobIMUsers;

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
        return "/console/member/view";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, MemberType memberType, String mobile, String searchName, ModelMap model,
            HttpServletRequest request)
    {
        String meunId = Member.class.getSimpleName();
        if (null == memberType)
        {
            memberType = MemberType.patriarch;
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
        return "/console/member/list";
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
        model.addAttribute("setting", setting);
        model.addAttribute("menuId", meunId);
        model.addAttribute("memberType", memberType);
        return "/console/member/add";
    }

    /**
     * 保存 新增页面
     */
    @RequestMapping(value = "/add_save", method = RequestMethod.POST)
    public String addSave(Member member, HttpServletRequest request, long areaId,
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

        /**
         * 注册IM用户[单个]
         */
        ObjectNode createNewIMUserSingleNode = easemobIMUsers.createUserSingle(member.getUsername(),
                member.getPassword().toLowerCase());
        if (null != createNewIMUserSingleNode)
        {
            if (createNewIMUserSingleNode.get("statusCode").asText().equalsIgnoreCase("200"))
            {
                model.addAttribute("menuId", Member.class.getSimpleName());
                addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
                return "redirect:list.ct";
            }
            else
            {
                memberService.delete(member);
                return ERROR_VIEW;
            }
        }
        else
        {
            memberService.delete(member);
            return ERROR_VIEW;
        }
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Member member, Long memberRankId, HttpServletRequest request,
            RedirectAttributes redirectAttributes, ModelMap model)
    {
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

        member.setUsername(member.getUsername().toLowerCase());
        member.setPassword(DigestUtils.md5Hex(member.getPassword()));
        member.setIsLocked(false);
        member.setLoginFailureCount(0);
        member.setLockedDate(null);
        member.setRegisterIp(EduUtil.getAddr(request));
        member.setLoginIp(null);
        member.setLoginDate(null);
        member.setSafeKey(null);
        member.setPayments(null);
        // member.setIsUpdate(true);
        member.setIsAcceptLeaveInfo(true);
        memberService.save(member, adminService.getCurrent());
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
        model.addAttribute("setting", setting);
        model.addAttribute("member", memberService.find(id));
        model.addAttribute("menuId", meunId);
        model.addAttribute("memberType", memberType);
        return "/console/member/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Member member, HttpServletRequest request, RedirectAttributes redirectAttributes, long areaId,
            ModelMap model)
    {
        Member preMember = memberService.find(member.getId());
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
        preMember.setIsEnabled(member.getIsEnabled());
        preMember.setIconPhoto(member.getIconPhoto());
        preMember.setIsEnabled(member.getIsEnabled());
        // preMember.setIsUpdate(true);
        // DigestUtils.md5Hex(member.getPassword())
        if (preMember.getMobile().equals(member.getMobile()))
        {
            // 手机号不变，需要修改环信的密码
            if ((member.getPassword() != null) && (!member.getPassword().equals("")))
            {
                String md5Password = DigestUtils.md5Hex(member.getPassword());
                ObjectNode modifyIMUserPasswordWithAdminTokenNode = easemobIMUsers.modifyUserPassword(
                        preMember.getUsername(), md5Password.toLowerCase());
                if ((null != modifyIMUserPasswordWithAdminTokenNode)
                        && (modifyIMUserPasswordWithAdminTokenNode.get("statusCode").asText().equalsIgnoreCase("200")))
                {
                    preMember.setPassword(md5Password);
                    memberService.update(preMember);
                    model.addAttribute("menuId", Member.class.getSimpleName());
                    addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
                    return "redirect:list.ct";
                }
                else
                {
                    model.addAttribute("menuId", Member.class.getSimpleName());
                    addFlashMessage(redirectAttributes, ERROR_MESSAGE);
                    return ERROR_VIEW;
                }
            }
            else
            {
                memberService.update(preMember);
                addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
                model.addAttribute("menuId", Member.class.getSimpleName());
                return "redirect:list.ct";
            }
        }
        // 手机号改变，环信需要删除，重新建用户
        else
        {
            String preUserName = preMember.getUsername();
            String password = preMember.getPassword();
            preMember.setMobile(member.getMobile());
            preMember.setUsername(member.getMobile());
            if (member.getPassword() != null)
            {
                preMember.setPassword(DigestUtils.md5Hex(member.getPassword()));
                password = DigestUtils.md5Hex(member.getPassword());
            }
            // // 获取原号码的好友列表
            // ObjectNode friendNode = EasemobIMUsers.getFriends(preUserName);
            // List<String> friendList = new ArrayList<String>();
            // JsonNode arrNode = friendNode.get("data");
            // if (arrNode.isArray())
            // {
            // for (final JsonNode objNode : arrNode)
            // {
            // friendList.add(objNode.asText());
            // }
            // }
            // 删除im用户
            easemobIMUsers.deleteUserByUserPrimaryKey(preUserName);
            // 新建环信用户
            easemobIMUsers.createUserSingle(preMember.getUsername(), password.toLowerCase());
            memberService.update(preMember);
            // // 好友不为空－》环信添加好友
            // if (friendList.size() > 0)
            // {
            // for (String friend : friendList)
            // {
            // easemobIMUsers.addFriend(preMember.getUsername(), friend);
            // }
            // }
            model.addAttribute("menuId", Member.class.getSimpleName());
            addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
            return "redirect:list.ct";
        }
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
        List<Member> members = excelService.getMembers(inputStream, dictSchool, memberType, request);
        excelMessage = excelService.validateMembers(members);
        if (excelMessage.getStatus().toString().equals("success"))
        {
            List<String> usernames = new LinkedList<String>();
            for (Member member : members)
            {
                usernames.add(member.getUsername());
            }
            Setting setting = SettingUtils.get();
            String initPassword = setting.getInitPassword();
            easemobIMUsers.createUserBatch(usernames, DigestUtils.md5Hex(initPassword));
            try
            {
                memberService.batchUpdate(members);
                data.put("message", Message.success("导入成功！"));
            }
            catch (Exception e)
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
            // System.out.println("批量导入家长时间为：" + (endDate.getTime() -
            // startDate.getTime()));
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), data);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
