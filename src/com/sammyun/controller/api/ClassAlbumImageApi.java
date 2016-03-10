package com.sammyun.controller.api;

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
import com.sammyun.controller.api.bean.ClassAlbumImageListBean;
import com.sammyun.controller.api.block.ClassAlbumImageContentBlock;
import com.sammyun.controller.api.block.ClassAlbumImageListBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.classalbum.ClassAlbumImageAttach;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.MemberService;
import com.sammyun.service.campusviewImg.CampusviewImgService;
import com.sammyun.service.classalbum.ClassAlbumImageService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.SpringUtils;

/**
 * api - 班级相册
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("classAlbumImage")
@Path("/classAlbumImage")
public class ClassAlbumImageApi
{
    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    @Resource(name = "campusviewImgServiceImpl")
    private CampusviewImgService campusviewImgService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "classAlbumImageServiceImpl")
    private ClassAlbumImageService classAlbumImageService;

    /**
     * 查询班级相册列表
     * 
     * @param classAlbumImageListBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/list")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel list(ClassAlbumImageListBean classAlbumImageListBean)
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<ClassAlbumImageListBlock> rows = new LinkedList<ClassAlbumImageListBlock>();
        Long dictSchoolId = classAlbumImageListBean.getDictSchoolId();
        Long dictClassId = classAlbumImageListBean.getDictClassId();
        if (dictSchoolId == null || dictClassId == null)
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

        DictClass dictClass = dictClassService.find(dictClassId);
        if (dictClass == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("未知班级不存在！"));
            return listRestFulModel;
        }

        Pageable pageable;
        if (classAlbumImageListBean.getPage() == null)
        {
            pageable = new Pageable();
        }
        else
        {
            pageable = new Pageable(classAlbumImageListBean.getPage().getPageNumber(),
                    classAlbumImageListBean.getPage().getPageSize());

        }
        // 状态（0未屏蔽，1屏蔽）
        Long status = 0L;

        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        pageable.setOrders(orders);

        Page<com.sammyun.entity.classalbum.ClassAlbumImage> classAlbumImages = classAlbumImageService.findBySchool(
                dictSchool, dictClass, status, pageable);
        if (classAlbumImages == null || classAlbumImages.getContent().size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关班级相册！"));
            return listRestFulModel;
        }
        for (com.sammyun.entity.classalbum.ClassAlbumImage classAlbumImage : classAlbumImages.getContent())
        {
            ClassAlbumImageListBlock classAlbumImageListBlock = new ClassAlbumImageListBlock();
            classAlbumImageListBlock.setId(classAlbumImage.getId());
            classAlbumImageListBlock.setCoverImage(classAlbumImage.getCoverImage());
            classAlbumImageListBlock.setCreator(classAlbumImage.getCreator());
            classAlbumImageListBlock.setDescription(classAlbumImage.getDescription());
            if (classAlbumImage.getCreateDate() != null)
            {
                classAlbumImageListBlock.setCreateDate(DateUtil.date2String(classAlbumImage.getCreateDate(), 10));
            }
            rows.add(classAlbumImageListBlock);
        }
        PageModel page = new PageModel();
        page.setPageNumber(classAlbumImages.getPageNumber());
        page.setTotalPages(classAlbumImages.getTotalPages());
        listRestFulModel.setPage(page);
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("获取班级相册信息成功！"));
        return listRestFulModel;
    }

    /**
     * 查看班级相册
     * 
     * @param classAlbumImageListBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/content/{classAlbumImageId}")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel content(@PathParam("classAlbumImageId") Long classAlbumImageId)
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<ClassAlbumImageContentBlock> rows = new LinkedList<ClassAlbumImageContentBlock>();
        // || classAlbumImageId == null
        if (classAlbumImageId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误！");
            return listRestFulModel;
        }

        com.sammyun.entity.classalbum.ClassAlbumImage classAlbumImage = classAlbumImageService.find(classAlbumImageId);
        if (classAlbumImage == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("未知相册不存在！"));
            return listRestFulModel;
        }

        if (classAlbumImage.getClassAlbumImageAttachs() == null
                || classAlbumImage.getClassAlbumImageAttachs().size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("没有相关照片！"));
            return listRestFulModel;
        }
        for (ClassAlbumImageAttach classAlbumImageAttach : classAlbumImage.getClassAlbumImageAttachs())
        {
            ClassAlbumImageContentBlock classAlbumImageContentBlock = new ClassAlbumImageContentBlock();
            classAlbumImageContentBlock.setId(classAlbumImageAttach.getId());
            classAlbumImageContentBlock.setImageAttach(classAlbumImageAttach.getImageAttach());
            rows.add(classAlbumImageContentBlock);
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("获取班级相册照片成功！"));
        return listRestFulModel;
    }
}
