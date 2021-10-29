package com.sdb.iot.driverManager.service.impl;

import java.util.List;
import com.sdb.base.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdb.iot.driverManager.mapper.sdbIotDriverInfoMapper;
import com.sdb.iot.driverManager.domain.sdbIotDriverInfo;
import com.sdb.iot.driverManager.service.IsdbIotDriverInfoService;

/**
 * 驱动配置Service业务层处理
 * 
 * @author sdb
 * @date 2021-10-21
 */
@Service
public class sdbIotDriverInfoServiceImpl implements IsdbIotDriverInfoService 
{
    @Autowired
    private sdbIotDriverInfoMapper sdbIotDriverInfoMapper;

    /**
     * 查询驱动配置
     * 
     * @param id 驱动配置ID
     * @return 驱动配置
     */
    @Override
    public sdbIotDriverInfo selectsdbIotDriverInfoById(Long id)
    {
        return sdbIotDriverInfoMapper.selectsdbIotDriverInfoById(id);
    }

    /**
     * 查询驱动配置列表
     * 
     * @param sdbIotDriverInfo 驱动配置
     * @return 驱动配置
     */
    @Override
    public List<sdbIotDriverInfo> selectsdbIotDriverInfoList(sdbIotDriverInfo sdbIotDriverInfo)
    {
        return sdbIotDriverInfoMapper.selectsdbIotDriverInfoList(sdbIotDriverInfo);
    }

    /**
     * 新增驱动配置
     * 
     * @param sdbIotDriverInfo 驱动配置
     * @return 结果
     */
    @Override
    public int insertsdbIotDriverInfo(sdbIotDriverInfo sdbIotDriverInfo)
    {
        sdbIotDriverInfo.setCreateTime(DateUtils.getNowDate());
        return sdbIotDriverInfoMapper.insertsdbIotDriverInfo(sdbIotDriverInfo);
    }

    /**
     * 修改驱动配置
     * 
     * @param sdbIotDriverInfo 驱动配置
     * @return 结果
     */
    @Override
    public int updatesdbIotDriverInfo(sdbIotDriverInfo sdbIotDriverInfo)
    {
        return sdbIotDriverInfoMapper.updatesdbIotDriverInfo(sdbIotDriverInfo);
    }

    /**
     * 批量删除驱动配置
     * 
     * @param ids 需要删除的驱动配置ID
     * @return 结果
     */
    @Override
    public int deletesdbIotDriverInfoByIds(Long[] ids)
    {
        return sdbIotDriverInfoMapper.deletesdbIotDriverInfoByIds(ids);
    }

    /**
     * 删除驱动配置信息
     * 
     * @param id 驱动配置ID
     * @return 结果
     */
    @Override
    public int deletesdbIotDriverInfoById(Long id)
    {
        return sdbIotDriverInfoMapper.deletesdbIotDriverInfoById(id);
    }
}
