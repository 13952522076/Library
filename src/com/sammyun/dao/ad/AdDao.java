/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.dao.ad;

import java.util.Date;
import java.util.List;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.BaseDao;
import com.sammyun.entity.ad.Ad;
import com.sammyun.entity.ad.AdArea;
import com.sammyun.entity.ad.AdCategory;
import com.sammyun.entity.ad.AdDeviceType;
import com.sammyun.entity.ad.AdNetType;
import com.sammyun.entity.ad.AdNetWork;
import com.sammyun.entity.ad.AdTimeSlot;
import com.sammyun.entity.ad.Ad.AdPosition;
import com.sammyun.entity.ad.Ad.Platform;
import com.sammyun.entity.ad.Ad.ShowType;
import com.sammyun.entity.ad.Ad.SimType;
import com.sammyun.entity.ad.Ad.Type;
import com.sammyun.entity.dict.DictSchool;

/**
 * Dao - 广告
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface AdDao extends BaseDao<Ad, Long>
{
    /**
     * 
     * 查询广告
     * 
     * @param type
     *           类型
     * @param adPosition
     *           处于App的什么位置
     * @param showType
     *           广告弹出类型
     * @param simType
     *           广告类型
     * @param adCategory
     *           广告分类
     * @param beginDate
     *           投放起始时间段 
     * @param endDate
     *           投放结束时间段 
     * @param adAreas
     *           定向地区
     * @param adDeviceTypes
     *           定向设备类型
     * @param adNetTypes
     *           定向网络类型
     * @param adNetWorks
     *           定向运营商类型
     * @param adTimeSlots
     *           定向时间段
     * @param adDictSchools
     *           定向学校
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Page<Ad> finPage(Type type,Platform platform, AdPosition adPosition, ShowType showType, SimType simType,
            AdCategory adCategory,  Date date,  List<AdArea> adAreas,
            List<AdDeviceType> adDeviceTypes, List<AdNetType> adNetTypes, List<AdNetWork> adNetWorks,
            List<AdTimeSlot> adTimeSlots, List<DictSchool> adDictSchools,Boolean isOnLine,Boolean isDraft,Boolean status,Pageable pageable);
}
