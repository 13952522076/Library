/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.DateEditor;
import com.sammyun.Filter;
import com.sammyun.Filter.Operator;
import com.sammyun.Message;
import com.sammyun.entity.Log;
import com.sammyun.template.directive.FlashMessageDirective;
import com.sammyun.util.ParamUtil;
import com.sammyun.util.SpringUtils;

/**
 * Controller - 基类
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public class BaseController
{

    /** 错误视图 */
    protected static final String ERROR_VIEW = "/console/common/error";

    /** 错误消息 */
    protected static final Message ERROR_MESSAGE = Message.error("console.message.error");

    /** 成功消息 */
    protected static final Message SUCCESS_MESSAGE = Message.success("console.message.success");

    /** "验证结果"参数名称 */
    private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Resource(name = "validator")
    private Validator validator;

    /**
     * 数据绑定
     * 
     * @param binder WebDataBinder
     */
    @InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class, new DateEditor(true));
    }

    /**
     * 数据验证
     * 
     * @param target 验证对象
     * @param groups 验证组
     * @return 验证结果
     */
    protected boolean isValid(Object target, Class<?>... groups)
    {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);
        if (constraintViolations.isEmpty())
        {
            return true;
        }
        else
        {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations,
                    RequestAttributes.SCOPE_REQUEST);
            return false;
        }
    }

    /**
     * 数据验证
     * 
     * @param type 类型
     * @param property 属性
     * @param value 值
     * @param groups 验证组
     * @return 验证结果
     */
    protected boolean isValid(Class<?> type, String property, Object value, Class<?>... groups)
    {
        Set<?> constraintViolations = validator.validateValue(type, property, value, groups);
        if (constraintViolations.isEmpty())
        {
            return true;
        }
        else
        {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            requestAttributes.setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations,
                    RequestAttributes.SCOPE_REQUEST);
            return false;
        }
    }

    /**
     * 货币格式化
     * 
     * @param amount 金额
     * @param showSign 显示标志
     * @param showUnit 显示单位
     * @return 货币格式化
     */
    protected String currency(BigDecimal amount, boolean showSign, boolean showUnit)
    {
        /**
         * Setting setting = SettingUtils.get(); String price =
         * setting.setScale(amount).toString(); if (showSign) { price =
         * setting.getCurrencySign() + price; } if (showUnit) { price +=
         * setting.getCurrencyUnit(); }
         **/
        return "price";
    }

    /**
     * 获取国际化消息
     * 
     * @param code 代码
     * @param args 参数
     * @return 国际化消息
     */
    protected String message(String code, Object... args)
    {
        return SpringUtils.getMessage(code, args);
    }

    /**
     * 添加瞬时消息
     * 
     * @param redirectAttributes RedirectAttributes
     * @param message 消息
     */
    protected void addFlashMessage(RedirectAttributes redirectAttributes, Message message)
    {
        if (redirectAttributes != null && message != null)
        {
            redirectAttributes.addFlashAttribute(FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME, message);
        }
    }

    /**
     * 添加日志
     * 
     * @param content 内容
     */
    protected void addLog(String content)
    {
        if (content != null)
        {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            requestAttributes.setAttribute(Log.LOG_CONTENT_ATTRIBUTE_NAME, content, RequestAttributes.SCOPE_REQUEST);
        }
    }

    /**
     * 构建Filters
     * 
     * @param request
     * @return
     * @see [类、类#方法、类#成员]
     */
    protected List<Filter> getFilters(HttpServletRequest request)
    {
        List<Filter> filters = new ArrayList<Filter>();
        Enumeration paramEnu = request.getParameterNames();
        while (paramEnu.hasMoreElements())
        {
            String paramName = (String) paramEnu.nextElement();

            if (paramName.startsWith("Q_"))
            {
                String paramValue = request.getParameter(paramName);
                String[] fieldInfo = paramName.split("[_]");
                Object value = null;
                if ((fieldInfo != null) && (fieldInfo.length == 4))
                {
                    value = ParamUtil.convertObject(fieldInfo[2], paramValue);
                    if (value != null)
                    {
                        Filter filter = new Filter(fieldInfo[1], Operator.valueOf(fieldInfo[3].toString()), value);
                        filters.add(filter);
                    }
                }
                else
                {
                    logger.error("Query param name [" + paramName + "] is not right format.");
                }

            }
        }
        return null;
    }

}
