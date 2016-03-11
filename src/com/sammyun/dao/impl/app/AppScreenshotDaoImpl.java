package com.sammyun.dao.impl.app;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.app.AppScreenshotDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.app.App;
import com.sammyun.entity.app.AppScreenshot;

/**
 * DaoImpl - 应用管理
 * 


 */
@Repository("appScreenshotDaoImpl")
public class AppScreenshotDaoImpl extends BaseDaoImpl<AppScreenshot, Long> implements AppScreenshotDao
{

    @Override
    public void deleteByApp(App app)
    {
        String jpql = "delete from AppScreenshot appScreenshot where appScreenshot.app = :app";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("app", app).executeUpdate();
        
    }

}
