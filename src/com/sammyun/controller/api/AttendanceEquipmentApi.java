package com.sammyun.controller.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.sammyun.Setting;
import com.sammyun.controller.api.bean.AttendanceEquipmentListBean;
import com.sammyun.controller.api.block.AttendanceEquipmentListBlock;
import com.sammyun.controller.api.block.AttendanceEquipmentListV2Block;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.attendance.AttendanceDetail;
import com.sammyun.entity.attendance.SchoolHours;
import com.sammyun.entity.attendance.TeacherAttendanceDetail;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.json.JsonFamilyMap;
import com.sammyun.plugin.StoragePlugin;
import com.sammyun.service.MemberService;
import com.sammyun.service.attendance.AttendanceDetailService;
import com.sammyun.service.attendance.AttendanceEquipmentService;
import com.sammyun.service.attendance.TeacherAttendanceDetailService;
import com.sammyun.service.attendance.TimeCardService;
import com.sammyun.service.dict.PatriarchStudentMapService;
import com.sammyun.service.json.JsonFamilyMapService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.FreemarkerUtils;
import com.sammyun.util.SettingUtils;
import com.sammyun.util.SpringUtils;

/**
 * api - 考勤机
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("attendanceEquipment")
@Path("/attendanceEquipment")
public class AttendanceEquipmentApi
{

    /** 目标文件类型 */
    private static final String DEST_CONTENT_TYPE = "image/jpeg";

    @Resource(name = "attendanceEquipmentServiceImpl")
    private AttendanceEquipmentService attendanceEquipmentService;

    @Resource(name = "timeCardServiceImpl")
    private TimeCardService timeCardService;

    @Resource(name = "patriarchStudentMapServiceImpl")
    private PatriarchStudentMapService patriarchStudentMapService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "taskExecutor")
    private TaskExecutor taskExecutor;

    @Resource(name = "attendanceDetailServiceImpl")
    private AttendanceDetailService attendanceDetailService;

    @Resource(name = "teacherAttendanceDetailServiceImpl")
    private TeacherAttendanceDetailService teacherAttendanceDetailService;

    @Resource(name = "jsonFamilyMapServiceImpl")
    private JsonFamilyMapService jsonFamilyMapService;

    @Resource
    private List<StoragePlugin> storagePlugins;

    private ServletFileUpload upload;

    /**
     * 通过设备号查询学生基本信息 <功能详细描述>
     * 
     * @param attendaceDetailBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    // @GET
    // @Path("/v1/list/{equipmentSequence}/{flag}")
    // @Produces("application/json;charset=UTF-8")
    // public MobileRestFulModel list(@PathParam("equipmentSequence") String
    // equipmentSequence,
    // @PathParam("flag") Integer flag)
    // {
    // MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
    // AttendanceEquipmentListV2Block rows = new
    // AttendanceEquipmentListV2Block();
    // List<AttendanceEquipmentListBlock> infos = new
    // LinkedList<AttendanceEquipmentListBlock>();
    // List<Member> members = new LinkedList<Member>();
    //
    // ImUserUtil imUserUtil = new ImUserUtil();
    //
    // if (equipmentSequence == null || "".equalsIgnoreCase(equipmentSequence)
    // || flag == null)
    // {
    // mobileRestFulModel.setResultCode(1);
    // mobileRestFulModel.setResultMessage("参数错误！");
    // return mobileRestFulModel;
    // }
    // com.sammyun.entity.attendance.AttendanceEquipment attendanceEquipment =
    // attendanceEquipmentService.findByEquipmentSequence(equipmentSequence);
    // if (attendanceEquipment == null)
    // {
    // mobileRestFulModel.setResultCode(1);
    // mobileRestFulModel.setResultMessage("未知设备不存在！");
    // return mobileRestFulModel;
    // }
    // DictSchool dictSchool = attendanceEquipment.getDictSchool();
    // if (dictSchool == null)
    // {
    // mobileRestFulModel.setResultCode(1);
    // mobileRestFulModel.setResultMessage("未知学校不存在！");
    // return mobileRestFulModel;
    // }
    //
    // if (flag == 0)
    // {
    // members.addAll(dictSchool.getMembers());
    // }
    // else if (flag == 1)
    // {
    // members = memberService.findByIsUpdate(dictSchool, true);
    // }
    //
    // // 获取人员信息
    // for (Member member : members)
    // {
    // MemberType memberType = member.getMemberType();
    // if (member == null || memberType == null)
    // {
    // continue;
    // }
    // if (memberType.equals(MemberType.patriarch))
    // {
    // for (PatriarchStudentMap patriarchStudentMap :
    // member.getPatriarchStudentMap())
    // {
    // AttendanceEquipmentListBlock attendanceEquipmentListBlock = new
    // AttendanceEquipmentListBlock();
    // List<AttendanceEquipmentListBlock> familyMembers = new
    // LinkedList<AttendanceEquipmentListBlock>();
    //
    // DictStudent dictStudent = patriarchStudentMap.getDictStudent();
    // List<TimeCard> timeCards = timeCardService.findByMember(member,
    // dictStudent, null);
    // List<PatriarchStudentMap> patriarchStudentMaps =
    // patriarchStudentMapService.findByStudent(dictStudent);
    //
    // if (timeCards != null && timeCards.size() != 0)
    // {
    // // 获取卡号
    // List<AttendanceEquipmentCardBlock> cardNumbers = new
    // LinkedList<AttendanceEquipmentCardBlock>();
    // for (TimeCard timeCard : timeCards)
    // {
    // AttendanceEquipmentCardBlock card = new AttendanceEquipmentCardBlock();
    // card.setCardNumber(timeCard.getCardNumber());
    // card.setCardStatus(timeCard.getCardStatus());
    // cardNumbers.add(card);
    // }
    //
    // // 查找家庭成员
    // for (PatriarchStudentMap item : patriarchStudentMaps)
    // {
    // if (item.equals(patriarchStudentMap))
    // {
    // continue;
    // }
    // else
    // {
    // AttendanceEquipmentListBlock itemBlock = new
    // AttendanceEquipmentListBlock();
    // itemBlock.setRealName(item.getMember().getRealName());
    // itemBlock.setIconPhoto(item.getMember().getIconPhoto());
    // familyMembers.add(itemBlock);
    // }
    // }
    // if (patriarchStudentMap.getType() != null)
    // {
    // attendanceEquipmentListBlock.setTypeName(SpringUtils.getMessage("PatriarchStudentMap."
    // + patriarchStudentMap.getType()));
    // }
    // attendanceEquipmentListBlock.setId(dictStudent.getId());
    // attendanceEquipmentListBlock.setCardNumbers(cardNumbers);
    // attendanceEquipmentListBlock.setDictClassName(dictStudent.getDictClass().getName());
    // attendanceEquipmentListBlock.setIconPhoto(imUserUtil.getDefaultImageUrl(member.getIconPhoto()));
    // attendanceEquipmentListBlock.setMemberType(member.getMemberType());
    // attendanceEquipmentListBlock.setRealName(member.getRealName());
    // attendanceEquipmentListBlock.setStudentIconPhoto(imUserUtil.getDefaultImageUrl(dictStudent.getIconPhoto()));
    // attendanceEquipmentListBlock.setStudentName(dictStudent.getStudentName());
    // attendanceEquipmentListBlock.setFamilyMembers(familyMembers);
    // infos.add(attendanceEquipmentListBlock);
    // }
    // }
    // }
    // else if (memberType.equals(MemberType.teacher))
    // {
    // List<TimeCard> timeCards = timeCardService.findByMember(member, null,
    // null);
    // List<AttendanceEquipmentCardBlock> cardNumbers = new
    // LinkedList<AttendanceEquipmentCardBlock>();
    //
    // if (timeCards != null && timeCards.size() != 0)
    // {
    // // 获取卡号
    // for (TimeCard timeCard : timeCards)
    // {
    // AttendanceEquipmentCardBlock card = new AttendanceEquipmentCardBlock();
    // card.setCardNumber(timeCard.getCardNumber());
    // card.setCardStatus(timeCard.getCardStatus());
    // cardNumbers.add(card);
    // }
    //
    // AttendanceEquipmentListBlock attendanceEquipmentListBlock = new
    // AttendanceEquipmentListBlock();
    // attendanceEquipmentListBlock.setCardNumbers(cardNumbers);
    // attendanceEquipmentListBlock.setId(member.getId());
    // attendanceEquipmentListBlock.setIconPhoto(imUserUtil.getDefaultImageUrl(member.getIconPhoto()));
    // attendanceEquipmentListBlock.setMemberType(member.getMemberType());
    // attendanceEquipmentListBlock.setRealName(member.getRealName());
    // infos.add(attendanceEquipmentListBlock);
    // }
    // }
    // // member.setIsUpdate(false);
    // }
    // memberService.batchUpdate(members);
    //
    // SchoolHours schoolHours = dictSchool.getSchoolHours();
    // if (schoolHours != null)
    // {
    // rows.setEndAttendTime(dictSchool.getSchoolHours().getEndAttendTime());
    // rows.setEndFinishTime(dictSchool.getSchoolHours().getEndFinishTime());
    // rows.setStartAttendTime(dictSchool.getSchoolHours().getStartAttendTime());
    // rows.setStartFinishTime(dictSchool.getSchoolHours().getStartFinishTime());
    // }
    // if (infos == null || infos.size() == 0)
    // {
    // mobileRestFulModel.setResultCode(1);
    // mobileRestFulModel.setResultMessage("没有可更新的信息！");
    // return mobileRestFulModel;
    // }
    //
    // rows.setInfos(infos);
    // mobileRestFulModel.setRows(rows);
    // mobileRestFulModel.setResultCode(0);
    // mobileRestFulModel.setResultMessage(SpringUtils.getMessage("同步成功"));
    // return mobileRestFulModel;
    // }

    /**
     * 通过设备号查询学生基本信息 <功能详细描述>
     * 
     * @param attendaceDetailBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/list")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileRestFulModel list(AttendanceEquipmentListBean attendanceEquipmentListBean)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        AttendanceEquipmentListV2Block rows = new AttendanceEquipmentListV2Block();
        List<AttendanceEquipmentListBlock> infos = new LinkedList<AttendanceEquipmentListBlock>();
        List<JsonFamilyMap> jsonFamilyMaps = new LinkedList<JsonFamilyMap>();

        String equipmentSequence = attendanceEquipmentListBean.getEquipmentSequence();
        String modifyDate = attendanceEquipmentListBean.getModifyDate();
        if (equipmentSequence == null || "".equalsIgnoreCase(equipmentSequence))
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误！");
            return mobileRestFulModel;
        }
        com.sammyun.entity.attendance.AttendanceEquipment attendanceEquipment = attendanceEquipmentService.findByEquipmentSequence(equipmentSequence);
        if (attendanceEquipment == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("未知设备不存在！");
            return mobileRestFulModel;
        }
        DictSchool dictSchool = attendanceEquipment.getDictSchool();
        if (dictSchool == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("未知学校不存在！");
            return mobileRestFulModel;
        }

        if (modifyDate == null || "".equalsIgnoreCase(modifyDate))
        {
            jsonFamilyMaps = jsonFamilyMapService.findBySchool(dictSchool.getId(), null);
        }
        else
        {
            jsonFamilyMaps = jsonFamilyMapService.findBySchool(dictSchool.getId(), DateUtil.string2Date(modifyDate));
        }

        if (jsonFamilyMaps == null || jsonFamilyMaps.size() == 0)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("没有可以同步数据");
            return mobileRestFulModel;
        }
        for (JsonFamilyMap jsonFamilyMap : jsonFamilyMaps)
        {
            AttendanceEquipmentListBlock attendanceEquipmentListBlock = new AttendanceEquipmentListBlock();
            attendanceEquipmentListBlock.setModifyDate(DateUtil.date2String(jsonFamilyMap.getModifyDate(), 1));
            attendanceEquipmentListBlock.setJson(jsonFamilyMap.getJson());
            infos.add(attendanceEquipmentListBlock);
        }
        SchoolHours schoolHours = dictSchool.getSchoolHours();
        if (schoolHours != null)
        {
            rows.setEndAttendTime(dictSchool.getSchoolHours().getEndAttendTime());
            rows.setEndFinishTime(dictSchool.getSchoolHours().getEndFinishTime());
            rows.setStartAttendTime(dictSchool.getSchoolHours().getStartAttendTime());
            rows.setStartFinishTime(dictSchool.getSchoolHours().getStartFinishTime());
        }
        rows.setInfos(infos);
        mobileRestFulModel.setRows(rows);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage(SpringUtils.getMessage("同步成功"));
        return mobileRestFulModel;
    }

    /**
     * 上传头像
     * 
     * @throws IOException
     */
    @POST
    @Path("/v1/upload")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @SuppressWarnings("unchecked")
    public ListRestFulModel upload(@Context HttpServletRequest request) throws ServletException, IOException,
            FileUploadException
    {
        FileItemFactory factory = new DiskFileItemFactory();
        this.upload = new ServletFileUpload(factory);
        List<FileItem> items = this.upload.parseRequest(request);

        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<Long> rows = new LinkedList<Long>();
        Setting setting = SettingUtils.get();

        if (items == null || items.size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage(SpringUtils.getMessage("参数错误！"));
            return listRestFulModel;
        }
        for (FileItem fileItem : items)
        {
            String url = null;
            Long serverId = null;
            String memberType = null;
            String fileName = fileItem.getName();
            InputStream stream = fileItem.getInputStream();

            try
            {
                serverId = Long.parseLong(fileItem.getFieldName());
                memberType = fileName.split("_")[0];
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            try
            {
                String uploadPath = setting.getImageUploadPath();
                Map<String, Object> model = new HashMap<String, Object>();
                model.put("uuid", UUID.randomUUID().toString());
                String path = FreemarkerUtils.process(uploadPath, model);
                String sourcePath = path + UUID.randomUUID() + "." + FilenameUtils.getExtension(fileName);

                Collections.sort(storagePlugins);
                for (StoragePlugin storagePlugin : storagePlugins)
                {
                    if (storagePlugin.getIsEnabled())
                    {
                        File tempFile = new File(System.getProperty("java.io.tmpdir") + "/upload_" + UUID.randomUUID()
                                + ".tmp");
                        if (!tempFile.getParentFile().exists())
                        {
                            tempFile.getParentFile().mkdirs();
                        }
                        OutputStream os = new FileOutputStream(tempFile);
                        int bytesRead = 0;
                        byte[] buffer = new byte[8192];
                        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1)
                        {
                            os.write(buffer, 0, bytesRead);
                        }
                        os.close();
                        stream.close();
                        addTask(sourcePath, tempFile);
                        url = storagePlugin.getUrl(sourcePath);
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

            if (memberType != null && !"".equalsIgnoreCase(memberType))
            {
                if (memberType.equalsIgnoreCase(MemberType.teacher.toString()))
                {
                    TeacherAttendanceDetail teacherAttendanceDetail = teacherAttendanceDetailService.find(serverId);
                    teacherAttendanceDetail.setIconPhoto(url);
                    teacherAttendanceDetailService.update(teacherAttendanceDetail);
                }
                if (memberType.equalsIgnoreCase(MemberType.patriarch.toString()))
                {
                    AttendanceDetail attendanceDetail = attendanceDetailService.find(serverId);
                    attendanceDetail.setIconPhoto(url);
                    attendanceDetailService.update(attendanceDetail);
                }
                rows.add(serverId);
            }
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("上传成功！");
        return listRestFulModel;
    }

    /**
     * 添加图片处理任务
     * 
     * @param sourcePath 原图片上传路径
     * @param tempFile 原临时文件
     * @param contentType 原文件类型
     */
    private void addTask(final String sourcePath, final File tempFile)
    {
        try
        {
            taskExecutor.execute(new Runnable()
            {
                public void run()
                {
                    Collections.sort(storagePlugins);
                    for (StoragePlugin storagePlugin : storagePlugins)
                    {
                        if (storagePlugin.getIsEnabled())
                        {
                            try
                            {
                                storagePlugin.upload(sourcePath, tempFile, DEST_CONTENT_TYPE);
                            }
                            finally
                            {
                                FileUtils.deleteQuietly(tempFile);
                            }
                            break;
                        }
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
