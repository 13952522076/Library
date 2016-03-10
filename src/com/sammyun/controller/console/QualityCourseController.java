package com.sammyun.controller.console;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Filter;
import com.sammyun.Filter.Operator;
import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.entity.course.QualityCourse;
import com.sammyun.entity.course.QualityCourseImageAttach;
import com.sammyun.entity.course.WeeklyPlanSection;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.course.QualityCourseImageAttachService;
import com.sammyun.service.course.QualityCourseService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.util.EduUtil;

/**
 * Controller - 精品课程管理
 * 
 * @author xutianlong
 * @version [版本号, Jun 5, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("adminQualityCourseController")
@RequestMapping("/console/quality_course")
public class QualityCourseController extends BaseController
{
    /** 日志 */
    // private static final Logger logger =
    // LoggerFactory.getLogger(QualityCourseController.class);

    @Resource(name = "qualityCourseServiceImpl")
    private QualityCourseService qualityCourseService;

    @Resource(name = "qualityCourseImageAttachServiceImpl")
    private QualityCourseImageAttachService qualityCourseImageAttachService;

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    /**
     * 列表
     * 
     * @param pageable
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        Filter filter = new Filter("dictSchool", Operator.eq, dictSchool);
        pageable.addFilters(filter);
        model.addAttribute("page", qualityCourseService.findPage(pageable));
        model.addAttribute("menuId", QualityCourse.class.getSimpleName());
        return "/console/quality_course/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("menuId", QualityCourse.class.getSimpleName());
        return "/console/quality_course/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(QualityCourse qualityCourse, Long schoolYearMngId, Long dictClassId,
            RedirectAttributes redirectAttributes, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        qualityCourse.setDictSchool(dictSchool);

        // 状态默认为未屏蔽
        qualityCourse.setStatus(0);
        if (!isValid(qualityCourse))
        {
            return ERROR_VIEW;
        }
        qualityCourseService.save(qualityCourse);

        List<QualityCourseImageAttach> qualityCourseImageAttachs = buildAttachs(
                qualityCourse.getQualityCourseImageAttachs(), qualityCourse);
        qualityCourseImageAttachService.batchUpdate(qualityCourseImageAttachs);

        model.addAttribute("menuId", QualityCourse.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        QualityCourse qualityCourse = qualityCourseService.find(id);
        model.addAttribute("qualityCourse", qualityCourse);
        model.addAttribute("menuId", QualityCourse.class.getSimpleName());
        return "/console/quality_course/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(QualityCourse qualityCourse, Long schoolYearMngId, Long dictClassId,
            RedirectAttributes redirectAttributes, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<QualityCourseImageAttach> originalQualityCourseImageAttachs = new ArrayList<QualityCourseImageAttach>();
        originalQualityCourseImageAttachs.addAll(qualityCourse.getQualityCourseImageAttachs());
        qualityCourse.setDictSchool(dictSchool);
        if (!isValid(qualityCourse))
        {
            return ERROR_VIEW;
        }

        qualityCourse.getQualityCourseImageAttachs().clear();
        qualityCourseService.update(qualityCourse);
        qualityCourseImageAttachService.deleteByQualityCourse(qualityCourse);
        List<QualityCourseImageAttach> qualityCourseImageAttachs = buildAttachs(originalQualityCourseImageAttachs,
                qualityCourse);
        qualityCourseImageAttachService.batchUpdate(qualityCourseImageAttachs);

        qualityCourseService.update(qualityCourse);
        model.addAttribute("menuId", WeeklyPlanSection.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 构建 校园相册图片附件
     * 
     * @param originalQualityCourseImageAttachs
     * @param campusviewImg
     * @return
     * @see [类、类#方法、类#成员]
     */
    private List<QualityCourseImageAttach> buildAttachs(
            List<QualityCourseImageAttach> originalQualityCourseImageAttachs, QualityCourse qualityCourse)
    {
        List<QualityCourseImageAttach> qualityCourseImageAttachs = new ArrayList<QualityCourseImageAttach>();
        for (QualityCourseImageAttach qualityCourseImageAttach : originalQualityCourseImageAttachs)
        {
            if (null != qualityCourseImageAttach && EduUtil.isNotEmpty(qualityCourseImageAttach.getImageAttach()))
            {
                qualityCourseImageAttach.setId(null);
                qualityCourseImageAttach.setQualityCourse(qualityCourse);
                qualityCourseImageAttachs.add(qualityCourseImageAttach);
            }
        }
        return qualityCourseImageAttachs;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        qualityCourseService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
