/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.template.directive;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.sammyun.interceptor.ExecuteTimeInterceptor;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - 执行时间
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Component("executeTimeDirective")
public class ExecuteTimeDirective extends BaseDirective
{

    /** 变量名称 */
    private static final String VARIABLE_NAME = "executeTime";

    @SuppressWarnings("rawtypes")
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException
    {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null)
        {
            Long executeTime = (Long) requestAttributes.getAttribute(
                    ExecuteTimeInterceptor.EXECUTE_TIME_ATTRIBUTE_NAME, RequestAttributes.SCOPE_REQUEST);
            if (executeTime != null)
            {
                setLocalVariable(VARIABLE_NAME, executeTime, env, body);
            }
        }
    }

}
