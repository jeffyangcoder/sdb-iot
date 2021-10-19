package com.sdb.iot.driverManager.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sdb.base.annotation.Excel;
import com.sdb.base.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 驱动配置对象 sdb_iot_driver_info
 * 
 * @author sdb
 * @date 2021-10-18
 */
public class sdbIotDriverInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 驱动id */
    @Excel(name = "驱动id")
    private Long driverAttributeId;

    /** 模板id */
    @Excel(name = "模板id")
    private Long profileId;

    /** 内容值 */
    @Excel(name = "内容值")
    private String value;

    /** 备注 */
    @Excel(name = "备注")
    private String description;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setDriverAttributeId(Long driverAttributeId) 
    {
        this.driverAttributeId = driverAttributeId;
    }

    public Long getDriverAttributeId() 
    {
        return driverAttributeId;
    }
    public void setProfileId(Long profileId) 
    {
        this.profileId = profileId;
    }

    public Long getProfileId() 
    {
        return profileId;
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
            .append("driverAttributeId", getDriverAttributeId())
            .append("profileId", getProfileId())
            .append("value", getValue())
            .append("description", getDescription())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
