package com.sammyun.controller.console;

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
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.poster.Poster;
import com.sammyun.service.AdminService;
import com.sammyun.service.poster.PosterService;

/**
 * Controller - 海报
 * 
 * @author xutianlong
 * @version [版本号, May 6, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("adminPosterController")
@RequestMapping("/console/poster")
public class PosterController extends BaseController
{

    @Resource(name = "posterServiceImpl")
    private PosterService posterService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
    	if(pageable.getPageSize()==12){
    		pageable.setPageSize(5);
    	}
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        Filter filter = new Filter("dictSchool", Operator.eq, dictSchool);
        pageable.addFilters(filter);
        model.addAttribute("page", posterService.findPage(pageable));
        model.addAttribute("menuId", Poster.class.getSimpleName());
        return "/console/poster/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("menuId", Poster.class.getSimpleName());
        return "/console/poster/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(ModelMap model, Poster poster, RedirectAttributes redirectAttributes)
    {
        DictSchool dictSchool = adminService.getCurrentDictSchool();
        poster.setDictSchool(dictSchool);
        poster.setStatus(true);
        poster.setViewCount(0L);
        if (!isValid(poster))
        {
            return ERROR_VIEW;
        }
        posterService.save(poster);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        model.addAttribute("menuId", Poster.class.getSimpleName());
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("poster", posterService.find(id));
        model.addAttribute("menuId", Poster.class.getSimpleName());
        return "/console/poster/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(ModelMap model, Poster poster, RedirectAttributes redirectAttributes)
    {
        Poster prePoster = posterService.find(poster.getId());
        prePoster.setContent(poster.getContent());
        prePoster.setPosterCover(poster.getPosterCover());
        prePoster.setPosterPosition(poster.getPosterPosition());
        prePoster.setPosterTitle(poster.getPosterTitle());
        prePoster.setPosterType(poster.getPosterType());
        prePoster.setStatus(poster.getStatus());
        if(prePoster.getViewCount()==null){
            prePoster.setViewCount(0L);
        }
        
        if (!isValid(prePoster))
        {
            return ERROR_VIEW;
        }
        posterService.update(prePoster);
        model.addAttribute("menuId", Poster.class.getSimpleName());
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
        posterService.delete(ids);
        return SUCCESS_MESSAGE;
    }
}
