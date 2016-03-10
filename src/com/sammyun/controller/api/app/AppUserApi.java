package com.sammyun.controller.api.app;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.sammyun.Order;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.controller.api.bean.app.AppUserBean;
import com.sammyun.controller.api.block.app.AppBlock;
import com.sammyun.controller.api.block.app.AppScreenshotBlock;
import com.sammyun.controller.api.block.app.AppUserBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.Member;
import com.sammyun.entity.app.App;
import com.sammyun.entity.app.AppScreenshot;
import com.sammyun.entity.app.AppUser;
import com.sammyun.service.MemberService;
import com.sammyun.service.app.AppService;
import com.sammyun.service.app.AppUserService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.SpringUtils;

/**
 * 
 * api - 用户App清单数据
 * 
 * @author  xutianlong
 * @version  [版本号, 2015-8-18]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("appUser")
@Path("/appUser")
public class AppUserApi
{
    @Resource(name = "memberServiceImpl")
    private MemberService memberService;
    
    @Resource(name = "appUserServiceImpl") 
    private AppUserService appUserService;
    
    @Resource(name = "appServiceImpl") 
    private AppService appService;
    
    /**
     * 查询用户应用清单(包含HBuilder和HyBird下载的应用，不包含native应用)
     * <功能详细描述>
     * @param appUserListBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/appUser/list")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel appUserList(AppUserBean appUserBean)
    {

        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<AppUserBlock> rows = new LinkedList<AppUserBlock>();
        Long memberId = appUserBean.getMemberId();
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
        
//        if (appUserBean.getOperatingSystem() == null) {
//			listRestFulModel.setResultCode(1);
//			listRestFulModel.setResultMessage("应用操作系统不能为空");
//			return listRestFulModel;
//		}

        Pageable pageable;
        if (appUserBean.getPage() == null)
        {
            pageable = new Pageable();
        }
        else
        {
            pageable = new Pageable(appUserBean.getPage().getPageNumber(),
            		appUserBean.getPage().getPageSize());

        }
        // 是否删除
        Boolean isDelete = false;

        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        pageable.setOrders(orders);

        //操作系统 HyBird 和 HBuilder
        Page<AppUser> appUsers = appUserService.findByMember(member, isDelete, null, pageable);
        if (appUsers == null || appUsers.getContent().size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("用户应用清单为空!"));
            return listRestFulModel;
        }
        for (AppUser appUser : appUsers.getContent())
        {
            AppUserBlock appUserBlock = new AppUserBlock();
            App app = appUser.getApp();
            
            appUserBlock.setId(appUser.getId());
            appUserBlock.setDeviceInfo(appUser.getDeviceInfo());

            if (app != null)
            {
            	AppBlock appBlock = new AppBlock();
            	appBlock.setId(app.getId());
                appBlock.setAppName(app.getAppName());
                appBlock.setAppDescription(app.getAppDescription());
                appBlock.setAppUpDescription(app.getAppUpDescription());
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
                appBlock.setAppScreenshotsBlock(appScreenshotBlocks);
            	appUserBlock.setAppBlock(appBlock);
            }
            if(appUser.getInstallTime() != null){
            	appUserBlock.setInstallTime(DateUtil.date2String(appUser.getInstallTime(), 1));
            }
            if(appUser.getUpdateTime() != null){
            	appUserBlock.setUpdateTime(DateUtil.date2String(appUser.getUpdateTime(), 1));
            }
            rows.add(appUserBlock);
        }
        PageModel page = new PageModel();
        page.setPageNumber(appUsers.getPageNumber());
        page.setTotalPages(appUsers.getTotalPages());
        listRestFulModel.setPage(page);
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("用户应用清单查询成功"));
        return listRestFulModel;
    }
    
   /**
    * 删除用户应用
    * <功能详细描述>
    * @param userAppId
    * @return
    * @see [类、类#方法、类#成员]
    */
    @GET
    @Path("/v1/deleteUserApp/{id}/{memberId}")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel deleteUserApp(@PathParam("id") Long id, @PathParam("memberId") Long memberId)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        if (id == null || memberId == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }
        
        App app = appService.find(id);
        if(app == null){
        	mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("不存在该应用！");
            return mobileRestFulModel;
        }
        Member member = memberService.find(memberId);
        if(member == null){
        	mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("不存在该用户！");
            return mobileRestFulModel;
        }
        
        AppUser appUser = appUserService.findByParam(member, app);
        if (appUser == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("安装记录不存在！");
            return mobileRestFulModel;
        }
        
        appUser.setIsDelete(true);
        appUserService.update(appUser);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage(SpringUtils.getMessage("删除成功！"));
        return mobileRestFulModel;

    }
    
    /**
     * 安装用户应用
     * @param appUserBean
     * @return
     */
     @POST
     @Path("/v1/installUserApp")
     @Produces("application/json;charset=UTF-8")
     public MobileRestFulModel installUserApp(AppUserBean appUserBean)
     {
         MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
         
         App app = appService.find(appUserBean.getAppId());
         if (app == null)
         {
             mobileRestFulModel.setResultCode(1);
             mobileRestFulModel.setResultMessage("该下载应用不存在！");
             return mobileRestFulModel;
         }
         
         Member member = memberService.find(appUserBean.getMemberId());
         if (member == null)
         {
             mobileRestFulModel.setResultCode(1);
             mobileRestFulModel.setResultMessage("该用户不存在！");
             return mobileRestFulModel;
         }
           
         AppUser appUser = appUserService.findByParam(member, app);
         if(appUser == null){
        	 appUser = new AppUser();
        	 appUser.setApp(app);
             appUser.setMember(member);
             appUser.setInstallTime(DateUtil.getDate());
             appUser.setIsDelete(false);
             appUser.setDeviceInfo(appUserBean.getDeviceInfo());
             appUserService.save(appUser);
             
             Set<AppUser> appUsers = app.getAppUsers();
             appUsers.add(appUser);
             app.setAppUsers(appUsers);
             appService.update(app);
         }
         else{
        	 if(appUser.getIsDelete()){
        		 appUser.setIsDelete(false);
            	 appUserService.update(appUser); 
        	 }
         }
         
         mobileRestFulModel.setResultCode(0);
         mobileRestFulModel.setResultMessage(SpringUtils.getMessage("安装应用成功！"));
         return mobileRestFulModel;

     }

}
