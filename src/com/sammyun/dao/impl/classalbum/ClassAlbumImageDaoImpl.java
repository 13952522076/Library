package com.sammyun.dao.impl.classalbum;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.attendance.WorkSchedulingDao;
import com.sammyun.dao.classalbum.ClassAlbumImageDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.Member;
import com.sammyun.entity.campusviewImg.CampusviewImg;
import com.sammyun.entity.classalbum.ClassAlbumImage;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;

/**
 * ClassAlbumImage * DaoImpl - 校园风光表
 * 
 * @author Sencloud Team
 * @version 3.0
 */

@Repository("classAlbumImageDaoImpl")
public class ClassAlbumImageDaoImpl extends BaseDaoImpl<ClassAlbumImage, Long> implements ClassAlbumImageDao
{
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(WorkSchedulingDao.class);

    @Override
    public Page<ClassAlbumImage> findBySchool(DictSchool dictSchool, DictClass dictClass, Long status, Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ClassAlbumImage> criteriaQuery = criteriaBuilder.createQuery(ClassAlbumImage.class);
        Root<ClassAlbumImage> root = criteriaQuery.from(ClassAlbumImage.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();

        if (dictSchool != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("dictSchool"), dictSchool));
        }
        if (dictSchool != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("dictClass"), dictClass));
        }
        if (status != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("status"), status));
        }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }

    @Override
    public List<DictClass> getDictClasses(DictSchool dictSchool)
    {
        if(dictSchool==null){
            return null;
        }
        String jpql = "select classAlbumImage.dictClass from ClassAlbumImage classAlbumImage where classAlbumImage.dictSchool = :dictSchool";
        List<DictClass> classes = new ArrayList<DictClass>();
        try
        {
            TypedQuery<DictClass> flushModel = entityManager.createQuery(jpql, DictClass.class).setFlushMode(
                    FlushModeType.COMMIT).setParameter("dictSchool", dictSchool);
            classes = (List<DictClass>) flushModel.getResultList();
        }
        catch (Exception e)
        {
            logger.error("ClassAlbumImageDaoImpl.getDictClasses通过dao查询：" + e.getMessage());
        }

        return classes;
    }

}
