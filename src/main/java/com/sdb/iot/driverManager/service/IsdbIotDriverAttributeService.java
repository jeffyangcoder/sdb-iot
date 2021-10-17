package com.sdb.iot.driverManager.service;

import java.util.List;
import com.sdb.iot.driverManager.domain.sdbIotDriverAttribute;

/**
 * 驱动属性信息Service接口
 * 
 * @author sdb
 * @date 2021-10-17
 */
public interface IsdbIotDriverAttributeService 
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
     * 批量删除驱动属性信息
     * 
     * @param ids 需要删除的驱动属性信息ID
     * @return 结果
     */
    public int deletesdbIotDriverAttributeByIds(Long[] ids);

    /**
     * 删除驱动属性信息信息
     * 
     * @param id 驱动属性信息ID
     * @return 结果
     */
    public int deletesdbIotDriverAttributeById(Long id);
}
