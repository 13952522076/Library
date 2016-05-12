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
import com.sammyun.entity.Admin;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.Mark;
import com.sammyun.form.KeyValue;
import com.sammyun.service.AdminService;
import com.sammyun.service.library.BookInfoService;
import com.sammyun.service.library.BookService;
import com.sammyun.service.library.CollectionService;
import com.sammyun.service.library.MarkService;
import com.sammyun.service.library.SimilarFormService;
import com.sammyun.util.JsonUtils;

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

    @Resource(name = "bookInfoServiceImpl")
    private BookInfoService bookInfoService;

    @Resource(name = "collectionServiceImpl")
    private CollectionService collectionService;

    @Resource(name = "similarFormServiceImpl")
    private SimilarFormService similarFormService;

    DataHelper dataHelper = new DataHelper();

    /**
     * 热门书籍 评论最多，评分最高，收藏最多 最多5个
     */
    @RequestMapping(value = "/hot", method = RequestMethod.GET)
    public String hot(ModelMap model)
    {
        // 评论最多，评分最高，收藏最多
        List<Book> books = bookService.findAll();

        List<KeyValue> mostMarks = markService.findMostMark(books);
        model.addAttribute("mostMarks", mostMarks);

        // 初始化一次就好
        // dataHelper.syncStatistics();

        // 评分最高
        // S.t. 1,最多返回5个；
        List<KeyValue> topMarks = bookInfoService.findTopMark(books);
        model.addAttribute("topMarks", topMarks);

        return "/console/recommend/hot";
    }

    @RequestMapping(value = "/similar", method = RequestMethod.GET)
    public String similar(ModelMap model)
    {
        Admin admin = adminService.getCurrent();
        List<Admin> simStudents = similarFormService.getSimialrStudents(admin.getId());
        model.addAttribute("simStudents", simStudents);
        return "/console/recommend/similar";
    }

    /** ajax获取最爱书籍列表 */
    @RequestMapping(value = "/favorite", method = RequestMethod.GET)
    public void editInfo(Long id, HttpServletResponse response)
    {
        Admin admin = adminService.find(id);
        List<Mark> marks = markService.findListByAdmin(admin);
        if (marks.size() >= 5)
        {
            marks = marks.subList(0, 4);
        }
        try
        {
            response.setContentType("text/html; charset=UTF-8");
            JsonUtils.writeValue(response.getWriter(), marks);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
