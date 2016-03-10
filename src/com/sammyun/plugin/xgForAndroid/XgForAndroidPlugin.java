/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.plugin.xgForAndroid;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.sammyun.entity.PluginConfig;
import com.sammyun.plugin.MessagePushPlugin;
import com.tencent.xinge.XingeApp;

/**
 * Plugin - 信鸽推送
 * 
 * @author xutianlong
 * @version 3.0
 */
@Component("xgForAndroidPlugin")
public class XgForAndroidPlugin extends MessagePushPlugin
{

    @Override
    public String getName()
    {
        return "信鸽 For Android";
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
        return "xgAndroid/install.ct";
    }

    @Override
    public String getUninstallUrl()
    {
        return "xgAndroid/uninstall.ct";
    }

    @Override
    public String getSettingUrl()
    {
        return "xgAndroid/setting.ct";
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
     * Android平台推送消息给单个设备
     * 
     * @param title 消息标题，Android专属
     * @param content 消息内容
     * @param token 接收消息的设备Token
     * @return
     * @see [类、类#方法、类#成员]
     */
    @Override
    public JSONObject pushTokenAndroid(String title, String content, String token)
    {
        PluginConfig pluginConfig = getPluginConfig();
        Long accessId = Long.valueOf(pluginConfig.getAttribute("accessID"));
        String secretKey = pluginConfig.getAttribute("secretKey");
        return XingeApp.pushTokenAndroid(accessId, secretKey, title, content, token);
    }

    @Override
    public JSONObject pushTokenIos(String content, String token)
    {
        return null;
    }

    @Override
    public JSONObject pushSingleDeviceIOS(String content, String token)
    {
        return null;
    }
}
