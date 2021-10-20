package com.sdb.iot.deviceManager.service.impl;

import java.util.List;
import com.sdb.base.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdb.iot.deviceManager.mapper.sdbIotDeviceMapper;
import com.sdb.iot.deviceManager.domain.sdbIotDevice;
import com.sdb.iot.deviceManager.service.IsdbIotDeviceService;

/**
 * 设备Service业务层处理
 * 
 * @author sdb
 * @date 2021-10-20
 */
@Service
public class sdbIotDeviceServiceImpl implements IsdbIotDeviceService 
{
    @Autowired
    private sdbIotDeviceMapper sdbIotDeviceMapper;

    /**
     * 查询设备
     * 
     * @param id 设备ID
     * @return 设备
     */
    @Override
    public sdbIotDevice selectsdbIotDeviceById(Long id)
    {
        return sdbIotDeviceMapper.selectsdbIotDeviceById(id);
    }

    /**
     * 查询设备列表
     * 
     * @param sdbIotDevice 设备
     * @return 设备
     */
    @Override
    public List<sdbIotDevice> selectsdbIotDeviceList(sdbIotDevice sdbIotDevice)
    {
        return sdbIotDeviceMapper.selectsdbIotDeviceList(sdbIotDevice);
    }

    /**
     * 新增设备
     * 
     * @param sdbIotDevice 设备
     * @return 结果
     */
    @Override
    public int insertsdbIotDevice(sdbIotDevice sdbIotDevice)
    {
        sdbIotDevice.setCreateTime(DateUtils.getNowDate());
        return sdbIotDeviceMapper.insertsdbIotDevice(sdbIotDevice);
    }

    /**
     * 修改设备
     * 
     * @param sdbIotDevice 设备
     * @return 结果
     */
    @Override
    public int updatesdbIotDevice(sdbIotDevice sdbIotDevice)
    {
        sdbIotDevice.setUpdateTime(DateUtils.getNowDate());
        return sdbIotDeviceMapper.updatesdbIotDevice(sdbIotDevice);
    }

    /**
     * 批量删除设备
     * 
     * @param ids 需要删除的设备ID
     * @return 结果
     */
    @Override
    public int deletesdbIotDeviceByIds(Long[] ids)
    {
        return sdbIotDeviceMapper.deletesdbIotDeviceByIds(ids);
    }

    /**
     * 删除设备信息
     * 
     * @param id 设备ID
     * @return 结果
     */
    @Override
    public int deletesdbIotDeviceById(Long id)
    {
        return sdbIotDeviceMapper.deletesdbIotDeviceById(id);
    }
}
