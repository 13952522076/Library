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
import com.sammyun.entity.Admin;
import com.sammyun.entity.campusviewImg.CampusviewImageAttach;
import com.sammyun.entity.campusviewImg.CampusviewImg;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.campusviewImg.CampusviewImageAttachService;
import com.sammyun.service.campusviewImg.CampusviewImgService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.util.EduUtil;

/**
 * Entity - 校园相册
 * 
 * @author xutianlong
 * @version [版本号, May 1, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("adminCampusviewImgController")
@RequestMapping("/console/campusview_img")
public class CampusviewImgController extends BaseController
{

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "campusviewImgServiceImpl")
    private CampusviewImgService campusviewImgService;

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
        model.addAttribute("page", campusviewImgService.findPage(pageable));
        model.addAttribute("menuId", CampusviewImg.class.getSimpleName());
        return "/console/campusview_img/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("menuId", CampusviewImg.class.getSimpleName());
        return "/console/campusview_img/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(CampusviewImg campusviewImg, RedirectAttributes redirectAttributes, ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        campusviewImg.setDictSchool(dictSchool);
        Admin admin = adminService.getCurrent();
        if (admin != null)
        {
            campusviewImg.setCreator(admin.getName());
        }
        else
        {
            campusviewImg.setCreator("");
        }
        campusviewImg.setStatus(0);

        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }
        campusviewImgService.save(campusviewImg);
        List<CampusviewImageAttach> campusviewImageAttachs = buildAttachs(campusviewImg.getCampusviewImageAttachs(),
                campusviewImg);
        campusviewImageAttachService.batchUpdate(campusviewImageAttachs);
        model.addAttribute("menuId", CampusviewImg.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("campusviewImg", campusviewImgService.find(id));
        model.addAttribute("menuId", CampusviewImg.class.getSimpleName());
        return "/console/campusview_img/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(CampusviewImg campusviewImg, Long dictSchoolId, RedirectAttributes redirectAttributes,
            ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        List<CampusviewImageAttach> originalCampusviewImageAttachs = new ArrayList<CampusviewImageAttach>();
        originalCampusviewImageAttachs.addAll(campusviewImg.getCampusviewImageAttachs());
        campusviewImg.setDictSchool(dictSchool);
        Admin admin = adminService.getCurrent();
        if (admin != null)
        {
            campusviewImg.setCreator(admin.getName());
        }
        else
        {
            campusviewImg.setCreator("");
        }
        campusviewImg.setStatus(0);

        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }
        campusviewImg.getCampusviewImageAttachs().clear();
        campusviewImgService.update(campusviewImg);
        campusviewImageAttachService.deleteByCampusviewImg(campusviewImg);
        List<CampusviewImageAttach> campusviewImageAttachs = buildAttachs(originalCampusviewImageAttachs, campusviewImg);
        campusviewImageAttachService.batchUpdate(campusviewImageAttachs);

        model.addAttribute("menuId", CampusviewImg.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 构建 校园相册图片附件
     * 
     * @param originalCampusviewImageAttachs
     * @param campusviewImg
     * @return
     * @see [类、类#方法、类#成员]
     */
    private List<CampusviewImageAttach> buildAttachs(List<CampusviewImageAttach> originalCampusviewImageAttachs,
            CampusviewImg campusviewImg)
    {
        List<CampusviewImageAttach> campusviewImageAttachs = new ArrayList<CampusviewImageAttach>();
        for (CampusviewImageAttach campusviewImageAttach : originalCampusviewImageAttachs)
        {
            if (null != campusviewImageAttach && EduUtil.isNotEmpty(campusviewImageAttach.getImageAttach()))
            {
                campusviewImageAttach.setId(null);
                campusviewImageAttach.setCampusviewImg(campusviewImg);
                campusviewImageAttachs.add(campusviewImageAttach);
            }
        }
        return campusviewImageAttachs;
    }

    /**
     * 删除
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(Long[] ids)
    {
        campusviewImgService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
