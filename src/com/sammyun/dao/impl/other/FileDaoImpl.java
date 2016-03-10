package com.sammyun.dao.impl.other;

import com.sammyun.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;
import com.sammyun.dao.other.FileDao;
import com.sammyun.entity.other.File;

/**
 * File * DaoImpl - 文件
 * 
 * @author Sencloud Team

 */
@Repository("fileDaoImpl")
public class FileDaoImpl extends BaseDaoImpl<File, Long> implements FileDao  {

}
