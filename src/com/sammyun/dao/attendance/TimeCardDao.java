package com.sammyun.dao.attendance;

import java.util.List;

import com.sammyun.dao.BaseDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.TimeCard;
import com.sammyun.entity.attendance.TimeCard.CardStatus;
import com.sammyun.entity.dict.DictStudent;

/**
 * Dao - 卡管理
 * 


 */ 
public interface TimeCardDao  extends BaseDao<TimeCard, Long>
{
    /**
     * 通过卡号查找
     * <功能详细描述>
     * @param cardNumber
     * @return
     * @see [类、类#方法、类#成员]
     */
    public TimeCard findByCardNumber(String cardNumber,CardStatus cardStatus);
    
    /**
     * 通过学生和家长查询卡
     * <功能详细描述>
     * @param member
     * @param dictStudent
     * @param cardStatus
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<TimeCard> findByMember(Member member,DictStudent dictStudent,CardStatus cardStatus);
    
    /**
     * 通过学生查询相关卡
     * @param dictStudent
     * @return
     */
    public List<TimeCard> findByStudent(DictStudent dictStudent);
    
    /**
     * 通过家长查询相关卡
     * @param member
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<TimeCard> findByPatriarch(Member member);
    
    
    /**
     * 判断卡号是否存在
     * <功能详细描述>
     * @param cardNumber
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Boolean timeCardExsit(String cardNumber);
    
    /**
     * 一个家长一个学生对应的正常卡的验证
     * <功能详细描述>
     * @param member
     * @param dictStudent
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Boolean patriarchStudentNormalExsit(Member member,DictStudent dictStudent);
    
    /**
     * 查询老师可用的卡
     * <功能详细描述>
     * @param member
     * @param cardStatus
     * @return
     * @see [类、类#方法、类#成员]
     */
//    public List<TimeCard> findByMember(Member member,CardStatus cardStatus);
    
}
