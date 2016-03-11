package com.sammyun.service.impl.campusviewImg;

import java.util.List;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.campusviewImg.CampusviewImgFavoriteService;

import com.sammyun.dao.campusviewImg.CampusviewImgFavoriteDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.campusviewImg.CampusviewImg;
import com.sammyun.entity.campusviewImg.CampusviewImgFavorite;

/**
 * CampusviewImgFavorite * ServiceImpl - 校园风光点赞记录表
 * 


 */

@Service("campusviewImgFavoriteServiceImpl")
public class CampusviewImgFavoriteServiceImpl extends BaseServiceImpl<CampusviewImgFavorite, Long> implements CampusviewImgFavoriteService {

    @Resource(name = "campusviewImgFavoriteDaoImpl")
    private CampusviewImgFavoriteDao campusviewImgFavoriteDao;

    @Resource(name = "campusviewImgFavoriteDaoImpl")
    public void setBaseDao(CampusviewImgFavoriteDao campusviewImgFavoriteDao){
        super.setBaseDao(campusviewImgFavoriteDao);
    }

    @Override
    public CampusviewImgFavorite findByMemberAndImg(Member member, CampusviewImg campusviewImg)
    {
        return campusviewImgFavoriteDao.findByMemberAndImg(member, campusviewImg);
    }

    @Override
    public List<CampusviewImgFavorite> findByImg(CampusviewImg campusviewImg)
    {
        return campusviewImgFavoriteDao.findByImg(campusviewImg);
    }


}
