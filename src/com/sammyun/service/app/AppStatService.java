package com.sammyun.service.app;

import com.sammyun.entity.app.App;
import com.sammyun.entity.app.AppStat;
import com.sammyun.service.BaseService;

/**
 * Service - 应用统计
 * 
 * @author Sencloud Team

 */
public interface AppStatService extends BaseService<AppStat, Long>
{
    /**
     * 获得平均得分 5min更新一次
     * 
     * @param app
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Double findAvgRating(Long id);

    /**
     * 获取评价数量 5min更新一次
     * 
     * @param app
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Integer findCountRating(Long id);

    /**
     * 获取应用安装用户量 <功能详细描述>
     * 
     * @param app
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Integer findCountUser(Long id);

    /**
     * 获取应用查看的数量 <功能详细描述>
     * 
     * @param appStatId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Integer findCountView(Long appStatId);
    
    /**
     *  生成appStat应用统计
     * @param app
     */
    public AppStat generateAppStat(App app);

}
