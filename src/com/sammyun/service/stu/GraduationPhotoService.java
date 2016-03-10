package com.sammyun.service.stu;

import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.stu.GraduationPhoto;
import com.sammyun.service.BaseService;

/**
 * Service - 毕业合影
 * 
 * @author Sencloud Team

 */
public interface GraduationPhotoService extends BaseService<GraduationPhoto, Long>
{
    /**
     * 根据班级删除毕业合影
     * @param dictClass
     * @see [类、类#方法、类#成员]
     */
    public void deleteByDictClass(DictClass dictClass);
}
