package com.sammyun.dao.stu;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.stu.GraduationPhoto;

/**
 * GraduationPhoto * Dao - 毕业合影
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface GraduationPhotoDao extends BaseDao<GraduationPhoto, Long>
{
    /**
     * 根据班级删除毕业合影
     * @param dictClass
     * @see [类、类#方法、类#成员]
     */
    public void deleteByDictClass(DictClass dictClass);
}
