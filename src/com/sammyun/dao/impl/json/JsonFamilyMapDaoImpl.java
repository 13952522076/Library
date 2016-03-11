package com.sammyun.dao.impl.json;

import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.json.JsonFamilyMapDao;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.json.JsonFamilyMap;

/**
 * JsonFamilyMap * DaoImpl - 家庭的JSON信息
 * 


 */
@Repository("jsonFamilyMapDaoImpl")
public class JsonFamilyMapDaoImpl extends BaseDaoImpl<JsonFamilyMap, Long> implements JsonFamilyMapDao  
{

    @Override
    public List<JsonFamilyMap> findBySchool(Long dictSchoolId, Date modifyDate)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<JsonFamilyMap> criteriaQuery = criteriaBuilder
                .createQuery(JsonFamilyMap.class);
        Root<JsonFamilyMap> root = criteriaQuery.from(JsonFamilyMap.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (dictSchoolId != null) {
            restrictions = criteriaBuilder.and(
                    restrictions,
                    criteriaBuilder.equal(
                            root.get("dictSchoolId"),dictSchoolId));
        }
        if (modifyDate != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.greaterThanOrEqualTo(root.<Date> get("modifyDate"), modifyDate));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery)
                 .setFlushMode(FlushModeType.COMMIT).getResultList();
    }

    @Override
    public JsonFamilyMap findByFamilyId(Long familyId, MemberType memberType)
    {
        if (familyId == null)
        {
            return null;
        }
        if (memberType == null)
        {
            return null;
        }
        String jpql = "select jsonFamilyMap from JsonFamilyMap jsonFamilyMap where jsonFamilyMap.familyId = :familyId and jsonFamilyMap.memberType = :memberType";
        try
        {
            return entityManager.createQuery(jpql, JsonFamilyMap.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "familyId", familyId).setParameter("memberType", memberType).getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

}
