package com.sammyun.service.classalbum;

import com.sammyun.entity.classalbum.ClassAlbumImage;
import com.sammyun.entity.classalbum.ClassAlbumImageAttach;
import com.sammyun.service.BaseService;

/**
 * ClassAlbumImage * Service - 班级相册
 */
public interface ClassAlbumImageAttachService extends BaseService<ClassAlbumImageAttach, Long>
{
    /**
     * 删除 班级相册图片附件
     * 
     * @param classAlbumImage 班级相册
     * @see [类、类#方法、类#成员]
     */
    void deleteByClassAlbumImage(ClassAlbumImage classAlbumImage);
}
