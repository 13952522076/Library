/*
 * author:maxu
 * TEL:13952522076
 */

package com.sammyun.controller.console;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.datamining.DataHelper;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.entity.Admin;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.Collection;
import com.sammyun.entity.library.Mark;
import com.sammyun.service.AdminService;
import com.sammyun.service.library.BookService;
import com.sammyun.service.library.CollectionService;
import com.sammyun.service.library.MarkService;
import com.sammyun.util.JsonUtils;

/**
 * Controller - 书籍controller
 */
@Controller("bookController")
@RequestMapping("/console/book")
public class BookController extends BaseController
{

    @Resource(name = "bookServiceImpl")
    private BookService bookService;

    @Resource(name = "adminServiceImpl")
    private AdminService adminService;

    @Resource(name = "markServiceImpl")
    private MarkService markService;

    @Resource(name = "collectionServiceImpl")
    private CollectionService collectionService;

    /**
     * 列表
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Pageable pageable, ModelMap model)
    {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "/console/book/list";

    }
    
    /**
     * 列表
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(Pageable pageable,String bookKeyword, ModelMap model)
    {
        List<Book> books = bookService.findByKeyword(bookKeyword);
        model.addAttribute("books", books);
        model.addAttribute("bookKeyword", bookKeyword);
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

    /**
     * 进入图书添加页面
     * 
     * @param pageable
     * @param model
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(ModelMap model)
    {

        return "/console/book/add";

    }

    /**
     * 保存书本 <功能详细描述>
     * 
     * @param book
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book)
    {
        bookService.save(book);
        return "redirect:list.ct";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(Long id, ModelMap model)
    {
        Book book = bookService.find(id);
        model.addAttribute("book", book);
        return "/console/book/detail";

    }

    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param book
     * @return
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/rating", method = RequestMethod.POST)
    public String rating(Mark mark, Long bookId)
    {
        Book book = bookService.find(bookId);
        mark.setBook(book);
        Admin admin = adminService.getCurrent();
        mark.setAdmin(admin);
        markService.save(mark);
        return "redirect:list.ct";
    }

    /**
     * 收藏书籍 <功能详细描述>
     * 
     * @param bookId
     * @see [类、类#方法、类#成员]
     */
    @RequestMapping(value = "/collect", method = RequestMethod.POST)
    public void collect(Long bookId, HttpServletResponse response)
    {
        Admin admin = adminService.getCurrent();
        Book book = bookService.find(bookId);
        List<Collection> collections = collectionService.findByBookAndAdmin(book, admin);
        String message = "";
        if (collections == null || collections.size() == 0)
        {
            Collection collection = new Collection();
            collection.setAdmin(admin);
            collection.setBook(book);
            collectionService.save(collection);
            message = "save";
        }
        else
        {
            for (Collection collection : collections)
            {
                collectionService.delete(collection);
            }
            message = "delete";
        }

        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), message);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    
    
    
    
    @RequestMapping(value = "/sync", method = RequestMethod.GET)
    public void sync(ModelMap model, HttpServletResponse response)
    {
        DataHelper dataHelper = new DataHelper();
        dataHelper.syncStatistics();
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), "success");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
