package com.sammyun.service.impl.app;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.app.AppDao;
import com.sammyun.entity.app.App;
import com.sammyun.entity.app.App.OperatingSystem;
import com.sammyun.entity.app.App.RunType;
import com.sammyun.entity.app.AppCategory;
import com.sammyun.entity.app.AppRole;
import com.sammyun.entity.app.AppScreenshot;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.app.AppService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 应用管理
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("appServiceImpl")
public class AppServiceImpl extends BaseServiceImpl<App, Long> implements AppService
{
    @Resource(name = "appDaoImpl")
    private AppDao appDao;

    @Resource(name = "appDaoImpl")
    public void setBaseDao(AppDao appDao)
    {
        super.setBaseDao(appDao);
    }

    @Override
    public App create(AppCategory appCategory, App appData)
    {

        App app = new App();
        BeanUtils.copyProperties(appData, app);
        app.setAppCategory(appCategory);
        app.setDeveloper("Sencloud");
        app.setIsAuditing(false);
        app.setIsCharge(false);
        app.setIsOnline(false);
        app.setIsPoint(false);
        app.setPoint(0L);
        app.setPrice(new BigDecimal(0));
        app.setPrefix("");
        List<AppScreenshot> appScreenshots = new ArrayList<AppScreenshot>();

        for (AppScreenshot appScreenshot : appData.getAppScreenshots())
        {
            appScreenshot.setApp(app);
            appScreenshots.add(appScreenshot);
        }
        app.setAppScreenshots(appScreenshots);

        appDao.persist(app);

        return app;
    }

    @Override
    public List<App> findByName(String name)
    {
        return appDao.findByName(name);
    }

    @Override
    public List<App> findSeriesApps(Long parentId)
    {
        return appDao.findSeriesApps(parentId);
    }

    @Override
    public Page<App> findPage(Long parentId, Pageable pageable)
    {
        return appDao.findPage(parentId, pageable);
    }

	@Override
	public Page<App> findPage(AppCategory appCategory, Pageable pageable,
			Boolean isOnline, OperatingSystem operatingSystem,
			List<AppRole> appRoles, RunType runType,
			List<DictSchool> dictSchools) {
		return appDao.findPage(appCategory, pageable, isOnline,
				operatingSystem, appRoles, runType, dictSchools);
	}

	@Override
	public Page<App> findBySearchKey(String searchKey, Pageable pageable,
			Boolean isOnline, OperatingSystem operatingSystem,
			List<AppRole> appRoles, List<DictSchool> dictSchools) {
		return appDao.findBySearchKey(searchKey, pageable, isOnline,
				operatingSystem, appRoles, dictSchools);
	}

    @Override
    public Boolean checkAppIdUnique(String appId)
    {
        return appDao.checkAppIdUnique(appId);
    }
    
    @Transactional(readOnly = true)
	public boolean appCodeUnique(String previousAppCode, String appCode) {
		if (StringUtils.equalsIgnoreCase(previousAppCode, appCode)) {
			return true;
		} else {
			if (appDao.appCodeUnique(appCode)) {
				return false;
			} else {
				return true;
			}
		}
	}
    
    @Override
    public List<App> findBySearchName(String searchName){
    	return appDao.findBySearchName(searchName);
    }
}
