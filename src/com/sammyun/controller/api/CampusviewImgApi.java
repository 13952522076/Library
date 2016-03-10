package com.sammyun.controller.api;

import java.math.BigDecimal;
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

import com.sammyun.Order;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.controller.api.bean.CampusviewImgAlbumsBean;
import com.sammyun.controller.api.block.CampusviewImgAlbumsAttachBlock;
import com.sammyun.controller.api.block.CampusviewImgAlbumsBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.Member;
import com.sammyun.entity.campusviewImg.CampusviewImageAttach;
import com.sammyun.entity.campusviewImg.CampusviewImgFavorite;
import com.sammyun.entity.campusviewImg.CampusviewImgView;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.MemberService;
import com.sammyun.service.campusviewImg.CampusviewImageAttachService;
import com.sammyun.service.campusviewImg.CampusviewImgFavoriteService;
import com.sammyun.service.campusviewImg.CampusviewImgService;
import com.sammyun.service.campusviewImg.CampusviewImgViewService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.SpringUtils;

/**
 * api - 校园风光表
 * 
 * @author Sencloud Team
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("campusviewImg")
@Path("/campusviewImg")
public class CampusviewImgApi
{
    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "campusviewImgServiceImpl")
    private CampusviewImgService campusviewImgService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "campusviewImgFavoriteServiceImpl")
    private CampusviewImgFavoriteService campusviewImgFavoriteService;

    @Resource(name = "campusviewImgViewServiceImpl")
    private CampusviewImgViewService campusviewImgViewService;

    @Resource(name = "campusviewImageAttachServiceImpl")
    private CampusviewImageAttachService campusviewImageAttachService;

    /**
     * 查询校园相册 做
     * 
     * @param dictSchoolId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/albums")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel albums(CampusviewImgAlbumsBean campusviewImgAlbumsBean)
    {

        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<CampusviewImgAlbumsBlock> rows = new LinkedList<CampusviewImgAlbumsBlock>();
        Long dictSchoolId = campusviewImgAlbumsBean.getDictSchoolId();
        if (dictSchoolId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误！");
            return listRestFulModel;
        }

        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        if (dictSchool == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("未知学校不存在！"));
            return listRestFulModel;
        }
        Pageable pageable;
        if (campusviewImgAlbumsBean.getPage() == null)
        {
            pageable = new Pageable();
        }
        else
        {
            pageable = new Pageable(campusviewImgAlbumsBean.getPage().getPageNumber(),
                    campusviewImgAlbumsBean.getPage().getPageSize());

        }
        // 状态（0未屏蔽，1屏蔽）
        Long status = 0L;

        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        pageable.setOrders(orders);

        Page<com.sammyun.entity.campusviewImg.CampusviewImg> campusviewImgs = campusviewImgService.findBySchool(
                dictSchool, status, pageable);
        if (campusviewImgs == null || campusviewImgs.getContent().size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关校园相册！"));
            return listRestFulModel;
        }
        for (com.sammyun.entity.campusviewImg.CampusviewImg campusviewImg : campusviewImgs.getContent())
        {
            CampusviewImgAlbumsBlock campusviewImgAlbumsBlock = new CampusviewImgAlbumsBlock();
            if (campusviewImg.getLastUpdateTime() != null)
            {
                campusviewImgAlbumsBlock.setLastUpdateTime(DateUtil.date2String(campusviewImg.getLastUpdateTime(), 1));
            }
            campusviewImgAlbumsBlock.setId(campusviewImg.getId());
            campusviewImgAlbumsBlock.setCoverImage(campusviewImg.getCoverImage());
            campusviewImgAlbumsBlock.setDescription(campusviewImg.getDescription());
            campusviewImgAlbumsBlock.setFavoriteCount(catulateNum(campusviewImg.getFavoriteCount()));
            campusviewImgAlbumsBlock.setLatitude(campusviewImg.getLatitude());
            campusviewImgAlbumsBlock.setLongitude(campusviewImg.getLongitude());
            campusviewImgAlbumsBlock.setLocationName(campusviewImg.getLocationName());
            campusviewImgAlbumsBlock.setViewCount(catulateNum(campusviewImg.getViewCount()));
            campusviewImgAlbumsBlock.setLastUpdator(campusviewImg.getLastUpdator());
            List<CampusviewImgAlbumsAttachBlock> campusviewImageAttachs = new LinkedList<CampusviewImgAlbumsAttachBlock>();
            for (CampusviewImageAttach campusviewImageAttacha : campusviewImg.getCampusviewImageAttachs())
            {
                CampusviewImgAlbumsAttachBlock campusviewImgAlbumsAttachBlock = new CampusviewImgAlbumsAttachBlock();
                campusviewImgAlbumsAttachBlock.setId(campusviewImageAttacha.getId());
                campusviewImgAlbumsAttachBlock.setImageAttach(campusviewImageAttacha.getImageAttach());
                campusviewImageAttachs.add(campusviewImgAlbumsAttachBlock);
            }
            campusviewImgAlbumsBlock.setCampusviewImageAttachs(campusviewImageAttachs);
            rows.add(campusviewImgAlbumsBlock);
        }
        PageModel page = new PageModel();
        page.setPageNumber(campusviewImgs.getPageNumber());
        page.setTotalPages(campusviewImgs.getTotalPages());
        listRestFulModel.setPage(page);
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("获取校园相册信息成功！"));
        return listRestFulModel;
    }

    /**
     * 计算访问量
     * 
     * @param viewNum
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String catulateNum(BigDecimal viewNum)
    {
        String returnResult = null;
        if (viewNum == null)
        {
            returnResult = "0次";
            return returnResult;
        }

        int result = viewNum.compareTo(new BigDecimal(9999));
        if (result <= 0)
        {
            returnResult = viewNum.toString();
        }
        else if (result > 0)
        {
            BigDecimal count = viewNum.divide((new BigDecimal(10000)));
            count = count.setScale(0, BigDecimal.ROUND_DOWN);
            returnResult = SpringUtils.getMessage("News.count", count);
        }
        return returnResult;
    }

    /**
     * 校园风光点赞记录表 <功能详细描述>
     * 
     * @param campusviewImgId
     * @param memberId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/favorite/{campusviewImgId}/{memberId}")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel favorite(@PathParam("campusviewImgId") Long campusviewImgId,
            @PathParam("memberId") Long memberId)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        if (campusviewImgId == null || memberId == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }

        com.sammyun.entity.campusviewImg.CampusviewImg campusviewImg = campusviewImgService.find(campusviewImgId);
        if (campusviewImg == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("未知校园相册不存在！"));
            return mobileRestFulModel;
        }

        Member member = memberService.find(memberId);
        if (member == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("未知用户不存在！"));
            return mobileRestFulModel;
        }
        CampusviewImgFavorite campusviewImgFavorite = campusviewImgFavoriteService.findByMemberAndImg(member,
                campusviewImg);
        if (campusviewImgFavorite == null)
        {
            CampusviewImgFavorite tcampusviewImgFavorite = new CampusviewImgFavorite();
            tcampusviewImgFavorite.setCampusviewImg(campusviewImg);
            tcampusviewImgFavorite.setMember(member);
            tcampusviewImgFavorite.setTimestamp(DateUtil.getDate());
            campusviewImgFavoriteService.save(tcampusviewImgFavorite);
        }
        else
        {
            campusviewImgFavorite.setTimestamp(DateUtil.getDate());
            campusviewImgFavoriteService.update(campusviewImgFavorite);
        }
        List<CampusviewImgFavorite> campusviewImgFavorites = campusviewImgFavoriteService.findByImg(campusviewImg);
        if (campusviewImgFavorites == null || campusviewImgFavorites.size() == 0)
        {
            campusviewImg.setFavoriteCount(new BigDecimal(0));

        }
        else
        {
            campusviewImg.setFavoriteCount(new BigDecimal(campusviewImgFavorites.size()));
        }
        campusviewImgService.update(campusviewImg);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage(SpringUtils.getMessage("点赞成功！"));
        return mobileRestFulModel;
    }

    /**
     * 校园风光查看记录表 <功能详细描述>
     * 
     * @param campusviewImgId
     * @param memberId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/view/{campusviewImgId}/{memberId}")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel view(@PathParam("campusviewImgId") Long campusviewImgId,
            @PathParam("memberId") Long memberId)
    {

        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        if (campusviewImgId == null || memberId == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }

        com.sammyun.entity.campusviewImg.CampusviewImg campusviewImg = campusviewImgService.find(campusviewImgId);
        if (campusviewImg == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("未知校园相册不存在！"));
            return mobileRestFulModel;
        }

        Member member = memberService.find(memberId);
        if (member == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("未知用户不存在！"));
            return mobileRestFulModel;
        }
        CampusviewImgView campusviewImgView = campusviewImgViewService.findByMemberAndImg(member, campusviewImg);
        if (campusviewImgView == null)
        {
            CampusviewImgView tcampusviewImgView = new CampusviewImgView();
            tcampusviewImgView.setCampusviewImg(campusviewImg);
            tcampusviewImgView.setMember(member);
            tcampusviewImgView.setTimestamp(DateUtil.getDate());
            campusviewImgViewService.save(tcampusviewImgView);
        }
        else
        {
            campusviewImgView.setTimestamp(DateUtil.getDate());
            campusviewImgViewService.update(campusviewImgView);
        }
        List<CampusviewImgView> campusviewImgViews = campusviewImgViewService.findByImg(campusviewImg);
        if (campusviewImgViews == null || campusviewImgViews.size() == 0)
        {
            campusviewImg.setViewCount(new BigDecimal(0));

        }
        else
        {
            campusviewImg.setViewCount(new BigDecimal(campusviewImgViews.size()));
        }
        campusviewImgService.update(campusviewImg);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage(SpringUtils.getMessage("查看校园风光成功！"));
        return mobileRestFulModel;
    }
}
