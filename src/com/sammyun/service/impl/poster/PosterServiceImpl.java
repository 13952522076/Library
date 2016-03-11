package com.sammyun.service.impl.poster;

import java.util.Date;
import java.util.List;

import com.sammyun.service.impl.BaseServiceImpl;
import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import com.sammyun.service.poster.PosterService;

import com.sammyun.dao.poster.PosterDao;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.poster.Poster;
import com.sammyun.entity.poster.Poster.PosterPosition;

/**
 * Poster * ServiceImpl - 海报
 * 


 */
@Service("posterServiceImpl")
public class PosterServiceImpl extends BaseServiceImpl<Poster, Long> implements PosterService {

    @Resource(name = "posterDaoImpl")
    private PosterDao posterDao;

    @Resource(name = "posterDaoImpl")
    public void setBaseDao(PosterDao posterDao){
        super.setBaseDao(posterDao);
    }

    @Override
    public List<Poster> findBySchool(DictSchool dictSchool, PosterPosition posterPosition, Boolean status)
    {
        return posterDao.findBySchool(dictSchool, posterPosition, status);
    }
    
    @Override
    public List<Poster> findBySchool(DictSchool dictSchool, PosterPosition posterPosition, Boolean status, Date updateDate)
    {
        return posterDao.findBySchool(dictSchool, posterPosition, status, updateDate);
    }


}
