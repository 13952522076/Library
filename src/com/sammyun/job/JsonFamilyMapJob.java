package com.sammyun.job;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sammyun.entity.Member;
import com.sammyun.service.json.JsonFamilyMapService;

@Component("jsonFamilyMapJob")
@Lazy(false)
@SuppressWarnings("unchecked")
public class JsonFamilyMapJob
{
    /** CacheManager */
    private static final CacheManager cacheManager = CacheManager.create();
    
    @Resource(name = "jsonFamilyMapServiceImpl")
    private JsonFamilyMapService jsonFamilyMapService;
   
    @Scheduled(cron = "${job.jsonFamilyMap.cron}")
    public void sendMessage()
    {
        List<Long> memberIds = new LinkedList<Long>();
        Set<Long> tempMemberIds = new HashSet<Long>();
        
        Ehcache cache = cacheManager.getEhcache(Member.CACHE_NAME);
        List<String> keys = cache.getKeys();
        for (String key : keys)
        {
            List<Long> memberId = new LinkedList<Long>();
            net.sf.ehcache.Element cacheElement = cache.get(key);
            if (cacheElement != null)
            {
                memberId = (List<Long>) cacheElement.getObjectValue();
                tempMemberIds.addAll(memberId);
            }
        }
        memberIds.addAll(tempMemberIds);
        cache.removeAll();
        jsonFamilyMapService.createData(memberIds);
    }
}
