package com.sdb.iot.driverManager.service.impl;

import java.util.List;
import com.sdb.base.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdb.iot.driverManager.mapper.sdbIotPointInfoMapper;
import com.sdb.iot.driverManager.domain.sdbIotPointInfo;
import com.sdb.iot.driverManager.service.IsdbIotPointInfoService;

/**
 * 位号属性Service业务层处理
 * 
 * @author sdb
 * @date 2021-10-19
 */
@Service
public class sdbIotPointInfoServiceImpl implements IsdbIotPointInfoService 
{
    @Autowired
    private sdbIotPointInfoMapper sdbIotPointInfoMapper;

    /**
     * 查询位号属性
     * 
     * @param id 位号属性ID
     * @return 位号属性
     */
    @Override
    public sdbIotPointInfo selectsdbIotPointInfoById(Long id)
    {
        return sdbIotPointInfoMapper.selectsdbIotPointInfoById(id);
    }

    /**
     * 查询位号属性列表
     * 
     * @param sdbIotPointInfo 位号属性
     * @return 位号属性
     */
    @Override
    public List<sdbIotPointInfo> selectsdbIotPointInfoList(sdbIotPointInfo sdbIotPointInfo)
    {
        return sdbIotPointInfoMapper.selectsdbIotPointInfoList(sdbIotPointInfo);
    }

    /**
     * 新增位号属性
     * 
     * @param sdbIotPointInfo 位号属性
     * @return 结果
     */
    @Override
    public int insertsdbIotPointInfo(sdbIotPointInfo sdbIotPointInfo)
    {
        sdbIotPointInfo.setCreateTime(DateUtils.getNowDate());
        return sdbIotPointInfoMapper.insertsdbIotPointInfo(sdbIotPointInfo);
    }

    /**
     * 修改位号属性
     * 
     * @param sdbIotPointInfo 位号属性
     * @return 结果
     */
    @Override
    public int updatesdbIotPointInfo(sdbIotPointInfo sdbIotPointInfo)
    {
        return sdbIotPointInfoMapper.updatesdbIotPointInfo(sdbIotPointInfo);
    }

    /**
     * 批量删除位号属性
     * 
     * @param ids 需要删除的位号属性ID
     * @return 结果
     */
    @Override
    public int deletesdbIotPointInfoByIds(Long[] ids)
    {
        return sdbIotPointInfoMapper.deletesdbIotPointInfoByIds(ids);
    }

    /**
     * 删除位号属性信息
     * 
     * @param id 位号属性ID
     * @return 结果
     */
    @Override
    public int deletesdbIotPointInfoById(Long id)
    {
        return sdbIotPointInfoMapper.deletesdbIotPointInfoById(id);
    }
}
