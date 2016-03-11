package com.sammyun.dao.gd;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.gd.DiaryTag;

/**
 * DiaryTagDao * Dao - 成长记标签
 * 


 */
public interface DiaryTagDao extends BaseDao<DiaryTag, Long>
{
    /**
     * 根据姓名查询出标签
     * <功能详细描述>
     * @param name
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<DiaryTag> findByName(String name);
}
