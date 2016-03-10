package com.sammyun.dao.app;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.BaseDao;
import com.sammyun.entity.app.AppPoster;
import com.sammyun.entity.app.AppPoster.OperatingSystem;

/**
 * Poster * Dao - 应用超市海报
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface AppPosterDao extends BaseDao<AppPoster, Long> {
	
	/**
	 *  根据条件查询应用超市海报清单
	 * @param pageable
	 * 	分页信息
	 * @param isOnline
	 * 	是否上架
	 * @param operatingSystem
	 * 	应用操作系统
	 * @return
	 */
	public Page<AppPoster> findPage(Pageable pageable, Boolean isOnline, OperatingSystem operatingSystem);

}

