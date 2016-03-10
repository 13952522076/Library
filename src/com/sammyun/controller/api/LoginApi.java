package com.sammyun.controller.api;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sammyun.Setting;
import com.sammyun.Setting.AccountLockType;
import com.sammyun.controller.api.bean.LoginBean;
import com.sammyun.controller.api.bean.LoginResetPassowordBean;
import com.sammyun.controller.api.bean.MemberDeviceInfoBean;
import com.sammyun.controller.api.block.Login4StudentInfoBlock;
import com.sammyun.controller.api.block.LoginBlock;
import com.sammyun.controller.api.block.LoginRequestVCodeBlock;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.controller.console.BaseController;
import com.sammyun.entity.BaseEntity.Save;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.MemberDeviceInfo;
import com.sammyun.entity.SafeKey;
import com.sammyun.entity.attendance.TimeCard;
import com.sammyun.entity.attendance.TimeCard.CardStatus;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.dict.PatriarchStudentMap;
import com.sammyun.huanxin.EasemobIMUsers;
import com.sammyun.service.MemberDeviceInfoService;
import com.sammyun.service.MemberService;
import com.sammyun.service.attendance.TimeCardService;
import com.sammyun.service.dict.DictStudentService;
import com.sammyun.service.dict.PatriarchStudentMapService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.ImUserUtil;
import com.sammyun.util.SettingUtils;
import com.sammyun.util.SmsCellUtil;
import com.sammyun.util.SpringUtils;

