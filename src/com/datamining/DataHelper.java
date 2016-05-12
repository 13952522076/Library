package com.datamining;

import java.util.List;
import java.util.Set;

import com.sammyun.entity.Admin;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.BookInfo;
import com.sammyun.entity.library.Mark;
import com.sammyun.entity.library.SimilarForm;
import com.sammyun.entity.library.Statistics;
import com.sammyun.service.AdminService;
import com.sammyun.service.library.BookInfoService;
import com.sammyun.service.library.BookService;
import com.sammyun.service.library.MarkService;
import com.sammyun.service.library.SimilarFormService;
import com.sammyun.service.library.StatisticsService;
import com.sammyun.util.SpringUtils;

/**
 * 数据挖掘帮助类 <一句话功能简述> <功能详细描述>
 * 
 * @author maxu
 * @version [版本号, 2016年4月9日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DataHelper
{
    BookService bookService = SpringUtils.getBean("bookServiceImpl", BookService.class);

    AdminService adminService = SpringUtils.getBean("adminServiceImpl", AdminService.class);

    SimilarFormService similarFormService = SpringUtils.getBean("similarFormServiceImpl", SimilarFormService.class);

    BookInfoService bookInfoService = SpringUtils.getBean("bookInfoServiceImpl", BookInfoService.class);

    MarkService markService = SpringUtils.getBean("markServiceImpl", MarkService.class);

    StatisticsService statisticsService = SpringUtils.getBean("statisticsServiceImpl", StatisticsService.class);

    /**
     * 同步书本和书本信息 <功能详细描述>
     * 
     * @see [类、类#方法、类#成员]
     */
    public void syncBookInfo()
    {
        List<Book> books = bookService.findAll();
        for (Book book : books)
        {
            BookInfo bookInfo = bookInfoService.findByBook(book);
            if (bookInfo == null)
            {
                this.saveBookInfo(book);
            }
        }
    }

    /**
     * 根据书本保存书本信息 <功能详细描述>
     * 
     * @param book
     * @see [类、类#方法、类#成员]
     */
    public void saveBookInfo(Book book)
    {
        BookInfo newBookInfo = new BookInfo();
        newBookInfo.setBook(book);
        Set<Mark> marks = book.getMarks();
        float rating = 0f;
        float voters = 0;
        if (marks != null && marks.size() != 0)
        {
            int sum = 0;
            for (Mark mark : marks)
            {
                sum += mark.getMark();
            }
            rating = sum / (marks.size());
            voters = marks.size();
        }
        newBookInfo.setRating(rating);
        newBookInfo.setVoters(voters);
        // 获取系统统计系统
        Statistics statistics = statisticsService.find(1L);
        Float score = WeightedRank.imdbTop250(rating, voters, statistics.getMinVoters(), statistics.getAverageScore());
        newBookInfo.setScore(score);
        bookInfoService.save(newBookInfo);
    }

    /**
     * 同步系统的所有书籍的平均分数 <一句话功能简述> <功能详细描述>
     * 
     * @see [类、类#方法、类#成员]
     */
    public void syncAverageScore()
    {
        List<Mark> marks = markService.findAll();
        float sum = 0;
        float average = 0;
        if (marks != null && marks.size() != 0)
        {
            for (Mark mark : marks)
            {
                sum += mark.getMark();
            }
            average = sum / (marks.size());
        }
        // 获取系统统计系统
        Statistics statistics = statisticsService.find(1L);
        statistics.setAverageScore(average);
        statisticsService.update(statistics);
    }

    /**
     * 同步每本书的评分 <功能详细描述>
     * 
     * @see [类、类#方法、类#成员]
     */
    public void syncBookScore()
    {
        List<Book> books = bookService.findAll();
        // 获取系统统计系统
        Statistics statistics = statisticsService.find(1L);
        for (Book book : books)
        {
            BookInfo bookInfo = bookInfoService.findByBook(book);
            if (bookInfo == null)
            {
                continue;
            }
            Set<Mark> marks = book.getMarks();
            float rating = 0f;
            float voters = 0;
            if (marks != null && marks.size() != 0)
            {
                int sum = 0;
                for (Mark mark : marks)
                {
                    sum += mark.getMark();
                }
                rating = sum / (marks.size());
                voters = marks.size();
            }
            bookInfo.setRating(rating);
            bookInfo.setVoters(voters);
            Float score = WeightedRank.imdbTop250(rating, voters, statistics.getMinVoters(),
                    statistics.getAverageScore());
            bookInfo.setScore(score);
            bookInfoService.update(bookInfo);
        }
    }

    /**
     * 同步相似度矩阵 <功能详细描述>
     * 
     * @see [类、类#方法、类#成员]
     */
    public void syncSimilarity()
    {

        List<Admin> admins = adminService.findAll();
        // 清空相似度矩阵
        List<SimilarForm> similarForms = similarFormService.findAll();
        if (similarForms != null && similarForms.size() > 0)
        {
            for (SimilarForm similarForm : similarForms)
            {
                similarFormService.delete(similarForm);
            }
        }

        // 遍历计算相似度保存
        for (Admin admin : admins)
        {
            List<Admin> students = adminService.findAll();
            students.remove(admin);

            Set<Mark> adminMarks = admin.getMarks();
            Similarity adminSimilarity = new Similarity();
            for (Mark adminMark : adminMarks)
            {
                Double mark = (double) adminMark.getMark();
                adminSimilarity.rating_map.put(adminMark.getBook().getId().toString(), mark);
            }

            for (Admin student : students)
            {
                Set<Mark> studentMarks = student.getMarks();
                Similarity studentSimilarity = new Similarity();
                for (Mark studentMark : studentMarks)
                {
                    Double mark = (double) studentMark.getMark();
                    studentSimilarity.rating_map.put(studentMark.getBook().getId().toString(), mark);
                }
                double similarity = adminSimilarity.getsimilarity_bydim(studentSimilarity);

                // 保存至similarForm
                SimilarForm similarForm = new SimilarForm(admin.getId(), student.getId(), similarity);
                similarFormService.save(similarForm);
            }

        }

    }

    /**
     * 一键同步所有 <功能详细描述>
     * 
     * @see [类、类#方法、类#成员]
     */
    public void syncStatistics()
    {
        this.syncAverageScore();
        this.syncBookInfo();
        this.syncBookScore();
        this.syncSimilarity();
    }

}
