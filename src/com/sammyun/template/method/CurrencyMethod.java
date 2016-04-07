/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.template.method;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 模板方法 - 货币格式化
 */
@SuppressWarnings("deprecation")
@Component("currencyMethod")
public class CurrencyMethod implements TemplateMethodModel
{

    @SuppressWarnings({"rawtypes", "unused"})
    public Object exec(List arguments) throws TemplateModelException
    {
        if (arguments != null && !arguments.isEmpty() && arguments.get(0) != null
                && StringUtils.isNotEmpty(arguments.get(0).toString()))
        {
            boolean showSign = false;
            boolean showUnit = false;
            if (arguments.size() == 2)
            {
                if (arguments.get(1) != null)
                {
                    showSign = Boolean.valueOf(arguments.get(1).toString());
                }
            }
            else if (arguments.size() > 2)
            {
                if (arguments.get(1) != null)
                {
                    showSign = Boolean.valueOf(arguments.get(1).toString());
                }
                if (arguments.get(2) != null)
                {
                    showUnit = Boolean.valueOf(arguments.get(2).toString());
                }
            }
            // Setting setting = SettingUtils.get();
            // BigDecimal amount = new
            // BigDecimal(arguments.get(0).toString());
            /**
             * String price = setting.setScale(amount).toString(); if
             * (showSign) { price = setting.getCurrencySign() + price; } if
             * (showUnit) { price += setting.getCurrencyUnit(); }
             **/
            return new SimpleScalar("0");
        }
        return null;
    }

}
