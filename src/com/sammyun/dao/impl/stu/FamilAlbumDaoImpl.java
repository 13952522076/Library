package com.sammyun.dao.impl.stu;

import javax.persistence.FlushModeType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.stu.FamilAlbumDao;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.stu.FamilAlbum;

/**
 * FamilAlbum * DaoImpl - 全家福
 * 


 */
@Repository("familAlbumDaoImpl")
public class FamilAlbumDaoImpl extends BaseDaoImpl<FamilAlbum, Long> implements FamilAlbumDao
{
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(FamilAlbumDaoImpl.class);

    @Override
    public void deleteByDictStudent(DictStudent dictStudent)
    {
        String jpql = "delete from FamilAlbum familAlbum where familAlbum.dictStudent = :dictStudent";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("dictStudent", dictStudent).executeUpdate();
        
    }

    

}
