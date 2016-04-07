package com.sammyun.controller.console;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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
import com.sammyun.entity.announcement.Announcement;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.MemberService;
import com.sammyun.service.announcement.AnnouncementService;
import com.sammyun.service.message.MessageService;
import com.sammyun.util.DateUtil;

/**
 * 通知公告
 * 
 * @author xutianlong
 * @version [版本号, Apr 29, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("adminAnnouncementController")
@RequestMapping("/console/announcement")
public class AnnouncementController extends BaseController
{

    @Resource(name = "announcementServiceImpl")
    private AnnouncementService announcementService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;
    
    @Resource(name = "messageServiceImpl")
    private MessageService messageService;
    
    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        Admin admin = adminService.getCurrent();
        DictSchool dictSchool = admin.getDictSchool();
        Filter filter = new Filter("dictSchool", Operator.eq, dictSchool);
        pageable.addFilters(filter);
        model.addAttribute("page", announcementService.findPage(pageable));
        model.addAttribute("menuId", Announcement.class.getSimpleName());
        return "/console/announcement/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("menuId", Announcement.class.getSimpleName());
        return "/console/announcement/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ModelMap model, Announcement announcement, Long newsCategoryId,
            RedirectAttributes redirectAttributes,HttpServletRequest request)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        announcement.setTimePublish(DateUtil.getDate());
        announcement.setStatus(0);
        announcement.setDictSchool(dictSchool);
        if (!isValid(announcement))
        {
            return ERROR_VIEW;
        }
        announcementService.save(announcement);

        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        model.addAttribute("menuId", Announcement.class.getSimpleName());
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("announcement", announcementService.find(id));
        model.addAttribute("menuId", Announcement.class.getSimpleName());
        return "/console/announcement/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap model, Announcement announcement, Long newsCategoryId,
            RedirectAttributes redirectAttributes)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        announcement.setDictSchool(dictSchool);
        announcement.setTimePublish(DateUtil.getDate());
        announcement.setStatus(0);
        if (!isValid(announcement))
        {
            return ERROR_VIEW;
        }
        announcementService.update(announcement);
        model.addAttribute("menuId", Announcement.class.getSimpleName());
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
        announcementService.delete(ids);
        return SUCCESS_MESSAGE;
    }

}
