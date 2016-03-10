package com.sammyun.dao.ad;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.ad.AdNetWork;

/**
 *  Dao - 定向运营商
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface AdNetWorkDao extends BaseDao<AdNetWork, Long>
{
    /**
     * 查找定向运行商
     * @param netWork
     * @return
     * @see [类、类#方法、类#成员]
     */
   public List<AdNetWork> finListByNetWork(String netWork);
}
