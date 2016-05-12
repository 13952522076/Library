package com.sammyun.service.impl.library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.AdminDao;
import com.sammyun.dao.library.SimilarFormDao;
import com.sammyun.entity.Admin;
import com.sammyun.entity.library.SimilarForm;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.library.SimilarFormService;

/**
 * ServiceImpl - 相似度矩阵
 */
@Service("similarFormServiceImpl")
public class SimilarFormServiceImpl extends BaseServiceImpl<SimilarForm, Long> implements SimilarFormService
{
    @Resource(name = "similarFormDaoImpl")
    private SimilarFormDao similarFormDao;

    @Resource(name = "similarFormDaoImpl")
    public void setBaseDao(SimilarFormDao similarFormDao)
    {
        super.setBaseDao(similarFormDao);
    }
    
    @Resource(name = "adminDaoImpl")
    private AdminDao adminDao;

    @Override
    public List<SimilarForm> getSimilarForms(Long adminId)
    {
        // TODO Auto-generated method stub
        List<SimilarForm> similarForms = similarFormDao.getSimilarForms(adminId);
        
        //排序
        Collections.sort(similarForms, new Comparator<SimilarForm>() {
            public int compare(SimilarForm arg0, SimilarForm arg1) {
                return arg1.getSimilarity().compareTo(arg0.getSimilarity());
            }
        });
        return similarForms;
    }

    @Override
    public List<Admin> getSimialrStudents(Long adminId)
    {
        List<SimilarForm> similarForms = this.getSimilarForms(adminId);
        List<Admin> students  = new ArrayList<Admin>();
        if(similarForms!=null && similarForms.size()>=6){
            similarForms = similarForms.subList(0, 6);
        }
        for(SimilarForm similarForm:similarForms){
            Long studentId =  similarForm.getStudentId();
            Admin student = adminDao.find(studentId);
            students.add(student);
        }
        return students;
    }

}
