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
import com.sammyun.Pageable;
import com.sammyun.entity.app.App;
import com.sammyun.entity.app.AppPoster;
import com.sammyun.entity.app.AppPoster.GotoMethod;
import com.sammyun.entity.app.AppPoster.OperatingSystem;
import com.sammyun.service.app.AppPosterService;
import com.sammyun.service.app.AppService;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 应用超市海报
 * 
 * @author Sencloud Team

 */
@Controller("appPosterController")
@RequestMapping("/console/app_poster")
public class AppPosterController extends BaseController
{

    @Resource(name = "appPosterServiceImpl")
    private AppPosterService appPosterService;

    @Resource(name="appServiceImpl")
    private AppService appService;
    
    /**
    * 列表
    */
   @RequestMapping(value = "/list", method = RequestMethod.GET)
   public String list(OperatingSystem operatingSystem,Boolean isOnline,String posterName,Pageable pageable, ModelMap model)
   {
   	   if(operatingSystem==null)
       {
   		   operatingSystem=OperatingSystem.android;
   	   }
   	   if(isOnline==null)
   	   {
   		   isOnline=false;
   	   }
   	   if (posterName != null) {
   		   Filter realNameFilter = new Filter("posterName", Operator.like, "%"
   				   + posterName + "%");
   		   pageable.addFilters(realNameFilter);
   		   model.addAttribute("posterName", posterName);
	   }
       Filter filterO = new Filter("operatingSystem", Operator.eq, operatingSystem);
       Filter filterT = new Filter("isOnline", Operator.eq, isOnline);
       pageable.addFilters(filterO);
       pageable.addFilters(filterT);
       model.addAttribute("isOnline",isOnline);
       model.addAttribute("operatingSystem", operatingSystem);
       model.addAttribute("page", appPosterService.findPage(pageable));
       model.addAttribute("menuId", "AppPoster");
       return "/console/app_poster/list";
   }
   
   /**
    * 添加
    */
   @RequestMapping(value = "/add", method = RequestMethod.GET)
   public String add(ModelMap model)
   {
       model.addAttribute("menuId","AppPoster");
       return "/console/app_poster/add";
   }
   
   /**
    * 保存
    */
   @RequestMapping(value = "/save", method = RequestMethod.POST)
   public String save( String appId,AppPoster appPoster,ModelMap model, RedirectAttributes redirectAttributes)
   {
	   appPoster.setIsOnline(false);
	   if(appId==null)
	   {
		   appPoster.setGotoMethod(GotoMethod.link);
		   appPoster.setApp(null);
	   }
	   else
	   {
		   App app=appService.find(Long.parseLong(appId));
		   appPoster.setApp(app);
		   appPoster.setExternalLink(null);
		   appPoster.setGotoMethod(GotoMethod.app);
	   }
       appPosterService.save(appPoster);
       addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
       model.addAttribute("menuId","AppPoster");
       return "redirect:list.ct";
   }
    
   /**
    * 排序
    */
   @RequestMapping(value = "/changeOrders", method = RequestMethod.POST)
   public @ResponseBody
   Message changeOrders(Long currentId, Long changeId, String btnType)
   {
       AppPoster currentAppPoster = appPosterService.find(currentId);
       AppPoster changeAppPoster = appPosterService.find(changeId);
       
       Integer currentOrder = appPosterService.find(currentId).getOrder();
       Integer changeOrder = appPosterService.find(changeId).getOrder();
       
       currentAppPoster.setOrder(changeOrder);
       appPosterService.save(currentAppPoster);
       changeAppPoster.setOrder(currentOrder);
       appPosterService.save(changeAppPoster);
       
       return SUCCESS_MESSAGE;
   }
   
   /**
    * 删除
    */
   @RequestMapping(value = "/delete", method = RequestMethod.POST)
   public @ResponseBody
   Message delete(Long[] ids)
   {
       appPosterService.delete(ids);
       return SUCCESS_MESSAGE;
   }
   
   /**
    * 编辑
    */
   @RequestMapping(value = "/edit", method = RequestMethod.GET)
   public String edit(Long id, ModelMap model)
   {
	   AppPoster appPoster=appPosterService.find(id);
       model.addAttribute("poster",appPoster);
       if(appPoster.getGotoMethod()==GotoMethod.app)
       {
    	   model.addAttribute("gotoContent", appPoster.getApp().getAppName());
       }
       else
       {
    	   model.addAttribute("gotoContent", appPoster.getExternalLink());
       }
       model.addAttribute("gotoMethod", appPoster.getGotoMethod().toString());
       model.addAttribute("menuId", "AppPoster");
       return "/console/app_poster/edit";
   }
   

   /**
    * 更新
    */
   @RequestMapping(value = "/update", method = RequestMethod.POST)
   public String update(String appId, AppPoster appPoster,ModelMap model, RedirectAttributes redirectAttributes)
   {
	   Long appPosterId = appPoster.getId();
	   AppPoster preAppPoster = appPosterService.find(appPosterId);
	   if(preAppPoster == null){
		   return ERROR_VIEW;
	   }
	   
	   if(appId==null)
	   {
		   preAppPoster.setGotoMethod(GotoMethod.link);
		   preAppPoster.setApp(null);
	   }
	   else
	   {
		   App app=appService.find(Long.parseLong(appId));
		   preAppPoster.setApp(app);
		   preAppPoster.setExternalLink(null);
		   preAppPoster.setGotoMethod(GotoMethod.app);
	   }
	   
	   preAppPoster.setOperatingSystem(appPoster.getOperatingSystem());
	   preAppPoster.setPosterImg(appPoster.getPosterImg());
	   preAppPoster.setPosterName(appPoster.getPosterName());
       appPosterService.update(preAppPoster);
       model.addAttribute("menuId","AppPoster");
       addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
       return "redirect:list.ct";
   }
   
   /**
    * 上架、下架
    */
   @RequestMapping(value = "/onLine", method = RequestMethod.GET)
   public String onLine(ModelMap model,Long id,RedirectAttributes redirectAttributes)
   {
	   AppPoster appPoster=appPosterService.find(id);
	   if(appPoster.getIsOnline())
	   {
		   appPoster.setIsOnline(false);
	   }
	   else
	   {
		   appPoster.setIsOnline(true);
		   appPoster.setOnlineDate(new Date());
	   }
       appPosterService.save(appPoster);
       model.addAttribute("menuId","AppPoster");
       addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
       return "redirect:list.ct";
   }
   
   /**
    * ajax根据App名称搜索App应用
    * 
    * @param appName
    * @param response
    */
   @RequestMapping(value = "/searchApp", method = RequestMethod.GET)
   public void searchApp(String appName, HttpServletResponse response)
   {
	   List<App> appList=new ArrayList<App>();
	   if(appName!=null)
	   {
		   appList=appService.findBySearchName(appName);
	   }
       try
       {
           response.setContentType("text/html; charset=UTF-8");
           JsonUtils.writeValue(response.getWriter(), appList);
       }
       catch (IOException e)
       {
           e.printStackTrace();
       }
   }
}
