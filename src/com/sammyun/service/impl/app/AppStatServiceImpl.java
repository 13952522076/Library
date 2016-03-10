package com.sammyun.service.impl.app;

import java.util.Set;

import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.stereotype.Service;

import com.sammyun.dao.app.AppDao;
import com.sammyun.dao.app.AppStatDao;
import com.sammyun.entity.app.App;
import com.sammyun.entity.app.AppReview;
import com.sammyun.entity.app.AppStat;
import com.sammyun.service.app.AppStatService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * Service - 应用统计
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("appStatServiceImpl")
public class AppStatServiceImpl extends BaseServiceImpl<AppStat, Long> implements AppStatService
{

    /** 查看应用安装用户量时间 */
    private long countUserTime = System.currentTimeMillis();

    /** 查看应用查看的数量时间 */
    private long countViewTime = System.currentTimeMillis();

    /** 查看应用得分数量的时间 */
    private long countRatingTime = System.currentTimeMillis();

    /** 查看应用评价均分的时间 */
    private long avgRatingTime = System.currentTimeMillis();

    @Resource(name = "appStatDaoImpl")
    private AppStatDao appStatDao;

    @Resource(name = "appDaoImpl")
    private AppDao appDao;

    @Resource(name = "appStatDaoImpl")
    public void setBaseDao(AppStatDao appStatDao)
    {
        super.setBaseDao(appStatDao);
    }

    @Resource(name = "ehCacheManager")
    private CacheManager cacheManager;

    @Override
    public Double findAvgRating(Long id)
    {

        Ehcache cache = cacheManager.getEhcache(App.AVGRATING_CACHE_NAME);
        Element element = cache.get(id + "_avgRating");
        Double grade = 0.00;
        if (element != null)
        {
            grade = (Double) element.getValue();
        }
        else
        {
            App app = appDao.find(id);
            Set<AppReview> appReviews = app.getAppReviews();
            if (appReviews == null || appReviews.size() == 0)
            {
                grade = 0.00;
            }
            else
            {
                Integer totalGrade = 0;
                Integer reViewCount = appReviews.size();
                for (AppReview appReview : appReviews)
                {
                    if (appReview.getScore() != null)
                    {
                        totalGrade += appReview.getScore();
                    }
                }
                grade = (totalGrade.doubleValue()) / (reViewCount.doubleValue());
            }
        }
        cache.put(new Element(id + "_avgRating", grade));
        long time = System.currentTimeMillis();
        if (time > (avgRatingTime + App.COUNTUSER_CACHE_INTERVAL))
        {
            avgRatingTime = time;
            App app = appDao.find(id);
            AppStat appStat = app.getAppStat();
            appStat.setAvgRating(grade);
            this.update(appStat);
            cache.remove(element);
        }
        return grade;
    }

    @Override
    public Integer findCountUser(Long id)
    {
        Ehcache cache = cacheManager.getEhcache(App.COUNTUSER_CACHE_NAME);
        Element element = cache.get(id + "_countUser");
        Integer count = 0;
        if (element != null)
        {
            count = (Integer) element.getValue();
        }
        else
        {
            App app = appDao.find(id);
            count = (app.getAppUsers() != null) ? (app.getAppUsers().size()) : 0;
        }
        cache.put(new Element(id + "_countUser", count));
        long time = System.currentTimeMillis();
        if (time > (countUserTime + App.COUNTUSER_CACHE_INTERVAL))
        {
            countUserTime = time;
            App app = appDao.find(id);
            AppStat appStat = app.getAppStat();
            appStat.setCountUser(count);
            this.update(appStat);
            cache.remove(element);
        }
        return count;
    }

    @Override
    public Integer findCountView(Long id)
    {

        Ehcache cache = cacheManager.getEhcache(App.COUNTVIEW_CACHE_NAME);
        Element element = cache.get(id + "_countView");
        Integer count = 0;
        if (element != null)
        {
            count = (Integer) element.getValue();
        }
        else
        {
            App app = appDao.find(id);
            AppStat appStat = app.getAppStat();
            count = appStat.getCountView();
        }
        count++;
        cache.put(new Element(id + "_countView", count));
        long time = System.currentTimeMillis();
        if (time > (countViewTime + App.COUNTVIEW_CACHE_INTERVAL))
        {
            countViewTime = time;
            App app = appDao.find(id);
            AppStat appStat = app.getAppStat();
            appStat.setCountView(count);
            this.update(appStat);
            cache.remove(element);
        }
        return count;
    }

    @Override
    public Integer findCountRating(Long id)
    {

        Ehcache cache = cacheManager.getEhcache(App.COUNTRATING_CACHE_NAME);
        Element element = cache.get(id + "_countRating");
        Integer count = 0;
        if (element != null)
        {
            count = (Integer) element.getValue();
        }
        else
        {
            App app = appDao.find(id);
            count = (app.getAppReviews() == null) ? 0 : app.getAppReviews().size();
        }
        cache.put(new Element(id + "_countRating", count));
        long time = System.currentTimeMillis();
        if (time > (countRatingTime + App.COUNTRATING_CACHE_INTERVAL))
        {
            countRatingTime = time;
            App app = appDao.find(id);
            AppStat appStat = app.getAppStat();
            appStat.setCountRating(count);
            this.update(appStat);
            cache.remove(element);
        }
        return count;
    }

	@Override
	public AppStat generateAppStat(App app) {

		AppStat appStat = new AppStat();
		appStat.setApp(app);
		appStat.setAvgRating(0.00);
		appStat.setCountUser(0);
		appStat.setCountRating(0);
		appStat.setCountView(0);
		appStatDao.persist(appStat);
		return appStat;
	}
}
