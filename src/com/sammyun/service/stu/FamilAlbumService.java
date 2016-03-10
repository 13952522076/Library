package com.sammyun.service.stu;

import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.stu.FamilAlbum;
import com.sammyun.service.BaseService;

/**
 * Service - 全家福
 * 
 * @author Sencloud Team
 * @version 3.0
 */
public interface FamilAlbumService extends BaseService<FamilAlbum, Long>
{
    /**
     * 根据学生删除其对应的所有全家福
     * @param dictStudent
     * @see [类、类#方法、类#成员]
     */
    public void deleteByDictStudent(DictStudent dictStudent);
}
