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
import com.sammyun.controller.api.bean.TeacherAskLeaveAddBean;
import com.sammyun.controller.api.bean.TeacherAskLeaveApprovalBean;
import com.sammyun.controller.api.bean.TeacherAskLeaveListBean;
import com.sammyun.controller.api.block.TeacherAskLeaveDetailBlock;
import com.sammyun.controller.api.block.TeacherAskLeaveListBlock;
import com.sammyun.controller.api.block.TeacherAskLeaveTypeBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.TeacherAskLeave;
import com.sammyun.entity.attendance.TeacherAskLeave.AskLeaveType;
import com.sammyun.service.MemberService;
import com.sammyun.service.attendance.TeacherAskLeaveService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.ImUserUtil;
import com.sammyun.util.SpringUtils;

/**
 * api - 老师请假
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  xutianlong
 * @version  [版本号, Aug 12, 2015]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("teacherAskLeave")
@Path("/teacherAskLeave")
public class TeacherAskLeaveApi
{
    @Resource(name = "teacherAskLeaveServiceImpl")
    private TeacherAskLeaveService teacherAskLeaveService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;
    
    ImUserUtil imUserUtil = new ImUserUtil();

    /**
     * 获取请假类型
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/type")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel type()
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<TeacherAskLeaveTypeBlock> rows = new LinkedList<TeacherAskLeaveTypeBlock>();
        for (AskLeaveType askLeaveType : AskLeaveType.values())
        {
            TeacherAskLeaveTypeBlock teacherAskLeaveTypeBlock = new TeacherAskLeaveTypeBlock();
            teacherAskLeaveTypeBlock.setAskLeaveType(askLeaveType);
            teacherAskLeaveTypeBlock.setTypeName(SpringUtils.getMessage("Api.teacherAskLeaveType." + askLeaveType));
            rows.add(teacherAskLeaveTypeBlock);
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("请求成功");
        return listRestFulModel;
    }

    /**
     * 我的请假列表,审核列表
     */

    @POST
    @Path("/v1/list")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel list(TeacherAskLeaveListBean teacherAskLeaveListBean)
    {
        Pageable pageable = new Pageable();
        List<Order> orders = new LinkedList<Order>();
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<TeacherAskLeaveListBlock> rows = new LinkedList<TeacherAskLeaveListBlock>();
        Page<com.sammyun.entity.attendance.TeacherAskLeave> teacherAskLeaves = new Page<com.sammyun.entity.attendance.TeacherAskLeave>();

        Long memberId = teacherAskLeaveListBean.getMemberId();
        Integer flag = teacherAskLeaveListBean.getFlag();

        if (memberId == null || flag == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误！");
            return listRestFulModel;
        }

        Member member = memberService.find(memberId);
        if (member == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("未知人员不存在！");
            return listRestFulModel;
        }

        if (teacherAskLeaveListBean.getPage() == null)
        {
            pageable = new Pageable();
        }
        else
        {
            pageable = new Pageable(teacherAskLeaveListBean.getPage().getPageNumber(),
                    teacherAskLeaveListBean.getPage().getPageSize());

        }
        orders.add(Order.desc("createDate"));
        pageable.setOrders(orders);

        if (flag == 0)
        {
            teacherAskLeaves = teacherAskLeaveService.findByMember(member, null, pageable);
        }
        if (flag == 1)
        {
            teacherAskLeaves = teacherAskLeaveService.findByMember(null, member, pageable);
        }

        if (teacherAskLeaves.getContent() == null || teacherAskLeaves.getContent().size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("暂无相关数据！");
            return listRestFulModel;
        }

        for (TeacherAskLeave teacherAskLeave : teacherAskLeaves.getContent())
        {

            TeacherAskLeaveListBlock teacherAskLeaveListBlock = new TeacherAskLeaveListBlock();
            teacherAskLeaveListBlock.setApprovalMemberName(teacherAskLeave.getApprovalMember().getRealName());
            teacherAskLeaveListBlock.setId(teacherAskLeave.getId());
            teacherAskLeaveListBlock.setIsAgree(teacherAskLeave.getIsAgree());
            teacherAskLeaveListBlock.setCreateDate(DateUtil.date2String(teacherAskLeave.getCreateDate(), 1));
            teacherAskLeaveListBlock.setLeaveMemberName(teacherAskLeave.getLeaveMember().getRealName());
            teacherAskLeaveListBlock.setLeaveMemberIconPhoto(imUserUtil.getDefaultImageUrl(teacherAskLeave.getLeaveMember().getIconPhoto()));
            rows.add(teacherAskLeaveListBlock);
        }

        PageModel page = new PageModel();
        page.setPageNumber(teacherAskLeaves.getPageNumber());
        page.setTotalPages(teacherAskLeaves.getTotalPages());

        listRestFulModel.setRows(rows);
        listRestFulModel.setPage(page);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("请求成功");
        return listRestFulModel;
    }

    /**
     * 请假详情 <功能详细描述>
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/detail/{id}")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel detail(@PathParam("id") Long id)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();

        if (id == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }

        com.sammyun.entity.attendance.TeacherAskLeave teacherAskLeave = teacherAskLeaveService.find(id);

        if (teacherAskLeave == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("暂无相关数据！");
            return mobileRestFulModel;
        }

        TeacherAskLeaveDetailBlock row = new TeacherAskLeaveDetailBlock();
        row.setApprovalMemberName(teacherAskLeave.getApprovalMember().getRealName());
        row.setApprovalOpinion(teacherAskLeave.getApprovalOpinion());
        row.setAskLeaveType(teacherAskLeave.getAskLeaveType());
        row.setDescription(teacherAskLeave.getDescription());
        row.setId(teacherAskLeave.getId());
        row.setIsAgree(teacherAskLeave.getIsAgree());
        row.setLeaveDay(teacherAskLeave.getLeaveDay());
        row.setLeaveEndDate(DateUtil.date2String(teacherAskLeave.getLeaveEndDate(), 10));
        row.setLeaveMemberName(teacherAskLeave.getLeaveMember().getRealName());
        row.setLeaveStartDate(DateUtil.date2String(teacherAskLeave.getLeaveStartDate(), 10));
        row.setAskLeaveTypeName(SpringUtils.getMessage("Api.teacherAskLeaveType." + teacherAskLeave.getAskLeaveType()));
        row.setApprovalMemberIconPhoto(imUserUtil.getDefaultImageUrl(teacherAskLeave.getApprovalMember().getIconPhoto()));
        row.setCreateDate(DateUtil.date2String(teacherAskLeave.getCreateDate(), 1));
        if (teacherAskLeave.getApprovalDate() != null)
        {
            row.setApprovalDate(DateUtil.date2String(teacherAskLeave.getApprovalDate(), 1));
        }
        
        mobileRestFulModel.setRows(row);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("请求成功");
        return mobileRestFulModel;
    }

    /**
     * 教师请假 <功能详细描述>
     * 
     * @param teacherAskLeaveAddBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/add")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileRestFulModel add(TeacherAskLeaveAddBean teacherAskLeaveAddBean)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        com.sammyun.entity.attendance.TeacherAskLeave teacherAskLeave = new com.sammyun.entity.attendance.TeacherAskLeave();

        String leaveStartDate = teacherAskLeaveAddBean.getLeaveStartDate();
        String leaveEndDate = teacherAskLeaveAddBean.getLeaveEndDate();
        Long leaveMemberId = teacherAskLeaveAddBean.getLeaveMemberId();
        Double leaveDay = teacherAskLeaveAddBean.getLeaveDay();
        String description = teacherAskLeaveAddBean.getDescription();
        AskLeaveType askLeaveType = teacherAskLeaveAddBean.getAskLeaveType();
        Long approvalMemberId = teacherAskLeaveAddBean.getApprovalMemberId();

        if (askLeaveType == null || leaveStartDate == null || leaveEndDate == null || leaveMemberId == null
                || leaveDay == null || approvalMemberId == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }

        Member leaveMember = memberService.find(leaveMemberId);
        Member approvalMember = memberService.find(approvalMemberId);

        if (leaveMember == null || approvalMember == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("未知人员不存在！");
            return mobileRestFulModel;
        }
        teacherAskLeave.setApprovalMember(approvalMember);
        teacherAskLeave.setAskLeaveType(askLeaveType);
        teacherAskLeave.setDescription(description);
        teacherAskLeave.setLeaveDay(leaveDay);
        teacherAskLeave.setLeaveEndDate(DateUtil.string2Date(leaveEndDate));
        teacherAskLeave.setLeaveMember(leaveMember);
        teacherAskLeave.setLeaveStartDate(DateUtil.string2Date(leaveStartDate));
        teacherAskLeaveService.save(teacherAskLeave);

        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("申请成功！");
        return mobileRestFulModel;
    }

    /**
     * 审批
     */
    @POST
    @Path("/v1/approval")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileRestFulModel approval(TeacherAskLeaveApprovalBean teacherAskLeaveapprovalBean)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();

        String approvalOpinion = teacherAskLeaveapprovalBean.getApprovalOpinion();
        Boolean isAgree = teacherAskLeaveapprovalBean.getIsAgree();
        Long id = teacherAskLeaveapprovalBean.getId();

        if (isAgree == null || id == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }

        com.sammyun.entity.attendance.TeacherAskLeave teacherAskLeave = teacherAskLeaveService.find(id);

        if (teacherAskLeave == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("未知请假记录不存在！");
            return mobileRestFulModel;
        }

        teacherAskLeave.setIsAgree(isAgree);
        teacherAskLeave.setApprovalDate(DateUtil.getDate());
        teacherAskLeave.setApprovalOpinion(approvalOpinion);
        teacherAskLeaveService.update(teacherAskLeave);

        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("审核成功！");
        return mobileRestFulModel;
    }

}
