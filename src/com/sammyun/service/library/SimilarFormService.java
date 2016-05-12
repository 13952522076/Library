package com.sammyun.service.library;

import java.util.List;

import com.sammyun.entity.Admin;
import com.sammyun.entity.library.SimilarForm;
import com.sammyun.service.BaseService;

/**
 * Service - 相似度矩阵
 */
public interface SimilarFormService extends BaseService<SimilarForm, Long>
{
    /**
     * 根据用户id获取相似度矩阵(已排序)
     * <功能详细描述>
     * @param adminId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<SimilarForm> getSimilarForms(Long adminId);
    
    /**
     * 根据用户id获取最相似同学(已排序)
     * <功能详细描述>
     * @param adminId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Admin> getSimialrStudents(Long adminId);
}
