package com.sammyun.service.impl.gd;

import java.util.List;

import javax.annotation.Resource;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.springframework.stereotype.Service;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.gd.GrowthDiaryDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.gd.DiaryTag;
import com.sammyun.entity.gd.GrowthDiary;
import com.sammyun.service.gd.GrowthDiaryService;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.util.DateUtil;

/**
 * GrowthDiary * ServiceImpl -
 * 
 * @author Sencloud Team

 */
@Service("growthDiaryServiceImpl")
public class GrowthDiaryServiceImpl extends BaseServiceImpl<GrowthDiary, Long> implements GrowthDiaryService
{

    /** 查看点击数时间 */
    private long viewCountTime = System.currentTimeMillis();

    /** 播放次数时间 */
    private long palyCountTime = System.currentTimeMillis();

    @Resource(name = "growthDiaryDaoImpl")
    private GrowthDiaryDao growthDiaryDao;

    @Resource(name = "growthDiaryDaoImpl")
    public void setBaseDao(GrowthDiaryDao growthDiaryDao)
    {
        super.setBaseDao(growthDiaryDao);
    }

    @Resource(name = "ehCacheManager")
    private CacheManager cacheManager;

    @Override
    public Page<GrowthDiary> findPage(List<Member> friends, Pageable pageable)
    {
        // TODO Auto-generated method stub
        return growthDiaryDao.findPage(friends, pageable);
    }

    @Override
    public Integer viewCount(Long growthDiaryId,Long memberId)
    {
        Ehcache cache = cacheManager.getEhcache(GrowthDiary.HITS_CACHE_NAME);
        Element element = cache.get(growthDiaryId + "_count");
        GrowthDiary growthDiary = growthDiaryDao.find(growthDiaryId);
        Integer count = 0;
        if (element != null)
        {
            count = (Integer) element.getValue();
        }
        else
        {
            count = growthDiary.getReadCount();
        }
        Long preMemberId = growthDiary.getMember().getId();
        if(!preMemberId.equals(memberId))
        {
            count++;
        }
        cache.put(new Element(growthDiaryId + "_count", count));
        cache.put(new Element(growthDiaryId + "_growthDiaryHitsDate", DateUtil.generateTime()));
        long time = System.currentTimeMillis();
        if (time > (viewCountTime + GrowthDiary.HITS_CACHE_INTERVAL))
        {
            viewCountTime = time;
            growthDiary.setReadCount(count);
            this.update(growthDiary);
            cache.remove(element);
        }

        return count;
    }

    @Override
    public Integer playCount(Long growthDiaryId,Long memberId)
    {
        Ehcache cache = cacheManager.getEhcache(GrowthDiary.PLAY_CACHE_NAME);
        Element element = cache.get(growthDiaryId + "_playCount");
        GrowthDiary growthDiary = growthDiaryDao.find(growthDiaryId);
        Integer count = 0;
        if (element != null)
        {
            count = (Integer) element.getValue();
        }
        else
        {
            count = growthDiary.getReadCount();
        }
        Long preMemberId = growthDiary.getMember().getId();
        if(!preMemberId.equals(memberId))
        {
            count++;
        }
        cache.put(new Element(growthDiaryId + "_playCount", count));
        long time = System.currentTimeMillis();
        if (time > (palyCountTime + GrowthDiary.PLAY_CACHE_INTERVAL))
        {
            palyCountTime = time;
            growthDiary.setPlayCount(count);
            this.update(growthDiary);
            cache.remove(element);
        }
        return count;
    }

    @Override
    public List<GrowthDiary> findByMember(Member member)
    {
        // TODO Auto-generated method stub
        return growthDiaryDao.findByMember(member);
    }

    @Override
    public List<GrowthDiary> findByDiaryTag(DiaryTag diaryTag)
    {
        // TODO Auto-generated method stub
        return growthDiaryDao.findByDiaryTag(diaryTag);
    }

}
