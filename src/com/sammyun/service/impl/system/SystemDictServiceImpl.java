package com.sammyun.service.impl.system;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sammyun.dao.system.SystemDictDao;
import com.sammyun.entity.system.SystemDict;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.system.SystemDictService;

/**
 * Service - 系统字典
 * 
 * @author xutianlong
 * @version [版本号, Jul 10, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service("systemDictServiceImpl")
public class SystemDictServiceImpl extends BaseServiceImpl<SystemDict, Long> implements SystemDictService
{
    public static final Logger logger = LoggerFactory.getLogger(SystemDictServiceImpl.class);

    @Resource(name = "systemDictDaoImpl")
    private SystemDictDao systemDictDao;

    @Resource(name = "systemDictDaoImpl")
    public void setBaseDao(SystemDictDao systemDictDao)
    {
        super.setBaseDao(systemDictDao);
    }

    /** 缓存管理 */
    private static final CacheManager cacheManager = CacheManager.create();

    /**
     * 设置 系统字典到内存
     * 
     * @return
     */
    @Override
    public void set()
    {

        Ehcache cache = cacheManager.getEhcache(SystemDict.CACHE_NAME);
        Map<String, Map<String, String>> systemDictLargeMap = new LinkedHashMap<String, Map<String, String>>();
        try
        {
            List<SystemDict> systemDicts = super.findAll();
            for (SystemDict systemDict : systemDicts)
            {
                Map<String, String> systemDictMap;
                String field = systemDict.getField();
                if (systemDictLargeMap.containsKey(field))
                {
                    systemDictMap = systemDictLargeMap.get(field);
                }
                else
                {
                    systemDictMap = new LinkedHashMap<String, String>();
                }
                systemDictMap.put(systemDict.getCode(), systemDict.getCodeDesc());
                systemDictLargeMap.put(field, systemDictMap);

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        }
        cache.put(new net.sf.ehcache.Element(SystemDict.CACHE_KEY, systemDictLargeMap));

    }

    @Override
    public List<SystemDict> findByCode(String code)
    {
        // TODO Auto-generated method stub
        return systemDictDao.findByCode(code);
    }

    @Override
    public List<SystemDict> findByCodeDesc(String codeDesc)
    {
        // TODO Auto-generated method stub
        return systemDictDao.findByCodeDesc(codeDesc);
    }
}
