package com.sammyun.controller.api;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.sammyun.Order;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.controller.api.bean.AdListBean;
import com.sammyun.controller.api.bean.AdStatBean;
import com.sammyun.controller.api.block.AdListBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.Member;
import com.sammyun.entity.ad.Ad;
import com.sammyun.entity.ad.Ad.AdPosition;
import com.sammyun.entity.ad.Ad.Platform;
import com.sammyun.entity.ad.Ad.ShowType;
import com.sammyun.entity.ad.Ad.SimType;
import com.sammyun.entity.ad.Ad.Type;
import com.sammyun.entity.ad.AdArea;
import com.sammyun.entity.ad.AdDeviceType;
import com.sammyun.entity.ad.AdNetType;
import com.sammyun.entity.ad.AdNetWork;
import com.sammyun.entity.ad.AdStat;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.MemberService;
import com.sammyun.service.ad.AdAreaService;
import com.sammyun.service.ad.AdDeviceTypeService;
import com.sammyun.service.ad.AdNetTypeService;
import com.sammyun.service.ad.AdNetWorkService;
import com.sammyun.service.ad.AdService;
import com.sammyun.service.ad.AdStatService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.SpringUtils;

/**
 * api - 广告数据
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("ad")
@Path("/ad")
public class AdApi
{
    @Resource(name = "adServiceImpl")
    private AdService adService;
    
    @Resource(name = "memberServiceImpl")
    private MemberService memberService;
    
    @Resource(name = "adAreaServiceImpl")
    private AdAreaService adAreaService;
    
    @Resource(name = "adDeviceTypeServiceImpl")
    private AdDeviceTypeService adDeviceTypeService;
    
    @Resource(name = "adStatServiceImpl") 
	private AdStatService adStatService;
    
    @Resource(name = "adNetTypeServiceImpl")
    private AdNetTypeService adNetTypeService;
    
    @Resource(name = "adNetWorkServiceImpl")
    private AdNetWorkService adNetWorkService;
    
    /**
     * 查询广告
     * 
     * @param announcementSummaryBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/list")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel list(AdListBean adListBean)
    {

        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<AdListBlock> rows = new LinkedList<AdListBlock>();
        Long memberId = adListBean.getMemberId();
        
        Type type = adListBean.getType();
        Platform platform = adListBean.getPlatform();
        AdPosition adPosition = adListBean.getAdPosition();
        ShowType showType = adListBean.getShowType();
        SimType simType = adListBean.getSimType();
        
        String adAreaName = adListBean.getAdAreaName();
        String deviceType =adListBean.getDeviceType();
        String netType = adListBean.getNetType();
        String netWork = adListBean.getNetWork();
        String slotTime = adListBean.getSlotTime();
         
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
            listRestFulModel.setResultMessage(SpringUtils.getMessage("未知人员不存在"));
            return listRestFulModel;
        }

        Pageable pageable = new Pageable();
        if (adListBean.getPage() == null)
        {
            pageable = new Pageable();
        }
        else
        {
            pageable = new Pageable(adListBean.getPage().getPageNumber(),
                    adListBean.getPage().getPageSize());

        }
        //是否上架 
        Boolean isOnLine = true;
         //是否是草稿
        Boolean isDraft  = false;
        
        // 广告分类是否被禁用 
        Boolean status = true;
        
        //查找定向条件
        List<AdArea> adAreas = adAreaService.finListByFullName(adAreaName);
        List<AdDeviceType> adDeviceTypes = adDeviceTypeService.finListByDeviceType(deviceType);
        List<AdNetType> adNetTypes = adNetTypeService.finListByNetType(netType);
        List<AdNetWork> adNetWorks = adNetWorkService.finListByNetWork(netWork);
        
        List<DictSchool> adDictSchools = new LinkedList<DictSchool>();
        adDictSchools.add(member.getDictSchool());

        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        pageable.setOrders(orders);

        Page<Ad> ads = adService.finPage(type,platform, adPosition, showType, simType, null,
                DateUtil.string2Date(DateUtil.date2String(new Date(), 10)), adAreas, adDeviceTypes, adNetTypes,
                adNetWorks, null, adDictSchools, isOnLine, isDraft, status, pageable);
        if (ads == null || ads.getContent().size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关广告数据！"));
            return listRestFulModel;
        }
        for (Ad ad : ads.getContent())
        {
            rows.add(getAdListBlock(ad));
        }
        PageModel page = new PageModel();
        page.setPageNumber(ads.getPageNumber());
        page.setTotalPages(ads.getTotalPages());
        listRestFulModel.setPage(page);
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("获取广告成功"));
        return listRestFulModel;
    }
    
    /**
     * 返回广告数据
     * <功能详细描述>
     * @param ad
     * @return
     * @see [类、类#方法、类#成员]
     */
    public AdListBlock getAdListBlock(Ad ad)
    {
        AdListBlock adListBlock = new AdListBlock();
        adListBlock.setId(ad.getId());
        adListBlock.setAdImage(ad.getAdImage());
        adListBlock.setAdName(ad.getAdName());
        adListBlock.setAdPackageUrl(ad.getAdPackageUrl());
        adListBlock.setAdSite(ad.getAdSite());
        adListBlock.setAppAuthor(ad.getAppAuthor());
        adListBlock.setAppDescription(ad.getAppDescription());
        adListBlock.setAppName(ad.getAppName());
        adListBlock.setContent(ad.getContent());
      
        if (ad.getAdCategory() != null)
        {
            adListBlock.setAdAppCategoryName(ad.getAdCategory().getName());
        }
        adListBlock.setAdPosition(ad.getAdPosition());
        adListBlock.setShowType(ad.getShowType());
        adListBlock.setSimType(ad.getSimType());
        adListBlock.setType(ad.getType());
        return adListBlock;
    }
    
    /**
     * 广告统计 <功能详细描述>
     * 
     * @param appBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/stat")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileRestFulModel stat(AdStatBean adStatBean)
    {
    	MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
    	
    	String ip = adStatBean.getIp();
    	Long memberId = adStatBean.getMemberId();
    	Long id=adStatBean.getId();
        if (ip == null || "".equalsIgnoreCase(ip))
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }
        
        if (memberId == null)
        {
        	 mobileRestFulModel.setResultCode(1);
             mobileRestFulModel.setResultMessage("参数错误！");
             return mobileRestFulModel;
        }
        
        String deviceInfo = adStatBean.getDeviceInfo();
        if (deviceInfo == null||"".equalsIgnoreCase(deviceInfo))
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("设备信息不能为空！");
            return mobileRestFulModel;
        }
        
        if (id == null)
        {
        	 mobileRestFulModel.setResultCode(1);
             mobileRestFulModel.setResultMessage("参数错误！");
             return mobileRestFulModel;
        }
        Ad ad=adService.find(id);
        if (ad == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("广告不存在！"));
            return mobileRestFulModel;
        }
        
        Member member = memberService.find(adStatBean.getMemberId());
        if (member == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.unknownAccount"));
            return mobileRestFulModel;
        }
        
    	AdStat adStat=new AdStat();
    	adStat.setIp(adStatBean.getIp());
    	adStat.setMemberId(adStatBean.getMemberId());
    	adStat.setDeviceInfo(adStatBean.getDeviceInfo());
    	adStatService.save(adStat);
    	
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage(SpringUtils.getMessage("广告统计成功！"));
		return mobileRestFulModel;
    }
}
