/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Setting;
import com.sammyun.service.CacheService;
import com.sammyun.util.SettingUtils;

/**
 * Controller - 统计
 * 


 */
@Controller("statisticsController")
@RequestMapping("/console/statistics")
public class StatisticsController extends BaseController
{

    @Resource(name = "cacheServiceImpl")
    private CacheService cacheService;

    /**
     * 查看
     */
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String view()
    {
        return "/console/statistics/view";
    }

    /**
     * 设置
     */
    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public String setting()
    {
        return "/console/statistics/setting";
    }

    /**
     * 设置
     */
    @RequestMapping(value = "/setting", method = RequestMethod.POST)
    public String setting(@RequestParam(defaultValue = "false") Boolean isEnabled, RedirectAttributes redirectAttributes)
    {
        Setting setting = SettingUtils.get();
        if (isEnabled)
        {
            //if (StringUtils.isEmpty(setting.getCnzzSiteId()) || StringUtils.isEmpty(setting.getCnzzPassword()))
            {
                String domain = setting.getSiteUrl().replaceAll(
                        "(^[\\s\\S]*?[^a-zA-Z0-9-.]+)|([^a-zA-Z0-9-.][\\s\\S]*$)", "");
                try
                {
                    String createAccountUrl = "http://intf.cnzz.com/user/companion/shopxx.php?domain=" + domain
                            + "&key=" + DigestUtils.md5Hex(domain + "Lfg4uP0H");
                    URLConnection urlConnection = new URL(createAccountUrl).openConnection();
                    BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    String line = null;
                    while ((line = in.readLine()) != null)
                    {
                        if (line.contains("@"))
                        {
                            break;
                        }
                    }
                    if (line != null)
                    {
                        //setting.setCnzzSiteId(StringUtils.substringBefore(line, "@"));
                        //setting.setCnzzPassword(StringUtils.substringAfter(line, "@"));
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        //setting.setIsCnzzEnabled(isEnabled);
        SettingUtils.set(setting);
        cacheService.clear();
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:setting.ct";
    }

}
