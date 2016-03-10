/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.plugin.xgForIos;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.sammyun.entity.PluginConfig;
import com.sammyun.plugin.MessagePushPlugin;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.XingeApp;

/**
 * Plugin - 信鸽推送
 * 
 * @author xutianlong
 * @version 3.0
 */
@Component("xgForIosPlugin")
public class XgForIosPlugin extends MessagePushPlugin
{

    @Override
    public String getName()
    {
        return "信鸽 For IOS";
    }

    @Override
    public String getVersion()
    {
        return "v2";
    }

    @Override
    public String getAuthor()
    {
        return "Draco";
    }

    @Override
    public String getSiteUrl()
    {
        return "";
    }

    @Override
    public String getInstallUrl()
    {
        return "xg/install.ct";
    }

    @Override
    public String getUninstallUrl()
    {
        return "xg/uninstall.ct";
    }

    @Override
    public String getSettingUrl()
    {
        return "xg/setting.ct";
    }

    @Override
    public String getRequestUrl()
    {
        return "http://openapi.xg.qq.com";
    }

    @Override
    public RequestMethod getRequestMethod()
    {
        return RequestMethod.post;
    }

    @Override
    public String getRequestCharset()
    {
        return "UTF-8";
    }

    /**
     * 设置本请求的unix时间戳，用于确认请求的有效期。默认情况下，请求时间戳与服务器时间（北京时间）偏差大于600秒则会被拒绝
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public String getTimestamp()
    {
        return "600";
    }

    /**
     * iOS平台推送消息给单个设备
     * 
     * @param content 消息内容
     * @param token 接收消息的设备Token
     * @param environment 可选值为XingeApp.IOSENV_PROD或者XingeApp.IOSENV_DEV，iOS专属
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public JSONObject pushTokenIos(String content, String token)
    {
        PluginConfig pluginConfig = getPluginConfig();
        Long accessId = Long.valueOf(pluginConfig.getAttribute("accessID"));
        String secretKey = pluginConfig.getAttribute("secretKey");
        String environmentConf = pluginConfig.getAttribute("environmentConf");
        
        int environment = 0;
        if (environmentConf != null && !"".equalsIgnoreCase(environmentConf))
        {

            if (environmentConf.equalsIgnoreCase("IOSENV_PROD"))
            {
                environment = XingeApp.IOSENV_PROD;
            }
            else if (environmentConf.equalsIgnoreCase("IOSENV_DEV"))
            {
                environment = XingeApp.IOSENV_DEV;
            }
        }
        return XingeApp.pushTokenIos(accessId, secretKey, content, token, environment);
    }
    /**
     * iOS平台推送消息给单个设备
     * <功能详细描述>
     * @param content
     * @param token
     * @param environment
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public JSONObject pushSingleDeviceIOS(String content, String token)
    {
        PluginConfig pluginConfig = getPluginConfig();
        Long accessId = Long.valueOf(pluginConfig.getAttribute("accessID"));
        String secretKey = pluginConfig.getAttribute("secretKey");
        String environmentConf = pluginConfig.getAttribute("environmentConf");
        
        int environment = 0;
        if (environmentConf != null && !"".equalsIgnoreCase(environmentConf))
        {

            if (environmentConf.equalsIgnoreCase("IOSENV_PROD"))
            {
                environment = XingeApp.IOSENV_PROD;
            }
            else if (environmentConf.equalsIgnoreCase("IOSENV_DEV"))
            {
                environment = XingeApp.IOSENV_DEV;
            }
        }
        MessageIOS message = new MessageIOS();
        message.setBadge(1);
        message.setSound("ping.aiff");
        message.setAlert(content);
        XingeApp xinge = new XingeApp(accessId, secretKey);  
        return xinge.pushSingleDevice(token, message,environment);
    }
    
    @Override
    public JSONObject pushTokenAndroid(String title, String content, String token)
    {
        return null;
    }
}
