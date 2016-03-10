package com.sammyun.controller.api;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.sammyun.controller.api.block.FriendsInfoBlock;
import com.sammyun.controller.api.block.FriendsListBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.dict.ClassTeacherMap;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictClass.ClassStatus;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.dict.PatriarchStudentMap;
import com.sammyun.service.MemberService;
import com.sammyun.service.dict.ClassTeacherMapService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictStudentService;
import com.sammyun.service.dict.PatriarchStudentMapService;
import com.sammyun.util.SpringUtils;

/**
 * api - 好友
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("friends")
@Path("/friends")
public class FriendsApi
{

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "patriarchStudentMapServiceImpl")
    private PatriarchStudentMapService patriarchStudentMapService;

    @Resource(name = "classTeacherMapServiceImpl")
    private ClassTeacherMapService classTeacherMapService;

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    @Resource(name = "dictStudentServiceImpl")
    private DictStudentService dictStudentService;

    /**
     * 查询好友列表 <功能详细描述>
     * 
     * @param memberId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/list/{memberId}")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel list(@PathParam("memberId") Long memberId)
    {

        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<FriendsListBlock> rows = new LinkedList<FriendsListBlock>();
        List<DictClass> dictClasses = new ArrayList<DictClass>();
        Set<DictClass> dictClassSets = new HashSet<DictClass>();
        List<Member> members = new LinkedList<Member>();
        Set<Member> memberSets = new HashSet<Member>();

        if (memberId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误！");
            return listRestFulModel;
        }

        Member member = memberService.find(memberId);
        if (member == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("shop.login.unknownAccount"));
            return listRestFulModel;
        }
        if (member.getMemberType().equals(MemberType.patriarch))
        {
            List<PatriarchStudentMap> patriarchStudentMaps = patriarchStudentMapService.findStudentByMember(member);

            if (patriarchStudentMaps == null || patriarchStudentMaps.size() == 0)
            {
                listRestFulModel.setResultCode(1);
                listRestFulModel.setResultMessage("没有相关数据！");
                return listRestFulModel;
            }

            // 教师列表
            for (PatriarchStudentMap patriarchStudentMap : patriarchStudentMaps)
            {
                DictStudent dictStudent = patriarchStudentMap.getDictStudent();
                DictClass dictClass = dictStudent.getDictClass();
                for (ClassTeacherMap classTeacherMap : dictClass.getClassTeacherMap())
                {
                    Member tempMember = classTeacherMap.getMember();
                    if (tempMember != null)
                    {
                        memberSets.add(classTeacherMap.getMember());
                    }
                }

                if (dictClass.getDictStudents() == null || dictClass.getDictStudents().size() == 0)
                {
                    continue;
                }
                else
                {
                    dictClassSets.add(dictClass);
                }
            }
            members.addAll(memberSets);
        }
        else if (member.getMemberType().equals(MemberType.teacher))
        {
            List<ClassTeacherMap> classTeacherMaps = classTeacherMapService.findClassesBySchoolAndMember(
                    member.getDictSchool(), member, ClassStatus.active);
            members = memberService.findBySchoolAndType(member.getDictSchool(), MemberType.teacher);

            if ((classTeacherMaps == null || classTeacherMaps.size() == 0) && (members == null || members.size() == 0))
            {

                listRestFulModel.setResultCode(1);
                listRestFulModel.setResultMessage("没有相关数据！");
                return listRestFulModel;
            }

            // 家长列表
            for (ClassTeacherMap classTeacherMap : classTeacherMaps)
            {
                DictClass dictClass = classTeacherMap.getDictClass();
                if (dictClass.getDictStudents() == null || dictClass.getDictStudents().size() == 0)
                {
                    continue;
                }
                else
                {
                    dictClassSets.add(dictClass);
                }
            }
        }
        // 老师列表
        rows.add(teacherList(members));
        // 家长列表
        dictClasses.addAll(dictClassSets);
        newSort(dictClasses);
        for (DictClass dictClass : dictClasses)
        {
            rows.add(patriarchList(dictClass));
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("获取好友列表成功!");
        return listRestFulModel;
    }

    /**
     * 重写sort方法，对栏目排序
     * 
     * @param productCategoryList 栏目列表
     */
    public void newSort(List<DictClass> dictClasses)
    {
        Collections.sort(dictClasses, new Comparator<DictClass>()
        {
            public int compare(DictClass arg0, DictClass arg1)
            {
                Integer order1 = Integer.parseInt(arg0.getCode());
                Integer order2 = Integer.parseInt(arg1.getCode());
                return order1.compareTo(order2);
            }
        });
    }

    /**
     * 老师列表 <功能详细描述>
     * 
     * @param members
     * @return
     * @see [类、类#方法、类#成员]
     */
    public FriendsListBlock teacherList(List<Member> members)
    {
        FriendsListBlock teacher = new FriendsListBlock();
        teacher.setName("教师列表");
        List<FriendsInfoBlock> teachers = new LinkedList<FriendsInfoBlock>();
        for (Member tempMember : members)
        {
            teachers.add(memberInfo(tempMember, null));
        }
        teacher.setFriends(teachers);
        return teacher;
    }

    /**
     * 家长列表 <功能详细描述>
     * 
     * @param patriarchStudentMap
     * @return
     * @see [类、类#方法、类#成员]
     */
    public FriendsListBlock patriarchList(DictClass dictClass)
    {
        FriendsListBlock patriarch = new FriendsListBlock();
        List<FriendsInfoBlock> patriarchs = new LinkedList<FriendsInfoBlock>();
        JSONArray jsonStrs = new JSONArray();
        Set<Member> memberSets = new HashSet<Member>();

        patriarch.setName(dictClass.getName());
        for (DictStudent tempDictStudent : dictClass.getDictStudents())
        {
            for (PatriarchStudentMap tempPatriarchStudentMap : tempDictStudent.getPatriarchStudentMap())
            {
                Member tempMember = tempPatriarchStudentMap.getMember();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put(tempMember.getId().toString(), tempDictStudent.getStudentName());
                jsonStrs.put(jsonObject);
                if (tempMember != null)
                {
                    memberSets.add(tempMember);
                }
            }
        }
        for (Member member : memberSets)
        {
            patriarchs.add(memberInfo(member, jsonStrs));
        }
        patriarch.setFriends(patriarchs);
        return patriarch;
    }

    /**
     * 设置返回的member信息 <功能详细描述>
     * 
     * @param member
     * @return
     * @see [类、类#方法、类#成员]
     */
    public FriendsInfoBlock memberInfo(Member member, JSONArray jsonStrs)
    {
        FriendsInfoBlock friendsInfoBlock = new FriendsInfoBlock();
        friendsInfoBlock.setId(member.getId());
        friendsInfoBlock.setRealName(member.getRealName());
        friendsInfoBlock.setIconPhoto(member.getIconPhoto());
        friendsInfoBlock.setMobile(member.getMobile());
        String identity = null;
        if (member.getMemberType().equals(MemberType.patriarch))
        {
            if (jsonStrs != null)
            {
                for (int i = 0; i < jsonStrs.length(); i++)
                {
                    JSONObject jsonObject = jsonStrs.getJSONObject(i);
                    Iterator iter = jsonObject.keys();
                    while (iter.hasNext())
                    {
                        String key = iter.next().toString();
                        String value = jsonObject.optString(key);
                        if (key.equalsIgnoreCase(member.getId().toString()))
                        {
                            if (identity != null)
                            {
                                identity += "、";
                                identity += value;
                            }
                            else
                            {
                                identity = value;
                            }
                            continue;
                        }
                        else
                        {
                            continue;
                        }
                    }
                }
                identity += "的家长";
            }
        }
        else if (member.getMemberType().equals(MemberType.teacher))
        {
            identity = member.getRealName().toString() + "老师";
        }
        friendsInfoBlock.setIdentity(identity);
        return friendsInfoBlock;
    }

}
