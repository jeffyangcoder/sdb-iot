package com.sdb.iot.dataManager.domain;

import com.sdb.base.annotation.Excel;
import com.sdb.base.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yangshiwei
 * @Description
 * @date 2021/10/28-11:01
 */

@Document(collection = "data")
public class SdbIotData extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Excel(name = "设备id")
    @Field("deviceid")
    private Long deviceId; // 设备

    @Excel(name = "设备位号id")
    @Field("devicepointid")
    private Long devicePointId; // 设备位号名称

    @Excel(name = "分组id")
    @Field("groupid")
    private Long groupId;

    @Excel(name = "原始值")
    @Field("originalvalue")
    private double originalValue; // 原始值

    @Excel(name = "处理值")
    @Field("processvalue")
    private double processValue; // 处理值

    @Excel(name = "自定义")
    private String custom; //  自定义

    @Excel(name = "采集时间")
    private LocalDateTime acquisition;// 采集时间

    @Excel(name = "时延")
    private int delay; // 时延

    @Excel(name = "保存时间")
    @Field("savedate")
    private LocalDateTime saveDate; // 保存时间

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getDevicePointId() {
        return devicePointId;
    }
    public void setDevicePointId(Long devicePointId) {
        this.devicePointId = devicePointId;
    }

    public Long getGroupId() {
        return groupId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public double getOriginalValue() {
        return originalValue;
    }
    public void setOriginalValue(double originalValue) {
        this.originalValue = originalValue;
    }

    public double getProcessValue() {
        return processValue;
    }
    public void setProcessValue(double processValue) {
        this.processValue = processValue;
    }

    public String getCustom() {
        return custom;
    }
    public void setCustom(String custom) {
        this.custom = custom;
    }

    public LocalDateTime getAcquisition() {
        return acquisition;
    }
    public void setAcquisition(LocalDateTime acquisition) {
        this.acquisition = acquisition;
    }

    public int getDelay() {
        return delay;
    }
    public void setDelay(int delay) {
        this.delay = delay;
    }

    public LocalDateTime getSaveDate() {
        return saveDate;
    }
    public void setSaveDate(LocalDateTime saveDate) {
        this.saveDate = saveDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("ID", getId())
                .append("deviceID", getDeviceId())
                .append("devicePointID", getDevicePointId())
                .append("groupID", getGroupId())
                .append("originalValue", getOriginalValue())
                .append("processValue",getProcessValue())
                .append("custom",getCustom())
                .append("acquisition",getAcquisition())
                .append("delay",getDelay())
                .append("saveData",getSaveDate())
                .toString();
    }
}
