package com.sammyun.dao.impl.app;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.app.AppStatDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.app.AppStat;

/**
 * Dao - 应用统计
 * 
 * @author Sencloud Team

 */
@Repository("appStatDaoImpl")
public class AppStatDaoImpl extends BaseDaoImpl<AppStat, Long> implements AppStatDao
{

}
