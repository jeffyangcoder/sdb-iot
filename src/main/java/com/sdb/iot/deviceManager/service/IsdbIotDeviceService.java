package com.sdb.iot.deviceManager.service;

import java.util.List;
import com.sdb.iot.deviceManager.domain.sdbIotDevice;

/**
 * 设备Service接口
 * 
 * @author sdb
 * @date 2021-10-20
 */
public interface IsdbIotDeviceService 
{
    /**
     * 查询设备
     * 
     * @param id 设备ID
     * @return 设备
     */
    public sdbIotDevice selectsdbIotDeviceById(Long id);

    /**
     * 查询设备列表
     * 
     * @param sdbIotDevice 设备
     * @return 设备集合
     */
    public List<sdbIotDevice> selectsdbIotDeviceList(sdbIotDevice sdbIotDevice);

    /**
     * 新增设备
     * 
     * @param sdbIotDevice 设备
     * @return 结果
     */
    public int insertsdbIotDevice(sdbIotDevice sdbIotDevice);

    /**
     * 修改设备
     * 
     * @param sdbIotDevice 设备
     * @return 结果
     */
    public int updatesdbIotDevice(sdbIotDevice sdbIotDevice);

    /**
     * 批量删除设备
     * 
     * @param ids 需要删除的设备ID
     * @return 结果
     */
    public int deletesdbIotDeviceByIds(Long[] ids);

    /**
     * 删除设备信息
     * 
     * @param id 设备ID
     * @return 结果
     */
    public int deletesdbIotDeviceById(Long id);
}
