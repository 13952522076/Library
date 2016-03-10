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
import com.sammyun.util.SpringUtils;

/**
 * JsonFamilyMap * ServiceImpl - 家庭的JSON信息
 * 
 * @author Sencloud Team

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
