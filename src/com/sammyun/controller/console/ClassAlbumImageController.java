package com.sammyun.controller.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
import com.sammyun.entity.Admin;
import com.sammyun.entity.classalbum.ClassAlbumImage;
import com.sammyun.entity.classalbum.ClassAlbumImageAttach;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.campusviewImg.CampusviewImageAttachService;
import com.sammyun.service.classalbum.ClassAlbumImageAttachService;
import com.sammyun.service.classalbum.ClassAlbumImageService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.EduUtil;

/**
 * Controller - 班级相册
 * 
 * @author xutianlong
 * @version [版本号, May 1, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("adminClassAlbumImageController")
@RequestMapping("/console/classAlbumImage")
public class ClassAlbumImageController extends BaseController
{

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;

    @Resource(name = "classAlbumImageAttachServiceImpl")
    private ClassAlbumImageAttachService classAlbumImageAttachService;

    @Resource(name = "classAlbumImageServiceImpl")
    private ClassAlbumImageService classAlbumImageService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "campusviewImageAttachServiceImpl")
    private CampusviewImageAttachService campusviewImageAttachService;

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
        Admin admin = adminService.getCurrent();
        DictSchool dictSchool = admin.getDictSchool();
        Filter filter = new Filter("dictSchool", Operator.eq, dictSchool);
        pageable.addFilters(filter);
        model.addAttribute("page", classAlbumImageService.findPage(pageable));
        model.addAttribute("menuId", ClassAlbumImage.class.getSimpleName());
        return "/console/classAlbumImage/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        Set<DictClass> dictClasses = dictSchool.getDictClasses();
        // List<DictClass> addDictClasses =
        // classAlbumImageService.getDictClasses(dictSchool);
        // dictClasses.removeAll(addDictClasses);
        model.addAttribute("dictClasses", dictClasses);
        model.addAttribute("menuId", ClassAlbumImage.class.getSimpleName());
        return "/console/classAlbumImage/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ClassAlbumImage classAlbumImage, Long classId, RedirectAttributes redirectAttributes,
            ModelMap model)
    {
        DictClass dictClass = dictClassService.find(classId);
        classAlbumImage.setDictClass(dictClass);
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        classAlbumImage.setDictSchool(dictSchool);
        Admin admin = adminService.getCurrent();
        if (admin != null)
        {
            classAlbumImage.setCreator(admin.getName());
        }
        else
        {
            classAlbumImage.setCreator("");
        }
        classAlbumImage.setStatus(0);
        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }
        classAlbumImageService.save(classAlbumImage);
        List<ClassAlbumImageAttach> classAlbumImageAttachs = buildAttachs(classAlbumImage.getClassAlbumImageAttachs(),
                classAlbumImage);
        classAlbumImageAttachService.batchUpdate(classAlbumImageAttachs);
        model.addAttribute("menuId", ClassAlbumImage.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("classAlbumImage", classAlbumImageService.find(id));
        model.addAttribute("menuId", ClassAlbumImage.class.getSimpleName());
        return "/console/classAlbumImage/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ClassAlbumImage classAlbumImage, Long dictClassId, RedirectAttributes redirectAttributes,
            ModelMap model)
    {
        DictClass dictClass = dictClassService.find(dictClassId);
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<ClassAlbumImageAttach> originalClassAlbumImageAttachs = new ArrayList<ClassAlbumImageAttach>();
        originalClassAlbumImageAttachs.addAll(classAlbumImage.getClassAlbumImageAttachs());
        classAlbumImage.setDictSchool(dictSchool);
        classAlbumImage.setDictClass(dictClass);
        Admin admin = adminService.getCurrent();
        if (admin != null)
        {
            classAlbumImage.setCreator(admin.getName());
        }
        else
        {
            classAlbumImage.setCreator("");
        }
        classAlbumImage.setStatus(0);
        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }
        classAlbumImage.getClassAlbumImageAttachs().clear();
        classAlbumImageService.update(classAlbumImage);
        classAlbumImageAttachService.deleteByClassAlbumImage(classAlbumImage);
        List<ClassAlbumImageAttach> classAlbumImageAttachs = buildAttachs(originalClassAlbumImageAttachs,
                classAlbumImage);
        classAlbumImageAttachService.batchUpdate(classAlbumImageAttachs);
        model.addAttribute("menuId", ClassAlbumImage.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 构建 班级相册图片附件
     * 
     * @param originalCampusviewImageAttachs
     * @param
     * @return
     * @see [类、类#方法、类#成员]
     */
    private List<ClassAlbumImageAttach> buildAttachs(List<ClassAlbumImageAttach> originalClassAlbumImageAttachs,
            ClassAlbumImage classAlbumImage)
    {
        List<ClassAlbumImageAttach> classAlbumImageAttachs = new ArrayList<ClassAlbumImageAttach>();
        for (ClassAlbumImageAttach classAlbumImageAttach : originalClassAlbumImageAttachs)
        {
            if (null != classAlbumImageAttach && EduUtil.isNotEmpty(classAlbumImageAttach.getImageAttach()))
            {
                classAlbumImageAttach.setId(null);
                classAlbumImageAttach.setClassAlbumImage(classAlbumImage);
                classAlbumImageAttachs.add(classAlbumImageAttach);
            }
        }
        return classAlbumImageAttachs;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        for (Long id : ids)
        {
            ClassAlbumImage classAlbumImage = classAlbumImageService.find(id);
            classAlbumImageAttachService.deleteByClassAlbumImage(classAlbumImage);
            classAlbumImageService.delete(classAlbumImage);
        }
        // classAlbumImageService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
