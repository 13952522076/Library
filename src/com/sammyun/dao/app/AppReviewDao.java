package com.sammyun.dao.app;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.BaseDao;
import com.sammyun.entity.app.AppReview;

/**
 * AppReview Dao - 应用评论
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface AppReviewDao extends BaseDao<AppReview, Long>{
	
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
