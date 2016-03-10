package com.sammyun.dao.stu;


import com.sammyun.dao.BaseDao;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.stu.FamilAlbum;

public interface FamilAlbumDao extends BaseDao<FamilAlbum, Long>
{   
    /**
     * 根据学生删除其对应的所有全家福
     * @param dictStudent
     * @see [类、类#方法、类#成员]
     */
    public void deleteByDictStudent(DictStudent dictStudent);
}
