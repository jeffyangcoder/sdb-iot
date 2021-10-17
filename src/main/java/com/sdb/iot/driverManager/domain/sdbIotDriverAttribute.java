package com.sdb.iot.driverManager.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sdb.base.annotation.Excel;
import com.sdb.base.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 驱动属性信息对象 sdb_iot_driver_attribute
 * 
 * @author sdb
 * @date 2021-10-17
 */
public class sdbIotDriverAttribute extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 驱动id */
    @Excel(name = "驱动id")
    private Long driverId;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 属性 */
    @Excel(name = "属性")
    private String property;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 默认值 */
    @Excel(name = "默认值")
    private String def;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    /** 驱动状态信息 */
    private List<sdbIotDriver> sdbIotDriverList;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setProperty(String property) 
    {
        this.property = property;
    }

    public String getProperty() 
    {
        return property;
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
    public void setModifyTime(Date modifyTime) 
    {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() 
    {
        return modifyTime;
    }

    public List<sdbIotDriver> getsdbIotDriverList()
    {
        return sdbIotDriverList;
    }

    public void setsdbIotDriverList(List<sdbIotDriver> sdbIotDriverList)
    {
        this.sdbIotDriverList = sdbIotDriverList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("driverId", getDriverId())
            .append("name", getName())
            .append("property", getProperty())
            .append("type", getType())
            .append("def", getDef())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .append("sdbIotDriverList", getsdbIotDriverList())
            .toString();
    }
}
