/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.CommonAttributes;
import com.sammyun.Message;
import com.sammyun.Setting;
import com.sammyun.Setting.AccountLockType;
import com.sammyun.Setting.CaptchaType;
import com.sammyun.service.CacheService;
import com.sammyun.service.FileService;
import com.sammyun.service.StaticService;
import com.sammyun.util.SettingUtils;
import com.sun.mail.smtp.SMTPSendFailedException;
import com.sun.mail.smtp.SMTPSenderFailedException;

/**
 * Controller - 系统设置
 */
@Controller("adminstingController")
@RequestMapping("/console/setting")
public class SettingController extends BaseController
{

    @Resource(name = "fileServiceImpl")
    private FileService fileService;

    @Resource(name = "cacheServiceImpl")
    private CacheService cacheService;

    @Resource(name = "staticServiceImpl")
    private StaticService staticService;

    /**
     * 邮件测试
     */
    @RequestMapping(value = "/mail_test", method = RequestMethod.POST)
    public @ResponseBody
    Message mailTest(String smtpFromMail, String smtpHost, Integer smtpPort, String smtpUsername, String smtpPassword,
            String toMail)
    {
        if (StringUtils.isEmpty(toMail))
        {
            return ERROR_MESSAGE;
        }
        if (StringUtils.isEmpty(smtpPassword))
        {
            // smtpPassword = setting.getSmtpPassword();
        }
        try
        {
            if (!isValid(Setting.class, "smtpFromMail", smtpFromMail) || !isValid(Setting.class, "smtpHost", smtpHost)
                    || !isValid(Setting.class, "smtpPort", smtpPort)
                    || !isValid(Setting.class, "smtpUsername", smtpUsername))
            {
                return ERROR_MESSAGE;
            }
        }
        catch (MailSendException e)
        {
            Exception[] messageExceptions = e.getMessageExceptions();
            if (messageExceptions != null)
            {
                for (Exception exception : messageExceptions)
                {
                    if (exception instanceof SMTPSendFailedException)
                    {
                        SMTPSendFailedException smtpSendFailedException = (SMTPSendFailedException) exception;
                        Exception nextException = smtpSendFailedException.getNextException();
                        if (nextException instanceof SMTPSenderFailedException)
                        {
                            return Message.error("console.setting.mailTestSenderFailed");
                        }
                    }
                    else if (exception instanceof MessagingException)
                    {
                        MessagingException messagingException = (MessagingException) exception;
                        Exception nextException = messagingException.getNextException();
                        if (nextException instanceof UnknownHostException)
                        {
                            return Message.error("console.setting.mailTestUnknownHost");
                        }
                        else if (nextException instanceof ConnectException)
                        {
                            return Message.error("console.setting.mailTestConnect");
                        }
                    }
                }
            }
            return Message.error("console.setting.mailTestError");
        }
        catch (MailAuthenticationException e)
        {
            return Message.error("console.setting.mailTestAuthentication");
        }
        catch (Exception e)
        {
            return Message.error("console.setting.mailTestError");
        }
        return Message.success("admin.setting.mailTestSuccess");
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap model)
    {
        // model.addAttribute("watermarkPositions",
        // WatermarkPosition.values());
        // model.addAttribute("roundTypes", RoundType.values());
        model.addAttribute("captchaTypes", CaptchaType.values());
        model.addAttribute("accountLockTypes", AccountLockType.values());
        // model.addAttribute("stockAllocationTimes",
        // StockAllocationTime.values());
        // model.addAttribute("reviewAuthorities", ReviewAuthority.values());
        // model.addAttribute("consultationAuthorities",
        // ConsultationAuthority.values());
        model.addAttribute("menuId", Setting.class.getSimpleName());
        return "/console/setting/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Setting setting, MultipartFile watermarkImageFile, RedirectAttributes redirectAttributes,
            ModelMap model)
    {
        if (!isValid(setting))
        {
            return ERROR_VIEW;
        }
        if (setting.getUsernameMinLength() > setting.getUsernameMaxLength()
                || setting.getPasswordMinLength() > setting.getPasswordMinLength())
        {
            return ERROR_VIEW;
        }

        SettingUtils.set(setting);
        cacheService.clear();
        staticService.buildIndex();
        staticService.buildOther();

        OutputStream outputStream = null;
        try
        {
            org.springframework.core.io.Resource resource = new ClassPathResource(
                    CommonAttributes.PRESCHOOLEDU_PROPERTIES_PATH);
            Properties properties = PropertiesLoaderUtils.loadProperties(resource);
            String templateUpdateDelay = properties.getProperty("template.update_delay");
            String messageCacheSeconds = properties.getProperty("message.cache_seconds");
            if (setting.getIsDevelopmentEnabled())
            {
                if (!templateUpdateDelay.equals("0") || !messageCacheSeconds.equals("0"))
                {
                    outputStream = new FileOutputStream(resource.getFile());
                    properties.setProperty("template.update_delay", "0");
                    properties.setProperty("message.cache_seconds", "0");
                    properties.store(outputStream, "Sencloud PROPERTIES");
                }
            }
            else
            {
                if (templateUpdateDelay.equals("0") || messageCacheSeconds.equals("0"))
                {
                    outputStream = new FileOutputStream(resource.getFile());
                    properties.setProperty("template.update_delay", "3600");
                    properties.setProperty("message.cache_seconds", "3600");
                    properties.store(outputStream, "Sencloud PROPERTIES");
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly(outputStream);
        }
        model.addAttribute("menuId", Setting.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:edit.ct";
    }

}
