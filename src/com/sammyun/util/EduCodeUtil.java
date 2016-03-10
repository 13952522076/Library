package com.sammyun.util;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sammyun.entity.dict.DictClass;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.dict.DictStudent;

public class EduCodeUtil
{
    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(DictSchool.class);

    /**
     * 生成学校code 规则：从10000排序
     * 
     * @param dictSchools
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String generateSchoolCode(List<DictSchool> dictSchools)
    {
        Long returnCode = 10000L;
        if (dictSchools != null && dictSchools.size() > 0)
        {
            for (DictSchool dictSchool : dictSchools)
            {
                String code = dictSchool.getCode();
                try
                {
                    Long codeLong = Long.parseLong(code);
                    if (codeLong > returnCode)
                    {
                        returnCode = codeLong;
                    }
                }
                catch (Exception e)
                {
                    logger.warn("EduCodeUtil.generateSchoolCode:  " + dictSchool.getId() + "学校编号错误；");
                }

            }
        }
        returnCode += 1;
        return returnCode.toString();
    }

    /**
     * 生成班级编号 规则：年份＋排序，共4位数，年份（2位）＋排序（2位），例：1501
     * 
     * @param dictClasses
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String generateClassCode(List<DictClass> dictClasses)
    {
        int year = DateUtil.getYear(new Date()) - 2000;
        Long returnCode = (long) (year * 100);
        if (dictClasses != null && dictClasses.size() > 0)
        {
            for (DictClass dictClass : dictClasses)
            {
                String code = dictClass.getCode();
                try
                {
                    Long codeLong = Long.parseLong(code);
                    if (codeLong > returnCode)
                    {
                        returnCode = codeLong;
                    }
                }
                catch (Exception e)
                {
                    logger.warn("EduCodeUtil.generateClassCode:  " + dictClass.getId() + "班级编号错误；");
                }
            }
        }
        returnCode += 1;
        return returnCode.toString();
    }
    
    /**
     * 生成学号 规则：班号＋排序 例：150101
     * 
     * @param dictClass
     * @param dictStudents
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String generateStudentNo(DictClass dictClass,List<DictStudent> dictStudents){
        String  dictClassCode = dictClass.getCode();
        Long number = 0L;
        if(dictStudents!=null && dictStudents.size()>0){
            for(DictStudent dictStudent:dictStudents){
                String studentNo =  dictStudent.getStudentNo();
                try
                {
                    Long stuNo = Long.parseLong(studentNo); 
                    if(stuNo>number){
                        number = stuNo;
                    }
                }
                catch (Exception e)
                {
                    logger.warn("EduCodeUtil.generateStudentNo:  " + dictStudent.getId() + "学号号错误；");
                }
                
            }
            number+=1; 
            return number.toString();
        }
        else{
            number+=1; 
            return dictClassCode+"01";
        }
        
        
    }

}
