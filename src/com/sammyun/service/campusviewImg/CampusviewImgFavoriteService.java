package com.sammyun.service.campusviewImg;

import java.util.List;

import com.sammyun.entity.Member;
import com.sammyun.entity.campusviewImg.CampusviewImg;
import com.sammyun.entity.campusviewImg.CampusviewImgFavorite;
import com.sammyun.service.BaseService;

/**
 * CampusviewImgFavorite * Service - 校园风光点赞记录表
 * 
 * @author Sencloud Team
 * @version 3.0
 */

public interface CampusviewImgFavoriteService extends BaseService<CampusviewImgFavorite, Long> {

    /**
     * 根据会员和相册查询点赞记录
     */
    public CampusviewImgFavorite findByMemberAndImg(Member member,CampusviewImg campusviewImg);
    
    /**
     * 根据相册查询点赞记录
     */
    public List<CampusviewImgFavorite> findByImg( CampusviewImg campusviewImg);
    
}
