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
import com.sammyun.controller.api.bean.app.AppCategoryBean;
import com.sammyun.controller.api.block.app.AppCategoryBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.app.AppCategory;
import com.sammyun.service.app.AppCategoryService;
import com.sammyun.util.SpringUtils;

/**
 * 
 * api - 应用分类数据
 * 
 * @author  xutianlong
 * @version  [版本号, 2015-8-18]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("appCategory")
@Path("/appCategory")
public class AppCategoryApi
{
	@Resource(name = "appCategoryServiceImpl")
    private AppCategoryService appCategoryService;
	
	 /**
     * 查询当前应用分类清单
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/appCategory/list")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel appCategoryList(AppCategoryBean appCategoryBean)
    {

        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<AppCategoryBlock> rows = new LinkedList<AppCategoryBlock>();

		Pageable pageable;
		if (appCategoryBean.getPage() == null) {
			pageable = new Pageable();
		} else {
			pageable = new Pageable(appCategoryBean.getPage().getPageNumber(),
					appCategoryBean.getPage().getPageSize());
		}

        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        pageable.setOrders(orders);

        Page<AppCategory> appCategorys = appCategoryService.findPage(true, pageable);
        if (appCategorys == null || appCategorys.getContent().size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("Api.appCategory.noCategory"));
            return listRestFulModel;
        }
        for (AppCategory appCategory : appCategorys.getContent())
        {
        	AppCategoryBlock appCategoryBlock = new AppCategoryBlock();
        	appCategoryBlock.setId(appCategory.getId());
        	appCategoryBlock.setName(appCategory.getName());
        	appCategoryBlock.setDescription(appCategory.getDescription());
        	appCategoryBlock.setAppCategoryLogoUrl(appCategory.getAppCategoryLogoUrl());
            rows.add(appCategoryBlock);
        }
        PageModel page = new PageModel();
        page.setPageNumber(appCategorys.getPageNumber());
        page.setTotalPages(appCategorys.getTotalPages());
        listRestFulModel.setPage(page);
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("Api.appCategory.success"));
        return listRestFulModel;
    }

}
