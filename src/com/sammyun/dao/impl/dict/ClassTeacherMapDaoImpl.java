package com.sammyun.dao.impl.dict;

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

import com.sammyun.dao.dict.ClassTeacherMapDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.impl.recipe.RecipeDaoImpl;
import com.sammyun.entity.Member;
import com.sammyun.entity.dict.ClassTeacherMap;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictClass.ClassStatus;
import com.sammyun.entity.recipe.Recipe;

/**
 * ClassTeacherMap * DaoImpl - 学校
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("classTeacherMapDaoImpl")
public class ClassTeacherMapDaoImpl  extends BaseDaoImpl<ClassTeacherMap, Long> implements ClassTeacherMapDao 
{
	/** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(RecipeDaoImpl.class);
	
    @Override
    public List<ClassTeacherMap> findClassesBySchoolAndMember(DictSchool school, Member member, ClassStatus classStatus)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ClassTeacherMap> criteriaQuery = criteriaBuilder
                .createQuery(ClassTeacherMap.class);
        Root<ClassTeacherMap> root = criteriaQuery.from(ClassTeacherMap.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (school != null) {
            restrictions = criteriaBuilder.and(
                    restrictions,
                    criteriaBuilder.equal(
                            root.get("dictClass").get("dictSchool"),school));
        }
        if (member != null) {
            restrictions = criteriaBuilder.and(
                    restrictions,
                    criteriaBuilder.equal(
                            root.get("member"), member));
        }
        if (classStatus != null) {
            restrictions = criteriaBuilder.and(
                    restrictions,
                    criteriaBuilder.equal(
                            root.get("dictClass").get("classStatus"), classStatus));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery)
                 .setFlushMode(FlushModeType.COMMIT).getResultList();
    }

	@Override
	public List<Member> findMemberByClass(DictClass dictClass) {

        String jpql = "select classTeacherMap.member from ClassTeacherMap classTeacherMap where 1=1 and classTeacherMap.dictClass = :dictClass";
        List<Member> members = null;
        try
        {
            TypedQuery<Member> flushModel = entityManager.createQuery(jpql, Member.class).setFlushMode(
                    FlushModeType.COMMIT);
            flushModel.setParameter("dictClass", dictClass);
            members = (List<Member>) flushModel.getResultList();
        }
        catch (Exception e)
        {
            logger.error("查询食谱列表页面错误：" + e.getMessage());
        }

        return members;
	}

	@Override
	public List<ClassTeacherMap> findMapByMember(Member member) {
		String jpql = "select classTeacherMap from ClassTeacherMap classTeacherMap where 1=1 and classTeacherMap.member = :member";
        List<ClassTeacherMap> classTeacherMaps = null;
        try
        {
            TypedQuery<ClassTeacherMap> flushModel = entityManager.createQuery(jpql, ClassTeacherMap.class).setFlushMode(
                    FlushModeType.COMMIT);
            flushModel.setParameter("member", member);
            classTeacherMaps = flushModel.getResultList();
        }
        catch (Exception e)
        {
            logger.error("查询班级教师关联表错误：" + e.getMessage());
        }

        return classTeacherMaps;
	}

	@Override
	public List<DictClass> findClassesByMember(Member member) {
		String jpql = "select classTeacherMap.dictClass from ClassTeacherMap classTeacherMap where 1=1 and classTeacherMap.member = :member";
        List<DictClass> dictClasses = null;
        try
        {
            TypedQuery<DictClass> flushModel = entityManager.createQuery(jpql, DictClass.class).setFlushMode(
                    FlushModeType.COMMIT);
            flushModel.setParameter("member", member);
            dictClasses = (List<DictClass>) flushModel.getResultList();
        }
        catch (Exception e)
        {
            logger.error("查询错误findClassesByMember：" + e.getMessage());
        }

        return dictClasses;
	}
}
