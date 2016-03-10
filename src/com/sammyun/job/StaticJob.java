/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.job;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sammyun.service.StaticService;

/**
 * Job - 静态化
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Component("staticJob")
@Lazy(false)
public class StaticJob
{

    @Resource(name = "staticServiceImpl")
    private StaticService staticService;

    /**
     * 生成静态
     */
    @Scheduled(cron = "${job.static_build.cron}")
    public void build()
    {
        staticService.buildAll();
    }

}
