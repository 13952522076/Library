package com.sammyun.controller.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sammyun.FileInfo.FileType;
import com.sammyun.Setting;
import com.sammyun.controller.api.bean.PersonalResetDictStudentBean;
import com.sammyun.controller.api.bean.PersonalResetPersonalBean;
import com.sammyun.controller.api.block.PersonalDictStudentBlock;
import com.sammyun.controller.api.block.PersonalPersonalInfoBlock;
import com.sammyun.controller.api.block.PersonalProvinceBlock;
import com.sammyun.controller.api.block.PersonalTypeBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.controller.console.BaseController;
import com.sammyun.entity.Area;
import com.sammyun.entity.BaseEntity.Save;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.dict.DictStudent.Gender;
import com.sammyun.entity.dict.PatriarchStudentMap;
import com.sammyun.entity.dict.PatriarchStudentMap.Type;
import com.sammyun.huanxin.EasemobIMUsers;
import com.sammyun.plugin.StoragePlugin;
import com.sammyun.service.AreaService;
import com.sammyun.service.FileService;
import com.sammyun.service.MemberService;
import com.sammyun.service.PluginService;
import com.sammyun.service.dict.ClassTeacherMapService;
import com.sammyun.service.dict.DictStudentService;
import com.sammyun.service.dict.PatriarchStudentMapService;
import com.sammyun.service.friendship.FriendshipService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.FreemarkerUtils;
import com.sammyun.util.ImUserUtil;
import com.sammyun.util.SettingUtils;
import com.sammyun.util.SpringUtils;

