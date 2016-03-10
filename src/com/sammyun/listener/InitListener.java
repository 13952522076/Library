/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.listener;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.sammyun.service.CacheService;
import com.sammyun.service.SearchService;
import com.sammyun.service.StaticService;
import com.sammyun.service.system.SystemDictService;

/**
 * Listener - 初始化
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Component("initListener")
public class InitListener implements ServletContextAware, ApplicationListener<ContextRefreshedEvent>
{

    /** 安装初始化配置文件 */
    private static final String INSTALL_INIT_CONFIG_FILE_PATH = "/install_init.conf";

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(InitListener.class);

    /** servletContext */
    private ServletContext servletContext;

    @Value("${system.version}")
    private String systemVersion;

    @Resource(name = "staticServiceImpl")
    private StaticService staticService;

    @Resource(name = "cacheServiceImpl")
    private CacheService cacheService;

    @Resource(name = "searchServiceImpl")
    private SearchService searchService;

    @Resource(name = "systemDictServiceImpl")
    private SystemDictService systemDictService;

    public void setServletContext(ServletContext servletContext)
    {
        this.servletContext = servletContext;
    }

    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        if (servletContext != null && contextRefreshedEvent.getApplicationContext().getParent() == null)
        {
            String info = "I|n|i|t|i|a|l|i|z|i|n|g| |盛|云|幼|教|平|台| |" + systemVersion;
            logger.info(info.replace("|", ""));

            /** 缓存 系统字典 */
            systemDictService.set();

            File installInitConfigFile = new File(servletContext.getRealPath(INSTALL_INIT_CONFIG_FILE_PATH));
            if (installInitConfigFile.exists())
            {
                cacheService.clear();
                staticService.buildAll();
                // searchService.purge();
                // searchService.index();
                installInitConfigFile.delete();
            }
            else
            {
                //staticService.buildIndex();
                staticService.buildOther();
            }
        }
    }

}
