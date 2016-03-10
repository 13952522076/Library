package com.sammyun.service.impl.attendance;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.attendance.TimeCardDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.TimeCard;
import com.sammyun.entity.attendance.TimeCard.CardStatus;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.service.attendance.TimeCardService;
import com.sammyun.service.impl.BaseServiceImpl;

/**
 * ServiceImpl - 卡管理
 * 
 * @author Sencloud Team

 */
@Service("timeCardServiceImpl")
public class TimeCardServiceImpl extends BaseServiceImpl<TimeCard, Long> implements TimeCardService
{
    @Resource(name = "timeCardDaoImpl")
    private TimeCardDao timeCardDao;
    
    @Resource(name = "timeCardDaoImpl")
    public void setBaseDao(TimeCardDao timeCardDao){
        super.setBaseDao(timeCardDao);
    }

    @Override
    public TimeCard findByCardNumber(String cardNumber,CardStatus cardStatus)
    {
        return timeCardDao.findByCardNumber(cardNumber,cardStatus);
    }

    @Override
    public List<TimeCard> findByMember(Member member, DictStudent dictStudent, CardStatus cardStatus)
    {
        return timeCardDao.findByMember(member, dictStudent, cardStatus);
    }

	@Override
	public List<TimeCard> findByStudent(DictStudent dictStudent) {
		// TODO Auto-generated method stub
		return timeCardDao.findByStudent(dictStudent);
	}

    @Override
    public List<TimeCard> findByPatriarch(Member member)
    {
        // TODO Auto-generated method stub
        return timeCardDao.findByPatriarch(member);
    }

    @Override
    public Boolean timeCardExsit(String cardNumber)
    {
        // TODO Auto-generated method stub
        return timeCardDao.timeCardExsit(cardNumber);
    }

    @Override
    public Boolean patriarchStudentNormalExsit(Member member, DictStudent dictStudent)
    {
        // TODO Auto-generated method stub
        return timeCardDao.patriarchStudentNormalExsit(member, dictStudent);
    }
    
//    @Override
//    public List<TimeCard> findByMember(Member member, CardStatus cardStatus)
//    {
//        return timeCardDao.findByMember(member, cardStatus);
//    }
}
