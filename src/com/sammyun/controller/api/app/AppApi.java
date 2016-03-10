package com.sammyun.controller.api.app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.sammyun.Filter;
import com.sammyun.Filter.Operator;
import com.sammyun.Order;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.controller.api.bean.app.AppBean;
import com.sammyun.controller.api.block.app.AppBlock;
import com.sammyun.controller.api.block.app.AppCategoryBlock;
import com.sammyun.controller.api.block.app.AppScreenshotBlock;
import com.sammyun.controller.api.block.app.AppStatBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.app.App;
import com.sammyun.entity.app.App.OperatingSystem;
import com.sammyun.entity.app.AppCategory;
import com.sammyun.entity.app.AppRole;
import com.sammyun.entity.app.AppScreenshot;
import com.sammyun.entity.app.AppStat;
import com.sammyun.entity.app.AppUser;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.MemberService;
import com.sammyun.service.app.AppCategoryService;
import com.sammyun.service.app.AppRoleService;
import com.sammyun.service.app.AppService;
import com.sammyun.service.app.AppStatService;
import com.sammyun.service.app.AppUserService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.SpringUtils;

/**
 * api - 应用数据
 * 
 * @author xutianlong
 * @version [版本号, 2015-8-18]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("app")
@Path("/app")
public class AppApi
{
    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "appCategoryServiceImpl")
    private AppCategoryService appCategoryService;

    @Resource(name = "appServiceImpl")
    private AppService appService;

    @Resource(name = "appStatServiceImpl")
    private AppStatService appStatService;
    
    @Resource(name = "appRoleServiceImpl")
    private AppRoleService appRoleService;
    
    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;
    
    @Resource(name = "appUserServiceImpl") 
    private AppUserService appUserService;

    /**
     * 应用清单 <这边的应用清单是不包含native应用的HBuilder和HyBird应用>
     * 
     * @param appBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/list")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel list(AppBean appBean)
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<AppBlock> rows = new ArrayList<AppBlock>();
        if (appBean == null || appBean.getAppCategoryId() == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误");
            return listRestFulModel;
        }

        Long memberId = appBean.getMemberId();
        if (memberId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("参数错误"));
            return listRestFulModel;
        }
        Member member = memberService.find(memberId);
        if (member == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("未知人员不存在！"));
            return listRestFulModel;
        }
        
        Long schoolId = appBean.getSchoolId();
        if (schoolId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("参数错误"));
            return listRestFulModel;
        }
        DictSchool dictSchool = dictSchoolService.find(schoolId);
        if(dictSchool == null){
        	listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("该学校不存在！"));
            return listRestFulModel;
        }
        
        AppCategory appCategory = appCategoryService.find(appBean.getAppCategoryId());
        if (appCategory == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("分类不存在");
            return listRestFulModel;
        }
        
        List<MemberType> memberTypes = new ArrayList<Member.MemberType>();
        List<DictSchool> dictSchools = new ArrayList<DictSchool>();
        memberTypes.add(member.getMemberType());
        dictSchools.add(dictSchool);
        List<AppRole> appRoles = appRoleService.findList(memberTypes);
        
        Pageable pageable;
        if (appBean.getPage() == null)
        {
            pageable = new Pageable();
        }
        else
        {
            pageable = new Pageable(appBean.getPage().getPageNumber(), appBean.getPage().getPageSize());
        }
        // 按什么排序呢？
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        pageable.setOrders(orders);
        
        //这边包含HBuilder和HyBird应用
        List<OperatingSystem> operatingSystems = new ArrayList<App.OperatingSystem>();
        operatingSystems.add(OperatingSystem.hbuilder);
        operatingSystems.add(OperatingSystem.hybird);
		pageable.addFilters(new Filter("operatingSystem", Operator.in,
				operatingSystems));
        
        Page<App> page = appService.findPage(appCategory, pageable, true, null ,appRoles,null,dictSchools);
        if (page == null || page.getContent().size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("无应用列表");
            return listRestFulModel;
        }
        for (App app : page.getContent())
        {
            AppBlock appBlock = new AppBlock();
            AppCategoryBlock appCategoryBlock = new AppCategoryBlock();
            appCategoryBlock.setName(app.getAppCategory().getName());
            appCategoryBlock.setDescription(app.getAppCategory().getDescription());
            appCategoryBlock.setAppCategoryLogoUrl(appCategory.getAppCategoryLogoUrl());
            List<AppScreenshotBlock> appScreenshotBlocks = new ArrayList<AppScreenshotBlock>();
            List<AppScreenshot> appScreenshots = app.getAppScreenshots();
            if (appScreenshots != null && appScreenshots.size() > 0)
            {
                for (AppScreenshot appScreenshot : appScreenshots)
                {
                    AppScreenshotBlock appScreenshotBlock = new AppScreenshotBlock();
                    appScreenshotBlock.setScreenshot(appScreenshot.getScreenshot());
                    appScreenshotBlock.setScreenshotUrl(appScreenshot.getScreenshotUrl());
                    appScreenshotBlocks.add(appScreenshotBlock);
                }
            }
            appBlock.setId(app.getId());
            appBlock.setAppCategoryBlock(appCategoryBlock);
            appBlock.setAppName(app.getAppName());
            appBlock.setAppDescription(app.getAppDescription());
            appBlock.setAppUpDescription(app.getAppUpDescription());
            appBlock.setAppScreenshotsBlock(appScreenshotBlocks);
            appBlock.setInstallUrl(app.getInstallUrl());
            appBlock.setLogoAppImg(app.getLogoAppImg());
            appBlock.setOpenUrl(app.getOpenUrl());
            appBlock.setAppSize(app.getAppSize());
            appBlock.setVersionCode(app.getVersionCode());
            appBlock.setVersionName(app.getVersionName());
            appBlock.setAppCode(app.getAppCode());
            appBlock.setDeveloper(app.getDeveloper());
            appBlock.setAppAttachment(app.getAppAttachment());
            appBlock.setOperatingSystem(app.getOperatingSystem().toString());
            if(app.getAppStat() != null) {
            	AppStatBlock appStatBlock = new AppStatBlock();
            	appStatBlock.setAvgRating(app.getAppStat().getAvgRating());
            	appStatBlock.setCountRating(app.getAppStat().getCountRating());
            	appStatBlock.setCountUser(app.getAppStat().getCountUser());
            	appStatBlock.setCountView(app.getAppStat().getCountView());
            }
            rows.add(appBlock);
        }
        PageModel pageModel = new PageModel();
        pageModel.setPageNumber(page.getPageNumber());
        pageModel.setTotalPages(page.getTotalPages());
        listRestFulModel.setPage(pageModel);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("获取应用列表成功");
        listRestFulModel.setRows(rows);
        return listRestFulModel;
    }

    @GET
    @Path("/v1/detail/{appId}")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileRestFulModel detail(@PathParam("appId") Long appId)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        App app = appService.find(appId);
        if (app == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("未查询到应用");
            return mobileRestFulModel;
        }
        AppBlock appBlock = new AppBlock();
        AppCategoryBlock appCategoryBlock = new AppCategoryBlock();
        appCategoryBlock.setName(app.getAppCategory().getName());
        appCategoryBlock.setDescription(app.getAppCategory().getDescription());
        List<AppScreenshotBlock> appScreenshotBlocks = new ArrayList<AppScreenshotBlock>();
        List<AppScreenshot> appScreenshots = app.getAppScreenshots();
        if (appScreenshots != null && appScreenshots.size() > 0)
        {
            for (AppScreenshot appScreenshot : appScreenshots)
            {
                AppScreenshotBlock appScreenshotBlock = new AppScreenshotBlock();
                appScreenshotBlock.setScreenshot(appScreenshot.getScreenshot());
                appScreenshotBlock.setScreenshotUrl(appScreenshot.getScreenshotUrl());
                appScreenshotBlocks.add(appScreenshotBlock);
            }
        }
        appBlock.setId(app.getId());
        appBlock.setAppCategoryBlock(appCategoryBlock);
        appBlock.setAppName(app.getAppName());
        appBlock.setAppDescription(app.getAppDescription());
        appBlock.setAppUpDescription(app.getAppUpDescription());
        appBlock.setAppScreenshotsBlock(appScreenshotBlocks);
        appBlock.setInstallUrl(app.getInstallUrl());
        appBlock.setLogoAppImg(app.getLogoAppImg());
        appBlock.setOpenUrl(app.getOpenUrl());
        appBlock.setAppSize(app.getAppSize());
        appBlock.setVersionCode(app.getVersionCode());
        appBlock.setVersionName(app.getVersionName());
        appBlock.setAppCode(app.getAppCode());
        appBlock.setDeveloper(app.getDeveloper());

        mobileRestFulModel.setRows(appBlock);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("获取应用详情成功");

        return mobileRestFulModel;
    }

    /**
     * 应用搜索清单 <这边的应用清单是不包含native应用的HBuilder和HyBird应用>
     * 
     * @param appBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/search")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel search(AppBean appBean)
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        if (appBean == null || appBean.getSearchKey() == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误");
        }
        
        Long memberId = appBean.getMemberId();
        if (memberId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("参数错误"));
            return listRestFulModel;
        }
        
        Member member = memberService.find(memberId);
        if (member == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("未知人员不存在！"));
            return listRestFulModel;
        }
        
        Long schoolId = appBean.getSchoolId();
        if (schoolId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("参数错误"));
            return listRestFulModel;
        }
        DictSchool dictSchool = dictSchoolService.find(schoolId);
        if(dictSchool == null){
        	listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("该学校不存在！"));
            return listRestFulModel;
        }
        
        List<MemberType> memberTypes = new ArrayList<Member.MemberType>();
        memberTypes.add(member.getMemberType());
        List<DictSchool> dictSchools = new ArrayList<DictSchool>();
        dictSchools.add(dictSchool);
        List<AppRole> appRoles = appRoleService.findList(memberTypes);

        Pageable pageable;
        if (appBean.getPage() == null)
        {
            pageable = new Pageable();
        }
        else
        {
            pageable = new Pageable(appBean.getPage().getPageNumber(), appBean.getPage().getPageSize());
        }
        // 按什么排序呢？
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        pageable.setOrders(orders);
        
        //这边包含HBuilder和HyBird应用
        List<OperatingSystem> operatingSystems = new ArrayList<App.OperatingSystem>();
        operatingSystems.add(OperatingSystem.hbuilder);
        operatingSystems.add(OperatingSystem.hybird);
		pageable.addFilters(new Filter("operatingSystem", Operator.in,
				operatingSystems));
        
        /** 搜索包括模糊匹配：1，应用名；2，应用关键词 */
		Page<App> page = appService.findBySearchKey(appBean.getSearchKey(),
				pageable, true, null, appRoles,
				dictSchools);
        List<AppBlock> rows = new ArrayList<AppBlock>();
        for (App app : page.getContent())
        {
            AppBlock appBlock = new AppBlock();
            List<AppScreenshotBlock> appScreenshotBlocks = new ArrayList<AppScreenshotBlock>();
            List<AppScreenshot> appScreenshots = app.getAppScreenshots();
            if (appScreenshots != null && appScreenshots.size() > 0)
            {
                for (AppScreenshot appScreenshot : appScreenshots)
                {
                    AppScreenshotBlock appScreenshotBlock = new AppScreenshotBlock();
                    appScreenshotBlock.setScreenshot(appScreenshot.getScreenshot());
                    appScreenshotBlock.setScreenshotUrl(appScreenshot.getScreenshotUrl());
                    appScreenshotBlocks.add(appScreenshotBlock);
                }
            }
            // AppCategoryBlock appCategoryBlock = new AppCategoryBlock();
            // appCategoryBlock.setName(app.getAppCategory().getName());
            // appCategoryBlock.setDescription(app.getAppCategory().getDescription());
            // appBlock.setAppCategoryBlock(appCategoryBlock);
            // appBlock.setAppDescription(app.getAppDescription());
            // appBlock.setAppUpDescription(app.getAppUpDescription());
            // appBlock.setAppSize(app.getAppSize());
            // appBlock.setVersionCode(app.getVersionCode());
            // appBlock.setVersionName(app.getVersionName());
            // appBlock.setAppCode(app.getAppCode());
            appBlock.setId(app.getId());
            appBlock.setAppName(app.getAppName());
            appBlock.setDeveloper(app.getDeveloper());
            appBlock.setAppScreenshotsBlock(appScreenshotBlocks);
            appBlock.setInstallUrl(app.getInstallUrl());
            appBlock.setLogoAppImg(app.getLogoAppImg());
            appBlock.setOpenUrl(app.getOpenUrl());
            appBlock.setAppAttachment(app.getAppAttachment());
            appBlock.setOperatingSystem(app.getOperatingSystem().toString());
            if(app.getAppStat() != null) {
            	AppStatBlock appStatBlock = new AppStatBlock();
            	appStatBlock.setAvgRating(app.getAppStat().getAvgRating());
            	appStatBlock.setCountRating(app.getAppStat().getCountRating());
            	appStatBlock.setCountUser(app.getAppStat().getCountUser());
            	appStatBlock.setCountView(app.getAppStat().getCountView());
            }
            rows.add(appBlock);
        }
        PageModel pageModel = new PageModel();
        pageModel.setPageNumber(page.getPageNumber());
        pageModel.setTotalPages(page.getTotalPages());
        listRestFulModel.setPage(pageModel);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("获取应用列表成功");
        listRestFulModel.setRows(rows);
        return listRestFulModel;
    }

    /**
     * app应用统计 <功能详细描述>
     * 
     * @1:获取，更新 应用平均分；
     * @2:获取，更新 应用评分数量；
     * @3:获取，更新 应用安装用户量；
     * @4:获取，更新 应用查看的数量；
     * @return
     * @see
     */
    @GET
    @Path("/v1/appStat/{id}")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileRestFulModel appStat(@PathParam("id") Long id)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        
		App app = appService.find(id);
		if (app == null) {
			mobileRestFulModel.setResultCode(1);
			mobileRestFulModel.setResultMessage("应用不存在");
			return mobileRestFulModel;
		}
		
		//如果该app下不存在appstat数据则生成
		if(app.getAppStat() == null){
			AppStat appStat = appStatService.generateAppStat(app);
			app.setAppStat(appStat);
			appService.update(app);
		}
        
        AppStatBlock appStatBlock = new AppStatBlock();
        // 设置appStatBlock
        appStatBlock.setId(id);
        appStatBlock.setAvgRating(appStatService.findAvgRating(id));
        appStatBlock.setCountRating(appStatService.findCountRating(id));
        appStatBlock.setCountUser(appStatService.findCountUser(id));
        appStatBlock.setCountView(appStatService.findCountView(id));

        // 设置返回值
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("获取统计成功");
        mobileRestFulModel.setRows(appStatBlock);

        return mobileRestFulModel;
    }
    
    /**
     * 用户安装应用清单 <这边的应用清单是包含native应用和HBuilder和HyBird应用>
     * 	这边的native应用不需要安装
     * @param appBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/existedAppList")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel existedAppList(AppBean appBean)
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<AppBlock> rows = new ArrayList<AppBlock>();

        OperatingSystem operatingSystem = appBean.getOperatingSystem();
        if(operatingSystem == null){
        	listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("操作系统不能为空！"));
            return listRestFulModel;
        }
        
        Long memberId = appBean.getMemberId();
        if (memberId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("参数错误"));
            return listRestFulModel;
        }
        Member member = memberService.find(memberId);
        if (member == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("未知人员不存在！"));
            return listRestFulModel;
        }
        
        Long schoolId = appBean.getSchoolId();
        if (schoolId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("参数错误"));
            return listRestFulModel;
        }
        DictSchool dictSchool = dictSchoolService.find(schoolId);
        if(dictSchool == null){
        	listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("该学校不存在！"));
            return listRestFulModel;
        }
        
        //这边app下发是由两部分组成，一个是native应用（后台授权的），还有一种就是用户安装的HyBird和HBuilder应用
        
        //1.native应用（后台授权的）
        List<MemberType> memberTypes = new ArrayList<Member.MemberType>();
        List<DictSchool> dictSchools = new ArrayList<DictSchool>();
        memberTypes.add(member.getMemberType());
        dictSchools.add(dictSchool);
        List<AppRole> appRoles = appRoleService.findList(memberTypes);
        
        Pageable pageable4App = new Pageable();
        
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        pageable4App.setOrders(orders);
        
        Page<App> page = appService.findPage(null, pageable4App, true, operatingSystem ,appRoles,null,dictSchools);
        
        
        //2.用户安装的HyBird和HBuilder应用
        Page<AppUser> appUsers = appUserService.findByMember(member, false, null, null);
        
        //应用数据判断
        if ((page == null || page.getContent().size() == 0) && (appUsers == null || appUsers.getContent().size() == 0))
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("无应用列表");
            return listRestFulModel;
        }
        
        //应用数据组装
        if(page != null && page.getContent().size() > 0){
        	for (App app : page.getContent())
            {
        		createAppBlock(app, rows);
            }
        }
        
        if(appUsers != null && appUsers.getContent().size() > 0){
        	for (AppUser appUser : appUsers.getContent())
            {
        		if(appUser.getApp() != null){
        			createAppBlock(appUser.getApp(), rows);
        		}
            }
        }
        
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("获取应用列表成功");
        listRestFulModel.setRows(rows);
        return listRestFulModel;
    }
    
    /**
     *  组装appBlock数据
     * @param app
     * @param rows
     */
    private void createAppBlock(App app, List<AppBlock> rows){
    	AppBlock appBlock = new AppBlock();
        AppCategoryBlock appCategoryBlock = new AppCategoryBlock();
        appCategoryBlock.setName(app.getAppCategory().getName());
        appCategoryBlock.setDescription(app.getAppCategory().getDescription());
        appCategoryBlock.setAppCategoryLogoUrl(app.getAppCategory().getAppCategoryLogoUrl());
        List<AppScreenshotBlock> appScreenshotBlocks = new ArrayList<AppScreenshotBlock>();
        List<AppScreenshot> appScreenshots = app.getAppScreenshots();
        if (appScreenshots != null && appScreenshots.size() > 0)
        {
            for (AppScreenshot appScreenshot : appScreenshots)
            {
                AppScreenshotBlock appScreenshotBlock = new AppScreenshotBlock();
                appScreenshotBlock.setScreenshot(appScreenshot.getScreenshot());
                appScreenshotBlock.setScreenshotUrl(appScreenshot.getScreenshotUrl());
                appScreenshotBlocks.add(appScreenshotBlock);
            }
        }
        appBlock.setId(app.getId());
        appBlock.setAppCategoryBlock(appCategoryBlock);
        appBlock.setAppName(app.getAppName());
        appBlock.setAppDescription(app.getAppDescription());
        appBlock.setAppUpDescription(app.getAppUpDescription());
        appBlock.setAppScreenshotsBlock(appScreenshotBlocks);
        appBlock.setInstallUrl(app.getInstallUrl());
        appBlock.setLogoAppImg(app.getLogoAppImg());
        appBlock.setOpenUrl(app.getOpenUrl());
        appBlock.setAppSize(app.getAppSize());
        appBlock.setVersionCode(app.getVersionCode());
        appBlock.setVersionName(app.getVersionName());
        appBlock.setAppCode(app.getAppCode());
        appBlock.setDeveloper(app.getDeveloper());
        appBlock.setAppAttachment(app.getAppAttachment());
        appBlock.setOperatingSystem(app.getOperatingSystem().toString());
        if(app.getAppStat() != null) {
        	AppStatBlock appStatBlock = new AppStatBlock();
        	appStatBlock.setAvgRating(app.getAppStat().getAvgRating());
        	appStatBlock.setCountRating(app.getAppStat().getCountRating());
        	appStatBlock.setCountUser(app.getAppStat().getCountUser());
        	appStatBlock.setCountView(app.getAppStat().getCountView());
        }
        rows.add(appBlock);
    }

}
