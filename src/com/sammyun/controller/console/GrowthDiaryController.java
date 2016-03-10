package com.sammyun.controller.console;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sammyun.Filter;
import com.sammyun.Filter.Mold;
import com.sammyun.Filter.Operator;
import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.entity.Member;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.gd.GrowthDiary;
import com.sammyun.service.AdminService;
import com.sammyun.service.MemberService;
import com.sammyun.service.gd.DiaryAgreeService;
import com.sammyun.service.gd.DiaryPictureService;
import com.sammyun.service.gd.DiaryTagService;
import com.sammyun.service.gd.DiaryTranspondService;
import com.sammyun.service.gd.GrowthDiaryService;

/**
 * Controller - 成长记
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("growthDiaryController")
@RequestMapping("/console/growthDiary")
public class GrowthDiaryController extends BaseController
{
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "growthDiaryServiceImpl")
    private GrowthDiaryService growthDiaryService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "diaryTagServiceImpl")
    private DiaryTagService diaryTagService;

    @Resource(name = "diaryPictureServiceImpl")
    private DiaryPictureService diaryPictureService;

    @Resource(name = "diaryAgreeServiceImpl")
    private DiaryAgreeService diaryAgreeService;

    @Resource(name = "diaryTranspondServiceImpl")
    private DiaryTranspondService diaryTranspondService;

    /**
     * 成长记列表
     * 
     * @param pageable
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, String realName, String startDate,String endDate,ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        Set<Member> members = dictSchool.getMembers();
        if (members != null && members.size() > 0)
        {
            if (realName != null)
            {
                model.addAttribute("realName", realName);
                List<Member> getMembers = memberService.findByRealName(realName);
                members.retainAll(getMembers);
            }
            if (startDate != null)
            {
                Filter startTimeFilter = new Filter();
                startTimeFilter.setOperator(Operator.gt);
                startTimeFilter.setMold(Mold.dl);
                startTimeFilter.setProperty("createDate");
                startTimeFilter.setValue(startDate);
                pageable.addFilters(startTimeFilter);

            }
            if (endDate != null)
            {
                Filter endTimeFilter = new Filter();
                endTimeFilter.setOperator(Operator.lt);
                endTimeFilter.setMold(Mold.dg);
                endTimeFilter.setProperty("createDate");
                endTimeFilter.setValue(endDate+" 23:59:59");
                pageable.addFilters(endTimeFilter);
            }
            if (members != null && members.size() > 0)
            {
                Filter filter = new Filter("member", Operator.in, members);
                pageable.addFilters(filter);
            }
            else
            {
                Filter filter = new Filter("id", Operator.eq, 0L);// 构建一个空的过滤器
                pageable.addFilters(filter);
            }
        }
        else
        {
            Filter filter = new Filter("id", Operator.eq, 0L);// 构建一个空的过滤器
            pageable.addFilters(filter);
        }

        model.addAttribute("page", growthDiaryService.findPage(pageable));
        model.addAttribute("menuId", GrowthDiary.class.getSimpleName());
        model.addAttribute("returnStartDate", startDate);
        model.addAttribute("returnEndDate", endDate);
        return "/console/growthDiary/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        growthDiaryService.delete(ids);
        return SUCCESS_MESSAGE;
    }

    /**
     * 进入详情页
     * 
     * @param id
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Long id, ModelMap model)
    {
        GrowthDiary growthDiary = growthDiaryService.find(id);
        // 基础数据包括：点赞人数，点赞人名列表，分享数，地理位置；
        List<Member> agreeMembers = diaryAgreeService.findMemberByGrowthDiary(growthDiary);
        model.addAttribute("agreeMembers", agreeMembers);
        model.addAttribute("growthDiary", growthDiary);
        model.addAttribute("menuId", GrowthDiary.class.getSimpleName());
        return "/console/growthDiary/detail";
    }
}
