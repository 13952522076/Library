package com.sammyun.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.sammyun.entity.Member.Gender;
import com.sammyun.entity.Member.MemberType;

public class ExcelImportUtil
{
    
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ExcelImportUtil.class);
    
    
    /**
     * 通过cell获取gender
     * <功能详细描述>
     * @param genderCell
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static Gender getGender(HSSFCell genderCell){
        String cellText = genderCell.toString();
        if(cellText.equals("男")){
            return Gender.male;
        }
        if(cellText.equals("女")) {
            return Gender.female;
        }
        else{
            return null; 
        }
    }
    
    public static MemberType getMemberType(HSSFCell genderCell){
        String cellText = genderCell.toString();
        if(cellText.equals("教师")){
            return MemberType.teacher;
        }
        if(cellText.equals("家长")) {
            return MemberType.patriarch;
        }
        else{
            return null; 
        }
    }
    
    /**
     * 获取inputStream文件流
     * <功能详细描述>
     * @param file
     * @return InputStream
     * @see [类、类#方法、类#成员]
     */
    public static InputStream getInputStream(MultipartFile file){
        if (file == null)  
            return null;  
        logger.info(file.getOriginalFilename());  
  
        String name = file.getOriginalFilename();// 获取上传文件名,包括路径  
        //name = name.substring(name.lastIndexOf("\\") + 1);// 从全路径中提取文件名  
        long size = file.getSize();  
        if ((name == null || name.equals("")) && size == 0)  
            return null;  
        InputStream in = null;
        try
        {
            in = file.getInputStream();
            return in;
        }
        catch (IOException e)
        {
            
            logger.error("ExcelController importMember: 转化excel错误:"+e.getMessage());
            return null;
        }  
        
    }
}