/*
 * author:maxu
 * TEL:13952522076
 */

package com.sammyun.controller.console;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sammyun.Filter;
import com.sammyun.Filter.Operator;
import com.sammyun.Message;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.Admin;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.news.News;
import com.sammyun.entity.recipe.Recipe;
import com.sammyun.entity.recipe.RecipeImage;
import com.sammyun.entity.recipe.RecipeSection;
import com.sammyun.entity.recipe.RecipeWeekDay;
import com.sammyun.service.AdminService;
import com.sammyun.service.library.BookService;
import com.sammyun.service.news.NewsService;
import com.sammyun.service.recipe.RecipeDetailService;
import com.sammyun.service.recipe.RecipeImageService;
import com.sammyun.service.recipe.RecipeService;
import com.sammyun.service.recipe.RecipeWeekDayService;
import com.sammyun.service.recipe.RecipesSectionService;
import com.sammyun.util.EduUtil;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 书籍controller
 */
@Controller("bookController")
@RequestMapping("/console/book")
public class BookController extends BaseController
{

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Resource(name = "bookServiceImpl")
    private BookService bookService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        Page<Book> page = bookService.findPage(pageable);
        model.addAttribute("page", page);
        return "/console/book/list";

    }
    
    /**
     * 下拉获取书本列表 <功能详细描述>
     * 
     * @param dictClassId
     * @param response
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/ajaxList", method = RequestMethod.GET)
    public void ajaxList(Pageable pageable, ModelMap model, HttpServletResponse response)
    {   
        Page<Book> page = bookService.findPage(pageable);
        List<Book> books = page.getContent();
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), books);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    
    
    
}
