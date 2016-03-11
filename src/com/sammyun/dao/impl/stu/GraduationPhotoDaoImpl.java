package com.sammyun.dao.impl.stu;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.stu.GraduationPhotoDao;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.stu.GraduationPhoto;

/**
 * GraduationPhoto * DaoImpl - 毕业合影
 * 


 */
@Repository("graduationPhotoDaoImpl")
public class GraduationPhotoDaoImpl extends BaseDaoImpl<GraduationPhoto, Long> implements GraduationPhotoDao
{

    @Override
    public void deleteByDictClass(DictClass dictClass)
    {
        String jpql = "delete from GraduationPhoto graduationPhoto where graduationPhoto.dictClass = :dictClass";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("dictClass", dictClass).executeUpdate();
        
    }

}
