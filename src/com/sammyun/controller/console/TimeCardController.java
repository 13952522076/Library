package com.sammyun.controller.console;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sammyun.entity.Member;
import com.sammyun.entity.attendance.TimeCard;
import com.sammyun.entity.attendance.TimeCard.CardStatus;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.service.MemberService;
import com.sammyun.service.attendance.TimeCardService;
import com.sammyun.service.dict.DictStudentService;
import com.sammyun.service.dict.PatriarchStudentMapService;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 考勤卡管理
 * 
 * @author xutianlong
 * @version [版本号, Apr 22, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("adminTimeCardController")
@RequestMapping("/console/timeCard")
public class TimeCardController extends BaseController
{
    @Resource(name = "timeCardServiceImpl")
    private TimeCardService timeCardService;

    @Resource(name = "dictStudentServiceImpl")
    private DictStudentService dictStudentService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "patriarchStudentMapServiceImpl")
    private PatriarchStudentMapService patriarchStudentMapService;

    /**
     * ajax通过学生获得相关考勤卡
     * 
     * @param id
     * @param response
     */
    @RequestMapping(value = "/getCards", method = RequestMethod.GET)
    public void getCards(Long id, HttpServletResponse response)
    {
        DictStudent dictStudent = dictStudentService.find(id);
        List<TimeCard> cards = timeCardService.findByStudent(dictStudent);
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), cards);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * ajax通过学生获得相关考勤卡
     * 
     * @param id
     * @param response
     */
    @RequestMapping(value = "/getCardsByMember", method = RequestMethod.GET)
    public void getCardsByMember(Long id, HttpServletResponse response)
    {
        Member member = memberService.find(id);
        List<TimeCard> cards = timeCardService.findByPatriarch(member);
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), cards);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * ajax通过卡号，家长ID，学生id添加卡
     * 
     * @param id
     * @param response
     */
    @RequestMapping(value = "/addCardMember", method = RequestMethod.GET)
    public void addCardMember(Long studentId, String cardNumber, Long memberId, HttpServletResponse response)
    {
        //判断卡号是否存在
        if(timeCardService.timeCardExsit(cardNumber)){
            try
            {
                response.setContentType("text/html; charset=UTF-8");
                JsonUtils.writeValue(response.getWriter(), "卡号已存在！");
                return;
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return;
            }
        }
        
        DictStudent dictStudent = dictStudentService.find(studentId);
        Member member = memberService.find(memberId);
      /*  //判断当前家长学生是否拥有正常状态的卡
        if(timeCardService.patriarchStudentNormalExsit(member, dictStudent)){
            try
            {
                response.setContentType("text/html; charset=UTF-8");
                JsonUtils.writeValue(response.getWriter(), "当前学生存在正常卡！");
                return;
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return;
            }
        }*/
        TimeCard timeCard = new TimeCard();
        timeCard.setCardNumber(cardNumber);
        timeCard.setCardStatus(CardStatus.normal);
        timeCard.setDictStudent(dictStudent);
        timeCard.setMember(member);
        try
        {
            timeCardService.save(timeCard);
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), "success");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * ajax通过学生获取家长
     * 
     * @param id
     * @param response
     */
    @RequestMapping(value = "/getPatriarches", method = RequestMethod.GET)
    public void getPatriarches(Long id, HttpServletResponse response)
    {
        DictStudent dictStudent = dictStudentService.find(id);
        List<Member> patriarches = patriarchStudentMapService.findMemberByStudent(dictStudent);
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), patriarches);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * <一句话功能简述>
     * 
     * @param id
     * @param response
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getDictStudents", method = RequestMethod.GET)
    public void getDictStudents(Long id, HttpServletResponse response)
    {
        // DictStudent dictStudent = dictStudentService.find(id);
        // List<Member> patriarches =
        // patriarchStudentMapService.findMemberByStudent(dictStudent);
        Member member = memberService.find(id);
        List<DictStudent> dictStudents = patriarchStudentMapService.findStudentByPatriarch(member);
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), dictStudents);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 通过学生判断正常卡片的数目
     */
    @RequestMapping(value = "/getNormalByStudent", method = RequestMethod.GET)
    public void getNormalByStudent(Long id, HttpServletResponse response)
    {
        DictStudent dictStudent = dictStudentService.find(id);
        List<TimeCard> cards = timeCardService.findByStudent(dictStudent);
        if (cards == null || cards.size() == 0)
        {
            try
            {
                response.setContentType("text/html; charset=UTF-8");
                JsonUtils.writeValue(response.getWriter(), 0);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            int num = 0;
            for (TimeCard card : cards)
            {
                if (card.getCardStatus() == CardStatus.normal)
                {
                    num += 1;
                }
            }
            try
            {
                response.setContentType("text/html; charset=UTF-8");
                JsonUtils.writeValue(response.getWriter(), num);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过学生判断正常卡片的数目
     */
    @RequestMapping(value = "/getNormalByPatriarch", method = RequestMethod.GET)
    public void getNormalByPatriarch(Long id, HttpServletResponse response)
    {
        Member member = memberService.find(id);
        List<TimeCard> cards = timeCardService.findByPatriarch(member);
        if (cards == null || cards.size() == 0)
        {
            try
            {
                response.setContentType("text/html; charset=UTF-8");
                JsonUtils.writeValue(response.getWriter(), 0);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            int num = 0;
            for (TimeCard card : cards)
            {
                if (card.getCardStatus() == CardStatus.normal)
                {
                    num += 1;
                }
            }
            try
            {
                response.setContentType("text/html; charset=UTF-8");
                JsonUtils.writeValue(response.getWriter(), num);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param id
     * @param response
     */
    @RequestMapping(value = "/cardChangeStatus", method = RequestMethod.GET)
    public void cardChangeStatus(Long carId, CardStatus status, HttpServletResponse response)
    {
        TimeCard timeCard = timeCardService.find(carId);
        if (timeCard != null)
        {
            timeCard.setCardStatus(status);
            timeCardService.update(timeCard);
        }
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), "success");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    /**
     * ajax通过卡号，家长ID，学生id添加卡
     * 
     * @param id
     * @param response
     */
    @RequestMapping(value = "/addCardByTeacher", method = RequestMethod.GET)
    public void addCardByTeacher(String cardNumber, Long memberId, HttpServletResponse response)
    {
        //判断卡号是否存在
        if(timeCardService.timeCardExsit(cardNumber)){
            try
            {
                response.setContentType("text/html; charset=UTF-8");
                JsonUtils.writeValue(response.getWriter(), "卡号已存在！");
                return;
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return;
            }
        }
        
        Member member = memberService.find(memberId);
        TimeCard timeCard = new TimeCard();
        timeCard.setCardNumber(cardNumber);
        timeCard.setCardStatus(CardStatus.normal);
        timeCard.setMember(member);
        try
        {
            timeCardService.save(timeCard);
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), "success");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    
    @RequestMapping(value = "/cardChangeValidate", method = RequestMethod.GET)
    public void cardChangeValidate(Long carId, HttpServletResponse response)
    {
        TimeCard timeCard = timeCardService.find(carId);
        Member member = timeCard.getMember();
        DictStudent dictStudent = timeCard.getDictStudent();
        Boolean bool = timeCardService.patriarchStudentNormalExsit(member, dictStudent); 
        try
        {
            timeCardService.save(timeCard);
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), bool);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
