package com.sammyun.dao.impl.campusviewImg;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.campusviewImg.CampusviewImgViewDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.campusviewImg.CampusviewImg;
import com.sammyun.entity.campusviewImg.CampusviewImgFavorite;
import com.sammyun.entity.campusviewImg.CampusviewImgView;

/**
 * CampusviewImgView * DaoImpl - 校园风光查看记录表
 * 
 * @author Sencloud Team

 */

@Repository("campusviewImgViewDaoImpl")
public class CampusviewImgViewDaoImpl extends BaseDaoImpl<CampusviewImgView, Long> implements CampusviewImgViewDao  {

    @Override
    public CampusviewImgView findByMemberAndImg(Member member, CampusviewImg campusviewImg)
    {
        if (member == null)
        {
            return null;
        }
        if (campusviewImg == null)
        {
            return null;
        }
        String jpql = "select campusviewImgView from CampusviewImgView campusviewImgView where campusviewImgView.member = :member and campusviewImgView.campusviewImg = :campusviewImg";
        try
        {
            return entityManager.createQuery(jpql, CampusviewImgView.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "member", member).setParameter("campusviewImg", campusviewImg).getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    @Override
    public List<CampusviewImgView> findByImg(CampusviewImg campusviewImg)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CampusviewImgView> criteriaQuery = criteriaBuilder.createQuery(CampusviewImgView.class);
        Root<CampusviewImgView> root = criteriaQuery.from(CampusviewImgView.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();

        if (campusviewImg != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("campusviewImg"), campusviewImg));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

}
