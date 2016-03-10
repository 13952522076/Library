/*
 * Copyright 2012-2014 sencloud.com.cn. All rights reserved.
 * Support: http://www.sencloud.com.cn
 * License: http://www.sencloud.com.cn/license
 */
package com.sammyun.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.sammyun.Template;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.service.StaticService;
import com.sammyun.service.TemplateService;
import com.sammyun.util.FreemarkerUtils;

/**
 * Service - 静态化
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("staticServiceImpl")
public class StaticServiceImpl implements StaticService, ServletContextAware
{

    /** Sitemap最大地址数 */
    private static final Integer SITEMAP_MAX_SIZE = 40000;

    /** servletContext */
    private ServletContext servletContext;

    @Resource(name = "freeMarkerConfigurer")
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Resource(name = "templateServiceImpl")
    private TemplateService templateService;

    public void setServletContext(ServletContext servletContext)
    {
        this.servletContext = servletContext;
    }

    @Transactional(readOnly = true)
    public int build(String templatePath, String staticPath, Map<String, Object> model)
    {
        Assert.hasText(templatePath);
        Assert.hasText(staticPath);

        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        Writer writer = null;
        try
        {
            freemarker.template.Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templatePath);
            File staticFile = new File(servletContext.getRealPath(staticPath));
            File staticDirectory = staticFile.getParentFile();
            if (!staticDirectory.exists())
            {
                staticDirectory.mkdirs();
            }
            fileOutputStream = new FileOutputStream(staticFile);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
            writer = new BufferedWriter(outputStreamWriter);
            template.process(model, writer);
            writer.flush();
            return 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly(writer);
            IOUtils.closeQuietly(outputStreamWriter);
            IOUtils.closeQuietly(fileOutputStream);
        }
        return 0;
    }

    @Transactional(readOnly = true)
    public int build(String templatePath, String staticPath)
    {
        return build(templatePath, staticPath, null);
    }

    @Transactional(readOnly = true)
    public int buildIndex()
    {
        Template template = templateService.get("index");
        return build(template.getTemplatePath(), template.getStaticPath());
    }

    @Transactional(readOnly = true)
    public int buildSitemap()
    {
        int buildCount = 0;
        Template sitemapIndexTemplate = templateService.get("sitemapIndex");
        Template sitemapTemplate = templateService.get("sitemap");
        Map<String, Object> model = new HashMap<String, Object>();
        List<String> staticPaths = new ArrayList<String>();
        for (int step = 0, index = 0, first = 0, count = SITEMAP_MAX_SIZE;;)
        {
            try
            {
                model.put("index", index);
                String templatePath = sitemapTemplate.getTemplatePath();
                String staticPath = FreemarkerUtils.process(sitemapTemplate.getStaticPath(), model);
                //if (step == 10)
                //{
                //}
                //else
                //{
                 //   buildCount = 0;
                //}
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Transactional(readOnly = true)
    public int buildOther()
    {
        int buildCount = 0;
        Template shopCommonJsTemplate = templateService.get("shopCommonJs");
        Template consoleCommonJsTemplate = templateService.get("consoleCommonJs");
        buildCount += build(shopCommonJsTemplate.getTemplatePath(), shopCommonJsTemplate.getStaticPath());
        buildCount += build(consoleCommonJsTemplate.getTemplatePath(), consoleCommonJsTemplate.getStaticPath());
        return buildCount;
    }

    @Transactional(readOnly = true)
    public int buildAll()
    {
        int buildCount = 0;

        buildIndex();
        // buildSitemap();
        buildOther();
        return buildCount;
    }

    @Transactional(readOnly = true)
    public int delete(String staticPath)
    {
        Assert.hasText(staticPath);

        File staticFile = new File(servletContext.getRealPath(staticPath));
        if (staticFile.exists())
        {
            staticFile.delete();
            return 1;
        }
        return 0;
    }

    @Transactional(readOnly = true)
    public int deleteIndex()
    {
        Template template = templateService.get("index");
        return delete(template.getStaticPath());
    }

    @Transactional(readOnly = true)
    public int deleteOther()
    {
        int deleteCount = 0;
        Template shopCommonJsTemplate = templateService.get("shopCommonJs");
        Template adminCommonJsTemplate = templateService.get("adminCommonJs");
        deleteCount += delete(shopCommonJsTemplate.getStaticPath());
        deleteCount += delete(adminCommonJsTemplate.getStaticPath());
        return deleteCount;
    }

    @Override
    public void setLocale(String lang)
    {
        // locale = new Locale(lang);
    }

    @Override
    public int build(DictStudent dictStudent)
    {
        Assert.notNull(dictStudent);

        delete(dictStudent);
        Template template = templateService.get("dictStudentContent");
        int buildCount = 0;
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("dictStudent", dictStudent);
            buildCount += build(template.getTemplatePath(), dictStudent.getPath(), model);
        return buildCount;
    }

    @Override
    public int delete(DictStudent dictStudent)
    {
        Assert.notNull(dictStudent);
        return delete(dictStudent.getPath());
    }
}
