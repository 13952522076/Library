package com.sammyun.dao.campusviewImg;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.campusviewImg.CampusviewImg;
import com.sammyun.entity.campusviewImg.CampusviewImgFavorite;

/**
 * CampusviewImgFavorite * Dao - 校园风光点赞记录表
 * 


 */

public interface CampusviewImgFavoriteDao extends BaseDao<CampusviewImgFavorite, Long> {

    /**
     * 根据会员和相册查询点赞记录
     */
    public CampusviewImgFavorite findByMemberAndImg(Member member,CampusviewImg campusviewImg);
    
    /**
     * 根据相册查询点赞记录
     */
    public List<CampusviewImgFavorite> findByImg( CampusviewImg campusviewImg);
}
