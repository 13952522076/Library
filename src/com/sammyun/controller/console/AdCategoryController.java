/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.entity.ad.Ad;
import com.sammyun.entity.ad.Ad.Type;
import com.sammyun.entity.ad.AdCategory;
import com.sammyun.service.ad.AdCategoryService;
import com.sammyun.service.ad.AdService;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 广告类别
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("adminAdCategoryController")
@RequestMapping("/console/adCategory")
public class AdCategoryController extends BaseController
{

    @Resource(name = "adServiceImpl")
    private AdService adService;
    
    @Resource(name = "adCategoryServiceImpl")
    private AdCategoryService adCategoryService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        model.addAttribute("page", adCategoryService.findPage(pageable));
        model.addAttribute("menuId", AdCategory.class.getSimpleName());
        return "/console/adCategory/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("menuId", AdCategory.class.getSimpleName());
        return "/console/adCategory/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(AdCategory adCategory, RedirectAttributes redirectAttributes)
    {
        

        adCategoryService.save(adCategory);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("adCategory", adCategoryService.find(id));
        model.addAttribute("menuId", AdCategory.class.getSimpleName());
        return "/console/adCategory/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(AdCategory adCategory,  RedirectAttributes redirectAttributes)
    {
        adCategoryService.update(adCategory);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        adCategoryService.delete(ids);
        return SUCCESS_MESSAGE;
    }
    
    /**
     * 检查name是否唯一
     */
    @RequestMapping(value = "/check_name", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkName(String name, String preName)
    {
        if (StringUtils.equalsIgnoreCase(name, preName))
        {
            return true;
        }
        if (adCategoryService.findList(name).size()>0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    @RequestMapping(value="/changeStatus",method = RequestMethod.GET)
    public void changeStatus(Long id,Boolean status,HttpServletResponse response){
       AdCategory adCategory = adCategoryService.find(id);
       adCategory.setStatus(status);
       adCategoryService.update(adCategory);
       try {
           response.setContentType("text/html; charset=UTF-8");
           JsonUtils.writeValue(response.getWriter(), "success");
       } catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       } 
    }

}
