package com.sammyun.codeGenerator;

/**
 * ID生成器
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  maxu
 * @version  [版本号, 2015年8月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class Generator
{
    /**
     * 字段名
     */
    private String fieldName;
    
    /**
     * 字段名构造函数
     */
    public Generator(String filedName){
        setFieldName(filedName);
    }
    
    public Generator(){
        
    }
    
    public DefaultCodeGenerator getDefaultCodeGenerator(){
        return null;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }
    
    
}
