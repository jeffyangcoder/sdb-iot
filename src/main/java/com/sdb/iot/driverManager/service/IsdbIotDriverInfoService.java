package com.sdb.iot.driverManager.service;

import java.util.List;
import com.sdb.iot.driverManager.domain.sdbIotDriverInfo;

/**
 * 驱动配置Service接口
 * 
 * @author sdb
 * @date 2021-10-21
 */
public interface IsdbIotDriverInfoService 
{
    /**
     * 查询驱动配置
     * 
     * @param id 驱动配置ID
     * @return 驱动配置
     */
    public sdbIotDriverInfo selectsdbIotDriverInfoById(Long id);

    /**
     * 查询驱动配置列表
     * 
     * @param sdbIotDriverInfo 驱动配置
     * @return 驱动配置集合
     */
    public List<sdbIotDriverInfo> selectsdbIotDriverInfoList(sdbIotDriverInfo sdbIotDriverInfo);

    /**
     * 新增驱动配置
     * 
     * @param sdbIotDriverInfo 驱动配置
     * @return 结果
     */
    public int insertsdbIotDriverInfo(sdbIotDriverInfo sdbIotDriverInfo);

    /**
     * 修改驱动配置
     * 
     * @param sdbIotDriverInfo 驱动配置
     * @return 结果
     */
    public int updatesdbIotDriverInfo(sdbIotDriverInfo sdbIotDriverInfo);

    /**
     * 批量删除驱动配置
     * 
     * @param ids 需要删除的驱动配置ID
     * @return 结果
     */
    public int deletesdbIotDriverInfoByIds(Long[] ids);

    /**
     * 删除驱动配置信息
     * 
     * @param id 驱动配置ID
     * @return 结果
     */
    public int deletesdbIotDriverInfoById(Long id);
}
