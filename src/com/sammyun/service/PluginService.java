/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service;

import java.util.List;

import com.sammyun.plugin.MessagePushPlugin;
import com.sammyun.plugin.PaymentPlugin;
import com.sammyun.plugin.StoragePlugin;

/**
 * Service - 插件
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface PluginService
{

    /**
     * 获取支付插件
     * 
     * @return 支付插件
     */
    List<PaymentPlugin> getPaymentPlugins();

    /**
     * 获取存储插件
     * 
     * @return 存储插件
     */
    List<StoragePlugin> getStoragePlugins();

    /**
     * 获取消息推送插件
     * 
     * @return 消息推送插件
     */
    List<MessagePushPlugin> getMessagePushPlugins();

    /**
     * 获取支付插件
     * 
     * @param isEnabled 是否启用
     * @return 支付插件
     */
    List<PaymentPlugin> getPaymentPlugins(boolean isEnabled);

    /**
     * 获取存储插件
     * 
     * @param isEnabled 是否启用
     * @return 存储插件
     */
    List<StoragePlugin> getStoragePlugins(boolean isEnabled);

    /**
     * 获取消息推送插件
     * 
     * @param isEnabled 是否启用
     * @return 消息推送插件
     */
    List<MessagePushPlugin> getMessagePushPlugins(boolean isEnabled);

    /**
     * 获取支付插件
     * 
     * @param id ID
     * @return 支付插件
     */
    PaymentPlugin getPaymentPlugin(String id);

    /**
     * 获取存储插件
     * 
     * @param id ID
     * @return 存储插件
     */
    StoragePlugin getStoragePlugin(String id);

    /**
     * 获取消息推送插件
     * 
     * @param id ID
     * @return 存储插件
     */
    MessagePushPlugin getMessagePushPlugin(String id);

}
