package com.sammyun.dao.impl.attendance;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.attendance.AskLeaveDao;
import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.entity.attendance.AskLeave;
import com.sammyun.entity.dict.DictStudent;

/**
 * DaoImpl - 请假
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("askLeaveDaoImpl")
public class AskLeaveDaoImpl extends BaseDaoImpl<AskLeave, Long> implements AskLeaveDao
{

    @Override
    public List<AskLeave> findByStudents(List<DictStudent> students)
    {
        // TODO Auto-generated method stub
        if(students==null){
            return null;
        }
        if(students.size()==0){
            return null;
        }
        String jpql = "select askLeave from AskLeave askLeave where 1=1 ";
        jpql = jpql + "and askLeave.dictStudent in :students ";
        TypedQuery<AskLeave> flushModel = entityManager.createQuery(jpql, AskLeave.class).setFlushMode(
                FlushModeType.COMMIT);
        flushModel.setParameter("students", students);
        return flushModel.getResultList();
    }

}
