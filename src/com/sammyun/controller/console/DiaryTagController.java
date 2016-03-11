package com.sammyun.controller.console;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.entity.gd.DiaryTag;
import com.sammyun.entity.gd.GrowthDiary;
import com.sammyun.service.AdminService;
import com.sammyun.service.course.CourseNameService;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.service.gd.DiaryTagService;
import com.sammyun.service.gd.GrowthDiaryService;

/**
 * Controller - 成长记标签
 * 


 */
@Controller("diaryTagController")
@RequestMapping("/console/diaryTag")
public class DiaryTagController extends BaseController
{
    @Resource(name = "courseNameServiceImpl")
    private CourseNameService courseNameService;

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "diaryTagServiceImpl")
    private DiaryTagService diaryTagService;

    @Resource(name = "growthDiaryServiceImpl")
    private GrowthDiaryService growthDiaryService;

    /**
     * 成长记标签列表
     * 
     * @param pageable
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        // DictSchool dictSchool = adminService.getCurrentDictSchool();
        // Set<Member> members = dictSchool.getMembers();
        // if(members!=null && members.size()>0){
        // Filter filter = new Filter("member", Operator.in, members);
        // pageable.addFilters(filter);
        // }
        // else{
        // Filter filter = new Filter("id", Operator.eq, 0L);//构建一个空的过滤器
        // pageable.addFilters(filter);
        // }
        model.addAttribute("page", diaryTagService.findPage(pageable));
        model.addAttribute("menuId", DiaryTag.class.getSimpleName());
        return "/console/diaryTag/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        model.addAttribute("menuId", DiaryTag.class.getSimpleName());
        return "/console/diaryTag/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(DiaryTag diaryTag, RedirectAttributes redirectAttributes)
    {
        String name = diaryTag.getName().trim();
        diaryTag.setName(name);
        diaryTagService.save(diaryTag);
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        model.addAttribute("diaryTag", diaryTagService.find(id));
        model.addAttribute("menuId", DiaryTag.class.getSimpleName());
        return "/console/diaryTag/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(DiaryTag diaryTag, RedirectAttributes redirectAttributes)
    {
        DiaryTag preDiaryTag = diaryTagService.find(diaryTag.getId());
        preDiaryTag.setName(diaryTag.getName());
        if (!isValid(diaryTag))
        {
            return ERROR_VIEW;
        }
        diaryTagService.update(preDiaryTag);
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
        for (Long id : ids)
        {
            DiaryTag diaryTag = diaryTagService.find(id);
            if (diaryTag != null)
            {
                List<GrowthDiary> growthDiaries = growthDiaryService.findByDiaryTag(diaryTag);
                if (growthDiaries != null && growthDiaries.size() > 0)
                {
                    for (GrowthDiary growthDiary : growthDiaries)
                    {
                        growthDiary.getDiaryTags().remove(diaryTag);
                        growthDiaryService.update(growthDiary);
                    }
                }
                diaryTagService.delete(diaryTag);
            }
        }
        return SUCCESS_MESSAGE;
    }

    /**
     * 判断标签名是否存在 <功能详细描述>
     * 
     * @param name
     * @param preName
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/checkNameExsit", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkNameExsit(String name, String preName)
    {
        if (preName != null)
        {
            if (StringUtils.equalsIgnoreCase(name.trim(), preName.trim()))
            {
                return true;
            }
        }
        if (StringUtils.isEmpty(name))
        {
            return false;
        }
        List<DiaryTag> diaryTags = diaryTagService.findByName(name);
        if (diaryTags == null || diaryTags.size() == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
