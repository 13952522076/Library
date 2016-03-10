package com.sammyun.controller.api;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.sammyun.controller.api.bean.SystemSuggestContentBean;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.entity.Member;
import com.sammyun.service.MemberService;
import com.sammyun.service.SystemSuggestService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.SpringUtils;

/**
 * api - 意见反馈
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("systemSuggest")
@Path("/systemSuggest")
public class SystemSuggestApi
{
    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "systemSuggestServiceImpl")
    private SystemSuggestService systemSuggestService;

    /**
     * 查询周计划表 <功能详细描述>
     * 
     * @param schoolYearMngId
     * @param dictClassId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/content")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileRestFulModel content(SystemSuggestContentBean systemSuggestContentBean)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        Long memberId = systemSuggestContentBean.getMemberId();
        if (memberId == null)
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
        if (systemSuggestContentBean.getSuggestContent() == null
                || systemSuggestContentBean.getSuggestContent().trim() == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("请填写内容");
            return mobileRestFulModel;
        }

        com.sammyun.entity.SystemSuggest systemSuggest = new com.sammyun.entity.SystemSuggest();
        systemSuggest.setSuggestContent(systemSuggestContentBean.getSuggestContent());
        if (systemSuggestContentBean.getSuggestDate() != null)
        {
            systemSuggest.setSuggestDate(DateUtil.string2Date(systemSuggestContentBean.getSuggestDate()));

        }
        systemSuggest.setContactInfo(member.getMobile());
        systemSuggest.setDeviceModel(systemSuggestContentBean.getDeviceModel());
        systemSuggest.setUuid(systemSuggestContentBean.getUuid());
        systemSuggest.setOsName(systemSuggestContentBean.getOsName());
        systemSuggest.setDeviceOs(systemSuggestContentBean.getDeviceOs());
        systemSuggest.setOsVersion(systemSuggestContentBean.getOsVersion());
        systemSuggest.setUserName(member.getUsername());
        systemSuggest.setAppver(systemSuggestContentBean.getAppver());
        systemSuggest.setAppid(systemSuggestContentBean.getAppid());
        systemSuggest.setEnv(systemSuggestContentBean.getEnv());
        systemSuggest.setMember(member);
        systemSuggestService.save(systemSuggest);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("提交成功！");
        return mobileRestFulModel;
    }
}
