package com.sammyun.controller.api.app;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.sammyun.controller.api.block.app.AppClientVersionBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.entity.app.AppClientVersion;
import com.sammyun.entity.app.AppClientVersion.OperatingSystem;
import com.sammyun.service.app.AppClientVersionService;
import com.sammyun.util.SpringUtils;

/**
 * 
 * api - 终端版本数据
 * 
 * @author  xutianlong
 * @version  [版本号, 2015-8-18]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Component("appClientVersion")
@Path("/appClientVersion")
public class AppClientVersionApi
{
	
	@Resource(name = "appClientVersionServiceImpl")
	private AppClientVersionService appClientVersionService;
	
	/**
     * 查询当前终端版本
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/appClientVersion/list/{operatingSystem}")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel appClientVersionList(@PathParam("operatingSystem") OperatingSystem operatingSystem)
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<AppClientVersionBlock> rows = new LinkedList<AppClientVersionBlock>();

		if (operatingSystem == null) {
			listRestFulModel.setResultCode(1);
			listRestFulModel.setResultMessage(SpringUtils
					.getMessage("Api.appClientVersion.operatingSystemNotNull"));
			return listRestFulModel;
		}
		
		List<AppClientVersion> appClientVersions = appClientVersionService.findByOperatingSystem(operatingSystem);
		
		if (appClientVersions == null || appClientVersions.size() == 0) {
			listRestFulModel.setResultCode(1);
			listRestFulModel.setResultMessage(SpringUtils
					.getMessage("Api.appClientVersion.noAppClientVersion"));
			return listRestFulModel;
		}
		
		AppClientVersion appClientVersion = appClientVersions.get(0);//取最新的终端版本
		AppClientVersionBlock appClientVersionBlock = new AppClientVersionBlock();
		appClientVersionBlock.setAppId(appClientVersion.getAppId());
		appClientVersionBlock.setVersionName(appClientVersion.getVersionName());
		appClientVersionBlock.setDescription(appClientVersion.getDescription());
		appClientVersionBlock.setUrlDownload(appClientVersion.getUrlDownload());
		appClientVersionBlock.setFileSize(appClientVersion.getFileSize());
		appClientVersionBlock.setVersionNumber(appClientVersion.getVersionNumber());
		appClientVersionBlock.setFlagPublish(appClientVersion.getFlagPublish());
		appClientVersionBlock.setOperatingSystem(appClientVersion.getOperatingSystem());
		appClientVersionBlock.setTypeOfUpgrade(appClientVersion.getTypeOfUpgrade());
		rows.add(appClientVersionBlock);
		
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("Api.appClientVersion.success"));
        return listRestFulModel;
    }
	

}
