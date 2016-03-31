/*
 * author:maxu
 * TEL:13952522076
 */

package com.sammyun.controller.console;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    /** 热门书籍 */
    @RequestMapping(value = "/hot", method = RequestMethod.GET)
    public String hot(){
        
        
        return "/console/recommend/hot";
    }

}
