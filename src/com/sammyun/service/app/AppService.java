package com.sammyun.service.app;

import java.util.List;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.app.App;
import com.sammyun.entity.app.App.OperatingSystem;
import com.sammyun.entity.app.App.RunType;
import com.sammyun.entity.app.AppCategory;
import com.sammyun.entity.app.AppRole;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.BaseService;

import freemarker.cache.StringTemplateLoader;

/**
 * Service - 应用管理
 * 


 */
public interface AppService extends BaseService<App, Long>
{
    /**
     * 根据应用名称搜索App
     */
    public List<App> findByName(String name);

    /**
     * 创建应用
     * 
     * @param appCategory 应用分类
     * @param appData 创建应用数据
     * @return
     */
    public App create(AppCategory appCategory, App appData);

    /**
     * 根据app系列父级app查找整个系列的apps
     * 
     * @param parentId
     * @return
     */
    public List<App> findSeriesApps(Long parentId);

    /**
     * 根据app系列父级app查找整个系列的apps(分页)
     * 
     * @param parentId
     * @param pageable
     * @return
     */
    public Page<App> findPage(Long parentId, Pageable pageable);

	/**
	 * 根据条件查询出App分页信息
	 * 
	 * @param appCategory
	 * @param operatingSystem
	 * @param appRoles
	 * @param runType
	 * @param dictSchools
	 * @param pageable
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Page<App> findPage(AppCategory appCategory, Pageable pageable,
			Boolean isOnline, OperatingSystem operatingSystem,
			List<AppRole> appRoles, RunType runType, List<DictSchool> dictSchools);

	/**
	 * 根据搜索词查询出APP分页信息
	 * 
	 * @param searchKey
	 * @param pageable
	 * @param operatingSystem
	 * @param appRoles
	 * @param dictSchools
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Page<App> findBySearchKey(String searchKey, Pageable pageable,
			Boolean isOnline, OperatingSystem operatingSystem,
			List<AppRole> appRole, List<DictSchool> dictSchoolss);

    /**
     * 验证appId唯一
     * 
     * @param appId
     * @return
     */
    public Boolean checkAppIdUnique(String appId);
    
    /**
	  *   判断应用标识是否唯一
	  * @param previousAppCode
	  * 	原应用标识
	  * @param appCode
	  * 	当前应用标识
	  * @return
	  */
    public boolean appCodeUnique(String previousAppCode, String appCode);
    
    /**
     * 模糊查询App
     * @param searchName
     * @return
     */
    public List<App> findBySearchName(String searchName);

}
