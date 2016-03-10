package com.sammyun.entity.system;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sammyun.entity.BaseEntity;

/**
 * 系统字典 <br />
 * 需要一次性加载到内存，作为每次检索的数据集合
 * 
 * @author xutianlong
 * @version [版本号, Jul 9, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Entity
@Table(name = "t_pe_system_dict")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_pe_system_dict_sequence")
public class SystemDict extends BaseEntity
{

    /**
     * 注释内容
     */
    private static final long serialVersionUID = 2407992855557854461L;

    /** 缓存名称 */
    public static final String CACHE_NAME = "systemDict";

    /** 缓存KEY */
    public static final Integer CACHE_KEY = 1;

    /** 对应字段 */
    private String field;

    /** 字段名 */
    private String fieldName;

    /** 代码 */
    private String code;

    /** 代码描述 */
    private String codeDesc;

    /** 是否 */
    private Boolean enabled;

    /** 编辑模式 */
    private Boolean editMode;

    /** 备注 */
    private String remark;

    /**
     * @return 返回 field
     */
    public String getField()
    {
        return field;
    }

    /**
     * @param 对field进行赋值
     */
    public void setField(String field)
    {
        this.field = field;
    }

    /**
     * @return 返回 fieldName
     */
    public String getFieldName()
    {
        return fieldName;
    }

    /**
     * @param 对fieldName进行赋值
     */
    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    /**
     * @return 返回 code
     */
    public String getCode()
    {
        return code;
    }

    /**
     * @param 对code进行赋值
     */
    public void setCode(String code)
    {
        this.code = code;
    }

    /**
     * @return 返回 codeDesc
     */
    public String getCodeDesc()
    {
        return codeDesc;
    }

    /**
     * @param 对codeDesc进行赋值
     */
    public void setCodeDesc(String codeDesc)
    {
        this.codeDesc = codeDesc;
    }

    /**
     * @return 返回 enabled
     */
    public Boolean getEnabled()
    {
        return enabled;
    }

    /**
     * @param 对enabled进行赋值
     */
    public void setEnabled(Boolean enabled)
    {
        this.enabled = enabled;
    }

    /**
     * @return 返回 editMode
     */
    public Boolean getEditMode()
    {
        return editMode;
    }

    /**
     * @param 对editMode进行赋值
     */
    public void setEditMode(Boolean editMode)
    {
        this.editMode = editMode;
    }

    /**
     * @return 返回 remark
     */
    public String getRemark()
    {
        return remark;
    }

    /**
     * @param 对remark进行赋值
     */
    public void setRemark(String remark)
    {
        this.remark = remark;
    }

}
