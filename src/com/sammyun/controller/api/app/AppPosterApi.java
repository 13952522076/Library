package com.sammyun.controller.api.app;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Component;

import com.sammyun.Order;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.controller.api.bean.app.AppPosterBean;
import com.sammyun.controller.api.block.app.AppPosterBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.entity.app.AppPoster;
import com.sammyun.service.app.AppPosterService;
import com.sammyun.util.SpringUtils;

/**
 * 
 * api - 应用海报数据
 * 
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("appPoster")
@Path("/appPoster")
public class AppPosterApi {
	@Resource(name = "appPosterServiceImpl")
	private AppPosterService appPosterService;

	/**
	 * 获取应用海报列表 <功能详细描述>
	 * 
	 * @param appPosterBean
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@POST
	@Path("/v1/appPoster/list")
	@Produces("application/json;charset=UTF-8")
	public ListRestFulModel appPosterList(AppPosterBean appPosterBean) {
		ListRestFulModel listRestFulModel = new ListRestFulModel();
		List<AppPosterBlock> rows = new LinkedList<AppPosterBlock>();

		if (appPosterBean == null) {
			listRestFulModel.setResultCode(1);
			listRestFulModel.setResultMessage("参数错误！");
			return listRestFulModel;
		}

		if (appPosterBean.getOperatingSystem() == null) {
			listRestFulModel.setResultCode(1);
			listRestFulModel.setResultMessage("应用海报的操作系统不能为空");
			return listRestFulModel;
		}

		Pageable pageable;
		if (appPosterBean.getPage() == null) {
			pageable = new Pageable();
		} else {
			pageable = new Pageable(appPosterBean.getPage().getPageNumber(),
					appPosterBean.getPage().getPageSize());
		}

		List<Order> orders = new LinkedList<Order>();
		orders.add(Order.desc("createDate"));
		pageable.setOrders(orders);

		Page<AppPoster> appPosters = appPosterService.findPage(pageable, true,
				appPosterBean.getOperatingSystem());
		if (appPosters == null || appPosters.getContent().size() == 0) {
			listRestFulModel.setResultCode(1);
			listRestFulModel.setResultMessage(SpringUtils
					.getMessage("没有相关应用海报信息！"));
			return listRestFulModel;
		}

		for (AppPoster appPoster : appPosters.getContent()) {
			AppPosterBlock appPosterBlock = new AppPosterBlock();
			appPosterBlock.setId(appPoster.getId());
			appPosterBlock.setPosterImg(appPoster.getPosterImg());
			appPosterBlock.setPosterName(appPoster.getPosterName());
			rows.add(appPosterBlock);
		}
		listRestFulModel.setRows(rows);
		listRestFulModel.setResultCode(0);
		listRestFulModel.setResultMessage(SpringUtils.getMessage("获取应用海报信息成功"));
		return listRestFulModel;
	}
}
