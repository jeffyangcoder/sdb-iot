package com.sdb.iot.profileManager.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sdb.base.annotation.Excel;
import com.sdb.base.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 位号对象 sdb_iot_point
 * 
 * @author sdb
 * @date 2021-10-20
 */
public class sdbIotPoint extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 模板id */
    @Excel(name = "模板id")
    private Long profileId;

    /** 位号值 */
    @Excel(name = "位号值")
    private String value;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 读/写 */
    @Excel(name = "读/写")
    private Long rw;

    /** 基础值 */
    @Excel(name = "基础值")
    private BigDecimal base;

    /** 最小值 */
    @Excel(name = "最小值")
    private BigDecimal minimum;

    /** 最大值 */
    @Excel(name = "最大值")
    private BigDecimal maximum;

    /** 倍数 */
    @Excel(name = "倍数")
    private BigDecimal multiple;

    /** 累计标识 */
    @Excel(name = "累计标识")
    private String accrue;

    /** 数据格式 */
    @Excel(name = "数据格式")
    private String format;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

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
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setRw(Long rw) 
    {
        this.rw = rw;
    }

    public Long getRw() 
    {
        return rw;
    }
    public void setBase(BigDecimal base) 
    {
        this.base = base;
    }

    public BigDecimal getBase() 
    {
        return base;
    }
    public void setMinimum(BigDecimal minimum) 
    {
        this.minimum = minimum;
    }

    public BigDecimal getMinimum() 
    {
        return minimum;
    }
    public void setMaximum(BigDecimal maximum) 
    {
        this.maximum = maximum;
    }

    public BigDecimal getMaximum() 
    {
        return maximum;
    }
    public void setMultiple(BigDecimal multiple) 
    {
        this.multiple = multiple;
    }

    public BigDecimal getMultiple() 
    {
        return multiple;
    }
    public void setAccrue(String accrue) 
    {
        this.accrue = accrue;
    }

    public String getAccrue() 
    {
        return accrue;
    }
    public void setFormat(String format) 
    {
        this.format = format;
    }

    public String getFormat() 
    {
        return format;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
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
            .append("profileId", getProfileId())
            .append("value", getValue())
            .append("type", getType())
            .append("rw", getRw())
            .append("base", getBase())
            .append("minimum", getMinimum())
            .append("maximum", getMaximum())
            .append("multiple", getMultiple())
            .append("accrue", getAccrue())
            .append("format", getFormat())
            .append("unit", getUnit())
            .append("modifyTime", getModifyTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
