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
import com.sammyun.controller.api.bean.ParentingSummaryBean;
import com.sammyun.controller.api.block.NewsContentBlock;
import com.sammyun.controller.api.block.ParentingCategoryBlock;
import com.sammyun.controller.api.block.ParentingSummaryBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.parenting.ParentingCategory;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.service.news.NewsCategoryService;
import com.sammyun.service.news.NewsService;
import com.sammyun.service.parenting.ParentingCategoryService;
import com.sammyun.service.parenting.ParentingService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.ImUserUtil;
import com.sammyun.util.SpringUtils;

/**
 * Api - 育儿数据数据
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("parenting")
@Path("/parenting")
public class ParentingApi
{
    @Resource(name = "parentingCategoryServiceImpl")
    private ParentingCategoryService parentingCategoryService;

    @Resource(name = "parentingServiceImpl")
    private ParentingService parentingService;

    @Resource(name = "newsCategoryServiceImpl")
    private NewsCategoryService newsCategoryService;

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "newsServiceImpl")
    private NewsService newsService;

    /**
     * 查询每个学校的育儿目录
     * 
     * @param dictSchoolId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/category/{dictSchoolId}")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel category(@PathParam("dictSchoolId") Long dictSchoolId)
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<ParentingCategoryBlock> rows = new LinkedList<ParentingCategoryBlock>();
        // if (dictSchoolId == null)
        // {
        // listRestFulModel.setResultCode(1);
        // listRestFulModel.setResultMessage("参数错误！");
        // return listRestFulModel;
        // }
        // DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        // if (dictSchool == null)
        // {
        // listRestFulModel.setResultCode(1);
        // listRestFulModel.setResultMessage(SpringUtils.getMessage("未知学校不存在！"));
        // return listRestFulModel;
        // }
        // 删除标记（0未删除，1删除）
        Integer defFlag = 0;
        // 状态（0，上架 1，下架)
        Integer status = 0;

        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        List<ParentingCategory> parentingCategories = parentingCategoryService.findBySchool(null, defFlag, status,
                orders);
        if (parentingCategories == null || parentingCategories.size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关育儿类别！"));
            return listRestFulModel;
        }
        for (ParentingCategory parentingCategory : parentingCategories)
        {
            ParentingCategoryBlock parentingCategoryBlock = new ParentingCategoryBlock();
            parentingCategoryBlock.setId(parentingCategory.getId());
            parentingCategoryBlock.setCategoryName(parentingCategory.getCategoryName());
            rows.add(parentingCategoryBlock);
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("获取育儿目录成功！"));
        return listRestFulModel;
    }

    /**
     * 查询具体学校的置顶育儿信息
     * 
     * @param dictSchoolId
     * @return
     * @see [类、类#方法、类#成员]
     */
    // @GET
    // @Path("/v1/top/{dictSchoolId}")
    // @Produces("application/json;charset=UTF-8")
    // public ListRestFulModel top(@PathParam("dictSchoolId") Long
    // dictSchoolId)
    // {
    // ListRestFulModel listRestFulModel = new ListRestFulModel();
    // List<ParentingSummaryBlock> rows = new
    // LinkedList<ParentingSummaryBlock>();
    // ImUserUtil imUserUtil = new ImUserUtil();
    // // if (dictSchoolId == null)
    // // {
    // // listRestFulModel.setResultCode(1);
    // // listRestFulModel.setResultMessage("参数错误！");
    // // return listRestFulModel;
    // // }
    // // DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
    // // if (dictSchool == null)
    // // {
    // // listRestFulModel.setResultCode(1);
    // //
    // listRestFulModel.setResultMessage(SpringUtils.getMessage("未知学校不存在！"));
    // // return listRestFulModel;
    // // }
    // List<com.sammyun.entity.parenting.Parenting> parentings =
    // parentingService.findBySchool(null, true, 0, 0, 0);
    // if (parentings == null || parentings.size() == 0)
    // {
    // listRestFulModel.setResultCode(1);
    // listRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关置顶育儿新闻！"));
    // return listRestFulModel;
    // }
    // for (com.sammyun.entity.parenting.Parenting parenting : parentings)
    // {
    // ParentingSummaryBlock parentingSummaryBlock = new
    // ParentingSummaryBlock();
    // parentingSummaryBlock.setId(parenting.getId());
    // parentingSummaryBlock.setTitle(parenting.getTitle());
    // parentingSummaryBlock.setSmallIconfile(imUserUtil.getDefaultImageUrl(parenting.getSmallIconfile()));
    // rows.add(parentingSummaryBlock);
    // }
    // listRestFulModel.setRows(rows);
    // listRestFulModel.setResultCode(0);
    // listRestFulModel.setResultMessage(SpringUtils.getMessage("获取置顶育儿新闻成功！"));
    // return listRestFulModel;
    // }

    @GET
    @Path("/v1/top/{categoryId}")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel top(@PathParam("categoryId") Long categoryId)
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        ParentingCategory parentingCategory = parentingCategoryService.find(categoryId);
        if (parentingCategory == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("未查找到对应分类");
            return listRestFulModel;
        }
        List<ParentingSummaryBlock> rows = new LinkedList<ParentingSummaryBlock>();
        ImUserUtil imUserUtil = new ImUserUtil();
        List<com.sammyun.entity.parenting.Parenting> parentings = parentingService.findByCategory(parentingCategory,
                true);
        if (parentings == null || parentings.size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关置顶育儿新闻！"));
            return listRestFulModel;
        }
        for (com.sammyun.entity.parenting.Parenting parenting : parentings)
        {
            ParentingSummaryBlock parentingSummaryBlock = new ParentingSummaryBlock();
            parentingSummaryBlock.setId(parenting.getId());
            parentingSummaryBlock.setTitle(parenting.getTitle());
            parentingSummaryBlock.setSmallIconfile(imUserUtil.getDefaultImageUrl(parenting.getSmallIconfile()));
            rows.add(parentingSummaryBlock);
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("获取置顶育儿新闻成功！"));
        return listRestFulModel;
    }

    /**
     * 查询具体学校的非置顶育儿目录下的新闻摘要
     * 
     * @param dictSchoolId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/summary")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel summary(ParentingSummaryBean parentingSummaryBean)
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<ParentingSummaryBlock> rows = new LinkedList<ParentingSummaryBlock>();
        ImUserUtil imUserUtil = new ImUserUtil();
        Long categoryId = parentingSummaryBean.getCategoryId();
        if (categoryId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误！");
            return listRestFulModel;
        }
        ParentingCategory parentingCategory = parentingCategoryService.find(categoryId);
        if (parentingCategory == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("未知育儿目录不存在！"));
            return listRestFulModel;
        }
        Pageable pageable;
        if (parentingSummaryBean.getPage() == null)
        {
            pageable = new Pageable();
        }
        else
        {
            pageable = new Pageable(parentingSummaryBean.getPage().getPageNumber(),
                    parentingSummaryBean.getPage().getPageSize());
        }
        // 状态（0未屏蔽，1屏蔽）
        // Integer status = 0;
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        pageable.setOrders(orders);
        Page<com.sammyun.entity.parenting.Parenting> parentings = parentingService.findByCategory(parentingCategory,
                false, 0, pageable);
        if (parentings == null || parentings.getContent().size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关育儿列表！"));
            return listRestFulModel;
        }
        for (com.sammyun.entity.parenting.Parenting parenting : parentings.getContent())
        {
            ParentingSummaryBlock parentingSummaryBlock = new ParentingSummaryBlock();
            parentingSummaryBlock.setId(parenting.getId());
            parentingSummaryBlock.setSmallIconfile(imUserUtil.getDefaultImageUrl(parenting.getSmallIconfile()));
            parentingSummaryBlock.setSummary(parenting.getSummary());
            parentingSummaryBlock.setTitle(parenting.getTitle());
            rows.add(parentingSummaryBlock);
        }
        PageModel page = new PageModel();
        page.setPageNumber(parentings.getPageNumber());
        page.setTotalPages(parentings.getTotalPages());
        listRestFulModel.setPage(page);
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("获取育儿摘要成功！"));
        return listRestFulModel;
    }

    /**
     * 查询育儿具体内容
     * 
     * @param dictSchoolId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/content/{parentingId}")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel content(@PathParam("parentingId") Long parentingId)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        if (parentingId == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }

        com.sammyun.entity.parenting.Parenting parenting = parentingService.find(parentingId);
        if (parenting == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("未知育儿新闻不存在！"));
            return mobileRestFulModel;
        }
        if (parenting.getViewCount() == null)
        {
            parenting.setViewCount(new BigDecimal(1));
        }
        else
        {
            BigDecimal count = parenting.getViewCount();
            count = count.add(new BigDecimal(1));
            parenting.setViewCount(count);
        }
        parentingService.update(parenting);
        NewsContentBlock rows = new NewsContentBlock();
        rows.setTitle(parenting.getTitle());
        rows.setAuthor(parenting.getAuthor());
        rows.setContent(parenting.getContent());
        rows.setDetailUrl(parenting.getDetailUrl());
        rows.setSource(parenting.getSource());
        rows.setSummary(parenting.getSummary());
        if (parenting.getTimePublish() != null)
        {
            rows.setTimePublish(DateUtil.date2String(parenting.getTimePublish(), 1));
        }
        int result = parenting.getViewCount().compareTo(new BigDecimal(9999));
        if (result <= 0)
        {
            rows.setViewCount(parenting.getViewCount().toString());
        }
        else if (result > 0)
        {
            BigDecimal count = parenting.getViewCount().divide((new BigDecimal(10000)));
            count = count.setScale(0, BigDecimal.ROUND_DOWN);
            rows.setViewCount(SpringUtils.getMessage("News.count", count));
        }
        mobileRestFulModel.setRows(rows);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage(SpringUtils.getMessage("获取育儿内容成功！"));
        return mobileRestFulModel;
    }
}
