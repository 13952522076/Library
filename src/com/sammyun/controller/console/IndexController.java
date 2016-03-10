/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sammyun.service.SearchService;

/**
 * Controller - 索引
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("adminIndexController")
@RequestMapping("/console/index")
public class IndexController extends BaseController
{

    @Resource(name = "searchServiceImpl")
    private SearchService searchService;

    /**
     * 生成类型
     */
    public enum BuildType
    {
        /**
         * 文章
         */
        article,
        /**
         * 商品
         */
        product
    }

    /**
     * 生成索引
     */
    @RequestMapping(value = "/build", method = RequestMethod.GET)
    public String build(ModelMap model)
    {
        model.addAttribute("buildTypes", BuildType.values());
        return "/console/index/build";
    }

    /**
     * 生成索引
     */
    @RequestMapping(value = "/build", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> build(BuildType buildType, Boolean isPurge, Integer first, Integer count)
    {
        long startTime = System.currentTimeMillis();
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
        if (buildType == BuildType.article)
        {
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
