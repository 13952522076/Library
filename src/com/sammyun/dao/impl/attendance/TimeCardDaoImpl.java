package com.sammyun.dao.impl.attendance;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.attendance.TimeCardDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.TimeCard;
import com.sammyun.entity.attendance.TimeCard.CardStatus;
import com.sammyun.entity.dict.DictStudent;

/**
 * DaoImpl - 卡管理
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("timeCardDaoImpl")
public class TimeCardDaoImpl extends BaseDaoImpl<TimeCard, Long> implements TimeCardDao
{

    @Override
    public TimeCard findByCardNumber(String cardNumber,CardStatus cardStatus)
    {

        if (cardNumber == null)
        {
            return null;
        }
        if (cardStatus == null)
        {
            return null;
        }
        String jpql = "select timeCard from TimeCard timeCard where timeCard.cardNumber = :cardNumber and timeCard.cardStatus = :cardStatus";
        try
        {
            return entityManager.createQuery(jpql, TimeCard.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "cardNumber", cardNumber).setParameter("cardStatus", cardStatus).getSingleResult();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    @Override
    public List<TimeCard> findByMember(Member member, DictStudent dictStudent, CardStatus cardStatus)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TimeCard> criteriaQuery = criteriaBuilder.createQuery(TimeCard.class);
        Root<TimeCard> root = criteriaQuery.from(TimeCard.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (member != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("member"), member));
        }
        if (dictStudent != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("dictStudent"), dictStudent));
        }
        if (cardStatus != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("cardStatus"), cardStatus));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

	@Override
	public List<TimeCard> findByStudent(DictStudent dictStudent) {
		if(dictStudent == null){
			return null;
		}
		String jpql = "select timeCard from TimeCard timeCard where timeCard.dictStudent = :dictStudent";
        try
        {
            return entityManager.createQuery(jpql, TimeCard.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "dictStudent", dictStudent).getResultList();
        }
        catch (NoResultException e)
        {
            return null;
        }
	}

    @Override
    public List<TimeCard> findByPatriarch(Member member)
    {
        if(member == null){
            return null;
        }
        String jpql = "select timeCard from TimeCard timeCard where timeCard.member = :member";
        try
        {
            return entityManager.createQuery(jpql, TimeCard.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                    "member", member).getResultList();
        }
        catch (NoResultException e)
        {
            return null;
        }
    }

    @Override
    public Boolean timeCardExsit(String cardNumber)
    {
        if (cardNumber == null)
        {
            return false;
        }
        String jpql = "select count(*) from TimeCard timeCard where lower(timeCard.cardNumber) = lower(:cardNumber)";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "cardNumber", cardNumber).getSingleResult();
        return count > 0;
    }

    @Override
    public Boolean patriarchStudentNormalExsit(Member member, DictStudent dictStudent)
    {
        if (member == null)
        {
            return false;
        }
        if(dictStudent == null){
            return false;
        }
        CardStatus cardStatus = CardStatus.normal;
        String jpql = "select count(*) from TimeCard timeCard where timeCard.member = :member and timeCard.dictStudent = :dictStudent and timeCard.cardStatus = :cardStatus";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "member", member).setParameter("dictStudent", dictStudent).setParameter("cardStatus", cardStatus).getSingleResult();
        return count > 0;
    }
    
//    @Override
//    public List<TimeCard> findByMember(Member member, CardStatus cardStatus)
//    {
//        if (member == null)
//        {
//            return null;
//        }
//        if (cardStatus == null)
//        {
//            return null;
//        }
//        String jpql = "select timeCard from TimeCard timeCard where timeCard.member = :member and timeCard.cardStatus = :cardStatus";
//        try
//        {
//            return entityManager.createQuery(jpql, TimeCard.class).setFlushMode(FlushModeType.COMMIT).setParameter(
//                    "member", member).setParameter("cardStatus", cardStatus).getSingleResult();
//        }
//        catch (NoResultException e)
//        {
//            return null;
//        }
//    }
}
