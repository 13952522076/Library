package com.sammyun.controller.console;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.profile.Profile;
import com.sammyun.service.AdminService;
import com.sammyun.service.profile.ProfileService;

/**
 * Controller - 校园介绍
 * 
 * @author xutianlong
 * @version [版本号, Apr 30, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("adminCampusProfileController")
@RequestMapping("/console/campus_profile")
public class CampusProfileController extends BaseController
{

    @Resource(name = "profileServiceImpl")
    private ProfileService profileService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ModelMap model, Profile profile, RedirectAttributes redirectAttributes)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        if (!isValid(profile))
        {
            return ERROR_VIEW;
        }
        if (profile.getId() != null)
        {
            profile.setDictSchool(dictSchool);
            profileService.update(profile);
        }
        else
        {
            profile.setDictSchool(dictSchool);
            profileService.save(profile);
        }
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        model.addAttribute("menuId", Profile.class.getSimpleName());
        return "redirect:edit.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap model)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        Profile profile = profileService.findBySchool(dictSchool);
        model.addAttribute("profile", profile);
        model.addAttribute("menuId", Profile.class.getSimpleName());
        return "/console/campus_profile/edit";
    }

}
