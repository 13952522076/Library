package com.sammyun.service.impl.app;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.app.AppReviewDao;
import com.sammyun.entity.app.AppReview;
import com.sammyun.service.app.AppReviewService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * AppReview * ServiceImpl - 应用评论
 * 


 */
@Service("appReviewServiceImpl")
public class AppReviewServiceImpl extends BaseServiceImpl<AppReview, Long> implements AppReviewService{

	@Resource(name = "appReviewDaoImpl")
    private AppReviewDao appReviewDao;
	
    @Resource(name = "appReviewDaoImpl")
    public void setBaseDao(AppReviewDao appReviewDao)
    {
        super.setBaseDao(appReviewDao);
    }
	
	@Override
    public Page<AppReview> findPage(Boolean isShow, Pageable pageable){
		return appReviewDao.findPage(isShow, pageable);
	}
	
}
