package com.sammyun.controller.console;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.FileInfo.FileType;
import com.sammyun.Filter;
import com.sammyun.Filter.Operator;
import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.entity.ExcelMessage;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;
import com.sammyun.entity.dict.PatriarchStudentMap;
import com.sammyun.entity.stu.FamilAlbum;
import com.sammyun.entity.stu.GraduationCertificate;
import com.sammyun.entity.stu.GraduationPhoto;
import com.sammyun.entity.stu.HealthFile;
import com.sammyun.entity.stu.StudentWorks;
import com.sammyun.service.AdminService;
import com.sammyun.service.ExcelService;
import com.sammyun.service.MemberService;
import com.sammyun.service.StaticService;
import com.sammyun.service.dict.ClassTeacherMapService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.service.dict.DictStudentService;
import com.sammyun.service.dict.PatriarchStudentMapService;
import com.sammyun.service.stu.FamilAlbumService;
import com.sammyun.service.stu.GraduationCertificateService;
import com.sammyun.service.stu.GraduationPhotoService;
import com.sammyun.service.stu.HealthFileService;
import com.sammyun.service.stu.StudentWorksService;
import com.sammyun.util.EduUtil;
import com.sammyun.util.ExcelImportUtil;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 学生管理
 * 
 * @author xutianlong
 * @version [版本号, Apr 1, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("dictStudentController")
@RequestMapping("/console/dict_student")
public class DictStudentController extends BaseController
{
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    @Resource(name = "dictStudentServiceImpl")
    private DictStudentService dictStudentService;

    @Resource(name = "studentWorksServiceImpl")
    private StudentWorksService studentWorksService;

    @Resource(name = "familAlbumServiceImpl")
    private FamilAlbumService familAlbumService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "patriarchStudentMapServiceImpl")
    private PatriarchStudentMapService patriarchStudentMapService;

    @Resource(name = "excelServiceImpl")
    private ExcelService excelService;

    @Resource(name = "classTeacherMapServiceImpl")
    private ClassTeacherMapService classTeacherMapService;

    @Resource(name = "staticServiceImpl")
    private StaticService staticService;

    @Resource(name = "healthFileServiceImpl")
    private HealthFileService healthFileService;

    @Resource(name = "graduationPhotoServiceImpl")
    private GraduationPhotoService graduationPhotoService;

    @Resource(name = "graduationCertificateServiceImpl")
    private GraduationCertificateService graduationCertificateService;


    /**
     * 检查学号是否唯一
     */
    @RequestMapping(value = "/check_studentNo", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkStudentNo(String previousStudentNo, String studentNo)
    {
        if (StringUtils.equalsIgnoreCase(previousStudentNo, studentNo))
        {
            return true;
        }
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        if (dictStudentService.studentNoUnique(previousStudentNo, studentNo, dictSchool))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    /**
     * 学生列表
     * 
     * @param pageable
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, String studentNo, String searchName, ModelMap model)
    {
        // 只能查看到当前学校的学生
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        Set<DictClass> dictClasses = dictSchool.getDictClasses();
        Filter filter = new Filter("dictClass", Operator.in, dictClasses);
        if (dictClasses.size() == 0)
        {
            filter = new Filter("id", Operator.eq, 0);
        }// 这个判断的意义在于如果学校下班级列表没有的话添加空过滤器防止in报错
        if (searchName != null)
        {
            Filter realNameFilter = new Filter("studentName", Operator.like, "%" + searchName + "%");
            pageable.addFilters(realNameFilter);
            model.addAttribute("searchName", searchName);
        }
        pageable.addFilters(filter);
        // 以上添加过滤器
        if (studentNo != null)
        {
            Filter studentNoFilter = new Filter("studentNo", Operator.like, studentNo);
            pageable.addFilters(studentNoFilter);
            model.addAttribute("studentNo", studentNo);
        }
        model.addAttribute("page", dictStudentService.findPage(pageable));
        model.addAttribute("menuId", DictStudent.class.getSimpleName());
        return "/console/dict_student/list";
    }

    /**
     * 添加学生
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<Member> members = memberService.findBySchoolAndType(dictSchool, MemberType.patriarch);
        model.addAttribute("members", members);
        model.addAttribute("dictClasses", dictClassService.getClassesBySchool(dictSchool));
        model.addAttribute("menuId", DictStudent.class.getSimpleName());
        return "/console/dict_student/add";
    }

    /**
     * 保存学生
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(DictStudent dictStudent, Long dictClassId, RedirectAttributes redirectAttributes,
            Long[] memberIds, ModelMap model)
    {
        DictClass dictClass = this.dictClassService.find(dictClassId);
        dictStudent.setDictClass(dictClass);
        if (!isValid(dictStudent))
        {
            return ERROR_VIEW;
        }
        dictStudentService.save(dictStudent);
        List<Member> prePatriarches = new ArrayList<Member>();
        Set<DictStudent> students = dictClass.getDictStudents();
        if (students != null && students.size() != 0)
        {
            List<Member> prePatriarchesItem = patriarchStudentMapService.findMemberByStudent(dictStudent);
            prePatriarches.addAll(prePatriarchesItem);
        }
        List<Member> teachers = classTeacherMapService.findMemberByClass(dictClass);
        prePatriarches.addAll(teachers);// 将家长和老师组合在一起
        HashSet<Member> hashSet = new HashSet<Member>(prePatriarches);
        prePatriarches.clear();
        prePatriarches.addAll(hashSet);

        List<Member> addPatriarches = new ArrayList<Member>();
        if (memberIds != null)
        {
            for (Long memberId : memberIds)
            {
                Member member = memberService.find(memberId);
                // member.setIsUpdate(true);
                // memberService.update(member);
                addPatriarches.add(member);
                PatriarchStudentMap patriarchStudentMap = new PatriarchStudentMap();
                patriarchStudentMap.setMember(member);
                patriarchStudentMap.setDictStudent(dictStudent);
                patriarchStudentMapService.save(patriarchStudentMap);

            }
        }
        // //调用环信将家长和班级下的所有家长和老师添加成好友。失败回滚
        //
        // imUserUtil.twoMemberListAddFriend(addPatriarches, prePatriarches);
        // // imUserUtil.addFriendInClass(dictClass);
        // model.addAttribute("menuId", DictStudent.class.getSimpleName());
        // Date endDate = new Date();
        // System.out.println("增加单个学生时间："+(endDate.getTime()-startDate.getTime()));
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑学生
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<Member> members = memberService.findBySchoolAndType(dictSchool, MemberType.patriarch);
        model.addAttribute("members", members);
        List<Member> linkMembers = new ArrayList<Member>();
        if (id != null)
        {
            DictStudent dictStudent = dictStudentService.find(id);
            linkMembers = patriarchStudentMapService.findMemberByStudent(dictStudent);
        }
        model.addAttribute("linkMembers", linkMembers);
        model.addAttribute("dictClasses", dictClassService.getClassesBySchool(dictSchool));
        model.addAttribute("dictStudent", dictStudentService.find(id));
        model.addAttribute("menuId", DictStudent.class.getSimpleName());
        return "/console/dict_student/edit";
    }

    /**
     * 静态化毕业相册
     */
    @RequestMapping(value = "/staticEdit", method = RequestMethod.GET)
    public String staticEdit(Long id, ModelMap model)
    {
        DictStudent dictStudent = dictStudentService.find(id);
        HealthFile healthFile = healthFileService.findByDictStudent(dictStudent);
        List<StudentWorks> studnetWorkses = studentWorksService.getListByDictStudent(dictStudent);
        model.addAttribute("studnetWorkses", studnetWorkses);
        model.addAttribute("dictStudent", dictStudent);
        model.addAttribute("healthFile", healthFile);
        model.addAttribute("menuId", DictStudent.class.getSimpleName());
        return "/console/dict_student/staticEdit";
    }

    /**
     * 静态化保存学生信息（健康档案，学生作品，全家福）
     */
    @RequestMapping(value = "/staticSave", method = RequestMethod.POST)
    public String staticSave(Long dictStudentId, HealthFile healthFile, Long healthFileId, DictStudent dictStudent,
            DictClass dictClass, Long graduationCertificateId, GraduationCertificate graduationCertificate,
            RedirectAttributes redirectAttributes, ModelMap model)
    {
        // 健康档案
        healthFile.setDictStudent(dictStudentService.find(dictStudentId));
        if (healthFileId == null)
        {
            healthFileService.save(healthFile);
        }
        else
        {
            HealthFile preHealthFile = healthFileService.find(healthFileId);
            preHealthFile.setHeight(healthFile.getHeight());
            preHealthFile.setWeight(healthFile.getWeight());
            preHealthFile.setLeftVision(healthFile.getLeftVision());
            preHealthFile.setRightVision(healthFile.getRightVision());
            healthFileService.update(preHealthFile);
        }
        // 毕业证书
        graduationCertificate.setDictClass(dictStudentService.find(dictStudentId).getDictClass());
        if (graduationCertificateId == null)
        {
            graduationCertificateService.save(graduationCertificate);
        }
        else
        {
            GraduationCertificate preGraduationCertificate = graduationCertificateService.find(graduationCertificateId);
            preGraduationCertificate.setGraduationDate(graduationCertificate.getGraduationDate());
            graduationCertificateService.update(preGraduationCertificate);
        }
        // 学生作品
        List<StudentWorks> studentWorksList = buildStudentWotks(dictStudent.getStudentWorkss(),
                dictStudentService.find(dictStudentId));
        studentWorksService.deleteByDictStudent(dictStudentService.find(dictStudentId));
        studentWorksService.batchUpdate(studentWorksList);
        // 全家福
        List<FamilAlbum> familAlbumList = buildFamilAlbum(dictStudent.getFamilAlbums(),
                dictStudentService.find(dictStudentId));
        familAlbumService.deleteByDictStudent(dictStudentService.find(dictStudentId));
        familAlbumService.batchUpdate(familAlbumList);

        // 毕业合影
        List<GraduationPhoto> graduationPhotoList = buildGraduationPhoto(dictClass.getGraduationPhotos(),
                dictStudentService.find(dictStudentId).getDictClass());
        graduationPhotoService.deleteByDictClass(dictStudentService.find(dictStudentId).getDictClass());
        graduationPhotoService.batchUpdate(graduationPhotoList);

        // 静态化
        staticService.build(dictStudentService.find(dictStudentId));

        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 更新学生
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(DictStudent dictStudent, Long dictClassId, RedirectAttributes redirectAttributes,
            ModelMap model, Long[] memberIds)
    {
        // 获得之前的班级和家长
        //DictStudent preDictStudent = dictStudentService.find(dictStudent.getId());
        //DictClass preDictClass = preDictStudent.getDictClass();
        //List<Member> prepatriarches = patriarchStudentMapService.findMemberByStudent(dictStudent);
        DictClass dictClass = this.dictClassService.find(dictClassId);
        dictStudent.setDictClass(dictClass);
        if (!isValid(dictStudent))
        {
            return ERROR_VIEW;
        }
        dictStudentService.update(dictStudent);
        // 先删除所有关联，再添加
        if (dictStudent != null)
        {
            List<PatriarchStudentMap> patriarchStudentMaps = patriarchStudentMapService.findByStudent(dictStudent);
            if (patriarchStudentMaps != null)
            {
                if (patriarchStudentMaps.size() > 0)
                {
                    for (PatriarchStudentMap patriarchStudentMap : patriarchStudentMaps)
                    {
                        patriarchStudentMapService.delete(patriarchStudentMap);
                    }
                }
            }
            if (memberIds != null)
            {
                if (memberIds.length > 0)
                {
                    for (long memberId : memberIds)
                    {
                        Member member = memberService.find(memberId);
                        // member.setIsUpdate(true);
                        // memberService.update(member);
                        PatriarchStudentMap patriarchStudentMap = new PatriarchStudentMap();
                        patriarchStudentMap.setMember(member);
                        patriarchStudentMap.setDictStudent(dictStudent);
                        patriarchStudentMapService.save(patriarchStudentMap);
                    }
                }
            }

        }
        // //获得关联的家长————————————start关联关系
        // List<Member> newPatriarches =
        // patriarchStudentMapService.findMemberByStudent(dictStudent);
        // List<Member> addPatriarches = new ArrayList<Member>();
        // addPatriarches.addAll(newPatriarches);
        // addPatriarches.removeAll(prepatriarches);//新增的关联家长
        // List<Member> delPatriarches = new ArrayList<Member>();
        // delPatriarches.addAll(prepatriarches);
        // delPatriarches.removeAll(newPatriarches);//删除的家长
        // //如果没有改变班级
        // if(dictClass ==preDictClass){
        // //删除的家长与大家关系删除
        // imUserUtil.studentDelMember(delPatriarches, dictClass);
        // //新增家长与大家成为好友
        // imUserUtil.studentAddMember(addPatriarches, dictClass);
        // }
        // //改变班级的操作
        // else{
        // //所有原家长删除和原班级的关系
        // imUserUtil.studentDelMember(prepatriarches, preDictClass);
        // //所有新家长与新班级大家成为好友
        // imUserUtil.studentAddMember(newPatriarches, dictClass);
        // }
        // //－－－－－－－－－end关联关系

        model.addAttribute("menuId", DictStudent.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        if (ids != null)
        {
            for (Long id : ids)
            {
                DictStudent dictStudent = dictStudentService.find(id);
                if (dictStudent != null)
                {
                    // //start删除该学生所有的好友
                    // List<Member> delPatriarches =
                    // patriarchStudentMapService.findMemberByStudent(dictStudent);
                    // DictClass dictClass = dictStudent.getDictClass();
                    // imUserUtil.studentDelMember(delPatriarches, dictClass);
                    // //end
                    List<PatriarchStudentMap> patriarchStudentMaps = patriarchStudentMapService.findByStudent(dictStudent);
                    if (patriarchStudentMaps != null)
                    {
                        if (patriarchStudentMaps.size() > 0)
                        {
                            for (PatriarchStudentMap patriarchStudentMap : patriarchStudentMaps)
                            {
                                patriarchStudentMapService.delete(patriarchStudentMap);
                            }
                        }
                    }
                }
            }
            dictStudentService.delete(ids);
        }
        return SUCCESS_MESSAGE;
    }

    /**
     * 批量插入学生信息
     * 
     * @throws Exception
     * @throws IOException
     */
    @RequestMapping(value = "/batchSaveStudents", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public void batchSaveStudents(FileType fileType, MultipartFile file, HttpServletResponse response, ModelMap model,
            Pageable pageable, HttpServletRequest request) throws Exception
    {
        // Date startDate = new Date();
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        ExcelMessage excelMessage = new ExcelMessage();
        Map<String, Object> data = new HashMap<String, Object>();
        if (file == null)
        {
            data.put("message", Message.warn("导入文件为空文件，请检查文件是否正确！"));
        }
        InputStream inputStream = ExcelImportUtil.getInputStream(file);
        if (inputStream == null)
        {
            data.put("message", Message.warn("读取文件失败，请检查文件是否正确！"));
        }
        List<DictStudent> dictStudents = excelService.getDictStudents(inputStream, dictSchool, request);
        excelMessage = excelService.validateDictStudents(dictStudents);
        if (excelMessage.getStatus().toString().equals("success"))
        {
            try
            {
                dictStudentService.batchUpdate(dictStudents);
                List<DictStudent> addDictStudents = new ArrayList<DictStudent>();
                for (DictStudent dictStudent : dictStudents)
                {
                    addDictStudents.add(dictStudentService.findByStudentNoSingle(dictStudent.getStudentNo(), dictSchool));
                }
                List<PatriarchStudentMap> patriarchStudentMaps = patriarchStudentMapService.createMapsByStudents(addDictStudents);
                patriarchStudentMapService.batchUpdate(patriarchStudentMaps);
                dictStudentService.batchUpdate(addDictStudents);
                // //循环将导入学生中的相关班级下的学生的家长建立好友
                // for(DictStudent dictStudent:addDictStudents){
                // DictClass dictClass =
                // dictStudentService.find(dictStudent.getId()).getDictClass();
                // if(dictClass!=null){
                // List<Member> prePatriarches = new ArrayList<Member>();
                // Set<DictStudent> students = dictClass.getDictStudents();
                // if(students!=null&&students.size()!=0){
                // List<Member> prePatriarchesItem =
                // patriarchStudentMapService.findMemberByStudent(dictStudent);
                // prePatriarches.addAll(prePatriarchesItem);
                // }
                // List<Member> teachers =
                // classTeacherMapService.findMemberByClass(dictClass);
                // prePatriarches.addAll(teachers);//将家长和老师组合在一起
                // HashSet<Member> hashSet = new
                // HashSet<Member>(prePatriarches);
                // prePatriarches.clear();
                // prePatriarches.addAll(hashSet);
                // List<Member> addPatriarches =
                // patriarchStudentMapService.findMemberByStudent(dictStudent);
                //
                // imUserUtil.twoMemberListAddFriend(addPatriarches,
                // prePatriarches);
                //
                // //imUserUtil.addFriendInClass(dictClass);
                // }
                // }
                data.put("message", Message.success("导入成功！"));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                data.put("message", Message.warn("导入失败，请检查文件是否正确！"));
            }
        }
        else
        {
            data.put("message", Message.warn(excelMessage.getError()));
        }
        try
        {
            // Date endDate = new Date();
            // System.out.println("批量导入学生时间："+(endDate.getTime()-startDate.getTime()));
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), data);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    /**
     * 生成静态毕业纪念册
     */
    // @RequestMapping(value = "/buildDictStudent", method =
    // RequestMethod.GET)
    // public void build(Long id, HttpServletRequest request,
    // HttpServletResponse response)
    // {
    // DictStudent dictStudent = dictStudentService.find(id);
    // //add three tabs
    // if(dictStudent == null){
    // try
    // {
    // response.setContentType("text/html; charset=UTF-8");
    // JsonUtils.writeValue(response.getWriter(), "未知学生不存在！");
    // return;
    // }
    // catch (IOException e)
    // {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // return;
    // }
    // }
    // try
    // {
    // staticService.build(dictStudent);
    // response.setContentType("text/html; charset=UTF-8");
    // JsonUtils.writeValue(response.getWriter(), "success");
    // }
    // catch (IOException e)
    // {
    // e.printStackTrace();
    // }
    // }

    /**
     * 构建 学生作品集
     * 
     * @param originalStudentWorkses
     * @param dictStudent
     * @return
     * @see [类、类#方法、类#成员]
     */
    private List<StudentWorks> buildStudentWotks(List<StudentWorks> originalStudentWorkses, DictStudent dictStudent)
    {
        List<StudentWorks> studentWorkses = new ArrayList<StudentWorks>();
        for (StudentWorks studentWorks : originalStudentWorkses)
        {
            if (null != studentWorks && EduUtil.isNotEmpty(studentWorks.getImageAttach()))
            {
                studentWorks.setId(null);
                studentWorks.setDictStudent(dictStudent);
                studentWorkses.add(studentWorks);
            }
        }
        return studentWorkses;
    }

    /**
     * 构建 全家福
     * 
     * @param originalFamilAlbums
     * @param dictStudent
     * @return
     * @see [类、类#方法、类#成员]
     */
    private List<FamilAlbum> buildFamilAlbum(List<FamilAlbum> originalFamilAlbums, DictStudent dictStudent)
    {
        List<FamilAlbum> familAlbums = new ArrayList<FamilAlbum>();
        for (FamilAlbum familAlbum : originalFamilAlbums)
        {
            if (null != familAlbum && EduUtil.isNotEmpty(familAlbum.getImageAttach()))
            {
                familAlbum.setId(null);
                familAlbum.setDictStudent(dictStudent);
                familAlbums.add(familAlbum);
            }
        }
        return familAlbums;
    }

    /**
     * 构建 毕业合影
     * 
     * @param originalFamilAlbums
     * @param dictStudent
     * @return
     * @see [类、类#方法、类#成员]
     */
    private List<GraduationPhoto> buildGraduationPhoto(List<GraduationPhoto> originalGraduationPhotos,
            DictClass dictClass)
    {
        List<GraduationPhoto> graduationPhotos = new ArrayList<GraduationPhoto>();
        for (GraduationPhoto graduationPhoto : originalGraduationPhotos)
        {
            if (null != graduationPhoto && EduUtil.isNotEmpty(graduationPhoto.getImageAttach()))
            {
                graduationPhoto.setId(null);
                graduationPhoto.setDictClass(dictClass);
                graduationPhotos.add(graduationPhoto);
            }
        }
        return graduationPhotos;
    }

}
