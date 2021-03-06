/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.impl;

import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.sammyun.service.CacheService;
import com.sammyun.util.SettingUtils;

import freemarker.template.TemplateModelException;

/**
 * Service - 缓存
 * 


 */
@Service("cacheServiceImpl")
public class CacheServiceImpl implements CacheService
{

    @Resource(name = "ehCacheManager")
    private CacheManager cacheManager;

    @Resource(name = "messageSource")
    private ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource;

    @Resource(name = "freeMarkerConfigurer")
    private FreeMarkerConfigurer freeMarkerConfigurer;

    public String getDiskStorePath()
    {
        return cacheManager.getConfiguration().getDiskStoreConfiguration().getPath();
    }

    public int getCacheSize()
    {
        int cacheSize = 0;
        String[] cacheNames = cacheManager.getCacheNames();
        if (cacheNames != null)
        {
            for (String cacheName : cacheNames)
            {
                Ehcache cache = cacheManager.getEhcache(cacheName);
                if (cache != null)
                {
                    cacheSize += cache.getSize();
                }
            }
        }
        return cacheSize;
    }

    @CacheEvict(value = {"setting", "authorization", "logConfig", "template", "area", "seo", "adPosition",
            "navigation", "friendLink"}, allEntries = true)
    public void clear()
    {
        reloadableResourceBundleMessageSource.clearCache();
        try
        {
            freeMarkerConfigurer.getConfiguration().setSharedVariable("setting", SettingUtils.get());
        }
        catch (TemplateModelException e)
        {
            e.printStackTrace();
        }
        freeMarkerConfigurer.getConfiguration().clearTemplateCache();
    }

}
