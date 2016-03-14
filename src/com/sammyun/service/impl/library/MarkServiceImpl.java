package com.sammyun.service.impl.library;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.library.BookDao;
import com.sammyun.dao.library.MarkDao;
import com.sammyun.entity.library.Book;
import com.sammyun.entity.library.Mark;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.library.BookService;
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

}
