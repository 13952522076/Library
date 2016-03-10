package com.sammyun.service.impl.announcement;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.announcement.AnnouncementDao;
import com.sammyun.entity.announcement.Announcement;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.announcement.AnnouncementService;
import com.sammyun.service.impl.BaseServiceImpl;

@Service("announcementServiceImpl")
public class AnnouncementServiceImpl extends BaseServiceImpl<Announcement, Long> implements AnnouncementService 
{
    @Resource(name = "announcementDaoImpl")
    private AnnouncementDao announcementDao;

    @Resource(name = "announcementDaoImpl")
    public void setBaseDao(AnnouncementDao announcementDao){
        super.setBaseDao(announcementDao);
    }

    @Override
    public Page<Announcement> findBySchool(DictSchool dictSchool, Integer status, Pageable pageable)
    {
        return announcementDao.findBySchool(dictSchool, status, pageable);
    }
}
