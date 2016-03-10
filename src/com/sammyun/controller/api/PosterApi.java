package com.sammyun.controller.api;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Component;

import com.sammyun.controller.api.block.PosterListBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.poster.Poster.PosterPosition;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.service.poster.PosterService;
import com.sammyun.util.ImUserUtil;
import com.sammyun.util.SpringUtils;

/**
 * Api - 海报
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("poster")
@Path("/poster")
public class PosterApi
{
    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "posterServiceImpl")
    private PosterService posterService;

    /**
     * 根据学校和位置获取相关海报 <功能详细描述>
     * 
     * @param dictSchoolId
     * @param memberId
     * @param classStatus
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/list/{dictSchoolId}/{posterPosition}")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel list(@PathParam("dictSchoolId") Long dictSchoolId,
            @PathParam("posterPosition") PosterPosition posterPosition)
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<PosterListBlock> rows = new LinkedList<PosterListBlock>();
        ImUserUtil imUserUtil = new ImUserUtil();
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
        if (posterPosition == null)
        {
            posterPosition = PosterPosition.HOMEPAGE;
        }

        Boolean status = true;

        List<com.sammyun.entity.poster.Poster> posters = posterService.findBySchool(dictSchool, posterPosition, status);
        if (posters == null || posters.size() == 0)
        {

            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关海报信息！"));
            return listRestFulModel;
        }
        for (com.sammyun.entity.poster.Poster poster : posters)
        {

            PosterListBlock posterListBlock = new PosterListBlock();
            posterListBlock.setId(poster.getId());
            posterListBlock.setContent(poster.getContent());
            posterListBlock.setPosterCover(imUserUtil.getDefaultImageUrl(poster.getPosterCover()));
            posterListBlock.setPosterPosition(poster.getPosterPosition());
            posterListBlock.setPosterTitle(poster.getPosterTitle());
            posterListBlock.setPosterType(poster.getPosterType());
            posterListBlock.setUrl(poster.getUrl());
            posterListBlock.setViewCount(poster.getViewCount());
            rows.add(posterListBlock);
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("获取海报信息成功"));
        return listRestFulModel;

    }

    /**
     * 根据学校和位置获取相关海报 <功能详细描述>
     * 
     * @param dictSchoolId
     * @param memberId
     * @param classStatus
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v2/list/{dictSchoolId}/{posterPosition}")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel list_v2(@PathParam("dictSchoolId") Long dictSchoolId,
            @PathParam("posterPosition") PosterPosition posterPosition, @QueryParam("updateDate") Long updateDate)
    {

        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<PosterListBlock> rows = new LinkedList<PosterListBlock>();
        ImUserUtil imUserUtil = new ImUserUtil();
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
        if (posterPosition == null)
        {
            posterPosition = PosterPosition.HOMEPAGE;
        }

        Boolean status = true;

        // List<com.sammyun.entity.poster.Poster> posters =
        // posterService.findBySchool(dictSchool, posterPosition, status);

        List<com.sammyun.entity.poster.Poster> posters = posterService.findBySchool(dictSchool, posterPosition, status,
                new Date(updateDate));

        if (posters == null || posters.size() == 0)
        {

            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关海报信息！"));
            return listRestFulModel;
        }
        for (com.sammyun.entity.poster.Poster poster : posters)
        {

            PosterListBlock posterListBlock = new PosterListBlock();
            posterListBlock.setId(poster.getId());
            posterListBlock.setContent(poster.getContent());
            posterListBlock.setPosterCover(imUserUtil.getDefaultImageUrl(poster.getPosterCover()));
            posterListBlock.setPosterPosition(poster.getPosterPosition());
            posterListBlock.setPosterTitle(poster.getPosterTitle());
            posterListBlock.setPosterType(poster.getPosterType());
            posterListBlock.setUrl(poster.getUrl());
            posterListBlock.setViewCount(poster.getViewCount());
            rows.add(posterListBlock);
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("获取海报信息成功"));
        return listRestFulModel;

    }

    /**
     * 根据海报id增加查看次数 <功能详细描述>
     * 
     * @param posterId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/view/{posterId}")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel view(@PathParam("posterId") Long posterId)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        if (posterId == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }

        com.sammyun.entity.poster.Poster poster = posterService.find(posterId);
        if (poster == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关海报信息！"));
            return mobileRestFulModel;
        }
        Long viewCount = 0L;
        if (poster.getViewCount() == null)
        {
            viewCount = 1L;

        }
        else
        {
            viewCount = poster.getViewCount() + 1;
        }
        poster.setViewCount(viewCount);
        posterService.update(poster);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage(SpringUtils.getMessage("查看成功！"));
        return mobileRestFulModel;

    }
}
