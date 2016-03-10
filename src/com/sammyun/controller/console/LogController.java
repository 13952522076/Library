/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.entity.Log;
import com.sammyun.service.LogService;

/**
 * Controller - 管理日志
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("adminLogController")
@RequestMapping("/console/log")
public class LogController extends BaseController
{

    @Resource(name = "logServiceImpl")
    private LogService logService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", logService.findPage(pageable));
        model.addAttribute("menuId", Log.class.getSimpleName());
        return "/console/log/list";
    }

    /**
     * 查看
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view(Long id, ModelMap model)
    {
        model.addAttribute("log", logService.find(id));
        model.addAttribute("menuId", Log.class.getSimpleName());
        return "/console/log/view";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        logService.delete(ids);
        return SUCCESS_MESSAGE;
    }

    /**
     * 清空
     */
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    public @ResponseBody
    Message clear()
    {
        logService.clear();
        return SUCCESS_MESSAGE;
    }

}
