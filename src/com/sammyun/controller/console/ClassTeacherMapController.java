/*
 * Copyright 2012-2014 sammyun.com.cn. All rights reserved.
 * Support: http://www.sammyun.com.cn
 * License: http://www.sammyun.com.cn/license
 */

package com.sammyun.controller.console;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sammyun.entity.Member;
import com.sammyun.entity.dict.DictClass;
import com.sammyun.service.dict.ClassTeacherMapService;
import com.sammyun.service.dict.DictClassService;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 班级老师
 * 
 * @author xutianlong
 * @version [版本号, May 3, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller("classTeacherMapController")
@RequestMapping("/console/classTeacherMap")
public class ClassTeacherMapController extends BaseController
{

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ClassTeacherMapController.class);
    
    /** 班级service */
    @Resource(name = "dictClassServiceImpl")
    private DictClassService dictClassService;
    
    /** 班级老师对应关系service */
    @Resource(name = "classTeacherMapServiceImpl")
    private ClassTeacherMapService classTeacherMapService;
    
    /**
     * ajax通过班级id获取 <功能详细描述>
     * 
     * @param dictClassId
     * @param response
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/getTeachers", method = RequestMethod.GET)
    public void getStudents(Long dictClassId, HttpServletResponse response)
    {
        DictClass dictClass = dictClassService.find(dictClassId);
        List<Member> members = classTeacherMapService.findMemberByClass(dictClass);
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), members);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }


}
