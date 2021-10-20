package com.sdb.iot.deviceManager.mapper;

import java.util.List;
import com.sdb.iot.deviceManager.domain.sdbIotDevicePoint;

/**
 * 位号配置Mapper接口
 * 
 * @author sdb
 * @date 2021-10-20
 */
public interface sdbIotDevicePointMapper 
{
    /**
     * 查询位号配置
     * 
     * @param id 位号配置ID
     * @return 位号配置
     */
    public sdbIotDevicePoint selectsdbIotDevicePointById(Long id);

    /**
     * 查询位号配置列表
     * 
     * @param sdbIotDevicePoint 位号配置
     * @return 位号配置集合
     */
    public List<sdbIotDevicePoint> selectsdbIotDevicePointList(sdbIotDevicePoint sdbIotDevicePoint);

    /**
     * 新增位号配置
     * 
     * @param sdbIotDevicePoint 位号配置
     * @return 结果
     */
    public int insertsdbIotDevicePoint(sdbIotDevicePoint sdbIotDevicePoint);

    /**
     * 修改位号配置
     * 
     * @param sdbIotDevicePoint 位号配置
     * @return 结果
     */
    public int updatesdbIotDevicePoint(sdbIotDevicePoint sdbIotDevicePoint);

    /**
     * 删除位号配置
     * 
     * @param id 位号配置ID
     * @return 结果
     */
    public int deletesdbIotDevicePointById(Long id);

    /**
     * 批量删除位号配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletesdbIotDevicePointByIds(Long[] ids);
}
