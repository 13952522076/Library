package com.sammyun.dao.impl.attendance;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.dao.attendance.TeacherAttendanceDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.TeacherAskLeave;
import com.sammyun.entity.attendance.TeacherAttendance;

/**
 * DaoImpl - 教师考勤
 * 
 * @author Sencloud Team

 */
@Repository("teacherAttendanceDaoImpl")
public class TeacherAttendanceDaoImpl extends BaseDaoImpl<TeacherAttendance, Long> implements TeacherAttendanceDao
{

    @Override
    public List<TeacherAttendance> findTeacherAttendance(Member member,Date beginDate, Date endDate)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TeacherAttendance> criteriaQuery = criteriaBuilder.createQuery(TeacherAttendance.class);
        Root<TeacherAttendance> root = criteriaQuery.from(TeacherAttendance.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (member != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("member"), member));
        }

        if (beginDate != null && endDate != null)
        {
            Subquery<TeacherAttendance> subquery1 = criteriaQuery.subquery(TeacherAttendance.class);
            Root<TeacherAttendance> subqueryRoot1 = subquery1.from(TeacherAttendance.class);
            subquery1.select(subqueryRoot1);
            subquery1.where(criteriaBuilder.equal(subqueryRoot1, root), criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(subqueryRoot1.<Date> get("workSwipeTime"), beginDate),
                    criteriaBuilder.lessThanOrEqualTo(subqueryRoot1.<Date> get("workSwipeTime"), endDate)));

            Subquery<TeacherAttendance> subquery3 = criteriaQuery.subquery(TeacherAttendance.class);
            Root<TeacherAttendance> subqueryRoot3 = subquery3.from(TeacherAttendance.class);
            subquery3.select(subqueryRoot3);
            subquery3.where(criteriaBuilder.equal(subqueryRoot3, root), criteriaBuilder.and(
                    criteriaBuilder.greaterThanOrEqualTo(subqueryRoot3.<Date> get("closingSwipeTime"), beginDate),
                    criteriaBuilder.lessThanOrEqualTo(subqueryRoot3.<Date> get("closingSwipeTime"), endDate)));

            restrictions = criteriaBuilder.and(restrictions,
                    criteriaBuilder.or(criteriaBuilder.exists(subquery1), criteriaBuilder.exists(subquery3)));
        }
        criteriaQuery.where(restrictions);
        return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
    }

    @Override
    public List<TeacherAttendance> findByMember(Member member)
    {
        if (member == null)
        {
            return null;
        }
        String jpql = "select teacherAttendance from TeacherAttendance teacherAttendance where teacherAttendance.member = :member";
        return entityManager.createQuery(jpql, TeacherAttendance.class).setFlushMode(FlushModeType.COMMIT).setParameter(
                "member", member).getResultList();
    }

    @Override
    public Page<TeacherAttendance> findList(Member member, Date beginDate, Date endDate, Pageable pageable)
    {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TeacherAttendance> criteriaQuery = criteriaBuilder.createQuery(TeacherAttendance.class);
        Root<TeacherAttendance> root = criteriaQuery.from(TeacherAttendance.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (member != null)
        {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("member"), member));
        }
        if (beginDate != null) {
            restrictions = criteriaBuilder.and(
                    restrictions,
                    criteriaBuilder.greaterThanOrEqualTo(
                            root.<Date> get("workSwipeTime"), beginDate));
        }
        if (endDate != null) {
            restrictions = criteriaBuilder.and(
                    restrictions,
                    criteriaBuilder.lessThanOrEqualTo(
                            root.<Date> get("workSwipeTime"), endDate));
        }
        criteriaQuery.where(restrictions);
        return super.findPage(criteriaQuery, pageable);
    }
    
    @Override
    public Long findAttendanceCount(Member member,int year,int month)
    {
        if (member == null)
        {
            return null;
        }
	    String jpql = "select count(*) from TeacherAttendance teacherAttendance where lower(TeacherAttendance.member) = lower(:member) "
	        	    + "and (month(lower(TeacherAttendance.workSwipeTime)) = lower(:month) and year(lower(TeacherAttendance.workSwipeTime)) = lower(:year)"
	        	    + "and month(lower(TeacherAttendance.closingSwipeTime)) = lower(:month) and year(lower(TeacherAttendance.closingSwipeTime)) = lower(:year))";
	    Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("member", member)
	        	    .setParameter("month",month).setParameter("year",year).getSingleResult();
	    return count;
    }
    
    @Override
    public List<Date> findAttendanceDate(Member member,int year,int month)
    {
        if (member == null)
        {
            return null;
        }
        String jpql = "select TeacherAttendance.closingSwipeTime from TeacherAttendance teacherAttendance where lower(TeacherAttendance.member) = lower(:member) "
       			+ "and lower(TeacherAttendance.workStatus) != lower(:workStatus) and (month(lower(TeacherAttendance.workSwipeTime)) = lower(:month)  and year(lower(TeacherAttendance.workSwipeTime)) = lower(:year)) "
       			+ "and (month(lower(TeacherAttendance.closingSwipeTime)) = lower(:month) and year(lower(TeacherAttendance.closingSwipeTime)) = lower(:year))";
        return entityManager.createQuery(jpql, Date.class).setFlushMode(FlushModeType.COMMIT).setParameter("member", member).setParameter("workStatus",4)
				.setParameter("month",month).setParameter("year",year).getResultList();
    }
    
    @Override
    public List<TeacherAskLeave> findAskLeave(Member member,Date minDate,Date maxDate)
    {
    	 List<TeacherAskLeave> leaveList=new ArrayList<TeacherAskLeave>();
    	 if (member == null)
         {
             return null;
         }
    		String jpql = "select TeacherAskLeave from TeacherAskLeave teacherAskLeave where lower(TeacherAskLeave.leaveMember) = lower(:member) "
    				+ "and lower(TeacherAskLeave.leaveStartDate) <= lower(:maxDate) and lower(TeacherAskLeave.leaveEndDate) >= lower(:minDate)";
    		leaveList= entityManager.createQuery(jpql, TeacherAskLeave.class).setFlushMode(FlushModeType.COMMIT).setParameter("member", member)
 				.setParameter("minDate",minDate).setParameter("maxDate",maxDate).getResultList();
    		if(leaveList.size()>0)
            {
                return leaveList;
            }
            else{
                return null;
            }
    }
    
    @Override
    public Long findTardinessCount(Member member,TeacherAttendance.Status workStatus,int year,int month)
    {
    	Long count=0L;
    	if (member == null)
    	{
    		return null;
    	}
    	//迟到次数
    	if(workStatus==TeacherAttendance.Status.late){
    		String jpql = "select count(*) from TeacherAttendance teacherAttendance where lower(TeacherAttendance.member) = lower(:member) "
    				    + "and lower(TeacherAttendance.workStatus) = lower(:workStatus) and (month(lower(TeacherAttendance.workSwipeTime)) = lower(:month) "
    				    + "and year(lower(TeacherAttendance.workSwipeTime)) = lower(:year) or month(lower(TeacherAttendance.closingSwipeTime)) = lower(:month) and year(lower(TeacherAttendance.closingSwipeTime)) = lower(:year) )";
    		count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("member", member).setParameter("workStatus",1)
    				.setParameter("month",month).setParameter("year",year).getSingleResult();
    	}
    	//早退次数
    	if(workStatus==TeacherAttendance.Status.early)
    	{
    		String jpql = "select count(*) from TeacherAttendance teacherAttendance where lower(TeacherAttendance.member) = lower(:member) "
    					+ "and lower(TeacherAttendance.workStatus) = lower(:workStatus) and ((month(lower(TeacherAttendance.workSwipeTime)) = lower(:month) "
    					+ "and year(lower(TeacherAttendance.workSwipeTime)) = lower(:year)) or (month(lower(TeacherAttendance.closingSwipeTime)) = lower(:month) and year(lower(TeacherAttendance.closingSwipeTime)) = lower(:year)))";
    	   count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("member", member).setParameter("workStatus",2)
				.setParameter("month",month).setParameter("year",year).getSingleResult();
    	}
    	return count;
    }
    
    @Override
    public Date findLeaveDateStart(Member member,int year,int month)
    {
    	if (member == null)
    	{
    		return null;
    	}
    	//查询请假开始时间在本月，请假结束时间不在本月 的请假开始日期
        String jpql = "select TeacherAskLeave.leaveStartDate from TeacherAskLeave teacherAskLeave where lower(TeacherAskLeave.leaveMember) = lower(:member) "
    			+ "and (month(lower(TeacherAskLeave.leaveStartDate)) = lower(:month)  and year(lower(TeacherAskLeave.leaveStartDate)) = lower(:year)) "
    			+ "and (month(lower(TeacherAskLeave.leaveEndDate)) != lower(:month) or year(lower(TeacherAskLeave.leaveEndDate)) != lower(:year))";
        List<Date> rs=entityManager.createQuery(jpql, Date.class).setFlushMode(FlushModeType.COMMIT).setParameter("member", member)
				.setParameter("month",month).setParameter("year",year).getResultList();
        if(rs.size()>0)
        {
            return rs.get(0);
        }
        else{
            return null;
        }
    }
    
}
