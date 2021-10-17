package com.sdb.iot.driverManager.service.impl;

import java.util.List;
import com.sdb.base.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.sdb.base.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.sdb.iot.driverManager.domain.sdbIotDriver;
import com.sdb.iot.driverManager.mapper.sdbIotDriverAttributeMapper;
import com.sdb.iot.driverManager.domain.sdbIotDriverAttribute;
import com.sdb.iot.driverManager.service.IsdbIotDriverAttributeService;

/**
 * 驱动属性信息Service业务层处理
 * 
 * @author sdb
 * @date 2021-10-17
 */
@Service
public class sdbIotDriverAttributeServiceImpl implements IsdbIotDriverAttributeService 
{
    @Autowired
    private sdbIotDriverAttributeMapper sdbIotDriverAttributeMapper;

    /**
     * 查询驱动属性信息
     * 
     * @param id 驱动属性信息ID
     * @return 驱动属性信息
     */
    @Override
    public sdbIotDriverAttribute selectsdbIotDriverAttributeById(Long id)
    {
        return sdbIotDriverAttributeMapper.selectsdbIotDriverAttributeById(id);
    }

    /**
     * 查询驱动属性信息列表
     * 
     * @param sdbIotDriverAttribute 驱动属性信息
     * @return 驱动属性信息
     */
    @Override
    public List<sdbIotDriverAttribute> selectsdbIotDriverAttributeList(sdbIotDriverAttribute sdbIotDriverAttribute)
    {
        return sdbIotDriverAttributeMapper.selectsdbIotDriverAttributeList(sdbIotDriverAttribute);
    }

    /**
     * 新增驱动属性信息
     * 
     * @param sdbIotDriverAttribute 驱动属性信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertsdbIotDriverAttribute(sdbIotDriverAttribute sdbIotDriverAttribute)
    {
        sdbIotDriverAttribute.setCreateTime(DateUtils.getNowDate());
        int rows = sdbIotDriverAttributeMapper.insertsdbIotDriverAttribute(sdbIotDriverAttribute);
        insertsdbIotDriver(sdbIotDriverAttribute);
        return rows;
    }

    /**
     * 修改驱动属性信息
     * 
     * @param sdbIotDriverAttribute 驱动属性信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updatesdbIotDriverAttribute(sdbIotDriverAttribute sdbIotDriverAttribute)
    {
        sdbIotDriverAttributeMapper.deletesdbIotDriverById(sdbIotDriverAttribute.getId());
        insertsdbIotDriver(sdbIotDriverAttribute);
        return sdbIotDriverAttributeMapper.updatesdbIotDriverAttribute(sdbIotDriverAttribute);
    }

    /**
     * 批量删除驱动属性信息
     * 
     * @param ids 需要删除的驱动属性信息ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deletesdbIotDriverAttributeByIds(Long[] ids)
    {
        sdbIotDriverAttributeMapper.deletesdbIotDriverByIds(ids);
        return sdbIotDriverAttributeMapper.deletesdbIotDriverAttributeByIds(ids);
    }

    /**
     * 删除驱动属性信息信息
     * 
     * @param id 驱动属性信息ID
     * @return 结果
     */
    @Override
    public int deletesdbIotDriverAttributeById(Long id)
    {
        sdbIotDriverAttributeMapper.deletesdbIotDriverById(id);
        return sdbIotDriverAttributeMapper.deletesdbIotDriverAttributeById(id);
    }

    /**
     * 新增驱动状态信息
     * 
     * @param sdbIotDriverAttribute 驱动属性信息对象
     */
    public void insertsdbIotDriver(sdbIotDriverAttribute sdbIotDriverAttribute)
    {
        List<sdbIotDriver> sdbIotDriverList = sdbIotDriverAttribute.getsdbIotDriverList();
        Long id = sdbIotDriverAttribute.getId();
        if (StringUtils.isNotNull(sdbIotDriverList))
        {
            List<sdbIotDriver> list = new ArrayList<sdbIotDriver>();
            for (sdbIotDriver sdbIotDriver : sdbIotDriverList)
            {
                sdbIotDriver.setId(id);
                list.add(sdbIotDriver);
            }
            if (list.size() > 0)
            {
                sdbIotDriverAttributeMapper.batchsdbIotDriver(list);
            }
        }
    }
}
