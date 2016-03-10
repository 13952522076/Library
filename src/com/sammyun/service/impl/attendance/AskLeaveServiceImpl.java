package com.sammyun.service.impl.attendance;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.attendance.AskLeaveDao;
import com.sammyun.entity.attendance.AskLeave;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.service.attendance.AskLeaveService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 请假
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("askLeaveServiceImpl")
public class AskLeaveServiceImpl extends BaseServiceImpl<AskLeave, Long> implements AskLeaveService
{ 
    @Resource(name = "askLeaveDaoImpl")
    private AskLeaveDao askLeaveDao;
    
    @Resource(name = "askLeaveDaoImpl")
    public void setBaseDao(AskLeaveDao askLeaveDao){
        super.setBaseDao(askLeaveDao);
    }

    @Override
    public List<AskLeave> findByStudents(List<DictStudent> students)
    {
        // TODO Auto-generated method stub
        return askLeaveDao.findByStudents(students);
    }
}
