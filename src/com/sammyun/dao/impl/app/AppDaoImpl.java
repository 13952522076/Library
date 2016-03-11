package com.sammyun.dao.impl.app;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.app.AppDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.app.App;
import com.sammyun.entity.app.App.OperatingSystem;
import com.sammyun.entity.app.App.RunType;
import com.sammyun.entity.app.AppCategory;
import com.sammyun.entity.app.AppRole;
import com.sammyun.entity.dict.DictSchool;

/**
 * DaoImpl - 应用管理
 * 


 */
@Repository("appDaoImpl")
public class AppDaoImpl extends BaseDaoImpl<App, Long> implements AppDao
{
    @Override
    public List<App> findByName(String appName)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<App> criteriaQuery = criteriaBuilder.createQuery(App.class);
        Root<App> root = criteriaQuery.from(App.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();

        if (appName != null || appName.length() <= 0)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("appName"), appName));
        }
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery, null, null, null, null);
    }

    @Override
    public List<App> findSeriesApps(Long parentId)
    {

        String jpql = "select apps from App apps where lower(apps.parent) = lower(:parentId) or lower(apps.id) = lower(:parentId) order by apps.createDate asc";
        TypedQuery<App> query = entityManager.createQuery(jpql, App.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "parentId", parentId);
        return query.getResultList();
    }

    @Override
    public Page<App> findPage(Long parentId, Pageable pageable)
    {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<App> criteriaQuery = criteriaBuilder.createQuery(App.class);
        Root<App> root = criteriaQuery.from(App.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();

        if (parentId != null)
        {
            restrictions = criteriaBuilder.and(
                    restrictions,
                    criteriaBuilder.or(criteriaBuilder.equal(root.get("parent").get("id"), parentId),
                            criteriaBuilder.equal(root.get("id"), parentId)));
            // restrictions = criteriaBuilder.and(restrictions,
            // criteriaBuilder
            // .equal(root.get("parent").get("id"), parentId));
        }
        criteriaQuery.where(restrictions);
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate")));
        return super.findPage(criteriaQuery, pageable);
    }

	@Override
	public Page<App> findPage(AppCategory appCategory, Pageable pageable,
			Boolean isOnline, OperatingSystem operatingSystem,
			List<AppRole> appRoles, RunType runType,
			List<DictSchool> dictSchools)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<App> criteriaQuery = criteriaBuilder.createQuery(App.class);
        Root<App> root = criteriaQuery.from(App.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (appCategory != null)
        {
            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.equal(root.get("appCategory"), appCategory));
        }
        
        if (isOnline != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isOnline"), isOnline));
        }
        
		if (operatingSystem != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder
					.equal(root.get("operatingSystem"), operatingSystem));
		}
        
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.isNull(root.get("parent")));
        
        if (appRoles != null) {
			Subquery<App> subquery = criteriaQuery.subquery(App.class);
			Root<App> subqueryRoot = subquery.from(App.class);
			subquery.select(subqueryRoot);
			subquery.where(criteriaBuilder.equal(subqueryRoot, root),
					subqueryRoot.join("appRoles").in(appRoles));
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.exists(subquery));
		}
        
        if (dictSchools != null) {
			Subquery<App> subquery = criteriaQuery.subquery(App.class);
			Root<App> subqueryRoot = subquery.from(App.class);
			subquery.select(subqueryRoot);
			subquery.where(criteriaBuilder.equal(subqueryRoot, root),
					subqueryRoot.join("dictSchools").in(dictSchools));
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.exists(subquery));
		}
        
        if(runType != null){
        	restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("runType"), runType));
        }
        
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }

    @Override
    public Boolean checkAppIdUnique(String appId)
    {
        if ("".equals(appId))
        {
            return false;
        }
        String jpql = "select count(*) from App app where app.appId = :appId";
        Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "appId", appId).getSingleResult();
        return count > 0;
    }

	@Override
	public Page<App> findBySearchKey(String searchKey, Pageable pageable,
			Boolean isOnline, OperatingSystem operatingSystem,
			List<AppRole> appRoles, List<DictSchool> dictSchools)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<App> criteriaQuery = criteriaBuilder.createQuery(App.class);
        Root<App> root = criteriaQuery.from(App.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        restrictions = criteriaBuilder.and(restrictions,
                criteriaBuilder.like(root.<String> get("appName"), "%" + searchKey + "%"));
        if (searchKey != null)
        {
            restrictions = criteriaBuilder.or(restrictions,
                    criteriaBuilder.like(root.<String> get("keyWord"), "%" + searchKey + "%"));
        }
        if (isOnline != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isOnline"), isOnline));
        }
        if (operatingSystem != null)
        {
            if (operatingSystem == OperatingSystem.android)
            {
                restrictions = criteriaBuilder.and(restrictions,
                        criteriaBuilder.notEqual(root.get("operatingSystem"), OperatingSystem.ios));
            }
            if (operatingSystem == OperatingSystem.ios)
            {
                restrictions = criteriaBuilder.and(restrictions,
                        criteriaBuilder.notEqual(root.get("operatingSystem"), OperatingSystem.android));
            }
        }
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.isNull(root.get("parent")));
        
        if (appRoles != null) {
			Subquery<App> subquery = criteriaQuery.subquery(App.class);
			Root<App> subqueryRoot = subquery.from(App.class);
			subquery.select(subqueryRoot);
			subquery.where(criteriaBuilder.equal(subqueryRoot, root),
					subqueryRoot.join("appRoles").in(appRoles));
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.exists(subquery));
		}
        
        if (dictSchools != null) {
			Subquery<App> subquery = criteriaQuery.subquery(App.class);
			Root<App> subqueryRoot = subquery.from(App.class);
			subquery.select(subqueryRoot);
			subquery.where(criteriaBuilder.equal(subqueryRoot, root),
					subqueryRoot.join("dictSchools").in(dictSchools));
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.exists(subquery));
		}
        
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }
    
    @Override
	public boolean appCodeUnique(String appCode) {
		if (appCode == null) {
			return false;
		}
		String jpql = "select count(*) from App apps where lower(apps.appCode) = lower(:appCode)";
		Long count = entityManager.createQuery(jpql, Long.class)
				.setFlushMode(FlushModeType.COMMIT).setParameter("appCode", appCode)
				.getSingleResult();
		return count > 0;
	}

    @Override
    public List<App> findBySearchName(String appName){
    	 if ("".equals(appName))
         {
             return null;
         }
    	 
    	 CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
         CriteriaQuery<App> criteriaQuery = criteriaBuilder.createQuery(App.class);
         Root<App> root = criteriaQuery.from(App.class);
         criteriaQuery.select(root);
         Predicate restrictions = criteriaBuilder.conjunction();
         restrictions = criteriaBuilder.and(restrictions,
                 criteriaBuilder.like(root.<String> get("appName"), "%" + appName + "%"));
         criteriaQuery.where(restrictions);
         return super.findList(criteriaQuery, null, null, null, null);
    }
}
