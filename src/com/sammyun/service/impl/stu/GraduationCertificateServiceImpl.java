package com.sammyun.service.impl.stu;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sammyun.dao.stu.GraduationCertificateDao;
import com.sammyun.entity.stu.GraduationCertificate;
import com.sammyun.service.impl.BaseServiceImpl;
import com.sammyun.service.stu.GraduationCertificateService;

/**
 * ServiceImpl - 毕业合影
 * 
 * @author Sencloud Team

 */
@Service("graduationCertificateServiceImpl")
public class GraduationCertificateServiceImpl extends BaseServiceImpl<GraduationCertificate, Long> implements
        GraduationCertificateService
{
    @Resource(name = "graduationCertificateDaoImpl")
    private GraduationCertificateDao graduationCertificateDao;

    @Resource(name = "graduationCertificateDaoImpl")
    public void setBaseDao(GraduationCertificateDao graduationCertificateDao)
    {
        super.setBaseDao(graduationCertificateDao);
    }
}
