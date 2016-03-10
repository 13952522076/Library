package com.sammyun.controller.console;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.entity.DeviceResolution;
import com.sammyun.service.DeviceResolutionService;

/**
 * Controller - mobile设备管理
 * 
 * @author Sencloud Team
 * @version 3.0
 */

@Controller("deviceResolutionController")
@RequestMapping("/console/mobile_device_resolution")
public class DeviceResolutionController extends BaseController
{
    @Resource(name = "deviceResolutionServiceImpl")
    private DeviceResolutionService deviceResolutionService;

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        return "/console/mobile_device_resolution/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(DeviceResolution deviceResolution, RedirectAttributes redirectAttributes)
    {
        if (!isValid(deviceResolution))
        {
            return ERROR_VIEW;
        }
        String regEx = "^[0-9]+_[0-9]+$";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(deviceResolution.getResolution());
        boolean isRegExOk = mat.find();
        if (isRegExOk == false)
        {
            addFlashMessage(redirectAttributes, Message.error("console.resolution.error"));
            return "redirect:add.ct";
        }
        List<DeviceResolution> deviceResolutionList = deviceResolutionService.findAll();
        for (DeviceResolution deviceResolutionItem : deviceResolutionList)
        {
            if (deviceResolutionItem.getResolution().equalsIgnoreCase(deviceResolution.getResolution()))
            {
                addFlashMessage(redirectAttributes, Message.error("console.resolution.exist"));
                return "redirect:add.ct";
            }
        }
        deviceResolutionService.save(deviceResolution);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";

    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("deviceResolution", deviceResolutionService.find(id));
        return "/console/mobile_device_resolution/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(DeviceResolution deviceResolution, String resolutionHidden,
            RedirectAttributes redirectAttributes)
    {
        if (!isValid(deviceResolution))
        {
            return ERROR_VIEW;
        }
        String regEx = "^[0-9]+_[0-9]+$";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(deviceResolution.getResolution());
        boolean isRegExOk = mat.find();
        if (isRegExOk == false)
        {
            addFlashMessage(redirectAttributes, Message.error("console.resolution.error"));
            return "redirect:add.ct";
        }
        if ((deviceResolution.getResolution()).equalsIgnoreCase(resolutionHidden))
        {

        }
        else
        {
            List<DeviceResolution> deviceResolutionList = deviceResolutionService.findAll();
            for (DeviceResolution deviceResolutionItem : deviceResolutionList)
            {
                if (deviceResolutionItem.getResolution().equalsIgnoreCase(deviceResolution.getResolution()))
                {
                    addFlashMessage(redirectAttributes, Message.error("console.resolution.exist"));
                    return "redirect:add.ct";
                }
            }
        }
        deviceResolutionService.update(deviceResolution);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", deviceResolutionService.findPage(pageable));
        return "/console/mobile_device_resolution/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        deviceResolutionService.delete(ids);
        return SUCCESS_MESSAGE;
    }

}
