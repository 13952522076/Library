/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Filter;
import com.sammyun.Filter.Operator;
import com.sammyun.Message;
import com.sammyun.Order;
import com.sammyun.Order.Direction;
import com.sammyun.Pageable;
import com.sammyun.entity.ad.Ad;
import com.sammyun.entity.ad.Ad.AdPosition;
import com.sammyun.entity.ad.Ad.ShowType;
import com.sammyun.entity.ad.Ad.Type;
import com.sammyun.entity.ad.AdCategory;
import com.sammyun.entity.app.AppCategory;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.ad.AdCategoryService;
import com.sammyun.service.ad.AdService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 广告
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Controller("adminAdController")
@RequestMapping("/console/ad")
public class AdController extends BaseController
{

    @Resource(name = "adServiceImpl")
    private AdService adService;

    @Resource(name = "adCategoryServiceImpl")
    private AdCategoryService adCategoryService;

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    /**
     * 添加第一步
     */
    @RequestMapping(value = "/add1", method = RequestMethod.GET)
    public String add1(ModelMap model)
    {
        Filter filter = new Filter("status", Operator.eq, true);
        List<Filter> categoryFilters = new ArrayList<Filter>();
        categoryFilters.add(filter);
        List<AdCategory> adCategories = adCategoryService.findList(null, categoryFilters, null);
        model.addAttribute("adCategories", adCategories);
        model.addAttribute("menuId", Ad.class.getSimpleName());
        return "/console/ad/add1";
    }

    /**
     * 保存第一步
     */
    @RequestMapping(value = "/save1", method = RequestMethod.POST)
    public String save1(Ad ad, Long adCategoryId, ModelMap model, RedirectAttributes redirectAttributes)
    {
        AdCategory adCategory = adCategoryService.find(adCategoryId);
        ad.setAdCategory(adCategory);
        if (ad.getType() == Type.app)
        {
            ad.setAdSite(null);
        }
        else
        {
            ad.setPlatform(null);
            ad.setAdPackageUrl(null);
            ad.setAppName(null);
            ad.setAppAuthor(null);
            ad.setAppDescription(null);
        }
        ad.setIsDraft(true);
        adService.save(ad);
        model.addAttribute("menuId", Ad.class.getSimpleName());
        model.addAttribute("success", "广告基本信息已添加，请添加定向内容");
        Long id = ad.getId();
        return "redirect:add2.ct?success=true&id=" + id;
    }

    /**
     * 添加第2步
     */
    @RequestMapping(value = "/add2", method = RequestMethod.GET)
    public String add2(ModelMap model, Boolean success, Long id, RedirectAttributes redirectAttributes)
    {
        List<DictSchool> dictSchools = dictSchoolService.findAll();
        model.addAttribute("dictSchools", dictSchools);
        if (success != null)
        {

        }
        else
        {
            addFlashMessage(redirectAttributes, new Message(com.sammyun.Message.Type.error, "请先成功添加广告"));
            return "redirect:list.ct";
        }
        model.addAttribute("success", "广告基本信息已添加，请添加定向内容");
        model.addAttribute("id", id);
        model.addAttribute("menuId", Ad.class.getSimpleName());
        return "/console/ad/add2";
    }

    /**
     * 保存第二步
     */
    @RequestMapping(value = "/save2", method = RequestMethod.POST)
    public String save2(Long[] dictschoolIds, Date beginDate, Date endDate, Long adId, ModelMap model,
            RedirectAttributes redirectAttributes)
    {
        List<DictSchool> dictSchools = new ArrayList<DictSchool>();
        if (dictschoolIds != null)
        {
            for (Long dictSchoolId : dictschoolIds)
            {
                dictSchools.add(dictSchoolService.find(dictSchoolId));
            }
        }
        Ad ad = adService.find(adId);
        ad.setBeginDate(beginDate);
        ad.setEndDate(endDate);
        ad.setAdDictSchools(dictSchools);
        adService.update(ad);
        return "redirect:add3.ct?success=true&id=" + adId;
    }

    /**
     * 添加第3步
     */
    @RequestMapping(value = "/add3", method = RequestMethod.GET)
    public String add3(ModelMap model, Boolean success, Long id, RedirectAttributes redirectAttributes)
    {
        model.addAttribute("id", id);
        return "/console/ad/add3";
    }

