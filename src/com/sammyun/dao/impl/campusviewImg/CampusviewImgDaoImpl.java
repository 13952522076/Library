package com.sammyun.dao.impl.campusviewImg;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.campusviewImg.CampusviewImgDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.campusviewImg.CampusviewImg;
import com.sammyun.entity.dict.DictSchool;

/**
 * CampusviewImg * DaoImpl - 校园风光表
 * 


 */

@Repository("campusviewImgDaoImpl")
public class CampusviewImgDaoImpl extends BaseDaoImpl<CampusviewImg, Long> implements CampusviewImgDao  {

    @Override
    public Page<CampusviewImg> findBySchool(DictSchool dictSchool, Long status, Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CampusviewImg> criteriaQuery = criteriaBuilder.createQuery(CampusviewImg.class);
        Root<CampusviewImg> root = criteriaQuery.from(CampusviewImg.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        
        if (dictSchool != null) {
          restrictions = criteriaBuilder.and(
                  restrictions,
                  criteriaBuilder.equal(
                          root.get("dictSchool"),dictSchool));
      }
      if (status != null) {
          restrictions = criteriaBuilder.and(
                  restrictions,
                  criteriaBuilder.equal(
                          root.get("status"), status));
      }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery,pageable);
    }

}
