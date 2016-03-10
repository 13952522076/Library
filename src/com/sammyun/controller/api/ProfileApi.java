package com.sammyun.controller.api;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import com.sammyun.controller.api.block.ProfileSchoolInfoBlock;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.service.profile.ProfileService;
import com.sammyun.util.ImUserUtil;
import com.sammyun.util.SpringUtils;

/**
 * Api - 学校概况数据
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("profile")
@Path("/profile")
public class ProfileApi
{

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "profileServiceImpl")
    private ProfileService profileService;

    /**
     * 校园风光 <功能详细描述>
     * 
     * @param dictSchoolId
     * @return
     * @see [类、类#方法、类#成员]
     */

    @GET
    @Path("/v1/schoolInfo/{dictSchoolId}")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel schoolInfo(@PathParam("dictSchoolId") Long dictSchoolId)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        ImUserUtil imUserUtil = new ImUserUtil();
        if (dictSchoolId == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }

        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        if (dictSchool == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("未知学校不存在！"));
            return mobileRestFulModel;
        }
        com.sammyun.entity.profile.Profile profile = profileService.findBySchool(dictSchool);
        if (profile == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关学校概况！"));
            return mobileRestFulModel;
        }
        ProfileSchoolInfoBlock profileSchoolInfoBlock = new ProfileSchoolInfoBlock();
        profileSchoolInfoBlock.setIndexImage(imUserUtil.getDefaultImageUrl(profile.getIndexImage()));
        profileSchoolInfoBlock.setIntroduction(profile.getIntroduction());
        profileSchoolInfoBlock.setSchoolBadge(profile.getSchoolBadge());
        profileSchoolInfoBlock.setSchoolMotto(profile.getSchoolMotto());
        profileSchoolInfoBlock.setSchoolSong(profile.getSchoolSong());
        mobileRestFulModel.setRows(profileSchoolInfoBlock);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage(SpringUtils.getMessage("获取学校信息成功"));
        return mobileRestFulModel;

    }
}
