package com.sdb.iot.driverManager.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdb.iot.driverManager.mapper.sdbIotDriverMapper;
import com.sdb.iot.driverManager.domain.sdbIotDriver;
import com.sdb.iot.driverManager.service.IsdbIotDriverService;

/**
 * 驱动状态Service业务层处理
 * 
 * @author sdb
 * @date 2021-10-17
 */
@Service
public class sdbIotDriverServiceImpl implements IsdbIotDriverService 
{
    @Autowired
    private sdbIotDriverMapper sdbIotDriverMapper;

    /**
     * 查询驱动状态
     * 
     * @param id 驱动状态ID
     * @return 驱动状态
     */
    @Override
    public sdbIotDriver selectsdbIotDriverById(Long id)
    {
        return sdbIotDriverMapper.selectsdbIotDriverById(id);
    }

    /**
     * 查询驱动状态列表
     * 
     * @param sdbIotDriver 驱动状态
     * @return 驱动状态
     */
    @Override
    public List<sdbIotDriver> selectsdbIotDriverList(sdbIotDriver sdbIotDriver)
    {
        return sdbIotDriverMapper.selectsdbIotDriverList(sdbIotDriver);
    }

    /**
     * 新增驱动状态
     * 
     * @param sdbIotDriver 驱动状态
     * @return 结果
     */
    @Override
    public int insertsdbIotDriver(sdbIotDriver sdbIotDriver)
    {
        return sdbIotDriverMapper.insertsdbIotDriver(sdbIotDriver);
    }

    /**
     * 修改驱动状态
     * 
     * @param sdbIotDriver 驱动状态
     * @return 结果
     */
    @Override
    public int updatesdbIotDriver(sdbIotDriver sdbIotDriver)
    {
        return sdbIotDriverMapper.updatesdbIotDriver(sdbIotDriver);
    }

    /**
     * 批量删除驱动状态
     * 
     * @param ids 需要删除的驱动状态ID
     * @return 结果
     */
    @Override
    public int deletesdbIotDriverByIds(Long[] ids)
    {
        return sdbIotDriverMapper.deletesdbIotDriverByIds(ids);
    }

    /**
     * 删除驱动状态信息
     * 
     * @param id 驱动状态ID
     * @return 结果
     */
    @Override
    public int deletesdbIotDriverById(Long id)
    {
        return sdbIotDriverMapper.deletesdbIotDriverById(id);
    }
}
