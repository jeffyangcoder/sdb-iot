package com.sdb.iot.driverManager.domain;

import com.sdb.base.annotation.Excel;
import com.sdb.base.core.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 驱动状态对象 sdb_iot_driver
 * 
 * @author sdb
 * @date 2021-10-17
 */
public class sdbIotDriver extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 驱动id */
    private Long id;

    /** 驱动名称 */
    @Excel(name = "驱动名称")
    private String name;

    /** 驱动服务 */
    @Excel(name = "驱动服务")
    private String server;

    /** 驱动状态 */
    @Excel(name = "驱动状态")
    private Integer enable;

    /** 驱动主机 */
    @Excel(name = "驱动主机")
    private String host;

    /** 驱动端口 */
    @Excel(name = "驱动端口")
    private String port;

    /** 修改日期 */
    @Excel(name = "修改日期")
    private String modifyDate;

    /** 创建日期 */
    @Excel(name = "创建日期")
    private String createDate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setServer(String server) 
    {
        this.server = server;
    }

    public String getServer() 
    {
        return server;
    }
    public void setEnable(Integer enable) 
    {
        this.enable = enable;
    }

    public Integer getEnable() 
    {
        return enable;
    }
    public void setHost(String host) 
    {
        this.host = host;
    }

    public String getHost() 
    {
        return host;
    }
    public void setPort(String port) 
    {
        this.port = port;
    }

    public String getPort() 
    {
        return port;
    }
    public void setModifyDate(String modifyDate) 
    {
        this.modifyDate = modifyDate;
    }

    public String getModifyDate() 
    {
        return modifyDate;
    }
    public void setCreateDate(String createDate) 
    {
        this.createDate = createDate;
    }

    public String getCreateDate() 
    {
        return createDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("server", getServer())
            .append("enable", getEnable())
            .append("host", getHost())
            .append("port", getPort())
            .append("remark", getRemark())
            .append("modifyDate", getModifyDate())
            .append("createDate", getCreateDate())
            .toString();
    }
}
