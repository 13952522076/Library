package com.sammyun.service.impl.campusviewImg;

import java.util.List;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.campusviewImg.CampusviewImgViewService;

import com.sammyun.dao.campusviewImg.CampusviewImgViewDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.campusviewImg.CampusviewImg;
import com.sammyun.entity.campusviewImg.CampusviewImgView;

/**
 * CampusviewImgView * ServiceImpl - 校园风光查看记录表
 * 
 * @author Sencloud Team
 * @version 3.0
 */

@Service("campusviewImgViewServiceImpl")
public class CampusviewImgViewServiceImpl extends BaseServiceImpl<CampusviewImgView, Long> implements CampusviewImgViewService {

    @Resource(name = "campusviewImgViewDaoImpl")
    private CampusviewImgViewDao campusviewImgViewDao;

    @Resource(name = "campusviewImgViewDaoImpl")
    public void setBaseDao(CampusviewImgViewDao campusviewImgViewDao){
        super.setBaseDao(campusviewImgViewDao);
    }

    @Override
    public CampusviewImgView findByMemberAndImg(Member member, CampusviewImg campusviewImg)
    {
        return campusviewImgViewDao.findByMemberAndImg(member, campusviewImg);
    }

    @Override
    public List<CampusviewImgView> findByImg(CampusviewImg campusviewImg)
    {
        return campusviewImgViewDao.findByImg(campusviewImg);
    }


}
