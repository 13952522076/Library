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
import com.sammyun.controller.api.bean.NewsSummaryBean;
import com.sammyun.controller.api.block.NewsCategoryBlock;
import com.sammyun.controller.api.block.NewsContentBlock;
import com.sammyun.controller.api.block.NewsSummaryBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.news.NewsCategory;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.service.news.NewsCategoryService;
import com.sammyun.service.news.NewsService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.ImUserUtil;
import com.sammyun.util.SpringUtils;

/**
 * Api - 新闻数据
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("news")
@Path("/news")
public class NewsApi
{

    @Resource(name = "newsCategoryServiceImpl")
    private NewsCategoryService newsCategoryService;

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "newsServiceImpl")
    private NewsService newsService;

    /**
     * 查询每个学校的新闻目录
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
        List<NewsCategoryBlock> rows = new LinkedList<NewsCategoryBlock>();
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

        // 删除标记（0未删除，1删除）
        Integer defFlag = 0;

        // 状态（0，上架 1，下架)
        Integer status = 0;

        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        List<NewsCategory> newsCategories = newsCategoryService.findBySchool(dictSchool, defFlag, status, orders);
        if (newsCategories == null || newsCategories.size() == 0)
        {

            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关新闻类别！"));
            return listRestFulModel;
        }
        for (NewsCategory newsCategory : newsCategories)
        {
            NewsCategoryBlock newsCategoryBlock = new NewsCategoryBlock();
            newsCategoryBlock.setId(newsCategory.getId());
            newsCategoryBlock.setCategoryName(newsCategory.getCategoryName());
            rows.add(newsCategoryBlock);
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("获取新闻目录成功！"));
        return listRestFulModel;
    }

    /**
     * 查询当前目录下的新闻摘要 做
     * 
     * @param dictSchoolId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/summary")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel summary(NewsSummaryBean newsSummaryBean)
    {

        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<NewsSummaryBlock> rows = new LinkedList<NewsSummaryBlock>();
        ImUserUtil imUserUtil = new ImUserUtil();
        Long categoryId = newsSummaryBean.getCategoryId();
        if (categoryId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误！");
            return listRestFulModel;
        }

        NewsCategory newsCategory = newsCategoryService.find(categoryId);
        if (newsCategory == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("未知新闻目录不存在！"));
            return listRestFulModel;
        }

        Pageable pageable;
        if (newsSummaryBean.getPage() == null)
        {
            pageable = new Pageable();
        }
        else
        {
            pageable = new Pageable(newsSummaryBean.getPage().getPageNumber(), newsSummaryBean.getPage().getPageSize());

        }
        // 状态（0未屏蔽，1屏蔽）
        Integer status = 0;

        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        pageable.setOrders(orders);
        Page<com.sammyun.entity.news.News> newss = newsService.findBySchool(newsCategory, status, pageable);
        if (newss == null || newss.getContent().size() == 0)
        {

            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("当前目录没有新闻简介！"));
            return listRestFulModel;
        }
        for (com.sammyun.entity.news.News news : newss.getContent())
        {
            NewsSummaryBlock newsSummaryBlock = new NewsSummaryBlock();
            newsSummaryBlock.setId(news.getId());
            newsSummaryBlock.setSmallIconfile(imUserUtil.getDefaultImageUrl(news.getSmallIconfile()));
            newsSummaryBlock.setSummary(news.getSummary());
            newsSummaryBlock.setTitle(news.getTitle());
            rows.add(newsSummaryBlock);
        }
        PageModel page = new PageModel();
        page.setPageNumber(newss.getPageNumber());
        page.setTotalPages(newss.getTotalPages());
        listRestFulModel.setPage(page);
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("获取新闻摘要成功！"));
        return listRestFulModel;
    }

    /**
     * 查询新闻具体内容
     * 
     * @param dictSchoolId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/content/{newsId}")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel content(@PathParam("newsId") Long newsId)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        if (newsId == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }

        com.sammyun.entity.news.News news = newsService.find(newsId);
        if (news == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("未知新闻不存在！"));
            return mobileRestFulModel;
        }
        if (news.getViewCount() == null)
        {
            news.setViewCount(new BigDecimal(1));
        }
        else
        {
            BigDecimal count = news.getViewCount();
            count = count.add(new BigDecimal(1));
            news.setViewCount(count);
        }
        newsService.update(news);

        NewsContentBlock rows = new NewsContentBlock();
        rows.setTitle(news.getTitle());
        rows.setAuthor(news.getAuthor());
        rows.setContent(news.getContent());
        rows.setDetailUrl(news.getDetailUrl());
        // rows.setSmallIconfile(news.getSmallIconfile());
        rows.setSource(news.getSource());
        rows.setSummary(news.getSummary());
        if (news.getTimePublish() != null)
        {
            rows.setTimePublish(DateUtil.date2String(news.getTimePublish(), 1));
        }
        int result = news.getViewCount().compareTo(new BigDecimal(9999));
        if (result <= 0)
        {
            rows.setViewCount(news.getViewCount().toString());
        }
        else if (result > 0)
        {
            BigDecimal count = news.getViewCount().divide((new BigDecimal(10000)));
            count = count.setScale(0, BigDecimal.ROUND_DOWN);
            rows.setViewCount(SpringUtils.getMessage("News.count", count));

        }
        mobileRestFulModel.setRows(rows);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage(SpringUtils.getMessage("获取新闻内容成功！"));
        return mobileRestFulModel;
    }
}
