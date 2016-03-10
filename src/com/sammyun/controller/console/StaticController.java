/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sammyun.service.StaticService;
import com.sammyun.util.WebUtils;

/**
 * Controller - 静态化
 * 
 * @author Sencloud Team

 */
@Controller("adminStaticController")
@RequestMapping("/console/static")
public class StaticController extends BaseController
{

    /**
     * 生成类型
     */
    public enum BuildType
    {
        /**
         * 首页
         */
        index,
        /**
         * 文章
         */
        article,
        /**
         * 商品
         */
        product,
        /**
         * 其它
         */
        other
    }

    public final static String langCookieName = "lang";

    @Resource(name = "staticServiceImpl")
    private StaticService staticService;

    /**
     * 生成静态
     */
    @RequestMapping(value = "/build", method = RequestMethod.GET)
    public String build(ModelMap model)
    {
        model.addAttribute("buildTypes", BuildType.values());
        model.addAttribute("defaultBeginDate", DateUtils.addDays(new Date(), -7));
        model.addAttribute("defaultEndDate", new Date());
        return "/console/static/build";
    }

    /**
     * 生成静态
     */
    @RequestMapping(value = "/build", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> build(BuildType buildType, Long articleCategoryId, Long productCategoryId, Date beginDate,
            Date endDate, Integer first, Integer count, String lang, HttpServletRequest request,
            HttpServletResponse response)
    {
        long startTime = System.currentTimeMillis();
        if (beginDate != null)
        {
            Calendar calendar = DateUtils.toCalendar(beginDate);
            calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
            beginDate = calendar.getTime();
        }
        if (endDate != null)
        {
            Calendar calendar = DateUtils.toCalendar(endDate);
            calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
            calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
            calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
            endDate = calendar.getTime();
        }
        if (first == null || first < 0)
        {
            first = 0;
        }
        if (count == null || count <= 0)
        {
            count = 50;
        }
        int buildCount = 0;
        boolean isCompleted = true;
        if (lang == null)
        {
            lang = "zh_CN";
        }
        staticService.setLocale(lang);
        WebUtils.removeCookie(request, response, langCookieName);
        WebUtils.addCookie(request, response, langCookieName, lang);
        if (buildType == BuildType.index)
        {
            buildCount = staticService.buildIndex();
        }
        else if (buildType == BuildType.article)
        {
        }
        else if (buildType == BuildType.other)
        {
            buildCount = staticService.buildOther();
        }
        long endTime = System.currentTimeMillis();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("first", first);
        map.put("buildCount", buildCount);
        map.put("buildTime", endTime - startTime);
        map.put("isCompleted", isCompleted);
        return map;
    }

}
