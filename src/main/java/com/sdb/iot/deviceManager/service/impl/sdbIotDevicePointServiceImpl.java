package com.sdb.iot.deviceManager.service.impl;

import java.util.List;
import com.sdb.base.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdb.iot.deviceManager.mapper.sdbIotDevicePointMapper;
import com.sdb.iot.deviceManager.domain.sdbIotDevicePoint;
import com.sdb.iot.deviceManager.service.IsdbIotDevicePointService;

import javax.annotation.Resource;

/**
 * 位号配置Service业务层处理
 * 
 * @author sdb
 * @date 2021-10-20
 */
@Service
public class sdbIotDevicePointServiceImpl implements IsdbIotDevicePointService 
{
    @Resource
    private sdbIotDevicePointMapper sdbIotDevicePointMapper;

    /**
     * 查询位号配置
     * 
     * @param id 位号配置ID
     * @return 位号配置
     */
    @Override
    public sdbIotDevicePoint selectsdbIotDevicePointById(Long id)
    {
        return sdbIotDevicePointMapper.selectsdbIotDevicePointById(id);
    }

    /**
     * 查询位号配置列表
     * 
     * @param sdbIotDevicePoint 位号配置
     * @return 位号配置
     */
    @Override
    public List<sdbIotDevicePoint> selectsdbIotDevicePointList(sdbIotDevicePoint sdbIotDevicePoint)
    {
        return sdbIotDevicePointMapper.selectsdbIotDevicePointList(sdbIotDevicePoint);
    }

    /**
     * 新增位号配置
     * 
     * @param sdbIotDevicePoint 位号配置
     * @return 结果
     */
    @Override
    public int insertsdbIotDevicePoint(sdbIotDevicePoint sdbIotDevicePoint)
    {
        sdbIotDevicePoint.setCreateTime(DateUtils.getNowDate());
        return sdbIotDevicePointMapper.insertsdbIotDevicePoint(sdbIotDevicePoint);
    }

    /**
     * 修改位号配置
     * 
     * @param sdbIotDevicePoint 位号配置
     * @return 结果
     */
    @Override
    public int updatesdbIotDevicePoint(sdbIotDevicePoint sdbIotDevicePoint)
    {
        sdbIotDevicePoint.setUpdateTime(DateUtils.getNowDate());
        return sdbIotDevicePointMapper.updatesdbIotDevicePoint(sdbIotDevicePoint);
    }

    /**
     * 批量删除位号配置
     * 
     * @param ids 需要删除的位号配置ID
     * @return 结果
     */
    @Override
    public int deletesdbIotDevicePointByIds(Long[] ids)
    {
        return sdbIotDevicePointMapper.deletesdbIotDevicePointByIds(ids);
    }

    /**
     * 删除位号配置信息
     * 
     * @param id 位号配置ID
     * @return 结果
     */
    @Override
    public int deletesdbIotDevicePointById(Long id)
    {
        return sdbIotDevicePointMapper.deletesdbIotDevicePointById(id);
    }
}
