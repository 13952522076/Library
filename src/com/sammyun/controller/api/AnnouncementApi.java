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
import com.sammyun.controller.api.bean.AnnouncementSummaryBean;
import com.sammyun.controller.api.block.AnnouncementContentBlock;
import com.sammyun.controller.api.block.AnnouncementSummaryBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.announcement.AnnouncementService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.ImUserUtil;
import com.sammyun.util.SpringUtils;

/**
 * api - 通知公告数据
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("announcement")
@Path("/announcement")
public class AnnouncementApi
{

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "announcementServiceImpl")
    private AnnouncementService announcementService;

    /**
     * 查询当前学校的通知公告
     * 
     * @param dictSchoolId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/summary")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel summary(AnnouncementSummaryBean announcementSummaryBean)
    {

        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<AnnouncementSummaryBlock> rows = new LinkedList<AnnouncementSummaryBlock>();
        ImUserUtil imUserUtil = new ImUserUtil();
        Long dictSchoolId = announcementSummaryBean.getDictSchoolId();
        if (dictSchoolId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("Api.error.message"));
            return listRestFulModel;
        }

        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        if (dictSchool == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("Api.error.school"));
            return listRestFulModel;
        }

        Pageable pageable;
        if (announcementSummaryBean.getPage() == null)
        {
            pageable = new Pageable();
        }
        else
        {
            pageable = new Pageable(announcementSummaryBean.getPage().getPageNumber(),
                    announcementSummaryBean.getPage().getPageSize());

        }
        // 状态（0未屏蔽，1屏蔽）
        Integer status = 0;

        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        pageable.setOrders(orders);

        Page<com.sammyun.entity.announcement.Announcement> announcements = announcementService.findBySchool(dictSchool,
                status, pageable);
        if (announcements == null || announcements.getContent().size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("Api.announcement.noAnnouncement"));
            return listRestFulModel;
        }
        for (com.sammyun.entity.announcement.Announcement announcement : announcements.getContent())
        {
            AnnouncementSummaryBlock announcementSummaryBlock = new AnnouncementSummaryBlock();
            announcementSummaryBlock.setId(announcement.getId());
            announcementSummaryBlock.setSummary(announcement.getSummary());
            announcementSummaryBlock.setTitle(announcement.getTitle());
            announcementSummaryBlock.setSmallIconfile(imUserUtil.getDefaultImageUrl(announcement.getSmallIconfile()));
            rows.add(announcementSummaryBlock);
        }
        PageModel page = new PageModel();
        page.setPageNumber(announcements.getPageNumber());
        page.setTotalPages(announcements.getTotalPages());
        listRestFulModel.setPage(page);
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("Api.announcement.success"));
        return listRestFulModel;
    }

    /**
     * 查询通知公告具体内容
     * 
     * @param dictSchoolId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/content/{announcementId}")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel content(@PathParam("announcementId") Long announcementId)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        if (announcementId == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }

        com.sammyun.entity.announcement.Announcement announcement = announcementService.find(announcementId);
        if (announcement == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("未知通知公告不存在！"));
            return mobileRestFulModel;
        }
        if (announcement.getViewCount() == null)
        {
            announcement.setViewCount(new BigDecimal(1));
        }
        else
        {
            BigDecimal count = announcement.getViewCount();
            count = count.add(new BigDecimal(1));
            announcement.setViewCount(count);
        }
        announcementService.update(announcement);

        AnnouncementContentBlock rows = new AnnouncementContentBlock();
        rows.setTitle(announcement.getTitle());
        rows.setAuthor(announcement.getAuthor());
        rows.setContent(announcement.getContent());
        rows.setSource(announcement.getSource());
        rows.setSummary(announcement.getSummary());
        if (announcement.getTimePublish() != null)
        {
            rows.setTimePublish(DateUtil.date2String(announcement.getTimePublish(), 1));
        }
        int result = announcement.getViewCount().compareTo(new BigDecimal(9999));
        if (result <= 0)
        {
            rows.setViewCount(announcement.getViewCount().toString());
        }
        else if (result > 0)
        {
            BigDecimal count = announcement.getViewCount().divide((new BigDecimal(10000)));
            count = count.setScale(0, BigDecimal.ROUND_DOWN);
            rows.setViewCount(SpringUtils.getMessage("News.count", count));

        }
        mobileRestFulModel.setRows(rows);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage(SpringUtils.getMessage("获取通知公告成功！"));
        return mobileRestFulModel;
    }
}
