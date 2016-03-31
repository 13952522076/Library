package com.sammyun.service.impl.library;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.library.MarkDao;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.Mark;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.library.MarkService;

/**
 * ServiceImpl - 评分
 */
@Service("markServiceImpl")
public class MarkServiceImpl extends BaseServiceImpl<Mark, Long> implements MarkService
{
    @Resource(name = "markDaoImpl")
    private MarkDao markDao;

    @Resource(name = "markDaoImpl")
    public void setBaseDao(MarkDao markDao)
    {
        super.setBaseDao(markDao);
    }

    
    
    @Override
    public Map<Integer,Book> findMostMark(List<Book> books)
    {
        Map<Integer, Book> map = new TreeMap<Integer,Book>(new Comparator<Integer>() {
            public int compare(Integer obj1, Integer obj2) {
                // 降序排序
                return obj2.compareTo(obj1);
            }
        });
        for(Book book:books){
            List<Mark> marks = this.findListByBook(book);
            Integer count = marks.size();
            map.put(count,book);
        }
        
        System.out.println("map size:"+map.size());
       
        return map;
    }



    @Override
    public List<Mark> findListByBook(Book book)
    {
        return markDao.findListByBook(book);
    }

}
