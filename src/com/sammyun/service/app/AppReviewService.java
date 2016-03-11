package com.sammyun.service.app;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.app.AppReview;
import com.sammyun.service.BaseService;

/**
 * AppReview Service - 应用评论
 * 


 */
public interface AppReviewService extends BaseService<AppReview, Long>{
	
	/**
     *  根据条件查找应用评论
     * @param isShow
     * 		是否展示
     * @param pageable
     * 		分页信息
     * @return
     */
    Page<AppReview> findPage(Boolean isShow, Pageable pageable);
	
}
