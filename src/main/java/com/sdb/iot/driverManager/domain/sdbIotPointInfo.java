package com.sdb.iot.driverManager.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sdb.base.annotation.Excel;
import com.sdb.base.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 位号属性对象 sdb_iot_point_info
 * 
 * @author sdb
 * @date 2021-10-19
 */
public class sdbIotPointInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 驱动id */
    @Excel(name = "驱动id")
    private Long driverId;

    /** 显示名称 */
    @Excel(name = "显示名称")
    private String displayValue;

    /** 属性 */
    @Excel(name = "属性")
    private String value;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 默认值 */
    @Excel(name = "默认值")
    private String def;

    /** 备注 */
    @Excel(name = "备注")
    private String description;

    /** 修改日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDriverId(Long driverId) 
    {
        this.driverId = driverId;
    }

    public Long getDriverId() 
    {
        return driverId;
    }
    public void setDisplayValue(String displayValue) 
    {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() 
    {
        return displayValue;
    }
    public void setValue(String value) 
    {
        this.value = value;
    }

    public String getValue() 
    {
        return value;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setDef(String def) 
    {
        this.def = def;
    }

    public String getDef() 
    {
        return def;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setModifyTime(Date modifyTime) 
    {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() 
    {
        return modifyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("driverId", getDriverId())
            .append("displayValue", getDisplayValue())
            .append("value", getValue())
            .append("type", getType())
            .append("def", getDef())
            .append("description", getDescription())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
