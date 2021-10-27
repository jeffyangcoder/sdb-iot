package com.sdb.iot.dataManager.domain;

import com.sdb.base.annotation.Excel;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 驱动状态对象 sdb_iot_data
 *
 * @author sdb
 * @date 2021-10-17
 */
@Document(collection = "data")
public class sdbIotData {
    private static final long serialVersionUID = 1L;

    /** 数据id */
    private Long id;

    /** 驱动id */
    @Excel(name = "驱动id")
    private Long deviceId;

    /** 分组id */
    @Excel(name = "分组id")
    private Long groupId;

    /** 位号Id */
    @Excel(name = "位号id")
    private Long devicePointId;

    /** 原始值 */
    @Excel(name = "原始值")
    private double originalValue;

    /** 处理值 */
    @Excel(name = "处理值")
    private double processValue;

    /** 自定义*/
    @Excel(name = "自定义")
    private String custom;

    /**采集日期 */
    @Excel(name = "采集日期")
    private LocalDateTime acquisition;

    /** 时延*/
    @Excel(name = "时延")
    private int delay;

    /** 保存日期*/
    @Excel(name = "保存日期")
    private Date saveDate;


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

    public Long getGroupId() {
        return groupId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getDevicePointId() {
        return devicePointId;
    }
    public void setDevicePointId(Long devicePointId) {
        this.devicePointId = devicePointId;
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

    public Date getSaveDate() {
        return saveDate;
    }
    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id",getId())
                .append("deviceId",getDeviceId())
                .append("groupId",getGroupId())
                .append("devicePointId",getDevicePointId())
                .append("originalValue",getOriginalValue())
                .append("custom",getCustom())
                .append("acquisition",getAcquisition())
                .append("delay",getDelay())
                .append("saveDate",getSaveDate())
                .toString();
    }
}
