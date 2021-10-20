package com.sdb.iot.deviceManager.domain;

import com.sdb.base.annotation.Excel;
import com.sdb.base.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 位号配置对象 sdb_iot_device_point
 * 
 * @author sdb
 * @date 2021-10-20
 */
public class sdbIotDevicePoint extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 设备id */
    @Excel(name = "设备id")
    private Long deviceId;

    /** 模板id */
    @Excel(name = "模板id")
    private Long pointId;

    /** 位号属性id */
    @Excel(name = "位号属性id")
    private Long pointInfoId;

    /** 内容 */
    @Excel(name = "内容")
    private String value;

    /** 备注 */
    @Excel(name = "备注")
    private String description;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }
    public void setPointId(Long pointId) 
    {
        this.pointId = pointId;
    }

    public Long getPointId() 
    {
        return pointId;
    }
    public void setPointInfoId(Long pointInfoId) 
    {
        this.pointInfoId = pointInfoId;
    }

    public Long getPointInfoId() 
    {
        return pointInfoId;
    }
    public void setValue(String value) 
    {
        this.value = value;
    }

    public String getValue() 
    {
        return value;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceId", getDeviceId())
            .append("pointId", getPointId())
            .append("pointInfoId", getPointInfoId())
            .append("value", getValue())
            .append("description", getDescription())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
