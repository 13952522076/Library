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

import com.sammyun.Filter;
import com.sammyun.Filter.Operator;
import com.sammyun.Message;
import com.sammyun.Pageable;
import com.sammyun.entity.attendance.AttendanceEquipment;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.attendance.AttendanceEquipmentService;
import com.sammyun.service.dict.DictSchoolService;

/**
 * Entity - 考勤机
 * 
 * @author xutianlong
 * @version [版本号, Apr 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("attendanceEquipmentController")
@RequestMapping("/console/attendance_equipment")
public class AttendanceEquipmentController extends BaseController
{

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;
    
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "attendanceEquipmentServiceImpl")
    private AttendanceEquipmentService attendanceEquipmentService;

    /**
     * 考勤机列表
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
        model.addAttribute("page", attendanceEquipmentService.findPage(pageable));
        model.addAttribute("menuId", AttendanceEquipment.class.getSimpleName());
        return "/console/attendance_equipment/list";
    }

    /**
     * 添加
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {
        List<DictSchool> dictSchools = dictSchoolService.findAll();
        model.addAttribute("dictSchools", dictSchools);
        model.addAttribute("menuId", AttendanceEquipment.class.getSimpleName());
        return "/console/attendance_equipment/add";
    }

    /**
     * 保存
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(AttendanceEquipment attendanceEquipment,
            RedirectAttributes redirectAttributes, ModelMap model)
    {

        DictSchool dictSchool = adminService.getCurrentDictSchool();
        attendanceEquipment.setDictSchool(dictSchool);
        if (!isValid(dictSchool))
        {
            return ERROR_VIEW;
        }
        attendanceEquipmentService.save(attendanceEquipment);
        model.addAttribute("menuId", AttendanceEquipment.class.getSimpleName());
        addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
        return "redirect:list.ct";
    }

    /**
     * 编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(Long id, ModelMap model)
    {
        List<DictSchool> dictSchools = dictSchoolService.findAll();
        model.addAttribute("dictSchools", dictSchools);
        model.addAttribute("attendanceEquipment", attendanceEquipmentService.find(id));
        model.addAttribute("menuId", AttendanceEquipment.class.getSimpleName());
        return "/console/attendance_equipment/edit";
    }

    /**
     * 更新
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(AttendanceEquipment attendanceEquipment, 
            RedirectAttributes redirectAttributes, ModelMap model)
    {
        DictSchool dictSchool = attendanceEquipmentService.find(attendanceEquipment.getId()).getDictSchool();
        attendanceEquipment.setDictSchool(dictSchool);
        attendanceEquipmentService.update(attendanceEquipment);
        model.addAttribute("menuId", AttendanceEquipment.class.getSimpleName());
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
        attendanceEquipmentService.delete(ids);
        return SUCCESS_MESSAGE;
    }
    
    /**
     * 检查考勤机序列号是否被已存在
     */
    @RequestMapping(value = "/check_equipmentSequence", method = RequestMethod.GET)
    public @ResponseBody
    boolean checkSequence(String equipmentSequence)
    {
        if (StringUtils.isEmpty(equipmentSequence))
        {
            return false;
        }
        if (attendanceEquipmentService.sequenceExsit(equipmentSequence))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    /**
     * 编辑时检查考勤机序列号是否被已存在
     * <功能详细描述>
     * @param equipmentSequence
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/edit_check_equipmentSequence", method = RequestMethod.GET)
    public @ResponseBody
    boolean editCheckSequence(String equipmentSequence,String previousSequence)
    {
        if(StringUtils.equalsIgnoreCase(equipmentSequence, previousSequence)){
            return true;
        }
        if (StringUtils.isEmpty(equipmentSequence))
        {
            return false;
        }
        if (attendanceEquipmentService.sequenceExsit(equipmentSequence))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    
    
}
