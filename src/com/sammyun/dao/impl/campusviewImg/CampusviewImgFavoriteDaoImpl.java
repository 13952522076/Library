package com.sammyun.dao.impl.campusviewImg;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.campusviewImg.CampusviewImgFavoriteDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.Member;
import com.sammyun.entity.campusviewImg.CampusviewImg;
import com.sammyun.entity.campusviewImg.CampusviewImgFavorite;

/**
 * CampusviewImgFavorite * DaoImpl - 校园风光点赞记录表
 * 
 * @author Sencloud Team
 * @version 3.0
 */

@Repository("campusviewImgFavoriteDaoImpl")
public class CampusviewImgFavoriteDaoImpl extends BaseDaoImpl<CampusviewImgFavorite, Long> implements
        CampusviewImgFavoriteDao
{

    @Override
    public CampusviewImgFavorite findByMemberAndImg(Member member, CampusviewImg campusviewImg)
    {
        if (member == null)
        {
            return null;
        }
        if (campusviewImg == null)
        {
            return null;
        }
        String jpql = "select campusviewImgFavorite from CampusviewImgFavorite campusviewImgFavorite where campusviewImgFavorite.member = :member and campusviewImgFavorite.campusviewImg = :campusviewImg";
        try
        {
            return entityManager.createQuery(jpql, CampusviewImgFavorite.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "member", member).setParameter("campusviewImg", campusviewImg).getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    @Override
    public List<CampusviewImgFavorite> findByImg(CampusviewImg campusviewImg)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<CampusviewImgFavorite> criteriaQuery = criteriaBuilder.createQuery(CampusviewImgFavorite.class);
        Root<CampusviewImgFavorite> root = criteriaQuery.from(CampusviewImgFavorite.class);
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
