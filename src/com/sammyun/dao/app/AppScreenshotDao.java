package com.sammyun.dao.app;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.app.App;
import com.sammyun.entity.app.AppScreenshot;

/**
 * Dao - 应用管理
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface AppScreenshotDao extends BaseDao<AppScreenshot, Long>
{
    /**
     * 根据app删除截图
     * <功能详细描述>
     * @param app
     * @see [类、类#方法、类#成员]
     */
    public void deleteByApp(App app);
}
