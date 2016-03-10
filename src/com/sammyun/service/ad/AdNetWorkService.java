package com.sammyun.service.ad;

import java.util.List;

import com.sammyun.entity.ad.AdNetWork;
import com.sammyun.service.BaseService;

/**
 *  Service - 定向运营商
 * 
 * @author chenyunfeng
 * @version [版本号, Aug 28, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface AdNetWorkService extends BaseService<AdNetWork, Long>
{
    /**
     * 查找定向运行商
     * @param netWork
     * @return
     * @see [类、类#方法、类#成员]
     */
   public List<AdNetWork> finListByNetWork(String netWork);
}
