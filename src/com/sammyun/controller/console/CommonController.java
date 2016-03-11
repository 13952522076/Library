/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */
package com.sammyun.controller.console;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.sammyun.entity.Area;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.service.AdminService;
import com.sammyun.service.AreaService;
import com.sammyun.service.CaptchaService;
import com.sammyun.service.MemberService;
import com.sammyun.service.dict.DictStudentService;
import com.sammyun.service.message.MessageService;

/**
 * Controller - 共用
 * 


 */
@Controller("adminCommonController")
@RequestMapping("/console/common")
public class CommonController implements ServletContextAware
{

    @Value("${system.name}")
    private String systemName;

    @Value("${system.version}")
    private String systemVersion;

    @Value("${system.description}")
    private String systemDescription;

    @Value("${system.show_powered}")
    private Boolean systemShowPowered;

    @Resource(name = "areaServiceImpl")
    private AreaService areaService;

    @Resource(name = "captchaServiceImpl")
    private CaptchaService captchaService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "messageServiceImpl")
    private MessageService messageService;
    
    @Resource(name = "adminServiceImpl")
    private AdminService adminService;
    
    @Resource(name = "dictStudentServiceImpl")
    private DictStudentService dictStudentService;

    /** servletContext */
    private ServletContext servletContext;

    public void setServletContext(ServletContext servletContext)
    {
        this.servletContext = servletContext;
    }

    /**
     * 主页
     */
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(ModelMap model,HttpServletRequest request)
    {
    	DictSchool dictSchool = adminService.getCurrentDictSchool();
    	String description = dictSchool.getDescription();
    	int classNumber = dictSchool.getDictClasses().size();
    	int teacherNumber = 0;
    	int patriarchNumber =0;
    	int studentNumber =0;
    	try {
    	    teacherNumber = memberService.findBySchoolAndType(dictSchool, MemberType.teacher).size();
    		patriarchNumber = memberService.findBySchoolAndType(dictSchool, MemberType.patriarch).size();
    		studentNumber = dictStudentService.findStudentsBySchool(dictSchool).size();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	int newsNumber = dictSchool.getNewses().size();
    	model.addAttribute("newsNumber", newsNumber);
    	model.addAttribute("classNumber", classNumber);
    	model.addAttribute("teacherNumber", teacherNumber);
    	model.addAttribute("patriarchNumber", patriarchNumber);
    	model.addAttribute("studentNumber", studentNumber);
    	model.addAttribute("dictSchool", dictSchool);
    	model.addAttribute("description", description);
    	String realName = adminService.getCurrent().getName();
    	request.getSession().setAttribute("realName", realName);
    	request.getSession().setAttribute("iconPhoto", adminService.getCurrent().getIconPhoto());
    	request.getSession().setAttribute("schoolName", dictSchool.getName());
        return "/console/common/main";
    }

    /**
     * 首页
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(ModelMap model,HttpServletRequest request)
    {
//        model.addAttribute("systemName", systemName);
//        model.addAttribute("systemVersion", systemVersion);
//        model.addAttribute("systemDescription", systemDescription);
//        model.addAttribute("systemShowPowered", systemShowPowered);
//        model.addAttribute("javaVersion", System.getProperty("java.version"));
//        model.addAttribute("javaHome", System.getProperty("java.home"));
//        model.addAttribute("osName", System.getProperty("os.name"));
//        model.addAttribute("osArch", System.getProperty("os.arch"));
//        model.addAttribute("serverInfo", servletContext.getServerInfo());
//        model.addAttribute("servletVersion", servletContext.getMajorVersion() + "." + servletContext.getMinorVersion());
//        return "/console/common/index";
    	DictSchool dictSchool = adminService.getCurrentDictSchool();
    	String description = dictSchool.getDescription();
    	int classNumber = dictSchool.getDictClasses().size();
    	int teacherNumber = 0;
    	int patriarchNumber =0;
    	int studentNumber =0;
    	try {
    		teacherNumber = memberService.findBySchoolAndType(dictSchool, MemberType.teacher).size();
    		patriarchNumber = memberService.findBySchoolAndType(dictSchool, MemberType.patriarch).size();
    		studentNumber = dictStudentService.findStudentsBySchool(dictSchool).size();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	int newsNumber = dictSchool.getNewses().size();
    	model.addAttribute("newsNumber", newsNumber);
    	model.addAttribute("classNumber", classNumber);
    	model.addAttribute("teacherNumber", teacherNumber);
    	model.addAttribute("patriarchNumber", patriarchNumber);
    	model.addAttribute("studentNumber", studentNumber);
    	model.addAttribute("dictSchool", dictSchool);
    	model.addAttribute("description", description);
    	String realName = adminService.getCurrentUsername();
    	request.getSession().setAttribute("realName", realName);
    	request.getSession().setAttribute("iconPhoto", adminService.getCurrent().getIconPhoto());
    	request.getSession().setAttribute("schoolName", dictSchool.getName());
		return "/console/common/main";
    }

    /**
     * 地区
     */
    @RequestMapping(value = "/area", method = RequestMethod.GET)
    public @ResponseBody
    Map<Long, String> area(Long parentId)
    {
        List<Area> areas = new ArrayList<Area>();
        Area parent = areaService.find(parentId);
        if (parent != null)
        {
            areas = new ArrayList<Area>(parent.getChildren());
        }
        else
        {
            areas = areaService.findRoots();
        }
        Map<Long, String> options = new HashMap<Long, String>();
        for (Area area : areas)
        {
            options.put(area.getId(), area.getName());
        }
        return options;
    }

    /**
     * 验证码
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    public void image(String captchaId, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        if (StringUtils.isEmpty(captchaId))
        {
            captchaId = request.getSession().getId();
        }
        String pragma = new StringBuffer().append("yB").append("-").append("der").append("ewoP").reverse().toString();
        String value = new StringBuffer().append("moc").append(".").append("oc-om.").append("www").reverse().toString();
        response.addHeader(pragma, value);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        ServletOutputStream servletOutputStream = null;
        try
        {
            servletOutputStream = response.getOutputStream();
            BufferedImage bufferedImage = captchaService.buildImage(captchaId);
            ImageIO.write(bufferedImage, "jpg", servletOutputStream);
            servletOutputStream.flush();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            IOUtils.closeQuietly(servletOutputStream);
        }
    }

    /**
     * 错误提示
     */
    @RequestMapping("/error")
    public String error()
    {
        return "/console/common/error";
    }

    /**
     * 权限错误
     */
    @RequestMapping("/unauthorized")
    public String unauthorized(HttpServletRequest request, HttpServletResponse response)
    {
        String requestType = request.getHeader("X-Requested-With");
        if (requestType != null && requestType.equalsIgnoreCase("XMLHttpRequest"))
        {
            response.addHeader("loginStatus", "unauthorized");
            try
            {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return null;
        }
        return "/console/common/unauthorized";
    }

}
