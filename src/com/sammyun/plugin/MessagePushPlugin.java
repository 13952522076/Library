package com.sammyun.plugin;

import javax.annotation.Resource;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.sammyun.entity.PluginConfig;
import com.sammyun.service.PluginConfigService;

/**
 * Plugin - 消息推送
 * 
 * @author xutianlong
 * @version [版本号, Apr 17, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class MessagePushPlugin implements Comparable<MessagePushPlugin>
{

    @Resource(name = "pluginConfigServiceImpl")
    private PluginConfigService pluginConfigService;

    /**
     * 请求方法
     */
    public enum RequestMethod
    {

        /** POST */
        post,

        /** GET */
        get
    }

    /**
     * 获取ID
     * 
     * @return ID
     */
    public final String getId()
    {
        return getClass().getAnnotation(Component.class).value();
    }

    /**
     * 获取名称
     * 
     * @return 名称
     */
    public abstract String getName();

    /**
     * 获取版本
     * 
     * @return 版本
     */
    public abstract String getVersion();

    /**
     * 获取作者
     * 
     * @return 作者
     */
    public abstract String getAuthor();

    /**
     * 设置本请求的unix时间戳，用于确认请求的有效期。默认情况下，请求时间戳与服务器时间（北京时间）偏差大于600秒则会被拒绝
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public abstract String getTimestamp();

    /**
     * 获取网址
     * 
     * @return 网址
     */
    public abstract String getSiteUrl();

    /**
     * 获取安装URL
     * 
     * @return 安装URL
     */
    public abstract String getInstallUrl();

    /**
     * 获取卸载URL
     * 
     * @return 卸载URL
     */
    public abstract String getUninstallUrl();

    /**
     * 获取设置URL
     * 
     * @return 设置URL
     */
    public abstract String getSettingUrl();
    
    
    /**
     * Android平台推送消息给单个设备
     * 
     * @param title 消息标题，Android专属
     * @param content 消息内容
     * @param token 接收消息的设备Token
     * @return
     * @see [类、类#方法、类#成员]
     */
    public abstract JSONObject pushTokenAndroid(String title, String content, String token);

    /**
     * iOS平台推送消息给单个设备
     * 
     * @param content 消息内容
     * @param token 接收消息的设备Token
     * @param environment 可选值为XingeApp.IOSENV_PROD或者XingeApp.IOSENV_DEV，iOS专属
     * @return
     * @see [类、类#方法、类#成员]
     */
    public abstract JSONObject pushTokenIos(String content, String token);
    
    /**
     * iOS平台推送消息给单个设备
     * <功能详细描述>
     * @param content
     * @param token
     * @param environment
     * @return
     * @see [类、类#方法、类#成员]
     */
    public abstract JSONObject pushSingleDeviceIOS(String content, String token);
    
    /**
     * 获取是否已安装
     * 
     * @return 是否已安装
     */
    public boolean getIsInstalled()
    {
        return pluginConfigService.pluginIdExists(getId());
    }

    /**
     * 获取插件配置
     * 
     * @return 插件配置
     */
    public PluginConfig getPluginConfig()
    {
        return pluginConfigService.findByPluginId(getId());
    }

    /**
     * 获取请求方法
     * 
     * @return 请求方法
     */
    public abstract RequestMethod getRequestMethod();

    /**
     * 获取请求URL
     * 
     * @return 请求URL
     */
    public abstract String getRequestUrl();

    /**
     * 获取请求的编码
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    public abstract String getRequestCharset();


    /**
     * 获取是否已启用
     * 
     * @return 是否已启用
     */
    public boolean getIsEnabled()
    {
        PluginConfig pluginConfig = getPluginConfig();
        return pluginConfig != null ? pluginConfig.getIsEnabled() : false;
    }

    /**
     * 获取属性值
     * 
     * @param name 属性名称
     * @return 属性值
     */
    public String getAttribute(String name)
    {
        PluginConfig pluginConfig = getPluginConfig();
        return pluginConfig != null ? pluginConfig.getAttribute(name) : null;
    }

    /**
     * 获取排序
     * 
     * @return 排序
     */
    public Integer getOrder()
    {
        PluginConfig pluginConfig = getPluginConfig();
        return pluginConfig != null ? pluginConfig.getOrder() : null;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        if (this == obj)
        {
            return true;
        }
        MessagePushPlugin other = (MessagePushPlugin) obj;
        return new EqualsBuilder().append(getId(), other.getId()).isEquals();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder(17, 37).append(getId()).toHashCode();
    }

    public int compareTo(MessagePushPlugin messagePushPlugin)
    {
        return new CompareToBuilder().append(getOrder(), messagePushPlugin.getOrder()).append(getId(),
                messagePushPlugin.getId()).toComparison();
    }

}
