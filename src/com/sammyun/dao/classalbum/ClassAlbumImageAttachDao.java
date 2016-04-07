package com.sammyun.dao.classalbum;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.classalbum.ClassAlbumImage;
import com.sammyun.entity.classalbum.ClassAlbumImageAttach;

/**
 * ClassAlbumImage * Dao - 班级相册
 * 


 */

public interface ClassAlbumImageAttachDao extends BaseDao<ClassAlbumImageAttach, Long>
{
    /**
     * 删除 班级相册图片附件
     * 
     * @param classAlbumImage 班级相册
     * @see [类、类#方法、类#成员]
     */
    void deleteByClassAlbumImage(ClassAlbumImage classAlbumImage);
}
