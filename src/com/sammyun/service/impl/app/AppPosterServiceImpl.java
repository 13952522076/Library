package com.sammyun.service.impl.app;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.app.AppPosterDao;
import com.sammyun.entity.app.AppPoster;
import com.sammyun.entity.app.AppPoster.OperatingSystem;
import com.sammyun.service.app.AppPosterService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * Poster * ServiceImpl - 应用超市海报
 * 


 */
@Service("appPosterServiceImpl")
public class AppPosterServiceImpl extends BaseServiceImpl<AppPoster, Long>
		implements AppPosterService {

	@Resource(name = "appPosterDaoImpl")
	private AppPosterDao appPosterDao;

	@Resource(name = "appPosterDaoImpl")
	public void setBaseDao(AppPosterDao appPosterDao) {
		super.setBaseDao(appPosterDao);
	}

	@Override
	public Page<AppPoster> findPage(Pageable pageable, Boolean isOnline,
			OperatingSystem operatingSystem) {
		return appPosterDao.findPage(pageable, isOnline, operatingSystem);
	}

}