    /**
     * 保存第3步
     */
    @RequestMapping(value = "/save3", method = RequestMethod.POST)
    public String save3(AdPosition adPosition, ShowType showType, String adImage, Long adId, ModelMap model,
            RedirectAttributes redirectAttributes)
    {

        Ad ad = adService.find(adId);
        ad.setAdPosition(adPosition);
        ad.setShowType(showType);
        ad.setAdImage(adImage);
        ad.setIsDraft(false);
        adService.update(ad);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑第一步
     */
    @RequestMapping(value = "/edit1", method = RequestMethod.GET)
    public String edit1(Long id, ModelMap model)
    {
        Filter filter = new Filter("status", Operator.eq, true);
        List<Filter> categoryFilters = new ArrayList<Filter>();
        categoryFilters.add(filter);
        List<AdCategory> adCategories = adCategoryService.findList(null, categoryFilters, null);
        model.addAttribute("adCategories", adCategories);
        model.addAttribute("ad", adService.find(id));
        model.addAttribute("menuId", Ad.class.getSimpleName());
        return "/console/ad/edit1";
    }

    /**
     * 更新第一步
     */
    @RequestMapping(value = "/update1", method = RequestMethod.POST)
    public String update(Ad ad, Long id, Long adCategoryId, RedirectAttributes redirectAttributes)
    {
        Ad preAd = adService.find(id);
        preAd.setAdName(ad.getAdName());
        AdCategory adCategory = adCategoryService.find(adCategoryId);
        preAd.setAdCategory(adCategory);
        preAd.setContent(ad.getContent());
        preAd.setType(ad.getType());
        if (ad.getType() == Type.app)
        {
            preAd.setAdSite(null);

            preAd.setPlatform(ad.getPlatform());
            preAd.setAdPackageUrl(ad.getAdPackageUrl());
            preAd.setAppName(ad.getAppName());
            preAd.setAppAuthor(ad.getAppAuthor());
            preAd.setAppDescription(ad.getAppDescription());

        }
        else
        {
            preAd.setPlatform(null);
            preAd.setAdPackageUrl(null);
            preAd.setAppName(null);
            preAd.setAppAuthor(null);
            preAd.setAppDescription(null);

            preAd.setAdSite(ad.getAdSite());
        }

        adService.update(preAd);
        return "redirect:edit2.ct?success=true&id=" + id;
    }

    /**
     * 编辑第2步
     */
    @RequestMapping(value = "/edit2", method = RequestMethod.GET)
    public String eidt2(ModelMap model, Boolean success, Long id, RedirectAttributes redirectAttributes)
    {
        List<DictSchool> dictSchools = dictSchoolService.findAll();
        model.addAttribute("dictSchools", dictSchools);
        if (success != null)
        {

        }
        else
        {
            addFlashMessage(redirectAttributes, new Message(com.sammyun.Message.Type.error, "请先成功编辑广告"));
            return "redirect:list.ct";
        }
        model.addAttribute("success", "广告基本信息已编辑，请编辑定向内容");
        model.addAttribute("id", id);
        Ad ad = adService.find(id);
        model.addAttribute("ad", ad);
        model.addAttribute("menuId", Ad.class.getSimpleName());
        return "/console/ad/edit2";
    }

    /**
     * 更新第二步
     */
    @RequestMapping(value = "/update2", method = RequestMethod.POST)
    public String update2(Long[] dictschoolIds, Date beginDate, Date endDate, Long adId, ModelMap model,
            RedirectAttributes redirectAttributes)
    {
        List<DictSchool> dictSchools = new ArrayList<DictSchool>();
        if (dictschoolIds != null)
        {
            for (Long dictSchoolId : dictschoolIds)
            {
                dictSchools.add(dictSchoolService.find(dictSchoolId));
            }
        }

        Ad ad = adService.find(adId);
        ad.setBeginDate(beginDate);
        ad.setEndDate(endDate);
        ad.setAdDictSchools(dictSchools);
        adService.update(ad);
        return "redirect:edit3.ct?success=true&id=" + adId;
    }

    /**
     * 编辑第3步
     */
    @RequestMapping(value = "/edit3", method = RequestMethod.GET)
    public String edit3(ModelMap model, Boolean success, Long id, RedirectAttributes redirectAttributes)
    {
        model.addAttribute("id", id);
        model.addAttribute("ad", adService.find(id));
        return "/console/ad/edit3";
    }

    /**
     * 保存第3步
     */
    @RequestMapping(value = "/update3", method = RequestMethod.POST)
    public String update3(AdPosition adPosition, ShowType showType, String adImage, Long adId, ModelMap model,
            RedirectAttributes redirectAttributes)
    {

        Ad ad = adService.find(adId);
        ad.setAdPosition(adPosition);
        ad.setAdImage(adImage);
        ad.setShowType(showType);
        ad.setIsDraft(false);
        adService.update(ad);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(String searchName, Pageable pageable, ModelMap model)
    {
        if (searchName != null)
        {
            Filter adNameFilter = new Filter("adName", Operator.like, "%" + searchName + "%");
            pageable.addFilters(adNameFilter);
            model.addAttribute("searchName", searchName);
        }
        // Order order = new Order("createDate", Direction.asc);
        // List<Order> orders = new ArrayList<Order>();
        // orders.add(order);
        pageable.setOrderProperty("createDate");
        pageable.setOrderDirection(Direction.desc);
        model.addAttribute("page", adService.findPage(pageable));
        model.addAttribute("menuId", Ad.class.getSimpleName());
        return "/console/ad/list";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        adService.delete(ids);
        return SUCCESS_MESSAGE;
    }

    /**
     * 上架、下架
     * 
     * @param id
     * @param status
     * @param response
     */
    @RequestMapping(value = "/changeStatus", method = RequestMethod.GET)
    public void changeStatus(Long id, Boolean status, HttpServletResponse response)
    {
        Ad ad = adService.find(id);
        ad.setIsOnLine(status);
        adService.update(ad);
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), "success");
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
