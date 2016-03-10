package com.sammyun.controller.console;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sammyun.plugin.MessagePushPlugin;
import com.sammyun.service.PluginService;

/**
 * Controller - 消息推送插件
 * 
 * @author xutianlong
 * @version [版本号, Apr 17, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("adminMessagePushPluginController")
@RequestMapping("/console/message_push_plugin")
public class MessagePushPluginController extends BaseController
{

    @Resource(name = "pluginServiceImpl")
    private PluginService pluginService;
    
    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(ModelMap model)
    {
        model.addAttribute("messagePushPlugins", pluginService.getMessagePushPlugins());
        model.addAttribute("menuId", MessagePushPlugin.class.getSimpleName());
        return "/console/message_push_plugin/list";
    }
}
