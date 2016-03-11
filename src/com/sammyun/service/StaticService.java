/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service;

import java.util.Map;

import com.sammyun.entity.dict.DictStudent;

/**
 * Service - 静态化
 * 


 */
public interface StaticService
{

    /**
     * 生成静态
     * 
     * @param templatePath 模板文件路径
     * @param staticPath 静态文件路径
     * @param model 数据
     * @return 生成数量
     */
    int build(String templatePath, String staticPath, Map<String, Object> model);

    /**
     * 生成静态
     * 
     * @param templatePath 模板文件路径
     * @param staticPath 静态文件路径
     * @return 生成数量
     */
    int build(String templatePath, String staticPath);

    /**
     * 生成首页静态
     * 
     * @return 生成数量
     */
    int buildIndex();

    /**
     * 生成Sitemap
     * 
     * @return 生成数量
     */
    int buildSitemap();

    /**
     * 生成其它静态
     * 
     * @return 生成数量
     */
    int buildOther();

    /**
     * 生成所有静态
     * 
     * @return 生成数量
     */
    int buildAll();

    /**
     * 删除静态
     * 
     * @param staticPath 静态文件路径
     * @return 删除数量
     */
    int delete(String staticPath);

    /**
     * 删除首页静态
     * 
     * @return 删除数量
     */
    int deleteIndex();

    /**
     * 删除其它静态
     * 
     * @return 删除数量
     */
    int deleteOther();

    /**
     * 设置语言
     * 
     * @param lang 语言
     * @see [类、类#方法、类#成员]
     */
    void setLocale(String lang);
    
    
    /**
     * 生成静态
     * 
     * @param dictStudent
     *            学生
     * @return 生成数量
     */
    int build(DictStudent dictStudent);
    
    
    /**
     * 删除静态
     * 
     * @param dictStudent
     *            学生
     * @return 删除数量
     */
    int delete(DictStudent dictStudent);

}
