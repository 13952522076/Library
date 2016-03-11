package com.sammyun.service.app;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.app.AppPoster;
import com.sammyun.entity.app.AppPoster.OperatingSystem;
import com.sammyun.service.BaseService;

/**
 * Poster * Service - 应用超市海报
 * 


 */
public interface AppPosterService extends BaseService<AppPoster, Long> {
	
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

