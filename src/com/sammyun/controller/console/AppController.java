package com.sammyun.controller.console;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Filter;
import com.sammyun.Filter.Operator;
import com.sammyun.Message;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.Admin;
import com.sammyun.entity.app.App;
import com.sammyun.entity.app.App.OperatingSystem;
import com.sammyun.entity.app.App.RunType;
import com.sammyun.entity.app.AppCategory;
import com.sammyun.entity.app.AppCredential;
import com.sammyun.entity.app.AppRole;
import com.sammyun.entity.app.AppScreenshot;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.secret.AppKeySecretUtil;
import com.sammyun.service.AdminService;
import com.sammyun.service.app.AppCategoryService;
import com.sammyun.service.app.AppCredentialService;
import com.sammyun.service.app.AppRoleService;
import com.sammyun.service.app.AppScreenshotService;
import com.sammyun.service.app.AppService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.EduUtil;
import com.sammyun.util.JsonUtils;
import com.sammyun.uuid.UUIDHexGenerator;

/**
 * Controller - 应用管理
 * 
 * @author Sencloud Team

 */
@Controller("appController")
@RequestMapping("/console/app")
public class AppController extends BaseController {

	@Resource(name = "appServiceImpl")
	private AppService appService;

	@Resource(name = "appCategoryServiceImpl")
	private AppCategoryService appCategoryService;

	@Resource(name = "appScreenshotServiceImpl")
	private AppScreenshotService appScreenshotService;

	@Resource(name = "appRoleServiceImpl")
	private AppRoleService appRoleService;

	@Resource(name = "appCredentialServiceImpl")
	private AppCredentialService appCredentialService;
	
	@Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;
    
    private static String ALL_SCHOOLS = "allSchools";
    
