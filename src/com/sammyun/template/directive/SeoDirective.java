/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.template.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sammyun.entity.Seo;
import com.sammyun.entity.Seo.Type;
import com.sammyun.service.SeoService;
import com.sammyun.util.FreemarkerUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - SEO设置
 * 


 */
@Component("seoDirective")
public class SeoDirective extends BaseDirective
{

    /** "类型"参数名称 */
    private static final String TYPE_PARAMETER_NAME = "type";

    /** 变量名称 */
    private static final String VARIABLE_NAME = "seo";

    @Resource(name = "seoServiceImpl")
    private SeoService seoService;

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException
    {
        Type type = FreemarkerUtils.getParameter(TYPE_PARAMETER_NAME, Type.class, params);

        Seo seo;
        boolean useCache = useCache(env, params);
        String cacheRegion = getCacheRegion(env, params);
        if (useCache)
        {
            seo = seoService.find(type, cacheRegion);
        }
        else
        {
            seo = seoService.find(type);
        }
        setLocalVariable(VARIABLE_NAME, seo, env, body);
    }

}
