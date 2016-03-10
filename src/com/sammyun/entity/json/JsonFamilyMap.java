package com.sammyun.entity.json;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;
import com.sammyun.entity.Member.MemberType;

/**
 * Entity -家庭的JSON信息表， <br />
 * 当用户信息（包含学生、家长、老师）发生变更的时候，把这个用户的家庭信息更新到当前白哦
 *  {
        "cardNumbers" : [
          "1330660496",
          "1330814736"
        ],
        "realName" : "姜秀华",
        "id" : 1006,
        "iconPhoto" : "",
        "studentIconPhoto" : "http://120.25.148.27/upload/image/201505/dce36f76-7a6c-4ecd-bd15-776158214037.jpg",
        "dictClassName" : "小2班",
        "memberType" : "patriarch",
        "studentName" : "姜宇泽",
        "familyMembers" : [
             {
                "iconPhoto" : "",
                  "realName" : "姜秀华"
                },
                  {
                   "iconPhoto" : "",
                  "realName" : "姜秀华"
                }
           ]
      }
 * @author xutianlong
 * @version [版本号, Jul 11, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_json_family_map")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_json_family_map_sequence")
public class JsonFamilyMap extends BaseEntity
{
    /**
     * 注释内容
     */
    private static final long serialVersionUID = -1141167040076337591L;

    /** 隶属学校 */
    private Long dictSchoolId;

    /** 如果memberType是老师，familyId对应memberId，如果memberType是家长，familyId对应学生的id */
    private Long familyId;

    /** 角色属性 */
    private MemberType memberType;

    /** 构建单个账号JSON数据 
     * */
    private String json;

    /**
     * @return 返回 dictSchoolId
     */

    public Long getDictSchoolId()
    {
        return dictSchoolId;
    }

    /**
     * @param 对dictSchoolId进行赋值
     */
    public void setDictSchoolId(Long dictSchoolId)
    {
        this.dictSchoolId = dictSchoolId;
    }

    
    /**
     * @return 返回 familyId
     */

    public Long getFamilyId()
    {
        return familyId;
    }

    /**
     * @param 对familyId进行赋值
     */
    public void setFamilyId(Long familyId)
    {
        this.familyId = familyId;
    }    

    /**
     * @return 返回 memberType
     */
    public MemberType getMemberType()
    {
        return memberType;
    }

    /**
     * @param 对memberType进行赋值
     */
    public void setMemberType(MemberType memberType)
    {
        this.memberType = memberType;
    }

    /**
     * @return 返回 json
     */
    public String getJson()
    {
        return json;
    }

    /**
     * @param 对json进行赋值
     */
    public void setJson(String json)
    {
        this.json = json;
    }

}
