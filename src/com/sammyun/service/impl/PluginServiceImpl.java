/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.stereotype.Service;

import com.sammyun.plugin.MessagePushPlugin;
import com.sammyun.plugin.PaymentPlugin;
import com.sammyun.plugin.StoragePlugin;
import com.sammyun.service.PluginService;

/**
 * Service - 插件
 * 
 * @author Sencloud Team

 */
@Service("pluginServiceImpl")
public class PluginServiceImpl implements PluginService
{

    @Resource
    private List<PaymentPlugin> paymentPlugins = new ArrayList<PaymentPlugin>();

    @Resource
    private List<StoragePlugin> storagePlugins = new ArrayList<StoragePlugin>();

    @Resource
    private List<MessagePushPlugin> messagePlugins = new ArrayList<MessagePushPlugin>();

    @Resource
    private Map<String, PaymentPlugin> paymentPluginMap = new HashMap<String, PaymentPlugin>();

    @Resource
    private Map<String, StoragePlugin> storagePluginMap = new HashMap<String, StoragePlugin>();

    @Resource
    private Map<String, MessagePushPlugin> messagePushPluginMap = new HashMap<String, MessagePushPlugin>();

    public List<PaymentPlugin> getPaymentPlugins()
    {
        Collections.sort(paymentPlugins);
        return paymentPlugins;
    }

    public List<StoragePlugin> getStoragePlugins()
    {
        Collections.sort(storagePlugins);
        return storagePlugins;
    }

    public List<MessagePushPlugin> getMessagePushPlugins()
    {
        Collections.sort(messagePlugins);
        return messagePlugins;
    }

    public List<PaymentPlugin> getPaymentPlugins(final boolean isEnabled)
    {
        List<PaymentPlugin> result = new ArrayList<PaymentPlugin>();
        CollectionUtils.select(paymentPlugins, new Predicate()
        {
            public boolean evaluate(Object object)
            {
                PaymentPlugin paymentPlugin = (PaymentPlugin) object;
                return paymentPlugin.getIsEnabled() == isEnabled;
            }
        }, result);
        Collections.sort(result);
        return result;
    }

    public List<StoragePlugin> getStoragePlugins(final boolean isEnabled)
    {
        List<StoragePlugin> result = new ArrayList<StoragePlugin>();
        CollectionUtils.select(storagePlugins, new Predicate()
        {
            public boolean evaluate(Object object)
            {
                StoragePlugin storagePlugin = (StoragePlugin) object;
                return storagePlugin.getIsEnabled() == isEnabled;
            }
        }, result);
        Collections.sort(result);
        return result;
    }

    public List<MessagePushPlugin> getMessagePushPlugins(final boolean isEnabled)
    {
        List<MessagePushPlugin> result = new ArrayList<MessagePushPlugin>();
        CollectionUtils.select(messagePlugins, new Predicate()
        {
            public boolean evaluate(Object object)
            {
                MessagePushPlugin messagePushPlugin = (MessagePushPlugin) object;
                return messagePushPlugin.getIsEnabled() == isEnabled;
            }
        }, result);
        Collections.sort(result);
        return result;
    }

    public PaymentPlugin getPaymentPlugin(String id)
    {
        return paymentPluginMap.get(id);
    }

    public StoragePlugin getStoragePlugin(String id)
    {
        return storagePluginMap.get(id);
    }

    public MessagePushPlugin getMessagePushPlugin(String id)
    {
        return messagePushPluginMap.get(id);
    }

}
