/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.service.impl.ad;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.ad.AdDao;
import com.sammyun.entity.ad.Ad;
import com.sammyun.entity.ad.Ad.AdPosition;
import com.sammyun.entity.ad.Ad.Platform;
import com.sammyun.entity.ad.Ad.ShowType;
import com.sammyun.entity.ad.Ad.SimType;
import com.sammyun.entity.ad.Ad.Type;
import com.sammyun.entity.ad.AdArea;
import com.sammyun.entity.ad.AdCategory;
import com.sammyun.entity.ad.AdDeviceType;
import com.sammyun.entity.ad.AdNetType;
import com.sammyun.entity.ad.AdNetWork;
import com.sammyun.entity.ad.AdTimeSlot;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.ad.AdService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * Service - 广告
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("adServiceImpl")
public class AdServiceImpl extends BaseServiceImpl<Ad, Long> implements AdService {

    @Resource(name = "adDaoImpl")
    private AdDao adDao;
    
	@Resource(name = "adDaoImpl")
	public void setBaseDao(AdDao adDao) {
		super.setBaseDao(adDao);
	}

	@Override
	@Transactional
	@CacheEvict(value = "adPosition", allEntries = true)
	public void save(Ad ad) {
		super.save(ad);
	}

	@Override
	@Transactional
	@CacheEvict(value = "adPosition", allEntries = true)
	public Ad update(Ad ad) {
		return super.update(ad);
	}

	@Override
	@Transactional
	@CacheEvict(value = "adPosition", allEntries = true)
	public Ad update(Ad ad, String... ignoreProperties) {
		return super.update(ad, ignoreProperties);
	}

	@Override
	@Transactional
	@CacheEvict(value = "adPosition", allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}

	@Override
	@Transactional
	@CacheEvict(value = "adPosition", allEntries = true)
	public void delete(Long... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	@CacheEvict(value = "adPosition", allEntries = true)
	public void delete(Ad ad) {
		super.delete(ad);
	}

    @Override
    public Page<Ad> finPage(Type type, Platform platform, AdPosition adPosition, ShowType showType, SimType simType,
            AdCategory adCategory,  Date date,  List<AdArea> adAreas,
            List<AdDeviceType> adDeviceTypes, List<AdNetType> adNetTypes, List<AdNetWork> adNetWorks,
            List<AdTimeSlot> adTimeSlots, List<DictSchool> adDictSchools, Boolean isOnLine,Boolean isDraft,Boolean status,Pageable pageable)
    {
        return adDao.finPage(type, platform,adPosition, showType, simType, adCategory,  date,  adAreas,
                adDeviceTypes, adNetTypes, adNetWorks, adTimeSlots, adDictSchools,isOnLine,isDraft,status, pageable);
    }


}