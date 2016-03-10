package com.sammyun.dao.impl.stu;

import org.springframework.stereotype.Repository;

import com.sammyun.dao.impl.BaseDaoImpl;
import com.sammyun.dao.stu.GraduationCertificateDao;
import com.sammyun.entity.stu.GraduationCertificate;

/**
 * GraduationCertificate * DaoImpl - 毕业证书
 * 
 * @author Sencloud Team
 * @version 3.0
 */
@Repository("graduationCertificateDaoImpl")
public class GraduationCertificateDaoImpl extends BaseDaoImpl<GraduationCertificate, Long> implements GraduationCertificateDao
{

}
