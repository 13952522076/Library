package com.sammyun.service.impl.app;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.app.AppScreenshotDao;
import com.sammyun.entity.app.App;
import com.sammyun.entity.app.AppScreenshot;
import com.sammyun.service.app.AppScreenshotService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 应用管理
 * 
 * @author Sencloud Team

 */
@Service("appScreenshotServiceImpl")
public class AppScreenshotServiceImpl extends BaseServiceImpl<AppScreenshot, Long> implements AppScreenshotService
{
    @Resource(name = "appScreenshotDaoImpl")
    private AppScreenshotDao appScreenshotDao;

    @Resource(name = "appScreenshotDaoImpl")
    public void setBaseDao(AppScreenshotDao appScreenshotDao)
    {
        super.setBaseDao(appScreenshotDao);
    }

    @Override
    public void deleteByApp(App app)
    {
        appScreenshotDao.deleteByApp(app);

    }

}
