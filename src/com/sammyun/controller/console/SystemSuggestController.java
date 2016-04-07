/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sammyun.Filter;
import com.sammyun.Filter.Mold;
import com.sammyun.Filter.Operator;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.SystemSuggest;
import com.sammyun.service.SystemSuggestService;

/**
 * Controller - 意见反馈
 * 


 */
@Controller("adminSystemSuggestController")
@RequestMapping("/console/systemSuggest")
public class SystemSuggestController extends BaseController
{

    @Resource(name = "systemSuggestServiceImpl")
    private SystemSuggestService systemSuggestService;

    /**
     * 列表
     */
    @SuppressWarnings("unused")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, MemberType memberType, String mobile, String searchName, String startDate,
            String endDate, ModelMap model, HttpServletRequest request)
    {
        String meunId = SystemSuggest.class.getSimpleName();

        if (mobile != null)
        {
            Filter filter = new Filter("contactInfo", Operator.like, "%" + mobile + "%");
            pageable.addFilters(filter);
            model.addAttribute("mobile", mobile);
        }
        if (startDate != null)
        {
            Filter startTimeFilter = new Filter();
            startTimeFilter.setOperator(Operator.gt);
            startTimeFilter.setMold(Mold.dl);
            startTimeFilter.setProperty("suggestDate");
            startTimeFilter.setValue(startDate);
            pageable.addFilters(startTimeFilter);

        }
        if (endDate != null)
        {
            Filter endTimeFilter = new Filter();
            endTimeFilter.setOperator(Operator.lt);
            endTimeFilter.setMold(Mold.dg);
            endTimeFilter.setProperty("suggestDate");
            endTimeFilter.setValue(endDate+" 23:59:59");
            pageable.addFilters(endTimeFilter);
        }
        // 下面的方法为了解决两个问题：1hibernate的懒加载导致freemaker显示异常。2member被删除或者为空时不显示。
        Page<SystemSuggest> page = new Page<SystemSuggest>();
        if (searchName != null)
        {
            model.addAttribute("searchName", searchName);
            page = systemSuggestService.findByRealName(searchName, pageable);
        }
        else
        {
            page = systemSuggestService.findPage(pageable);
        }
        // List<SystemSuggest> systemSuggests = page.getContent();
        List<SystemSuggest> systemSuggests = new ArrayList<SystemSuggest>();
        systemSuggests.addAll(page.getContent());

        if (systemSuggests != null)
        {
            for (SystemSuggest systemSuggest : page.getContent())
            {
                Member member = systemSuggest.getMember();
                try
                {
                    String tempString = member.getRealName();
                }
                catch (Exception e)
                {
                    // System.out.println("1");
                    systemSuggests.remove(systemSuggest);
                }
                // if(member!=null){
                // String tempString = member.getRealName();//为了hibernate的懒加载
                // }else{
                // page.getContent().remove(systemSuggest);
                // }
            }
        }

        Page<SystemSuggest> newPage = new Page<SystemSuggest>(systemSuggests, systemSuggests.size(), page.getPageable());

        model.addAttribute("page", newPage);
        model.addAttribute("menuId", meunId);
        model.addAttribute("memberType", memberType);

        model.addAttribute("mobile", mobile);
        model.addAttribute("searchName", searchName);
        model.addAttribute("returnStartDate", startDate);
        model.addAttribute("returnEndDate", endDate);

        return "/console/systemSuggest/list";
    }

}
