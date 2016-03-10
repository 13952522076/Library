package com.sammyun.controller.api.app;

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
import com.sammyun.controller.api.bean.app.AppReviewBean;
import com.sammyun.controller.api.block.app.AppReviewBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.Member;
import com.sammyun.entity.app.App;
import com.sammyun.entity.app.AppReview;
import com.sammyun.service.MemberService;
import com.sammyun.service.app.AppReviewService;
import com.sammyun.service.app.AppService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.SpringUtils;

/**
 * 
 * api - 应用评论数据
 * 
 * @author xutianlong
 * @version [版本号, 2015-8-18]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("appReview")
@Path("/appReview")
public class AppReviewApi {

	@Resource(name = "appReviewServiceImpl")
	private AppReviewService appReviewService;

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;

	@Resource(name = "appServiceImpl")
	private AppService appService;

	/**
	 * 查询当前应用的应用评论
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@POST
	@Path("/v1/appReview/list")
	@Produces("application/json;charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public ListRestFulModel appReviewList(AppReviewBean appReviewBean) {

		ListRestFulModel listRestFulModel = new ListRestFulModel();
		List<AppReviewBlock> rows = new LinkedList<AppReviewBlock>();

		Pageable pageable;
		if (appReviewBean.getPage() == null) {
			pageable = new Pageable();
		} else {
			pageable = new Pageable(appReviewBean.getPage().getPageNumber(),
					appReviewBean.getPage().getPageSize());
		}

		List<Order> orders = new LinkedList<Order>();
		orders.add(Order.desc("createDate"));
		pageable.setOrders(orders);

		Page<AppReview> appReviews = appReviewService.findPage(true, pageable);
		if (appReviews == null || appReviews.getContent().size() == 0) {
			listRestFulModel.setResultCode(1);
			listRestFulModel.setResultMessage(SpringUtils
					.getMessage("Api.appReview.noAppReview"));
			return listRestFulModel;
		}
		for (AppReview appReview : appReviews.getContent()) {
			AppReviewBlock appReviewBlock = new AppReviewBlock();
			appReviewBlock.setId(appReview.getId());
			appReviewBlock.setNickName(appReview.getNickName());
			appReviewBlock.setContent(appReview.getContent());
			appReviewBlock.setScore(appReview.getScore());
			appReviewBlock.setTitle(appReview.getTitle());
			appReviewBlock.setCreateDate(DateUtil.date2String(
					appReview.getCreateDate(), -10));
			rows.add(appReviewBlock);
		}
		PageModel page = new PageModel();
		page.setPageNumber(appReviews.getPageNumber());
		page.setTotalPages(appReviews.getTotalPages());
		listRestFulModel.setPage(page);
		listRestFulModel.setRows(rows);
		listRestFulModel.setResultCode(0);
		listRestFulModel.setResultMessage(SpringUtils
				.getMessage("Api.appReview.success"));
		return listRestFulModel;
	}

	/**
	 * 新增应用评论
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@POST
	@Path("/v1/appReview/add")
	@Produces("application/json;charset=UTF-8")
	@Consumes(MediaType.APPLICATION_JSON)
	public MobileRestFulModel appReviewAdd(AppReviewBean appReviewBean) {

		MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();

		Integer score = appReviewBean.getScore();
		if (score == null) {
			mobileRestFulModel.setResultCode(1);
			mobileRestFulModel.setResultMessage(SpringUtils
					.getMessage("Api.appReview.scoreNotNull"));
			return mobileRestFulModel;
		}

		String title = appReviewBean.getTitle();
		if (title == null || "".equals(title)) {
			mobileRestFulModel.setResultCode(1);
			mobileRestFulModel.setResultMessage(SpringUtils
					.getMessage("Api.appReview.titleNotNull"));
			return mobileRestFulModel;
		}

		String nickName = appReviewBean.getNickName();
		if (nickName == null || "".equals(nickName)) {
			mobileRestFulModel.setResultCode(1);
			mobileRestFulModel.setResultMessage(SpringUtils
					.getMessage("Api.appReview.nickNameNotNull"));
			return mobileRestFulModel;
		}

		String content = appReviewBean.getContent();
		if (content == null || "".equals(content)) {
			mobileRestFulModel.setResultCode(1);
			mobileRestFulModel.setResultMessage(SpringUtils
					.getMessage("Api.appReview.contentNotNull"));
			return mobileRestFulModel;
		}

		String ip = appReviewBean.getIp();
		if (ip == null || "".equals(ip)) {
			mobileRestFulModel.setResultCode(1);
			mobileRestFulModel.setResultMessage(SpringUtils
					.getMessage("Api.appReview.ipNotNull"));
			return mobileRestFulModel;
		}

		Member member = memberService.find(appReviewBean.getMemberId());
		if (member == null) {
			mobileRestFulModel.setResultCode(1);
			mobileRestFulModel.setResultMessage(SpringUtils
					.getMessage("shop.login.unknownAccount"));
			return mobileRestFulModel;
		}

		App app = appService.find(appReviewBean.getAppId());
		if (app == null) {
			mobileRestFulModel.setResultCode(1);
			mobileRestFulModel.setResultMessage(SpringUtils
					.getMessage("Api.appReview.appNotNull"));
			return mobileRestFulModel;
		}

		AppReview appReview = new AppReview();
		appReview.setScore(score);
		appReview.setTitle(title);
		appReview.setNickName(nickName);
		appReview.setContent(content);
		appReview.setIp(ip);
		appReview.setMember(member);
		appReview.setApp(app);
		appReview.setIsShow(true);
		appReviewService.save(appReview);

		mobileRestFulModel.setResultCode(0);
		mobileRestFulModel.setResultMessage(SpringUtils
				.getMessage("Api.appReview.addSuccess"));
		return mobileRestFulModel;
	}

}
