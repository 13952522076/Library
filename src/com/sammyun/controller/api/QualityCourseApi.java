package com.sammyun.controller.api;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import com.sammyun.Order;
import com.sammyun.controller.api.block.QualityCourseImageAttachBlock;
import com.sammyun.controller.api.block.QualityCourseListBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.entity.course.QualityCourseImageAttach;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.MemberService;
import com.sammyun.service.campusviewImg.CampusviewImgService;
import com.sammyun.service.course.QualityCourseService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.SpringUtils;

/**
 * Api - 精品课程
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("qualityCourse")
@Path("/qualityCourse")
public class QualityCourseApi
{
    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "campusviewImgServiceImpl")
    private CampusviewImgService campusviewImgService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "qualityCourseServiceImpl")
    private QualityCourseService qualityCourseService;

    /**
     * 查看精品课程 <功能详细描述>
     * 
     * @param campusviewImgAlbumsBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/list/{dictSchoolId}")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel list(@PathParam("dictSchoolId") Long dictSchoolId)
    {

        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<QualityCourseListBlock> rows = new LinkedList<QualityCourseListBlock>();
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
        // 状态（0未屏蔽，1屏蔽）
        Long status = 0L;

        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));

        List<com.sammyun.entity.course.QualityCourse> qualityCourses = qualityCourseService.findBySchool(dictSchool,
                status, orders);
        if (qualityCourses == null || qualityCourses.size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关精品课程！"));
            return listRestFulModel;
        }
        for (com.sammyun.entity.course.QualityCourse qualityCourse : qualityCourses)
        {
            QualityCourseListBlock qualityCourseListBlock = new QualityCourseListBlock();
            qualityCourseListBlock.setId(qualityCourse.getId());
            qualityCourseListBlock.setDescription(qualityCourse.getDescription());
            List<QualityCourseImageAttachBlock> qualityCourseImageAttachs = new LinkedList<QualityCourseImageAttachBlock>();
            for (QualityCourseImageAttach qualityCourseImageAttach : qualityCourse.getQualityCourseImageAttachs())
            {
                QualityCourseImageAttachBlock qualityCourseImageAttachBlock = new QualityCourseImageAttachBlock();
                qualityCourseImageAttachBlock.setId(qualityCourseImageAttach.getId());
                qualityCourseImageAttachBlock.setImageAttach(qualityCourseImageAttach.getImageAttach());
                qualityCourseImageAttachBlock.setDescription(qualityCourseImageAttach.getDescription());
                qualityCourseImageAttachs.add(qualityCourseImageAttachBlock);
            }
            qualityCourseListBlock.setQualityCourseImageAttachs(qualityCourseImageAttachs);
            rows.add(qualityCourseListBlock);
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("获取精品课程信息成功！"));
        return listRestFulModel;
    }

}
