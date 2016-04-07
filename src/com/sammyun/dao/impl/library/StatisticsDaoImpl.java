package com.sammyun.dao.impl.library;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.library.StatisticsDao;
import com.sammyun.entity.library.Statistics;

/**
 * DaoImpl - 系统统计信息
 */
@Repository("statisticsDaoImpl")
public class StatisticsDaoImpl extends BaseDaoImpl<Statistics, Long> implements StatisticsDao
{

}
