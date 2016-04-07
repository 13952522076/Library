/*
 * author:maxu
 * TEL:13952522076
 */

package com.sammyun.controller.console;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sammyun.entity.library.Book;
import com.sammyun.form.KeyValue;
import com.sammyun.service.AdminService;
import com.sammyun.service.library.BookService;
import com.sammyun.service.library.CollectionService;
import com.sammyun.service.library.MarkService;

/**
 * Controller - 书籍推荐controller
 */
@Controller("recommendController")
@RequestMapping("/console/recommend")
public class RecommendController extends BaseController
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
     * 热门书籍 
     * 评论最多，评分最高，收藏最多
     * 最多5个
     * */
    @RequestMapping(value = "/hot", method = RequestMethod.GET)
    public String hot(ModelMap model)
    {
        // 评论最多，评分最高，收藏最多
        List<Book> books = bookService.findAll();
        
        List<KeyValue> mostMarks = markService.findMostMark(books);
        model.addAttribute("mostMarks", mostMarks);
        
        
        //评分最高
        //S.t. 1,最多返回5个；2，超过10个的评分；
        
        
        return "/console/recommend/hot";
    }

}
