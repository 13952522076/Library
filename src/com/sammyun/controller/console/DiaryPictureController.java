package com.sammyun.controller.console;

import java.util.HashSet;
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
import com.sammyun.entity.gd.DiaryPicture;
import com.sammyun.entity.gd.GrowthDiary;
import com.sammyun.service.AdminService;
import com.sammyun.service.MemberService;
import com.sammyun.service.gd.DiaryPictureService;
import com.sammyun.service.gd.DiaryTagService;

/**
 * Controller - 成长记图片附件
 * 


 */
@Controller("diaryPictureController")
@RequestMapping("/console/diaryPicture")
public class DiaryPictureController extends BaseController
{
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "diaryTagServiceImpl")
    private DiaryTagService diaryTagService;

    @Resource(name = "diaryPictureServiceImpl")
    private DiaryPictureService diaryPictureService;

    /**
     * 成长记图片列表
     * 
     * @param pageable
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, String realName,String startDate,String endDate, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        Set<Member> members = dictSchool.getMembers();
        Set<GrowthDiary> growthDiaries = new HashSet<GrowthDiary>();
        if (members != null && members.size() > 0)
        {
            if (realName != null)
            {
                model.addAttribute("realName", realName);
                List<Member> getMembers = memberService.findByRealName(realName.trim());
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
            if (members.size() > 0)
            {
                for (Member member : members)
                {
                    growthDiaries.addAll(member.getGrowthDiaries());
                }
            }
        }
        if (growthDiaries.size() > 0)
        {
            Filter filter = new Filter("growthDiary", Operator.in, growthDiaries);
            pageable.addFilters(filter);
        }
        else
        {
            Filter filter = new Filter("id", Operator.eq, 0L);
            pageable.addFilters(filter);
        }
        model.addAttribute("page", diaryPictureService.findPage(pageable));
        model.addAttribute("menuId", DiaryPicture.class.getSimpleName());
        model.addAttribute("returnStartDate", startDate);
        model.addAttribute("returnEndDate", endDate);
        return "/console/diaryPicture/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        diaryPictureService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
