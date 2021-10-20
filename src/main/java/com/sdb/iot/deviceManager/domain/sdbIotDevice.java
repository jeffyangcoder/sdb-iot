package com.sdb.iot.deviceManager.domain;

import com.sdb.base.annotation.Excel;
import com.sdb.base.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备对象 sdb_iot_device
 * 
 * @author sdb
 * @date 2021-10-20
 */
public class sdbIotDevice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String name;

    /** 模板id */
    @Excel(name = "模板id")
    private Long profileId;

    /** 所属分组id */
    @Excel(name = "所属分组id")
    private Long groupId;

    /** 在线状态 */
    @Excel(name = "在线状态")
    private String enable;

    /** 储存类型 */
    @Excel(name = "储存类型")
    private String mulit;

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
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setProfileId(Long profileId) 
    {
        this.profileId = profileId;
    }

    public Long getProfileId() 
    {
        return profileId;
    }
    public void setGroupId(Long groupId) 
    {
        this.groupId = groupId;
    }

    public Long getGroupId() 
    {
        return groupId;
    }
    public void setEnable(String enable) 
    {
        this.enable = enable;
    }

    public String getEnable() 
    {
        return enable;
    }
    public void setMulit(String mulit) 
    {
        this.mulit = mulit;
    }

    public String getMulit() 
    {
        return mulit;
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
            .append("name", getName())
            .append("profileId", getProfileId())
            .append("groupId", getGroupId())
            .append("enable", getEnable())
            .append("mulit", getMulit())
            .append("description", getDescription())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
