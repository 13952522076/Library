package com.sammyun.dao.library;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.library.SimilarForm;

/**
 * Dao - 相似度矩阵
 */
public interface SimilarFormDao extends BaseDao<SimilarForm, Long>
{
    /**
     * 根据用户id获取最相似的同学(未排序) <功能详细描述>
     * 
     * @param adminId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<SimilarForm> getSimilarForms(Long adminId);
}
