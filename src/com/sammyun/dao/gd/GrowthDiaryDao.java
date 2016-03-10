package com.sammyun.dao.gd;

import java.util.List;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.BaseDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.gd.DiaryTag;
import com.sammyun.entity.gd.GrowthDiary;

/**
 * GrowthDiary * Dao - 成长记
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface GrowthDiaryDao extends BaseDao<GrowthDiary, Long>
{
    /**
     * 查询出成长记列表
     * @param friends
     * @param pageable
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Page<GrowthDiary> findPage(List<Member> friends,Pageable pageable);
    
    /**
     * 通过用户查找到成长记列表
     * <功能详细描述>
     * @param member
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<GrowthDiary> findByMember(Member member);
    
    /**
     * 通过标签查找到成长记列表
     * <功能详细描述>
     * @param diaryTag
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<GrowthDiary> findByDiaryTag(DiaryTag diaryTag);
}
