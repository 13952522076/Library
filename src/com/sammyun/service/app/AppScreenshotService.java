package com.sammyun.service.app;

import com.sammyun.entity.app.App;
import com.sammyun.entity.app.AppScreenshot;
import com.sammyun.service.BaseService;

/**
 * Service - 应用管理
 * 
 * @author Sencloud Team

 */
public interface AppScreenshotService extends BaseService<AppScreenshot, Long>
{
    /**
     * 根据app删除截图
     * <功能详细描述>
     * @param app
     * @see [类、类#方法、类#成员]
     */
    public void deleteByApp(App app);
}