	/**
	 * 列表 <功能详细描述>
	 * 
	 * @param model
	 * @param pageable
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model, Pageable pageable, String appName,
			Long appCategoryId, OperatingSystem operatingSystem,
			RunType runType, Boolean isOnline) {
		
		if (isOnline == null) {
			isOnline = true;
		}
		
		if (appName != null) {
			Filter realNameFilter = new Filter("appName", Operator.like, "%"
					+ appName + "%");
			pageable.addFilters(realNameFilter);
			model.addAttribute("appName", appName);
		}

		AppCategory appCategory = appCategoryService.find(appCategoryId);

		Page<App> page = appService.findPage(appCategory, pageable, isOnline,
				operatingSystem, null, runType, null);
		model.addAttribute("appCategories", appCategoryService.findList(true));
		model.addAttribute("appCategoryId", appCategoryId);
		model.addAttribute("operatingSystem", operatingSystem);
		model.addAttribute("runType", runType);
		model.addAttribute("isOnline", isOnline);
		model.addAttribute("menuId", App.class.getSimpleName());
		model.addAttribute("page", page);
		return "/console/app/list";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model, HttpServletRequest request) {

		List<AppCategory> appCategories = appCategoryService.findList(true);

		model.addAttribute("menuId", App.class.getSimpleName());
		model.addAttribute("appCategories", appCategories);
		return "/console/app/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Long appCategoryId, App appData,
			HttpServletRequest request, RedirectAttributes redirectAttributes,
			ModelMap model) {

		AppCategory appCategory = appCategoryService.find(appCategoryId);

		for (Iterator<AppScreenshot> iterator = appData.getAppScreenshots()
				.iterator(); iterator.hasNext();) {
			AppScreenshot appScreenshot = iterator.next();
			if (appScreenshot == null || !isValid(appScreenshot)) {
				iterator.remove();
				continue;
			}
		}

		// 给app添加唯一的appId标识
		Boolean flag = false;
		String appId = "";
		do {
			appId = (String) UUIDHexGenerator.generate();
			flag = appService.checkAppIdUnique(appId);
		} while (flag);
		appData.setAppId(appId);

		App app = appService.create(appCategory, appData);

		appService.save(app);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.ct";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		App app = appService.find(id);
		model.addAttribute("app", app);
		model.addAttribute("menuId", App.class.getSimpleName());
		model.addAttribute("appCategories", appCategoryService.findList(true));
		return "/console/app/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(App app, Long id, Long appCategoryId,
			RedirectAttributes redirectAttributes, ModelMap model) {
		App preApp = appService.find(id);
		preApp.setAppName(app.getAppName());
		preApp.setAppDescription(app.getAppDescription());
		preApp.setOperatingSystem(app.getOperatingSystem());
		preApp.setRunType(app.getRunType());
		preApp.setLogoAppImg(app.getLogoAppImg());
		preApp.setOpenUrl(app.getOpenUrl());
		preApp.setInstallUrl(app.getInstallUrl());
		preApp.setAppSize(app.getAppSize());
		preApp.setVersionName(app.getVersionName());
		preApp.setVersionCode(app.getVersionCode());
		preApp.setAppCode(app.getAppCode());
		preApp.setAppAttachment(app.getAppAttachment());
		AppCategory appCategory = appCategoryService.find(appCategoryId);
		preApp.setAppCategory(appCategory);

		List<AppScreenshot> originalAppScreenshots = new ArrayList<AppScreenshot>();
		originalAppScreenshots.addAll(app.getAppScreenshots());
		preApp.getAppScreenshots().clear();

		appService.update(preApp);

		appScreenshotService.deleteByApp(preApp);

		List<AppScreenshot> appScreenshotAttachs = buildAttachs(
				originalAppScreenshots, app);
		appScreenshotService.batchUpdate(appScreenshotAttachs);

		model.addAttribute("menuId", App.class.getSimpleName());
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.ct";
	}

	@RequestMapping(value = "/unShelve", method = RequestMethod.GET)
	public void unShelve(Long id, String type, HttpServletResponse response) {
		App app = appService.find(id);
		if ("down".equals(type)) {
			app.setIsOnline(false);
		} else {
			app.setIsOnline(true);
			app.setOnlineDate(DateUtil.getDate());
		}
		appService.update(app);

		try {
			response.setContentType("text/html; charset=UTF-8");
			JsonUtils.writeValue(response.getWriter(), app.getAppName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 构建 app截图
	 * 
	 * @param originalCampusviewImageAttachs
	 * @param
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private List<AppScreenshot> buildAttachs(
			List<AppScreenshot> originalAppScreenshots, App app) {
		List<AppScreenshot> AppScreenshots = new ArrayList<AppScreenshot>();
		for (AppScreenshot appScreenshot : originalAppScreenshots) {
			if (null != appScreenshot
					&& EduUtil.isNotEmpty(appScreenshot.getScreenshotUrl())) {
				appScreenshot.setId(null);
				appScreenshot.setApp(app);
				AppScreenshots.add(appScreenshot);
			}
		}
		return AppScreenshots;
	}

	/**
	 * app回退版本列表
	 * 
	 * @param model
	 * @param pageable
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/appVersionList", method = RequestMethod.GET)
	public String appVersionList(ModelMap model, Pageable pageable, Long id) {

		App app = appService.find(id);
		Long parentId = 0L;
		if (app != null && app.getParent() != null) {
			parentId = app.getParent().getId();
		} else {
			parentId = id;
		}
		Page<App> page = appService.findPage(parentId, pageable);
		model.addAttribute("menuId", App.class.getSimpleName());
		model.addAttribute("page", page);
		model.addAttribute("id", id);
		return "/console/app/versionBack";
	}

	/**
	 * 版本回退
	 * 
	 * @param model
	 * @param pageable
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/versionBack", method = RequestMethod.POST)
	public @ResponseBody Message versionBack(ModelMap model, Pageable pageable,
			Long id) {

		App currentApp = appService.find(id);
		Long parentId = 0L;
		if (currentApp != null && currentApp.getParent() != null) {
			parentId = currentApp.getParent().getId();
		} else {
			parentId = id;
		}
		List<App> apps = appService.findSeriesApps(parentId);
		Set<App> historyApps = new HashSet<App>();
		for (App app : apps) {
			app.setHistory(null);
			if (app.getId() != id) {
				app.setParent(currentApp);
				historyApps.add(app);
			} else {
				app.setParent(null);
			}
			appService.update(app);
		}

		currentApp.setHistory(historyApps);
		appService.update(currentApp);

		return SUCCESS_MESSAGE;
	}

	/**
	 * 应用授权 <功能详细描述>
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/authorization", method = RequestMethod.GET)
	public String authorization(Long id, ModelMap model) {
		
		App app = appService.find(id);
		List<DictSchool> dictSchools = new ArrayList<DictSchool>();
		String dictSchoolIds = "";
		
		if(app.getDictSchools() != null && app.getDictSchools().size() > 0){
			dictSchools.addAll(app.getDictSchools());
			if(dictSchools != null && dictSchools.size() > 0){
				for (int i = 0; i < dictSchools.size(); i++) {
					DictSchool dictSchool = dictSchools.get(i);
					if(i == dictSchools.size() - 1){
						dictSchoolIds += dictSchool.getId().toString();
					}
					else{
						dictSchoolIds += dictSchool.getId().toString() + ",";
					}
				}
			}
		} 
		
		model.addAttribute("appRoles", appRoleService.findAll());
		model.addAttribute("app", app);
		model.addAttribute("dictschools", dictSchools);
		model.addAttribute("menuId", App.class.getSimpleName());
		model.addAttribute("dictSchoolIds", dictSchoolIds);
		return "/console/app/authorization";
	}

	/**
	 * 更新应用授权 <功能详细描述>
	 * 
	 * @param id
	 * @param appRoleIds
	 * @param redirectAttributes
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/updateAuthorization", method = RequestMethod.POST)
	public String updateAuthorization(Long id, Long[] appRoleIds,
			Long[] schoolIds, String allSchools, RedirectAttributes redirectAttributes,
			ModelMap model) {
		App app = appService.find(id);
		List<AppRole> appRoles = appRoleService.findList(appRoleIds);
		List<DictSchool> dictSchools = new ArrayList<DictSchool>();
		if(ALL_SCHOOLS.equals(allSchools)){
			Admin admin = adminService.getCurrent();
	        // 校园管理员只能看到自己学校的，超级管理员可以看到所有的
			Pageable pageable = new Pageable();
			Page<DictSchool> page = new Page<DictSchool>();
	        if (admin.getIsSchoolManager() != null && admin.getIsSchoolManager())
	        {
	            Filter filter = new Filter("id", Operator.eq, admin.getDictSchool().getId());
	            pageable.addFilters(filter);
	            page = dictSchoolService.findPage(pageable);
	        }
	        else
	        {
	        	page = dictSchoolService.findPage(pageable);
	        }
	        dictSchools = page.getContent();
	        if(dictSchools != null && dictSchools.size() > 0){
        		Set<DictSchool> dictSchoolsSet = app.getDictSchools();
        		for(DictSchool dictSchool : dictSchools){
	        		if(!dictSchoolsSet.contains(dictSchool)){
	        			dictSchoolsSet.add(dictSchool);
	        		}
	    			app.setDictSchools(dictSchoolsSet);
	        	}
	        }
		}
		else{
			dictSchools = dictSchoolService.findList(schoolIds);
			app.getDictSchools().clear();
			app.setDictSchools(new HashSet<DictSchool>(dictSchools));
		}
		
		app.getAppRoles().clear();
		app.setAppRoles(new HashSet<AppRole>(appRoles));
		
		appService.update(app);
		
		/** 更新学校的可以访问的app*/
		for(DictSchool dictSchool : dictSchools){
			Set<App> apps = dictSchool.getApps();
			if(!apps.contains(app)){
				apps.add(app);
			}
			dictSchool.setApps(apps);
			dictSchoolService.update(dictSchool);
		}
		
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		model.addAttribute("appRoles", appRoleService.findAll());
		model.addAttribute("app", appService.find(id));
		model.addAttribute("menuId", App.class.getSimpleName());
		return "redirect:edit.ct?id=" + id;
	}

	/**
	 * API授权
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/credential", method = RequestMethod.GET)
	public String credential(Long id, ModelMap model) {
		model.addAttribute("app", appService.find(id));
		if (appService.find(id).getAppCredential() == null) {
			model.addAttribute("appCredential", new AppCredential());
		} else {
			model.addAttribute("appCredential", appService.find(id)
					.getAppCredential());
		}
		model.addAttribute("appCredential", appService.find(id)
				.getAppCredential());
		model.addAttribute("menuId", App.class.getSimpleName());
		return "/console/app/credential";
	}

	/**
	 * 保存API授权
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/updateCredential", method = RequestMethod.POST)
	public String updateCredential(AppCredential appCredential, Long appId,
			ModelMap model) {
		App app = appService.find(appId);
		appCredential.setApp(app);
		appCredentialService.update(appCredential);
		return "redirect:list.ct";
	}

	@RequestMapping(value = "/upgrade", method = RequestMethod.GET)
	public String upgrade(Long id, ModelMap model) {
		App app = appService.find(id);
		model.addAttribute("app", app);
		model.addAttribute("menuId", App.class.getSimpleName());
		model.addAttribute("appCategories", appCategoryService.findAll());
		return "/console/app/upgrade";
	}

	@RequestMapping(value = "/saveUpgrade", method = RequestMethod.POST)
	public String saveUpgrade(App app, Long preId,
			RedirectAttributes redirectAttributes) {
		App preApp = appService.find(preId);
		app.setAppDescription(preApp.getAppDescription());
		app.setOperatingSystem(preApp.getOperatingSystem());
		app.setRunType(preApp.getRunType());
		app.setAppCategory(preApp.getAppCategory());
		app.setAppCode(preApp.getAppCode());
		app.setInstallUrl(preApp.getInstallUrl());
		app.setIsCharge(preApp.getIsCharge());
		app.setPrefix(preApp.getPrefix());
		app.setIsOnline(false);
		app.setOnlineDate(new Date());
		app.setPrice(BigDecimal.ZERO);
		app.setIsPoint(preApp.getIsPoint());
		app.setPoint(preApp.getPoint());
		app.setDeveloper("Sencloud");
		// app.setAppRoles(preApp.getAppRoles());
		app.setIsAuditing(false);

		app.setAppId(preApp.getAppId());
		List<AppScreenshot> appScreenshots = app.getAppScreenshots();
		app.setAppScreenshots(null);
		appService.save(app);
		List<AppScreenshot> appScreenshotAttachs = buildAttachs(appScreenshots,
				app);
		appScreenshotService.batchUpdate(appScreenshotAttachs);

		List<App> historyApps = appService.findSeriesApps(preApp.getId());
		if (historyApps != null && historyApps.size() > 0) {
			for (App historyApp : historyApps) {
				historyApp.setParent(app);
				appService.update(historyApp);
			}
		}

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);

		return "redirect:list.ct";
	}

	/**
	 * 重置key
	 * 
	 * @param response
	 * @see [类、类#方法、类#成员]
	 */
	@RequestMapping(value = "/changeAppKey", method = RequestMethod.GET)
	public void changeAppKey(HttpServletResponse response) {
		AppCredential appKey = this.generaterAppKey();
		try {
			response.setContentType("text/html; charset=UTF-8");
			JsonUtils.writeValue(response.getWriter(), appKey);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 递归生成appkey <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public AppCredential generaterAppKey() {
		AppCredential appKey = new AppCredential();
		appKey.setAppKey(AppKeySecretUtil.generateAppKey());
		appKey.setAppSecret(AppKeySecretUtil.generateAppSecret());
		List<AppCredential> appCredentials = appCredentialService.findAll();
		if (appCredentials != null && appCredentials.size() > 0) {
			for (AppCredential appCredential : appCredentials) {
				if (appKey.getAppKey().equals(appCredential.getAppKey())
						|| appKey.getAppSecret().equals(
								appCredential.getAppSecret())) {
					return generaterAppKey();
				}
			}
			return appKey;
		} else {
			return appKey;
		}
	}

	/**
	 * 检查应用标识是否唯一
	 */
	@RequestMapping(value = "/check_app_code", method = RequestMethod.GET)
	public @ResponseBody boolean checkAppCode(String previousAppCode,
			String appCode) {
		if (StringUtils.isEmpty(appCode)) {
			return false;
		}
		if (appService.appCodeUnique(previousAppCode, appCode)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
     * 获取学校列表
     * 
     * @param pageable
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/schoolList", method = RequestMethod.GET)
    public String schoolList(Pageable pageable, ModelMap model, String dictSchoolIds)
    {
        Admin admin = adminService.getCurrent();
        // 校园管理员只能看到自己学校的，超级管理员可以看到所有的
        if (admin.getIsSchoolManager() != null && admin.getIsSchoolManager())
        {
            Filter filter = new Filter("id", Operator.eq, admin.getDictSchool().getId());
            pageable.addFilters(filter);
            model.addAttribute("page", dictSchoolService.findPage(pageable));
        }
        else
        {
            model.addAttribute("page", dictSchoolService.findPage(pageable));
        }
        String[] dictSchoolIdArray = null ;
        List<Long> alreadyChoiceSchoolIds = new ArrayList<Long>();
        List<DictSchool> alreadyChoiceSchools = new ArrayList<DictSchool>();
        if(dictSchoolIds != null){
        	dictSchoolIdArray= dictSchoolIds.split(",");
        	for(String dictSchoolId : dictSchoolIdArray){
            	if(dictSchoolId != null && !"".equals(dictSchoolId)){
            		alreadyChoiceSchoolIds.add(Long.valueOf(dictSchoolId));
            		DictSchool dictSchool = dictSchoolService.find(Long.valueOf(dictSchoolId));
            		alreadyChoiceSchools.add(dictSchool);
            	}
            }
        }
        model.addAttribute("dictSchoolIds", dictSchoolIds);
        model.addAttribute("alreadyChoiceSchoolIds", alreadyChoiceSchoolIds);
        model.addAttribute("alreadyChoiceSchools", alreadyChoiceSchools);
        return "/console/app/schoolList";
    }

}
