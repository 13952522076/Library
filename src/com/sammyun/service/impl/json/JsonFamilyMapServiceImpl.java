package com.sammyun.service.impl.json;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.sammyun.dao.json.JsonFamilyMapDao;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.attendance.TimeCard;
import com.sammyun.entity.attendance.TimeCard.CardStatus;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.dict.PatriarchStudentMap;
import com.sammyun.entity.json.JsonFamilyMap;
import com.sammyun.service.MemberService;
import com.sammyun.service.attendance.TimeCardService;
import com.sammyun.service.dict.PatriarchStudentMapService;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.json.JsonFamilyMapService;
import com.sammyun.util.ImUserUtil;
import com.sammyun.util.SpringUtils;

/**
 * JsonFamilyMap * ServiceImpl - 家庭的JSON信息
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Service("jsonFamilyMapServiceImpl")
public class JsonFamilyMapServiceImpl extends BaseServiceImpl<JsonFamilyMap, Long> implements JsonFamilyMapService 
{
    @Resource(name = "jsonFamilyMapDaoImpl")
    private JsonFamilyMapDao jsonFamilyMapDao;

    @Resource(name = "jsonFamilyMapDaoImpl")
    public void setBaseDao(JsonFamilyMapDao jsonFamilyMapDao){
        super.setBaseDao(jsonFamilyMapDao);
    }
    
    @Resource(name = "memberServiceImpl")
    private MemberService memberService;
    
    @Resource(name = "timeCardServiceImpl")
    private TimeCardService timeCardService;
    
    @Resource(name = "patriarchStudentMapServiceImpl")
    private PatriarchStudentMapService patriarchStudentMapService;
    

    @Override
    public List<JsonFamilyMap> findBySchool(Long dictSchoolId, Date modifyDate)
    {
        return jsonFamilyMapDao.findBySchool(dictSchoolId, modifyDate);
    }

    @Override
    public JsonFamilyMap findByFamilyId(Long familyId, MemberType memberType)
    {
        return jsonFamilyMapDao.findByFamilyId(familyId, memberType);
    }

    @Override
    public void createData(List<Long> memberIds)
    {
        ImUserUtil imUserUtil = new ImUserUtil();
        for (Long memberId : memberIds)
        {
            Member member = memberService.find(memberId);
            JSONObject outData = new JSONObject();
            Long familyId = null;

            if (member == null)
            {
                continue;
            }

            MemberType memberType = member.getMemberType();
            if (memberType == null)
            {
                continue;
            }
            
            if (memberType.equals(MemberType.patriarch))
            {
                for (PatriarchStudentMap patriarchStudentMap : member.getPatriarchStudentMap())
                {
                    DictStudent dictStudent = patriarchStudentMap.getDictStudent();
                    List<TimeCard> timeCards = timeCardService.findByMember(member, dictStudent, null);
                    List<PatriarchStudentMap> patriarchStudentMaps = patriarchStudentMapService.findByStudent(dictStudent);

                    if (timeCards != null && timeCards.size() != 0)
                    {
                        // 获取卡号
                        JSONArray cardNumbers = new JSONArray(); 
                        for (TimeCard timeCard : timeCards)
                        {
                            JSONObject cardNumber = new JSONObject();
                            cardNumber.put("cardNumber",timeCard.getCardNumber());
                            cardNumber.put("cardStatus",timeCard.getCardStatus());
                            cardNumbers.put(cardNumber);
                        }

                        // 查找家庭成员
                        JSONArray familyMembers = new JSONArray(); 
                        for (PatriarchStudentMap item : patriarchStudentMaps)
                        {
                            if (item.equals(patriarchStudentMap))
                            {
                                continue;
                            }
                            else
                            {
                                JSONObject familyMember = new JSONObject();
                                familyMember.put("realName", item.getMember().getRealName());  
                                familyMember.put("iconPhoto", imUserUtil.getDefaultImageUrl(item.getMember().getIconPhoto()));  
                                familyMembers.put(familyMember);
                            }
                        }
                        if (patriarchStudentMap.getType() != null)
                        {
                            outData.put("typeName", SpringUtils.getMessage("PatriarchStudentMap."
                                    + patriarchStudentMap.getType()));
                        }
                        outData.put("id", dictStudent.getId());
                        outData.put("cardNumbers", cardNumbers);
                        outData.put("dictClassName", dictStudent.getDictClass().getName());
                        outData.put("iconPhoto", imUserUtil.getDefaultImageUrl(member.getIconPhoto()));
                        outData.put("memberType", member.getMemberType());
                        outData.put("realName", member.getRealName());
                        outData.put("studentIconPhoto", imUserUtil.getDefaultImageUrl(dictStudent.getIconPhoto()));
                        outData.put("studentName",dictStudent.getStudentName());
                        outData.put("familyMembers", familyMembers);
                        familyId = dictStudent.getId();
                        createJsonFamilyMap(familyId,outData.toString(),member);
                    }
                }
            }
            else if (memberType.equals(MemberType.teacher))
            {
                List<TimeCard> timeCards = timeCardService.findByMember(member, null,null);
                if (timeCards != null && timeCards.size() != 0)
                {
                 // 获取卡号
                    JSONArray cardNumbers = new JSONArray(); 
                    for (TimeCard timeCard : timeCards)
                    {
                        JSONObject cardNumber = new JSONObject();
                        cardNumber.put("cardNumber",timeCard.getCardNumber());
                        cardNumber.put("cardStatus",timeCard.getCardStatus());
                        cardNumbers.put(cardNumber);
                    }

                    outData.put("cardNumbers", cardNumbers);
                    outData.put("id", member.getId());
                    outData.put("iconPhoto", imUserUtil.getDefaultImageUrl(member.getIconPhoto()));
                    outData.put("memberType", member.getMemberType());
                    outData.put("realName", member.getRealName());
                    familyId = member.getId();
                    createJsonFamilyMap(familyId,outData.toString(),member);
                }
            }
        }
        
    }
    
    /**
     * 构建JsonFamilyMap
     * <功能详细描述>
     * @param familyId
     * @param json
     * @param member
     * @see [类、类#方法、类#成员]
     */
    public void createJsonFamilyMap(Long familyId,String json,Member member){
        
        if (familyId == null)
        {
            return;
        }
        JsonFamilyMap jsonFamilyMap = this.findByFamilyId(familyId, member.getMemberType());
        if (jsonFamilyMap == null)
        {
            JsonFamilyMap temp = new JsonFamilyMap();
            temp.setDictSchoolId(member.getDictSchool().getId());
            temp.setFamilyId(familyId);
            temp.setJson(json);
            temp.setMemberType(member.getMemberType());
            this.save(temp);
        }
        else
        {
            jsonFamilyMap.setJson(json);
            this.update(jsonFamilyMap);
        }
    }
}
