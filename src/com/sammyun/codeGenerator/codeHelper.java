package com.sammyun.codeGenerator;

/**
 * code生成器 静态类解决多线程兵法访问生成code的问题
 * 此类第一次实例化会执行所有的static代码块，如果想按需加载这些code生成器，则应该一个code写一个静态类就可以
 * 
 * @author  maxu
 * @version  [版本号, 2015年8月8日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class codeHelper
{
   /**
    * 学校Code
    */
    private static DefaultCodeGenerator defaultCodeGenerator4schoolCode = null;

    static{
        Generator generator_schoolCode =  new Generator("code");
        generator_schoolCode.setFieldName("code");
        defaultCodeGenerator4schoolCode = generator_schoolCode.getDefaultCodeGenerator();
    }
    
    public static String getRoleID() throws Exception
    {
        return defaultCodeGenerator4schoolCode.create();
    }
    
    
    public static DefaultCodeGenerator getDefaultCodeGenerator4schoolCode(){
        return defaultCodeGenerator4schoolCode;
    }
}
