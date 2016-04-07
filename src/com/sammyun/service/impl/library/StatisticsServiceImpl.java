package com.sammyun.service.impl.library;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.library.StatisticsDao;
import com.sammyun.entity.library.Statistics;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.library.StatisticsService;

/**
 * ServiceImpl - 系统统计信息
 */
@Service("statisticsServiceImpl")
public class StatisticsServiceImpl extends BaseServiceImpl<Statistics, Long> implements StatisticsService
{
    @Resource(name = "statisticsDaoImpl")
    private StatisticsDao statisticsDao;

    @Resource(name = "statisticsDaoImpl")
    public void setBaseDao(StatisticsDao statisticsDao)
    {
        super.setBaseDao(statisticsDao);
    }

}
