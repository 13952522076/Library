package com.sammyun.dao.classalbum;

import java.util.List;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.BaseDao;
import com.sammyun.entity.classalbum.ClassAlbumImage;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;

/**
 * ClassAlbumImage * Dao - 班级相册
 * 
 * @author Sencloud Team

 */

public interface ClassAlbumImageDao extends BaseDao<ClassAlbumImage, Long>
{
    /**
     * 根据学校查询校园相册
     */
    public Page<ClassAlbumImage> findBySchool(DictSchool dictSchool, DictClass dictClass, Long status, Pageable pageable);

    /**
     * 根据学校查询出已经添加相册的班级
     * 
     * @param dictSchool
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<DictClass> getDictClasses(DictSchool dictSchool);
}
