package com.sammyun.service.impl.attendance;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.attendance.WorkSchedulingDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.WorkScheduling;
import com.sammyun.service.attendance.WorkSchedulingService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 排班管理
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("workSchedulingServiceImpl")
public class WorkSchedulingServiceImpl extends BaseServiceImpl<WorkScheduling, Long> implements WorkSchedulingService
{
    @Resource(name = "workSchedulingDaoImpl")
    private WorkSchedulingDao workSchedulingDao;
    
    @Resource(name = "workSchedulingDaoImpl")
    public void setBaseDao(WorkSchedulingDao workSchedulingDao){
        super.setBaseDao(workSchedulingDao);
    }

    @Override
    public List<WorkScheduling> findWorkScheduling(Member member)
    {
        return workSchedulingDao.findWorkScheduling(member);
    }
    public List<Member> findMembers()
    {
        // TODO Auto-generated method stub
        return workSchedulingDao.findMembers();
    }
}