/**
 * api - 用户登录
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("login")
@Path("/")
public class LoginApi extends BaseController
{

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "patriarchStudentMapServiceImpl")
    private PatriarchStudentMapService patriarchStudentMapService;

    @Resource(name = "dictStudentServiceImpl")
    private DictStudentService dictStudentService;

    @Resource(name = "memberDeviceInfoServiceImpl")
    private MemberDeviceInfoService memberDeviceInfoService;

    @Resource(name = "timeCardServiceImpl")
    private TimeCardService timeCardService;

    @Resource(name = "easemobIMUsers")
    private EasemobIMUsers easemobIMUsers;

    /**
     * 家长和教师登陆 <功能详细描述>
     * 
     * @param loginBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/login")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileRestFulModel loginByUserName(LoginBean loginBean)
    {
        MobileRestFulModel loginRestFulModel = new MobileRestFulModel();
        ImUserUtil imUserUtil = new ImUserUtil();
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        MemberDeviceInfoBean memberDeviceInfoBean = loginBean.getMemberDeviceInfoBean();
        if (username == null || "".equalsIgnoreCase(username))
        {
            loginRestFulModel.setResultCode(1);
            loginRestFulModel.setResultMessage("用户名不可为空！");
            return loginRestFulModel;
        }
        if (password == null || "".equalsIgnoreCase(password))
        {
            loginRestFulModel.setResultCode(1);
            loginRestFulModel.setResultMessage("密码不可为空！");
            return loginRestFulModel;
        }
        // if (memberDeviceInfoBean == null ||
        // memberDeviceInfoBean.getDeviceToken() == null
        // || "".equalsIgnoreCase(memberDeviceInfoBean.getDeviceToken()))
        // {
        // loginRestFulModel.setResultCode(1);
        // loginRestFulModel.setResultMessage("设备信息不可为空！");
        // return loginRestFulModel;
        // }

        // /**
        // * IM用户登录
        // */
        // ObjectNode imUserLoginNode = easemobIMUsers.userLogin(username,
        // password.toLowerCase());
        // if (null != imUserLoginNode)
        // {
        // if
        // (!imUserLoginNode.get("statusCode").asText().equalsIgnoreCase("200"))
        // {
        // loginRestFulModel.setResultCode(1);
        // loginRestFulModel.setResultMessage("登录失败");
        // return loginRestFulModel;
        // }
        // }
        // else
        // {
        // loginRestFulModel.setResultCode(1);
        // loginRestFulModel.setResultMessage("登陆失败！");
        // return loginRestFulModel;
        // }
        // 登录信息
        Setting setting = SettingUtils.get();
        Member member = memberService.findByUsername(username);
        if (member == null)
        {
            loginRestFulModel.setResultCode(1);
            loginRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.unknownAccount"));
            return loginRestFulModel;
        }
        if (!member.getIsEnabled())
        {
            loginRestFulModel.setResultCode(1);
            loginRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.disabledAccount"));
            return loginRestFulModel;
        }
        if (member.getIsLocked())
        {
            if (ArrayUtils.contains(setting.getAccountLockTypes(), AccountLockType.member))
            {
                int loginFailureLockTime = setting.getAccountLockTime();
                if (loginFailureLockTime == 0)
                {
                    loginRestFulModel.setResultCode(1);
                    loginRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.disabledAccount",
                            setting.getAccountLockTime()));
                    return loginRestFulModel;
                }
                Date lockedDate = member.getLockedDate();
                Date unlockDate = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
                if (new Date().after(unlockDate))
                {
                    member.setLoginFailureCount(0);
                    member.setIsLocked(false);
                    member.setLockedDate(null);
                    memberService.update(member);
                }
                else
                {
                    loginRestFulModel.setResultCode(1);
                    loginRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.lockedAccount",
                            setting.getAccountLockTime()));
                    return loginRestFulModel;
                }
            }
            else
            {
                member.setLoginFailureCount(0);
                member.setIsLocked(false);
                member.setLockedDate(null);
                memberService.update(member);
            }
        }

        if (!password.equalsIgnoreCase(member.getPassword()))
        {
            int loginFailureCount = member.getLoginFailureCount() + 1;
            if (loginFailureCount >= setting.getAccountLockCount())
            {
                member.setIsLocked(true);
                member.setLockedDate(new Date());
            }
            member.setLoginFailureCount(loginFailureCount);
            memberService.update(member);
            if (ArrayUtils.contains(setting.getAccountLockTypes(), AccountLockType.member))
            {
                loginRestFulModel.setResultCode(1);
                loginRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.accountLockCount",
                        setting.getAccountLockCount()));
                return loginRestFulModel;
            }
            else
            {
                loginRestFulModel.setResultCode(1);
                loginRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.incorrectCredentials"));
                return loginRestFulModel;
            }
        }
        member.setLoginDate(new Date());
        member.setLoginFailureCount(0);

        // 保存或更新设备信息
        int count = 0;
        if (member.getMemberDeviceInfos() != null)
        {
            for (MemberDeviceInfo memberDeviceInfo : member.getMemberDeviceInfos())
            {
                if (memberDeviceInfo.getDeviceToken() != null
                        && !"".equalsIgnoreCase(memberDeviceInfo.getDeviceToken()))
                {
                    if (memberDeviceInfo.getDeviceToken().equalsIgnoreCase(
                            loginBean.getMemberDeviceInfoBean().getDeviceToken()))
                    {
                        memberDeviceInfoService.update(saveMemberDeviceInfo(member, memberDeviceInfo, loginBean));
                        count++;
                        break;
                    }
                }
            }
        }

        if (count == 0)
        {
            MemberDeviceInfo memberDeviceInfo = new MemberDeviceInfo();
            memberDeviceInfoService.save(saveMemberDeviceInfo(member, memberDeviceInfo, loginBean));
        }
        memberService.update(member);

        // 返回app的数据
        List<Login4StudentInfoBlock> login4StudentInfos = new LinkedList<Login4StudentInfoBlock>();
        String cardNumber = null;

        if (member.getMemberType() != null && !"".equalsIgnoreCase(member.getMemberType().toString()))
        {
            if (member.getMemberType().equals(MemberType.patriarch))
            {
                List<PatriarchStudentMap> patriarchStudentMaps = patriarchStudentMapService.findStudentByMember(member);
                if (patriarchStudentMaps != null && patriarchStudentMaps.size() != 0)
                {
                    for (PatriarchStudentMap patriarchStudentMap : patriarchStudentMaps)
                    {
                        DictStudent dictStudent = patriarchStudentMap.getDictStudent();
                        List<TimeCard> timeCards = timeCardService.findByMember(member, dictStudent, CardStatus.normal);
                        DictClass dictClass = dictStudent.getDictClass();

                        Login4StudentInfoBlock login4StudentInfo = new Login4StudentInfoBlock();
                        login4StudentInfo.setDictClassId(dictClass.getId());
                        login4StudentInfo.setName(dictClass.getName());
                        login4StudentInfo.setDictStudentId(dictStudent.getId());
                        login4StudentInfo.setStudentNo(dictStudent.getStudentNo());
                        login4StudentInfo.setStudentName(dictStudent.getStudentName());
                        login4StudentInfo.setStudentStatus(dictStudent.getStudentStatus());
                        if (timeCards != null && timeCards.size() != 0)
                        {
                            login4StudentInfo.setCardNumber(timeCards.get(0).getCardNumber());
                        }
                        login4StudentInfos.add(login4StudentInfo);
                    }
                }
            }
            else if (member.getMemberType().equals(MemberType.teacher))
            {
                List<TimeCard> timeCards = timeCardService.findByMember(member, null, CardStatus.normal);
                if (timeCards != null && timeCards.size() != 0)
                {
                    cardNumber = timeCards.get(0).getCardNumber();
                }
            }
        }

        LoginBlock rows = new LoginBlock();
        rows.setIconPhoto(imUserUtil.getDefaultImageUrl(member.getIconPhoto()));
        rows.setMemberType(member.getMemberType());
        rows.setRealName(member.getRealName());
        rows.setMemberId(member.getId());
        rows.setDictSchoolId(member.getDictSchool().getId());
        rows.setSchoolLogo(member.getDictSchool().getSchoolLogo());
        rows.setSchoolName(member.getDictSchool().getName());
        rows.setBgColor(member.getDictSchool().getBgColor());
        rows.setCardNumber(cardNumber);
        rows.setStudentInfos(login4StudentInfos);
        loginRestFulModel.setRows(rows);
        loginRestFulModel.setResultCode(0);
        loginRestFulModel.setResultMessage("登陆成功");
        return loginRestFulModel;
    }

    // 保存用户设备信息
    public MemberDeviceInfo saveMemberDeviceInfo(Member member, MemberDeviceInfo memberDeviceInfo, LoginBean loginBean)
    {
        memberDeviceInfo.setAccuracy(loginBean.getMemberDeviceInfoBean().getAccuracy());
        memberDeviceInfo.setAltitude(loginBean.getMemberDeviceInfoBean().getAltitude());
        memberDeviceInfo.setAltitudeAccuracy(loginBean.getMemberDeviceInfoBean().getAltitudeAccuracy());
        memberDeviceInfo.setAppid(loginBean.getMemberDeviceInfoBean().getAppid());
        memberDeviceInfo.setAppver(loginBean.getMemberDeviceInfoBean().getAppver());
        memberDeviceInfo.setCarrierName(loginBean.getMemberDeviceInfoBean().getCarrierName());
        memberDeviceInfo.setDeviceModel(loginBean.getMemberDeviceInfoBean().getDeviceModel());
        memberDeviceInfo.setDeviceName(loginBean.getMemberDeviceInfoBean().getDeviceName());
        memberDeviceInfo.setDeviceOs(loginBean.getMemberDeviceInfoBean().getDeviceOs());
        memberDeviceInfo.setDeviceToken(loginBean.getMemberDeviceInfoBean().getDeviceToken());
        memberDeviceInfo.setEnv(loginBean.getMemberDeviceInfoBean().getEnv());
        memberDeviceInfo.setHeading(loginBean.getMemberDeviceInfoBean().getHeading());
        memberDeviceInfo.setIsoCountryCode(loginBean.getMemberDeviceInfoBean().getIsoCountryCode());
        memberDeviceInfo.setLatitude(loginBean.getMemberDeviceInfoBean().getLatitude());
        memberDeviceInfo.setLocalLanguage(loginBean.getMemberDeviceInfoBean().getLocalLanguage());
        memberDeviceInfo.setLongitude(loginBean.getMemberDeviceInfoBean().getLongitude());
        memberDeviceInfo.setMacAddress(loginBean.getMemberDeviceInfoBean().getMacAddress());
        memberDeviceInfo.setMember(member);
        memberDeviceInfo.setMobileCountryCode(loginBean.getMemberDeviceInfoBean().getMobileCountryCode());
        memberDeviceInfo.setOsName(loginBean.getMemberDeviceInfoBean().getOsName());
        memberDeviceInfo.setOsVersion(loginBean.getMemberDeviceInfoBean().getOsVersion());
        memberDeviceInfo.setSpeed(loginBean.getMemberDeviceInfoBean().getSpeed());
        memberDeviceInfo.setTimestamp(loginBean.getMemberDeviceInfoBean().getTimestamp());
        memberDeviceInfo.setTimezone(loginBean.getMemberDeviceInfoBean().getTimestamp());
        memberDeviceInfo.setUserName(loginBean.getMemberDeviceInfoBean().getUserName());
        memberDeviceInfo.setUuid(loginBean.getMemberDeviceInfoBean().getUuid());
        return memberDeviceInfo;

    }

    /**
     * 获取手机短信验证
     */
    @GET
    @Path("/v1/requestVCode/{phoneNumber}")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel requestVCode(@PathParam("phoneNumber") String phoneNumber)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        LoginRequestVCodeBlock rows = new LoginRequestVCodeBlock();
        Member member = memberService.findByUsername(phoneNumber);

        try
        {
            if (member != null)
            {
                Setting setting = SettingUtils.get();
                Integer validateCodeNumber = member.getValidateCodeNumber();
                if (validateCodeNumber != null && validateCodeNumber >= setting.getValidateCodeMaxNumber())
                {
                    mobileRestFulModel.setResultCode(1);
                    mobileRestFulModel.setResultMessage(SpringUtils.getMessage("已超过每天请求验证码最大次数！"));
                    return mobileRestFulModel;
                }
                // 每天请求验证码次数 为空或者null，初始为0
                if (validateCodeNumber == null)
                {
                    validateCodeNumber = 0;
                }
                // 随机生成6位验证码
                Double d = Math.random();
                int validateCodeInt = (int) (d * 900000 + 100000);
                String validateCode = String.valueOf(validateCodeInt);
                String msg = SpringUtils.getMessage("login.validate.sms.info", validateCode, 30);
                SafeKey safeKey = new SafeKey();
                safeKey.setValue(UUID.randomUUID().toString()
                        + DigestUtils.md5Hex(RandomStringUtils.randomAlphabetic(30)));
                safeKey.setExpire(setting.getSafeKeyExpiryTime() != 0 ? DateUtils.addMinutes(new Date(),
                        setting.getSafeKeyExpiryTime()) : null);
                member.setSafeKey(safeKey);
                validateCodeNumber += 1;
                String sendTime = DateUtil.date2String(new Date(), 1);
                if (!setting.getIsMsgNotified())
                {
                    mobileRestFulModel.setResultCode(1);
                    mobileRestFulModel.setResultMessage(SpringUtils.getMessage("获取手机短信验证失败"));
                    return mobileRestFulModel;
                }
                HashMap returnData = SmsCellUtil.getInstance().sendDone(phoneNumber, msg, sendTime);
                if ("0".equals(returnData.get("return")))
                {
                    member.setValidateCodeNumber(validateCodeNumber);
                    memberService.update(member);
                    rows.setPhoneNumber(phoneNumber);
                    rows.setSafeKey(safeKey.getValue());
                    rows.setValidateCode(validateCode);
                }
                else
                {
                    mobileRestFulModel.setResultCode(1);
                    mobileRestFulModel.setResultMessage(SpringUtils.getMessage("获取手机短信验证失败"));
                    return mobileRestFulModel;
                }
            }
            else
            {
                mobileRestFulModel.setResultCode(1);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("手机号码系统中不存在"));
                return mobileRestFulModel;
            }
        }
        catch (Exception e)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("获取手机短信验证失败"));
            return mobileRestFulModel;
        }
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage(SpringUtils.getMessage("获取验证码成功！"));
        mobileRestFulModel.setRows(rows);
        return mobileRestFulModel;
    }

    /**
     * 重置密码提交
     */
    @POST
    @Path("/v1/resetPassword")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileRestFulModel resetPassoword(LoginResetPassowordBean loginResetPassowordBean)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        Member member = memberService.findByUsername(loginResetPassowordBean.getPhoneNumber());
        String password = loginResetPassowordBean.getPassword();

        if (member == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("未知用户不存在！"));
            return mobileRestFulModel;
        }
        String oldPassword = member.getPassword();
        if (!isValid(Member.class, "password", password, Save.class))
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("密码格式错误！"));
            return mobileRestFulModel;
        }
        Setting setting = SettingUtils.get();
        if (password.length() < setting.getPasswordMinLength())
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("密码设置太短！"));
            return mobileRestFulModel;
        }
        SafeKey safeKey = member.getSafeKey();
        if (safeKey == null || safeKey.getValue() == null
                || !safeKey.getValue().equals(loginResetPassowordBean.getSafeKey()))
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("该找回密码链接已失效！"));
            return mobileRestFulModel;
        }
        if (safeKey.hasExpired())
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("该找回密码链接已失效！"));
            return mobileRestFulModel;
        }
        member.setPassword(password);
        safeKey.setExpire(new Date());
        safeKey.setValue(null);
        memberService.update(member);

        /**
         * 重置IM用户密码 提供管理员token
         */
        ObjectNode modifyIMUserPasswordWithAdminTokenNode = easemobIMUsers.modifyUserPassword(member.getUsername(),
                password.toLowerCase());
        if (null != modifyIMUserPasswordWithAdminTokenNode)
        {
            if (modifyIMUserPasswordWithAdminTokenNode.get("statusCode").asText().equalsIgnoreCase("200"))
            {
                mobileRestFulModel.setResultCode(0);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("密码修改成功！"));
                return mobileRestFulModel;
            }
            else
            {
                member.setPassword(oldPassword);
                safeKey.setExpire(new Date());
                safeKey.setValue(null);
                memberService.update(member);
                mobileRestFulModel.setResultCode(1);
                mobileRestFulModel.setResultMessage(SpringUtils.getMessage("密码修改失败！"));
                return mobileRestFulModel;
            }
        }
        else
        {
            member.setPassword(oldPassword);
            safeKey.setExpire(new Date());
            safeKey.setValue(null);
            memberService.update(member);
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("密码修改失败！"));
            return mobileRestFulModel;
        }
    }
}
