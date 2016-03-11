package com.sammyun.service.campusviewImg;

import java.util.List;

import com.sammyun.entity.Member;
import com.sammyun.entity.campusviewImg.CampusviewImg;
import com.sammyun.entity.campusviewImg.CampusviewImgView;
import com.sammyun.service.BaseService;

/**
 * CampusviewImgView * Service - 校园风光查看记录表
 * 


 */

public interface CampusviewImgViewService extends BaseService<CampusviewImgView, Long> {

    /**
     * 根据会员和学校查询点赞记录
     */
    public CampusviewImgView findByMemberAndImg(Member member,CampusviewImg campusviewImg);
    
    /**
     * 根据相册查询查看记录
     */
    public List<CampusviewImgView> findByImg( CampusviewImg campusviewImg);
}
