package com.sdb.iot.profileManager.service.impl;

import java.util.List;
import com.sdb.base.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sdb.iot.profileManager.mapper.sdbIotPointMapper;
import com.sdb.iot.profileManager.domain.sdbIotPoint;
import com.sdb.iot.profileManager.service.IsdbIotPointService;

/**
 * 位号Service业务层处理
 * 
 * @author sdb
 * @date 2021-10-20
 */
@Service
public class sdbIotPointServiceImpl implements IsdbIotPointService 
{
    @Autowired
    private sdbIotPointMapper sdbIotPointMapper;

    /**
     * 查询位号
     * 
     * @param id 位号ID
     * @return 位号
     */
    @Override
    public sdbIotPoint selectsdbIotPointById(Long id)
    {
        return sdbIotPointMapper.selectsdbIotPointById(id);
    }

    /**
     * 查询位号列表
     * 
     * @param sdbIotPoint 位号
     * @return 位号
     */
    @Override
    public List<sdbIotPoint> selectsdbIotPointList(sdbIotPoint sdbIotPoint)
    {
        return sdbIotPointMapper.selectsdbIotPointList(sdbIotPoint);
    }

    /**
     * 新增位号
     * 
     * @param sdbIotPoint 位号
     * @return 结果
     */
    @Override
    public int insertsdbIotPoint(sdbIotPoint sdbIotPoint)
    {
        sdbIotPoint.setCreateTime(DateUtils.getNowDate());
        return sdbIotPointMapper.insertsdbIotPoint(sdbIotPoint);
    }

    /**
     * 修改位号
     * 
     * @param sdbIotPoint 位号
     * @return 结果
     */
    @Override
    public int updatesdbIotPoint(sdbIotPoint sdbIotPoint)
    {
        return sdbIotPointMapper.updatesdbIotPoint(sdbIotPoint);
    }

    /**
     * 批量删除位号
     * 
     * @param ids 需要删除的位号ID
     * @return 结果
     */
    @Override
    public int deletesdbIotPointByIds(Long[] ids)
    {
        return sdbIotPointMapper.deletesdbIotPointByIds(ids);
    }

    /**
     * 删除位号信息
     * 
     * @param id 位号ID
     * @return 结果
     */
    @Override
    public int deletesdbIotPointById(Long id)
    {
        return sdbIotPointMapper.deletesdbIotPointById(id);
    }
}
