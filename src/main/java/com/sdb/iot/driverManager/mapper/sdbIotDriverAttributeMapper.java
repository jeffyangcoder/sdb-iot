package com.sdb.iot.driverManager.mapper;

import java.util.List;
import com.sdb.iot.driverManager.domain.sdbIotDriverAttribute;
import com.sdb.iot.driverManager.domain.sdbIotDriver;

/**
 * 驱动属性信息Mapper接口
 * 
 * @author sdb
 * @date 2021-10-17
 */
public interface sdbIotDriverAttributeMapper 
{
    /**
     * 查询驱动属性信息
     * 
     * @param id 驱动属性信息ID
     * @return 驱动属性信息
     */
    public sdbIotDriverAttribute selectsdbIotDriverAttributeById(Long id);

    /**
     * 查询驱动属性信息列表
     * 
     * @param sdbIotDriverAttribute 驱动属性信息
     * @return 驱动属性信息集合
     */
    public List<sdbIotDriverAttribute> selectsdbIotDriverAttributeList(sdbIotDriverAttribute sdbIotDriverAttribute);

    /**
     * 新增驱动属性信息
     * 
     * @param sdbIotDriverAttribute 驱动属性信息
     * @return 结果
     */
    public int insertsdbIotDriverAttribute(sdbIotDriverAttribute sdbIotDriverAttribute);

    /**
     * 修改驱动属性信息
     * 
     * @param sdbIotDriverAttribute 驱动属性信息
     * @return 结果
     */
    public int updatesdbIotDriverAttribute(sdbIotDriverAttribute sdbIotDriverAttribute);

    /**
     * 删除驱动属性信息
     * 
     * @param id 驱动属性信息ID
     * @return 结果
     */
    public int deletesdbIotDriverAttributeById(Long id);

    /**
     * 批量删除驱动属性信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletesdbIotDriverAttributeByIds(Long[] ids);

    /**
     * 批量删除驱动状态
     * 
     * @param customerIds 需要删除的数据ID
     * @return 结果
     */
    public int deletesdbIotDriverByIds(Long[] ids);
    
    /**
     * 批量新增驱动状态
     * 
     * @param sdbIotDriverList 驱动状态列表
     * @return 结果
     */
    public int batchsdbIotDriver(List<sdbIotDriver> sdbIotDriverList);
    

    /**
     * 通过驱动属性信息ID删除驱动状态信息
     * 
     * @param id 驱动属性信息ID
     * @return 结果
     */
    public int deletesdbIotDriverById(Long id);
}