/***
 * api - 用户信息
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("personal")
@Path("/personal")
public class PersonalApi extends BaseController
{
    /** 目标文件类型 */
    private static final String DEST_CONTENT_TYPE = "image/jpeg";

    @Resource(name = "areaServiceImpl")
    private AreaService areaService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "patriarchStudentMapServiceImpl")
    private PatriarchStudentMapService patriarchStudentMapService;

    @Resource(name = "dictStudentServiceImpl")
    private DictStudentService dictStudentService;

    @Resource(name = "fileServiceImpl")
    private FileService fileService;

    @Resource(name = "pluginServiceImpl")
    private PluginService pluginService;

    @Resource(name = "taskExecutor")
    private TaskExecutor taskExecutor;

    @Resource(name = "easemobIMUsers")
    private EasemobIMUsers easemobIMUsers;

    @Resource(name = "classTeacherMapServiceImpl")
    private ClassTeacherMapService classTeacherMapService;

    @Resource(name = "friendshipServiceImpl")
    private FriendshipService friendshipService;

    @Resource
    private List<StoragePlugin> storagePlugins;

    ImUserUtil imUserUtil = new ImUserUtil();

    /**
     * 获取所有的省份信息
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */

    @GET
    @Path("/v1/provinceInfo")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel provinceInfo()
    {
        ListRestFulModel loginRestFulModel = new ListRestFulModel();
        List<PersonalProvinceBlock> rows = new LinkedList<PersonalProvinceBlock>();
        List<Area> areas = areaService.findAll();
        for (Area area : areas)
        {
            PersonalProvinceBlock provinceInfo = new PersonalProvinceBlock();
            if (area.getParent() == null)
            {
                provinceInfo.setName(area.getName());
                provinceInfo.setId(area.getId());
                List<Area> citys = new ArrayList<Area>(area.getChildren());
                List<PersonalProvinceBlock> cityList = new LinkedList<PersonalProvinceBlock>();
                if (citys == null || citys.size() == 0)
                {
                    continue;
                }
                else
                {
                    for (Area city : citys)
                    {
                        PersonalProvinceBlock cityInfo = new PersonalProvinceBlock();
                        List<Area> regions = new ArrayList<Area>(city.getChildren());
                        List<PersonalProvinceBlock> regionList = new LinkedList<PersonalProvinceBlock>();
                        for (Area region : regions)
                        {
                            PersonalProvinceBlock regionInfo = new PersonalProvinceBlock();
                            regionInfo.setName(region.getName());
                            regionInfo.setId(region.getId());
                            regionList.add(regionInfo);
                        }
                        cityInfo.setName(city.getName());
                        cityInfo.setId(city.getId());
                        cityInfo.setCitys(regionList);
                        cityList.add(cityInfo);
                    }
                    provinceInfo.setCitys(cityList);
                    rows.add(provinceInfo);
                }
            }
            else
            {
                continue;
            }
        }
        loginRestFulModel.setRows(rows);
        loginRestFulModel.setResultCode(0);
        loginRestFulModel.setResultMessage("请求成功");
        return loginRestFulModel;
    }

    /**
     * 查看个人信息
     * 
     * @param model
     * @return
     */
    @GET
    @Path("/v1/personalInfo/{memberId}/{memberType}")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel personalInfo(@PathParam("memberId") Long memberId,
            @PathParam("memberType") MemberType memberType)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        PersonalPersonalInfoBlock rows = new PersonalPersonalInfoBlock();
        ImUserUtil imUserUtil = new ImUserUtil();
        if (memberId == null || memberType == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }
        Member member = memberService.find(memberId);
        if (member == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.unknownAccount"));
            return mobileRestFulModel;
        }

        rows.setIconPhoto(imUserUtil.getDefaultImageUrl(member.getIconPhoto()));
        rows.setMobile(member.getMobile());
        rows.setRealName(member.getRealName());

        PersonalProvinceBlock region = new PersonalProvinceBlock();
        PersonalProvinceBlock city = new PersonalProvinceBlock();
        PersonalProvinceBlock province = new PersonalProvinceBlock();
        if (member.getArea() != null && member.getArea().getParent() == null)
        {
            province.setId(member.getArea().getId());
            province.setName(member.getArea().getName());
        }
        else if (member.getArea() != null && member.getArea().getParent() != null
                && member.getArea().getParent().getParent() == null)
        {
            city.setId(member.getArea().getId());
            city.setName(member.getArea().getName());
            province.setId(member.getArea().getParent().getId());
            province.setName(member.getArea().getParent().getName());
        }
        else if (member.getArea() != null && member.getArea().getParent() != null
                && member.getArea().getParent().getParent() != null)
        {
            region.setId(member.getArea().getId());
            region.setName(member.getArea().getName());
            city.setId(member.getArea().getParent().getId());
            city.setName(member.getArea().getParent().getName());
            province.setId(member.getArea().getParent().getParent().getId());
            province.setName(member.getArea().getParent().getParent().getName());
        }
        rows.setProvince(province);
        rows.setCity(city);
        rows.setRegion(region);

        List<PersonalDictStudentBlock> dictStudents = new LinkedList<PersonalDictStudentBlock>();
        if (member.getMemberType().equals(memberType))
        {
            if (memberType.equals(MemberType.patriarch))
            {
                List<PatriarchStudentMap> patriarchStudentMaps = patriarchStudentMapService.findStudentByMember(member);
                if (patriarchStudentMaps != null && patriarchStudentMaps.size() != 0)
                {
                    Set<PatriarchStudentMap> patriarchStudentMapSet = new HashSet<PatriarchStudentMap>();
                    for (PatriarchStudentMap patriarchStudentMap : patriarchStudentMaps)
                    {
                        patriarchStudentMapSet.add(patriarchStudentMap);
                    }
                    for (PatriarchStudentMap patriarchStudentMap : patriarchStudentMapSet)
                    {
                        DictStudent dictStudent = patriarchStudentMap.getDictStudent();
                        PersonalDictStudentBlock personalDictStudentBlock = new PersonalDictStudentBlock();
                        if (dictStudent.getBirthday() != null)
                        {
                            personalDictStudentBlock.setBirthday(DateUtil.date2String(dictStudent.getBirthday(), 10));
                        }
                        personalDictStudentBlock.setGender(dictStudent.getGender());
                        if (dictStudent.getGender() != null)
                        {
                            personalDictStudentBlock.setGenderName(SpringUtils.getMessage("DictStudent.gender."
                                    + dictStudent.getGender()));
                        }
                        personalDictStudentBlock.setIconPhoto(imUserUtil.getDefaultImageUrl(dictStudent.getIconPhoto()));
                        personalDictStudentBlock.setStudentName(dictStudent.getStudentName());
                        personalDictStudentBlock.setStudentNo(dictStudent.getStudentNo());
                        personalDictStudentBlock.setStudentId(dictStudent.getId());
                        personalDictStudentBlock.setType(patriarchStudentMap.getType());
                        if (patriarchStudentMap.getType() != null)
                        {
                            personalDictStudentBlock.setTypeName(SpringUtils.getMessage("PatriarchStudentMap."
                                    + patriarchStudentMap.getType()));
                        }
                        dictStudents.add(personalDictStudentBlock);
                    }
                    rows.setDictStudents(dictStudents);
                }
            }
        }
        else
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("用户类型错误！"));
            return mobileRestFulModel;
        }

        mobileRestFulModel.setRows(rows);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("请求成功");
        return mobileRestFulModel;
    }

    /**
     * 修改个人信息
     * 
     * @param profileRestPersonalBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/resetPersonal")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileRestFulModel resetPersonal(PersonalResetPersonalBean personalResetPersonalBean)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();

        Long memberId = personalResetPersonalBean.getMemberId();
        String realName = personalResetPersonalBean.getRealName();
        Long areaId = personalResetPersonalBean.getAreaId();
        String mobile = personalResetPersonalBean.getMobile();
        String newPassword = personalResetPersonalBean.getPassword();
        String oldPassword = personalResetPersonalBean.getOldPassword();
        String type = personalResetPersonalBean.getType();
        String iconPhoto = personalResetPersonalBean.getIconPhoto();

        if (memberId == null || type == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }
        Member member = memberService.find(memberId);
        if (member == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.unknownAccount"));
            return mobileRestFulModel;
        }
        if (type.equalsIgnoreCase("iconPhoto"))
        {
            if (iconPhoto != null || !"".equalsIgnoreCase(iconPhoto))
            {
                member.setIconPhoto(iconPhoto);
            }
            else
            {
                mobileRestFulModel.setResultCode(1);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("头像不可为空！"));
                return mobileRestFulModel;
            }
        }
        else if (type.equalsIgnoreCase("realName"))
        {
            if (realName != null || !"".equalsIgnoreCase(realName))
            {
                member.setRealName(realName);
            }
            else
            {
                mobileRestFulModel.setResultCode(1);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("姓名不可为空！"));
                return mobileRestFulModel;
            }
        }
        else if (type.equalsIgnoreCase("area"))
        {
            if (areaId != null)
            {
                Area area = this.areaService.find(areaId);
                if (area != null)
                {
                    member.setArea(area);
                }
                else
                {
                    mobileRestFulModel.setResultCode(1);
                    mobileRestFulModel.setResultMessage(SpringUtils.getMessage("所选地区不存在！"));
                    return mobileRestFulModel;
                }
            }
            else
            {
                mobileRestFulModel.setResultCode(1);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("所在地不可为空！"));
                return mobileRestFulModel;
            }
        }
        else if (type.equalsIgnoreCase("mobile"))
        {
            // Date date = new Date();
            // Date modifyDate = member.getModifyDate();
            // double minuteBetweenDates =
            // DateUtil.getMinuteBetweenDates(modifyDate, date);
            // if (minuteBetweenDates < 10)
            // {
            // mobileRestFulModel.setResultCode(1);
            // mobileRestFulModel.setResultMessage(SpringUtils.getMessage("10分钟之内不可修改手机号！"));
            // return mobileRestFulModel;
            // }
            //
            if (mobile != null || !"".equalsIgnoreCase(mobile))
            {
                if (!checkMobile(member.getMobile(), mobile))
                {
                    mobileRestFulModel.setResultCode(1);
                    mobileRestFulModel.setResultMessage(SpringUtils.getMessage("手机号已存在！"));
                    return mobileRestFulModel;
                }
                if (!checkUsername(mobile))
                {
                    mobileRestFulModel.setResultCode(1);
                    mobileRestFulModel.setResultMessage(SpringUtils.getMessage("该手机号已被禁用！"));
                    return mobileRestFulModel;
                }
                updateMobile(member, mobile);
            }
            else
            {
                mobileRestFulModel.setResultCode(1);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("手机号不可为空！"));
                return mobileRestFulModel;
            }
        }
        else if (type.equalsIgnoreCase("password"))
        {
            if ((oldPassword == null || oldPassword.length() <= 0)
                    || (newPassword == null || newPassword.length() <= 0))
            {
                mobileRestFulModel.setResultCode(1);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("密码不可为空！"));
                return mobileRestFulModel;
            }
            Setting setting = SettingUtils.get();
            if (!isValid(Member.class, "password", newPassword, Save.class))
            {
                mobileRestFulModel.setResultCode(1);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("密码格式错误！"));
                return mobileRestFulModel;
            }
            if (newPassword.length() < setting.getPasswordMinLength())
            {
                mobileRestFulModel.setResultCode(1);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("密码设置太短！"));
                return mobileRestFulModel;
            }
            if (oldPassword.equalsIgnoreCase(member.getPassword()))
            {
                member.setPassword(newPassword);
            }
            else
            {
                mobileRestFulModel.setResultCode(1);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("原密码错误！"));
                return mobileRestFulModel;
            }

            /**
             * 重置IM用户密码 提供管理员token
             */
            EasemobIMUsers easemobIMUsers = new EasemobIMUsers();
            ObjectNode modifyIMUserPasswordWithAdminTokenNode = easemobIMUsers.modifyUserPassword(member.getUsername(),
                    newPassword.toLowerCase());
            if (null != modifyIMUserPasswordWithAdminTokenNode)
            {
                if (!modifyIMUserPasswordWithAdminTokenNode.get("statusCode").asText().equalsIgnoreCase("200"))
                {

                    mobileRestFulModel.setResultCode(1);
                    mobileRestFulModel.setResultMessage(SpringUtils.getMessage("密码修改失败！"));
                    return mobileRestFulModel;
                }
            }
            else
            {
                mobileRestFulModel.setResultCode(1);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("密码修改失败！"));
                return mobileRestFulModel;
            }
        }
        else
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("类型错误！"));
            return mobileRestFulModel;
        }
        // member.setIsUpdate(true);
        memberService.update(member);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("信息修改成功！");
        return mobileRestFulModel;
    }

    /**
     * 检查用户名是否被禁用或已存在
     */
    public boolean checkUsername(String username)
    {
        if (StringUtils.isEmpty(username))
        {
            return false;
        }
        if (memberService.usernameDisabled(username))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 检查手机号是否唯一
     */
    public boolean checkMobile(String previousMobile, String mobile)
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
     * 修改手机号 <功能详细描述>
     * 
     * @param preMember
     * @param moible
     * @see [类、类#方法、类#成员]
     */
    public void updateMobile(Member preMember, String moible)
    {

        // 手机号改变，环信需要删除，重新建用户
        String preUserName = preMember.getUsername();
        String password = preMember.getPassword();
        preMember.setMobile(moible);
        preMember.setUsername(moible);
        // 获取原号码的好友列表
        // ObjectNode friendNode = easemobIMUsers.getFriends(preUserName);
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
        // List<Friendship> friendships = new LinkedList<Friendship>();
        // 好友不为空－》环信添加好友
        // if (friendList != null && friendList.size() != 0)
        // {
        // for (String friend : friendList)
        // {
        // Friendship friendship = new Friendship();
        // friendship.setOwnerUserPrimaryKey(preMember.getUsername());
        // friendship.setFriendUserPrimaryKey(friend);
        // friendship.setHasCreated(false);
        // friendships.add(friendship);
        // // easemobIMUsers.addFriend(preMember.getUsername(), friend);
        // }
        // }
        // friendshipService.batchUpdate(friendships);
    }

    /**
     * 获取我的身份选择项 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/type")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel type()
    {
        ListRestFulModel loginRestFulModel = new ListRestFulModel();
        List<PersonalTypeBlock> rows = new LinkedList<PersonalTypeBlock>();
        List<Type> types = new LinkedList<Type>();
        types.add(Type.father);
        types.add(Type.mother);
        types.add(Type.grandfather);
        types.add(Type.grandmother);
        types.add(Type.mgrandfather);
        types.add(Type.mgrandmother);

        for (Type type : types)
        {
            PersonalTypeBlock personalTypeBlock = new PersonalTypeBlock();
            personalTypeBlock.setType(type);
            personalTypeBlock.setTypeName(SpringUtils.getMessage("PatriarchStudentMap." + type));
            rows.add(personalTypeBlock);
        }
        loginRestFulModel.setRows(rows);
        loginRestFulModel.setResultCode(0);
        loginRestFulModel.setResultMessage("请求成功");
        return loginRestFulModel;
    }

    /**
     * 修改学生信息
     * 
     * @param model
     * @return
     */
    @POST
    @Path("/v1/resetDictStudent")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileRestFulModel resetDictStudent(PersonalResetDictStudentBean personalResetDictStudentBean)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        Long studentId = personalResetDictStudentBean.getStudentId();
        String studentName = personalResetDictStudentBean.getStudentName();
        Gender gender = personalResetDictStudentBean.getGender();
        String birthday = personalResetDictStudentBean.getBirthday();
        Type type = personalResetDictStudentBean.getType();
        String modifyType = personalResetDictStudentBean.getModifyType();
        Long memberId = personalResetDictStudentBean.getMemberId();
        String iconPhoto = personalResetDictStudentBean.getIconPhoto();

        if (memberId == null || modifyType == null || studentId == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }
        Member member = memberService.find(memberId);
        if (member == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.unknownAccount"));
            return mobileRestFulModel;
        }
        DictStudent dictStudent = dictStudentService.find(studentId);
        if (dictStudent == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.unknownAccount"));
            return mobileRestFulModel;
        }

        if (modifyType.equalsIgnoreCase("iconPhoto"))
        {
            if (iconPhoto != null || !"".equalsIgnoreCase(iconPhoto))
            {
                dictStudent.setIconPhoto(iconPhoto);
                dictStudentService.save(dictStudent);
            }
            else
            {
                mobileRestFulModel.setResultCode(1);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("头像不可为空！"));
                return mobileRestFulModel;
            }
        }
        else if (modifyType.equalsIgnoreCase("studentName"))
        {
            if (studentName != null || !"".equalsIgnoreCase(studentName))
            {
                dictStudent.setStudentName(studentName);
                dictStudentService.save(dictStudent);
            }
            else
            {
                mobileRestFulModel.setResultCode(1);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("姓名不可为空！"));
                return mobileRestFulModel;
            }
        }
        else if (modifyType.equalsIgnoreCase("gender"))
        {
            if (gender != null)
            {
                dictStudent.setGender(gender);
                dictStudentService.save(dictStudent);
            }
            else
            {
                mobileRestFulModel.setResultCode(1);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("性别不可为空！"));
                return mobileRestFulModel;
            }
        }
        else if (modifyType.equalsIgnoreCase("birthday"))
        {
            if (birthday != null || !"".equalsIgnoreCase(birthday))
            {
                dictStudent.setBirthday(DateUtil.string2Date(birthday));
                dictStudentService.save(dictStudent);
            }
            else
            {
                mobileRestFulModel.setResultCode(1);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("生日不可为空！"));
                return mobileRestFulModel;
            }
        }
        else if (modifyType.equalsIgnoreCase("type"))
        {
            if (type != null)
            {
                PatriarchStudentMap patriarchStudentMap = patriarchStudentMapService.findStudentByMember(member,
                        dictStudent);
                if (patriarchStudentMap == null)
                {
                    mobileRestFulModel.setResultCode(1);
                    mobileRestFulModel.setResultMessage(SpringUtils.getMessage("学生家长列表不存在！"));
                    return mobileRestFulModel;
                }
                patriarchStudentMap.setType(type);
                patriarchStudentMapService.save(patriarchStudentMap);
            }
            else
            {
                mobileRestFulModel.setResultCode(1);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("身份不可为空！"));
                return mobileRestFulModel;
            }
        }
        else
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("类型错误！"));
            return mobileRestFulModel;
        }
        // member.setIsUpdate(true);
        // memberService.update(member);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("信息修改成功！");
        return mobileRestFulModel;
    }

    /**
     * 上传头像
     * 
     * @throws IOException
     */
    @POST
    @Path("/v1/upload/{fileType}")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public MobileRestFulModel upload(@PathParam("fileType") FileType fileType,
            @FormDataParam("uploadFile") final InputStream stream,
            @FormDataParam("uploadFile") final FormDataContentDisposition contentDispositionHeader)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        String url = "";
        try
        {
            Setting setting = SettingUtils.get();
            String uploadPath;
            if (fileType == FileType.flash)
            {
                uploadPath = setting.getFlashUploadPath();
            }
            else if (fileType == FileType.media)
            {
                uploadPath = setting.getMediaUploadPath();
            }
            else if (fileType == FileType.file)
            {
                uploadPath = setting.getFileUploadPath();
            }
            else
            {
                uploadPath = setting.getImageUploadPath();
            }
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("uuid", UUID.randomUUID().toString());
            String path = FreemarkerUtils.process(uploadPath, model);
            String sourcePath = path + UUID.randomUUID() + "."
                    + FilenameUtils.getExtension(contentDispositionHeader.getFileName());

            Collections.sort(storagePlugins);
            for (StoragePlugin storagePlugin : storagePlugins)
            {
                if (storagePlugin.getIsEnabled())
                {
                    File tempFile = new File(System.getProperty("java.io.tmpdir") + "/upload_" + UUID.randomUUID()
                            + ".tmp");
                    if (!tempFile.getParentFile().exists())
                    {
                        tempFile.getParentFile().mkdirs();
                    }
                    OutputStream os = new FileOutputStream(tempFile);
                    int bytesRead = 0;
                    byte[] buffer = new byte[8192];
                    while ((bytesRead = stream.read(buffer, 0, 8192)) != -1)
                    {
                        os.write(buffer, 0, bytesRead);
                    }
                    os.close();
                    stream.close();
                    addTask(sourcePath, tempFile, fileType);
                    url = storagePlugin.getUrl(sourcePath);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        mobileRestFulModel.setRows(url);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("上传成功！");
        return mobileRestFulModel;

    }

    /**
     * 添加图片处理任务
     * 
     * @param sourcePath 原图片上传路径
     * @param tempFile 原临时文件
     * @param contentType 原文件类型
     */
    private void addTask(final String sourcePath, final File tempFile, final FileType fileType)
    {
        try
        {
            taskExecutor.execute(new Runnable()
            {
                public void run()
                {
                    Collections.sort(storagePlugins);
                    for (StoragePlugin storagePlugin : storagePlugins)
                    {
                        if (storagePlugin.getIsEnabled())
                        {
                            try
                            {
                                storagePlugin.upload(sourcePath, tempFile, DEST_CONTENT_TYPE);
                            }
                            finally
                            {
                                FileUtils.deleteQuietly(tempFile);
                            }
                            break;
                        }
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
