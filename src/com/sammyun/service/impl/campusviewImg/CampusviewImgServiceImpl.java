package com.sammyun.service.impl.campusviewImg;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.campusviewImg.CampusviewImgDao;
import com.sammyun.entity.campusviewImg.CampusviewImg;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.campusviewImg.CampusviewImgService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * CampusviewImg * ServiceImpl - 校园风光表
 * 
 * @author Sencloud Team

 */

@Service("campusviewImgServiceImpl")
public class CampusviewImgServiceImpl extends BaseServiceImpl<CampusviewImg, Long> implements CampusviewImgService {

    @Resource(name = "campusviewImgDaoImpl")
    private CampusviewImgDao campusviewImgDao;

    @Resource(name = "campusviewImgDaoImpl")
    public void setBaseDao(CampusviewImgDao campusviewImgDao){
        super.setBaseDao(campusviewImgDao);
    }

    @Override
    public Page<CampusviewImg> findBySchool(DictSchool dictSchool, Long status, Pageable pageable)
    {
        return campusviewImgDao.findBySchool(dictSchool, status, pageable);
    }


}
