package com.sammyun.service.gd;

import java.util.List;

import com.sammyun.entity.gd.DiaryTag;
import com.sammyun.service.BaseService;

/**
 * DiaryTag * Service - 成长记标签
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface DiaryTagService extends BaseService<DiaryTag, Long>
{
    /**
     * 根据姓名查询出标签 <功能详细描述>
     * 
     * @param name
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<DiaryTag> findByName(String name);
}
